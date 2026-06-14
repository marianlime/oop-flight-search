package com.marianlime.flightbooking.ui;

import com.marianlime.flightbooking.model.Booking;
import com.marianlime.flightbooking.model.Flight;
import com.marianlime.flightbooking.model.Passenger;
import com.marianlime.flightbooking.service.BookingService;
import com.marianlime.flightbooking.service.FlightSearchService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FlightBookingFrame extends JFrame {

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final FlightSearchService flightSearchService;
    private final BookingService bookingService;

    private final JTextField departureField = new JTextField(12);
    private final JTextField arrivalField = new JTextField(12);
    private final JTextField passengerNameField = new JTextField(16);
    private final JTextField passengerEmailField = new JTextField(16);

    private final DefaultTableModel flightTableModel;
    private final JTable flightTable;

    private final DefaultListModel<String> bookingListModel = new DefaultListModel<>();
    private final JList<String> bookingList = new JList<>(bookingListModel);

    private List<Flight> currentFlights = new ArrayList<>();

    public FlightBookingFrame(
            FlightSearchService flightSearchService,
            BookingService bookingService
    ) {
        this.flightSearchService = flightSearchService;
        this.bookingService = bookingService;

        setTitle("Flight Booking System");
        setSize(1050, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] columns = {
                "Flight No",
                "Airline",
                "From",
                "To",
                "Departure",
                "Seats",
                "Price"
        };

        flightTableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        flightTable = new JTable(flightTableModel);
        flightTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        flightTable.setAutoCreateRowSorter(true);

        setLayout(new BorderLayout(10, 10));
        add(createSearchPanel(), BorderLayout.NORTH);
        add(createMainPanel(), BorderLayout.CENTER);
        add(createBookingPanel(), BorderLayout.SOUTH);

        loadAllAvailableFlights();
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton searchButton = new JButton("Search Flights");
        JButton clearButton = new JButton("Clear");

        searchButton.addActionListener(event -> searchFlights());
        clearButton.addActionListener(event -> {
            departureField.setText("");
            arrivalField.setText("");
            loadAllAvailableFlights();
        });

        panel.add(new JLabel("From:"));
        panel.add(departureField);
        panel.add(new JLabel("To:"));
        panel.add(arrivalField);
        panel.add(searchButton);
        panel.add(clearButton);

        return panel;
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        JScrollPane tableScrollPane = new JScrollPane(flightTable);
        tableScrollPane.setBorder(BorderFactory.createTitledBorder("Available Flights"));

        JScrollPane bookingScrollPane = new JScrollPane(bookingList);
        bookingScrollPane.setPreferredSize(new Dimension(350, 0));
        bookingScrollPane.setBorder(BorderFactory.createTitledBorder("Booking History"));

        panel.add(tableScrollPane, BorderLayout.CENTER);
        panel.add(bookingScrollPane, BorderLayout.EAST);

        return panel;
    }

    private JPanel createBookingPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton bookButton = new JButton("Book Selected Flight");

        bookButton.addActionListener(event -> bookSelectedFlight());

        panel.setBorder(BorderFactory.createTitledBorder("Passenger Details"));
        panel.add(new JLabel("Name:"));
        panel.add(passengerNameField);
        panel.add(new JLabel("Email:"));
        panel.add(passengerEmailField);
        panel.add(bookButton);

        return panel;
    }

    private void loadAllAvailableFlights() {
        currentFlights = flightSearchService.search("", "");
        refreshFlightTable();
    }

    private void searchFlights() {
        String departure = departureField.getText();
        String arrival = arrivalField.getText();

        currentFlights = flightSearchService.search(departure, arrival);
        refreshFlightTable();

        if (currentFlights.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "No matching flights found.",
                    "Search Results",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    private void refreshFlightTable() {
        flightTableModel.setRowCount(0);

        for (Flight flight : currentFlights) {
            flightTableModel.addRow(new Object[]{
                    flight.getFlightNumber(),
                    flight.getAirline(),
                    flight.getDepartureCity(),
                    flight.getArrivalCity(),
                    flight.getDepartureTime().format(DATE_FORMATTER),
                    flight.getAvailableSeats(),
                    "£" + flight.getPrice()
            });
        }
    }

    private void bookSelectedFlight() {
        int selectedRow = flightTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please select a flight first.",
                    "No Flight Selected",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        String passengerName = passengerNameField.getText();
        String passengerEmail = passengerEmailField.getText();

        try {
            int modelRow = flightTable.convertRowIndexToModel(selectedRow);
            Flight selectedFlight = currentFlights.get(modelRow);

            Passenger passenger = new Passenger(passengerName, passengerEmail);
            Booking booking = bookingService.createBooking(selectedFlight, passenger);

            bookingListModel.addElement(
                    booking.getBookingReference()
                            + " | " + passenger.getFullName()
                            + " | " + selectedFlight.getFlightNumber()
                            + " | " + selectedFlight.getDepartureCity()
                            + " -> " + selectedFlight.getArrivalCity()
            );

            passengerNameField.setText("");
            passengerEmailField.setText("");

            refreshFlightTable();

            JOptionPane.showMessageDialog(
                    this,
                    "Booking confirmed!\nReference: " + booking.getBookingReference(),
                    "Booking Confirmed",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(
                    this,
                    exception.getMessage(),
                    "Booking Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
