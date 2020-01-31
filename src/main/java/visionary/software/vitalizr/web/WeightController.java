package visionary.software.vitalizr.web;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.Collections;
import java.util.List;

@Controller("/weight")
public class WeightController {
    @Get
    public List<Weight> list() {
        return Collections.singletonList(Weight.createCannedNick());
    }
}
