package com.example.demo.assembler;

import com.example.demo.api.ItemDto;
import com.example.demo.core.data.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItemAssembler {

    public ItemDto assemble(Item entity) {
        return new ItemDto()
                .setId(entity.getId())
                .setName(entity.getName())
                .setDescription(entity.getDescription())
                .setPrice(entity.getPrice());
    }

    public Item disassemble(ItemDto dto) {
        Item entity = Item.newInstance();
        return disassembleInto(dto, entity);
    }

    public Item disassembleInto(ItemDto dto, Item entity) {
        return entity
                .setId(dto.getId())
                .setName(dto.getName())
                .setDescription(dto.getDescription())
                .setPrice(dto.getPrice());
    }
}
