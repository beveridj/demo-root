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

    @GetMapping("/{cartId}")
    public CartItemDto findById(@PathVariable("cartId") Long cartId){
        return service.findById(cartId);
    }

    @PostMapping("")
    public CartItemDto create(@RequestBody CartItemDto anItem){
        return service.create(anItem);
    }

    @DeleteMapping("/{cartId}")
    public void delete (@PathVariable ("cartId") Long cartId){
        service.delete(cartId);
    }
}
