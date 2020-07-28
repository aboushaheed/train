package com.train.company.billing;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.train.company.billing.exception.CheapestPriceException;
import com.train.company.domaine.city.Station;
import com.train.company.domaine.day.CustomerSummary;
import com.train.company.domaine.day.Tap;
import com.train.company.domaine.day.Trip;
import com.train.company.domaine.travel.Journey;
import com.train.company.domaine.travel.Summary;
import com.train.company.interfaces.console.in.mapping.processing.JsonFileReaderAndConverter;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BillerTest {

    @Test
    void should_calculate_the_best_price_from_B_station_in_zone_1_to_C_station_in_zone_2_3() {
        Biller biller = new Biller();
        int price = 240;
        int cheapestPrice = biller.cheapestPrice(Station.B.getBestZone(), Station.C.getBestZone());
        assertEquals(price,cheapestPrice);

    }
    @Test
    void should_throw_CheapestPriceException() {
        Biller biller = new Biller();
        Throwable exception = assertThrows(CheapestPriceException.class, () -> biller.cheapestPrice(0, 5));
        assertEquals("can not calculate cheapest price from zone 0 to zone 5", exception.getMessage());

    }

    @Test
    void should_bill_A_D_D_A_journey() {
        Biller biller = new Biller();
        Journey journey = get_A_D_D_A_Journey();
        Summary expected = get_A_D_D_A_Summary();
        Summary summary = biller.bill(journey);
        assertEquals(expected,summary);
    }

    @Test
    void should_bill_8_euros_when_traveling_within_zone_1_an_2() {
        Biller biller = new Biller();
        Journey journey = get_3_times_A_D_D_A_Journey();
        Summary summary = biller.bill(journey);
        CustomerSummary customerSummary_1 = summary.getCustomerSummaries()
                .stream().filter(customerSummary -> customerSummary.getCustomerId() == 1).findFirst().get();
        assertEquals(800,customerSummary_1.getTotalCostInCents());
    }


    @Test
    void should_bill_input_example_file_of_journey() throws IOException {
        Biller biller = new Biller();
        JsonFileReaderAndConverter jsonFileReaderAndConverter = new JsonFileReaderAndConverter();

        ClassLoader classLoader = getClass().getClassLoader();
        File inputFile = new File(requireNonNull(classLoader.getResource("CandidateInputExample.txt")).getFile());

        Journey inputJourney = jsonFileReaderAndConverter.convert(inputFile);

        Summary expected = getOutputSummary(classLoader);

        Summary summary = biller.bill(inputJourney);
        assertEquals(expected,summary);
    }

    private Summary getOutputSummary(ClassLoader classLoader) throws IOException {
        File outputFile = new File(requireNonNull(classLoader.getResource("CandidateOutputExample.txt")).getFile());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        return objectMapper.readValue(outputFile, Summary.class);
    }


    private Summary get_A_D_D_A_Summary() {
        return Summary.newSummary()
                    .customerSummaries(List.of(
                            CustomerSummary.newCustomerSummary()
                                    .customerId(1)
                                    .totalCostInCents(480)
                                    .trips(List.of(
                                            Trip.newTrip()
                                                    .stationStart(Station.A)
                                                    .stationEnd(Station.D)
                                                    .startedJourneyAt(1572242400L)
                                                    .costInCents(240)
                                                    .zoneFrom(1)
                                                    .zoneTo(2)
                                                    .build(),
                                            Trip.newTrip()
                                                    .stationStart(Station.D)
                                                    .stationEnd(Station.A)
                                                    .startedJourneyAt(1572282000L)
                                                    .costInCents(240)
                                                    .zoneFrom(2)
                                                    .zoneTo(1)
                                                    .build()
                                    ))
                                    .build()
                    ))
                    .build();
    }

    private Journey get_A_D_D_A_Journey() {
        return Journey.newJourney()
                    .taps(List.of(
                            Tap.newTap()
                                    .unixTimestamp(1572242400L)
                                    .station(Station.A)
                                    .customerId(1)
                                    .build(),
                            Tap.newTap()
                                    .unixTimestamp(1572244200L)
                                    .station(Station.D)
                                    .customerId(1)
                                    .build(),
                            Tap.newTap()
                                    .unixTimestamp(1572282000L)
                                    .station(Station.D)
                                    .customerId(1)
                                    .build(),
                            Tap.newTap()
                                    .unixTimestamp(1572283800L)
                                    .station(Station.A)
                                    .customerId(1)
                                    .build()
                    ))
                    .build();
    }

    private Journey get_3_times_A_D_D_A_Journey() {
        return Journey.newJourney()
                .taps(List.of(
                        Tap.newTap()
                                .unixTimestamp(1)
                                .station(Station.A)
                                .customerId(1)
                                .build(),
                        Tap.newTap()
                                .unixTimestamp(2)
                                .station(Station.D)
                                .customerId(1)
                                .build(),
                        Tap.newTap()
                                .unixTimestamp(3)
                                .station(Station.D)
                                .customerId(1)
                                .build(),
                        Tap.newTap()
                                .unixTimestamp(4)
                                .station(Station.A)
                                .customerId(1)
                                .build(),
                        Tap.newTap()
                                .unixTimestamp(5)
                                .station(Station.A)
                                .customerId(1)
                                .build(),
                        Tap.newTap()
                                .unixTimestamp(6)
                                .station(Station.D)
                                .customerId(1)
                                .build(),
                        Tap.newTap()
                                .unixTimestamp(7)
                                .station(Station.D)
                                .customerId(1)
                                .build(),
                        Tap.newTap()
                                .unixTimestamp(8)
                                .station(Station.A)
                                .customerId(1)
                                .build(),
                        Tap.newTap()
                                .unixTimestamp(9)
                                .station(Station.A)
                                .customerId(1)
                                .build(),
                        Tap.newTap()
                                .unixTimestamp(10)
                                .station(Station.D)
                                .customerId(1)
                                .build(),
                        Tap.newTap()
                                .unixTimestamp(11)
                                .station(Station.D)
                                .customerId(1)
                                .build(),
                        Tap.newTap()
                                .unixTimestamp(12)
                                .station(Station.A)
                                .customerId(1)
                                .build()
                ))
                .build();
    }
}