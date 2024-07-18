package com.example.tp_hopital.service;

import jakarta.servlet.http.HttpSession;

public class LoginServiceImpl implements LoginService {
    private HttpSession _session;

    public LoginServiceImpl(HttpSession session) {
        _session = session;
    }

    @Override
    public boolean isLogged() {
        return _session.getAttribute("isLogged") != null && (boolean) _session.getAttribute("isLogged") == true;
    }

    @Override
    public boolean login(String user, String password) {
        if (user.equals("admin") && password.equals("admin")) {
            _session.setAttribute("isLogged", true);
            return true;
        }
        return false;
    }
}
