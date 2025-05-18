package com.park.themepark.service;

import com.park.themepark.model.KhachHang;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class KhachHangService {

    private final JdbcTemplate jdbcTemplate;

    public KhachHangService(JdbcTemplate jdbcTemplate) {
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
}