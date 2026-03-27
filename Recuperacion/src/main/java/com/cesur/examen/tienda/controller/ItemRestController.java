package com.cesur.examen.tienda.controller;

import com.cesur.examen.tienda.model.Item;
import com.cesur.examen.tienda.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ItemRestController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/")
    public List<Item> getAllItem() {
        return itemRepository.findAll();
    }

    @PostMapping("/")
    public Item addItem(@RequestBody Item item) {
        return itemRepository.save(item);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable String id) {
        itemRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    public Item getItem(@PathVariable String id) {
        return itemRepository.findById(id).orElse(null);
    }
}