package org.israel.cookies.services.services;

import org.israel.cookies.services.models.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> listar();
    Optional<Usuario> porId(Long id);
    void guardar(Usuario usuario);
}
