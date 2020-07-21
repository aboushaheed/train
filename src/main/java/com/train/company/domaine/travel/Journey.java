package com.train.company.domaine.travel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.train.company.domaine.day.Tap;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Journey implements Serializable {

    @JsonProperty("taps")
    private List<Tap> taps;

    public Journey() {
    }

    private Journey(Builder builder) {
        this.taps = builder.taps;
    }

    public static Builder newJourney() {
        return new Builder();
    }


    public List<Tap> getTaps() {
        return taps;
    }

    public void setTaps(List<Tap> taps) {
        this.taps = taps;
    }

    @Override
    public String toString() {
        return "Journey{" +
                "taps=" + taps +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Journey journey = (Journey) o;
        return taps.equals(journey.taps);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taps);
    }

    public static final class Builder {
        private List<Tap> taps;

        private Builder() {
        }

        public Journey build() {
            return new Journey(this);
        }

        public Builder taps(List<Tap> taps) {
            this.taps = taps;
            return this;
        }
    }
}
