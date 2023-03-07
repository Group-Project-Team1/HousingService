package com.beaconfire.housingservice.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FacilityReportDetailDAO {
    @Autowired
    SessionFactory sessionFactory;
}
