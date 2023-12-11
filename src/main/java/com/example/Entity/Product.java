package com.example.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productid")
    private int productid;

    @Column(name="productname")
    private String productname;

    @Column(name="description")
    private String description;

    @Column(name="price")
    private int price;

    @Column(name="image")
    private String image;
    @Column(name="quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "categoryid")
    private Category category;
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private Set<Cart_item> cartitems;


}
