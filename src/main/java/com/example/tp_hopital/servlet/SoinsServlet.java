package com.example.tp_hopital.servlet;

import com.example.tp_hopital.service.FicheSoinsService;
import com.example.tp_hopital.util.HibernateSession;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(name = "soins", value = "/soins")
public class SoinsServlet extends HttpServlet {
    private FicheSoinsService ficheSoinsService;

    @Override
    public void init() throws ServletException {
        ficheSoinsService = new FicheSoinsService(HibernateSession.getSessionFactory());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String contextPath = req.getContextPath();
        String fullURL = req.getScheme() + "://" +
                req.getServerName() +
                ":" + req.getServerPort() +
                contextPath;
        if(req.getParameter("consultationId") != null && !req.getParameter("consultationId").equals("") && !req.getParameter("content").equals("")) {
            int consultationId = Integer.parseInt(req.getParameter("consultationId"));
            if(ficheSoinsService.createSoins(consultationId, req.getParameter("content"))) {
                resp.sendRedirect(fullURL+"/consultation?id="+consultationId);
            }
        }
    }
}
