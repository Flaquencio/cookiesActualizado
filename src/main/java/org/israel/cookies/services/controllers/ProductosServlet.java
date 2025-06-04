package controllers;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.israel.cookies.services.models.Productos;
import org.israel.cookies.services.ProductoService;
import org.israel.cookies.services.ProductoServiceImplement;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/productos")

public class ProductosServlet extends HttpServlet {

        //creacion de productos en clase
    /*@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductoService service = new ProductoServiceImplement();
        List<Productos> productos = service.listar();

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        // Creo la plantilla HTML
        out.print("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"utf-8\">");
        out.println("<title>Listar Producto</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Listar producto</h1>");
        out.println("<table>");
        out.println("<tr>");
        out.println("<th>ID PRODUCTO</th>");
        out.println("<th>NOMBRE</th>");
        out.println("<th>CATEGORIA</th>");
        out.println("<th>PRECIO</th>");
        out.println("</tr>");

        productos.forEach(p -> {
            out.println("<tr>");
            out.println("<td>" + p.getId() + "</td>");
            out.println("<td>" + p.getNombre() + "</td>");
            out.println("<td>" + p.getTipo() + "</td>");
            out.println("<td>" + p.getPrecio() + "</td>");
            out.println("</tr>");
        });

        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }*/

    //creacion para el apartado de sesiones
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //traemos los datos de productos y la lista de los mismos
        ProductoService service = new ProductoServiceImplement();
        List<Productos> productos = service.listar();


        //ver si la sesion se encuentra activa
        //usar en el caso que no se tenga la sesion activa
        HttpSession session = req.getSession(false);
        //usar si la sesion se encuentra activa
        String username = (session != null) ? (String) session.getAttribute("username") : null;

        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<head>");
            out.println("<meta charset=\"utf-8\">");
            out.println("<title>Listar Producto</title>");
            out.println("</head>");
            out.println("<body>");

            if (username != null) {
                out.println("<h1>Bienvenido " + username + " espero encuentres lo que necesites </h1>");
                out.println("<h2>Listar producto</h2>");
                out.println("<table>");
                out.println("<tr>");
                out.println("<th>ID PRODUCTO</th>");
                out.println("<th>NOMBRE</th>");
                out.println("<th>CATEGORIA</th>");
                out.println("<th>PRECIO</th>");
                out.println("</tr>");

                    productos.forEach(p ->{
                        out.println("<tr>");
                        out.println("<td>" + p.getId() + "</td>");
                        out.println("<td>" + p.getNombre() + "</td>");
                        out.println("<td>" + p.getTipo() + "</td>");
                        out.println("<td>" + p.getPrecio() + "</td>");
                        out.println("</tr>");
                    });

                //out.println("<h2><a href='index.html'>Volver</a></h2>");
                out.println("<form action='index.html'><button type='submit'>Volver</button></form>");
                //out.println("<a href=\"index.html\">Volver a inicio</a>");

            } else {
                out.println("<h2>No tienes una cuenta, si quieres revisar los los datos por favor crea una en <a href='login.jsp'>login</a>.</h2>");
            }

            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}