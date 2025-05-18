package com.park.themepark.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.park.themepark.model.CTHD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CTHDDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CTHD mapRow(ResultSet rs, int rowNum) throws SQLException {
        CTHD ct = new CTHD();
        ct.setMaHD(rs.getString("MAHD"));
        ct.setLoai(rs.getString("LOAI"));
        ct.setMaLoai(rs.getString("MALOAI"));
        ct.setSoLuong(rs.getInt("SOLUONG"));
        ct.setThanhTien(rs.getDouble("THANHTIEN"));
        return ct;
    }

    public List<CTHD> findByMaHD(String maHD) {
        String sql = "SELECT * FROM CHITIET_HD WHERE MAHD = ?";
        return jdbcTemplate.query(sql, this::mapRow, maHD);
    }

    public int insert(CTHD ct) {
        String sql = "INSERT INTO CHITIET_HD (MAHD, LOAI, MALOAI, SOLUONG, THANHTIEN) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, ct.getMaHD(), ct.getLoai(), ct.getMaLoai(), ct.getSoLuong(), ct.getThanhTien());
    }

    // Update / Delete nếu cần
}