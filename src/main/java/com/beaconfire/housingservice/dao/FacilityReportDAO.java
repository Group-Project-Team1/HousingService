package com.beaconfire.housingservice.dao;

import com.beaconfire.housingservice.domain.entity.FacilityReport;
import com.beaconfire.housingservice.domain.entity.FacilityReportDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Repository
public class FacilityReportDAO {
    @Autowired
    SessionFactory sessionFactory;

    public FacilityReport findFacilityReportById(int id){
        Session session;
        Optional<FacilityReport> facilityReport = null;
        try{
            session = sessionFactory.getCurrentSession();
            CriteriaBuilder cb = sessionFactory.getCriteriaBuilder();
            CriteriaQuery<FacilityReport> cq = cb.createQuery(FacilityReport.class);
            Root<FacilityReport> root = cq.from(FacilityReport.class);
            Predicate predicate = cb.equal(root.get("id"), id);
            cq.select(root).where(predicate);
            facilityReport = session.createQuery(cq).uniqueResultOptional();
        }catch (Exception e){
            e.printStackTrace();
        }
        return facilityReport.isPresent() ?  facilityReport.get(): null;
    }

    public Integer addFacilityReport(FacilityReport facilityReport){
        Session session;
        Integer facilityReportId = null;
        try{
            session = sessionFactory.getCurrentSession();
            facilityReportId = (Integer)session.save(facilityReport);
        }catch (Exception e){
            e.printStackTrace();
        }
        return facilityReportId;
    }

    public void updateFacilityReport(FacilityReport facilityReport){
        Session session;
        try{
            session = sessionFactory.getCurrentSession();
            session.update(facilityReport);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
