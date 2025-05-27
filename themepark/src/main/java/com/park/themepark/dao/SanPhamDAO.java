package com.park.themepark.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.park.themepark.model.SanPham;

@Repository
public class SanPhamDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SanPham mapRow(ResultSet rs, int rowNum) throws SQLException {
        SanPham sp = new SanPham();
        sp.setMaSP(rs.getString("MASP"));
        sp.setTenSP(rs.getString("TENSP"));
        sp.setLoaiSP(rs.getString("LOAISP"));
        sp.setTongSL(rs.getInt("TONGSL"));
        sp.setDonViTinh(rs.getString("DONVITINH"));
        sp.setGiaNhap(rs.getDouble("GIANHAP"));
        sp.setGhiChu(rs.getString("GHICHU"));
        sp.setTrangThai(rs.getString("TRANGTHAI"));
        return sp;
    }

    public List<SanPham> findAll() {
        return jdbcTemplate.query("SELECT * FROM SANPHAM", this::mapRow);
    }

    public SanPham findById(String maSP) {
        return jdbcTemplate.queryForObject("SELECT * FROM SANPHAM WHERE MASP = ?", this::mapRow, maSP);
    }

    public int insert(SanPham sp) {
        String sql = "INSERT INTO SANPHAM VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
            sp.getMaSP(), sp.getTenSP(), sp.getLoaiSP(), sp.getTongSL(),
            sp.getDonViTinh(), sp.getGiaNhap(), sp.getGhiChu(), sp.getTrangThai());
    }

    public int update(SanPham sp) {
        String sql = "UPDATE SANPHAM SET TENSP=?, LOAISP=?, TONGSL=?, DONVITINH=?, GIANHAP=?, GHICHU=?, TRANGTHAI=? WHERE MASP=?";
        return jdbcTemplate.update(sql,
            sp.getTenSP(), sp.getLoaiSP(), sp.getTongSL(), sp.getDonViTinh(),
            sp.getGiaNhap(), sp.getGhiChu(), sp.getTrangThai(), sp.getMaSP());
    }

    public int delete(String maSP) {
        return jdbcTemplate.update("DELETE FROM SANPHAM WHERE MASP = ?", maSP);
    }
}