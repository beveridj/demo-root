package com.example.demo.controller;

import com.example.demo.api.CartItemDto;
import com.example.demo.api.CatalogItemDto;
import com.example.demo.core.CartItemService;
import com.example.demo.core.CatalogItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/catalogitems")
public class CatalogItemController {

    @Autowired
    CatalogItemService service;

    @GetMapping("")
    public List<CatalogItemDto> findAll(){
        return service.findAll();
    }

    @GetMapping("/{catalogItemId}")
    public CatalogItemDto findById(@PathVariable("catalogItemId") Long aCatalogItemId){
        return service.findById(aCatalogItemId);
    }

    @PostMapping("")
    public CatalogItemDto create(@RequestBody CatalogItemDto aCatalogItemId){
        return service.create(aCatalogItemId);
    }

    @DeleteMapping("/{catalogItemId}")
    public void delete (@PathVariable ("catalogItemId") Long catalogItemId){
        service.delete(catalogItemId);
    }

    @PutMapping("/{catalogItemId}")
    public CatalogItemDto update(@PathVariable("catalogItemId") Long catalogItemId, @RequestBody CatalogItemDto aCatalogItemDto){
        return service.update(catalogItemId, aCatalogItemDto);
    }

}
