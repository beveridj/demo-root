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
                .setCartId(entity.getCartId());
        if(entity.getItem() != null)
            dto.setItem(itemAssembler.assemble(entity.getItem()));
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
                .setQuantity(dto.getQuantity());
//    TODO on create, DTO(JSON) will not include an Item. How can an Optional be incorporated?
        if(dto.getItem() != null)
            entity.setItem(itemAssembler.disassemble(dto.getItem()));
         return entity;

    }
}
