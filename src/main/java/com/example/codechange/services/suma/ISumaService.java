package com.example.codechange.services.suma;

import com.example.codechange.models.suma.SumaEntradaMayorQue;
import com.example.codechange.responses.suma.SumaResponseRest;
import org.springframework.http.ResponseEntity;

public interface ISumaService {
    ResponseEntity<SumaResponseRest> buscarPorReferencia(String referencia);

    ResponseEntity<SumaResponseRest> buscarMayorQue(SumaEntradaMayorQue sumaEntradaMayorQue);
}
