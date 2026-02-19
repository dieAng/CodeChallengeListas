package com.example.codechange.models.lista;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "listas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ListaObjeto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;
    private String nombre;
    private List<String> lista;

    public ListaObjeto(ListaObjetoDto listaObjetoDTO) {
        this.nombre = listaObjetoDTO.getNombre();
        this.lista = listaObjetoDTO.getLista();
    }
}
