package com.example.demo.api;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import static com.example.demo.api.CatalogType.NONE;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class CatalogDto {
    private Long id;
    private String name;
    private String description;
    private CatalogType type;

    public static CatalogDto newInstance() {
        CatalogDto order = new CatalogDto();
        order.setId(Long.valueOf(0))
                .setName("")
                .setDescription("")
                .setType(NONE);
        return order;
    }

}
