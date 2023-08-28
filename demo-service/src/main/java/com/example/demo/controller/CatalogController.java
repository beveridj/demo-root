package com.example.demo.controller;

import com.example.demo.api.CatalogDto;
import com.example.demo.core.CatalogService;
import com.example.demo.core.data.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/catalogs")
public class CatalogController {

    @Autowired
    CatalogService service;

    @GetMapping("")
    public List<CatalogDto> findAll(){
        return service.findAll();
    }

    @GetMapping("/{catalogId}")
    public CatalogDto findById(@PathVariable("catalogId") Long aCatalog){
        return service.findById(aCatalog);
    }

    @PostMapping("")
    public CatalogDto create(@RequestBody CatalogDto aCatalog){
        return service.create(aCatalog);
    }

    @DeleteMapping("/{catalogId}")
    public void delete (@PathVariable ("catalogId") Long catalogId){
        service.delete(catalogId);
    }

}
