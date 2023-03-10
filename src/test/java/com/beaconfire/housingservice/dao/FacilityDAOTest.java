package com.beaconfire.housingservice.dao;

import com.beaconfire.housingservice.domain.entity.Facility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles(value = "test")
public class FacilityDAOTest {
    @Autowired
    private FacilityDAO facilityDAO;

    @Test
    @Transactional
    public void test_getFacilityByOrderId(){
        Facility actual = facilityDAO.findFacilityById(1);
        Facility expected = new Facility(1,null,"chair","chair", "4", null);
        actual.setHouse(null);
        actual.setFacilityReports(null);
        assertEquals(expected.toString(), actual.toString());
    }
}
