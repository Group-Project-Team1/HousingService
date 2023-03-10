package com.beaconfire.housingservice.dao;

import com.beaconfire.housingservice.domain.entity.House;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles(value = "test")
public class HouseDAOTest {
    @Autowired
    private HouseDAO houseDAO;

    @Test
    @Transactional
    public void test_getHouseByOrderId(){
        House actual = houseDAO.findHouseById(1);
        House expected = new House(1, null,  4, "666 Tom Ave", null);
        actual.setLandlord(null);
        assertEquals(expected.toString(), actual.toString());
    }
}
