package com.beaconfire.housingservice.service;

import com.beaconfire.housingservice.dao.HouseDAO;
import com.beaconfire.housingservice.domain.entity.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
}
