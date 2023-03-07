package com.beaconfire.housingservice.config;


import com.beaconfire.housingservice.domain.response.ErrorResponse;
import com.beaconfire.housingservice.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorResponse> handleException(Exception e){
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .message(e.getMessage())
                        .build(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(value = {HouseNotFoundException.class,
            FacilityReportDetailNotFoundException.class,
            PageExceedMaxCountException.class,
            FacilityReportNotFoundException.class,
            FacilityNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleDuplicateException(Exception e){
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .message(e.getMessage())
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }


}