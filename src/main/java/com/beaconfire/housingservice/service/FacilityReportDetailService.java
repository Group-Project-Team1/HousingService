package com.beaconfire.housingservice.service;

import com.beaconfire.housingservice.dao.FacilityReportDetailDAO;
import com.beaconfire.housingservice.domain.entity.FacilityReportDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class FacilityReportDetailService {
    FacilityReportDetailDAO facilityReportDetailDAO;

    @Autowired
    public void setFacilityReportDetailService(FacilityReportDetailDAO facilityReportDetailDAO) {
        this.facilityReportDetailDAO = facilityReportDetailDAO;
    }

    @Transactional
    public FacilityReportDetail findFacilityReportDetailById(int id){
        return facilityReportDetailDAO.findFacilityReportDetailById(id);
    }

    @Transactional
    public Integer addFacilityReportDetail(FacilityReportDetail facilityReportDetail){
        return facilityReportDetailDAO.addFacilityReportDetail(facilityReportDetail);
    }

    @Transactional
    public void updateFacilityReportDetail(FacilityReportDetail facilityReportDetail){
        facilityReportDetailDAO.updateFacilityReportDetail(facilityReportDetail);
    }
}
