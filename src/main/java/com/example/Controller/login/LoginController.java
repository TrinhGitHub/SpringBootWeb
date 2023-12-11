package com.example.Controller.login;

import com.example.Entity.Cart;
import com.example.Entity.User;
import com.example.Model.UserModel;
import com.example.Services.ICartService;
import com.example.Services.IUserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/login")
@Service
public class LoginController {
    @Autowired
    IUserService userService;
    @Autowired
    ICartService cartService;
    @RequestMapping("")
    public String myPage() {
        return "login/login";
    }
    @RequestMapping("add")
    public String add(ModelMap model) {
        return "login/register";
    }
    @PostMapping("register")
    public ModelAndView register(ModelMap map, @Valid @ModelAttribute("user") UserModel user, BindingResult result) {
        if(result.hasErrors()){
            // In thông tin lỗi ra console cho mục đích debug
            System.out.println(user);
            for (ObjectError error : result.getAllErrors()) {
                System.out.println("ObjectError: " + error.getDefaultMessage());
            }
            return new ModelAndView("/login/register");
        }
        User userEntity = new User();
        BeanUtils.copyProperties(user,userEntity);
        Optional<User> opt = userService.findUserByUsername(userEntity.getUsername());
        String message = "";
        if(!opt.isPresent()){
            userService.save(userEntity);
            Cart entity = new Cart();
            entity.setUser(userEntity);
            cartService.save(entity);
            message = "Đăng ký thành công";
        }else{
            message = "Tài khoản đã tồn tại";
            map.addAttribute("message",message);
            return new ModelAndView("/login/register",map);
        }
        map.addAttribute("message",message);
        return new ModelAndView("forward:/login",map);
    }
    @PostMapping("checkLogin")
    public ModelAndView loginss(ModelMap modelmap, HttpSession session,
                                @RequestParam("username") String username,
                                @RequestParam("password") String password) {
        Optional<User> opt = userService.findUserByUsername(username);
        User entityUser = opt.get();
        if(entityUser != null){
            UserModel user = new UserModel();
            BeanUtils.copyProperties(entityUser,user);
            if(user.isRole() == true){
                if(BCrypt.checkpw(password,user.getPassword())){
                    UserModel admin = new UserModel();
                    BeanUtils.copyProperties(user,admin);
                    session.setAttribute("admin", admin);
                    return new ModelAndView("redirect:/admin/productManager",modelmap);
                }
            }
            else{
                if(BCrypt.checkpw(password,user.getPassword())){
                    session.setAttribute("user", user);
                    return new ModelAndView("redirect:/home", modelmap);
                }
            }

        }
        return new ModelAndView("redirect:/login",modelmap);
    }
    @GetMapping("logout")
    public ModelAndView logout(ModelMap modelmap, HttpSession session){
        UserModel user = (UserModel) session.getAttribute("user");
        UserModel admin = (UserModel) session.getAttribute("admin");
        if(admin != null){
            session.removeAttribute("admin");
        }
        else if(user != null) {
            session.removeAttribute("user");
        }
        return new ModelAndView("redirect:/home", modelmap);
    }

    @RequestMapping("/admin")
    public String login(){
        return "/admin/login";
    }
}
