package com.example.demo.core;

import com.example.demo.api.CartDto;
import com.example.demo.assembler.CartAssembler;
import com.example.demo.assembler.CustomerAssembler;
import com.example.demo.core.data.Cart;
import com.example.demo.core.data.CartRepository;
import com.example.demo.core.data.Customer;
import com.example.demo.core.data.CustomerRepository;
import com.example.demo.core.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;
    private final CartAssembler cartAssembler;

    public List<CartDto> findAll(){
        List<Cart> carts = cartRepository.findAll();
        return carts.stream()
                .map(cartAssembler::assemble)
                .collect(Collectors.toList());
    }

    public CartDto findById(Long anId){
        Optional<Cart> order = cartRepository.findById(anId);
        return order
                .map(cartAssembler::assemble)
                .orElseThrow(NotFoundException::new);
    }

    public CartDto create(CartDto aCart){
        Cart cart = cartAssembler.disassemble(aCart);
        cart = cartRepository.save(cart);
        return cartAssembler.assemble(cart);
    }

    public void delete(Long anId){
        if(! cartRepository.existsById(anId)){
            throw new NotFoundException();
        };
        cartRepository.deleteById(anId);
    }

    public CartDto update(Long anId, CartDto aCartDto){
        Cart cart = cartRepository.findById(anId)
                .orElseThrow(NotFoundException::new);
        cart.setCustomerId(aCartDto.getCustomerId());
        Customer customer = customerRepository.findById(aCartDto.getCustomerId())
                .orElseThrow(NotFoundException::new);
        cart.setCustomer(customer);
        cart = cartRepository.save(cart);
        return cartAssembler.assemble(cart);
    }

}
