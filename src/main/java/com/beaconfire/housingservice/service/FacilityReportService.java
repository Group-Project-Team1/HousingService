package com.beaconfire.housingservice.service;

import com.beaconfire.housingservice.dao.FacilityReportDAO;
import com.beaconfire.housingservice.domain.entity.FacilityReport;
import com.beaconfire.housingservice.domain.entity.FacilityReportDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class FacilityReportService {
    FacilityReportDAO facilityReportDAO;

    @Autowired
    public void setFacilityReportDAO(FacilityReportDAO facilityReportDAO) {
        this.facilityReportDAO = facilityReportDAO;
    }

    @Transactional
    public FacilityReport findFacilityReportById(int id){
        return facilityReportDAO.findFacilityReportById(id);
    }

    @Transactional
    public Integer addFacilityReport(FacilityReport facilityReport){
        return facilityReportDAO.addFacilityReport(facilityReport);
    }

    @Transactional
    public void updateFacilityReport(FacilityReport facilityReport){
        facilityReportDAO.updateFacilityReport(facilityReport);
    }
}
