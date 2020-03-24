package com.github.dariozubaray.springboot.usuarios.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.github.dariozubaray.springboot.usuarios.models.entity.Usuario;

public interface UsuarioDao extends PagingAndSortingRepository<Usuario, Long>{

    public Usuario findByUsername(String username);

    @Query("select u from Usuario u where u.username = ?1")
    public Usuario obtenerPorNombreusuario(String nombreusuario);

    @Query(value = "select * from usuarios where username = ?1", nativeQuery = true)
    public Usuario obtenerPorConsultaNativa(String username);
}
