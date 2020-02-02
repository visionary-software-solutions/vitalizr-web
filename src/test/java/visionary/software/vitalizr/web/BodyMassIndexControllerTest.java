package visionary.software.vitalizr.web;

import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
class BodyMassIndexControllerTest {
    @Inject
    EmbeddedServer server;

    @Inject
    BMIClient client;

    @Test
    void listResponse() {
        final List<BodyMassIndex> response = client.list();
        final List<BodyMassIndex> expected = new ArrayList<>();
        expected.add(BodyMassIndex.createCannedNick());
        assertEquals(expected, response);
    }
}
