package com.park.themepark.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

public class dashboard {
    @GetMapping("/ho-so")
    public String hienThiHoSo() {
        return "e_ho_so"; // Trả về tên file HTML không cần .html
    }
}
