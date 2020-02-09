package visionary.software.vitalizr.web.weight;

import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
class WeightControllerTest {
    @Inject
    EmbeddedServer server;

    @Inject
    WeightClient client;

    @Test
    void listWeightResponse() {
        final List<Weight> response = client.list();
        final List<Weight> expected = new ArrayList<>();
        expected.add(Weight.createCannedNick());
        assertEquals(expected, response);
    }
}
