package com.insat.ParkingApp.Models;

import javax.persistence.*;

/**
 * Created by ASUS on 04/04/2017.
 */
@Entity
@Table(name = "user_comment_parking", schema = "mydb", catalog = "")
@IdClass(UserCommentParkingPK.class)
public class UserCommentParking {
    private int parkingIdParking;
    private int userIdUser;
    private Double note;
    private String contenu;

    @Id
    @Column(name = "Parking_idParking")
    public int getParkingIdParking() {
        return parkingIdParking;
    }

    public void setParkingIdParking(int parkingIdParking) {
        this.parkingIdParking = parkingIdParking;
    }

    @Id
    @Column(name = "User_idUser")
    public int getUserIdUser() {
        return userIdUser;
    }

    public void setUserIdUser(int userIdUser) {
        this.userIdUser = userIdUser;
    }

    @Basic
    @Column(name = "note")
    public Double getNote() {
        return note;
    }

    public void setNote(Double note) {
        this.note = note;
    }

    @Basic
    @Column(name = "contenu")
    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserCommentParking that = (UserCommentParking) o;

        if (parkingIdParking != that.parkingIdParking) return false;
        if (userIdUser != that.userIdUser) return false;
        if (note != null ? !note.equals(that.note) : that.note != null) return false;
        if (contenu != null ? !contenu.equals(that.contenu) : that.contenu != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = parkingIdParking;
        result = 31 * result + userIdUser;
        result = 31 * result + (note != null ? note.hashCode() : 0);
        result = 31 * result + (contenu != null ? contenu.hashCode() : 0);
        return result;
    }
}
