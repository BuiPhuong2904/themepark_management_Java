package com.park.themepark.dao;

import com.park.themepark.model.KhachHang;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class KhachHangDAO {

    private final JdbcTemplate jdbcTemplate;

    public KhachHangDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void luuKhachHang(KhachHang kh) {
        String sql = "INSERT INTO KHACHHANG (HOTEN, NGAYSINH, GIOITINH, SDT, MATK) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, kh.getHoten(), kh.getNgaysinh(), kh.getGioitinh(), kh.getSdt(), kh.getMatk());
    }

    public String layMaKhachHangMoiNhat() {
        return jdbcTemplate.queryForObject("SELECT seq_khachhang.CURRVAL FROM dual", String.class);
    }

    public KhachHang timTheoTaiKhoanId(String matk) {
        String sql = "SELECT * FROM KHACHHANG WHERE MATK = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(KhachHang.class), matk);
        } catch (EmptyResultDataAccessException e) {
            return null; // không tìm thấy
        }
    }

    public String findMaKHByMaTK(String maTK) {
        String sql = "SELECT MAKH FROM KHACHHANG WHERE MATK = ?";
        try {
            return jdbcTemplate.queryForObject(sql, String.class, maTK);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public KhachHang timTheoMaKH(String maKH) {
        String sql = "SELECT * FROM KHACHHANG WHERE MAKH = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(KhachHang.class), maKH);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public String findMaKHByTenKhachHang(String tenKH) {
        if (tenKH == null || tenKH.trim().isEmpty()) {
            return null;  // Tên rỗng thì không cần tìm
        }

        String sql = "SELECT MAKH FROM KHACHHANG WHERE HOTEN = ?";
        try {
            return jdbcTemplate.queryForObject(sql, String.class, tenKH);
        } catch (EmptyResultDataAccessException e) {
            return null;  // Không tìm thấy => trả null
        }
    }

}