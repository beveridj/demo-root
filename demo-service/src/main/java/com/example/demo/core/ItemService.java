package com.example.demo.core;

import com.example.demo.api.ItemDto;
import com.example.demo.assembler.ItemAssembler;
import com.example.demo.core.data.Item;
import com.example.demo.core.data.ItemRepository;
import com.example.demo.core.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private final ItemRepository repository;
    private final ItemAssembler assembler;

    public ItemService(ItemRepository aRepository, ItemAssembler anAssembler) {
        repository = aRepository;
        assembler = anAssembler;
    }

    public List<ItemDto> findAll(){
        List<Item> items = repository.findAll();
        return items.stream()
                .map(assembler::assemble)
                .collect(Collectors.toList());
    }

    public ItemDto findById(Long anId){
        Optional<Item> item = repository.findById(anId);
        return item
                .map(assembler::assemble)
                .orElseThrow(NotFoundException::new);
    }

    public ItemDto create(ItemDto anItem){
        Item item = assembler.disassemble(anItem);
        item = repository.save(item);
        return assembler.assemble(item);
    }

    public void delete(Long anId){
        if(! repository.existsById(anId)){
            throw new NotFoundException();
        };
        repository.deleteById(anId);
    }
}
