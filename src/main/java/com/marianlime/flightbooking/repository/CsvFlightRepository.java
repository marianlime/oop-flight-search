package com.marianlime.flightbooking.repository;

import com.marianlime.flightbooking.model.Flight;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CsvFlightRepository implements FlightRepository {

    private static final String CSV_RESOURCE = "/flights.csv";
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final List<Flight> flights;

    public CsvFlightRepository() {
        this.flights = loadFlights();
    }

    @Override
    public List<Flight> findAll() {
        return List.copyOf(flights);
    }

    private List<Flight> loadFlights() {
        List<Flight> loadedFlights = new ArrayList<>();

        try {
            InputStream inputStream = Objects.requireNonNull(
                    CsvFlightRepository.class.getResourceAsStream(CSV_RESOURCE),
                    "Could not find flights.csv in src/main/resources"
            );

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8)
            )) {
                String line = reader.readLine(); // skip header

                while ((line = reader.readLine()) != null) {
                    String[] columns = line.split(",", -1);

                    if (columns.length != 8) {
                        continue;
                    }

                    Flight flight = new Flight(
                            columns[0],
                            columns[1],
                            columns[2],
                            columns[3],
                            LocalDateTime.parse(columns[4], DATE_TIME_FORMATTER),
                            Integer.parseInt(columns[5]),
                            Integer.parseInt(columns[6]),
                            new BigDecimal(columns[7])
                    );

                    loadedFlights.add(flight);
                }
            }

            return loadedFlights;
        } catch (Exception exception) {
            throw new IllegalStateException("Failed to load flights from CSV", exception);
        }
    }
}
