package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.israel.cookies.services.LoginService;
import org.israel.cookies.services.LoginServiceSessionImplement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet({"/login", "/login.html"})
public class LoginServlet extends HttpServlet {
    final static String USERNAME = "admin";
    final static String PASSWORD = "12345";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*//creamos la nueva cookie
        Cookie[] cookies = req.getCookies() != null ? req.getCookies() : new Cookie[0];

        // busco en el arreglo de cookies si existe la cookie "username" y la convierto a String
        //optional permite devolver una variable de tipo string, este se encargaria de devolver un valor
        Optional<String> cookieOptional = Arrays.stream(cookies)
                .filter(c -> "username".equals(c.getName()))
                .map(Cookie::getValue)
                .findAny();*/

        //llama a la cookie
        //LoginService auth = new LoginServiceImplement();

        //implementamos los datos desde la sesion
        LoginService auth = new LoginServiceSessionImplement();
        //de la cookie
        //Optional<String> cookieOptional=auth.getUserName(req);

        Optional<String> usernameOptional = auth.getUserName(req);

        if (usernameOptional.isPresent()) {
            resp.setContentType("text/html;charset=UTF-8");

            try (PrintWriter out = resp.getWriter()) {
                // creo la plantilla HTML
                out.print("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=\"utf-8\">");
                out.println("<title>Hola" + usernameOptional.get() + "</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Hola " + usernameOptional.get() + " ya iniciaste sesión anteriormente!</h1>");
                out.println("<p><a href='" + req.getContextPath() + "/index.html'>Volver al inicio</a></p>");
                out.println("</body>");
                out.println("</html>");
            }
        } else {
            getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username.equals(USERNAME) && password.equals(PASSWORD)) {

            /*// creamos la cookie
            Cookie usernameCookie = new Cookie("username", username);
            resp.addCookie(usernameCookie);
            resp.setContentType("text/html;charset=UTF-8");

            try (PrintWriter out = resp.getWriter()) {
                // creo la plantilla HTML
                out.print("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset=\"utf-8\">");
                out.println("<title>Bienvenido a la app</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Bienvenido a mi APP</h1>");
                out.println("</body>");
                out.println("</html>");
            }*/


            //creamos la sesion para ver usuario por sesion
            HttpSession session = req.getSession();
            session.setAttribute("username", username);

            resp.sendRedirect(req.getContextPath() + "/login.html");
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lo sentimos no tiene acceso");
        }
    }
}
