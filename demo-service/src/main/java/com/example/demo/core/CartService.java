package com.example.demo.core;

import com.example.demo.api.CartDto;
import com.example.demo.assembler.CartAssembler;
import com.example.demo.core.data.Cart;
import com.example.demo.core.data.CartRepository;
import com.example.demo.core.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {

    private final CartRepository repository;
    private final CartAssembler assembler;

    public CartService(CartRepository aRepository, CartAssembler anAssembler) {
        repository = aRepository;
        assembler = anAssembler;
    }

    public List<CartDto> findAll(){
        List<Cart> carts = repository.findAll();
        return carts.stream()
                .map(assembler::assemble)
                .collect(Collectors.toList());
    }

    public CartDto findById(Long anId){
        Optional<Cart> order = repository.findById(anId);
        return order
                .map(assembler::assemble)
                .orElseThrow(NotFoundException::new);
    }

    public CartDto create(CartDto anOrder){
        Cart order = assembler.disassemble(anOrder);
        order = repository.save(order);
        return assembler.assemble(order);
    }

    public void delete(Long anId){
        if(! repository.existsById(anId)){
            throw new NotFoundException();
        };
        repository.deleteById(anId);
    }

}
