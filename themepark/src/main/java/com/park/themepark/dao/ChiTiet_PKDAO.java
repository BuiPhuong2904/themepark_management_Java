package com.park.themepark.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.park.themepark.model.ChiTiet_PK;

@Repository
public class ChiTiet_PKDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private ChiTiet_PK mapRow(ResultSet rs, int rowNum) throws SQLException {
        ChiTiet_PK ct = new ChiTiet_PK();
        ct.setMaPhieu(rs.getString("MAPHIEU"));
        ct.setMaSP(rs.getString("MASP"));
        ct.setSoLuong(rs.getInt("SOLUONG"));
        ct.setDonGia(rs.getDouble("DONGIA"));
        return ct;
    }

    public List<ChiTiet_PK> findByPhieu(String maPhieu) {
        String sql = "SELECT * FROM CT_PHIEUKHO WHERE MAPHIEU = ?";
        return jdbcTemplate.query(sql, this::mapRow, maPhieu);
    }

    public int insert(ChiTiet_PK ct) {
        String sql = "INSERT INTO CT_PHIEUKHO VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
            ct.getMaPhieu(), ct.getMaSP(), ct.getSoLuong(), ct.getDonGia());
    }
}