package com.park.themepark.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.park.themepark.model.ChiTiet_CB_Ve;

@Repository
public class ChiTiet_CB_VeDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private ChiTiet_CB_Ve mapRow(ResultSet rs, int rowNum) throws SQLException {
        ChiTiet_CB_Ve ct = new ChiTiet_CB_Ve();
        ct.setMaCB(rs.getString("MACB"));
        ct.setMaVe(rs.getString("MAVE"));
        return ct;
    }

    public List<ChiTiet_CB_Ve> findByCombo(String maCB) {
        String sql = "SELECT * FROM CHITIET_CB_VE WHERE MACB = ?";
        return jdbcTemplate.query(sql, this::mapRow, maCB);
    }

    public int insert(ChiTiet_CB_Ve ct) {
        String sql = "INSERT INTO CHITIET_CB_VE (MACB, MAVE) VALUES (?, ?)";
        return jdbcTemplate.update(sql, ct.getMaCB(), ct.getMaVe());
    }

    public int delete(String maCB, String maVe) {
        String sql = "DELETE FROM CHITIET_CB_VE WHERE MACB = ? AND MAVE = ?";
        return jdbcTemplate.update(sql, maCB, maVe);
    }
}