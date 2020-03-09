package com.github.dariozubaray.springboot.items.models.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import com.github.dariozubaray.springboot.items.models.Item;
import com.github.dariozubaray.springboot.items.models.Producto;

public class ItemServiceImpl implements ItemService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Item> listar() {
        Producto[] productos = restTemplate.getForObject("http://localhost:8001/listar", Producto[].class);
        List<Producto> list = Arrays.asList(productos);
        return list.stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
    }

    @Override
    public Item detalle(Long id, Integer cantidad) {
        Map<String, String> pathVariables = new HashMap<>();
        pathVariables.put("id", id.toString());
        Producto producto = restTemplate.getForObject("http://localhost:8001/ver/{id}", Producto.class, pathVariables);
        return new Item(producto, cantidad);
    }

}
