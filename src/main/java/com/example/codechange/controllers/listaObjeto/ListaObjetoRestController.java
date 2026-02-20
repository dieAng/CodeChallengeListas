package com.example.codechange.controllers.listaObjeto;

import com.example.codechange.models.lista.ListaObjeto;
import com.example.codechange.responses.listaObjeto.ListaObjetoResponseRest;
import com.example.codechange.services.listaObjeto.IListaObjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/listas")
public class ListaObjetoRestController {
    @Autowired
    private IListaObjetoService service;

    @GetMapping("/")
    private ResponseEntity<ListaObjetoResponseRest> get() {
        return service.get();
    }

    @GetMapping("/id/{id}")
    private ResponseEntity<ListaObjetoResponseRest> getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/nombre/{nombre}")
    private ResponseEntity<ListaObjetoResponseRest> getByNombre(@PathVariable String nombre) {
        return service.getByNombre(nombre);
    }

    @PostMapping("/")
    private ResponseEntity<ListaObjetoResponseRest> save(@RequestBody ListaObjeto lista) {
        return service.save(lista);
    }

    @PutMapping("/{id}")
    private ResponseEntity<ListaObjetoResponseRest> update(
            @PathVariable Long id, @RequestBody ListaObjeto lista
    ) {
        return service.update(id, lista);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<ListaObjetoResponseRest> delete(@PathVariable Long id) {
        return service.deleteById(id);
    }

    @DeleteMapping("/nombre/{nombre}")
    private ResponseEntity<ListaObjetoResponseRest> deleteByNombre(@PathVariable String nombre) {
        return service.deleteByNombre(nombre);
    }
}
