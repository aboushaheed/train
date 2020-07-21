package com.train.company.billing;

import com.train.company.billing.exception.CheapestPriceException;
import com.train.company.billing.validating.JourneyValidator;
import com.train.company.domaine.day.CustomerSummary;
import com.train.company.domaine.day.Tap;
import com.train.company.domaine.day.Trip;
import com.train.company.domaine.travel.Journey;
import com.train.company.domaine.travel.Summary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static java.util.Map.Entry;
import static java.util.Map.of;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class Biller {
    private static final Logger log = LoggerFactory.getLogger(Biller.class);
    /**
     * after journey validation, it calculates for each customer the best price and affect the best zone from or to
     * @param journey : customer journey
     * @return Summary it contains customerSummaries
     */
    public Summary bill(Journey journey) {

        log.info("validating journey ...");
        JourneyValidator journeyValidator = new JourneyValidator();
        journeyValidator.validate(journey);

        log.info("sorting journey ...");
        List<Tap> taps = sortTaps(journey);

        Map<Integer, List<Tap>> customerTaps = taps.stream()
                .collect(groupingBy(Tap::getCustomerId));

        List<CustomerSummary> customerSummaries = new ArrayList<>();

        customerTaps.forEach((id, list) -> {
            CustomerSummary customerSummary = CustomerSummary.newCustomerSummary().customerId(id)
                    .trips(new ArrayList<>())
                    .build();

            for (int i = 0; i < list.size() - 1; i += 2) {

                int bestZoneFrom = list.get(i).getStation().getBestZone();
                int bestZoneTo = list.get(i + 1).getStation().getBestZone();

                Trip trip = Trip.newTrip()
                        .stationStart(list.get(i).getStation())
                        .stationEnd(list.get(i + 1).getStation())
                        .startedJourneyAt(list.get(i).getUnixTimestamp())
                        .costInCents(cheapestPrice(bestZoneFrom, bestZoneTo))
                        .zoneFrom(bestZoneFrom)
                        .zoneTo(bestZoneTo)
                        .build();

                List<Integer> fromZones = trip.getStationStart().getZones();
                List<Integer> toZones = trip.getStationEnd().getZones();

                fromZones.forEach(from -> toZones.forEach(to -> {
                    int cheapestPrice = cheapestPrice(from, to);
                    if (trip.getCostInCents() > cheapestPrice) {
                        trip.setCostInCents(cheapestPrice);
                        trip.setZoneFrom(from);
                        trip.setZoneTo(to);
                    }
                }));

                int totalCostInCents = customerSummary.getTotalCostInCents() + trip.getCostInCents();
                customerSummary.setTotalCostInCents(totalCostInCents);
                customerSummary.getTrips().add(trip);
                log.info("new Trip was added : {}", trip);
                log.info("for the customer : {}", id);
            }
            customerSummaries.add(customerSummary);
        });
        return Summary.newSummary()
                .customerSummaries(customerSummaries)
                .build();

    }

    /**
     *
     * @param from : zone to travel from
     * @param to : zone to travel to
     * @return : the best price to charge
     * throw an exception if no price founded
     */
   public int cheapestPrice(int from, int to) {

        Map<Integer, List<Integer>> prices = of(
                240, List.of(1,2),
                200, List.of(3,4),
                280, List.of(1,2,3),
                300, List.of(1,2,4)

        );
        Optional<Entry<Integer, List<Integer>>> optionalBestPrice = prices.entrySet()
                .stream().filter(map -> map.getValue().containsAll(List.of(from, to))).min(Comparator.comparingInt(Entry::getKey));
       if (optionalBestPrice.isPresent()) {
           return   optionalBestPrice.get().getKey();
        } else {
            throw new CheapestPriceException(from, to);
        }
    }

    /**
     *
     * @param journey all customers journey
     * @return List of sorted Taps ordering by customerId, and time
     */
    private List<Tap> sortTaps(Journey journey) {
        Comparator<Tap> comparator = Comparator
                .comparing(Tap::getCustomerId)
                .thenComparing(Tap::getUnixTimestamp);
        return journey.getTaps().stream()
                .sorted(comparator)
                .collect(toList());
    }
}
