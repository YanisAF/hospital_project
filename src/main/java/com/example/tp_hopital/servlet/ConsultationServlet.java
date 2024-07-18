package com.example.tp_hopital.servlet;

import com.example.tp_hopital.entity.Consultation;
import com.example.tp_hopital.service.ConsultationService;
import com.example.tp_hopital.util.HibernateSession;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(name = "consultations",value = "/consultation")
public class ConsultationServlet extends HttpServlet {
    private ConsultationService consultationService;

    @Override
    public void init() throws ServletException {
        consultationService = new ConsultationService(HibernateSession.getSessionFactory());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("id") != null && !request.getParameter("id").equals("")) {
            int consultationId = Integer.parseInt(request.getParameter("id"));
            Consultation consultation = consultationService.getByIdConsultation(consultationId);
            request.setAttribute("consultation", consultation);
            request.getRequestDispatcher("/WEB-INF/views/consultation.jsp").forward(request, response);
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        if(request.getParameter("patientId") != null && !request.getParameter("patientId").equals("")) {
            int patientId = Integer.parseInt(request.getParameter("patientId"));
            String contextPath = request.getContextPath();
            String fullURL = request.getScheme() + "://" +
                    request.getServerName() +
                    ":" + request.getServerPort() +
                    contextPath;
            if(consultationService.createConsultation(patientId)) {
                response.sendRedirect(fullURL+"/consultation?id="+consultationService.getLastConsultationId(patientId));
            }
        }
    }
}
