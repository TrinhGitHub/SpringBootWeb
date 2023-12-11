package com.example.Repository;

import com.example.Entity.Category;
import com.example.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    @Query("SELECT p FROM Product p ORDER BY p.productid DESC")
    List<Product> findRecentProduct(Pageable pageable);

    Page<Product> findByProductnameContaining(String name, Pageable pageable);

    Page<Product> findByCategoryCateidAndProductnameContaining(Integer categoryId, String name, Pageable pageable);
    Page<Product> findByCategoryCateid(Integer categoryId, Pageable pageable);
    List<Product> findAllByCategoryCateid(Integer categoryId);

}
