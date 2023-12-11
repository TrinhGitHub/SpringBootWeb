package com.example.Repository;

import com.example.Entity.Cart;
import com.example.Entity.Cart_item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartItemRepository extends JpaRepository<Cart_item,Integer> {
    List<Cart_item> findAllByCartCartid(int cartid);

    List<Cart_item> findAllByProductProductid(int proid);
}
