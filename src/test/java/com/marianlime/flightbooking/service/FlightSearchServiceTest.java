package com.marianlime.flightbooking.service;

import com.marianlime.flightbooking.model.Flight;
import com.marianlime.flightbooking.repository.FlightRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightSearchServiceTest {

    @Test
    void shouldFindAvailableFlightsByDepartureAndArrivalCity() {
        Flight londonToParis = flight("BA101", "London", "Paris", 10);
        Flight londonToTokyo = flight("BA202", "London", "Tokyo", 5);
        Flight fullLondonToParis = flight("BA303", "London", "Paris", 0);

        FlightRepository repository = () -> List.of(
                londonToTokyo,
                londonToParis,
                fullLondonToParis
        );

        FlightSearchService service = new FlightSearchService(repository);

        List<Flight> results = service.search("London", "Paris");

        assertEquals(1, results.size());
        assertEquals("BA101", results.get(0).getFlightNumber());
    }

    @Test
    void shouldReturnAvailableFlightsSortedByDepartureTime() {
        Flight later = new Flight(
                "BA200",
                "British Airways",
                "London",
                "Paris",
                LocalDateTime.of(2026, 1, 10, 18, 0),
                100,
                10,
                new BigDecimal("120.00")
        );

        Flight earlier = new Flight(
                "BA100",
                "British Airways",
                "London",
                "Paris",
                LocalDateTime.of(2026, 1, 10, 9, 0),
                100,
                10,
                new BigDecimal("120.00")
        );

        FlightRepository repository = () -> List.of(later, earlier);
        FlightSearchService service = new FlightSearchService(repository);

        List<Flight> results = service.search("London", "Paris");

        assertEquals("BA100", results.get(0).getFlightNumber());
        assertEquals("BA200", results.get(1).getFlightNumber());
    }

    private Flight flight(String flightNumber, String from, String to, int availableSeats) {
        return new Flight(
                flightNumber,
                "British Airways",
                from,
                to,
                LocalDateTime.of(2026, 1, 20, 10, 0),
                100,
                availableSeats,
                new BigDecimal("150.00")
        );
    }
}
