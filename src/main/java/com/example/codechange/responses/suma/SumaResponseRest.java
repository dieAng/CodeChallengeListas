package com.example.codechange.responses.suma;

import com.example.codechange.responses.ResponseRest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SumaResponseRest extends ResponseRest {
    private SumaResponse sumaResponse = new SumaResponse();
}
