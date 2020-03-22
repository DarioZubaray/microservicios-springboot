package com.github.dariozubaray.springboot.items.models.service;

import java.util.List;
import com.github.dariozubaray.springboot.items.models.Item;
import com.github.dariozubaray.springboot.items.models.Producto;

public interface ItemService {

    public List<Item> listar();

    public Item detalle(Long id, Integer cantidad);

    public Producto save(Producto producto);

    public Producto update(Producto producto, Long id);

    public void delete(Long id);
}
