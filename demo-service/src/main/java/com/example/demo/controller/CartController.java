package com.example.demo.controller;

import com.example.demo.api.CartDto;
import com.example.demo.core.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carts")
public class CartController {

    @Autowired
    CartService service;

    @GetMapping("")
    public List<CartDto> findAll(){
        return service.findAll();
    }

    @GetMapping("/{cartId}")
    public CartDto findById(@PathVariable("cartId") Long cartId){
        return service.findById(cartId);
    }

    @PostMapping("")
    public CartDto create(@RequestBody CartDto aCart){
        return service.create(aCart);
    }

    @DeleteMapping("/{cartId}")
    public void delete (@PathVariable ("cartId") Long cartId){
        service.delete(cartId);
    }
}
