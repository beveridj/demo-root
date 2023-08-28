package com.example.demo.core;

import com.example.demo.api.CatalogDto;
import com.example.demo.assembler.CatalogAssembler;
import com.example.demo.core.data.Catalog;
import com.example.demo.core.data.CatalogRepository;
import com.example.demo.core.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public  class CatalogService {

    private final CatalogRepository repository;
    private final CatalogAssembler assembler;

    public List<CatalogDto> findAll(){
        List<Catalog> catalogs = repository.findAll();
        return catalogs.stream()
                .map(assembler::assemble)
                .collect(Collectors.toList());
    }

    public CatalogDto findById(Long anId){
        Optional<Catalog> order = repository.findById(anId);
        return order
                .map(assembler::assemble)
                .orElseThrow(NotFoundException::new);
    }

    public CatalogDto create(CatalogDto anOrder){
        Catalog cata = assembler.disassemble(anOrder);
        cata = repository.save(cata);
        return assembler.assemble(cata);
    }

    public void delete(Long anId){
        if(! repository.existsById(anId)){
            throw new NotFoundException();
        };
        repository.deleteById(anId);
    }

}
