package software.visionary.vitalizr.web;

import javax.inject.Singleton;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Singleton
final class VitalSerializationStrategy implements Function<String, List<Vital>> {
    public static final String TOKEN = "\uD83C\uDF01";
    private static final Logger LOG = Logger.getLogger(VitalizrService.class.getSimpleName());

    @Override
    public List<Vital> apply(final String s) {
        final String[] pieces = s.split(TOKEN);
        final String[] parts = pieces[2].replace("\u0004", "").split("\u0023");
        return Arrays.stream(parts).filter(string -> !string.isBlank()).map(record -> {
            LOG.info("Record is " + record);
            final String[] fields = record.split("\u2049");
            LOG.info("The fields are " + Arrays.toString(fields));
            return new Vital(pieces[0], Instant.ofEpochMilli(Long.parseLong(fields[0])), Double.parseDouble(fields[1]), fields.length == 3 ? fields[2] : "", pieces[1]);
        }).collect(Collectors.toUnmodifiableList());
    }
}
