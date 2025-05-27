package com.park.themepark.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.park.themepark.model.Combo_Ve;

@Repository
public class Combo_VeDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Combo_Ve mapRow(ResultSet rs, int rowNum) throws SQLException {
        Combo_Ve cb = new Combo_Ve();
        cb.setMaCB(rs.getString("MACB"));
        cb.setTenCB(rs.getString("TENCB"));
        cb.setLoaiCB(rs.getString("LOAICB"));
        cb.setHinhAnh(rs.getString("HINHANH"));
        cb.setGiaCB(rs.getDouble("GIACB"));
        cb.setMoTa(rs.getString("MOTA"));
        cb.setTrangThai(rs.getString("TRANGTHAI"));
        return cb;
    }

    public List<Combo_Ve> findAll() {
        String sql = "SELECT * FROM COMBO_VE";
        List<Combo_Ve> list = jdbcTemplate.query(sql, this::mapRow);
        System.out.println("DAO - found combos: " + list.size());
        return list;
    }

    public Combo_Ve findById(String maCB) {
        String sql = "SELECT * FROM COMBO_VE WHERE MACB = ?";
        return jdbcTemplate.queryForObject(sql, this::mapRow, maCB);
    }

    public int insert(Combo_Ve cb) {
        String sql = "INSERT INTO COMBO_VE (MACB, TENCB, LOAICB, HINHANH, GIACB, MOTA, TRANGTHAI) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                cb.getMaCB(), cb.getTenCB(), cb.getLoaiCB(), cb.getHinhAnh(),
                cb.getGiaCB(), cb.getMoTa(), cb.getTrangThai());
    }

    public int update(Combo_Ve cb) {
        String sql = "UPDATE COMBO_VE SET TENCB = ?, LOAICB = ?, HINHANH = ?, GIACB = ?, MOTA = ?, TRANGTHAI = ? WHERE MACB = ?";
        return jdbcTemplate.update(sql,
                cb.getTenCB(), cb.getLoaiCB(), cb.getHinhAnh(),
                cb.getGiaCB(), cb.getMoTa(), cb.getTrangThai(), cb.getMaCB());
    }

    public int delete(String maCB) {
        String sql = "DELETE FROM COMBO_VE WHERE MACB = ?";
        return jdbcTemplate.update(sql, maCB);
    }

    public Combo_Ve findByTenCB(String tenCB) {
        String sql = "SELECT * FROM COMBO_VE WHERE TENCB = ?";
        List<Combo_Ve> list = jdbcTemplate.query(sql, this::mapRow, tenCB);
        System.out.println("Tìm combo theo tên: '" + tenCB + "'");
        return list.isEmpty() ? null : list.get(0);
    }

}