package com.danfoss.api.Controllers;

import com.danfoss.api.Models.Usuarios.Usuario;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @RequestMapping(value = "/insertar",  method = RequestMethod.POST)
    public ResponseEntity<?>  Insertar(@RequestBody Usuario usuario){
        try
        {
            return new ResponseEntity<>(new Gson().toJson(usuario.Insertar()), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new Gson().toJson("Error al tratar de insertar usuario" + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(value = "/actualizar",  method = RequestMethod.PUT)
    public ResponseEntity<?>  Actualizar(@RequestBody Usuario usuario){
        try
        {
            return new ResponseEntity<>(new Gson().toJson(usuario.Actualizar()), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new Gson().toJson("Error al tratar de Modificar usuario" + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/eliminar",  method = RequestMethod.POST)
    public ResponseEntity<?>  Eliminar(@RequestParam("IdUsuario") int IdUsuario){
        try
        {
            Usuario u = new Usuario();
            u.setId(IdUsuario);
            return new ResponseEntity<>(new Gson().toJson(u.Eliminar()), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new Gson().toJson("Error al tratar de Eliminar usuario" + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(value = "/listar",  method = RequestMethod.GET)
    public ResponseEntity<?>  Listar(){
        try
        {
            Usuario u = new Usuario();
            return new ResponseEntity<>(new Gson().toJson(u.Listar()), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new Gson().toJson("Error al tratar de Listar usuario" + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
