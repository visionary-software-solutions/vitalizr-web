package visionary.software.vitalizr.web;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.List;

@Controller("/bmi")
public class BodyMassIndexController {
    @Get
    public List<BodyMassIndex> list() {
        final String id = BodyMassIndex.createCannedNick().lifeform;
        return VitalizrClient.LIST_BMI.getVital(id);
    }
}
