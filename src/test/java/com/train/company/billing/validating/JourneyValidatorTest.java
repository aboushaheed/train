package com.train.company.billing.validating;

import com.train.company.billing.exception.JourneyValidationException;
import com.train.company.domaine.city.Station;
import com.train.company.domaine.day.Tap;
import com.train.company.domaine.travel.Journey;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JourneyValidatorTest {

    @Test
    void should_not_validate_null_journey() {
        JourneyValidator journeyValidator = new JourneyValidator();
        Throwable exception = assertThrows(JourneyValidationException.class, () -> journeyValidator.validate(null));
        assertEquals("Journey must be not null", exception.getMessage());
    }
    @Test
    void should_not_validate_null_taps() {
        JourneyValidator journeyValidator = new JourneyValidator();
        Journey journey = new Journey();
        journey.setTaps(null);
        Throwable exception = assertThrows(JourneyValidationException.class, () -> journeyValidator.validate(journey));
        assertEquals("Taps must be not null", exception.getMessage());
    }
    @Test
    void should_not_validate_empty_taps() {
        JourneyValidator journeyValidator = new JourneyValidator();
        Journey journey = new Journey();
        journey.setTaps(emptyList());
        Throwable exception = assertThrows(JourneyValidationException.class, () -> journeyValidator.validate(journey));
        assertEquals("Taps must be not empty", exception.getMessage());
    }
    @Test
    void should_not_validate_odd_taps() {
        JourneyValidator journeyValidator = new JourneyValidator();
        Journey journey = new Journey();
        journey.setTaps(List.of(Tap.newTap()
                .customerId(1)
                .station(Station.A)
                .unixTimestamp(2L)
                .build()));
        Throwable exception = assertThrows(JourneyValidationException.class, () -> journeyValidator.validate(journey));
        assertEquals("Taps must be pair", exception.getMessage());
    }



}