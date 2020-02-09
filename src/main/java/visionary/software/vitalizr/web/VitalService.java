package visionary.software.vitalizr.web;

import java.util.List;

public interface VitalService<T extends Vital> {
    List<T> getVitalById(String id);
}
