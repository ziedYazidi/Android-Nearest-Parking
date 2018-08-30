package com.insat.ParkingApp.Dao;

import javax.transaction.Transactional;

import com.insat.ParkingApp.Models.User;
import org.springframework.data.repository.CrudRepository;


@Transactional
public interface UserDao extends CrudRepository<User, Long> {

  /**
   * Return the user having the passed email or null if no user is found.
   * 
   * @param email the user email.
   */
  public User findByEmail(String email);
  public User findByNom(String name);


} // class UserDao
