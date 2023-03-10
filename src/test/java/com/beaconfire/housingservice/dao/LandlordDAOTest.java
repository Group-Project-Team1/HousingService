package com.beaconfire.housingservice.dao;

import com.beaconfire.housingservice.domain.entity.Landlord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles(value = "test")
public class LandlordDAOTest {
    @Autowired
    private LandlordDAO landlordDAO;

    @Test
    @Transactional
    public void test_getLandlordByOrderId(){
        Landlord actual = landlordDAO.findLandlordById(1);
        Landlord expected = new Landlord(1, "Tom", "Cat", "Tom@gmail.com", "123456789", null);
        actual.setHouses(null);
        assertEquals(expected.toString(), actual.toString());
    }
}
