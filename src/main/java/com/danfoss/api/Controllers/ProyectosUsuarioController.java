package com.danfoss.api.Controllers;

import com.danfoss.api.Models.ProyectosUsuario.ProyectosUsuario;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/ProyectosUsuario")
public class ProyectosUsuarioController {

    @RequestMapping(value = "/insertar",  method = RequestMethod.POST)
    public ResponseEntity<?> Insertar(@RequestBody ProyectosUsuario proyectosUsuario){
        try
        {
            proyectosUsuario.Insertar();

            return new ResponseEntity<>(new Gson().toJson("insertado con éxito"), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new Gson().toJson("Error al tratar de insertar" + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/listar",  method = RequestMethod.GET)
    public ResponseEntity<?> Listar(){
        try
        {
            ProyectosUsuario c = new ProyectosUsuario();
            return new ResponseEntity<>(new Gson().toJson(c.Listar()), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new Gson().toJson("Error al tratar de Listar" + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/actualizar",  method = RequestMethod.POST)
    public ResponseEntity<?> Actualizar(@RequestBody ProyectosUsuario proyectosUsuario){
        try
        {
            return new ResponseEntity<>(new Gson().toJson(proyectosUsuario.Actualizar()), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new Gson().toJson("Error al actualizar" + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/eliminar",  method = RequestMethod.POST)
    public ResponseEntity<?> eliminar(@RequestBody ProyectosUsuario proyectosUsuario){
        try
        {
            proyectosUsuario.Eliminar();
            return new ResponseEntity<>(new Gson().toJson("Se elimino con exito"), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new Gson().toJson("Error al tratar de eliminar" + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
