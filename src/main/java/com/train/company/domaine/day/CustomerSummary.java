package com.train.company.domaine.day;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomerSummary implements Serializable {

    private int customerId;
    private int totalCostInCents;
    private List<Trip> trips = new ArrayList<>();

    public CustomerSummary() {
    }

    private CustomerSummary(Builder builder) {
        this.customerId = builder.customerId;
        this.totalCostInCents = builder.totalCostInCents;
        this.trips = builder.trips;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setTotalCostInCents(int totalCostInCents) {
        this.totalCostInCents = totalCostInCents;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public static Builder newCustomerSummary() {
        return new Builder();
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getTotalCostInCents() {
        return totalCostInCents;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void addTripAndUpdateTotalCost(Trip trip) {

    }

    public static final class Builder {
        private int customerId;
        private int totalCostInCents;
        private List<Trip> trips;

        private Builder() {
        }

        public CustomerSummary build() {
            return new CustomerSummary(this);
        }

        public Builder customerId(int customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder totalCostInCents(int totalCostInCents) {
            this.totalCostInCents = totalCostInCents;
            return this;
        }

        public Builder trips(List<Trip> trips) {
            this.trips = trips;
            return this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerSummary that = (CustomerSummary) o;
        return customerId == that.customerId &&
                totalCostInCents == that.totalCostInCents &&
                trips.equals(that.trips);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, totalCostInCents, trips);
    }
}
