package com.example.codechange.models.lista;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListaObjetoDto {
    private String nombre;
    private List<String> lista;
}
