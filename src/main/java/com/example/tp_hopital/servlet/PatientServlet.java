package com.example.tp_hopital.servlet;


import com.example.tp_hopital.entity.Patient;
import com.example.tp_hopital.service.LoginService;
import com.example.tp_hopital.service.LoginServiceImpl;
import com.example.tp_hopital.service.PatientService;
import com.example.tp_hopital.util.HibernateSession;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "patient", value = "/patient")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
public class PatientServlet extends HttpServlet {
    private PatientService patientService;
    private LoginService loginService;
    private List<Patient> patients;

    @Override
    public void init() throws ServletException {
        patientService = new PatientService(HibernateSession.getSessionFactory());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        loginService = new LoginServiceImpl(req.getSession());
        req.setAttribute("isLogged", loginService.isLogged());
        String contextPath = req.getContextPath();
        String fullURL = req.getScheme() + "://" +
                req.getServerName() +
                ":" + req.getServerPort() +
                contextPath;
        String messageError = null;
        if(req.getParameter("action").equals("search")) {
            req.setAttribute("searchMode",true);
            patients = patientService.getPatients(req.getParameter("search"));
        }
        else {
            patients = patientService.getPatients(null);
            if(req.getParameter("name") != null && req.getParameter("phone") != null && !req.getParameter("name").equals("") && !req.getParameter("phone").equals("")) {
                String uploadPath = getServletContext().getRealPath("/") + "photos";
                File file = new File(uploadPath);
                if(!file.exists()){
                    file.mkdir();
                }
                Part photo = req.getPart("photo");
                String fileName = photo.getSubmittedFileName();
                String name= req.getParameter("name");
                String phone = req.getParameter("phone");
                LocalDate dateOfBirth = LocalDate.parse(req.getParameter("dateOfBirth"));
                photo.write(uploadPath + File.separator + fileName);
                if(patientService.createPatient(name, phone,dateOfBirth,"photos/"+fileName)) {
                    resp.sendRedirect(fullURL+"/patient");
                }else {
                    messageError = "Erreur d'ajout";
                }
            }
            else {
                messageError = "Merci de saisir les deux champs";
            }
        }
        if(messageError != null || req.getParameter("action").equals("search")) {
            req.setAttribute("patients", patients);
            req.setAttribute("messageError", messageError);
            req.getRequestDispatcher("/WEB-INF/views/patients.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String contextPath = req.getContextPath();
        String fullURL = req.getScheme() + "://" +
                req.getServerName() +
                ":" + req.getServerPort() +
                contextPath;
        loginService = new LoginServiceImpl(req.getSession());
        req.setAttribute("isLogged", loginService.isLogged());
        if(req.getParameter("id") != null && !req.getParameter("id").equals("")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Patient patient = patientService.getByIdPatient(id);
            req.setAttribute("patient", patient);
            req.setAttribute("fullURL", fullURL);
            req.getRequestDispatcher("/WEB-INF/views/patient.jsp").forward(req, resp);
        }else {
            patients = patientService.getPatients(null);
            req.setAttribute("searchMode",false);
            req.setAttribute("patients", patients);
            req.getRequestDispatcher("/WEB-INF/views/patients.jsp").forward(req, resp);
        }
    }
}
