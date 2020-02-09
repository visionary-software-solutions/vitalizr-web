package visionary.software.vitalizr.web.weight;

import visionary.software.vitalizr.web.VitalService;
import visionary.software.vitalizr.web.VitalizrClient;

import javax.inject.Singleton;
import java.time.Instant;
import java.util.*;

@Singleton
final class WeightService implements VitalService<Weight> {
    static List<Weight> fromString(final String s, final String lifeform) {
        final String[] parts = s.split("\u2049");
        final Deque<String> deque = new ArrayDeque<>(Arrays.asList(parts));
        final List<Weight> returned = new ArrayList<>();
        while(!deque.isEmpty()) {
            returned.add(new Weight(Instant.ofEpochMilli(Long.parseLong(deque.pop())), Double.parseDouble(deque.pop()), deque.pop(), lifeform));
        }
        return returned;
    }

    @Override
    public List<Weight> getVitalById(final String id) {
        return fromString(VitalizrClient.LIST_WEIGHT.forId(id), id);
    }
}
