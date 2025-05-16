package com.park.themepark.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
    @GetMapping("/dangnhap")
    public String loginPage() {
        return "dangnhap";
    }

    @GetMapping("/dangky")
    public String registerPage() {
        return "dangky";
    }

    @PostMapping("/dangky")
    public String xuLyDangKy(@ModelAttribute String user) {
        // Lưu user vào database (chưa có thì cần tạo)
        return "redirect:/dangnhap";
    }

    @PostMapping("/dangnhap")
    public String xuLyDangNhap(@RequestParam String email, @RequestParam String password) {
        // Kiểm tra thông tin đăng nhập từ DB
        return "redirect:/"; // hoặc thông báo lỗi nếu sai
    }

}