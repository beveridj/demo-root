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
                .setCatalogId(entity.getCatalogId());
 // TODO this smells. Should be able to use Optional functionally somehow
        if(entity.getItem() != null)
            dto.setItem(itemAssembler.assemble(entity.getItem()));
        if(entity.getCatalog() != null)
            dto.setCatalog(catalogAssembler.assemble(entity.getCatalog()));
        return dto;
    }

    public CatalogItem disassemble(CatalogItemDto dto) {
        CatalogItem entity = CatalogItem.newInstance();
        return disassembleInto(dto, entity);
    }

    public CatalogItem disassembleInto(CatalogItemDto dto, CatalogItem entity) {
        entity.setId(dto.getId())
                .setItemId(dto.getItemId())
                .setCatalogId(dto.getCatalogId());
//    TODO on create, DTO(JSON) will not include an Item or a Catalog. How can an Optional be incorporated?
        if(dto.getItem() != null)
            entity.setItem(itemAssembler.disassemble(dto.getItem()));
        if(dto.getCatalog() != null)
            entity.setCatalog(catalogAssembler.disassemble(dto.getCatalog()));
        return entity;
    }

}
