package org.israel.cookies.services.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.israel.cookies.services.models.Usuario;
import org.israel.cookies.services.services.UsuarioService;
import org.israel.cookies.services.services.UsuarioServiceJdbcImplement;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;


@WebServlet("/usuario/form")
public class UsuarioFormControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        UsuarioService service = new UsuarioServiceJdbcImplement(conn);
        Long id;

        try {
            id = Long.parseLong(req.getParameter("idUsuario"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        Usuario usuario = new Usuario();
        if (id > 0) {
            Optional<Usuario> optionalUsuario = service.porId(id);
            if (optionalUsuario.isPresent()) {
                usuario = optionalUsuario.get();
            }
        }

        req.setAttribute("usuario", usuario);
        getServletContext().getRequestDispatcher("/formularioUsuario.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        UsuarioService service = new UsuarioServiceJdbcImplement(conn);

        String nombre = req.getParameter("nombre");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        Long idUsuario;
        try {
            idUsuario = Long.parseLong(req.getParameter("idUsuario"));
        } catch (NumberFormatException e) {
            idUsuario = 0L;
        }

        if (nombre == null || nombre.isEmpty() ||
                email == null || email.isEmpty() ||
                password == null || password.isEmpty()) {

            Usuario usuario = new Usuario();
            usuario.setIdUsuario(idUsuario);
            usuario.setNombre(nombre);
            usuario.setEmail(email);
            usuario.setPassword(password);

            req.setAttribute("usuario", usuario);
            req.setAttribute("error", "Todos los campos son obligatorios.");
            getServletContext().getRequestDispatcher("/formularioUsuario.jsp").forward(req, resp);
            return;
        }

        Usuario usuario = new Usuario();
        usuario.setIdUsuario(idUsuario);
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setPassword(password);

        service.guardar(usuario);
        resp.sendRedirect(req.getContextPath() + "/usuario");
    }


}
