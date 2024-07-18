package com.example.tp_hopital.repository;

import com.example.tp_hopital.entity.Patient;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class PatientRepository extends Repository<Patient> {
    public PatientRepository(Session session) {
        super(session);
    }

    @Override
    public List<Patient> findAll() {
        return _session.createQuery("from Patient").list();
    }

    @Override
    public Patient findById(int id) {
        return _session.get(Patient.class, id);
    }

    public List<Patient> findAllByName(String name) {
        Query<Patient> query = _session.createQuery("from Patient p where p.name = :name");
        query.setParameter("name", name);
        return query.list();
    }
}
