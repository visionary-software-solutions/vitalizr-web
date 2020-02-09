package visionary.software.vitalizr.web.bmi;

import visionary.software.vitalizr.web.VitalService;
import visionary.software.vitalizr.web.VitalizrClient;

import javax.inject.Singleton;
import java.time.Instant;
import java.util.*;

@Singleton
final class BMIService implements VitalService<BodyMassIndex> {
    static List<BodyMassIndex> fromString(final String s, final String lifeform) {
        final String[] parts = s.split("\u2049");
        final Deque<String> deque = new ArrayDeque<>(Arrays.asList(parts));
        final List<BodyMassIndex> returned = new ArrayList<>();
        while(!deque.isEmpty()) {
            returned.add(new BodyMassIndex(Instant.ofEpochMilli(Long.parseLong(deque.pop())), Double.parseDouble(deque.pop()), lifeform));
        }
        return returned;
    }

    @Override
    public List<BodyMassIndex> getVitalById(final String id) {
        return fromString(VitalizrClient.LIST_BMI.forId(id), id);
    }
}
