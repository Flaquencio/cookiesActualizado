package org.israel.cookies.services.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public class LoginServiceSessionImplement implements LoginService {

    @Override
    public Optional<String> getUserName(HttpServletRequest request) {
        //obtengo la sesion en objeto
        HttpSession session = request.getSession();

        //canvertir el objeto de tipo sesion a String
        String username = (String) session.getAttribute("username");

        /*implemento una candicion en la cual se va a validar si al obtener el
        * username es distinto de nulo !=0*. Cao contrario se encarga de devolver la sesion
        vacia */

        if (username != null) {
            return Optional.of(username);
        }
        return Optional.empty();

    }
}
