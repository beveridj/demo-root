package com.example.demo.assembler;

import com.example.demo.api.CartDto;
import com.example.demo.core.data.Cart;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CartAssembler {

    private final CustomerAssembler customerAssembler;
    private final CartItemAssembler cartItemAssembler;

    public CartAssembler(CustomerAssembler aCustomerAssembler, CartItemAssembler aCartItemAssembler) {
        customerAssembler = aCustomerAssembler;
        cartItemAssembler = aCartItemAssembler;
    }

    public CartDto assemble(Cart entity) {
        return new CartDto()
                .setId(entity.getId())
                .setCustomerId(entity.getCustomerId())
                .setCustomer(customerAssembler.assemble(entity.getCustomer()))
                .setCartItems(entity.getCartItems().stream()
                             .map(cartItemAssembler::assemble)
                            .collect(Collectors.toList())
                );
    }

    public Cart disassemble(CartDto dto) {
        Cart entity = Cart.newInstance();
        return disassembleInto(dto, entity);
    }

    public Cart disassembleInto(CartDto dto, Cart entity) {
        return entity
                .setId(dto.getId())
                .setCustomerId(dto.getCustomerId())
                .setCustomer(customerAssembler.disassemble(dto.getCustomer()));
    }

}
