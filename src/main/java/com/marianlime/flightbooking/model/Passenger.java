package com.marianlime.flightbooking.model;

public class Passenger {
    private final String fullName;
    private final String email;

    public Passenger(String fullName, String email) {
        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("Passenger name cannot be empty");
        }

        if (email == null || email.isBlank() || !email.contains("@")) {
            throw new IllegalArgumentException("Passenger email is invalid");
        }

        this.fullName = fullName;
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }
}
