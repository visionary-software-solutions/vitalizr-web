package software.visionary.vitalizr.web;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.function.Function;

@Singleton
final class VitalizrService implements VitalService {
    private final Function<String, List<Vital>> serializationStrategy;

    @Inject
    VitalizrService(final VitalSerializationStrategy strategy) {
        this.serializationStrategy = strategy;
    }


    @Override
    public List<Vital> getVitalById(final String type, final String id) {
        return serializationStrategy.apply(String.format("%s%s%s%s%s", type, VitalSerializationStrategy.TOKEN, id, VitalSerializationStrategy.TOKEN, mapRequest(type).forId(id)));

    }

    private VitalizrClient mapRequest(final String type) {
        switch(type) {
            case "bmi": return VitalizrClient.LIST_BMI;
            case "weight": return VitalizrClient.LIST_WEIGHT;
            case "fat": return VitalizrClient.LIST_FAT;
            case "o2": return VitalizrClient.LIST_O2;
            case "bpm": return VitalizrClient.LIST_PULSE;
            case "temp": return VitalizrClient.LIST_TEMP;
            case "sugar": return VitalizrClient.LIST_SUGAR;
            case "water": return VitalizrClient.LIST_WATER;
            case "bp": return VitalizrClient.LIST_BP;
            default: throw new UnsupportedOperationException("dont know about " + type);
        }
    }
}
