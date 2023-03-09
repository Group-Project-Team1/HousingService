package com.beaconfire.housingservice.exception;

public class LandlordNotFoundException extends Exception{
    public LandlordNotFoundException() {
        super("Given landlord id not existing in the database");
    }
}