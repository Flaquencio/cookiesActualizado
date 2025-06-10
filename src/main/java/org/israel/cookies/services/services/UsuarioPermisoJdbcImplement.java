package org.israel.cookies.services.services;


import org.israel.cookies.services.models.UsuarioPermiso;
import org.israel.cookies.services.repository.UsuarioPermisoRepositoryJdbcImplement;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UsuarioPermisoJdbcImplement implements UsuarioPermisoService {

    private UsuarioPermisoRepositoryJdbcImplement repositoryJdbc;

    //implementamos un cosntructor para obtener la conecion y los metodos del crud
    public UsuarioPermisoJdbcImplement(Connection conn) {
        this.repositoryJdbc = new UsuarioPermisoRepositoryJdbcImplement(conn);
    }

    @Override
    public List<UsuarioPermiso> listar() {
        try {
            return repositoryJdbc.listar();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<UsuarioPermiso> porId(Long id) {
        try {
            return Optional.ofNullable(repositoryJdbc.porId(id));
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void guardar(UsuarioPermiso usuarioPermiso) {
        try {
            repositoryJdbc.guardar(usuarioPermiso);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }
    /*
    @Override
    public void eliminar(Long id) {
        try {
            repositoryJdbc.eliminar(id);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

     */

}
