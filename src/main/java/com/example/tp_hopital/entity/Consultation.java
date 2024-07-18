package com.example.tp_hopital.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;


@Entity
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Temporal(TemporalType.DATE)
    private Date dateConsultation;

    @ManyToOne
    private Patient patient;

    
    @OneToOne(mappedBy = "consultation",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private FicheSoins ficheSoins;

    @OneToOne(mappedBy = "consultation",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Prescription prescription;

    public Consultation() {
    }

    public Consultation(int id, Date dateConsultation, Patient patient, FicheSoins ficheSoins, Prescription prescription) {
        this.id = id;
        this.dateConsultation = dateConsultation;
        this.patient = patient;
        this.ficheSoins = ficheSoins;
        this.prescription = prescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateConsultation() {
        return dateConsultation;
    }

    public void setDateConsultation(Date dateConsultation) {
        this.dateConsultation = dateConsultation;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public FicheSoins getFicheSoins() {
        return ficheSoins;
    }

    public void setFicheSoins(FicheSoins ficheSoins) {
        this.ficheSoins = ficheSoins;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }
}
