package com.example.Services;

import com.example.Entity.Cart;
import org.springframework.data.domain.Page;

public interface ICartService {

    <S extends Cart> S save(S entity);

    Cart findByUserUserid(Integer userid);
}
