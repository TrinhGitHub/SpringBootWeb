package com.example.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart_ItemModel {
    private int cartitemid;
    private int quantity;
    private int productid;
    private int cartid;
    private String productname;
    private String image;
}
