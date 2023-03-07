package com.beaconfire.housingservice.controller;

import com.beaconfire.housingservice.domain.entity.Facility;
import com.beaconfire.housingservice.domain.entity.House;
import com.beaconfire.housingservice.domain.entity.Landlord;
import com.beaconfire.housingservice.domain.response.HouseResponse;
import com.beaconfire.housingservice.domain.response.MessageResponse;
import com.beaconfire.housingservice.exception.HouseNotFoundException;
import com.beaconfire.housingservice.service.FacilityService;
import com.beaconfire.housingservice.service.HouseService;
import com.beaconfire.housingservice.service.LandlordService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@RestController
@Api(value = "HouseController RESTful endpoints")
@RequestMapping("house")
public class HouseController {
    HouseService houseService;
    LandlordService landlordService;
    FacilityService facilityService;

    @Autowired
    public HouseController(HouseService houseService, LandlordService landlordService, FacilityService facilityService) {
        this.houseService = houseService;
        this.landlordService = landlordService;
        this.facilityService = facilityService;
    }

    @GetMapping("find/{id}")
    public HouseResponse findHouseById(@PathVariable Integer id) throws HouseNotFoundException {
        House house = houseService.findHouseById(id);
        if(house==null){
            throw new HouseNotFoundException();
        }
        return HouseResponse.builder()
                .house(house)
                .build();
    }

    @PostMapping("add")
    public MessageResponse addHouse(@RequestBody House house){
        Landlord landlord;
        if(house.getLandlord().getId()!=null){
            landlord = landlordService.findLandlordById(house.getLandlord().getId());
            landlord.getHouses().add(house);
            house.setLandlord(landlord);
        }
        else{
            landlord = house.getLandlord();
            landlord.setHouses(new HashSet<>());
            landlord.getHouses().add(house);
        }

        for(Facility facility: house.getFacilities()){
            facility.setHouse(house);
        }

        if(house.getLandlord().getId()!=null){
            houseService.addHouse(house);
        }
        else{
            landlordService.addLandlord(landlord);
        }
        return MessageResponse.builder()
                .message("House created successfully.")
                .build();
    }

    @DeleteMapping("delete/{id}")
    public MessageResponse deleteHouse(@PathVariable Integer id) throws HouseNotFoundException {
        House house = houseService.findHouseById(id);
        if(house==null){
            throw new HouseNotFoundException();
        }
        houseService.deleteHouse(id);
        return MessageResponse.builder()
                .message("House deleted successfully.")
                .build();
    }
}