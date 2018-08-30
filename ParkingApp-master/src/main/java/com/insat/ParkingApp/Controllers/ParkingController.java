package com.insat.ParkingApp.Controllers;

import com.google.common.hash.Hashing;
import com.google.maps.DirectionsApi;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.TravelMode;
import com.insat.ParkingApp.Dao.ParkingDao;
import com.insat.ParkingApp.Models.Parking;
import com.insat.ParkingApp.Models.ReponseParking;
import com.insat.ParkingApp.Models.User;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by ASUS on 04/04/2017.
 */
@RestController
public class ParkingController {
    @Autowired
    ParkingDao parkingDao;

    @RequestMapping(value="/addParking", method = RequestMethod.GET,headers="Accept=application/json")
    @ResponseBody
    public String create(double longitude, double latitude, String nom, String horaire, String placeDisponibles, Double moyenne) {
        Parking park = null;
        try {
            park = new Parking(longitude,latitude,nom,horaire,placeDisponibles,moyenne);
            parkingDao.save(park);
        }
        catch (Exception ex) {
            return "Error creating the Parking: " + ex.toString();
        }
        return "Parking succesfully added! (nom= " + park.getNom() + ")";
    }


    @RequestMapping(value="/findParking", method = RequestMethod.GET,headers="Accept=application/json")
    @ResponseBody
    public List<ReponseParking> findParkingDistance2(String name) {
        DistanceMatrix trix = null;

        List<ReponseParking> reponseParkings = new ArrayList<ReponseParking>();
        try {

            GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyAakRMkp2TWPVX2IrYPovAZQetpXm2ry2w");




            Iterable<Parking> parkings = parkingDao.findAll();




            //parking = parkingDao.findOne((long)2);
            for (Parking parking : parkings
                    ) {
                DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(context);
                trix = req.origins(name)
                        .destinations(parking.getNom())
                        .mode(TravelMode.DRIVING)
                        .avoid(DirectionsApi.RouteRestriction.HIGHWAYS)
                        .language("fr-FR")
                        .await();



                //reponseParking=new ReponseParking(parking.getIdParking(),parking.getNom(),parking.getHoraire(),trix.rows[0].elements[0].distance.toString(),trix.rows[0].elements[0].duration.toString(),parking.getMoyenne(),parking.getHoraire());

                reponseParkings.add(new ReponseParking(parking.getIdParking(),parking.getNom(),parking.getHoraire(),trix.rows[0].elements[0].distance.toString(),trix.rows[0].elements[0].duration.toString(),parking.getMoyenne(),parking.getPlaceDisponibles(),trix.rows[0].elements[0].duration.inSeconds)) ;



            }
            Collections.sort(reponseParkings, new Comparator<ReponseParking>() {
                @Override
                public int compare(ReponseParking fruit2, ReponseParking fruit1)
                {

                    return  fruit2.getDurationInSeconds().compareTo(fruit1.getDurationInSeconds());
                }
            });

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return reponseParkings;}


    @RequestMapping(value="/getParkings", method = RequestMethod.GET,headers="Accept=application/json")
    @ResponseBody
    public Iterable<Parking> fetchall() {


        Iterable<Parking> parkings =null;
        try {

            parkings = parkingDao.findAll();

        }
        catch (Exception ex) {
            // return "Error creating the user: " + ex.toString();

        }
        return parkings; }




    @RequestMapping(value="/deleteParking", method = RequestMethod.GET,headers="Accept=application/json")
    @ResponseBody
    public String delete(long id) {
        try {
            Parking user = parkingDao.findOne(id);
            parkingDao.delete(user);

        }
        catch (Exception ex) {
            return "Error deleting the Parking: " + ex.toString();
        }
        return "Parking succesfully deleted!";
    }

    @RequestMapping(value="/updateParking",method = RequestMethod.GET,headers="Accept=application/json")
    @ResponseBody
    public String updateParking(String nom,double longitude,double latitude, String nvnom,String horaire, String placeDisponibles) {
        try {
            Parking parking = parkingDao.findOneByNom(nom);

            parking.setLongitude(longitude);
            parking.setNom(nvnom);
            parking.setLatitude(latitude);
            parking.setHoraire(horaire);
            parking.setPlaceDisponibles(placeDisponibles);

            parkingDao.save(parking);

        }
        catch (Exception ex) {
            return "Error updating the user: " + ex.toString();
        }
        return "Parking!";
    }
}
