package visionary.software.vitalizr.web;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public final class Weight {
    public final Instant time;
    public final double quantity;
    public final String unit;
    public final String lifeform;

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Weight weight = (Weight) o;
        return Double.compare(weight.quantity, quantity) == 0 &&
                Objects.equals(time, weight.time) &&
                Objects.equals(unit, weight.unit) &&
                Objects.equals(lifeform, weight.lifeform);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, quantity, unit, lifeform);
    }

    @JsonCreator
    public Weight(@JsonProperty("time") final Instant time,
                  @JsonProperty("quantity") final Number quantity,
                  @JsonProperty("unit") final String unit,
                  @JsonProperty("owner") final String lifeform) {
        this.time = time;
        this.quantity = quantity.doubleValue();
        this.unit = unit;
        this.lifeform = lifeform;
    }

    static Weight createCannedNick() {
        final Instant time = Instant.ofEpochSecond(1580485835);
        final Number quantity = 234.6;
        final String lifeform = UUID.fromString("7ab35698-21f9-463e-8e74-bd3d56109336").toString();
        return new Weight(time, quantity, "lb", lifeform);
    }
}
