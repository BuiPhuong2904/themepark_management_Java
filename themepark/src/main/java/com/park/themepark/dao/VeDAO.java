package com.park.themepark.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.park.themepark.model.Ve;

@Repository
public class VeDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Ve mapRow(ResultSet rs, int rowNum) throws SQLException {
        Ve ve = new Ve();
        ve.setMaVe(rs.getString("MAVE"));
        ve.setTenVe(rs.getString("TENVE"));
        ve.setLoaiVe(rs.getString("LOAIVE"));
        ve.setMoTa(rs.getString("MOTA"));
        ve.setHinhAnh(rs.getString("HINHANH"));
        ve.setGiaVe(rs.getDouble("GIAVE"));
        // ve.setNgaySuDung(rs.getDate("NGAYSUDUNG"));
        ve.setTrangThai(rs.getString("TRANGTHAI"));
        ve.setMaKTC(rs.getString("MAKTC"));
        return ve;
    }

    public List<Ve> findAll() {
        String sql = "SELECT * FROM VE";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    public Ve findById(String maVe) {
        String sql = "SELECT * FROM VE WHERE MAVE = ?";
        return jdbcTemplate.queryForObject(sql, this::mapRow, maVe);
    }

    public int insert(Ve ve) {
        String sql = "INSERT INTO VE (MAVE, TENVE, LOAIVE, MOTA, HINHANH, GIAVE, TRANGTHAI, MAKTC) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                ve.getMaVe(), ve.getTenVe(), ve.getLoaiVe(), ve.getMoTa(), ve.getHinhAnh(),
                ve.getGiaVe(), ve.getTrangThai(), ve.getMaKTC());
    }

    public int update(Ve ve) {
        String sql = "UPDATE VE SET TENVE = ?, LOAIVE = ?, MOTA = ?, HINHANH = ?, GIAVE = ?, TRANGTHAI = ?, MAKTC = ? " +
                     "WHERE MAVE = ?";
        return jdbcTemplate.update(sql,
                ve.getTenVe(), ve.getLoaiVe(), ve.getMoTa(), ve.getHinhAnh(),
                ve.getGiaVe(), ve.getTrangThai(), ve.getMaKTC(), ve.getMaVe());
    }

    public int delete(String maVe) {
        String sql = "DELETE FROM VE WHERE MAVE = ?";
        return jdbcTemplate.update(sql, maVe);
    }
}