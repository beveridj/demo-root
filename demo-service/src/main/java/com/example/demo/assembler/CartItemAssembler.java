package com.example.demo.assembler;

import com.example.demo.api.CartItemDto;
import com.example.demo.core.data.Cart;
import com.example.demo.core.data.CartItem;
import com.example.demo.core.data.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartItemAssembler {

    final private ItemAssembler itemAssembler;

    public CartItemDto assemble(CartItem entity) {
        CartItemDto dto = new CartItemDto();
        dto.setId(entity.getId())
                .setQuantity(entity.getQuantity())
                .setItemId(entity.getItemId())
                .setCartId(entity.getCartId())
                .setItem(entity.getItem() != null ? itemAssembler.assemble(entity.getItem()) : null);
        return dto;
    }

    public CartItem disassemble(CartItemDto dto) {
        CartItem entity = CartItem.newInstance();
        return disassembleInto(dto, entity);
    }

    public CartItem disassembleInto(CartItemDto dto, CartItem entity) {
        entity.setId(dto.getId())
                .setItemId(dto.getItemId())
                .setCartId(dto.getCartId())
                .setQuantity(dto.getQuantity())
                .setItem(dto.getItem() != null ? itemAssembler.disassemble(dto.getItem()) : null);
          return entity;

    }
}
