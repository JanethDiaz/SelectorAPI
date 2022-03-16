package com.danfoss.api.Security;

import com.danfoss.api.Models.Usuarios.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        Usuario usuarioApp = null;
        try {
            usuarioApp = Usuario.cargarPorNombre(authentication.getPrincipal().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        httpServletRequest.getSession().setAttribute("idUsuarioAPP", usuarioApp.getId());
    }
}
