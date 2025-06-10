package org.israel.cookies.services.services;

import org.israel.cookies.services.models.UsuarioPermiso;

import java.util.List;
import java.util.Optional;

public interface UsuarioPermisoService {
    List<UsuarioPermiso> listar();
    Optional<UsuarioPermiso> porId(Long id);
    void guardar(UsuarioPermiso usuarioPermiso);

}
