package com.example.demo.api;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class CartDto {
    private Long id;
    private Long customerId;
    private CustomerDto customer;
    private List<CartItemDto> cartItems;

    public static CartDto newInstance() {
        CartDto order = new CartDto();
        order.setId(Long.valueOf(0))
                .setCustomerId(Long.valueOf(0))
                .setCustomer(CustomerDto.newInstance())
                .setCartItems(List.of());
        return order;
    }
}
