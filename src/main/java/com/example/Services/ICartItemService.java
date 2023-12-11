package com.example.Services;

import com.example.Entity.Cart_item;

import java.util.List;
import java.util.Optional;

public interface ICartItemService {
    <S extends Cart_item> S save(S entity);

    List<Cart_item> saveAllCartItems(List<Cart_item> cartItems);

    List<Cart_item> findAllByCartCartid(int cartid);

    void deleteById(Integer integer);

    Optional<Cart_item> findById(Integer integer);

    List<Cart_item> findAllByProductProductid(int proid);
}
