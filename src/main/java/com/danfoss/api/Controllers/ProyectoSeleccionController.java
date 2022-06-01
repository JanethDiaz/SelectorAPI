package com.danfoss.api.Controllers;

import com.danfoss.api.DAO.SeleccionDAO;
import com.danfoss.api.Models.ProyectosUsuario.ProyectoSeleccionA;
import com.danfoss.api.Models.Selecciones.Seleccion;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ProyectoSeleccion")
public class ProyectoSeleccionController {

    @RequestMapping(value = "/insertar",  method = RequestMethod.POST)
    public ResponseEntity<?>  Insertar(@RequestBody SeleccionDAO seleccionDAO){
        try
        {

            Seleccion seleccion = getSeleccionPorParametros(seleccionDAO);
            ProyectoSeleccionA proyectoSeleccionA = new ProyectoSeleccionA();
            proyectoSeleccionA.setIdSeleccion(seleccion.getId());
            proyectoSeleccionA.setIdUsuario(seleccionDAO.getIdUsuario());
            proyectoSeleccionA.setIdProyecto(seleccionDAO.getIdProyecto());
            proyectoSeleccionA.setCantidad(seleccionDAO.getCantidad());
            proyectoSeleccionA.setAreaSeleccion(seleccionDAO.getAreaSeleccion());
            proyectoSeleccionA.Insertar();
            return new ResponseEntity<>(new Gson().toJson("Selección guardada con éxito"), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new Gson().toJson("Error al tratar de insertar" + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Seleccion getSeleccionPorParametros(SeleccionDAO seleccionDAO) throws Exception {
        Seleccion seleccion = new Seleccion();
        seleccion.setIdSistema_SVC(seleccionDAO.getSistema_svc().getId());
        seleccion.setIdCapacidad(seleccionDAO.getCapacidad().getId());
        seleccion.setIdTemperatura(seleccionDAO.getTemperatura().getId());
        seleccion.setIdDeshielo(seleccionDAO.getDeshielo().getId());
        seleccion.CargarPorParametros();
        return seleccion;
    }

    @RequestMapping(value = "/actualizar",  method = RequestMethod.POST)
    public ResponseEntity<?> Actualizar(@RequestBody ProyectoSeleccionA proyectoSeleccion){
        try
        {
            return new ResponseEntity<>(new Gson().toJson(proyectoSeleccion.Actualizar()), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new Gson().toJson("Error al actualizar" + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/eliminar",  method = RequestMethod.POST)
    public ResponseEntity<?> eliminar(@RequestBody ProyectoSeleccionA proyectoSeleccion){
        try
        {
            proyectoSeleccion.Eliminar();
            return new ResponseEntity<>(new Gson().toJson("Se eliminó con éxito"), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new Gson().toJson("Error al tratar de eliminar" + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
