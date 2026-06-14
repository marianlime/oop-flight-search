package com.marianlime.flightbooking.repository;

import com.marianlime.flightbooking.model.Flight;

import java.util.List;

public interface FlightRepository {
    List<Flight> findAll();
}
