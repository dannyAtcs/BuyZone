package com.atcs.product.BuyZone.controller;

import com.atcs.product.BuyZone.dto.Product;
import com.atcs.product.BuyZone.service.AuthService;
import com.atcs.product.BuyZone.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Controller

public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    ProductService productService;

    @GetMapping("/error/access-denied")
    public String getError()
    {
        return "error/access-denied";
    }

    @GetMapping("/")
    public String getIndex()
    {
        return "home";
    }

    @GetMapping("/home")
    public String homePro(HttpServletRequest req, HttpServletResponse res, Model model) throws IOException {
        List<Product> product = productService.getAllProduct(req, res, 1);
        model.addAttribute("products",product);
        return "home";
    }

    @GetMapping("/productview/")
    public String getByParameter(HttpServletRequest req, HttpServletResponse res, Model model,@RequestParam(value = "pro") String pcode) throws IOException{
        List<Product> product = productService.getByParameter(req,res,pcode);
        model.addAttribute("products",product);
        return "product";
    }

    @GetMapping("/register")
    public String register(HttpServletRequest req, HttpServletResponse res,@RequestParam(value = "params",required = false) String params,Model model)
    {
        if(authService.checkAuthToken(req))
        {

            return "redirect:/";
        }
        if(!(Objects.isNull(params)))
        {
            model.addAttribute("params",params);
        }
        return "register";
    }


    @GetMapping("/login")
    public String login(HttpServletRequest req, HttpServletResponse res, Model model, @RequestParam(value = "error", required = false) String error) throws IOException {
        if(authService.checkAuthToken(req))
        {
            List<Product> product = productService.getProduct(req, res, 1);
            model.addAttribute("products",product);

            return "welcome";
        }
        if(!(Objects.isNull(error)))
        {
            model.addAttribute("params",error);
        }

        return "login";
    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest req, HttpServletResponse res)
    {
        return "home";
    }

    @PostMapping("/api/register")
    public void apiRegister(HttpServletRequest req, HttpServletResponse res,@RequestParam("email") String email,@RequestParam("password") String password,@RequestParam("cpassword") String cpassword,@RequestParam("firstname") String firstName,@RequestParam("lastname") String lastName) throws IOException {
        authService.register(req,res,email,password,cpassword,firstName,lastName);
    }


    @PostMapping("/api/login")
    public void apiLogin(HttpServletRequest req, HttpServletResponse res, @RequestParam("username") String username, @RequestParam("password") String password) throws IOException {
       authService.login(req,res,username,password);

    }

    @PostMapping("/api/logout")
    public void apiLogout(HttpServletRequest req, HttpServletResponse res) throws IOException {
        authService.logout(req,res);
    }
}
