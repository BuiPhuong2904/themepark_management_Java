package com.park.themepark.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.park.themepark.dao.KhuVucDAO;
import com.park.themepark.model.KhuVuc;

@RestController
public class KhuVucController {
    
    @Autowired
    private KhuVucDAO khuVucDAO;
    
    @GetMapping("/api/khu-vuc")
    public List<KhuVuc> getDanhSachKhuVuc() {
        return khuVucDAO.findAllActive(); // trả về danh sách khu vực đang thuê được
    }

}
