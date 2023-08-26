package com.example.demo.core.data;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Entity
@Table(name = "cart")
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
    @SequenceGenerator(name = "sequence_generator", sequenceName = "cart_id_seq", initialValue = 1, allocationSize = 1)
    @Column(name = "cart_id")
    private Long id;

    @OneToMany
    @JoinColumn(name = "cart_id")
    private List<CartItem> cartItems;

    @Column(name = "customer_id")
    private Long customerId;

    @OneToOne
    @JoinColumn(name = "customer_id", insertable=false, updatable=false)
    private Customer customer;

    public static Cart newInstance(){
        return new Cart();
    }

}

