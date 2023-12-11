package com.example.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "shipping")
@NoArgsConstructor
@AllArgsConstructor
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shippingid")
    private int shipingid;

    @Column(name="shippingname")
    private String shipingname;

    @Column(name="price")
    private int price;
    @OneToMany(mappedBy = "shipping",cascade = CascadeType.ALL)
    private Set<ShopOrder> shopOrders;
}
