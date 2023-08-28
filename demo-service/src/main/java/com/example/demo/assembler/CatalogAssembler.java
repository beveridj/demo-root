package com.example.demo.assembler;

import com.example.demo.api.CartDto;
import com.example.demo.api.CatalogDto;
import com.example.demo.core.data.Cart;
import com.example.demo.core.data.Catalog;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CatalogAssembler {

    public CatalogDto assemble(Catalog entity) {
        return new CatalogDto()
                .setId(entity.getId())
                .setName(entity.getName())
                .setDescription(entity.getDescription())
                .setType(entity.getType());
    }

    public Catalog disassemble(CatalogDto dto) {
        Catalog entity = Catalog.newInstance();
        return disassembleInto(dto, entity);
    }

    public Catalog disassembleInto(CatalogDto dto, Catalog entity) {
        return entity
                .setId(dto.getId())
                .setName(dto.getName())
                .setDescription(dto.getDescription())
                .setType(dto.getType());
    }

}
