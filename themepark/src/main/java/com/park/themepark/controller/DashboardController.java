package com.park.themepark.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.park.themepark.dao.KhuTroChoiDAO;
import com.park.themepark.dao.VeDAO;
import com.park.themepark.model.KhuTroChoi;
import com.park.themepark.model.Ve;

@Controller
public class DashboardController {

    @Autowired
    private KhuTroChoiDAO khuTroChoiDAO;

    @Autowired
    private VeDAO veDAO;

    @GetMapping("/ho_so")
    public String hienThiHoSo() {
        return "e_ho_so"; 
    }

    @GetMapping("/ds_tro_choi")
    public String hienThiDSTroChoi(Model model) {
        List<KhuTroChoi> list = khuTroChoiDAO.findAll();
        model.addAttribute("danhSachKTC", list);
        return "e_ds_trochoi"; 
    }

    @GetMapping("/ql_ve")
    public String hienThiQLVe(Model model) {
        List<Ve> list = veDAO.findAll();
        model.addAttribute("danhSachVe", list);
        return "e_ql_ve"; 
    }
}
