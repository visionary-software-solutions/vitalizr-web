package visionary.software.vitalizr.web.bmi;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import visionary.software.vitalizr.web.Vital;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public final class BodyMassIndex implements Vital {
    public final Instant time;
    public final double quantity;
    public final String lifeform;

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final BodyMassIndex weight = (BodyMassIndex) o;
        return Double.compare(weight.quantity, quantity) == 0 &&
                Objects.equals(time, weight.time) &&
                Objects.equals(lifeform, weight.lifeform);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, quantity, lifeform);
    }

    @JsonCreator
    public BodyMassIndex(@JsonProperty("time") final Instant time,
                         @JsonProperty("quantity") final Number quantity,
                         @JsonProperty("owner") final String lifeform) {
        this.time = time;
        this.quantity = quantity.doubleValue();
        this.lifeform = lifeform;
    }

    static BodyMassIndex createCannedNick() {
        final Instant time = Instant.ofEpochSecond(1580485835);
        final Number quantity = 234.6;
        final String lifeform = UUID.fromString("7ab35698-21f9-463e-8e74-bd3d56109336").toString();
        return new BodyMassIndex(time, quantity, lifeform);
    }

}
