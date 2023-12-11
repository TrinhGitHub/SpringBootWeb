package com.example.Controller.web;

import com.example.Entity.User;
import com.example.Model.CategoryModel;
import com.example.Model.UserModel;
import com.example.Services.ICategoryService;
import com.example.Services.IUserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class ImfomationUserController {
    @Autowired
    IUserService userService;
    @Autowired
    ICategoryService categoryService;
    @ModelAttribute("categories")
    public List<CategoryModel> getcategories(){
        return categoryService.findAll().stream().map(item->{
            CategoryModel cate = new CategoryModel();
            BeanUtils.copyProperties(item,cate);
            return cate;
        }).toList();
    }
    @RequestMapping("/inforUpdate")
    public ModelAndView infor(ModelMap modelMap, HttpSession session,
                              @Valid @ModelAttribute("user") UserModel user, BindingResult result
                         ) {
            User entity = new User();
            BeanUtils.copyProperties(user,entity);
            userService.updateUserDetails(entity.getUserid(),entity.getEmail(),entity.getAddress(),entity.getPhone(),entity.getName());
            session.removeAttribute("user");
            UserModel userModel = new UserModel();
            User entitydata = userService.findById(entity.getUserid()).get();
            BeanUtils.copyProperties(entitydata,userModel);
            session.setAttribute("user",userModel);
            modelMap.addAttribute("message","Thay đổi thông tin thành công");
            return new ModelAndView("forward:infor", modelMap);
    }

    @RequestMapping("/infor")
    public String information(ModelMap modelMap, HttpSession session
    ) {
            return "/web/information";
    }
}
