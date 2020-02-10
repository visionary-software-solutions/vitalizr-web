package software.visionary.vitalizr.web;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public final class Vital {
    public final String type;
    public final Instant time;
    public final double quantity;
    public final String unit;
    public final String lifeform;

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Vital v = (Vital) o;
        return Double.compare(v.quantity, quantity) == 0 &&
                Objects.equals(time, v.time) &&
                Objects.equals(unit, v.unit) &&
                Objects.equals(lifeform, v.lifeform);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, quantity, unit, lifeform);
    }

    @JsonCreator
    public Vital(@JsonProperty("type") final String type,
                 @JsonProperty("time") final Instant time,
                 @JsonProperty("quantity") final Number quantity,
                 @JsonProperty("unit") final String unit,
                 @JsonProperty("owner") final String lifeform) {
        this.type = type;
        this.time = time;
        this.quantity = quantity.doubleValue();
        this.unit = unit;
        this.lifeform = lifeform;
    }

    static Vital createCannedWeight() {
        final Instant time = Instant.ofEpochSecond(1580485835);
        final Number quantity = 234.6;
        final String lifeform = UUID.fromString("7ab35698-21f9-463e-8e74-bd3d56109336").toString();
        return new Vital("weight", time, quantity, "lb", lifeform);
    }

    static Vital createCannedBMI() {
        final Instant time = Instant.ofEpochSecond(1580485835);
        final Number quantity = 234.6;
        final String lifeform = UUID.fromString("7ab35698-21f9-463e-8e74-bd3d56109336").toString();
        return new Vital("bmi",time, quantity, "", lifeform);
    }
}
