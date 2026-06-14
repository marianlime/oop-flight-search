package com.marianlime.flightbooking.service;

import com.marianlime.flightbooking.model.Booking;
import com.marianlime.flightbooking.model.BookingStatus;
import com.marianlime.flightbooking.model.Flight;
import com.marianlime.flightbooking.model.Passenger;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class BookingServiceTest {

    @Test
    void shouldCreateBookingAndReduceAvailableSeats() {
        BookingService service = new BookingService();
        Flight flight = flightWithSeats(2);
        Passenger passenger = new Passenger("Marian Chihai", "marian@example.com");

        Booking booking = service.createBooking(flight, passenger);

        assertNotNull(booking.getBookingReference());
        assertEquals(BookingStatus.CONFIRMED, booking.getStatus());
        assertEquals(1, flight.getAvailableSeats());
        assertEquals(1, service.getBookings().size());
    }

    @Test
    void shouldRejectBookingWhenNoSeatsAvailable() {
        BookingService service = new BookingService();
        Flight flight = flightWithSeats(0);
        Passenger passenger = new Passenger("Marian Chihai", "marian@example.com");

        assertThrows(
                IllegalStateException.class,
                () -> service.createBooking(flight, passenger)
        );
    }

    @Test
    void shouldCancelBookingAndReleaseSeat() {
        BookingService service = new BookingService();
        Flight flight = flightWithSeats(1);
        Passenger passenger = new Passenger("Marian Chihai", "marian@example.com");

        Booking booking = service.createBooking(flight, passenger);

        assertEquals(0, flight.getAvailableSeats());

        service.cancelBooking(booking.getBookingReference());

        assertEquals(BookingStatus.CANCELLED, booking.getStatus());
        assertEquals(1, flight.getAvailableSeats());
    }

    private Flight flightWithSeats(int availableSeats) {
        return new Flight(
                "BA101",
                "British Airways",
                "London",
                "Paris",
                LocalDateTime.of(2026, 1, 20, 10, 0),
                100,
                availableSeats,
                new BigDecimal("150.00")
        );
    }
}
