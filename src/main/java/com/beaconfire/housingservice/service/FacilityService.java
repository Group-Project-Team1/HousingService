package com.beaconfire.housingservice.service;

import com.beaconfire.housingservice.dao.FacilityDAO;
import com.beaconfire.housingservice.domain.entity.Facility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class FacilityService {
    private FacilityDAO facilityDAO;

    @Autowired
    public void setFacilityDAO(FacilityDAO facilityDAO) {
        this.facilityDAO = facilityDAO;
    }

    @Transactional
    public Facility findFacilityById(int id){
        return facilityDAO.findFacilityById(id);
    }

    @Transactional
    public Integer addFacility(Facility facility){
        return facilityDAO.addFacility(facility);
    }
}
