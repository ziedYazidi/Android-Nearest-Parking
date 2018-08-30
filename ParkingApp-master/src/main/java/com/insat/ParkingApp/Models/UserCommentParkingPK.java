package com.insat.ParkingApp.Models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by ASUS on 04/04/2017.
 */
public class UserCommentParkingPK implements Serializable {
    private int parkingIdParking;
    private int userIdUser;

    @Column(name = "Parking_idParking")
    @Id
    public int getParkingIdParking() {
        return parkingIdParking;
    }

    public void setParkingIdParking(int parkingIdParking) {
        this.parkingIdParking = parkingIdParking;
    }

    @Column(name = "User_idUser")
    @Id
    public int getUserIdUser() {
        return userIdUser;
    }

    public void setUserIdUser(int userIdUser) {
        this.userIdUser = userIdUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserCommentParkingPK that = (UserCommentParkingPK) o;

        if (parkingIdParking != that.parkingIdParking) return false;
        if (userIdUser != that.userIdUser) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = parkingIdParking;
        result = 31 * result + userIdUser;
        return result;
    }
}
