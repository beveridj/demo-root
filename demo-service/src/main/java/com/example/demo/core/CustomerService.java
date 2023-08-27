package com.example.demo.core;

import com.example.demo.api.CustomerDto;
import com.example.demo.api.ItemDto;
import com.example.demo.assembler.CustomerAssembler;
import com.example.demo.assembler.ItemAssembler;
import com.example.demo.core.data.Customer;
import com.example.demo.core.data.CustomerRepository;
import com.example.demo.core.data.Item;
import com.example.demo.core.data.ItemRepository;
import com.example.demo.core.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerAssembler assembler;

    public List<CustomerDto> findAll(){
        List<Customer> customers = repository.findAll();
        return customers.stream()
                .map(assembler::assemble)
                .collect(Collectors.toList());
    }

    public CustomerDto findById(Long anId){
        Optional<Customer> Customer = repository.findById(anId);
        return Customer
                .map(assembler::assemble)
                .orElseThrow(NotFoundException::new);
    }

    public CustomerDto create(CustomerDto anCustomer){
        Customer Customer = assembler.disassemble(anCustomer);
        Customer = repository.save(Customer);
        return assembler.assemble(Customer);
    }

    public void delete(Long anId){
        if(! repository.existsById(anId)){
            throw new NotFoundException();
        };
        repository.deleteById(anId);
    }
}
