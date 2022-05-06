package com.danfoss.api.Controllers;

import com.danfoss.api.Models.ProyectosUsuario.ProyectosUsuario;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/proyectosUsuario")
public class ProyectosUsuarioController {

    @RequestMapping(value = "/insertar",  method = RequestMethod.POST)
    public ResponseEntity<?> Insertar(@RequestBody ProyectosUsuario proyectosUsuario) {
        try
        {
            proyectosUsuario.Insertar();

            return new ResponseEntity<>(new Gson().toJson("Proyecto guardado con éxito"), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new Gson().toJson("Error al tratar de guardar el proyecto " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/listar",  method = RequestMethod.POST)
    public ResponseEntity<?> Listar(@RequestBody ProyectosUsuario c){
        try
        {
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
            proyectosUsuario.Actualizar();
            return new ResponseEntity<>(new Gson().toJson("Proyecto actualizado con éxito"), HttpStatus.OK);
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
