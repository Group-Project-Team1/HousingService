package com.beaconfire.housingservice.controller;

import com.beaconfire.housingservice.domain.entity.Facility;
import com.beaconfire.housingservice.domain.entity.FacilityReport;
import com.beaconfire.housingservice.domain.entity.House;
import com.beaconfire.housingservice.domain.entity.Landlord;
import com.beaconfire.housingservice.domain.response.*;
import com.beaconfire.housingservice.exception.HouseNotFoundException;
import com.beaconfire.housingservice.exception.PageExceedMaxCountException;
import com.beaconfire.housingservice.service.FacilityService;
import com.beaconfire.housingservice.service.HouseService;
import com.beaconfire.housingservice.service.LandlordService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(value = "HouseController RESTful endpoints")
@RequestMapping("house/employee/")
public class EmployeeHouseController {
    HouseService houseService;
    LandlordService landlordService;
    FacilityService facilityService;

    @Autowired
    public EmployeeHouseController(HouseService houseService, LandlordService landlordService, FacilityService facilityService) {
        this.houseService = houseService;
        this.landlordService = landlordService;
        this.facilityService = facilityService;
    }

    @GetMapping("all")
    public AllHouseResponse findAllHousesEmployee(){
        List<House> houses = houseService.findAllHouses();

        List<HouseAssignInfo> houseAssignInfo = houses.stream().map(h -> new HouseAssignInfo(h.getId(),h.getMaxOccupant())).collect(Collectors.toList());
        return AllHouseResponse.builder()
                .houseAssignInfo(houseAssignInfo)
                .build();
    }
}
