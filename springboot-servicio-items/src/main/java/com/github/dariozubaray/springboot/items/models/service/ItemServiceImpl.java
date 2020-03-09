package com.github.dariozubaray.springboot.items.models.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.github.dariozubaray.springboot.items.models.Item;
import com.github.dariozubaray.springboot.items.models.Producto;

@Service("ItemRestTemplate")
public class ItemServiceImpl implements ItemService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Item> listar() {
        Producto[] productos = restTemplate.getForObject("http://servicio-productos/listar", Producto[].class);
        List<Producto> list = Arrays.asList(productos);
        return list.stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
    }

    @Override
    public Item detalle(Long id, Integer cantidad) {
        Map<String, String> pathVariables = new HashMap<>();
        pathVariables.put("id", id.toString());
        Producto producto = restTemplate.getForObject("http://servicio-productos/ver/{id}", Producto.class, pathVariables);
        return new Item(producto, cantidad);
    }

}
