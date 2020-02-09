package visionary.software.vitalizr.web.bmi;

import io.micronaut.context.annotation.Replaces;
import io.micronaut.test.annotation.MicronautTest;
import io.micronaut.test.annotation.MockBean;
import org.junit.jupiter.api.Test;
import visionary.software.vitalizr.web.VitalService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
class BodyMassIndexControllerTest {
    @Inject
    BMIService service;

    @Inject
    BMIClient client;

    @Test
    void listResponse() {
        final List<BodyMassIndex> response = client.list();
        final List<BodyMassIndex> expected = new ArrayList<>();
        expected.add(BodyMassIndex.createCannedNick());
        assertEquals(expected, response);
    }

    @MockBean(VitalService.class)
    @Replaces(BMIService.class)
    VitalService<BodyMassIndex> service() {
        final List<BodyMassIndex> result = new ArrayList<>();
        result.add(BodyMassIndex.createCannedNick());
        return id -> result;
    }
}
