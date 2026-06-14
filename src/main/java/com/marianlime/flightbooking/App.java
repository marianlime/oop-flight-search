package com.marianlime.flightbooking;

import com.marianlime.flightbooking.model.Flight;
import com.marianlime.flightbooking.repository.CsvFlightRepository;
import com.marianlime.flightbooking.repository.FlightRepository;
import com.marianlime.flightbooking.service.FlightSearchService;

import java.util.List;

public class App {
    public static void main(String[] args) {
        FlightRepository repository = new CsvFlightRepository();
        FlightSearchService searchService = new FlightSearchService(repository);

        List<Flight> londonFlights = searchService.search("London", "");

        System.out.println("Loaded flights: " + repository.findAll().size());
        System.out.println("Flights departing London: " + londonFlights.size());

        londonFlights.stream()
                .limit(5)
                .forEach(flight -> System.out.println(
                        flight.getFlightNumber()
                                + " | " + flight.getAirline()
                                + " | " + flight.getDepartureCity()
                                + " -> " + flight.getArrivalCity()
                                + " | " + flight.getDepartureTime()
                                + " | £" + flight.getPrice()
                ));
    }
}
