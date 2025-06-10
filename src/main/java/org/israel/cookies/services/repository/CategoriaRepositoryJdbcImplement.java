package org.israel.cookies.services.repository;

import org.israel.cookies.services.models.Categorias;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaRepositoryJdbcImplement implements Repository<Categorias>{

    //obtenemos la conexcion a la base de datos
    private Connection conn;

    //constructor
    public CategoriaRepositoryJdbcImplement(Connection conn){
        this.conn = conn;
    }



    @Override
    public List<Categorias> listar() throws SQLException {
        List<Categorias> categorias = new ArrayList<>();
        // con el try interactuamos con la base de daots
        try (Statement stml = conn.createStatement();
             ResultSet rs = stml.executeQuery("select * from categoria")){

            //recorremos todos los elementos que tenemos en la base de datos
            while(rs.next()) {
                Categorias cate = getCategorias(rs);
                categorias.add(cate);
            }
        }
        return categorias;
    }



    @Override
    public Categorias porId(Long id) throws SQLException {
        Categorias categoria = null;
        //generar la consulta con un stament = deja interactuar con la base de datos
        try(PreparedStatement stmt = conn.prepareStatement(
                "select * from categoria where idcategoria=?")){
            stmt.setLong(1, id);

            //ejecutar el result set
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()) {
                    categoria=getCategorias(rs);
                }
            }
        }
    return categoria;
    }

    @Override
    public void guardar(Categorias categorias) throws SQLException {
        //inicializamos y declaramos una variable de tipo string de nombre sql
        String sql;
        //implemento un condicional para saber si el idcategoria es distinto de nulo y mayor a 0
        if (categorias.getIdCategoria() != null && categorias.getIdCategoria()>0){
            sql = "update categoria set nombre=?, descripcion=? Where idcategoria=?";
        }else{
            sql="insert into categoria(nombre,descripcion, estado) values(?,?,1)";
        }
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            if(categorias.getIdCategoria()!=null && categorias.getIdCategoria()>0){
                stmt.setString(1, categorias.getNombre());
                stmt.setString(2, categorias.getDescripcion());
                stmt.setLong(3, categorias.getIdCategoria());
            }else{
                stmt.setString(1, categorias.getNombre());
                stmt.setString(2, categorias.getDescripcion());
            }

            //stmt.setInt(3, categorias.getCondicion());
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        //usando el comamdo designado para sql indicamos que queremos eliminar
        String sql = "delete from categorias where idcategoria=?";
        //usamos el try para que cerrar el ciclo
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            //encargado de mandar la orden para que la ejercuten
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }

    }

    /*@Override
    public void estado(Long id, boolean activo) throws SQLException {
        // mediante un update indicamos que queremos modificar o no los apartados en la base de datos
        String sql = "UPDATE categorias SET estado = ? WHERE idcategoria = ?";
        //creamos el ciclo
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            //si lo queremos activo usamos el 1 caso contrario usamos el 0
            stmt.setInt(1, activo ? 1 : 0);
            stmt.setLong(2, id);
            stmt.executeUpdate();
        }
    }*/

    private static Categorias getCategorias(ResultSet rs) throws SQLException {
        Categorias cate = new Categorias();
        cate.setIdCategoria(rs.getLong("idcategoria"));
        cate.setNombre(rs.getString("nombre"));
        cate.setDescripcion(rs.getString("descripcion"));
        cate.setCondicion(rs.getInt("condicion"));
        return cate;
    }

}
