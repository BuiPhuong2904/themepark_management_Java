package com.park.themepark.controller;

import com.park.themepark.dao.PhieuKhoDAO;
import com.park.themepark.dao.ChiTiet_PKDAO;
import com.park.themepark.model.PhieuKho;
import com.park.themepark.model.ChiTiet_PK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class PhieuKhoController {

    @Autowired
    private PhieuKhoDAO phieuKhoDAO;

    @Autowired
    private ChiTiet_PKDAO chiTietPKDAO;

    @GetMapping("/api/chi-tiet-phieu/{maPhieu}")
    @ResponseBody
    public Map<String, Object> getChiTietPhieu(@PathVariable String maPhieu) {
        Map<String, Object> response = new HashMap<>();

        PhieuKho phieu = phieuKhoDAO.findById(maPhieu); 
        List<ChiTiet_PK> dsChiTiet = chiTietPKDAO.findByPhieu(maPhieu);

        response.put("phieu", phieu);
        response.put("dsChiTiet", dsChiTiet);

        return response;
    }
}