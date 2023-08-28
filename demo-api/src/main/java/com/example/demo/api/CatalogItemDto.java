package com.example.demo.api;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class CatalogItemDto {

    private Long id;
    private Long itemId;
    private ItemDto item;
    private Long catalogId;
    private CatalogDto catalog;

    public static CatalogItemDto newInstance() {
        CatalogItemDto catalogItem = new CatalogItemDto();
        catalogItem.setId(Long.valueOf(0))
                .setItemId(Long.valueOf(0))
                .setItem(ItemDto.newInstance())
                .setCatalogId(Long.valueOf(0))
                .setCatalog(CatalogDto.newInstance());
        return catalogItem;
    }

}