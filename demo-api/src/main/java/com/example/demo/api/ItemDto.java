package com.example.demo.api;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class ItemDto {

    private Long id;
    private String name;
    private String description;
    private Double price;

    public static ItemDto newInstance() {
        ItemDto item = new ItemDto();
        item.setId(Long.valueOf(0))
                .setName("")
                .setDescription("")
                .setPrice(Double.valueOf(0.0d));
        return item;
    }
}