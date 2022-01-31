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
            return new ResponseEntity<>(new Gson().toJson("Error al tratar de Listar usuario" + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
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
            return new ResponseEntity<>(new Gson().toJson("Error al tratar de Listar cbo tipoCliente" + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/insertar",  method = RequestMethod.POST)
    public ResponseEntity<?> Insertar(@RequestBody Cliente cliente){
        try
        {

            return new ResponseEntity<>(new Gson().toJson(cliente.Insertar()), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new Gson().toJson("Error al tratar de Listar usuario" + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
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
            return new ResponseEntity<>(new Gson().toJson("Error al tratar de Listar usuario" + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
