package com.example.tp_hopital.entity;


import jakarta.persistence.*;

@Entity
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String content;
    @OneToOne
    private Consultation consultation;

    public Prescription() {
    }

    public Prescription(int id, String content, Consultation consultation) {
        this.id = id;
        this.content = content;
        this.consultation = consultation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }
}
