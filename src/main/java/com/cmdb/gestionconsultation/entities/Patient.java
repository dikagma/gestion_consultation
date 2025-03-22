package com.cmdb.gestionconsultation.entities;

import java.util.List;

public class Patient {
    private long id;
    private String nom;
    private String prenoms;
    private String tel;
    private List<Consultation> consultations;

    public Patient() {
    }
    public Patient(String nom, String prenoms, String tel) {
        this.nom = nom;
        this.prenoms = prenoms;
        this.tel = tel;
    }

    @Override
    public String toString() {
        return nom + " " + prenoms;

    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenoms() {
        return prenoms;
    }

    public void setPrenoms(String prenoms) {
        this.prenoms = prenoms;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
