package com.cmdb.gestionconsultation.entities;

import java.sql.Date;

public class Consultation {
    private int id;
    private Date date_consultation;
    private String description;
    Patient patient;

    public Consultation() {
    }

    public Consultation(Date date_consultation, String description, Patient patient) {
        this.date_consultation = date_consultation;
        this.description = description;
        this.patient = patient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate_consultation() {
        return date_consultation;
    }

    public void setDate_consultation(Date date_consultation) {
        this.date_consultation = date_consultation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
