package com.example.tp_hopital.entity;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String phone;
    private LocalDate birthDate;
    private String urlPhoto;
    @OneToMany(mappedBy = "patient",fetch = FetchType.EAGER)
    private List<Consultation> consultations;


    public Patient() {
    }

    public Patient(int id, String name, String phone, List<Consultation> consultations) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.consultations = consultations;
    }

    public Patient(int id, String name, String phone, LocalDate birthDate, List<Consultation> consultations) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.birthDate = birthDate;
        this.consultations = consultations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }
}
