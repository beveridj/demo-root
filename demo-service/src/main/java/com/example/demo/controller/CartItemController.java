package com.example.demo.controller;

import com.example.demo.api.CartItemDto;
import com.example.demo.core.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cartitems")
public class CartItemController {

    @Autowired
    CartItemService service;

    @GetMapping("")
    public List<CartItemDto> findAll(){
        return service.findAll();
    }

    @GetMapping("/{cartItemId}")
    public CartItemDto findById(@PathVariable("cartItemId") Long cartItemId){
        return service.findById(cartItemId);
    }

    @PostMapping("")
    public CartItemDto create(@RequestBody CartItemDto anItem){
        return service.create(anItem);
    }

    @DeleteMapping("/{cartItemId}")
    public void delete (@PathVariable ("cartItemId") Long cartItemId){
        service.delete(cartItemId);
    }

    @PutMapping("/{cartItemId}")
    public CartItemDto update(@PathVariable("cartItemId") Long cartItemId, @RequestBody CartItemDto cartItemDto){ return service.update(cartItemId, cartItemDto);}
}
