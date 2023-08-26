package com.example.demo.assembler;

import com.example.demo.api.CustomerDto;
import com.example.demo.api.ItemDto;
import com.example.demo.core.data.Customer;
import com.example.demo.core.data.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerAssembler {

    public CustomerDto assemble(Customer entity) {
        return new CustomerDto()
                .setId(entity.getId())
                .setFirstName(entity.getFirstName())
                .setLastName(entity.getLastName())
                .setAddress(entity.getAddress());
    }

    public Customer disassemble(CustomerDto dto) {
        Customer entity = Customer.newInstance();
        return disassembleInto(dto, entity);
    }

    public Customer disassembleInto(CustomerDto dto, Customer entity) {
        return entity
                .setId(dto.getId())
                .setFirstName(dto.getFirstName())
                .setLastName(dto.getLastName())
                .setAddress(dto.getAddress());
    }
}
