package com.danfoss.api.Controllers;

import com.danfoss.api.DAO.UploadProductosDAO;
import com.danfoss.api.ExcelHelper.ExcelHelper;
import com.danfoss.api.Message.ResponseMessage;
import com.danfoss.api.Models.Productos.Producto;
import com.danfoss.api.Models.Selecciones.Seleccion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/excel")
public class ExelController {

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("idUsuario") int idUsuario)
    {
        String message;

        UploadProductosDAO uploadProductosDAO = new UploadProductosDAO();
        uploadProductosDAO.setFile(file);
        uploadProductosDAO.setIdUsuario(idUsuario);
        if (ExcelHelper.hasExcelFormat(uploadProductosDAO.getFile())) {
            try
            {
                Producto p = new Producto();
                p.ActualizarListaPrecios(uploadProductosDAO);
                message = "Uploaded the file successfully: " + uploadProductosDAO.getFile().getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            }
            catch (Exception e) {
                message = "Could not upload the file: " + uploadProductosDAO.getFile().getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    @RequestMapping(value = "/uploadPorCliente", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage> uploadFileCliente(@RequestParam("file") MultipartFile file,
                                                             @RequestParam("idCliente") int idCliente,
                                                             @RequestParam("idUsuario") int idUsuarioRegistra) {
        String message;

        if ( ExcelHelper.hasExcelFormat(file) ) {
            try
            {
                Producto p = new Producto();
                p.ActualizarListaPreciosCliente(idCliente, idUsuarioRegistra, file);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            }
            catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    @RequestMapping(value = "/uploadDataBase", method = RequestMethod.POST)
    public ResponseEntity<ResponseMessage> uploadFileDataBase(@RequestParam("file") MultipartFile file)
    {
        String response;
        if (ExcelHelper.hasExcelFormat(file)) {
            try
            {
                Seleccion seleccion = new Seleccion();
                seleccion.ImportarSelecciones(file);
                response = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(response));
            }
            catch (Exception e) {
                response = "Could not upload the file: " + file.getOriginalFilename() + "! " + e.getMessage();
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(response));
            }
        }
        response = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(response));
    }


}
