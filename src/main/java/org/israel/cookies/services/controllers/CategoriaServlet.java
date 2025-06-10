package org.israel.cookies.services.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.OverridesAttribute;
import org.israel.cookies.services.models.Categorias;
import org.israel.cookies.services.repository.CategoriaRepositoryJdbcImplement;
import org.israel.cookies.services.services.CategoriaService;
import org.israel.cookies.services.services.CategoriaServiceJdbcImplement;
import org.israel.cookies.services.services.LoginService;
import org.israel.cookies.services.services.LoginServiceSessionImplement;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;


@WebServlet("/categorias")
public class CategoriaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Creamos la conexion
        Connection conn = (Connection) req.getAttribute("conn");
        //Creamos el nuevo objeto de Categorias
        CategoriaService service= new CategoriaServiceJdbcImplement(conn);
        List<Categorias> categorias = service.listar();

        //Obtengo el username
        LoginService auth= new LoginServiceSessionImplement();
        Optional<String> userName= auth.getUserName(req);

        //Seteamos los parámetros
        req.setAttribute("categorias", categorias);
        req.setAttribute("username", userName);
        //redireccionamos a la vista de categoria
        getServletContext().getRequestDispatcher("/categoriaListar.jsp").forward(req, resp);


    }
}

    /*
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //traemos la conexión a la base de datos
        Connection conn = (Connection) req.getAttribute("conn");
        CategoriaService service = new CategoriaServiceJdbcImplement(conn);
        Long id;
        //Validamos que el campo ingresado sea un número
        try{
            //se guarda el mapeo de id categoria en esta variable
            id = Long.parseLong(req.getParameter("idCategoria"));
        }catch(NumberFormatException e){
            id = 0L;
        }

        //Creamos un objeto Categoria vacío
        Categorias categorias = new Categorias();
        //Verificamos si el id > 0
        if (id > 0){
            //Creamos una variable de tipo optional para obtener la categoria por id
            Optional<Categorias> optionalCategoria = service.porId(id);
            //Si la variable optional esta presente obtenemos todos los valores
            if(optionalCategoria.isPresent()){
                categorias = optionalCategoria.get();
            }
        }

        //Seteamos los atributos en el alcance de request
        req.setAttribute("categorias", categorias);
        getServletContext().getRequestDispatcher("/formularioCategoria.jsp").forward(req, resp);
    }

    //sobreescribimos el metodo post
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        CategoriaService service = new CategoriaServiceJdbcImplement(conn);
        String nombre = req.getParameter("nombre");
        String descripcion = req.getParameter("descripcion");

        //Obtenemos el idCategoria
        Long idCategoria;
        try{
            idCategoria = Long.parseLong(req.getParameter("idCategoria"));
        }catch(NumberFormatException e){
            idCategoria = 0L;
        }

        Categorias categoria = new Categorias();
        categoria.setIdCategoria(idCategoria);
        categoria.setNombre(nombre);
        categoria.setDescripcion(descripcion);
        service.guardar(categoria);
        //Redireccionamos al listado para que no se ejecute el metodo doPost
        resp.sendRedirect(req.getContextPath() + "/categoria");
    }

}
*/
