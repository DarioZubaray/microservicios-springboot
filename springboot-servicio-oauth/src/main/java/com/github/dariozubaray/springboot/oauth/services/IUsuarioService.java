package com.github.dariozubaray.springboot.oauth.services;

import com.github.dariozubaray.springboot.usuarios.commons.models.entity.Usuario;

public interface IUsuarioService {

    public Usuario findByUsername(String username);
}
