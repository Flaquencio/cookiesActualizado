package org.israel.cookies.services.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.israel.cookies.services.models.Categorias;
import org.israel.cookies.services.services.CategoriaService;
import org.israel.cookies.services.services.CategoriaServiceJdbcImplement;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;


@WebServlet("/categoria/form")
public class CategoriaFormControlador extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // traemos la conexión a la base de datos
        Connection conn = (Connection) req.getAttribute("conn");
        CategoriaService service = new CategoriaServiceJdbcImplement(conn);
        Long id;
        //validamos si el numero
        try {
            // guarmas lo que estamos queriendo ingresar en idcategoria
            id = Long.parseLong(req.getParameter("idCategoria"));
        } catch (NumberFormatException e) {
            id = 0L;
        }

        // Creamos un objeto Categoria vacio
        Categorias categorias = new Categorias();
        // Verificamos si el id > 0
        if (id > 0) {
            // Creamos una variable de tipo optional para obtener la categoria por id
            Optional<Categorias> optionalCategoria = service.porId(id);
            // Si la variable optional está presente obtenemos todos los valores
            if (optionalCategoria.isPresent()) {
                categorias = optionalCategoria.get();
            }
        }

        // Seteamos los atributos en el alcance de request
        req.setAttribute("categorias", categorias);
        getServletContext().getRequestDispatcher("/formularioCategoria.jsp").forward(req, resp);
    }

    // sobreescribimos el metodo post
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        CategoriaService service = new CategoriaServiceJdbcImplement(conn);

        String nombre = req.getParameter("nombre");
        String descripcion = req.getParameter("descripcion");

        //obtemos los id de las categorias
        Long idCategoria;
        try {
            idCategoria = Long.parseLong(req.getParameter("idCategoria"));
        } catch (NumberFormatException e) {
            idCategoria = 0L;
        }

        //verificamos is los campos estan se encuentran vacios
        if(nombre==null|| nombre.isEmpty() || descripcion==null  || descripcion.isEmpty()){
            Categorias categoria = new Categorias();
            categoria.setIdCategoria(idCategoria);
            categoria.setNombre(nombre);
            categoria.setDescripcion(descripcion);

            req.setAttribute("categoria", categoria);
            req.setAttribute("error","Se requiere que todos los campos se encuentren llenos.");
            getServletContext().getRequestDispatcher("/formularioCategoria.jsp").forward(req, resp);
            return;
        }


        //proceso para que se guarden los datos ingresados
        Categorias categoria = new Categorias();
        categoria.setIdCategoria(idCategoria);
        categoria.setNombre(nombre);
        categoria.setDescripcion(descripcion);
        service.guardar(categoria);

        // redirigimos el estado para que se pueda ejecutar el metodo post
        resp.sendRedirect(req.getContextPath() + "/categoria");
    }

}
