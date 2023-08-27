package com.example.demo.core;

import com.example.demo.api.CartItemDto;
import com.example.demo.assembler.CartItemAssembler;
import com.example.demo.core.data.CartItem;
import com.example.demo.core.data.CartItemRepository;
import com.example.demo.core.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartItemService {

    private final CartItemRepository repository;
    private final CartItemAssembler assembler;

    public CartItemService(CartItemRepository aRepository, CartItemAssembler anAssembler) {
        repository = aRepository;
        assembler = anAssembler;
    }

    public List<CartItemDto> findAll(){
        List<CartItem> items = repository.findAll();
        return items.stream()
                .map(assembler::assemble)
                .collect(Collectors.toList());
    }

    public CartItemDto findById(Long anId){
        Optional<CartItem> item = repository.findById(anId);
        return item
                .map(assembler::assemble)
                .orElseThrow(NotFoundException::new);
    }

//    TODO should cart item be an Optional because Item will be null on creation
    public CartItemDto create(CartItemDto aCartItem){
        CartItem cartItem = assembler.disassemble(aCartItem);
        cartItem = repository.save(cartItem);
        return assembler.assemble(cartItem);
    }

    public void delete(Long anId){
        if(! repository.existsById(anId)){
            throw new NotFoundException();
        };
        repository.deleteById(anId);
    }
}
