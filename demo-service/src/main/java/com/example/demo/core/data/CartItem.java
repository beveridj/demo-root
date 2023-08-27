package com.example.demo.core.data;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity
@Table(name = "cart_item")
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class CartItem {
    @Id
    @Column(name = "cart_itm_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
    @SequenceGenerator(name = "sequence_generator", sequenceName = "cart_item_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "cart_id")
    private Long cartId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id", referencedColumnName = "cart_id", insertable = false, updatable = false)
    private Cart cart;

    @Column(name = "item_id")
    private Long itemId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id", referencedColumnName = "item_id", insertable = false, updatable = false)
    private Item item;

    @Column(name = "quantity")
    private Integer quantity;

    public static CartItem newInstance(){
        return new CartItem();
    }

}

