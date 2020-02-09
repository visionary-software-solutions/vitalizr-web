package visionary.software.vitalizr.web.bmi;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import visionary.software.vitalizr.web.VitalService;

import javax.inject.Inject;
import java.util.List;

@Controller("/bmi")
public class BodyMassIndexController {
    @Inject
    VitalService<BodyMassIndex> service;
    @Get
    public List<BodyMassIndex> list() {
        final String id = BodyMassIndex.createCannedNick().lifeform;
        return service.getVitalById(id);
    }
}
