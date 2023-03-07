package com.beaconfire.housingservice.exception;

public class FacilityReportDetailNotFoundException extends Exception{
    public FacilityReportDetailNotFoundException() {
        super("Given FacilityReportDetail id not existing in the database");
    }
}
