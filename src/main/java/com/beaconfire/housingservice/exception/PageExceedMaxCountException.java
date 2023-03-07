package com.beaconfire.housingservice.exception;

public class PageExceedMaxCountException extends Exception{
    public PageExceedMaxCountException() {
        super("Given page exceeds the max count");
    }
}
