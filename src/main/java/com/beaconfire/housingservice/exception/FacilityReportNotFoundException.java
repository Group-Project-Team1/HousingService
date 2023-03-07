package com.beaconfire.housingservice.exception;

public class FacilityReportNotFoundException extends Exception {

    public FacilityReportNotFoundException() {
        super("Given FacilityReport id not existing in the database");
    }
}
