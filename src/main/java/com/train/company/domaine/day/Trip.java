package com.train.company.domaine.day;

import com.train.company.domaine.city.Station;

import java.io.Serializable;
import java.util.Objects;

public class Trip implements Serializable {

    private Station stationStart;
    private Station stationEnd;
    private long startedJourneyAt;
    private int costInCents;
    private int zoneFrom;
    private int zoneTo;

    public Trip() {
    }

    public void setStationStart(Station stationStart) {
        this.stationStart = stationStart;
    }

    public void setStationEnd(Station stationEnd) {
        this.stationEnd = stationEnd;
    }

    public void setStartedJourneyAt(long startedJourneyAt) {
        this.startedJourneyAt = startedJourneyAt;
    }

    public void setCostInCents(int costInCents) {
        this.costInCents = costInCents;
    }

    public void setZoneFrom(int zoneFrom) {
        this.zoneFrom = zoneFrom;
    }

    public void setZoneTo(int zoneTo) {
        this.zoneTo = zoneTo;
    }

    private Trip(Builder builder) {
        this.stationStart = builder.stationStart;
        this.stationEnd = builder.stationEnd;
        this.startedJourneyAt = builder.startedJourneyAt;
        this.costInCents = builder.costInCents;
        this.zoneFrom = builder.zoneFrom;
        this.zoneTo = builder.zoneTo;
    }

    public static Builder newTrip() {
        return new Builder();
    }

    public Station getStationStart() {
        return stationStart;
    }

    public Station getStationEnd() {
        return stationEnd;
    }

    public long getStartedJourneyAt() {
        return startedJourneyAt;
    }

    public int getCostInCents() {
        return costInCents;
    }

    public int getZoneFrom() {
        return zoneFrom;
    }

    public int getZoneTo() {
        return zoneTo;
    }


    public static final class Builder {
        private Station stationStart;
        private Station stationEnd;
        private long startedJourneyAt;
        private int costInCents;
        private int zoneFrom;
        private int zoneTo;

        private Builder() {
        }

        public Trip build() {
            return new Trip(this);
        }

        public Builder stationStart(Station stationStart) {
            this.stationStart = stationStart;
            return this;
        }

        public Builder stationEnd(Station stationEnd) {
            this.stationEnd = stationEnd;
            return this;
        }

        public Builder startedJourneyAt(long startedJourneyAt) {
            this.startedJourneyAt = startedJourneyAt;
            return this;
        }

        public Builder costInCents(int costInCents) {
            this.costInCents = costInCents;
            return this;
        }

        public Builder zoneFrom(int zoneFrom) {
            this.zoneFrom = zoneFrom;
            return this;
        }

        public Builder zoneTo(int zoneTo) {
            this.zoneTo = zoneTo;
            return this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return startedJourneyAt == trip.startedJourneyAt &&
                costInCents == trip.costInCents &&
                zoneFrom == trip.zoneFrom &&
                zoneTo == trip.zoneTo &&
                stationStart == trip.stationStart &&
                stationEnd == trip.stationEnd;
    }

    @Override
    public int hashCode() {
        return Objects.hash(stationStart, stationEnd, startedJourneyAt, costInCents, zoneFrom, zoneTo);
    }

    @Override
    public String toString() {
        return "Trip{" +
                "stationStart=" + stationStart +
                ", stationEnd=" + stationEnd +
                ", startedJourneyAt=" + startedJourneyAt +
                ", costInCents=" + costInCents +
                ", zoneFrom=" + zoneFrom +
                ", zoneTo=" + zoneTo +
                '}';
    }
}
