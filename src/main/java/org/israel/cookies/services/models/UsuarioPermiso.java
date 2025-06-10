package org.israel.cookies.services.models;

public class UsuarioPermiso {
    private Long idUsuarioPermiso;
    private Long idUsuario;
    private Long idPermiso;

    public UsuarioPermiso() {

    }

    public UsuarioPermiso(Long idUsuarioPermiso, Long idUsuario, Long idPermiso) {
        this.idUsuarioPermiso = idUsuarioPermiso;
        this.idUsuario = idUsuario;
        this.idPermiso = idPermiso;
    }

    //traemos los metodos get and set
    public Long getIdUsuarioPermiso() {
        return idUsuarioPermiso;
    }

    public void setIdUsuarioPermiso(Long idUsuarioPermiso) {
        this.idUsuarioPermiso = idUsuarioPermiso;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(Long idPermiso) {
        this.idPermiso = idPermiso;
    }
}
