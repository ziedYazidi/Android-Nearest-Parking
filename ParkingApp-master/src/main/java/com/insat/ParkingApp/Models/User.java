package com.insat.ParkingApp.Models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by ASUS on 04/04/2017.
 */
@Entity
public class User {
    private long idUser;
    private String nom;
    private String prenom;
    private String email;
    private Integer isResponsible;
    private String hashedMdp;

    public User(String nom, String prenom, String email, int isResponsible, String hashedMdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.isResponsible = isResponsible;
        this.hashedMdp = hashedMdp;
    }
    public User(){}
    @Id
    @Column(name = "idUser")
    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
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
    @Column(name = "prenom")
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "isResponsible")
    public Integer getIsResponsible() {
        return isResponsible;
    }

    public void setIsResponsible(Integer isResponsible) {
        this.isResponsible = isResponsible;
    }





    @Basic
    @Column(name = "hashedMdp")
    public String getHashedMdp() {
        return hashedMdp;
    }

    public void setHashedMdp(String hashedMdp) {
        this.hashedMdp = hashedMdp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (idUser != user.idUser) return false;
        if (nom != null ? !nom.equals(user.nom) : user.nom != null) return false;
        if (prenom != null ? !prenom.equals(user.prenom) : user.prenom != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        /*if (isResponsible != 0 ? isResponsible==(user.isResponsible) : user.isResponsible != null)
            return false;*/
        if (hashedMdp != null ? !hashedMdp.equals(user.hashedMdp) : user.hashedMdp != null) return false;

        return true;
    }

  //  @Override
    /*public lon hashCode() {
        long result = idUser;
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        result = 31 * result + (prenom != null ? prenom.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        //result = 31 * result + (isResponsible != null ? isResponsible.hashCode() : 0);
        result = 31 * result + (hashedMdp != null ? hashedMdp.hashCode() : 0);
        return result;
    }*/
}
