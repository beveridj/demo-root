package com.example.demo.core.data;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity
@Table(name = "catalog_item")
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class CatalogItem {
    @Id
    @Column(name = "catalog_itm_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
    @SequenceGenerator(name = "sequence_generator", sequenceName = "catalog_item_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "catalog_id")
    private Long catalogId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "catalog_id", referencedColumnName = "catalog_id", insertable = false, updatable = false)
    private Catalog catalog;

    @Column(name = "item_id")
    private Long itemId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id", referencedColumnName = "item_id", insertable = false, updatable = false)
    private Item item;

    public static CatalogItem newInstance(){
        return new CatalogItem();
    }

}
