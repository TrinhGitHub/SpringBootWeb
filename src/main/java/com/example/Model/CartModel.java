package com.example.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartModel {
    private int cartid;
    private Long transaction_id_user;
    private Long transaction_id_merchant;
    private int userid;
    private int shoporderid;
}
