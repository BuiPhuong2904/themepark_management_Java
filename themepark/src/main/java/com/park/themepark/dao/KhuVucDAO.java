package com.park.themepark.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.park.themepark.model.KhuVuc;

@Repository
public class KhuVucDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private KhuVuc mapRow(ResultSet rs, int rowNum) throws SQLException {
        KhuVuc kv = new KhuVuc();
        kv.setMaKV(rs.getString("MAKV"));
        kv.setTenKV(rs.getString("TENKV"));
        kv.setMoTa(rs.getString("MOTA"));
        kv.setGiaThue(rs.getDouble("GIATHUE"));
        kv.setTrangThai(rs.getString("TRANGTHAI"));
        return kv;
    }

    public List<KhuVuc> findAll() {
        String sql = "SELECT * FROM KHUVUC";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    public KhuVuc findById(String maKV) {
        String sql = "SELECT * FROM KHUVUC WHERE MAKV = ?";
        return jdbcTemplate.queryForObject(sql, this::mapRow, maKV);
    }

    public int insert(KhuVuc kv) {
        String sql = "INSERT INTO KHUVUC (MAKV, TENKV, MOTA, GIATHUE, TRANGTHAI) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, kv.getMaKV(), kv.getTenKV(), kv.getMoTa(), kv.getGiaThue(), kv.getTrangThai());
    }

    public int update(KhuVuc kv) {
        String sql = "UPDATE KHUVUC SET TENKV = ?, MOTA = ?, GIATHUE = ?, TRANGTHAI = ? WHERE MAKV = ?";
        return jdbcTemplate.update(sql, kv.getTenKV(), kv.getMoTa(), kv.getGiaThue(), kv.getTrangThai(), kv.getMaKV());
    }

    public int delete(String maKV) {
        String sql = "DELETE FROM KHUVUC WHERE MAKV = ?";
        return jdbcTemplate.update(sql, maKV);
    }
}