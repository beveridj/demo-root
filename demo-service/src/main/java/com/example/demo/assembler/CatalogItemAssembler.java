package com.example.demo.assembler;

import com.example.demo.api.CatalogItemDto;
import com.example.demo.core.data.CatalogItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CatalogItemAssembler {

    final private ItemAssembler itemAssembler;
    final private CatalogAssembler catalogAssembler;

    public CatalogItemDto assemble(CatalogItem entity) {
        CatalogItemDto dto = new CatalogItemDto();
        dto.setId(entity.getId())
                .setItemId(entity.getItemId())
                .setCatalogId(entity.getCatalogId())
                .setItem(entity.getItem() != null ? itemAssembler.assemble(entity.getItem()) : null);
         return dto;
    }

    public CatalogItem disassemble(CatalogItemDto dto) {
        CatalogItem entity = CatalogItem.newInstance();
        return disassembleInto(dto, entity);
    }

    public CatalogItem disassembleInto(CatalogItemDto dto, CatalogItem entity) {
        entity.setId(dto.getId())
                .setItemId(dto.getItemId())
                .setCatalogId(dto.getCatalogId())
                .setItem(dto.getItem() != null ? itemAssembler.disassemble(dto.getItem()) : null);
        return entity;
    }

}
