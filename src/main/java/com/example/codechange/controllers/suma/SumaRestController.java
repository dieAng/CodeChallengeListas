package com.example.codechange.controllers.suma;

import com.example.codechange.models.suma.SumaEntradaMayorQue;
import com.example.codechange.responses.suma.SumaResponseRest;
import com.example.codechange.services.suma.ISumaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sumas")
public class SumaRestController {
    @Autowired
    private ISumaService service;

    @GetMapping("/{referencia}")
    public ResponseEntity<SumaResponseRest> getSuma(@PathVariable String referencia) {
        return service.buscarPorReferencia(referencia);
    }

    @PostMapping("/mayor-que")
    public ResponseEntity<SumaResponseRest> getMayorQue(@RequestBody SumaEntradaMayorQue sumaEntradaMayorQue) {
        return service.buscarMayorQue(sumaEntradaMayorQue);
    }
}
