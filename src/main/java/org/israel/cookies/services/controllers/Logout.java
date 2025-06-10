package org.israel.cookies.services.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.israel.cookies.services.services.LoginService;
import org.israel.cookies.services.services.LoginServiceSessionImplement;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/logout")
public class Logout extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //creamos e implementamos el logout
        LoginService auth = new LoginServiceSessionImplement();
        Optional<String> usernameOptional = auth.getUserName(req);

        // se crea una condicion para saber si la sesion se encuentra presente
        if (usernameOptional.isPresent()) {
            HttpSession session = req.getSession();
            //destruimos la sesion
            session.invalidate();
        }

        resp.sendRedirect(req.getContextPath() + "/login.html");
    }
}
