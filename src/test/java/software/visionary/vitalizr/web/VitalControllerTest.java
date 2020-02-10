package software.visionary.vitalizr.web;

import io.micronaut.context.annotation.Replaces;
import io.micronaut.test.annotation.MicronautTest;
import io.micronaut.test.annotation.MockBean;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
class VitalControllerTest {
    @Inject
    VitalService service;

    @Inject
    VitalClient client;

    @Test
    void listResponse() {
        final List<Vital> response = client.weights("7ab35698-21f9-463e-8e74-bd3d56109336");
        final List<Vital> expected = new ArrayList<>();
        expected.add(Vital.createCannedWeight());
        assertEquals(expected, response);
    }

    @MockBean(VitalService.class)
    @Replaces(VitalizrService.class)
    VitalService service() {
        final List<Vital> result = new ArrayList<>();
        result.add(Vital.createCannedWeight());
        return (type, id) -> result;
    }
}