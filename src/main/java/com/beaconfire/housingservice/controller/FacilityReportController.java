package com.beaconfire.housingservice.controller;

import com.beaconfire.housingservice.dao.FacilityReportDAO;
import com.beaconfire.housingservice.domain.entity.Facility;
import com.beaconfire.housingservice.domain.entity.FacilityReport;
import com.beaconfire.housingservice.domain.entity.House;
import com.beaconfire.housingservice.domain.request.FacilityReportRequest;
import com.beaconfire.housingservice.domain.response.MessageResponse;
import com.beaconfire.housingservice.exception.FacilityNotFoundException;
import com.beaconfire.housingservice.exception.FacilityReportAlreadyClosedException;
import com.beaconfire.housingservice.exception.FacilityReportNotFoundException;
import com.beaconfire.housingservice.exception.FacilityReportNotOpenException;
import com.beaconfire.housingservice.service.FacilityReportService;
import com.beaconfire.housingservice.service.FacilityService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@Api(value = "FacilityReportController RESTful endpoints")
@RequestMapping("facility-report")
public class FacilityReportController {
    FacilityReportService facilityReportService;
    FacilityService facilityService;
    @Autowired
    public FacilityReportController(FacilityReportService facilityReportService, FacilityService facilityService) {
        this.facilityReportService = facilityReportService;
        this.facilityService = facilityService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('hr')")
    public FacilityReport addFacilityReport(@RequestBody FacilityReportRequest facilityReportRequest) throws FacilityNotFoundException {
        Facility facility = facilityService.findFacilityById(facilityReportRequest.getFacilityId());

        if(facility==null){
            throw new FacilityNotFoundException();
        }

        FacilityReport facilityReport = new FacilityReport(facility,
                facilityReportRequest.getEmployeeId(),
                facilityReportRequest.getTitle(),
                facilityReportRequest.getDescription(),
                "Open",
                new Timestamp(System.currentTimeMillis()));
        int facilityReportId = facilityReportService.addFacilityReport(facilityReport);
        return facilityReportService.findFacilityReportById(facilityReportId);
    }

    @PutMapping("{id}/inProgress")
    @PreAuthorize("hasAuthority('hr')")
    public MessageResponse markAsInProgress(@PathVariable Integer id) throws FacilityReportNotFoundException, FacilityReportNotOpenException {

        FacilityReport facilityReport = facilityReportService.findFacilityReportById(id);
        if(facilityReport==null){
            throw new FacilityReportNotFoundException();
        }
        if(!facilityReport.getStatus().equals("Open")){
            throw new FacilityReportNotOpenException();
        }
        facilityReport.setStatus("In Progress");
        facilityReportService.updateFacilityReport(facilityReport);
        return MessageResponse.builder()
                .message("FacilityReport marked as In Progress.")
                .build();
    }

    @PutMapping("{id}/close")
    @PreAuthorize("hasAuthority('hr')")
    public MessageResponse markAsClosed(@PathVariable Integer id) throws FacilityReportNotFoundException, FacilityReportAlreadyClosedException {

        FacilityReport facilityReport = facilityReportService.findFacilityReportById(id);
        if(facilityReport==null){
            throw new FacilityReportNotFoundException();
        }
        if(facilityReport.getStatus().equals("Closed")){
            throw new FacilityReportAlreadyClosedException();
        }
        facilityReport.setStatus("Closed");
        facilityReportService.updateFacilityReport(facilityReport);
        return MessageResponse.builder()
                .message("FacilityReport marked as Closed.")
                .build();
    }
}
