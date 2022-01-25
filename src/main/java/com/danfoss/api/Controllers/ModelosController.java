package com.danfoss.api.Controllers;

import com.danfoss.api.Models.Productos.Modelo;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/insertar", method = RequestMethod.GET)
    public ResponseEntity<?> Insertar() {
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

}
