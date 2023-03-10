package com.beaconfire.housingservice.controller;

import com.beaconfire.housingservice.domain.entity.House;
import com.beaconfire.housingservice.domain.entity.Landlord;
import com.beaconfire.housingservice.domain.response.AllHouseResponse;
import com.beaconfire.housingservice.domain.response.HouseAssignInfo;
import com.beaconfire.housingservice.domain.response.HouseResponse;
import com.beaconfire.housingservice.domain.response.MessageResponse;
import com.beaconfire.housingservice.exception.HouseNotFoundException;
import com.beaconfire.housingservice.security.JwtFilter;
import com.beaconfire.housingservice.security.JwtProvider;
import com.beaconfire.housingservice.service.FacilityService;
import com.beaconfire.housingservice.service.HouseService;
import com.beaconfire.housingservice.service.LandlordService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest()
@Slf4j
public class HouseCrontrollerTest {
    @Mock
    private HouseService houseService;

    @Mock
    private LandlordService landlordService;

    @Mock
    private FacilityService facilityService;

    private MockMvc mockMvc;

    private HouseController houseController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.houseController = new HouseController(houseService, landlordService, facilityService);
    }

    @Test
    public void testFindAllHouses() {
        List<House> houses = new ArrayList<>();
        houses.add(new House(1, null, 1, "1", null));
        houses.add(new House(2, null, 2, "1", null));
        when(houseService.findAllHouses()).thenReturn(houses);

        List<HouseAssignInfo> expected = new ArrayList<>();
        expected.add(new HouseAssignInfo(1,1));
        expected.add(new HouseAssignInfo(2,2));
        AllHouseResponse response = houseController.findAllHouses();

        assertEquals(response.getHouseAssignInfo().toString(), expected.toString());
    }

    @Test
    public void findHouseById() throws HouseNotFoundException {
        House house = new House(1, null, 1, "1", null);
        HouseResponse expected = HouseResponse.builder()
                .house(house).build();

        when(houseService.findHouseById(1)).thenReturn(house);
        HouseResponse actual = houseController.findHouseById(1);

        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void deleteHouse() throws HouseNotFoundException {
        House house = new House(1, null, 1, "1", null);
        int id = 1;
        when(houseService.findHouseById(1)).thenReturn(house);
        doNothing().when(houseService).deleteHouse(id);

        MessageResponse expectedResponse = MessageResponse.builder()
                .message("House deleted successfully.")
                .build();

        MessageResponse actualResponse = houseController.deleteHouse(id);

        assertEquals(expectedResponse.toString(), actualResponse.toString());
    }



}
