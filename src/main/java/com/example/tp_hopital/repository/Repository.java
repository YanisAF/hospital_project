package com.example.tp_hopital.repository;

import org.hibernate.Session;

import java.util.List;

public abstract class Repository<T> {
    protected Session _session;
    public Repository(Session session) {
        _session = session;
    }

    public boolean create(T entity) {
        _session.save(entity);
        return true;
    }

    public boolean update(T entity) {
        _session.update(entity);
        return true;
    }
    public boolean delete(T entity) {
        _session.delete(entity);
        return true;
    }

    public abstract List<T> findAll();

    public abstract T findById(int id);
}
