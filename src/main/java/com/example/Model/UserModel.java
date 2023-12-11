package com.example.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    private int userid;
    private String username;
    private String password;
    private String email;
    private String address;
    private String phone;
    private String name;
    private boolean role;

}
