package com.beaconfire.housingservice.service;

import com.beaconfire.housingservice.dao.FacilityReportDetailDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacilityReportDetailService {
    FacilityReportDetailDAO facilityReportDetailDAO;

    @Autowired
    public void setFacilityReportDetailService(FacilityReportDetailDAO facilityReportDetailDAO) {
        this.facilityReportDetailDAO = facilityReportDetailDAO;
    }
}
