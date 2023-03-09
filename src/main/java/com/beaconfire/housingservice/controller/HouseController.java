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

    @GetMapping("all")
    public AllHouseResponse findAllHouses(){
        List<House> houses = houseService.findAllHouses();

        List<HouseAssignInfo> houseAssignInfo = houses.stream().map(h -> new HouseAssignInfo(h.getId(),h.getMaxOccupant())).collect(Collectors.toList());
        return AllHouseResponse.builder()
                .houseAssignInfo(houseAssignInfo)
                .build();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('hr')")
    public HouseResponse findHouseById(@PathVariable Integer id) throws HouseNotFoundException {
        House house = houseService.findHouseById(id);
        if(house==null){
            throw new HouseNotFoundException();
        }
        return HouseResponse.builder()
                .house(house)
                .build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('hr')")
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

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('hr')")
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

    @GetMapping("{id}/facility-report/page/{page}")
    @PreAuthorize("hasAuthority('hr')")
    public FacilityReportsPageResponse findFacilityReportsByPage(@PathVariable Integer id, @PathVariable Integer page) throws HouseNotFoundException, PageExceedMaxCountException {
        List<FacilityReport> facilityReports = houseService.findFacilityReportByPage(id, page);

        return FacilityReportsPageResponse.builder()
                .page(page)
                .facilityReports(facilityReports)
                .build();
    }
}
