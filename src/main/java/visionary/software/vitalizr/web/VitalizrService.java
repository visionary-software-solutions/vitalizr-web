package visionary.software.vitalizr.web;

import javax.inject.Singleton;
import java.util.List;

@Singleton
final class VitalizrService implements VitalService {

    @Override
    public List<Vital> getVitalById(final String type, final String id) {
        switch(type) {
            case "bmi": return toVitals(VitalizrClient.LIST_BMI.forId(id));
            case "weight": return toVitals(VitalizrClient.LIST_WEIGHT.forId(id));
            default: throw new UnsupportedOperationException("dont know about " + type);
        }
    }

    private List<Vital> toVitals(final String response) {
        return null;
    }
}
