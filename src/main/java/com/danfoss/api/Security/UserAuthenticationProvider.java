package com.danfoss.api.Security;

import com.danfoss.api.Models.Usuarios.TipoUsuario;
import com.danfoss.api.Models.Usuarios.Usuario;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String usuario = authentication.getName();
        String password = authentication.getCredentials().toString();
        if (usuario.isEmpty()) {
            throw new BadCredentialsException("Favor de capturar el usuario");
        }
        if (password.isEmpty()) {
            throw  new BadCredentialsException("Favor de capturar la contraseña");
        }

        if (existeEnBD(usuario)) {
            return loginBD(usuario, password);
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }

    private boolean existeEnBD(String nombreUsuario) throws BadCredentialsException {
        Usuario u = new Usuario();

        try
        {
            u = Usuario.cargarPorNombre(nombreUsuario);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (u.getId() > 0) {
            return true;
        }
        else {
            throw new BadCredentialsException("Usuario no registrado");
        }
    }

    private UsernamePasswordAuthenticationToken loginBD(String nombreUsuario, String password) {
        Usuario usuarioApp = new Usuario();
        usuarioApp.setUsuario(nombreUsuario);
        usuarioApp.setPasword(password);
        try
        {
            usuarioApp =  usuarioApp.login();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        if ( usuarioApp.getId() > 0 ) {
            return token(nombreUsuario, password, usuarioApp.getTipoUsuario());
        }
        else {
            throw new BadCredentialsException("usuario/contraseña incorrecta");
        }
    }

    private List<GrantedAuthority> getAuthorities(TipoUsuario perfil) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(perfil.getDescripcion()));
        return authorities;
    }

    private UsernamePasswordAuthenticationToken token(String username, String password, TipoUsuario perfil) {
        return new UsernamePasswordAuthenticationToken(
                username, password, getAuthorities(perfil));
    }

}
