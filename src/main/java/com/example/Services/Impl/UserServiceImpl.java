package com.example.Services.Impl;

import com.example.Entity.User;
import com.example.Repository.UserRepository;
import com.example.Services.IUserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public <S extends User> S save(S entity) {
        entity.setPassword(BCrypt.hashpw(entity.getPassword(),BCrypt.gensalt(12)));
        return userRepository.save(entity);
    }
    @Override
    public Optional<User> findUserByUsername(String username) {return userRepository.findByUsername(username);}
    @Override
    public Optional<User> findById(Integer integer) {
        return userRepository.findById(integer);
    }

    @Override
    public void updateUserDetails(int userId, String email, String address, String phone, String name) {
         userRepository.updateUserDetails(userId,email,address,phone,name);
    }
}
