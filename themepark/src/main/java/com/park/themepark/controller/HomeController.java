package com.park.themepark.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
    
    @GetMapping("/")
    public String homePage(HttpSession session, Model model) {
        // KhachHang khachHang = (KhachHang) session.getAttribute("khachHang");
        // if (khachHang != null) {
        //     model.addAttribute("khachHang", khachHang);
        // }
        return "index"; 
    }

    @GetMapping("/admin")
    public String adminPage(HttpSession session, Model model) {
        return "dashboard"; 
    }
}