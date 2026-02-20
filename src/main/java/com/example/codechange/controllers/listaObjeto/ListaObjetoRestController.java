package com.example.codechange.controllers.listaObjeto;

import com.example.codechange.models.lista.ListaObjeto;
import com.example.codechange.responses.listaObjeto.ListaObjetoResponseRest;
import com.example.codechange.services.listaObjeto.IListaObjetoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "API Rest para listas de objetos",
        description = "Esta API se encarga de gestionar listas personalizas para guardar cadenas de texto en una base de datos." +
                "Cada lista tiene un nombre correspondiente y una lista de cadenas de texto."
)
@RestController
@RequestMapping("/listas")
public class ListaObjetoRestController {
    @Autowired
    private IListaObjetoService service;

    @Operation(summary = "Obtener todos las listas de objetos")
    @GetMapping("/")
    private ResponseEntity<ListaObjetoResponseRest> getAll() {
        return service.getAll();
    }

    @Operation(summary = "Obtiene una lista de objetos por id")
    @GetMapping("/id/{id}")
    private ResponseEntity<ListaObjetoResponseRest> getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @Operation(summary = "Obtiene todas las listas de objetos por nombre")
    @GetMapping("/nombre/{nombre}")
    private ResponseEntity<ListaObjetoResponseRest> getAllByNombre(@PathVariable String nombre) {
        return service.getAllByNombre(nombre);
    }

    @Operation(summary = "Guarda una lista de objetos")
    @PostMapping("/")
    private ResponseEntity<ListaObjetoResponseRest> save(@RequestBody ListaObjeto lista) {
        return service.save(lista);
    }

    @Operation(summary = "Actualiza una lista de objetos")
    @PutMapping("/{id}")
    private ResponseEntity<ListaObjetoResponseRest> update(@PathVariable Long id, @RequestBody ListaObjeto lista) {
        return service.update(id, lista);
    }

    @Operation(summary = "Elimina una lista de objetos")
    @DeleteMapping("/{id}")
    private ResponseEntity<ListaObjetoResponseRest> delete(@PathVariable Long id) {
        return service.deleteById(id);
    }

    @Operation(summary = "Elimina todas las listas de objetos por nombre")
    @DeleteMapping("/todos-nombre/{nombre}")
    private ResponseEntity<ListaObjetoResponseRest> deleteAllByNombre(@PathVariable String nombre) {
        return service.deleteAllByNombre(nombre);
    }
}
