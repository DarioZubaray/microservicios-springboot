package com.github.dariozubaray.springboot.items.models.service;

import java.util.List;
import com.github.dariozubaray.springboot.items.models.Item;

public interface ItemService {

    public List<Item> listar();

    public Item detalle(Long id, Integer cantidad);
}
