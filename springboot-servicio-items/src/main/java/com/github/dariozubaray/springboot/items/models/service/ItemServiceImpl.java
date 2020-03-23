package com.github.dariozubaray.springboot.items.models.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.github.dariozubaray.springboot.items.models.Item;
import com.github.dariozubaray.springboot.commons.models.entity.Producto;

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

    @Override
    public Producto save(Producto producto) {
        String url = "http://servicio-productos/crear";
        HttpEntity<Producto> body = new HttpEntity<Producto>(producto);
        ResponseEntity<Producto> response = restTemplate.exchange(url, HttpMethod.POST, body, Producto.class);

        Producto productoResponse = response.getBody();
        return productoResponse;
    }

    @Override
    public Producto update(Producto producto, Long id) {
        String url = "http://servicio-productos/editar/{id}";
        HttpEntity<Producto> body = new HttpEntity<Producto>(producto);
        Map<String, String> pathVariables = new HashMap<>();
        pathVariables.put("id", id.toString());
        ResponseEntity<Producto> response = restTemplate.exchange(url, HttpMethod.PUT, body, Producto.class, pathVariables);
        return response.getBody();
    }

    @Override
    public void delete(Long id) {
        Map<String, String> pathVariables = new HashMap<>();
        pathVariables.put("id", id.toString());
        restTemplate.delete("http://servicio-productos/borrar/{id}", pathVariables);
    }

}
