package com.danfoss.api.Controllers;

import com.danfoss.api.Models.Productos.Producto;
import com.danfoss.api.Models.Usuarios.Usuario;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producto")

public class ProductoController {
//    @RequestMapping(value = "/cargarPorId", method = RequestMethod.GET)
//    public ResponseEntity<?> Listar(int idProducto) {
//        try {
//            return new ResponseEntity<>(new Gson().toJson(Producto.cargarPorId(idProducto)), HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(new Gson().toJson("Error al tratar de cargar productos" + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @RequestMapping(value = "/insertar", method = RequestMethod.POST)
//    public ResponseEntity<?> Insertar() {
//        try {
//            return new ResponseEntity<>(new Gson().toJson(Producto.Insertar()), HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(new Gson().toJson("Error al tratar de Insertar productos" + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
////    @RequestMapping(value = "/modificar", method = RequestMethod.POST)
////    public ResponseEntity<?> Modificar() {
////        try {
////            return new ResponseEntity<>(new Gson().toJson(Producto.Actualizar()), HttpStatus.OK);
////        } catch (Exception e) {
////            return new ResponseEntity<>(new Gson().toJson("Error al tratar de Insertar productos" + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
////        }
////    }
//
//    @RequestMapping(value = "/eliminar", method = RequestMethod.POST)
//    public ResponseEntity<?> Eliminar() {
//        try {
//            return new ResponseEntity<>(new Gson().toJson(Producto.Eliminar()), HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(new Gson().toJson("Error al tratar de Eliminar productos" + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @RequestMapping(value = "/listar", method = RequestMethod.GET)
//    public ResponseEntity<?> Listar() {
//        try {
//            return new ResponseEntity<>(new Gson().toJson(Producto.Listar()), HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(new Gson().toJson("Error al tratar de Listar productos" + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

}
