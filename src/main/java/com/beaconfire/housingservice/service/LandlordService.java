package com.beaconfire.housingservice.service;

import com.beaconfire.housingservice.dao.LandlordDAO;
import com.beaconfire.housingservice.domain.entity.Landlord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class LandlordService {
    LandlordDAO landlordDAO;

    @Autowired
    public void setLandlordDAO(LandlordDAO landlordDAO) {
        this.landlordDAO = landlordDAO;
    }

    @Transactional
    public Landlord findLandlordById(int id){
        return landlordDAO.findLandlordById(id);
    }

    @Transactional
    public Integer addLandlord(Landlord landlord){
        return landlordDAO.addLandlord(landlord);
    }
}
