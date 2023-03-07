package com.beaconfire.housingservice.dao;

import com.beaconfire.housingservice.domain.entity.Landlord;
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
public class LandlordDAO {
    @Autowired
    SessionFactory sessionFactory;

    public Landlord findLandlordById(int id){
        Session session;
        Optional<Landlord> landlord = null;
        try{
            session = sessionFactory.getCurrentSession();
            CriteriaBuilder cb = sessionFactory.getCriteriaBuilder();
            CriteriaQuery<Landlord> cq = cb.createQuery(Landlord.class);
            Root<Landlord> root = cq.from(Landlord.class);
            Predicate predicate = cb.equal(root.get("id"), id);
            cq.select(root).where(predicate);
            landlord = session.createQuery(cq).uniqueResultOptional();
        }catch (Exception e){
            e.printStackTrace();
        }
        return landlord.isPresent() ?  landlord.get(): null;
    }
    
    public int addLandlord(Landlord landlord){
        Session session;
        Integer landlordId = null;
        try{
            session = sessionFactory.getCurrentSession();
            landlordId=(Integer)session.save(landlord);
        }catch (Exception e){
            e.printStackTrace();
        }
        return landlordId;
    }
}
