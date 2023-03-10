package com.beaconfire.housingservice.controller;

import com.beaconfire.housingservice.domain.entity.Facility;
import com.beaconfire.housingservice.domain.entity.FacilityReport;
import com.beaconfire.housingservice.domain.request.FacilityReportRequest;
import com.beaconfire.housingservice.domain.response.MessageResponse;
import com.beaconfire.housingservice.exception.FacilityNotFoundException;
import com.beaconfire.housingservice.exception.FacilityReportAlreadyClosedException;
import com.beaconfire.housingservice.exception.FacilityReportNotFoundException;
import com.beaconfire.housingservice.exception.FacilityReportNotOpenException;
import com.beaconfire.housingservice.service.FacilityReportService;
import com.beaconfire.housingservice.service.FacilityService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest()
@Slf4j
public class FacilityReportControllerTest {
    @Mock
    FacilityReportService facilityReportService;
    @Mock
    FacilityService facilityService;

    private FacilityReportController facilityReportController;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.facilityReportController = new FacilityReportController(facilityReportService, facilityService);
    }


    @Test
    public void testAddFacilityReport() throws FacilityNotFoundException {
        // Mock data
        FacilityReportRequest request = new FacilityReportRequest();
        request.setFacilityId(1);
        request.setEmployeeId(1);
        request.setTitle("Test Facility Report");
        request.setDescription("This is a test facility report.");

        Facility facility = new Facility();
        facility.setId(1);
        facility.setType("Test Facility");

        FacilityReport report = new FacilityReport();
        report.setId(1);
        report.setFacility(facility);
        report.setEmployeeId(request.getEmployeeId());
        report.setTitle(request.getTitle());
        report.setDescription(request.getDescription());
        report.setStatus("Open");
        report.setCreateDate(new Timestamp(System.currentTimeMillis()));

        when(facilityService.findFacilityById(request.getFacilityId())).thenReturn(facility);
        when(facilityReportService.addFacilityReport(Mockito.any(FacilityReport.class))).thenReturn(1);
        when(facilityReportService.findFacilityReportById(1)).thenReturn(report);

        FacilityReport result = facilityReportController.addFacilityReport(request);

        assertEquals(report.toString(), result.toString());
    }

    @Test
    public void testMarkAsInProgress_Success() throws FacilityReportNotFoundException, FacilityReportNotOpenException {

        FacilityReport facilityReport = new FacilityReport();
        facilityReport.setStatus("Open");
        when(facilityReportService.findFacilityReportById(anyInt())).thenReturn(facilityReport);

        MessageResponse response = facilityReportController.markAsInProgress(1);

        assertEquals("FacilityReport marked as In Progress.", response.getMessage());
        assertEquals("In Progress", facilityReport.getStatus());
        Mockito.verify(facilityReportService, times(1)).updateFacilityReport(facilityReport);
    }

    @Test
    public void testMarkAsClosed_Success() throws FacilityReportNotFoundException, FacilityReportAlreadyClosedException {

        FacilityReport facilityReport = new FacilityReport();
        facilityReport.setStatus("Open");
        when(facilityReportService.findFacilityReportById(anyInt())).thenReturn(facilityReport);

        MessageResponse response = facilityReportController.markAsClosed(1);

        assertEquals("FacilityReport marked as Closed.", response.getMessage());
        assertEquals("Closed", facilityReport.getStatus());
        Mockito.verify(facilityReportService, times(1)).updateFacilityReport(facilityReport);
    }

}
