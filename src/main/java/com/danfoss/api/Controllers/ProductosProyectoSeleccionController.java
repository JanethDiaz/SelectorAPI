package com.danfoss.api.Controllers;

import com.danfoss.api.Models.ProyectosUsuario.ProductosProyectoSeleccion;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/ProductosProyectoSeleccion")
public class ProductosProyectoSeleccionController {

    @RequestMapping(value = "/insertar",  method = RequestMethod.POST)
    public ResponseEntity<?> Insertar(@RequestBody ProductosProyectoSeleccion productosProyectoSeleccion){
        try
        {
            productosProyectoSeleccion.Insertar();

            return new ResponseEntity<>(new Gson().toJson("insertado con éxito"), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new Gson().toJson("Error al tratar de insertar" + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/actualizar",  method = RequestMethod.POST)
    public ResponseEntity<?> Actualizar(@RequestBody ProductosProyectoSeleccion productosProyectoSeleccion){
        try
        {
            return new ResponseEntity<>(new Gson().toJson(productosProyectoSeleccion.Actualizar()), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new Gson().toJson("Error al actualizar" + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/eliminar",  method = RequestMethod.POST)
    public ResponseEntity<?> eliminar(@RequestBody ProductosProyectoSeleccion productosProyectoSeleccion){
        try
        {
            productosProyectoSeleccion.Eliminar();
            return new ResponseEntity<>(new Gson().toJson("Se elimino con exito"), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new Gson().toJson("Error al tratar de eliminar" + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
