package com.marianlime.flightbooking.service;

import com.marianlime.flightbooking.model.Flight;
import com.marianlime.flightbooking.repository.FlightRepository;

import java.util.Comparator;
import java.util.List;

public class FlightSearchService {

    private final FlightRepository flightRepository;

    public FlightSearchService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> search(String departureCity, String arrivalCity) {
        String departureFilter = normalise(departureCity);
        String arrivalFilter = normalise(arrivalCity);

        return flightRepository.findAll()
                .stream()
                .filter(Flight::hasAvailableSeats)
                .filter(flight -> departureFilter.isEmpty()
                        || flight.getDepartureCity().toLowerCase().contains(departureFilter))
                .filter(flight -> arrivalFilter.isEmpty()
                        || flight.getArrivalCity().toLowerCase().contains(arrivalFilter))
                .sorted(Comparator.comparing(Flight::getDepartureTime))
                .toList();
    }

    private String normalise(String value) {
        if (value == null) {
            return "";
        }

        return value.trim().toLowerCase();
    }
}
