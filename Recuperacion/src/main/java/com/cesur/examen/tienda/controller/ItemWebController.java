package com.cesur.examen.tienda.controller;

import com.cesur.examen.tienda.model.Item;
import com.cesur.examen.tienda.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/items")
public class ItemWebController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping
    public String listItems(Model model) {
        model.addAttribute("items", itemRepository.findAll());
        return "listado";
    }

    @GetMapping("/detalle/{id}")
    public String viewDetail(@PathVariable String id, Model model) {
        itemRepository.findById(id).ifPresent(i -> model.addAttribute("item", i));
        return "detalle";
    }

    @GetMapping("/editar/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        System.out.println(id);
        var it = itemRepository.findById(id);
        System.out.println(it);
        it.ifPresent(i -> model.addAttribute("item", i));
        return "editar";
    }


    @PostMapping("/update")
    public String updateItem(@ModelAttribute Item item) {
        itemRepository.save(item);
        return "redirect:/items";
    }

    @PostMapping("/update-quick")
    public String quickUpdate(@RequestParam String id,
                              @RequestParam Double price,
                              @RequestParam Integer count) {
        itemRepository.findById(id).ifPresent(item -> {
            item.setPrice(price);
            item.setCount(count);
            itemRepository.save(item);
        });
        return "redirect:/items";
    }
    @GetMapping("/stats")
    public String showStats(Model model) {
        List<Item> allItems = itemRepository.findAll();


        long total = allItems.size();
        List<Item> lowStock = allItems.stream()
                .filter(i -> i.getCount() != null && i.getCount() < 100)
                .collect(Collectors.toList());
        List<String> manufacturers = allItems.stream()
                .map(Item::getManufacturer)
                .filter(m -> m != null && !m.isEmpty())
                .distinct()
                .collect(Collectors.toList());

        model.addAttribute("total", total);
        model.addAttribute("lowStock", lowStock);
        model.addAttribute("manufacturers", manufacturers);

        return "estadisticas";
    }
}