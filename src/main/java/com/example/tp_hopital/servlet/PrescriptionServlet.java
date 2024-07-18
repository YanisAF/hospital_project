package com.example.tp_hopital.servlet;

import com.example.tp_hopital.service.PrescriptionService;
import com.example.tp_hopital.util.HibernateSession;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(name = "prescription",value = "/prescription")
public class PrescriptionServlet extends HttpServlet {
    private PrescriptionService prescriptionService;

    @Override
    public void init() throws ServletException {
        prescriptionService = new PrescriptionService(HibernateSession.getSessionFactory());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String contextPath = request.getContextPath();
        String fullURL = request.getScheme() + "://" +
                request.getServerName() +
                ":" + request.getServerPort() +
                contextPath;
        if(request.getParameter("consultationId") != null && !request.getParameter("consultationId").equals("") && !request.getParameter("content").equals("")) {
            int consultationId = Integer.parseInt(request.getParameter("consultationId"));
            if(prescriptionService.createPrescription(consultationId, request.getParameter("content"))) {
                response.sendRedirect(fullURL+"/consultation?id="+consultationId);
            }
        }
    }
}
