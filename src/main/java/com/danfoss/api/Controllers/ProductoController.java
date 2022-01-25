package com.danfoss.api.Controllers;

import com.danfoss.api.Models.Productos.HistorialPrecioProducto;
import com.danfoss.api.Models.Productos.Producto;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @RequestMapping(value = "/insertar", method = RequestMethod.POST)
    public ResponseEntity<?> Listar(@RequestBody Producto producto) {
        try
        {
            int idProducto =  producto.Insertar();
            HistorialPrecioProducto hpp = new HistorialPrecioProducto();
            hpp.setIdProducto(idProducto);
            hpp.setPrecio(producto.getPrecio());
            hpp.setIdUsuarioRegistro(1);
            hpp.Insertar();
            return new ResponseEntity<>(new Gson().toJson("Producto insertado"), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(new Gson().toJson("Error al tratar de cargar productos" + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/eliminar", method = RequestMethod.POST)
    public ResponseEntity<?> Eliminar(@RequestParam("IdProducto") int IdProducto) {
        try
        {
            Producto p = new Producto();
            p.setId(IdProducto);
            return new ResponseEntity<>(new Gson().toJson(p.Eliminar()), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new Gson().toJson("Error al tratar de Eliminar producto" + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/cargar", method = RequestMethod.GET)
    public ResponseEntity<?> Cargar(@RequestParam("IdProducto") int IdProducto) {
        try
        {
            Producto p = new Producto();
            return new ResponseEntity<>(new Gson().toJson(p.cargarPorId(IdProducto)), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new Gson().toJson("Error al tratar de Eliminar producto" + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(value = "/actualizar",  method = RequestMethod.POST)
    public ResponseEntity<?>  Actualizar(@RequestBody Producto producto) {
        try
        {
            return new ResponseEntity<>(new Gson().toJson(producto.Actualizar()), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new Gson().toJson("Error al tratar de Modificar Producto" + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public ResponseEntity<?> Listar() {
        try
        {
            Producto p = new Producto();
            return new ResponseEntity<>(new Gson().toJson(p.Listar()), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(new Gson().toJson(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @RequestMapping(value = "/validar", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> validar(@RequestParam("codigo") String codigo) {
        try
        {
            Producto p = new Producto();
            p.cargarPorCodigo(codigo);
            if ( p.getId() > 0 ) {
                return new ResponseEntity<>(new Gson().toJson(true), HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(new Gson().toJson(false), HttpStatus.OK);
            }
        }
        catch (Exception e) {
            return new ResponseEntity<>(new Gson().toJson("Error al tratar de cargar productos" + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
