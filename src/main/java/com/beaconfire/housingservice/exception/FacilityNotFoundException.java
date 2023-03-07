package com.beaconfire.housingservice.exception;

public class FacilityNotFoundException extends Exception {
    public FacilityNotFoundException() {
        super("Given Facility id not existing in the database");
    }
}
