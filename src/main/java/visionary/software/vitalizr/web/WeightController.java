package visionary.software.vitalizr.web;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.List;

@Controller("/weight")
public class WeightController {
    @Get
    public List<Weight> list() {
        final String id = Weight.createCannedNick().lifeform;
        return VitalizrClient.LIST_WEIGHTS.fetchVitals(id);
    }
}
