package com.example.demo.core;

import com.example.demo.api.CartDto;
import com.example.demo.assembler.CartAssembler;
import com.example.demo.assembler.CartItemAssembler;
import com.example.demo.core.data.Cart;
import com.example.demo.core.data.CartItem;
import com.example.demo.core.data.CartRepository;
import com.example.demo.core.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository repository;
    private final CartAssembler cartAssembler;

    public List<CartDto> findAll(){
        List<Cart> carts = repository.findAll();
        return carts.stream()
                .map(cartAssembler::assemble)
                .collect(Collectors.toList());
    }

    public CartDto findById(Long anId){
        Optional<Cart> order = repository.findById(anId);
        return order
                .map(cartAssembler::assemble)
                .orElseThrow(NotFoundException::new);
    }

    public CartDto create(CartDto aCart){
        Cart cart = cartAssembler.disassemble(aCart);
        cart = repository.save(cart);
        return cartAssembler.assemble(cart);
    }

    public void delete(Long anId){
        if(! repository.existsById(anId)){
            throw new NotFoundException();
        };
        repository.deleteById(anId);
    }

    public CartDto update(Long anId, CartDto aCartDto){
        Cart cart = repository.findById(anId)
                .orElseThrow(() -> new NotFoundException());
        cart.setCustomerId(aCartDto.getCustomerId());
        repository.save(cart);
        return cartAssembler.assemble(cart);
    }

}
