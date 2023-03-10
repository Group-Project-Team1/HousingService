package com.beaconfire.housingservice.controller;

import com.beaconfire.housingservice.domain.entity.FacilityReport;
import com.beaconfire.housingservice.domain.entity.FacilityReportDetail;
import com.beaconfire.housingservice.domain.entity.House;
import com.beaconfire.housingservice.domain.entity.Landlord;
import com.beaconfire.housingservice.domain.request.FacilityReportDetailRequest;
import com.beaconfire.housingservice.exception.LandlordNotFoundException;
import com.beaconfire.housingservice.security.AuthUserDetail;
import com.beaconfire.housingservice.service.FacilityReportDetailService;
import com.beaconfire.housingservice.service.FacilityReportService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest()
@Slf4j
public class FacilityReportDetailControllerTest {
    @Mock
    FacilityReportDetailService facilityReportDetailService;
    @Mock
    FacilityReportService facilityReportService;
    @Mock
    private AuthUserDetail authUserDetail;
    @Mock
    private SecurityContext securityContext;

    private FacilityReportDetailController facilityReportDetailController;

    @BeforeEach
    public void setUp() {
        SecurityContextHolder.setContext(securityContext);
        MockitoAnnotations.openMocks(this);
        this.facilityReportDetailController = new FacilityReportDetailController(facilityReportDetailService, facilityReportService);
    }

//    @Test
//    public void addFacilityReportDetailTest() throws Exception {
//        // Arrange
//        FacilityReportDetailRequest request = new FacilityReportDetailRequest();
//        request.setFacilityReportId(1);
//        request.setComment("Test comment");
//        FacilityReport facilityReport = new FacilityReport();
//        facilityReport.setEmployeeId(1);
//        when(facilityReportService.findFacilityReportById(request.getFacilityReportId())).thenReturn(facilityReport);
//        when(securityContext.getAuthentication()).thenReturn(new UsernamePasswordAuthenticationToken(authUserDetail, null));
//        when(authUserDetail.getUserId()).thenReturn(1);
//
//        FacilityReportDetail facilityReportDetail = new FacilityReportDetail(facilityReport, 1, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), request.getComment());
//        when(facilityReportDetailService.addFacilityReportDetail(facilityReportDetail)).thenReturn(1);
//        when(facilityReportDetailService.findFacilityReportDetailById(1)).thenReturn(facilityReportDetail);
//
//        // Act
//        FacilityReportDetail result = facilityReportDetailController.addFacilityReportDetail(request);
//
//        // Assert
//        assertEquals(request.getFacilityReportId(), result.getFacilityReport().getId());
//        assertEquals(request.getComment(), result.getComment());
//    }
}
