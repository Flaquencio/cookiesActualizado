package org.israel.cookies.services.services;

import org.israel.cookies.services.models.Categorias;
import org.israel.cookies.services.repository.CategoriaRepositoryJdbcImplement;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CategoriaServiceJdbcImplement implements CategoriaService {

    private CategoriaRepositoryJdbcImplement repositoryJdbc;

    //implementamos un cosntructor para obtener la conecion y los metodos del crud
    public CategoriaServiceJdbcImplement(Connection conn) {
        this.repositoryJdbc = new CategoriaRepositoryJdbcImplement(conn);
    }

    @Override
    public List<Categorias> listar() {
        try {
            return repositoryJdbc.listar();
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public Optional<Categorias> porId(Long id) {
        try {
            return Optional.ofNullable(repositoryJdbc.porId(id));
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }

    @Override
    public void guardar(Categorias categoria) {
        try {
            repositoryJdbc.guardar(categoria);
        } catch (SQLException throwables) {
            throw new ServiceJdbcException(throwables.getMessage(), throwables.getCause());
        }
    }
}
