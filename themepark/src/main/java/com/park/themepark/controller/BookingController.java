package com.park.themepark.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookingController {
    @GetMapping("/datve")
    public String bookingPage() {
        return "datve";
    }

    @GetMapping("/thanhtoan")
    public String paymentPage() {
        return "thanhtoan";
    }
}