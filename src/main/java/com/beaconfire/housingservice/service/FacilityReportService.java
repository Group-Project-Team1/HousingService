package com.beaconfire.housingservice.service;

import com.beaconfire.housingservice.dao.FacilityReportDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacilityReportService {
    FacilityReportDAO facilityReportDAO;

    @Autowired
    public void setFacilityReportDAO(FacilityReportDAO facilityReportDAO) {
        this.facilityReportDAO = facilityReportDAO;
    }
}
