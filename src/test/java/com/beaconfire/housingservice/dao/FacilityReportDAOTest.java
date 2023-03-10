package com.beaconfire.housingservice.dao;

import com.beaconfire.housingservice.domain.entity.FacilityReport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles(value = "test")
public class FacilityReportDAOTest {
    @Autowired
    private FacilityReportDAO facilityReportDAO;

    @Test
    @Transactional
    public void test_getFacilityReportByOrderId(){
        FacilityReport actual = facilityReportDAO.findFacilityReportById(1);
        FacilityReport expected = new FacilityReport(1, null,1, "table is broken","table broken","Open", null, null);
        actual.setFacility(null);
        actual.setCreateDate(null);
        actual.setFacilityReportDetails(null);
        assertEquals(expected.toString(), actual.toString());
    }
}
