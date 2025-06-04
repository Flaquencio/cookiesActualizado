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


@WebServlet("/categorias/form")
public class CategoriaServlet extends HttpServlet {

    //creamos la conexion a la base de datos
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Creamos la conexion
        Connection coon = (Connection) req.getAttribute("conn");
        //creamos el nuevo categorias objeto
        CategoriaService service = new CategoriaServiceJdbcImplement(coon);
        //creamos la lista
        List<Categorias> categorias = service.listar();

        //obtenemos los parametros de la sesion
        LoginService auth = new LoginServiceSessionImplement();
        Optional<String> userNameOptional = auth.getUserName(req);

        //seteamos los atributos de categoria y el username
        req.setAttribute("categorias", categorias);
        req.setAttribute("username", userNameOptional);

        //redireccionamos a la lista de Listar Categorias.jsp
        getServletContext().getRequestDispatcher("/categoriaListar.jsp").forward(req, resp);

    }


}



/*
@WebServlet("/categoria/form")
public class CategoriaFormControlador extends HttpServlet {
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

    //Sobrescribimos el método doPost
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
}*/