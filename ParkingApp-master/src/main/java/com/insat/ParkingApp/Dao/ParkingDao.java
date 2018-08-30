package com.insat.ParkingApp.Dao;

import com.insat.ParkingApp.Models.Parking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ASUS on 04/04/2017.
 */
@Transactional
@Repository
public interface ParkingDao extends CrudRepository <Parking,Long> {
    public Parking findOneByNom(String name);
}