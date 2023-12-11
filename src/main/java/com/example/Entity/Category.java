package com.example.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cateid")
    private int cateid;

    @Column(name="catename")
    private String catename;

    @Column(name="image")
    private String image;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private Set<Product> products;
}
