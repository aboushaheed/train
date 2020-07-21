package com.train.company.billing.exception;

public class CheapestPriceException extends RuntimeException {

    public CheapestPriceException(int from, int to) {
        super(String.format("can not calculate cheapest price from zone %s to zone %s",from,to));
    }

}
