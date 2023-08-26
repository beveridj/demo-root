package com.example.demo.api;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class CartItemDto {

    private Long id;
    private Long itemId;
    private ItemDto item;
    private Long cartId;
    private Integer quantity;

    public static CartItemDto newInstance() {
        CartItemDto orderItem = new CartItemDto();
        orderItem.setId(Long.valueOf(0))
                .setItemId(Long.valueOf(0))
                .setItem(ItemDto.newInstance())
                .setCartId(Long.valueOf(0))
                .setQuantity(Integer.valueOf(0));
        return orderItem;
    }
}