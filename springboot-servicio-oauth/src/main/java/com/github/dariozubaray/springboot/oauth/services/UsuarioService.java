package com.github.dariozubaray.springboot.oauth.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.github.dariozubaray.springboot.oauth.clients.UsuarioFeignClient;
import com.github.dariozubaray.springboot.usuarios.commons.models.entity.Usuario;

@Service
public class UsuarioService implements UserDetailsService {

    private Logger log = LoggerFactory.getLogger(UsuarioService.class);

    @Autowired
    private UsuarioFeignClient client;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = client.findByUsername(username);
        if (usuario == null) {
            String msj = "Error en el login, no existe el usuario '"+username+"' en el sistema";
            log.error(msj);
            throw new UsernameNotFoundException(msj);
        }

        log.info("Usuario autenticado: " + usuario.getUsername());
        boolean accountNonExpired = true, credentialsNonExpired = true, accountNonLocked = true;
        List<GrantedAuthority> authorities = usuario.getRoles()
                                                    .stream()
                                                    .map(role -> new SimpleGrantedAuthority(role.getNombre()))
                                                    .peek(authority -> log.info("Role: " + authority.getAuthority()))
                                                    .collect(Collectors.toList());

        return new User(usuario.getUsername(), 
                usuario.getPassword(), 
                usuario.getEnabled(), 
                accountNonExpired, 
                credentialsNonExpired, 
                accountNonLocked, 
                authorities);
    }

}
