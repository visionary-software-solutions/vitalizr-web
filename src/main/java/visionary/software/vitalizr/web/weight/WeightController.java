package visionary.software.vitalizr.web.weight;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import visionary.software.vitalizr.web.VitalService;

import javax.inject.Inject;
import java.util.List;

@Controller("/weight")
public class WeightController {
    @Inject
    VitalService<Weight> service;
    @Get
    public List<Weight> list() {
        final String id = Weight.createCannedNick().lifeform;
        return service.getVitalById(id);
    }
}
