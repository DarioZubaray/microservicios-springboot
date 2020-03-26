package com.github.dariozubaray.springboot.usuarios.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.github.dariozubaray.springboot.usuarios.commons.models.entity.Usuario;

@RepositoryRestResource(path = "usuarios")
public interface UsuarioDao extends PagingAndSortingRepository<Usuario, Long>{

    @RestResource(path = "buscarUsuario")
    public Usuario findByUsername(@Param("nombre") String username);

    @Query("select u from Usuario u where u.username = ?1")
    public Usuario obtenerPorNombreusuario(String nombreusuario);

    @Query(value = "select * from usuarios where username = ?1", nativeQuery = true)
    public Usuario obtenerPorConsultaNativa(String username);
}
