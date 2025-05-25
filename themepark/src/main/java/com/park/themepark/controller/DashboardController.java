package com.park.themepark.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    @GetMapping("/ho_so")
    public String hienThiHoSo() {
        return "e_ho_so"; 
    }
}
