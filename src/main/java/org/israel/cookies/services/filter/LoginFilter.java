package org.israel.cookies.services.filter;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.israel.cookies.services.services.LoginService;
import org.israel.cookies.services.services.LoginServiceSessionImplement;

import java.io.IOException;
import java.util.Optional;

@WebFilter({"/productos", "/agregar-carro", "/actualiza", "/categoria" })
public class LoginFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //Implemento el objetio de tipo sesion para traer el nombre del usuario
        LoginService service = new LoginServiceSessionImplement();
        Optional<String> username = service.getUserName((HttpServletRequest) request);

        //hago una condicion para verificar si el usuario esta presente
        if (username.isPresent()) {
            chain.doFilter(request, response);
        }else{
            ((HttpServletResponse)response).sendError(HttpServletResponse.SC_UNAUTHORIZED,"Lo sentimos no tienes acceso a este recurso");
        }


    }

}
