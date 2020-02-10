package software.visionary.vitalizr.web;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;

import javax.inject.Inject;
import java.util.List;
import java.util.Objects;

@Controller("/vital")
public class VitalController {
    private final VitalService service;

    @Inject
    public VitalController(final VitalService vitalService) {
        this.service = Objects.requireNonNull(vitalService);
    }

    @Get("/{type}/{id}")
    public List<Vital> list(@PathVariable final String type, @PathVariable final String id) {
        return service.getVitalById(type, id);
    }
}
