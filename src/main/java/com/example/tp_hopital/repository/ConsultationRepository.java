package com.example.tp_hopital.repository;

import com.example.tp_hopital.entity.Consultation;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ConsultationRepository extends Repository<Consultation> {
    public ConsultationRepository(Session session) {
        super(session);
    }

    @Override
    public List<Consultation> findAll() {
        return _session.createQuery("from Consultation").list();
    }

    @Override
    public Consultation findById(int id) {
        return _session.get(Consultation.class, id);
    }

    public List<Consultation> findAllByPatientId(int patientId) {
        Query<Consultation> query = _session.createQuery("from Consultation where patient.id = :patientId");
        query.setParameter("patientId", patientId);
        return query.list();
    }
}
