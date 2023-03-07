package com.beaconfire.housingservice.dao;

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
public class FacilityReportDetailDAO {
    @Autowired
    SessionFactory sessionFactory;

    public FacilityReportDetail findFacilityReportDetailById(int id){
        Session session;
        Optional<FacilityReportDetail> facilityReportDetail = null;
        try{
            session = sessionFactory.getCurrentSession();
            CriteriaBuilder cb = sessionFactory.getCriteriaBuilder();
            CriteriaQuery<FacilityReportDetail> cq = cb.createQuery(FacilityReportDetail.class);
            Root<FacilityReportDetail> root = cq.from(FacilityReportDetail.class);
            Predicate predicate = cb.equal(root.get("id"), id);
            cq.select(root).where(predicate);
            facilityReportDetail = session.createQuery(cq).uniqueResultOptional();
        }catch (Exception e){
            e.printStackTrace();
        }
        return facilityReportDetail.isPresent() ?  facilityReportDetail.get(): null;
    }

    public Integer addFacilityReportDetail(FacilityReportDetail facilityReportDetail){
        Session session;
        Integer facilityReportDetailId = null;
        try{
            session = sessionFactory.getCurrentSession();
            facilityReportDetailId = (Integer)session.save(facilityReportDetail);
        }catch (Exception e){
            e.printStackTrace();
        }
        return facilityReportDetailId;
    }

    public void updateFacilityReportDetail(FacilityReportDetail facilityReportDetail){
        Session session;
        try{
            session = sessionFactory.getCurrentSession();
            session.update(facilityReportDetail);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
