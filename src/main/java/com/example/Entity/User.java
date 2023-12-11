package com.example.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private int userid;
    @Column(name="username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name="address")
    private String address;
    @Column(name = "phone")
    private String phone;
    @Column(name = "name")
    private String name;
    @Column(name = "role")
    private boolean role;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private Set<Payment> payments;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private Set<ShopOrder> shopOrders;
}
