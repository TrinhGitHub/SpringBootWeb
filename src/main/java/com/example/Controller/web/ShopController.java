package com.example.Controller.web;

import com.example.Entity.*;
import com.example.Model.*;
import com.example.Services.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequestMapping("/shop")
@Controller
public class ShopController {
    @Autowired
    IProductService productService;

    @Autowired
    ICategoryService categoryService;
    @Autowired
    ICartService cartService;
    @Autowired
    ICartItemService cartItemService;
    @Autowired
    IUserService userService;

    @ModelAttribute("categories")
    public List<CategoryModel> getcategories(){
        return categoryService.findAll().stream().map(item->{
            CategoryModel cate = new CategoryModel();
            BeanUtils.copyProperties(item,cate);
            return cate;
        }).toList();
    }

    @RequestMapping("products")
    public String searchPage(ModelMap modelMap, @RequestParam(name = "name",required = false)String name,
                         @RequestParam("page") Optional<Integer> page,
                         @RequestParam("size") Optional<Integer> size
    ){
        int count = (int) productService.count();
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(3);
        Pageable pageable = PageRequest.of(currentPage-1,pageSize, Sort.by("productid"));
        Page<Product> resulPage = null;
        // name là tên của titol của video entity
        //kiểm tra name có dự liệu ko
        if(StringUtils.hasText(name)){
            resulPage = productService.findByProductnameContaining(name,pageable);
            modelMap.addAttribute("name",name);
        }else{
            resulPage = productService.findAll(pageable);
        }
        int totalPages = resulPage.getTotalPages();
        if(totalPages> 0){
            int start = Math.max(1,currentPage -2);
            int end = Math.min(currentPage+2,totalPages);
            if(totalPages > count){
                if(end == totalPages) start = end - count;
                else if (start == 1 ) end = start = count;
            }
            List<Integer> Pagenumbers = IntStream.rangeClosed(start,end)
                    .boxed()
                    .collect(Collectors.toList());
            modelMap.addAttribute("pageNumbers",Pagenumbers);
        }
        modelMap.addAttribute("productPage",resulPage);
        return "web/shop";
    }

    @RequestMapping("productsWithCate")
    public String searchPageCate(ModelMap modelMap, @RequestParam(name = "name",required = false)String name,
                             @RequestParam("page") Optional<Integer> page,
                             @RequestParam("size") Optional<Integer> size,
                             @RequestParam("cateid") Integer cateid
    ){
        int count = (int) productService.count();
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(3);
        Pageable pageable = PageRequest.of(currentPage-1,pageSize, Sort.by("productid"));
        Page<Product> resulPage = null;
        // name là tên của titol của video entity
        //kiểm tra name có dự liệu ko
        if(StringUtils.hasText(name)){
            resulPage = productService.findByProductnameContaining(name,pageable);
            modelMap.addAttribute("name",name);
            if(cateid != null){
                resulPage = productService.findByCategoryCateidAndProductnameContaining(cateid,name,pageable);
            }
        }else{
            if(cateid != null){
                resulPage = productService.findByCategoryCateid(cateid,pageable);
            }else {
                resulPage = productService.findAll(pageable);
            }
        }
        int totalPages = resulPage.getTotalPages();
        if(totalPages> 0){
            int start = Math.max(1,currentPage -2);
            int end = Math.min(currentPage+2,totalPages);
            if(totalPages > count){
                if(end == totalPages) start = end - count;
                else if (start == 1 ) end = start = count;
            }
            List<Integer> Pagenumbers = IntStream.rangeClosed(start,end)
                    .boxed()
                    .collect(Collectors.toList());
            modelMap.addAttribute("pageNumbers",Pagenumbers);
        }
        modelMap.addAttribute("productPage",resulPage);
        modelMap.addAttribute("cateActive",cateid);
        return "web/shop";
    }
    @RequestMapping("detailProduct")
    public String ProductDetail(ModelMap modelMap, @RequestParam("productid") Integer proid){
        ProductModel productModel = new ProductModel();
        Optional<Product> entity = productService.findById(proid);
        Category categoryentity = categoryService.findById(entity.get().getCategory().getCateid()).get();
        CategoryModel categoryModel = new CategoryModel();
        BeanUtils.copyProperties(categoryentity,categoryModel);
        BeanUtils.copyProperties(entity.get(),productModel);
        productModel.setCategoryModel(categoryModel);
        modelMap.addAttribute("productdetail",productModel);
        return "web/shopsingle";
    }

}
