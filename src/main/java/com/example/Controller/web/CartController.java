package com.example.Controller.web;

import com.example.Entity.Cart;
import com.example.Entity.Cart_item;
import com.example.Model.Cart_ItemModel;
import com.example.Model.CategoryModel;
import com.example.Model.UserModel;
import com.example.Services.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
@Controller
public class CartController {
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
    @RequestMapping("/addtocart")
    public String addtoCart(ModelMap modelMap, HttpSession session , @RequestParam("productid") Integer proid){
        UserModel userModel = (UserModel) session.getAttribute("user");
        if(userModel == null){
            return "login/login";
        }
        else{
            Cart cart = cartService.findByUserUserid(userModel.getUserid());
            List<Cart_item> cartItem = cartItemService.findAllByCartCartid(cart.getCartid());
            if(cartItem.size() == 0){
                Cart_item entity = new Cart_item();
                entity.setCart(cart);
                entity.setProduct(productService.findById(proid).get());
                entity.setQuantity(1);
                cartItemService.save(entity);
            }else {
                int haveindate = 0;
                for(Cart_item item :cartItem){
                    if(productService.findById(proid).get().getProductid() == item.getProduct().getProductid()){
                        item.setQuantity(item.getQuantity()+1);
                        cartItemService.save(item);
                        haveindate = 1;
                    }
                }
                if(haveindate == 0){
                    Cart_item cartIteme = new Cart_item();
                    cartIteme.setCart(cart);
                    cartIteme.setQuantity(1);
                    cartIteme.setProduct(productService.findById(proid).get());
                    cartItemService.save(cartIteme);
                }
            }
            List<Cart_item> cartItemall = cartItemService.findAllByCartCartid(cart.getCartid());
            List<Cart_ItemModel> listcart = new ArrayList<>();
            for(Cart_item item :cartItemall){
                Cart_ItemModel model = new Cart_ItemModel();
                model.setProductname(item.getProduct().getProductname());
                model.setCartid(cart.getCartid());
                model.setCartitemid(item.getCartitemid());
                model.setProductid(item.getProduct().getProductid());
                model.setQuantity(item.getQuantity());
                model.setImage(productService.findById(item.getProduct().getProductid()).get().getImage());
                listcart.add(model);
            }
            modelMap.addAttribute("list",listcart);
        }
        return "web/cart";
    }

    @RequestMapping("/cart")
    public ModelAndView CartCheck(ModelMap modelMap, HttpSession session){
        UserModel userModel = (UserModel) session.getAttribute("user");
        if(userModel == null){
            return new ModelAndView("redirect:/login",modelMap);
        }else{
            return new ModelAndView("forward:/shop/cart",modelMap);
        }

    }


    @RequestMapping("/shop/cart")
    public String Cart(ModelMap modelMap, HttpSession session){
        UserModel userModel = (UserModel) session.getAttribute("user");
        int totalproduct = 0;
        Cart cart = cartService.findByUserUserid(userModel.getUserid());
        List<Cart_item> cartItemall = cartItemService.findAllByCartCartid(cart.getCartid());
        List<Cart_ItemModel> listcart = new ArrayList<>();
        for(Cart_item item :cartItemall) {
            Cart_ItemModel model = new Cart_ItemModel();
            model.setProductname(item.getProduct().getProductname());
            model.setCartid(cart.getCartid());
            model.setCartitemid(item.getCartitemid());
            model.setProductid(item.getProduct().getProductid());
            model.setQuantity(item.getQuantity());
            model.setImage(productService.findById(item.getProduct().getProductid()).get().getImage());
            totalproduct += item.getQuantity();
            listcart.add(model);
        }
            modelMap.addAttribute("list",listcart);
            modelMap.addAttribute("totalproduct",totalproduct);
            return "/web/cart";
    }

    @RequestMapping("/cart/delete/{cartitemid}")
    public ModelAndView deletetoCart(ModelMap modelMap,HttpSession session, @PathVariable("cartitemid") Integer cartitemid){
        cartItemService.deleteById(cartitemid);
        return new ModelAndView("redirect:/shop/cart");
    }

    @RequestMapping("/minustocart")
    public ModelAndView minustocart(ModelMap modelMap, HttpSession session, @RequestParam("cart_itemid") Integer cart_itemid)
    {
        Cart_item entity = cartItemService.findById(cart_itemid).get();
        if(entity.getQuantity()>1){
            entity.setQuantity(entity.getQuantity()-1);
            cartItemService.save(entity);
        }
        else{
            cartItemService.deleteById(cart_itemid);
        }
        return new ModelAndView("redirect:/shop/cart");

    }
}
