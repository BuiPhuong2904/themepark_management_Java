package com.park.themepark.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.park.themepark.dao.SanPhamDAO;
import com.park.themepark.model.SanPham;

@RestController
public class SanPhamController {
    
    @Autowired
    private SanPhamDAO sanPhamDAO;

    @GetMapping("/api/san-pham")
    @ResponseBody
    public List<SanPham> getAllSanPham() {
        return sanPhamDAO.findAll();
    }

}
