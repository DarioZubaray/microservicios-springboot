package com.github.dariozubaray.springboot.oauth.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.dariozubaray.springboot.usuarios.commons.models.entity.Usuario;

@FeignClient(name="servicio-usuarios")
public interface UsuarioFeignClient {

    @GetMapping("/usuarios/search/buscarUsuario")
    public Usuario findByUseername(@RequestParam String username);
}
