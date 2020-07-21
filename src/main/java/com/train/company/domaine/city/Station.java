package com.train.company.domaine.city;

import java.io.Serializable;
import java.util.List;

import static java.util.Collections.emptyList;

public enum Station implements Serializable {

    A(false),
    B(false),
    C(true),
    D(false),
    E(true),
    F(true),
    G(false),
    H(false),
    I(false);

    private boolean isBoundary;

    Station(boolean isBoundary) {
         this.isBoundary = isBoundary;
    }

    public boolean isBoundary() {
        return isBoundary;
    }

    /**
     * this function associate a list of zone to a station
     * @return list of zones that cover the station, if no association it will return an empty list
     */
    public List<Integer> getZones() {
        if(this.equals(A) || this.equals(B)) return List.of(1);
        if(this.equals(D) ) return List.of(2);
        if(this.equals(C) || this.equals(E)) return List.of(2,3);
        if(this.equals(F) ) return List.of(3,4);
        if(this.equals(G) || this.equals(H)|| this.equals(I)) return List.of(4);
        return emptyList();
    }

    /**
     * retrieve the cheapest zone to bill
     * @return zone number possible values 1,2,3,4
     * 0 if no zone
     */
    public int getBestZone() {
        if(this.equals(A) || this.equals(B)) return 1;
        if(this.equals(C) || this.equals(E) || this.equals(D)) return 2;
        if(this.equals(F) ) return 3;
        if(this.equals(G) || this.equals(H)|| this.equals(I)) return 4;
        return 0;
    }

}
