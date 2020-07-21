package com.train.company.billing.validating;


import com.train.company.billing.exception.JourneyValidationException;
import com.train.company.domaine.travel.Journey;

public class JourneyValidator {

    public  boolean validate(Journey journey) {
        if(journey == null) throw new JourneyValidationException("Journey must be not null");
        if(journey.getTaps() == null) throw new JourneyValidationException("Taps must be not null");
        if(journey.getTaps().isEmpty()) throw new JourneyValidationException("Taps must be not empty");
        if(journey.getTaps().size()%2 != 0) throw new JourneyValidationException("Taps must be pair");

        return true;
    }

}
