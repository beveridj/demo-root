package com.example.demo.core;

import com.example.demo.api.CatalogItemDto;
import com.example.demo.assembler.CatalogItemAssembler;
import com.example.demo.core.data.CatalogItem;
import com.example.demo.core.data.CatalogItemRepository;
import com.example.demo.core.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CatalogItemService {

    private final CatalogItemRepository repository;
    private final CatalogItemAssembler assembler;

    public List<CatalogItemDto> findAll(){
        List<CatalogItem> items = repository.findAll();
        return items.stream()
                .map(assembler::assemble)
                .collect(Collectors.toList());
    }

    public CatalogItemDto findById(Long anId){
        Optional<CatalogItem> item = repository.findById(anId);
        return item
                .map(assembler::assemble)
                .orElseThrow(NotFoundException::new);
    }

    public CatalogItemDto create(CatalogItemDto aCatalogItem){
        CatalogItem catalogItem = assembler.disassemble(aCatalogItem);
        catalogItem = repository.save(catalogItem);
        return assembler.assemble(catalogItem);
    }

    public void delete(Long anId){
        if(! repository.existsById(anId)){
            throw new NotFoundException();
        };
        repository.deleteById(anId);
    }

}
