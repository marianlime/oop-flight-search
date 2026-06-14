package com.marianlime.flightbooking.repository;

import com.marianlime.flightbooking.model.Flight;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvFlightRepositoryTest {

    @Test
    void shouldLoadGeneratedFlightsFromCsv() {
        CsvFlightRepository repository = new CsvFlightRepository();

        List<Flight> flights = repository.findAll();

        assertEquals(100, flights.size());
        assertNotNull(flights.get(0).getFlightNumber());
        assertNotNull(flights.get(0).getAirline());
        assertNotNull(flights.get(0).getDepartureCity());
        assertNotNull(flights.get(0).getArrivalCity());
        assertTrue(flights.get(0).getTotalSeats() >= flights.get(0).getAvailableSeats());
    }
}
