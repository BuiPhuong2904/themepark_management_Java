package com.park.themepark.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.park.themepark.model.KhachHang;
import com.park.themepark.model.TaiKhoan;

import jakarta.servlet.http.HttpSession;

@ControllerAdvice
public class GlobalController {

    @ModelAttribute
    public void addSessionAttributesToModel(HttpSession session, Model model) {
        // Lấy từ session
        String maTK = (String) session.getAttribute("maTK");
        TaiKhoan taiKhoan = (TaiKhoan) session.getAttribute("user");
        KhachHang khachHang = (KhachHang) session.getAttribute("khachHang");

        // Thêm vào model
        model.addAttribute("isLoggedIn", maTK != null);
        model.addAttribute("taiKhoan", taiKhoan);
        model.addAttribute("khachHang", khachHang);
    }

}