package com.beaconfire.housingservice.exception;

public class FacilityReportAlreadyClosedException  extends Exception {
    public FacilityReportAlreadyClosedException() {
        super("FacilityReport is already closed and could not be closed again");
    }
}
