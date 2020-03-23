package com.github.dariozubaray.springboot.items.models.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.dariozubaray.springboot.items.models.Item;
import com.github.dariozubaray.springboot.commons.models.entity.Producto;
import com.github.dariozubaray.springboot.items.models.clientes.ProductoClienteRest;

@Service("ItemServiceFeign")
public class ItemServiceFeign implements ItemService {

    @Autowired
    private ProductoClienteRest clienteFeign;

    @Override
    public List<Item> listar() {
        return clienteFeign.listar().stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
    }

    @Override
    public Item detalle(Long id, Integer cantidad) {
        Producto p = clienteFeign.detalle(id);
        return new Item(p, cantidad);
    }

    @Override
    public Producto save(Producto producto) {
        return clienteFeign.crear(producto);
    }

    @Override
    public Producto update(Producto producto, Long id) {
        return clienteFeign.editar(producto, id);
    }

    @Override
    public void delete(Long id) {
        clienteFeign.eliminar(id);
    }

}
