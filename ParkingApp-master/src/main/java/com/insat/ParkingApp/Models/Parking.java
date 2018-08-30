package com.insat.ParkingApp.Models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by ASUS on 04/04/2017.
 */
@Entity
public class Parking {
    private long idParking;
    private double longitude;
    private double latitude;
    private String nom;
    private String horaire;

    public Parking(double longitude, double latitude, String nom, String horaire, String placeDisponibles, Double moyenne) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.nom = nom;
        this.horaire = horaire;
        this.placeDisponibles = placeDisponibles;
        this.moyenne = moyenne;
    }
    public Parking(){}

    private String placeDisponibles;
    private Double moyenne;

    @Id
    @Column(name = "idParking")
    public long getIdParking() {
        return idParking;
    }

    public void setIdParking(long idParking) {
        this.idParking = idParking;
    }

    @Basic
    @Column(name = "longitude")
    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Basic
    @Column(name = "latitude")
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Basic
    @Column(name = "nom")
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Basic
    @Column(name = "horaire")
    public String getHoraire() {
        return horaire;
    }

    public void setHoraire(String horaire) {
        this.horaire = horaire;
    }

    @Basic
    @Column(name = "placeDisponibles")
    public String getPlaceDisponibles() {
        return placeDisponibles;
    }

    public void setPlaceDisponibles(String placeDisponibles) {
        this.placeDisponibles = placeDisponibles;
    }

    @Basic
    @Column(name = "moyenne")
    public Double getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(Double moyenne) {
        this.moyenne = moyenne;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Parking parking = (Parking) o;

        if (idParking != parking.idParking) return false;
        if (Double.compare(parking.longitude, longitude) != 0) return false;
        if (Double.compare(parking.latitude, latitude) != 0) return false;
        if (nom != null ? !nom.equals(parking.nom) : parking.nom != null) return false;
        if (horaire != null ? !horaire.equals(parking.horaire) : parking.horaire != null) return false;
        if (placeDisponibles != null ? !placeDisponibles.equals(parking.placeDisponibles) : parking.placeDisponibles != null)
            return false;
        if (moyenne != null ? !moyenne.equals(parking.moyenne) : parking.moyenne != null) return false;

        return true;
    }
/*
    @Override
    public int hashCode() {
        long result;
        long temp;
        result = idParking;
        temp = Double.doubleToLongBits(longitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(latitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        result = 31 * result + (horaire != null ? horaire.hashCode() : 0);
        result = 31 * result + (placeDisponibles != null ? placeDisponibles.hashCode() : 0);
        result = 31 * result + (moyenne != null ? moyenne.hashCode() : 0);
        return result;
    }*/
}
