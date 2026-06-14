package com.marianlime.flightbooking.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

public class Flight {
    private final String flightNumber;
    private final String airline;
    private final String departureCity;
    private final String arrivalCity;
    private final LocalDateTime departureTime;
    private final int totalSeats;
    private int availableSeats;
    private final BigDecimal price;

    public Flight(
            String flightNumber,
            String airline,
            String departureCity,
            String arrivalCity,
            LocalDateTime departureTime,
            int totalSeats,
            int availableSeats,
            BigDecimal price
    ) {
        if (flightNumber == null || flightNumber.isBlank()) {
            throw new IllegalArgumentException("Flight number cannot be empty");
        }

        if (totalSeats < 0 || availableSeats < 0 || availableSeats > totalSeats) {
            throw new IllegalArgumentException("Invalid seat counts");
        }

        this.flightNumber = flightNumber;
        this.airline = airline;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureTime = departureTime;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
        this.price = price.setScale(2, RoundingMode.HALF_UP);
    }

    public boolean hasAvailableSeats() {
        return availableSeats > 0;
    }

    public void reserveSeat() {
        if (!hasAvailableSeats()) {
            throw new IllegalStateException("No seats available for this flight");
        }

        availableSeats--;
    }

    public void releaseSeat() {
        if (availableSeats < totalSeats) {
            availableSeats++;
        }
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getAirline() {
        return airline;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
