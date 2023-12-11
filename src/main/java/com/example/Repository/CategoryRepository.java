package com.example.Repository;

import com.example.Entity.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    @Query("SELECT p FROM Category p ORDER BY p.cateid DESC")
    List<Category> findRecentCategory(Pageable pageable);
}
