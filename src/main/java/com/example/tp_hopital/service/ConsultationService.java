package com.example.tp_hopital.service;

import com.example.tp_hopital.entity.Consultation;
import com.example.tp_hopital.repository.ConsultationRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Date;
import java.util.List;

public class ConsultationService {
    private PatientService patientService;
    private SessionFactory _sessionFactory;
    private ConsultationRepository consultationRepository;
    private Session session;

    public ConsultationService(SessionFactory sessionFactory){
        _sessionFactory = sessionFactory;
        patientService = new PatientService(_sessionFactory);
    }

    public boolean createConsultation (int patientId){
        boolean result = false;
        session = _sessionFactory.openSession();
        session.beginTransaction();
        consultationRepository = new ConsultationRepository(session);
        Consultation consultation  = new Consultation();
        consultation.setDateConsultation(new Date());
        consultation.setPatient(patientService.getByIdPatient(patientId));
        try{
            consultationRepository.create(consultation);
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

    public Consultation getByIdConsultation (int id){
        Consultation consultation = null ;
        session = _sessionFactory.openSession();
        consultationRepository = new ConsultationRepository(session);
        try{
            consultation = consultationRepository.findById(id);
        }catch (Exception e){

        }finally {
            session.close();
        }
        return consultation;
    }

    public int getLastConsultationId(int patientId){
        session = _sessionFactory.openSession();
        List<Consultation> consultations = patientService.getByIdPatient(patientId).getConsultations();
        int lastConsultationId = consultations.get(consultations.size()-1).getId();
        session.close();
        return lastConsultationId;

    }
}
