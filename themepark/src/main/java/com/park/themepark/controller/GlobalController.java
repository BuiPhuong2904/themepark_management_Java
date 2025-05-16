package com.park.themepark.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpSession;

@Controller
public class GlobalController {

    @ModelAttribute
    public void addKhachHangToModel(HttpSession session, Model model) {
        Object khachHang = session.getAttribute("khachHang");
        if (khachHang != null) {
            model.addAttribute("khachHang", khachHang);
        }
    }
}