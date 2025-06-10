package org.israel.cookies.services.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.israel.cookies.services.models.UsuarioPermiso;
import org.israel.cookies.services.services.UsuarioPermisoJdbcImplement;
import org.israel.cookies.services.services.UsuarioPermisoService;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;


@WebServlet("/usuario_permiso/form")
public class UsuarioPermisoFormControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        UsuarioPermisoService service = new UsuarioPermisoJdbcImplement(conn);
        Long id;

        try {
            id = Long.parseLong(req.getParameter("idUsuarioPermiso"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        UsuarioPermiso permiso = new UsuarioPermiso();
        if (id > 0) {
            Optional<UsuarioPermiso> optionalPermiso = service.porId(id);
            if (optionalPermiso.isPresent()) {
                permiso = optionalPermiso.get();
            }
        }

        req.setAttribute("usuarioPermiso", permiso);
        getServletContext().getRequestDispatcher("/formularioUsuarioPermiso.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        UsuarioPermisoService service = new UsuarioPermisoJdbcImplement(conn);

        Long idUsuarioPermiso, idUsuario, idPermiso;
        try {
            idUsuarioPermiso = Long.parseLong(req.getParameter("idUsuarioPermiso"));
        } catch (NumberFormatException e) {
            idUsuarioPermiso = 0L;
        }

        try {
            idUsuario = Long.parseLong(req.getParameter("idUsuario"));
            idPermiso = Long.parseLong(req.getParameter("idPermiso"));
        } catch (NumberFormatException e) {
            req.setAttribute("error", "ID de usuario y permiso deben ser válidos.");
            getServletContext().getRequestDispatcher("/formularioUsuarioPermiso.jsp").forward(req, resp);
            return;
        }

        UsuarioPermiso permiso = new UsuarioPermiso();
        permiso.setIdUsuarioPermiso(idUsuarioPermiso);
        permiso.setIdUsuario(idUsuario);
        permiso.setIdPermiso(idPermiso);

        service.guardar(permiso);
        resp.sendRedirect(req.getContextPath() + "/usuario_permiso");
    }

}
