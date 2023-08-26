package com.example.demo.api;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class CustomerDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String address;

    public static CustomerDto newInstance() {
        CustomerDto customer = new CustomerDto();
        customer.setId(Long.valueOf(0))
                .setFirstName("")
                .setLastName("")
                .setAddress("");
        return customer;
    }
}