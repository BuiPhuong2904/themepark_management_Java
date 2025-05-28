package com.park.themepark.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.park.themepark.dao.KhuTroChoiDAO;
import com.park.themepark.model.KhuTroChoi;

@RestController
@RequestMapping("/api")
public class KhuTroChoiController {

    @Autowired
    private KhuTroChoiDAO khuTroChoiDAO;

    @GetMapping("/khu-tro-choi")
    public List<KhuTroChoi> findAll() {
        return khuTroChoiDAO.findAll();
    }
}
