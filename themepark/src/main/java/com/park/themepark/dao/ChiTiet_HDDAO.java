package com.park.themepark.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.park.themepark.model.ChiTiet_HD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ChiTiet_HDDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private ChiTiet_HD mapRow(ResultSet rs, int rowNum) throws SQLException {
        ChiTiet_HD ct = new ChiTiet_HD();
        ct.setMaHD(rs.getString("MAHD"));
        ct.setLoai(rs.getString("LOAI"));
        ct.setMaLoai(rs.getString("MALOAI"));
        ct.setSoLuong(rs.getInt("SOLUONG"));
        ct.setThanhTien(rs.getDouble("THANHTIEN"));
        return ct;
    }

    public List<ChiTiet_HD> findByMaHD(String maHD) {
        String sql = "SELECT * FROM CHITIET_HD WHERE MAHD = ?";
        return jdbcTemplate.query(sql, this::mapRow, maHD);
    }

    public boolean isMaLoaiValid(ChiTiet_HD ct) {
        String sql;
        if ("VE".equalsIgnoreCase(ct.getLoai())) {
            sql = "SELECT COUNT(*) FROM VE WHERE MAVE = ?";
        } else if ("COMBO".equalsIgnoreCase(ct.getLoai())) {
            sql = "SELECT COUNT(*) FROM COMBO_VE WHERE MACB = ?";
        } else {
            return false; // Trường hợp LOAI không hợp lệ
        }

        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, ct.getMaLoai());
        return count != null && count > 0;
    }
    
    public int insert(ChiTiet_HD ct) {
        if (!isMaLoaiValid(ct)) {
            throw new IllegalArgumentException("Mã loại không hợp lệ hoặc không phù hợp với loại: " + ct.getLoai());
        }

        String sql = "INSERT INTO CHITIET_HD (MAHD, LOAI, MALOAI, SOLUONG, THANHTIEN) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, ct.getMaHD(), ct.getLoai(), ct.getMaLoai(), ct.getSoLuong(), ct.getThanhTien());
    }
    // Update / Delete nếu cần
}