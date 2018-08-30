package com.insat.ParkingApp.Models;

/**
 * Created by ASUS on 30/04/2017.
 */
public class ReponseParking
{
    public long getIdParking() {
        return idParking;
    }

    public void setIdParking(long idParking) {
        this.idParking = idParking;
    }

    private long idParking;
    private String nom;
    private String horaire;

    public String getPlaceDisponibles() {
        return placeDisponibles;
    }

    public void setPlaceDisponibles(String placeDisponibles) {
        this.placeDisponibles = placeDisponibles;
    }

    public Double getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(Double moyenne) {
        this.moyenne = moyenne;
    }

    private String placeDisponibles;
    private Double moyenne;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getHoraire() {
        return horaire;
    }

    public void setHoraire(String horaire) {
        this.horaire = horaire;
    }

    public Long getDurationInSeconds() {
        return durationInSeconds;
    }

    public void setDurationInSeconds(long durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
    }

    long durationInSeconds ;
    public ReponseParking(long idParking,String nom, String horaire, String distanceToOrigine, String durationToOrigine,double moyenne,String placeDisponibles,Long durationInSeconds) {
        this.nom= nom;
        this.horaire=horaire;
        this.distanceToOrigine = distanceToOrigine;
        this.durationToOrigine = durationToOrigine;
        this.placeDisponibles=placeDisponibles;
        this.moyenne=moyenne;
        this.idParking=idParking;
        this.placeDisponibles=placeDisponibles;
        this.durationInSeconds=durationInSeconds;
    }

    private String distanceToOrigine;
    private String durationToOrigine;



    public void setDistanceToOrigine(String distanceToOrigine) {
        this.distanceToOrigine = distanceToOrigine;
    }

    public void setDurationToOrigine(String durationToOrigine) {
        this.durationToOrigine = durationToOrigine;
    }


    public String getDistanceToOrigine() {
        return distanceToOrigine;
    }

    public String getDurationToOrigine() {
        return durationToOrigine;
    }


}
