package com.park.themepark.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.park.themepark.model.ChamCong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ChamCongDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private ChamCong mapRow(ResultSet rs, int rowNum) throws SQLException {
        ChamCong cc = new ChamCong();
        cc.setMaChamCong(rs.getString("MACHAMCONG"));
        cc.setNgayLV(rs.getDate("NGAYLV"));
        cc.setSoGioLam(rs.getDouble("SOGIOLAM"));
        cc.setMaNV(rs.getString("MANV"));
        return cc;
    }

    public List<ChamCong> findAll() {
        String sql = "SELECT * FROM CHAMCONG";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    public ChamCong findById(String maChamCong) {
        String sql = "SELECT * FROM CHAMCONG WHERE MACHAMCONG = ?";
        return jdbcTemplate.queryForObject(sql, this::mapRow, maChamCong);
    }

    public int insert(ChamCong cc) {
        String sql = "INSERT INTO CHAMCONG (MACHAMCONG, NGAYLV, SOGIOLAM, MANV) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, cc.getMaChamCong(), cc.getNgayLV(), cc.getSoGioLam(), cc.getMaNV());
    }

    public int update(ChamCong cc) {
        String sql = "UPDATE CHAMCONG SET NGAYLV = ?, SOGIOLAM = ?, MANV = ? WHERE MACHAMCONG = ?";
        return jdbcTemplate.update(sql, cc.getNgayLV(), cc.getSoGioLam(), cc.getMaNV(), cc.getMaChamCong());
    }

    public int delete(String maChamCong) {
        String sql = "DELETE FROM CHAMCONG WHERE MACHAMCONG = ?";
        return jdbcTemplate.update(sql, maChamCong);
    }
}
