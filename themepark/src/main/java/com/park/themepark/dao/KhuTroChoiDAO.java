package com.park.themepark.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.park.themepark.model.KhuTroChoi;

@Repository
public class KhuTroChoiDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private KhuTroChoi mapRow(ResultSet rs, int rowNum) throws SQLException {
        KhuTroChoi ktc = new KhuTroChoi();
        ktc.setMaKTC(rs.getString("MAKTC"));
        ktc.setTenKTC(rs.getString("TENKTC"));
        ktc.setMoTa(rs.getString("MOTA"));
        ktc.setTrangThai(rs.getString("TRANGTHAI"));
        return ktc;
    }

    public List<KhuTroChoi> findAll() {
        String sql = "SELECT * FROM KHUTROCHOI";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    public KhuTroChoi findById(String maKTC) {
        String sql = "SELECT * FROM KHUTROCHOI WHERE MAKTC = ?";
        return jdbcTemplate.queryForObject(sql, this::mapRow, maKTC);
    }

    public int insert(KhuTroChoi ktc) {
        String sql = "INSERT INTO KHUTROCHOI (MAKTC, TENKTC, MOTA, TRANGTHAI) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, ktc.getMaKTC(), ktc.getTenKTC(), ktc.getMoTa(), ktc.getTrangThai());
    }

    public int update(KhuTroChoi ktc) {
        String sql = "UPDATE KHUTROCHOI SET TENKTC = ?, MOTA = ?, TRANGTHAI = ? WHERE MAKTC = ?";
        return jdbcTemplate.update(sql, ktc.getTenKTC(), ktc.getMoTa(), ktc.getTrangThai(), ktc.getMaKTC());
    }

    public int delete(String maKTC) {
        String sql = "DELETE FROM KHUTROCHOI WHERE MAKTC = ?";
        return jdbcTemplate.update(sql, maKTC);
    }
}