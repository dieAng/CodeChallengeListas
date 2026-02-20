package com.example.codechange.services.listaObjeto;

import com.example.codechange.models.lista.ListaObjeto;
import com.example.codechange.responses.listaObjeto.ListaObjetoResponseRest;
import org.springframework.http.ResponseEntity;

public interface IListaObjetoService {
    ResponseEntity<ListaObjetoResponseRest> getAll();

    ResponseEntity<ListaObjetoResponseRest> getById(Long id);

    ResponseEntity<ListaObjetoResponseRest> getAllByNombre(String nombre);

    ResponseEntity<ListaObjetoResponseRest> save(ListaObjeto listaObjeto);

    ResponseEntity<ListaObjetoResponseRest> update(Long id, ListaObjeto listaObjeto);

    ResponseEntity<ListaObjetoResponseRest> deleteById(Long id);

    ResponseEntity<ListaObjetoResponseRest> deleteAllByNombre(String nombre);
}
