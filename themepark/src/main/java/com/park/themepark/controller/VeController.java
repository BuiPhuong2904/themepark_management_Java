package com.park.themepark.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.park.themepark.dao.VeDAO;
import com.park.themepark.model.Ve;

@RestController
public class VeController {

    @Autowired
    private VeDAO veDAO;

    @GetMapping("/api/ve")
    @ResponseBody
    public List<Ve> getAllVe() {
        return veDAO.findAll(); 
    }

}
