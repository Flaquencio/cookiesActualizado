package org.israel.cookies.services.repository;

import org.israel.cookies.services.models.UsuarioPermiso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioPermisoRepositoryJdbcImplement implements Repository<UsuarioPermiso> {

    //creamos conexion con la base de datos
    private Connection conn;

    //constructor para recibir la conexion
    public UsuarioPermisoRepositoryJdbcImplement(Connection conn) {
        this.conn = conn;
    }

    //creamos la lista
    @Override
    public List<UsuarioPermiso> listar() throws SQLException {
        List<UsuarioPermiso> lista = new ArrayList<>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select * from usuario_permiso")) {
            while (rs.next()) {
                lista.add(getUsuarioPermiso(rs));
            }
        }
        return lista;
    }

    //buscar usuario por id
    @Override
    public UsuarioPermiso porId(Long id) throws SQLException {
        UsuarioPermiso UsuPermiso = null;
        try (PreparedStatement stmt = conn.prepareStatement("select * from usuario_permiso where idusuario_permiso = ?")) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    UsuPermiso = getUsuarioPermiso(rs);
                }
            }
        }
        return UsuPermiso;
    }

    //guardar permiso usaurio por id
    @Override
    public void guardar(UsuarioPermiso UsuPermiso) throws SQLException {
        String sql;
        boolean isUpdate = UsuPermiso.getIdUsuarioPermiso() != null && UsuPermiso.getIdUsuarioPermiso() > 0;

        if (isUpdate) {
            sql = "update usuario_permiso set idusuario=?, idpermiso=? where idusuario_permiso=?";
        } else {
            sql = "insert into usuario_permiso(idusuario, idpermiso) values (?, ?)";
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, UsuPermiso.getIdUsuario());
            stmt.setLong(2, UsuPermiso.getIdPermiso());

            if (isUpdate) {
                stmt.setLong(3, UsuPermiso.getIdUsuarioPermiso());
            }

            stmt.executeUpdate();
        }
    }

    //eliminar permiso del usaurio por id
    @Override
    public void eliminar(Long id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("delete from usuario_permiso where idusuario_permiso = ?")) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }


    private UsuarioPermiso getUsuarioPermiso(ResultSet rs) throws SQLException {
        UsuarioPermiso UsuPermiso = new UsuarioPermiso();
        UsuPermiso.setIdUsuarioPermiso(rs.getLong("idusuario_permiso"));
        UsuPermiso.setIdUsuario(rs.getLong("idusuario"));
        UsuPermiso.setIdPermiso(rs.getLong("idpermiso"));
        return UsuPermiso;
    }
}
