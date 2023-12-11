package com.example.Controller.web;

import com.example.Entity.Cart;
import com.example.Entity.Cart_item;
import com.example.Entity.Category;
import com.example.Entity.Product;
import com.example.Model.Cart_ItemModel;
import com.example.Model.CategoryModel;
import com.example.Model.UserModel;
import com.example.Services.ICartItemService;
import com.example.Services.ICartService;
import com.example.Services.ICategoryService;
import com.example.Services.IProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    ICategoryService categoryService;
    @Autowired
    IProductService productService;
    @Autowired
    ICartService cartService;
    @Autowired
    ICartItemService cartItemService;

    @ModelAttribute("categories")
    public List<CategoryModel> getcategories(){
        return categoryService.findAll().stream().map(item->{
            CategoryModel cate = new CategoryModel();
            BeanUtils.copyProperties(item,cate);
            return cate;
        }).toList();
    }
    @RequestMapping("/home")
    public String myPage(ModelMap modelMap,HttpSession session) {
        Pageable pageable = PageRequest.of(0, 3);
        List<Product> Top3NewProducts = productService.findRecentProduct(pageable);
        UserModel user = (UserModel) session.getAttribute("user");
        modelMap.addAttribute("top3pros",Top3NewProducts);
        modelMap.addAttribute("user", user);
        // lấy ssoosluowngj sản phầm
        if(user != null){
            int totalproduct = 0;
            Cart cart = cartService.findByUserUserid(user.getUserid());
            List<Cart_item> cartItemall = cartItemService.findAllByCartCartid(cart.getCartid());
            List<Cart_ItemModel> listcart = new ArrayList<>();
            for(Cart_item item :cartItemall){
                Cart_ItemModel Cart_Itemmodel = new Cart_ItemModel();
                Cart_Itemmodel.setProductname(item.getProduct().getProductname());
                Cart_Itemmodel.setCartid(cart.getCartid());
                Cart_Itemmodel.setCartitemid(item.getCartitemid());
                Cart_Itemmodel.setProductname(item.getProduct().getProductname());
                Cart_Itemmodel.setQuantity(item.getQuantity());
                totalproduct += item.getQuantity();
            }
            modelMap.addAttribute("totalproduct",totalproduct);
        }

        return "web/home";
    }
}
