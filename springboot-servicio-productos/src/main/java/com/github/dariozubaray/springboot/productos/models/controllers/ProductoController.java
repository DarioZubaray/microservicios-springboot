package com.github.dariozubaray.springboot.productos.models.controllers;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.github.dariozubaray.springboot.productos.models.entity.Producto;
import com.github.dariozubaray.springboot.productos.models.service.IProductoService;

@RestController
public class ProductoController {

    @Autowired
    private Environment env;
    @Value("${server.port}")
    private Integer port;

    @Autowired
    private IProductoService productoService;

    @GetMapping("/listar")
    public List<Producto> listar() {
        return productoService.findAll().stream().map(p -> {
//            p.setPuerto(Integer.parseInt(env.getProperty("local.server.port")));
            p.setPuerto(port);
            return p;
        }).collect(Collectors.toList());
    }

    @GetMapping("/ver/{id}")
    public Producto detalle(@PathVariable Long id) {
        Producto p = productoService.findById(id);
//        p.setPuerto(Integer.parseInt(env.getProperty("local.server.port")));
        p.setPuerto(port);
        return p;
    }
}
