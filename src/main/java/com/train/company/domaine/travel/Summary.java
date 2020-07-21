package com.train.company.domaine.travel;

import com.train.company.domaine.day.CustomerSummary;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Summary implements Serializable {

    private List<CustomerSummary> customerSummaries;

    public Summary() {
    }

    private Summary(Builder builder) {
        this.customerSummaries = builder.customerSummaries;
    }

    public static Builder newSummary() {
        return new Builder();
    }

    public List<CustomerSummary> getCustomerSummaries() {
        return customerSummaries;
    }


    public static final class Builder {
        private List<CustomerSummary> customerSummaries;

        private Builder() {
        }

        public Summary build() {
            return new Summary(this);
        }

        public Builder customerSummaries(List<CustomerSummary> customerSummaries) {
            this.customerSummaries = customerSummaries;
            return this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Summary summary = (Summary) o;
        return customerSummaries.equals(summary.customerSummaries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerSummaries);
    }
}
