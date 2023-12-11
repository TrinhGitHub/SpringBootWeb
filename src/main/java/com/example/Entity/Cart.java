package com.example.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartid")
    private int cartid;

    @Column(name = "transaction_id_user")
    private Long transaction_id_user;

    @Column(name = "transaction_id_merchant")
    private Long transaction_id_merchant;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private Set<Payment> payments;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private Set<Cart_item> cartitems;

    @ManyToOne
    @JoinColumn(name = "shoporderid")
    private ShopOrder shopOrder;

    // getters and setters
}
