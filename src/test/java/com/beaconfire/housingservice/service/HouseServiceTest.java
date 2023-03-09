package com.beaconfire.housingservice.service;

import com.beaconfire.housingservice.dao.HouseDAO;
import com.beaconfire.housingservice.domain.entity.Facility;
import com.beaconfire.housingservice.domain.entity.FacilityReport;
import com.beaconfire.housingservice.domain.entity.House;
import com.beaconfire.housingservice.exception.HouseNotFoundException;
import com.beaconfire.housingservice.exception.PageExceedMaxCountException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doAnswer;

@ExtendWith(MockitoExtension.class)
public class HouseServiceTest {
    @InjectMocks
    private HouseService houseService;

    @Mock
    private HouseDAO houseDAO;

    @Test
    void test_findHouseById(){
        House house = new House(1, null, 1, "", null);
        Mockito.when(houseDAO.findHouseById(1)).thenReturn(house);
        assertEquals(house, houseService.findHouseById(1));
    }

    @Test
    void test_addHouse(){
        House house = new House(1, null, 1, "", null);

        Mockito.when(houseDAO.addHouse(house)).thenReturn(1);
        assertEquals(1, houseService.addHouse(house));
    }

    @Test
    void test_deleteHouse(){
        House house = new House(1, null, 1, "", null);
        doAnswer(new Answer<Void>() {
            public Void answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                System.out.println("called with arguments: " + Arrays.toString(args));
                return null;
            }
        }).when(houseDAO).deleteHouse(1);
        houseService.deleteHouse(1);
    }

    @Test
    void test_findFacilityReportByPage() throws HouseNotFoundException, PageExceedMaxCountException {
        House house = new House(1, null, 1, "", new HashSet<>());

        house.getFacilities().add(new Facility(1, null ,"", "", "", new HashSet<>()));
        Mockito.when(houseDAO.findHouseById(1)).thenReturn(house);

        List<FacilityReport> facilityReports = new ArrayList<>();
        assertEquals(new ArrayList<>(), houseService.findFacilityReportByPage(1,1));
    }
}
