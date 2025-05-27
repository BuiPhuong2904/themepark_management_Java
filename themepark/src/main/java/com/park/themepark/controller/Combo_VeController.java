package com.park.themepark.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.park.themepark.dao.ChiTiet_CB_VeDAO;
import com.park.themepark.dao.Combo_VeDAO;
import com.park.themepark.model.ChiTiet_CB_Ve;
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

    @GetMapping("/api/combos")
    public List<Combo_Ve> getAllCombos() {
        return comboVeDAO.findAll();
    }

    @PostMapping("/api/combos/full")
    @ResponseBody
    public ResponseEntity<?> addFullCombo(@RequestBody Map<String, Object> payload) {
        try {
            // Lấy combo
            Map<String, Object> comboMap = (Map<String, Object>) payload.get("combo");
            Combo_Ve combo = new Combo_Ve();
            combo.setMaCB((String) comboMap.get("maCB"));
            combo.setTenCB((String) comboMap.get("tenCB"));
            combo.setLoaiCB((String) comboMap.get("loaiCB"));
            combo.setHinhAnh((String) comboMap.get("hinhAnh"));
            combo.setGiaCB(Double.parseDouble(comboMap.get("giaCB").toString()));
            combo.setMoTa((String) comboMap.get("moTa"));
            combo.setTrangThai((String) comboMap.get("trangThai"));

            // Thêm combo
            int rows = comboVeDAO.insert(combo);
            if (rows == 0) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Không thể thêm combo");
            }

            // Lấy chi tiết combo-ve
            List<Map<String, String>> chiTietList = (List<Map<String, String>>) payload.get("chiTietVe");
            for (Map<String, String> ct : chiTietList) {
                ChiTiet_CB_Ve ctcb = new ChiTiet_CB_Ve();
                ctcb.setMaCB(ct.get("maCB"));
                ctcb.setMaVe(ct.get("maVe"));
                chiTietCBVeDAO.insert(ctcb); // bạn viết DAO tương tự insert cho chi tiết combo-ve
            }

            return ResponseEntity.ok(combo);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi: " + e.getMessage());
        }
    }

}
