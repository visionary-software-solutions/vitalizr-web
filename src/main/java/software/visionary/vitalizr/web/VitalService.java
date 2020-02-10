package software.visionary.vitalizr.web;

import java.util.List;

public interface VitalService {
    List<Vital> getVitalById(String type, String id);
}
