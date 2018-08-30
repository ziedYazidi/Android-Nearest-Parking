package com.insat.ParkingApp.Dao;

import com.insat.ParkingApp.Models.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ASUS on 10/04/2017.
 */
@Transactional
@Repository
public interface ReservationDao extends CrudRepository <Reservation,Long> {

}