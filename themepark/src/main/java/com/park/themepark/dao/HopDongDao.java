package com.park.themepark.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.park.themepark.model.HopDong;

@Repository
public class HopDongDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private HopDong mapRow(ResultSet rs, int rowNum) throws SQLException {
        HopDong hd = new HopDong();
        hd.setMaHDong(rs.getString("MAHDONG"));
        hd.setTenHDong(rs.getString("TENHDONG"));
        hd.setNgayBD(rs.getDate("NGAYBD"));
        hd.setNgayKT(rs.getDate("NGAYKT"));
        hd.setTongTien(rs.getDouble("TONGTIEN"));
        hd.setMaKH(rs.getString("MAKH"));
        hd.setMaNV(rs.getString("MANV"));
        hd.setMaKV(rs.getString("MAKV"));
        hd.setGhiChu(rs.getString("GHICHU"));
        return hd;
    }

    public List<HopDong> findAll() {
        String sql = "SELECT * FROM HOPDONG";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    public HopDong findById(String maHDong) {
        String sql = "SELECT * FROM HOPDONG WHERE MAHDONG = ?";
        return jdbcTemplate.queryForObject(sql, this::mapRow, maHDong);
    }

    public int insert(HopDong hd) {
        String sql = "INSERT INTO HOPDONG (MAHDONG, TENHDONG, NGAYBD, NGAYKT, TONGTIEN, MAKH, MANV, MAKV, GHICHU) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                hd.getMaHDong(), hd.getTenHDong(), new java.sql.Date(hd.getNgayBD().getTime()),
                new java.sql.Date(hd.getNgayKT().getTime()), hd.getTongTien(),
                hd.getMaKH(), hd.getMaNV(), hd.getMaKV(), hd.getGhiChu());
    }

    public int update(HopDong hd) {
        String sql = "UPDATE HOPDONG SET TENHDONG = ?, NGAYBD = ?, NGAYKT = ?, TONGTIEN = ?, " +
                     "MAKH = ?, MANV = ?, MAKV = ?, GHICHU = ? WHERE MAHDONG = ?";
        return jdbcTemplate.update(sql,
                hd.getTenHDong(), new java.sql.Date(hd.getNgayBD().getTime()),
                new java.sql.Date(hd.getNgayKT().getTime()), hd.getTongTien(),
                hd.getMaKH(), hd.getMaNV(), hd.getMaKV(), hd.getGhiChu(), hd.getMaHDong());
    }

    public int delete(String maHDong) {
        String sql = "DELETE FROM HOPDONG WHERE MAHDONG = ?";
        return jdbcTemplate.update(sql, maHDong);
    }
}
