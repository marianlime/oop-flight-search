package com.marianlime.flightbooking.service;

import com.marianlime.flightbooking.model.Booking;
import com.marianlime.flightbooking.model.Flight;
import com.marianlime.flightbooking.model.Passenger;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookingService {

    private final List<Booking> bookings = new ArrayList<>();

    public Booking createBooking(Flight flight, Passenger passenger) {
        if (flight == null) {
            throw new IllegalArgumentException("Flight cannot be null");
        }

        if (passenger == null) {
            throw new IllegalArgumentException("Passenger cannot be null");
        }

        flight.reserveSeat();

        Booking booking = new Booking(
                UUID.randomUUID().toString(),
                flight,
                passenger
        );

        bookings.add(booking);
        return booking;
    }

    public void cancelBooking(String bookingReference) {
        Booking booking = findBookingByReference(bookingReference);
        booking.cancel();
    }

    public List<Booking> getBookings() {
        return List.copyOf(bookings);
    }

    private Booking findBookingByReference(String bookingReference) {
        return bookings.stream()
                .filter(booking -> booking.getBookingReference().equals(bookingReference))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Booking not found"));
    }
}
