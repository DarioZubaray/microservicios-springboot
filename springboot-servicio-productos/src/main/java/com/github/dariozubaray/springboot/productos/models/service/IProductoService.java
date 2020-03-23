package com.github.dariozubaray.springboot.productos.models.service;

import java.util.List;
import com.github.dariozubaray.springboot.commons.models.entity.Producto;

public interface IProductoService {

    public List<Producto> findAll();
    public Producto findById(Long id);
    public Producto save(Producto producto);
    public void delete(Long id);
}
