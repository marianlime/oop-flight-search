package com.marianlime.flightbooking.model;

import java.time.LocalDateTime;

public class Booking {
    private final String bookingReference;
    private final Flight flight;
    private final Passenger passenger;
    private final LocalDateTime bookedAt;
    private BookingStatus status;

    public Booking(String bookingReference, Flight flight, Passenger passenger) {
        if (bookingReference == null || bookingReference.isBlank()) {
            throw new IllegalArgumentException("Booking reference cannot be empty");
        }

        if (flight == null) {
            throw new IllegalArgumentException("Flight cannot be null");
        }

        if (passenger == null) {
            throw new IllegalArgumentException("Passenger cannot be null");
        }

        this.bookingReference = bookingReference;
        this.flight = flight;
        this.passenger = passenger;
        this.bookedAt = LocalDateTime.now();
        this.status = BookingStatus.CONFIRMED;
    }

    public void cancel() {
        if (status == BookingStatus.CANCELLED) {
            throw new IllegalStateException("Booking is already cancelled");
        }

        status = BookingStatus.CANCELLED;
        flight.releaseSeat();
    }

    public String getBookingReference() {
        return bookingReference;
    }

    public Flight getFlight() {
        return flight;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public LocalDateTime getBookedAt() {
        return bookedAt;
    }

    public BookingStatus getStatus() {
        return status;
    }
}
