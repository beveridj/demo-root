package com.example.demo.controller;

import com.example.demo.api.CustomerDto;
import com.example.demo.api.ItemDto;
import com.example.demo.core.CustomerService;
import com.example.demo.core.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    CustomerService service;

    @GetMapping("")
    public List<CustomerDto> findAll(){
        return service.findAll();
    }

    @GetMapping("/{customerId}")
    public CustomerDto findById(@PathVariable("customerId") Long customerId){
        return service.findById(customerId);
    }

    @PostMapping("")
    public CustomerDto create(@RequestBody CustomerDto aCustomer){
        return service.create(aCustomer);
    }

    @DeleteMapping("/{customerId}")
    public void delete (@PathVariable ("customerId") Long customerId){
        service.delete(customerId);
    }
}
