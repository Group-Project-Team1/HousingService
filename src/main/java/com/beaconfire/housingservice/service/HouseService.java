package com.beaconfire.housingservice.service;

import com.beaconfire.housingservice.dao.HouseDAO;
import com.beaconfire.housingservice.domain.entity.FacilityReport;
import com.beaconfire.housingservice.domain.entity.House;
import com.beaconfire.housingservice.exception.HouseNotFoundException;
import com.beaconfire.housingservice.exception.PageExceedMaxCountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HouseService {
    private HouseDAO houseDAO;

    @Autowired
    public void setHouseDAO(HouseDAO houseDAO) {
        this.houseDAO = houseDAO;
    }

    @Transactional
    public House findHouseById(int id){
        return houseDAO.findHouseById(id);
    }

    @Transactional
    public Integer addHouse(House house){
        return houseDAO.addHouse(house);
    }

    @Transactional
    public void deleteHouse(Integer id){
        houseDAO.deleteHouse(id);
    }

    @Transactional
    public List<House> findAllHouses(){ return houseDAO.findAllHouses(); }

    @Transactional
    public List<FacilityReport> findFacilityReportByPage(Integer id, Integer page) throws HouseNotFoundException, PageExceedMaxCountException {
        House house = houseDAO.findHouseById(id);
        if(house==null){
            throw new HouseNotFoundException();
        }

        int size = house.getFacilities()
                .stream()
                .flatMap(f -> f.getFacilityReports().stream())
                .collect(Collectors.toList())
                .size();

        if(page > (size-1)/5+1){
            throw new PageExceedMaxCountException();
        }

        return house.getFacilities()
                .stream()
                .flatMap(f -> f.getFacilityReports().stream())
                .sorted((a,b) -> a.getCreateDate().compareTo(b.getCreateDate()))
                .skip(5*(page-1))
                .limit(5)
                .collect(Collectors.toList());

    }
}
