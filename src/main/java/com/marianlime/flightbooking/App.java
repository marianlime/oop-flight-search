package com.marianlime.flightbooking;

import com.marianlime.flightbooking.repository.CsvFlightRepository;
import com.marianlime.flightbooking.repository.FlightRepository;
import com.marianlime.flightbooking.service.BookingService;
import com.marianlime.flightbooking.service.FlightSearchService;
import com.marianlime.flightbooking.ui.FlightBookingFrame;

import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FlightRepository flightRepository = new CsvFlightRepository();
            FlightSearchService flightSearchService = new FlightSearchService(flightRepository);
            BookingService bookingService = new BookingService();

            FlightBookingFrame frame = new FlightBookingFrame(
                    flightSearchService,
                    bookingService
            );

            frame.setVisible(true);
        });
    }
}
