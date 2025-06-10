package org.israel.cookies.services.repository;

import java.sql.SQLException;
import java.util.List;

public interface Repository <T>{

    //implementamos los metodos para el crud a la base de datos
    List<T> listar() throws SQLException;

    //implementamos un metodo por id
    T porId(Long id) throws SQLException;

    //implementamos un metodo para guardar
    void guardar(T t) throws SQLException;

    //implementamos el metodo para eliminar
    void eliminar (Long id) throws SQLException;

    //implementamos un metodo para activar y desactivar

}
