package org.israel.cookies.services.services;

import org.israel.cookies.services.models.Usuario;
import org.israel.cookies.services.repository.UsuarioRepositoryJdbcImplement;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UsuarioServiceJdbcImplement implements UsuarioService {

    private UsuarioRepositoryJdbcImplement repositoryJdbc;

    //implementamos un cosntructor para obtener la conecion y los metodos del crud
    public UsuarioServiceJdbcImplement(Connection conn) {
        this.repositoryJdbc = new UsuarioRepositoryJdbcImplement(conn);
    }

    @Override
    public List<Usuario> listar() {
        try {
            return repositoryJdbc.listar();
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Usuario> porId(Long id) {
        try {
            return Optional.ofNullable(repositoryJdbc.porId(id));
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void guardar(Usuario usuario) {
        try {
            repositoryJdbc.guardar(usuario);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

}
