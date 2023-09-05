package com.example.demo.core.data;

import com.example.demo.core.ItemService;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import com.example.demo.api.CatalogType;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "catalog")
@Data
@Accessors(chain = true)
public class Catalog {

    public Catalog(){
        type = CatalogType.NONE;
    }

    @Id
    @Column(name = "catalog_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
    @SequenceGenerator(name = "sequence_generator", sequenceName = "catalog_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private CatalogType type;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "catalog_id", referencedColumnName = "catalog_id", insertable = false, updatable = false)
    private List<CatalogItem> items = new ArrayList<>();

    public static Catalog newInstance(){
        return new Catalog();
    }

}
