package com.example.Services.Impl;

import com.example.Entity.Category;
import com.example.Repository.CategoryRepository;
import com.example.Services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
    @Override
    public List<Category> findRecentCategory(Pageable pageable){
        return categoryRepository.findRecentCategory(pageable);
    }
    @Override
    public Optional<Category> findById(Integer integer) {
        return categoryRepository.findById(integer);
    }

    public <S extends Category> S save(S entity) {
        return categoryRepository.save(entity);
    }

    public void deleteById(Integer integer) {
        categoryRepository.deleteById(integer);
    }
}
