package com.github.dariozubaray.springboot.oauth.security.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.github.dariozubaray.springboot.oauth.services.IUsuarioService;
import com.github.dariozubaray.springboot.usuarios.commons.models.entity.Usuario;

import feign.FeignException;

@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {

    private Logger log = LoggerFactory.getLogger(AuthenticationSuccessErrorHandler.class);

    @Autowired
    private IUsuarioService usuarioService;

    @Override
    public void publishAuthenticationSuccess(Authentication authentication) {
        UserDetails user = (UserDetails) authentication.getPrincipal();
        log.info("Success Login: {}", user.getUsername());

        Usuario usuario = usuarioService.findByUsername(authentication.getName());
        if (usuario.getIntentos() != null && usuario.getIntentos() > 0) {
            usuario.setIntentos(0);
            usuarioService.update(usuario, usuario.getId());
        }
    }

    @Override
    public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
        log.error("Error en el login: {}", exception.getMessage());
        log.error("Usuario no valido: {}", authentication.getName());

        try {
            Usuario usuario = usuarioService.findByUsername(authentication.getName());
            if (usuario.getIntentos() == null) {
                usuario.setIntentos(0);
            }
            log.info("Intentos actuales es de {}", usuario.getIntentos());
            usuario.setIntentos(usuario.getIntentos() + 1);
            log.info("Intentos después es de {}", usuario.getIntentos());

            if (usuario.getIntentos() >= 3) {
                log.error("El usuario {} deshabilitado por alcanzar los maximos intentos", usuario.getUsername());
                usuario.setEnabled(false);
            }

            usuarioService.update(usuario, usuario.getId());
        } catch (FeignException e) {
            log.error("El usuario {} no existe en el sistema", authentication.getName());
        }

    }

}
