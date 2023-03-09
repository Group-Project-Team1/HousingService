package com.beaconfire.housingservice.dao;

import com.beaconfire.housingservice.domain.entity.House;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class HouseDAO {
    @Autowired
    SessionFactory sessionFactory;

    public House findHouseById(int id){
        Session session;
        Optional<House> house = null;
        try{
            session = sessionFactory.getCurrentSession();
            CriteriaBuilder cb = sessionFactory.getCriteriaBuilder();
            CriteriaQuery<House> cq = cb.createQuery(House.class);
            Root<House> root = cq.from(House.class);
            Predicate predicate = cb.equal(root.get("id"), id);
            cq.select(root).where(predicate);
            house = session.createQuery(cq).uniqueResultOptional();
        }catch (Exception e){
            e.printStackTrace();
        }
        return house.isPresent() ?  house.get(): null;
    }

    public Integer addHouse(House house){
        Session session;
        Integer houseId = null;
        try{
            session = sessionFactory.getCurrentSession();
            houseId = (Integer)session.save(house);
        }catch (Exception e){
            e.printStackTrace();
        }
        return houseId;
    }

    public void deleteHouse(Integer id){
        Session session;
        try{
            session = sessionFactory.getCurrentSession();
            CriteriaBuilder cb = sessionFactory.getCriteriaBuilder();
            CriteriaQuery<House> cq = cb.createQuery(House.class);
            Root<House> root = cq.from(House.class);
            Predicate predicate = cb.equal(root.get("id"), id);
            cq.select(root).where(predicate);
            Optional<House> house = session.createQuery(cq).uniqueResultOptional();
            House houseToBeDeleted = house.get();
            houseToBeDeleted.getLandlord().getHouses().remove(houseToBeDeleted);
            houseToBeDeleted.setLandlord(null);
            session.delete(houseToBeDeleted);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<House> findAllHouses(){
        Session session;
        List<House> houses = null;
        try{
            session = sessionFactory.getCurrentSession();
            CriteriaBuilder cb = sessionFactory.getCriteriaBuilder();
            CriteriaQuery<House> cq = cb.createQuery(House.class);
            Root<House> root = cq.from(House.class);
            cq.select(root);
            houses = session.createQuery(cq).getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return houses;

    }
}
