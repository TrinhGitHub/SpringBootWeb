package com.example.Services;

import com.example.Entity.Category;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    List<Category> findAll();
    List<Category> findRecentCategory(Pageable pageable);
    Optional<Category> findById(Integer integer);

    <S extends Category> S save(S entity);
    void deleteById(Integer integer);
}
