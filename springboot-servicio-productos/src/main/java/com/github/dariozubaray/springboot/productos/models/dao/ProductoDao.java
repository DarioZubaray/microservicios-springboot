package com.github.dariozubaray.springboot.productos.models.dao;

import org.springframework.data.repository.CrudRepository;
import com.github.dariozubaray.springboot.productos.models.entity.Producto;

public interface ProductoDao extends CrudRepository<Producto, Long>{

}
