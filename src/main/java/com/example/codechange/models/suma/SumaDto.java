package com.example.codechange.models.suma;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SumaDto {
    private Date fecha;
    private Integer suma;
    private String comentario;
}
