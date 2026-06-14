# Flight Booking System

A Java Swing flight booking application refactored from an older university project into a cleaner OOP design with separated model, repository, service, and UI layers.

## Features

- Swing GUI for flight search and passenger booking
- Search flights by departure and arrival city
- Display available flights in a sortable table
- Book selected flights for passengers
- Track booking history in the GUI
- Seat availability validation
- CSV-backed flight data with 100 generated sample flights
- JUnit tests for flight search, booking logic, and CSV loading

## Tech Stack

- Java 21
- Java Swing
- Maven
- JUnit 5
- Python CSV generation script

## Project Structure

src/main/java/com/marianlime/flightbooking/
├── model/
├── repository/
├── service/
├── ui/
└── App.java

## Design

The application separates responsibilities across layers:

- model contains domain objects such as flights, passengers, and bookings.
- repository loads flight data from flights.csv.
- service contains search and booking business logic.
- ui contains the Swing interface and delegates business actions to the service layer.

This avoids placing business logic directly inside Swing event handlers and makes the code easier to test and maintain.

## Generate Flight Data

python3 scripts/generate_flights_csv.py

This creates src/main/resources/flights.csv with 100 generated flights.

## Run the Application

mvn clean compile
mvn exec:java

## Run Tests

mvn test

## Purpose

This project demonstrates Java OOP, Swing GUI development, service-layer design, CSV data loading, validation, collections, and unit testing.
