package com.beaconfire.housingservice.controller;

import com.beaconfire.housingservice.domain.entity.FacilityReport;
import com.beaconfire.housingservice.domain.entity.FacilityReportDetail;
import com.beaconfire.housingservice.domain.request.FacilityReportDetailRequest;
import com.beaconfire.housingservice.domain.request.FacilityReportRequest;
import com.beaconfire.housingservice.domain.response.MessageResponse;
import com.beaconfire.housingservice.exception.FacilityReportDetailNotFoundException;
import com.beaconfire.housingservice.service.FacilityReportDetailService;
import com.beaconfire.housingservice.service.FacilityReportService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@Api(value = "FacilityReportDetailController RESTful endpoints")
@RequestMapping("facility-report-detail")
public class FacilityReportDetailController {
    FacilityReportDetailService facilityReportDetailService;
    FacilityReportService facilityReportService;

    @Autowired
    public FacilityReportDetailController(FacilityReportDetailService facilityReportDetailService, FacilityReportService facilityReportService) {
        this.facilityReportDetailService = facilityReportDetailService;
        this.facilityReportService = facilityReportService;
    }


    @PostMapping
    @PreAuthorize("hasAuthority('employee')")
    public MessageResponse addFacilityReportDetail(@RequestBody FacilityReportDetailRequest facilityReportDetailRequest){
        FacilityReport facilityReport = facilityReportService.findFacilityReportById(facilityReportDetailRequest.getFacilityReportId());
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        FacilityReportDetail facilityReportDetail = new FacilityReportDetail(facilityReport,
                facilityReportDetailRequest.getEmployeeId(),
                currentTime,
                currentTime,
                facilityReportDetailRequest.getComment());

        facilityReportDetailService.addFacilityReportDetail(facilityReportDetail);
        return MessageResponse.builder()
                .message("FacilityReportDetail created successfully.")
                .build();
    }

    @PutMapping
    @PreAuthorize("hasAuthority('employee')")
    public MessageResponse updateFacilityReportDetail(@RequestBody FacilityReportDetail facilityReportDetail) throws FacilityReportDetailNotFoundException {
        //TODO: userId match, determine if current user has right(or is hr) to edit the FacilityReportDetail

        FacilityReportDetail oldFacilityReportDetail = facilityReportDetailService.findFacilityReportDetailById(facilityReportDetail.getId());

        if(oldFacilityReportDetail==null){
            throw new FacilityReportDetailNotFoundException();
        }

        oldFacilityReportDetail.setLastModificationDate(new Timestamp(System.currentTimeMillis()));
        oldFacilityReportDetail.setComment(facilityReportDetail.getComment());
        facilityReportDetailService.updateFacilityReportDetail(oldFacilityReportDetail);
        return MessageResponse.builder()
                .message("FacilityReportDetail updated successfully.")
                .build();
    }

}
