package com.beaconfire.housingservice.exception;

public class FacilityReportNotOpenException  extends Exception {
    public FacilityReportNotOpenException() {
        super("FacilityReport is not Open, so it could not be marked as In Progress");
    }
}
