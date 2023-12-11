package com.example.Services.Impl;

import com.example.Entity.Cart;
import com.example.Repository.CartRepository;
import com.example.Services.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements ICartService{
    @Autowired
    CartRepository cartRepository;

    @Override
    public <S extends Cart> S save(S entity) {
        return cartRepository.save(entity);
    }

    public Cart findByUserUserid(Integer userid){
        return cartRepository.findByUserUserid(userid);
    }
}
