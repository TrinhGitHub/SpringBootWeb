package com.example.Controller.admin;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.Entity.Cart_item;
import com.example.Entity.Category;
import com.example.Entity.Product;
import com.example.Entity.User;
import com.example.Model.CategoryModel;
import com.example.Model.ProductModel;
import com.example.Model.UserModel;
import com.example.Services.*;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@RequestMapping("/admin")
@Controller
public class ProductAdminController {
    @Autowired
    IUserService userService;
    @Autowired
    IProductService productService;
    @Autowired
    ICategoryService categoryService;
    @Autowired
    IStorageService iStorageService;
    @Autowired
    Cloudinary cloudinary;
    @Autowired
    ICartItemService cartItemService;
    //lấy hình ảnh
    @GetMapping("/images/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serverFile(@PathVariable String filename){
        Resource file = iStorageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\""+file.getFilename()+"\"").body(file);
    }
    @ModelAttribute("categories")
    public List<CategoryModel> getcategories(){
        return categoryService.findAll().stream().map(item->{
            CategoryModel cate = new CategoryModel();
            BeanUtils.copyProperties(item,cate);
            return cate;
        }).toList();
    }

    @RequestMapping("productManager")
    public ModelAndView productManager(ModelMap modelMap,HttpSession session){
        if(session.getAttribute("admin") == null){
            return new ModelAndView("redirect:/login/admin");
        }
        return new ModelAndView("redirect:products");
    }

    @RequestMapping("products")
    public String products(ModelMap modelMap, HttpSession session, @RequestParam(name = "categoryid",required = false) Integer categoryid){
        if(session.getAttribute("admin") == null){
            return "redirect:/login/admin";
        }


        if(categoryid == null){
            List<Product> productList = productService.findAll();
            modelMap.addAttribute("listproducts",productList);
        }else {
            List<Product> productList = productService.findAllByCategoryCateid(categoryid);
            modelMap.addAttribute("listproducts",productList);
        }
        modelMap.addAttribute("active","products");
        return "/admin/productmanager";
    }

    @PostMapping("saveofupdate")
    public ModelAndView saveOrupdate(ModelMap map, @Valid @ModelAttribute("product") ProductModel product, BindingResult result){
        Product entity = new Product();
        BeanUtils.copyProperties(product,entity);
        System.out.println(entity);
        System.out.println(product);
        Category category = new Category();
        category.setCateid(product.getCategoryid());
        entity.setCategory(category);
        if(!product.getImageFile().isEmpty()){
            if(product.getIsSource() == false){
                UUID uuid = UUID.randomUUID();
                String uuString = uuid.toString();
                // lưu file vào poster
                entity.setImage(iStorageService.getStorageFilename(product.getImageFile(),uuString));
                iStorageService.store(product.getImageFile(),entity.getImage());
                product.setIsSource(false);
            }else{
                // lưu file vao cloudinary
                try {
                    Map r = this.cloudinary.uploader().upload(product.getImageFile().getBytes(),
                            ObjectUtils.asMap("resource_type","auto"));
                    String img = (String) r.get("secure_url");
                    entity.setImage(img);
                    product.setIsSource(true);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }
        productService.save(entity);
        return new ModelAndView("forward:/admin/products",map);
    }
    @RequestMapping("/add")
    public String add(ModelMap model) {
        ProductModel productModel = new ProductModel();
        productModel.setAddorupdate(true);
        model.addAttribute("product",productModel);
        return "/admin/productdetail";
    }

    @RequestMapping("/edit")
    public String edit(ModelMap model,@RequestParam("productid") Integer proid) {
        Product entity = new Product();
        ProductModel productModel = new ProductModel();
        entity = productService.findById(proid).get();
        BeanUtils.copyProperties(entity,productModel);

        if(entity.getImage() !=null){
        if(("http").equals(entity.getImage().substring(0,4))){
            productModel.setIsSource(true);
        }else {
            productModel.setIsSource(false);
        }
        }
        model.addAttribute("product",productModel);
        return "/admin/productdetail";
    }

    @RequestMapping("/editsave")
    public ModelAndView editsave(ModelMap map, @Valid @ModelAttribute("product") ProductModel product, BindingResult result) {
        Product entity = new Product();
        BeanUtils.copyProperties(product,entity);
        System.out.println(product.getProductid());
        entity.setCategory(categoryService.findById(product.getCategoryid()).get());
        productService.save(entity);
        return new ModelAndView("redirect:/admin/products");
    }

    @RequestMapping("/delete")
    public ModelAndView delete(ModelMap model,@RequestParam("productid") Integer proid) {
        List<Cart_item> list = cartItemService.findAllByProductProductid(proid);
        if(list.size() != 0){
            model.addAttribute("REF_cartitem_product",true);
            return new ModelAndView("forward:/admin/products");
        }
        productService.deleteById(proid);
        return new ModelAndView("redirect:/admin/products");
    }
}
