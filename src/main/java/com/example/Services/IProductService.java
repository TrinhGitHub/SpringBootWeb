package com.example.Services;

import com.example.Entity.Category;
import com.example.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> findRecentProduct(Pageable pageable);

    List<Product> findAll();

    long count();
    Page<Product> findAll(Pageable pageable);

    Page<Product> findByProductnameContaining(String name, Pageable pageable);

    Page<Product> findByCategoryCateidAndProductnameContaining(Integer categoryId, String name, Pageable pageable);
    Page<Product> findByCategoryCateid(Integer categoryId, Pageable pageable);

    Optional<Product> findById(Integer integer);

    List<Product> findAllByCategoryCateid(Integer categoryId);
    <S extends Product> S save(S entity);
    void deleteById(Integer integer);
}
