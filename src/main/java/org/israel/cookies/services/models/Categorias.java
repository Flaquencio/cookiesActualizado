package org.israel.cookies.services.models;

public class Categorias {
    //encapsular todos los parametros de los objetos
    private Long idCategoria;
    private String nombre;
    private String descripcion;
    private int condicion;

    //implementar un constructor basio en javabeans
    public Categorias() {

    }

    //inicializamos un constructor con los parametros del objeto
    public Categorias(Long idCategoria, String nombre, String descripcion, int condicion) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.condicion = condicion;
    }

    //implementamos los metodos get and set para que puedan usar los datos

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCondicion() {
        return condicion;
    }

    public void setCondicion(int condicion) {
        this.condicion = condicion;
    }
}
