package com.example.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shop_order")
public class ShopOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shoporderid")
    private int shoporderid;

    @Column(name = "dateshoporder")
    private Date dateshoporder;

    @Column(name = "totalprice")
    private int totalprice;

    @Column(name = "status")
    private boolean status;

    @Column(name = "addressship")
    private String addressship;

    @OneToMany(mappedBy = "shopOrder", cascade = CascadeType.ALL)
    private Set<Cart> carts;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    @ManyToOne
    @JoinColumn(name = "shippingid")
    private Shipping shipping;

    // getters and setters
}
