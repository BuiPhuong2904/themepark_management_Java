package com.park.themepark.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping("/")
    public String homePage() {
        return "index"; // trỏ tới templates/index.html
    }

    @GetMapping("/dangnhap")
    public String loginPage() {
        return "dangnhap"; // -> templates/dangnhap.html
    }

    @GetMapping("/dangky")
    public String registerPage() {
        return "dangky"; // -> templates/dangky.html
    }

    @GetMapping("/datve")
    public String bookingPage() {
        return "datve"; // -> templates/datve.html
    }

    @GetMapping("/thanhtoan")
    public String paymentPage() {
        return "thanhtoan"; // -> templates/thanhtoan.html
    }

}