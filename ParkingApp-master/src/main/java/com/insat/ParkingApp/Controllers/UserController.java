package com.insat.ParkingApp.Controllers;

import com.google.common.hash.Hashing;
import com.insat.ParkingApp.Models.User;
import com.insat.ParkingApp.Dao.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

/**
 * A class to test interactions with the MySQL database using the UserDao class.
 *
 * @author netgloo
 */
@RestController
public class UserController {

  // ------------------------
  // PUBLIC METHODS
  // ------------------------
  
  /**
   * /create  --> Create a new user and save it in the database.
   * 
   * @param email User's email
   * @param nom User's name
   * @return A string describing if the user is succesfully created or not.
   */
  @RequestMapping(value="/createUser", method = RequestMethod.GET,headers="Accept=application/json")
  @ResponseBody
  public String create(String nom,String prenom,String email, Byte isResponsible,String hashedMdp) {
    User user = null;
    try {

      String sha256hex = Hashing.sha256()
              .hashString(hashedMdp, StandardCharsets.UTF_8)
              .toString();
      user = new User(nom,prenom,email,isResponsible,sha256hex);

      userDao.save(user);


    }
    catch (Exception ex) {
      return "Error creating the user: " + ex.toString();
    }
    return "1";
  }

  @RequestMapping(value="/connexion", method = RequestMethod.GET,headers="Accept=application/json")
  @ResponseBody
  public User connexion (String email,String mdp) {


    User user = userDao.findByEmail(email);

      String sha256hex = Hashing.sha256()
              .hashString(mdp, StandardCharsets.UTF_8)
              .toString();
    if  ( user.getHashedMdp().equals(sha256hex)) {return user;}

    else
      {return null;}





    }


  /**
   * /delete  --> Delete the user having the passed id.
   * 
   * @param id The id of the user to delete
   * @return A string describing if the user is succesfully deleted or not.
   */
  @RequestMapping("/deleteUser")
  @ResponseBody
  public String delete(long id) {
    try {

       User user = userDao.findOne(id);

      userDao.delete(user);
    }
    catch (Exception ex) {
      return "Error deleting the user: " + ex.toString();
    }
    return "User succesfully deleted!";
  }
  
  /**
   * /get-by-email  --> Return the id for the user having the passed email.
   * 
   * @param email The email to search in the database.
   * @return The user id or a message error if the user is not found.
   */
  @RequestMapping(value="/getUserByEmail",method = RequestMethod.GET,headers="Accept=application/json")
  @ResponseBody
  public String getByEmail(String email) {
    String userId;
    try {

      User user = userDao.findByEmail(email);
      userId = String.valueOf(user.getIdUser());
    }
    catch (Exception ex) {
      return "User not found";
    }
    return "The user id is: " + userId;
  }

  @RequestMapping(value="/getUserByName",method = RequestMethod.GET,headers="Accept=application/json")
  @ResponseBody
  public String getByNom(String nom) {
    String userId;
    try {

      User user = userDao.findByNom(nom);
      userId = String.valueOf(user.getIdUser());
    }
    catch (Exception ex) {
      return "User not found";
    }
    return "The user id is: " + userId;
  }

  @RequestMapping(value="/getAllUsers",method = RequestMethod.GET,headers="Accept=application/json")
    @ResponseBody
    public Iterable<User> fetchall() {

        // Long idformateur=null;
        Iterable<User> users =null;
        try {
            users = userDao.findAll();

            System.out.println(userDao.count());

        }
        catch (Exception ex) {
            // return "Error creating the user: " + ex.toString();

        }
        return users; }




  /**
   * /update  --> Update the email and the name for the user in the database 
   * having the passed id.
   * 
   * @param id The id for the user to update.
   * @param email The new email.
   * @param nom The new nom.
   * @return A string describing if the user is succesfully updated or not.
   */
  @RequestMapping(value="/updateUser",method = RequestMethod.GET,headers="Accept=application/json")
  @ResponseBody
  public String updateUser(long id, String email, String nom,String prenom,String hashedMdp) {
    try {
      User user = userDao.findOne(id);
      String sha256hex = Hashing.sha256()
              .hashString(hashedMdp, StandardCharsets.UTF_8)
              .toString();
      user.setEmail(email);
      user.setNom(nom);
      user.setPrenom(prenom);
      user.setHashedMdp(sha256hex);
      userDao.save(user);

    }
    catch (Exception ex) {
      return "Error updating the user: " + ex.toString();
    }
    return "User succesfully updated!";
  }

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------

  @Autowired
  private UserDao userDao;
  
} // class UserController
