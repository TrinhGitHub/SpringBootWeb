package com.example.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "payment")
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paymentid")
    private int paymentid;

    @Column(name="paymentdate")
    private Date paymentdate;

    @Column(name="methodpayment")
    private String methodpayment;

    @ManyToOne
    @JoinColumn(name = "cartid")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;
}
