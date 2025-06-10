package org.israel.cookies.services.repository;

import org.israel.cookies.services.models.Articulo;
import org.israel.cookies.services.models.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositoryJdbcImplement implements Repository<Usuario>{

    //Creamos la concexion con al base de datos
    private Connection conn;

    //constructor para recibir la conexion
    public UsuarioRepositoryJdbcImplement(Connection conn) {
        this.conn = conn;
    }

    //creamos lista
    @Override
    public List<Usuario> listar() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select * from usuario")) {
            while (rs.next()) {
                Usuario usuario = getUsuario(rs);
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }

    //Busqueda de usuario por id
    @Override
    public Usuario porId(Long id) throws SQLException {
        Usuario usuario = null;
        try (PreparedStatement stmt = conn.prepareStatement("select * from usuario where idusuario = ?")) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = getUsuario(rs);
                }
            }
        }
        return usuario;
    }

    //creamos la cadena para guardar los usurios
    @Override
    public void guardar(Usuario usuario) throws SQLException {
        String sql;
        boolean esUpdate = usuario.getIdUsuario() != null && usuario.getIdUsuario() > 0;

        if (esUpdate) {
            sql = "update usuario set nombre=?, apellido=?, tipo_documento=?, num_documento=?, telefono=?, email=?, login=?, clave=?, imagen=?, condicion=? where idusuario=?";
        } else {
            sql = "insert into usuario(nombre, apellido, tipo_documento, num_documento, telefono, email, login, clave, imagen, condicion) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        }try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApellido());
            stmt.setString(3, usuario.getTipoDocumento());
            stmt.setString(4, usuario.getNumeroDocumento());
            stmt.setString(5, usuario.getTelefono());
            stmt.setString(6, usuario.getEmail());
            stmt.setString(7, usuario.getLogin());
            stmt.setString(8, usuario.getClave());
            stmt.setString(9, usuario.getImagen());
            stmt.setInt(10, usuario.getCondicion());

            if (esUpdate) {
                stmt.setLong(11, usuario.getIdUsuario());
            }

            stmt.executeUpdate();
        }
    }

    //creamos la cadena para eliminar usando el id
    @Override
    public void eliminar(Long id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM usuario WHERE idusuario = ?")) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    private Usuario getUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(rs.getLong("idusuario"));
        usuario.setNombre(rs.getString("nombre"));
        usuario.setApellido(rs.getString("apellido"));
        usuario.setTipoDocumento(rs.getString("tipo_documento"));
        usuario.setNumeroDocumento(rs.getString("num_documento"));
        usuario.setTelefono(rs.getString("telefono"));
        usuario.setEmail(rs.getString("email"));
        usuario.setLogin(rs.getString("login"));
        usuario.setClave(rs.getString("clave"));
        usuario.setImagen(rs.getString("imagen"));
        usuario.setCondicion(rs.getInt("condicion"));
        return usuario;
    }

}
