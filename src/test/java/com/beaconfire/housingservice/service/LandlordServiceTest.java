package com.beaconfire.housingservice.service;

import com.beaconfire.housingservice.dao.LandlordDAO;
import com.beaconfire.housingservice.domain.entity.Landlord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class LandlordServiceTest {
    @InjectMocks
    private LandlordService landlordService;

    @Mock
    private LandlordDAO landlordDAO;

    @Test
    void test_findLandlordById(){
        Landlord landlord = new Landlord(1, "test", "test","test","test",null);

        Mockito.when(landlordDAO.findLandlordById(1)).thenReturn(landlord);
        assertEquals(landlord, landlordService.findLandlordById(1));
    }

    @Test
    void test_addLandlord(){
        Landlord landlord = new Landlord(1, "test", "test","test","test",null);

        Mockito.when(landlordDAO.addLandlord(landlord)).thenReturn(1);
        assertEquals(1, landlordService.addLandlord(landlord));
    }
}
