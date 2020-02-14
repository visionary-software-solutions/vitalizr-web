package software.visionary.vitalizr.web;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VitalSerializationStrategyTest {
    @Test
    void handlesValidRecord() {
        final String type = "water";
        final String id = "7ab35698-21f9-463e-8e74-bd3d56109336";
        final String response = "1580724225662⁉52.10⁉#1580816474979⁉53.00⁉#1580901394623⁉53.70⁉#1580991845474⁉52.80⁉#1581091452721⁉52.90⁉";
        final String input = String.format("%s%s%s%s%s", type, VitalSerializationStrategy.TOKEN, id, VitalSerializationStrategy.TOKEN, response);
        final VitalSerializationStrategy toTest = new VitalSerializationStrategy();
        final List<Vital> result = toTest.apply(input);
        assertEquals(5, result.size());
        assertTrue(result.stream().allMatch( vital -> vital.type.equalsIgnoreCase(type)));
        assertTrue(result.stream().allMatch( vital -> vital.lifeform.equalsIgnoreCase(id)));
        assertTrue(result.stream().anyMatch( vital -> vital.quantity == 52.10 ));
        assertTrue(result.stream().anyMatch( vital -> vital.quantity == 53.00 ));
        assertTrue(result.stream().anyMatch( vital -> vital.quantity == 53.70 ));
        assertTrue(result.stream().anyMatch( vital -> vital.quantity == 52.80 ));
        assertTrue(result.stream().anyMatch( vital -> vital.quantity == 52.90 ));
    }

    @Test
    void handlesBlankCorrectly() {
        final String type = "water";
        final String id = "7ab35698-21f9-463e-8e74-bd3d56109336";
        final String response = "\u0004";
        final String input = String.format("%s%s%s%s%s", type, VitalSerializationStrategy.TOKEN, id, VitalSerializationStrategy.TOKEN, response);
        final VitalSerializationStrategy toTest = new VitalSerializationStrategy();
        final List<Vital> result = toTest.apply(input);
        assertEquals(0, result.size());
    }
}
