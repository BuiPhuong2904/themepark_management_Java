package com.park.themepark.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.park.themepark.dao.ChiTiet_CB_VeDAO;
import com.park.themepark.dao.Combo_VeDAO;
import com.park.themepark.model.Combo_Ve;
import com.park.themepark.model.Ve;

@RestController
public class Combo_VeController {

    @Autowired
    private Combo_VeDAO comboVeDAO;

    @Autowired
    private ChiTiet_CB_VeDAO chiTietCBVeDAO;
    
    @GetMapping("/api/chi-tiet-combo/{maCB}")
    @ResponseBody
    public Map<String, Object> apiChiTietCombo(@PathVariable String maCB) {
        Map<String, Object> res = new HashMap<>();
        Combo_Ve combo = comboVeDAO.findById(maCB);
        List<Ve> dsVe = chiTietCBVeDAO.findVeByMaCB(maCB);
        
        res.put("combo", combo);
        res.put("dsVe", dsVe);
        return res;
    }

}
