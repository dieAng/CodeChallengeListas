package com.example.codechange.services.suma;

import com.example.codechange.models.dao.ISumaDao;
import com.example.codechange.models.suma.Suma;
import com.example.codechange.models.suma.SumaEntradaMayorQue;
import com.example.codechange.responses.suma.SumaResponseRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SumaServiceImpl implements ISumaService {
    private static final Logger LOG = LoggerFactory.getLogger(SumaServiceImpl.class);
    @Autowired
    private ISumaDao sumaDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<SumaResponseRest> buscarPorReferencia(String referencia) {
        LOG.info("Buscando suma por referencia {}:", referencia);

        SumaResponseRest response = new SumaResponseRest();
        List<Suma> sumas = new ArrayList<>();

        try {
            Optional<Suma> suma = sumaDao.findById(referencia);

            if (suma.isPresent()) {
                sumas.add(suma.get());
                response.getSumaResponse().setSumas(sumas);
                response.setMetadata("Ok", "00", "Éxito al buscar suma por referencia");

            } else {
                LOG.error("Error al buscar suma por referencia {}:", referencia);
                response.setMetadata("Error", "-1", "Error al buscar suma por referencia");

                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            LOG.error("Error al buscar suma por referencia {}: {}", referencia, e.getMessage());
            response.setMetadata("Error", "-1", "Error al buscar suma por referencia");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<SumaResponseRest> buscarMayorQue(@RequestBody SumaEntradaMayorQue sumaEntradaMayorQue) {
        LOG.info("Buscando suma mayor que {}:", sumaEntradaMayorQue.getMayorQue());

        SumaResponseRest response = new SumaResponseRest();
        List<Suma> sumas = new ArrayList<>();

        try {
            Optional<Suma> suma = sumaDao.findById(sumaEntradaMayorQue.getReferencia());

            if (suma.isPresent()) {
                if (suma.get().getSuma() > sumaEntradaMayorQue.getMayorQue()) {
                    sumas.add(suma.get());
                    response.getSumaResponse().setSumas(sumas);

                } else {
                    LOG.info("No hay suma mayor que {}", sumaEntradaMayorQue.getMayorQue());
                    response.setMetadata("Éxito", "00", "No hay suma mayor que");

                    return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
                }

            } else {
                LOG.error("Error al buscar suma con referencia {}:", sumaEntradaMayorQue.getReferencia());
                response.setMetadata("Error", "-1", "Error al buscar suma con referencia");

                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            LOG.error("Error al buscar suma mayor que {}: {}", sumaEntradaMayorQue.getMayorQue(), e.getMessage());
            response.setMetadata("Error", "-1", "Error al buscar suma mayor que");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
