package com.danfoss.api.DAO;

import org.springframework.web.multipart.MultipartFile;

public class UploadProductosDAO {
    private MultipartFile file;
    private int idUsuario;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
