package com.example.tp_hopital.repository;

import com.example.tp_hopital.entity.FicheSoins;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;


public class SoinsRepository extends Repository<FicheSoins> {
    public SoinsRepository(Session session) {
        super(session);
    }

    @Override
    public List<FicheSoins> findAll() {
        return _session.createQuery("from FicheSoins").list();
    }

    @Override
    public FicheSoins findById(int id) {
        return _session.get(FicheSoins.class, id);
    }

    public List<FicheSoins> findAllByConsultationId(int consultationId) {
        Query<FicheSoins> query = _session.createQuery("from FicheSoins where consultation.id = :consultationId");
        query.setParameter("consultationId", consultationId);
        return query.list();
    }
}
