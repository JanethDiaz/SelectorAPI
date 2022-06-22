package com.danfoss.api.Controllers;

import com.danfoss.api.DAO.ListarDeshieloDAO;
import com.danfoss.api.DAO.TemperaturaCapacidadDAO;
import com.danfoss.api.Models.ProyectosUsuario.ProyectoSeleccionA;
import com.danfoss.api.Models.ProyectosUsuario.ProyectosUsuario;
import com.danfoss.api.Models.Selecciones.*;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("selecciones")
public class SeleccionesController {

    @RequestMapping(value = "/listar",  method = RequestMethod.GET)
    public ResponseEntity<?> Listar() {
        try
        {
            Seleccion seleccion = new Seleccion();
            return new ResponseEntity<>(new Gson().toJson(seleccion.Listar()), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new Gson().toJson("Error al tratar de Listar las selecciones " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/listarGruposSeleccion",  method = RequestMethod.POST)
    public ResponseEntity<?> ListarGruposSeleccion(@RequestBody Seleccion seleccion) {
        try
        {
            SeleccionGruposProductos seleccionGruposProductos = new SeleccionGruposProductos();
            return new ResponseEntity<>(new Gson().toJson(seleccionGruposProductos.ListarPorIdSeleccion(seleccion)), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new Gson().toJson("Error al tratar de Listar los productos de la seleccion " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/listarSistemas",  method = RequestMethod.GET)
    public ResponseEntity<?> ListarSistemas() {
        try
        {
            Sistema_SVC sistema_svc = new Sistema_SVC();
            return new ResponseEntity<>(new Gson().toJson(sistema_svc.Listar()), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new Gson().toJson("Error al tratar de Listar los sistemas" + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/listarCapacidad",  method = RequestMethod.POST)
    public ResponseEntity<?> ListarCapacidad(@RequestBody Sistema_SVC sistema_svc) {
        try
        {
            Capacidad c = new Capacidad();
            return new ResponseEntity<>(new Gson().toJson(c.ListarPorSistema(sistema_svc.getId())), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new Gson().toJson("Error al tratar de Listar las capacidades del sistema " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/listarTemperatura",  method = RequestMethod.POST)
    public ResponseEntity<?> ListarTemperatura(@RequestBody TemperaturaCapacidadDAO temperaturaCapacidadDAO) {
        try
        {
            Temperatura t = new Temperatura();
            return new ResponseEntity<>(
                    new Gson().toJson(
                            t.ListarPorCapacidadSistema( temperaturaCapacidadDAO.getCapacidad().getId(),
                                    temperaturaCapacidadDAO.getSistema_svc().getId() ) )
                    , HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new Gson().toJson("Error al tratar de Listar las capacidades del sistema " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/listarDeshielo",  method = RequestMethod.POST)
    public ResponseEntity<?> ListarDeshielo(@RequestBody Temperatura temperatura) {
        try
        {
            Deshielo d = new Deshielo();
            return new ResponseEntity<>(new Gson().toJson(d.ListarPorTemperatura(temperatura.getId())), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new Gson().toJson("Error al tratar de Listar las capacidades del sistema " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/listarDeshieloPorIdPadre",  method = RequestMethod.POST)
    public ResponseEntity<?> ListarDeshieloPorIdPadre(@RequestBody Deshielo deshielo) {
        try
        {
            return new ResponseEntity<>(new Gson().toJson(deshielo.ListarPorIdPadre()), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new Gson().toJson("Error al tratar de Listar las capacidades del sistema " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/listarPorParametros",  method = RequestMethod.POST)
    public ResponseEntity<?> ListarPorParametros(@RequestBody ListarDeshieloDAO deshieloDAO) {
        try
        {
            Deshielo d = new Deshielo();
            return new ResponseEntity<>(new Gson().toJson(
                    d.ListarPorParametros(deshieloDAO.getIdSistema()
                    , deshieloDAO.getIdCapacidad()
                    , deshieloDAO.getIdTemperatura())), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new Gson().toJson("Error al tratar de Listar las capacidades del sistema " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/listarPorParametrosIdPadre1",  method = RequestMethod.POST)
    public ResponseEntity<?> ListarPorParametrosIdPadre1(@RequestBody ListarDeshieloDAO deshieloDAO) {
        try
        {
            Deshielo d = new Deshielo();
            return new ResponseEntity<>(new Gson().toJson(
                    d.ListarPorParametrosIdPadre1(deshieloDAO.getIdSistema()
                            , deshieloDAO.getIdCapacidad()
                            , deshieloDAO.getIdTemperatura()
                            , deshieloDAO.getIdPadre())), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new Gson().toJson("Error al tratar de Listar las capacidades del sistema " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/listarPorParametrosIdPadre2",  method = RequestMethod.POST)
    public ResponseEntity<?> ListarPorParametrosIdPadre2(@RequestBody ListarDeshieloDAO deshieloDAO) {
        try
        {
            Deshielo d = new Deshielo();
            return new ResponseEntity<>(new Gson().toJson(
                    d.ListarPorParametrosIdPadre2(deshieloDAO.getIdSistema()
                            , deshieloDAO.getIdCapacidad()
                            , deshieloDAO.getIdTemperatura()
                            , deshieloDAO.getIdPadre())), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new Gson().toJson("Error al tratar de Listar las capacidades del sistema " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/cargarPorProyecto",  method = RequestMethod.POST)
    public ResponseEntity<?> cargarPorProyecto(@RequestBody ProyectosUsuario proyecto) {
        try
        {
            ProyectoSeleccionA proyectoSeleccion = new ProyectoSeleccionA();
            return new ResponseEntity<>(new Gson().toJson(proyectoSeleccion.Listar(proyecto.getId())), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new Gson().toJson("Error al tratar de Listar las capacidades del sistema " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/cargarPlantilla",  method = RequestMethod.POST)
    public ResponseEntity<?> cargarPlantilla(@RequestBody Seleccion seleccion) {
        try
        {
            seleccion.setIdSistema_SVC(seleccion.getSistema_svc().getId());
            seleccion.setIdCapacidad(seleccion.getCapacidad().getId());
            seleccion.setIdTemperatura(seleccion.getTemperatura().getId());
            seleccion.setIdDeshielo(seleccion.getDeshielo().getId());
            seleccion.CargarPorParametros();
            PlantillaSeleccion plantillaSeleccion = new PlantillaSeleccion();
            plantillaSeleccion.setSeleccion(seleccion);
            plantillaSeleccion.setAreaSeleccion(seleccion.getAreaSeleccion());
            plantillaSeleccion.CargarPorId();
            return new ResponseEntity<>(new Gson().toJson(plantillaSeleccion), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new Gson().toJson("Error al tratar de Listar las capacidades del sistema " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
