package com.beaconfire.housingservice.controller;

import com.beaconfire.housingservice.domain.entity.FacilityReport;
import com.beaconfire.housingservice.domain.entity.FacilityReportDetail;
import com.beaconfire.housingservice.domain.request.FacilityReportDetailRequest;
import com.beaconfire.housingservice.domain.request.FacilityReportRequest;
import com.beaconfire.housingservice.domain.response.MessageResponse;
import com.beaconfire.housingservice.exception.FacilityReportDetailNotFoundException;
import com.beaconfire.housingservice.exception.FacilityReportPermissionException;
import com.beaconfire.housingservice.security.AuthUserDetail;
import com.beaconfire.housingservice.service.FacilityReportDetailService;
import com.beaconfire.housingservice.service.FacilityReportService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public FacilityReportDetail addFacilityReportDetail(@RequestBody FacilityReportDetailRequest facilityReportDetailRequest) throws FacilityReportPermissionException{
        FacilityReport facilityReport = facilityReportService.findFacilityReportById(facilityReportDetailRequest.getFacilityReportId());
        AuthUserDetail authUserDetail = (AuthUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(facilityReport.getEmployeeId() != authUserDetail.getUserId()){
            throw new FacilityReportPermissionException();
        }

        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        FacilityReportDetail facilityReportDetail = new FacilityReportDetail(facilityReport,
                authUserDetail.getUserId(),
                currentTime,
                currentTime,
                facilityReportDetailRequest.getComment());

        int facilityReportDetailId = facilityReportDetailService.addFacilityReportDetail(facilityReportDetail);
        return facilityReportDetailService.findFacilityReportDetailById(facilityReportDetailId);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('employee')")
    public MessageResponse updateFacilityReportDetail(@RequestBody FacilityReportDetail facilityReportDetail) throws FacilityReportDetailNotFoundException,FacilityReportPermissionException {
        FacilityReportDetail oldFacilityReportDetail = facilityReportDetailService.findFacilityReportDetailById(facilityReportDetail.getId());
        AuthUserDetail authUserDetail = (AuthUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(oldFacilityReportDetail==null){
            throw new FacilityReportDetailNotFoundException();
        }

        if(oldFacilityReportDetail.getEmployeeId() != authUserDetail.getUserId()){
            throw new FacilityReportPermissionException();
        }

        oldFacilityReportDetail.setLastModificationDate(new Timestamp(System.currentTimeMillis()));
        oldFacilityReportDetail.setComment(facilityReportDetail.getComment());
        facilityReportDetailService.updateFacilityReportDetail(oldFacilityReportDetail);
        return MessageResponse.builder()
                .message("FacilityReportDetail updated successfully.")
                .build();
    }

}
