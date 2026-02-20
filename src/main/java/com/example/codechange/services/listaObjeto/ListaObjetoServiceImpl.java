package com.example.codechange.services.listaObjeto;

import com.example.codechange.models.dao.IListaObjetoDao;
import com.example.codechange.models.lista.ListaObjeto;
import com.example.codechange.responses.listaObjeto.ListaObjetoResponseRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ListaObjetoServiceImpl implements IListaObjetoService {
    private static final Logger LOG = LoggerFactory.getLogger(ListaObjetoServiceImpl.class);
    @Autowired
    private IListaObjetoDao listaObjetoDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ListaObjetoResponseRest> getAll() {
        LOG.info("Buscando listas de objetos");

        ListaObjetoResponseRest response = new ListaObjetoResponseRest();

        try {
            List<ListaObjeto> listas = (List<ListaObjeto>) listaObjetoDao.findAll();
            response.getListaObjetoResponse().setListasObjeto(listas);
            response.setMetadata("Ok", "00", "Éxito al buscar listas de objetos");

        } catch (Exception e) {
            LOG.error("Error al buscar listas de objetos: {}", e.getMessage());
            response.setMetadata("Error", "-1", "Error al buscar listas de objetos");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(response);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ListaObjetoResponseRest> getById(Long id) {
        LOG.info("Buscando lista de objetos por id");

        ListaObjetoResponseRest response = new ListaObjetoResponseRest();
        List<ListaObjeto> listas = new ArrayList<>();

        try {
            Optional<ListaObjeto> listaObjeto = listaObjetoDao.findById(id);

            if (listaObjeto.isPresent()) {
                listas.add(listaObjeto.get());
                response.getListaObjetoResponse().setListasObjeto(listas);
                response.setMetadata("Ok", "00", "Éxito al buscar lista de objetos por id");

            } else {
                LOG.error("Error al buscar lista de objetos con ID: {}", id);
                response.setMetadata("Error", "-1", "No se encontró lista de objetos con ID: " + id);

                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            LOG.error("Error al buscar lista de objetos por id: {}", e.getMessage());
            response.setMetadata("Error", "-1", "Error al buscar lista de objetos por id");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<ListaObjetoResponseRest> getAllByNombre(String nombre) {
        LOG.info("Buscando todas las listas de objetos por nombre");

        ListaObjetoResponseRest response = new ListaObjetoResponseRest();

        try {
            List<ListaObjeto> listas = (List<ListaObjeto>) listaObjetoDao.findAll();
            List<ListaObjeto> listasObjetoBuscada = listas.stream().
                    filter(listaObjeto -> listaObjeto.getNombre().equals(nombre))
                    .toList();
            listas.clear();

            if (!listasObjetoBuscada.isEmpty()) {
                listas.addAll(listasObjetoBuscada);
                response.getListaObjetoResponse().setListasObjeto(listas);
                response.setMetadata("Ok", "00", "Éxito al buscar todas las listas de objetos por nombre");

            } else {
                LOG.error("Error al buscar todas las listas de objetos por nombre: {}", nombre);
                response.setMetadata("Error", "-1", "No se encontró ninguna lista de objetos con nombre: " + nombre);

                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            LOG.error("Error al buscar todas las listas de objetos por nombre {}: {}", nombre, e.getMessage());
            response.setMetadata("Error", "-1", "Error al buscar todas las listas de objetos con nombre");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(response);
    }

    @Override
    @Transactional
    public ResponseEntity<ListaObjetoResponseRest> save(ListaObjeto listaObjeto) {
        LOG.info("Guardando lista de objetos: {}", listaObjeto);

        ListaObjetoResponseRest response = new ListaObjetoResponseRest();
        List<ListaObjeto> listas = new ArrayList<>();

        try {
            ListaObjeto listaObjetoGuardada = listaObjetoDao.save(listaObjeto);

            if (listaObjetoGuardada != null) {
                listas.add(listaObjetoGuardada);
                response.getListaObjetoResponse().setListasObjeto(listas);
                response.setMetadata("Ok", "00", "Éxito al guardar lista de objetos");

            } else {
                LOG.error("Error al guardar lista de objetos: {}", listaObjeto);
                response.setMetadata("Error", "-1", "Error al guardar lista de objetos");

                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            LOG.error("Error al guardar lista de objetos {}: {}", listaObjeto, e.getMessage());
            response.setMetadata("Error", "-1", "Error al guardar lista de objetos");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ListaObjetoResponseRest> update(Long id, ListaObjeto listaObjeto) {
        LOG.info("Actualizando lista de objetos con id: {}", id);

        ListaObjetoResponseRest response = new ListaObjetoResponseRest();
        List<ListaObjeto> listas = new ArrayList<>();

        try {
            Optional<ListaObjeto> listaObjetoBuscada = listaObjetoDao.findById(id);

            if (listaObjetoBuscada.isPresent()) {
                listaObjetoBuscada.get().setNombre(listaObjeto.getNombre());
                listaObjetoBuscada.get().setLista(listaObjeto.getLista());

                ListaObjeto listaObjetoActualizada = listaObjetoDao.save(listaObjetoBuscada.get());

                if (listaObjetoActualizada != null) {
                    listas.add(listaObjetoActualizada);
                    response.getListaObjetoResponse().setListasObjeto(listas);
                    response.setMetadata("Ok", "00", "Éxito al actualizar lista de objetos");

                } else {
                    LOG.error("Error al actualizar la lista objeto {}", listaObjeto);
                    response.setMetadata("Error", "-1", "Error al actualizar la lista objeto");

                    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
                }

            } else {
                LOG.error("Error al actualizar la lista objeto {}", listaObjeto);
                response.setMetadata("Error", "-1", "Error al actualizar la lista objeto");

                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOG.error("Error al actualizar la lista objeto {}: {}", listaObjeto, e.getMessage());
            response.setMetadata("Error", "-1", "Error al actualizar la lista objeto");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ListaObjetoResponseRest> deleteById(Long id) {
        LOG.info("Eliminando lista de objetos con id: {}", id);

        ListaObjetoResponseRest response = new ListaObjetoResponseRest();

        try {
            listaObjetoDao.deleteById(id);
            response.setMetadata("Ok", "00", "Éxito al eliminar lista de objetos");

        } catch (Exception e) {
            LOG.error("Error al eliminar lista de objetos con id: {}", id);
            response.setMetadata("Error", "-1", "Error al eliminar lista de objetos");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ListaObjetoResponseRest> deleteAllByNombre(String nombre) {
        LOG.info("Eliminando todas las listas de objetos con nombre: {}", nombre);

        ListaObjetoResponseRest response = new ListaObjetoResponseRest();

        try {
            List<ListaObjeto> listas = (List<ListaObjeto>) listaObjetoDao.findAll();
            List<ListaObjeto> listasObjetoBuscada = listas.stream().
                    filter(listaObjeto -> listaObjeto.getNombre().equals(nombre))
                    .toList();
            listas.clear();


            if (!listasObjetoBuscada.isEmpty()) {
                listaObjetoDao.deleteAll(listasObjetoBuscada);
                response.setMetadata("Ok", "00", "Éxito al eliminar todas las listas de objetos");

            } else {
                LOG.error("Error al buscar las listas de objetos por nombre: {}", nombre);
                response.setMetadata("Error", "-1", "No se encontró ninguna lista de objetos con nombre: " + nombre);

                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            LOG.error("Error al eliminar todas las listas de objetos con nombre {}: {}", nombre, e.getMessage());
            response.setMetadata("Error", "-1", "Error al eliminar todas las listas de objetos por nombre");

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(response);
    }
}
