package com.beaconfire.housingservice.dao;

import com.beaconfire.housingservice.domain.entity.Facility;
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
public class FacilityDAO {
    @Autowired
    SessionFactory sessionFactory;

    public Facility findFacilityById(int id){
        Session session;
        Optional<Facility> facility = null;
        try{
            session = sessionFactory.getCurrentSession();
            CriteriaBuilder cb = sessionFactory.getCriteriaBuilder();
            CriteriaQuery<Facility> cq = cb.createQuery(Facility.class);
            Root<Facility> root = cq.from(Facility.class);
            Predicate predicate = cb.equal(root.get("id"), id);
            cq.select(root).where(predicate);
            facility = session.createQuery(cq).uniqueResultOptional();
        }catch (Exception e){
            e.printStackTrace();
        }
        return facility.isPresent() ?  facility.get(): null;
    }

    public Integer addFacility(Facility facility){
        Session session;
        Integer facilityId = null;
        try{
            session = sessionFactory.getCurrentSession();
            facilityId = (Integer)session.save(facility);
        }catch (Exception e){
            e.printStackTrace();
        }
        return facilityId;
    }
}
