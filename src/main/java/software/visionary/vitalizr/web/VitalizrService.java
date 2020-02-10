package software.visionary.vitalizr.web;

import javax.inject.Singleton;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Singleton
final class VitalizrService implements VitalService {

    private Logger LOG = Logger.getLogger(VitalizrService.class.getSimpleName());

    @Override
    public List<Vital> getVitalById(final String type, final String id) {
        return toVitals(type, id, mapRequest(type, id));

    }

    private String mapRequest(final String type, final String id) {
        switch(type) {
            case "bmi": return VitalizrClient.LIST_BMI.forId(id);
            case "weight": return VitalizrClient.LIST_WEIGHT.forId(id);
            case "fat": return VitalizrClient.LIST_FAT.forId(id);
            case "o2": return VitalizrClient.LIST_O2.forId(id);
            case "bpm": return VitalizrClient.LIST_PULSE.forId(id);
            case "temp": return VitalizrClient.LIST_TEMP.forId(id);
            case "sugar": return VitalizrClient.LIST_SUGAR.forId(id);
            case "water": return VitalizrClient.LIST_WATER.forId(id);
            case "bp": return VitalizrClient.LIST_BP.forId(id);
            default: throw new UnsupportedOperationException("dont know about " + type);
        }
    }

    private List<Vital> toVitals(final String type, final String id, final String response) {
        final String[] parts = response.split("\u0023");
        return Arrays.stream(parts).map(record -> {
            LOG.info("Record is " + record);
            final String[] fields = record.split("\u2049");
            LOG.info("The fields are " + Arrays.toString(fields));
            return new Vital(type, Instant.ofEpochMilli(Long.parseLong(fields[0])), Double.parseDouble(fields[1]), fields[2], id);
        }).collect(Collectors.toUnmodifiableList());
    }
}
