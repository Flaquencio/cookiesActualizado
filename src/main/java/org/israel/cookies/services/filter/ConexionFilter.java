package org.israel.cookies.services.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.israel.cookies.services.services.ServiceJdbcException;
import org.israel.cookies.services.util.ConexionBaseDatos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter("/*")
public class ConexionFilter  implements Filter {
    //la clase filter siempre va a estar orientada a las peticiones request


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        //obtenemos la conexion a la base de datos
        try (Connection conn = ConexionBaseDatos.getConnection()) {
            //implementa un if para saber si esta activado el autocommit
            if(conn.getAutoCommit()){
                conn.setAutoCommit(false);
            }
            try{
                //seteamos los parametros de la conexcion
                request.setAttribute("conn", conn);
                //filtramos la conexcion
                chain.doFilter(request, response);
                conn.commit();
            }catch(SQLException | ServiceJdbcException e ){
                //la consulta no se ejecuta y regresa a su estado anterior de la consulta
                conn.rollback();
                ((HttpServletResponse)response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                e.printStackTrace();
            }

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
