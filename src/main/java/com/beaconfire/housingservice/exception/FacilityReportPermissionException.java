package com.beaconfire.housingservice.exception;

public class FacilityReportPermissionException extends Exception{
    public FacilityReportPermissionException() {
        super("This report doesn't belong to you!");
    }
}
