package com.insat.ParkingApp;

import com.google.maps.*;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixElement;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.TravelMode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@SpringBootApplication
public class Application {

  public static void main(String[] args) throws InterruptedException, ApiException, IOException {
    SpringApplication.run(Application.class, args);
   /*GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyA6rvM51qb0mlNn9P7CrhzuYTuu73n1Hvw");
    GeocodingResult[] results =  GeocodingApi.geocode(context,"1600 Amphitheatre Parkway Mountain View, CA 94043").await();
    System.out.println(results[0].formattedAddress);*/


   /* DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(context);
    DistanceMatrix trix = req.origins("Vancouver BC","Seattle")
            .destinations("San Francisco","Victoria BC")
            .mode(TravelMode.DRIVING)
            .avoid(DirectionsApi.RouteRestriction.HIGHWAYS)
            .language("fr-FR")
            .await();

    System.out.println( trix.rows[0].elements[0].distance);*/

   /* URL url = new URL("://maps.googleapis.com/maps/api/distancematrix/json?origins=Bangalore,KARNATAKA&destinations=Mysore,KARNATAKA&key=XXXXXXXXXXX");
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");

    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    System.out.println(reader);*/

  }

}
