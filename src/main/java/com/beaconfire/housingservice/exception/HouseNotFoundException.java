package com.beaconfire.housingservice.exception;

public class HouseNotFoundException extends Exception{
    public HouseNotFoundException() {
        super("Given house id not existing in the database");
    }
}
