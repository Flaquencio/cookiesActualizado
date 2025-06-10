package org.israel.cookies.services.models;

public class Articulo {

    //declaraqmos los atributos de la clase Articulo de la base de datos
    private Long idArticulo;
    private Categorias categoria;
    private String codigo;
    private String nombre;
    private int stock;
    private String descripcion;
    private String imagen;
    private int condicion;

    //implementamos el constructor vacio
    public Articulo() {


    }

    //implementamos un constructor que inicialize todos los parametros
    public Articulo(long idArticulo, String tipo,String codigo, String nombre, int stock, String descripcion, String imagen, int condicion) {
        this.idArticulo = idArticulo;
        Categorias categorias = new Categorias();
        categorias.setNombre(tipo);
        this.codigo = codigo;
        this.nombre = nombre;
        this.stock = stock;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.condicion = condicion;
    }

    //metodos get and set


    public Long getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Long idArticulo) {
        this.idArticulo = idArticulo;
    }

    public Categorias getCategoria() {
        return categoria;
    }

    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
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

