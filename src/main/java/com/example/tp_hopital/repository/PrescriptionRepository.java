package com.example.tp_hopital.repository;

import com.example.tp_hopital.entity.Prescription;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class PrescriptionRepository extends Repository<Prescription> {
    public PrescriptionRepository(Session session) {
        super(session);
    }

    @Override
    public List<Prescription> findAll() {
        return _session.createQuery("from Prescription").list();
    }

    @Override
    public Prescription findById(int id) {
        return _session.get(Prescription.class,id);
    }

    public List<Prescription> findAllByConsultationId(int consultationId) {
        Query<Prescription> query = _session.createQuery("from Prescription where consultation.id = :consultationId");
        query.setParameter("consultationId", consultationId);
        return query.list();
    }
}
