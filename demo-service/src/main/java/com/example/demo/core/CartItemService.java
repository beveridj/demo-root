package com.example.demo.core;

import com.example.demo.api.CartItemDto;
import com.example.demo.assembler.CartItemAssembler;
import com.example.demo.core.data.CartItem;
import com.example.demo.core.data.CartItemRepository;
import com.example.demo.core.data.CartRepository;
import com.example.demo.core.data.ItemRepository;
import com.example.demo.core.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemRepository repository;
    private final CartItemAssembler assembler;
    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;

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

    public CartItemDto create(CartItemDto aCartItem){
        if(! itemRepository.existsById(aCartItem.getItemId()))
            throw new NotFoundException();
        if(! cartRepository.existsById(aCartItem.getCartId()))
            throw new NotFoundException();

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

    public CartItemDto update(Long aCartItemId, CartItemDto aCartItemDto) {
        if(! repository.existsById(aCartItemId)){
            throw new NotFoundException();
        };
        CartItem cartItem = assembler.disassemble(aCartItemDto);
         return assembler.assemble(repository.save(cartItem));
    }

}
