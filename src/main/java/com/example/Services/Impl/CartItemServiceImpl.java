package com.example.Services.Impl;

import com.example.Entity.Cart_item;
import com.example.Repository.CartItemRepository;
import com.example.Services.ICartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements ICartItemService {
    @Autowired
    CartItemRepository cartItemRepository;

    @Override
    public <S extends Cart_item> S save(S entity) {
        return cartItemRepository.save(entity);
    }

    public List<Cart_item> saveAllCartItems(List<Cart_item> cartItems) {
        // Sử dụng phương thức saveAll của repository để lưu danh sách cart_items
        return cartItemRepository.saveAll(cartItems);
    }
    @Override
    public List<Cart_item> findAllByCartCartid(int cartid){
        return cartItemRepository.findAllByCartCartid(cartid);
    }
    @Override
    public void deleteById(Integer integer) {
        cartItemRepository.deleteById(integer);
    }

    public Optional<Cart_item> findById(Integer integer) {
        return cartItemRepository.findById(integer);
    }
    @Override
    public List<Cart_item> findAllByProductProductid(int proid){
        return cartItemRepository.findAllByProductProductid(proid);
    }
}
