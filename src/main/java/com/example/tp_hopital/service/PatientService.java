package com.example.tp_hopital.service;

import com.example.tp_hopital.entity.Patient;
import com.example.tp_hopital.repository.PatientRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.time.LocalDate;
import java.util.List;

public class PatientService {
    private SessionFactory _sessionFactory;
    private PatientRepository patientRepository;
    private Session session;

    public PatientService(SessionFactory sessionFactory) {
        _sessionFactory = sessionFactory;
    }

    public boolean createPatient (String name, String phone, LocalDate dateOfBirth,String urlImg){
        boolean result = false;
        session = _sessionFactory.openSession();
        session.beginTransaction();
        patientRepository = new PatientRepository(session);
        Patient patient = new Patient();
        patient.setName(name);
        patient.setPhone(phone);
        patient.setBirthDate(dateOfBirth);
        patient.setUrlPhoto(urlImg);
        try{
            patientRepository.create(patient);
            session.getTransaction().commit();
            result = true;
        }catch (Exception e){
            try{
                session.getTransaction().rollback();
            }catch (Exception except){
                System.out.println(except.getMessage());
            }
        }finally {
            session.close();
        }
        return result;
    }

    public Patient getByIdPatient (int id){
        Patient patient = null ;
        session = _sessionFactory.openSession();
        patientRepository = new PatientRepository(session);
        try{
            patient = patientRepository.findById(id);
        }catch (Exception e){

        }finally {
            session.close();
        }
        return patient;
    }

    public List<Patient> getPatients(String name){
        List<Patient> patients = null;
        session = _sessionFactory.openSession();
        patientRepository = new PatientRepository(session);
        try{
            if(name == null){
                patients = patientRepository.findAll();
            }else{
                patients = patientRepository.findAllByName(name);
            }
        }catch (Exception e){

        }finally {
            session.close();
        }
        return patients;
    }
}
