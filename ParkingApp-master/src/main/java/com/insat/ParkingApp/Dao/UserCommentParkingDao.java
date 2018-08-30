package com.insat.ParkingApp.Dao;

import com.insat.ParkingApp.Models.UserCommentParking;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ASUS on 10/04/2017.
 */
public interface UserCommentParkingDao extends CrudRepository<UserCommentParking,Integer> {
}
