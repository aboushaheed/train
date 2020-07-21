package com.train.company.domaine.day;

import com.train.company.domaine.city.Station;

import java.io.Serializable;
import java.util.Objects;

public class Tap implements Serializable {

    private long unixTimestamp;
    private int customerId;
    private Station station;

    public Tap() {
    }

    private Tap(Builder builder) {
        this.unixTimestamp = builder.unixTimestamp;
        this.customerId = builder.customerId;
        this.station = builder.station;
    }

    public static Builder newTap() {
        return new Builder();
    }


    public long getUnixTimestamp() {
        return unixTimestamp;
    }

    public void setUnixTimestamp(long unixTimestamp) {
        this.unixTimestamp = unixTimestamp;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    @Override
    public String toString() {
        return "Tap{" +
                "unixTimestamp=" + unixTimestamp +
                ", customerId=" + customerId +
                ", station=" + station +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tap tap = (Tap) o;
        return unixTimestamp == tap.unixTimestamp &&
                customerId == tap.customerId &&
                station == tap.station;
    }

    @Override
    public int hashCode() {
        return Objects.hash(unixTimestamp, customerId, station);
    }

    public static final class Builder {
        private long unixTimestamp;
        private int customerId;
        private Station station;

        private Builder() {
        }

        public Tap build() {
            return new Tap(this);
        }

        public Builder unixTimestamp(long unixTimestamp) {
            this.unixTimestamp = unixTimestamp;
            return this;
        }

        public Builder customerId(int customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder station(Station station) {
            this.station = station;
            return this;
        }
    }
}
