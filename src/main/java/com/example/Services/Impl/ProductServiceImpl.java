package com.example.Services.Impl;

import com.example.Entity.Product;
import com.example.Repository.ProductRepository;
import com.example.Services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> findRecentProduct(Pageable pageable) {
        return productRepository.findRecentProduct(pageable);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public long count() {
        return productRepository.count();
    }

    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> findByProductnameContaining(String name, Pageable pageable) {
        return productRepository.findByProductnameContaining(name,pageable);
    }

    @Override
    public Page<Product> findByCategoryCateidAndProductnameContaining(Integer categoryId, String name, Pageable pageable){
        return productRepository.findByCategoryCateidAndProductnameContaining(categoryId,name,pageable);
    }

    @Override
    public Page<Product> findByCategoryCateid(Integer categoryId, Pageable pageable) {
        return productRepository.findByCategoryCateid(categoryId,pageable);
    }

    @Override
    public Optional<Product> findById(Integer integer) {
        return productRepository.findById(integer);
    }
    @Override
    public List<Product> findAllByCategoryCateid(Integer categoryId){
        return productRepository.findAllByCategoryCateid(categoryId);
    }
    @Override
    public <S extends Product> S save(S entity) {
        if(entity.getProductid() != 0)
        {
            Optional<Product> opt = findById(entity.getProductid());
            if(opt.isPresent()){
                if(StringUtils.isEmpty(entity.getImage())){
                    entity.setImage(opt.get().getImage());
                }else {
                    entity.setImage(entity.getImage());
                }
            }
            return productRepository.save(entity);
        }else{
            return productRepository.save(entity);
        }
    }
    @Override
    public void deleteById(Integer integer) {
        productRepository.deleteById(integer);
    }
}
