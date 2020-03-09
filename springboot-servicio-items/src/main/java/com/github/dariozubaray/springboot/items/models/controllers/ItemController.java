package com.github.dariozubaray.springboot.items.models.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.github.dariozubaray.springboot.items.models.Item;
import com.github.dariozubaray.springboot.items.models.service.ItemService;

@RestController
public class ItemController {

    @Autowired
    @Qualifier("ItemServiceFeign")
    private ItemService itemService;

    @GetMapping("/listar")
    public List<Item> listar() {
        return itemService.listar();
    }

    @GetMapping("/ver/{id}/cantidad/{cantidad}")
    public Item detalle(@PathVariable Long id, @PathVariable Integer cantidad) {
        return itemService.detalle(id, cantidad);
    }
}
