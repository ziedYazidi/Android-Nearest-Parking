package com.insat.ParkingApp.Controllers;

import com.insat.ParkingApp.Dao.ReservationDao;
import com.insat.ParkingApp.Models.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ASUS on 10/04/2017.
 */
public class ReservationController {



   /* @RequestMapping(value="/createReservation", method = RequestMethod.GET,headers="Accept=application/json")
    @ResponseBody
    public String create(double longitude, double latitude, String nom, String horaire, String placeDisponibles, Double moyenne) {
        Reservation reservation = null;
        try {
            reservation = new Reservation();
           reservationDao.save(park);
        }
        catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "Parking succesfully added! (nom= " + park.getNom() + ")";
    }
*/

    @Autowired
    ReservationDao reservationDao ;



}
