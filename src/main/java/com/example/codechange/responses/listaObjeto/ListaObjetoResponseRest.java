package com.example.codechange.responses.listaObjeto;

import com.example.codechange.responses.ResponseRest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListaObjetoResponseRest extends ResponseRest {
    private ListaObjetoResponse listaObjetoResponse = new ListaObjetoResponse();
}
