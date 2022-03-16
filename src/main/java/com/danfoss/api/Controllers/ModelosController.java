package com.danfoss.api.Controllers;

import com.danfoss.api.Models.Productos.Modelo;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/modelo")
public class ModelosController {

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public ResponseEntity<?> Listar() {
        try
        {
            Modelo m = new Modelo();
            return new ResponseEntity<>(new Gson().toJson(m.listar()), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new Gson().toJson(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/insertar", method = RequestMethod.POST)
    public ResponseEntity<?> Insertar(@RequestBody Modelo modelo) {
        try
        {
            return new ResponseEntity<>(new Gson().toJson(modelo.Insertar()), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new Gson().toJson(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/eliminar", method = RequestMethod.GET)
    public ResponseEntity<?> Eliminar() {
        try
        {
            Modelo m = new Modelo();
            return new ResponseEntity<>(new Gson().toJson(m.listar()), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new Gson().toJson(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/validar", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> validar(@RequestBody Modelo modelo) {
        try
        {
            Modelo m = modelo.cargarPorDescripcion(modelo.getDescripcion());
            if ( m.getId() > 0 ) {
                return new ResponseEntity<>(new Gson().toJson(true), HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(new Gson().toJson(false), HttpStatus.OK);
            }
        }
        catch (Exception e) {
            return new ResponseEntity<>(new Gson().toJson( e.getMessage() ), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
