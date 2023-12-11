package com.example.Controller.admin;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.Entity.Cart_item;
import com.example.Entity.Category;
import com.example.Entity.Product;
import com.example.Model.CategoryModel;
import com.example.Model.ProductModel;
import com.example.Model.UserModel;
import com.example.Services.*;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class CategoryAdminController {
    @Autowired
    ICategoryService categoryService;
    @Autowired
    IUserService userService;
    @Autowired
    IProductService productService;
    @Autowired
    IStorageService iStorageService;
    @Autowired
    Cloudinary cloudinary;
    @Autowired
    ICartItemService cartItemService;
    @RequestMapping("categories")
    public String categories(ModelMap modelMap, HttpSession session){
        if(session.getAttribute("admin")==null){
            return "/admin/login";
        }
        UserModel admin = (UserModel) session.getAttribute("admin");
        modelMap.addAttribute("admin",admin);

        List<Category> listcategory = categoryService.findAll();
        modelMap.addAttribute("listcategory",listcategory);
        modelMap.addAttribute("active","categories");
        return "/admin/Categories/Categoryadmin";
    }

    @RequestMapping("/category/add")
    public String add(ModelMap model) {
        return "/admin/Categories/categorydetail";
    }
    @PostMapping("category/saveofupdate")
    public ModelAndView saveOrupdate(ModelMap map, @Valid @ModelAttribute("category") CategoryModel categoryModel, BindingResult result){
        Category entity = new Category();
        BeanUtils.copyProperties(categoryModel,entity);
        if(!categoryModel.getImageFile().isEmpty()){
            if(categoryModel.getIsSource() == false){
                UUID uuid = UUID.randomUUID();
                String uuString = uuid.toString();
                // lưu file vào poster
                entity.setImage(iStorageService.getStorageFilename(categoryModel.getImageFile(),uuString));
                iStorageService.store(categoryModel.getImageFile(),entity.getImage());
                categoryModel.setIsSource(false);
            }else{
                // lưu file vao cloudinary
                try {
                    Map r = this.cloudinary.uploader().upload(categoryModel.getImageFile().getBytes(),
                            ObjectUtils.asMap("resource_type","auto"));
                    String img = (String) r.get("secure_url");
                    entity.setImage(img);
                    categoryModel.setIsSource(true);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }
        categoryService.save(entity);
        return new ModelAndView("forward:/admin/categories",map);
    }
    @RequestMapping("/category/edit")
    public String edit(ModelMap model,@RequestParam(name = "cateid",required = false) Integer cateid) {
        System.out.println(cateid);
        Category entity = new Category();
        CategoryModel categoryModel = new CategoryModel();
        entity = categoryService.findById(cateid).get();
        BeanUtils.copyProperties(entity,categoryModel);

        if(entity.getImage() !=null){
           if(("http").equals(entity.getImage().substring(0,4))){
               categoryModel.setIsSource(true);
           }else {
               categoryModel.setIsSource(false);
           }
       }
        model.addAttribute("category",categoryModel);
        return "/admin/Categories/categorydetail";
    }

    @RequestMapping("/category/delete")
    public ModelAndView delete(ModelMap model,@RequestParam("cateid") Integer cateid) {
        List<Product> list = productService.findAllByCategoryCateid(cateid);
        if(list.size() != 0){
            model.addAttribute("REF_category_product",true);
            return new ModelAndView("forward:/admin/categories");
        }
        categoryService.deleteById(cateid);
        return new ModelAndView("redirect:/admin/categories");
    }

}
