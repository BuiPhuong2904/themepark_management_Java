package com.park.themepark.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.park.themepark.dao.Combo_VeDAO;
import com.park.themepark.dao.KhuTroChoiDAO;
import com.park.themepark.dao.KhuyenMaiDAO;
import com.park.themepark.dao.VeDAO;
import com.park.themepark.model.Combo_Ve;
import com.park.themepark.model.KhuTroChoi;
import com.park.themepark.model.KhuyenMai;
import com.park.themepark.model.Ve;

@Controller
public class DashboardController {

    @Autowired
    private KhuTroChoiDAO khuTroChoiDAO;

    @Autowired
    private VeDAO veDAO;

    @Autowired
    private Combo_VeDAO comboVeDAO;

    @Autowired
    private KhuyenMaiDAO khuyenMaiDAO;

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

    @GetMapping("/ql_combo")
    public String hienThiQLCombo(Model model) {
        List<Combo_Ve> list = comboVeDAO.findAll();
        model.addAttribute("danhSachCombo", list);
        return "e_ql_combo"; 
    }

    @GetMapping("/ql_khuyenmai")
    public String hienThiQLKhuyenMai(Model model) {
        List<KhuyenMai> list = khuyenMaiDAO.findAll();
        model.addAttribute("danhSachKhuyenMai", list);
        return "e_ql_khuyenmai"; 
    }
}
