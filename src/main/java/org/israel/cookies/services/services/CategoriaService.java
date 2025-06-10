package org.israel.cookies.services.services;

import org.israel.cookies.services.models.Categorias;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {
    List<Categorias> listar();
    Optional<Categorias> porId(Long id);

    void guardar(Categorias categoria);
}
