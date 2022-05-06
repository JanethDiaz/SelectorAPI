package com.danfoss.api.Controllers;

import com.danfoss.api.Models.Usuarios.Cliente;
import com.danfoss.api.Models.Usuarios.TipoCliente;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cliente")
public class ClienteController {

    @RequestMapping(value = "/listar",  method = RequestMethod.GET)
    public ResponseEntity<?> Listar(){
        try
        {
            Cliente c = new Cliente();
            return new ResponseEntity<>(new Gson().toJson(c.Listar()), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new Gson().toJson("Error al tratar de Listar" + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/listarTipoCliente",  method = RequestMethod.GET)
    public ResponseEntity<?> ListarTipoCliente(){
        try
        {
            TipoCliente tc = new TipoCliente();
            return new ResponseEntity<>(new Gson().toJson(tc.Listar()), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new Gson().toJson("Error al tratar de Listar el tipo de cliente" + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/insertar",  method = RequestMethod.POST)
    public ResponseEntity<?> Insertar(@RequestBody Cliente cliente){
        try
        {
            cliente.Insertar();
            return new ResponseEntity<>(new Gson().toJson("Cliente insertado con exito"), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new Gson().toJson("Error al tratar de insertar" + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //EL CONTROLADOR Y EL METODO DE CLIENTE ELIMINAR YA ESTABAN CERADOS EXCEPTO EN LA BASE DE DATOS
    //YO CREO UNO DE LOS DOS PUDO HABER ELIMINADO EL SP EN LA BASE DE DATOS.
    //OK
    @RequestMapping(value = "/eliminar",  method = RequestMethod.POST)
    public ResponseEntity<?> eliminar(@RequestBody Cliente cliente){
        try
        {
            cliente.Eliminar();
            return new ResponseEntity<>(new Gson().toJson("El cliente se eliminó con éxito"), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new Gson().toJson("Error al tratar de eliminar" + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/Desactivar",  method = RequestMethod.POST)
    public ResponseEntity<?> Desactivar(@RequestBody Cliente cliente){
        try
        {
            cliente.Desactivar();
            return new ResponseEntity<>(new Gson().toJson("El cliente se desactivo con éxito"), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new Gson().toJson("Error al tratar de Desactivar" + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/Activar",  method = RequestMethod.POST)
    public ResponseEntity<?> Activar(@RequestBody Cliente cliente){
        try
        {
            cliente.Activar();
            return new ResponseEntity<>(new Gson().toJson("El cliente se activo con éxito"), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new Gson().toJson("Error al tratar de activar" + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/actualizar",  method = RequestMethod.POST)
    public ResponseEntity<?> Actualizar(@RequestBody Cliente cliente){
        try
        {
            return new ResponseEntity<>(new Gson().toJson(cliente.Actualizar()), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new Gson().toJson("Error al actualizar" + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
