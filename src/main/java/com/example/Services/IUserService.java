package com.example.Services;

import com.example.Entity.User;

import java.util.Optional;

public interface IUserService {
    <S extends User> S save(S entity);

    Optional<User> findUserByUsername(String username);
    Optional<User> findById(Integer integer);

    void updateUserDetails(int userId, String email, String address, String phone, String name);
}
