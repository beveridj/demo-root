package com.example.demo.controller;

import com.example.demo.api.ItemDto;
import com.example.demo.core.ItemService;
import com.example.demo.core.data.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
public class ItemController {

    @Autowired
    ItemService service;

    @GetMapping("")
    public List<ItemDto> findAll(){
        return service.findAll();
    }

    @GetMapping("/{itemId}")
    public ItemDto findById(@PathVariable("itemId") Long itemId){
        return service.findById(itemId);
    }

    @PostMapping("")
    public ItemDto create(@RequestBody ItemDto anItem){
        return service.create(anItem);
    }

    @DeleteMapping("/{itemId}")
    public void delete (@PathVariable ("itemId") Long itemId){
        service.delete(itemId);
    }

    @PutMapping("/{itemId}")
    public ItemDto update(@PathVariable("itemId") Long itemId, @RequestBody ItemDto anItemDto){
        return service.update(itemId, anItemDto);
    }

}
