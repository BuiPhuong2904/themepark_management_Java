package com.park.themepark.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.park.themepark.model.KhachHang;
import com.park.themepark.service.KhachHangService;

@Controller
public class BookingController {

    @GetMapping("/chonve")
    public String chonVe(@RequestParam(required = false) String ngay, Model model) {
        model.addAttribute("ngayThamQuan", ngay); // để render lại nếu cần
        return "chonve"; 
    }

    @GetMapping("/thanhtoan")
    public String thanhToan(Model model, Principal principal) {
        if (principal != null) {
            KhachHang khachHang = KhachHangService.findByEmail(principal.getName());
            model.addAttribute("khachHang", khachHang);
        }
        return "thanhtoan"; 
    }

}