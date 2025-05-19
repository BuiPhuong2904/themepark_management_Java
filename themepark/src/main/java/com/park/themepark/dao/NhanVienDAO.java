package com.park.themepark.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.park.themepark.model.NhanVien;

@Repository
public class NhanVienDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private NhanVien mapRow(ResultSet rs, int rowNum) throws SQLException {
        NhanVien nv = new NhanVien();
        nv.setMaNV(rs.getString("MANV"));
        nv.setHoTen(rs.getString("HOTEN"));
        nv.setNgaySinh(rs.getDate("NGSINH"));
        nv.setGioiTinh(rs.getString("GIOITINH"));
        nv.setSdt(rs.getString("SDT"));
        nv.setNgayVaoLam(rs.getDate("NGAYVL"));
        nv.setChucVu(rs.getString("CHUCVU"));
        nv.setLuong(rs.getDouble("LUONG"));
        nv.setMaQL(rs.getString("MAQL"));
        nv.setMaTK(rs.getString("MATK"));
        return nv;
    }

    public List<NhanVien> findAll() {
        String sql = "SELECT * FROM NHANVIEN";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    public NhanVien findById(String maNV) {
        String sql = "SELECT * FROM NHANVIEN WHERE MANV = ?";
        return jdbcTemplate.queryForObject(sql, this::mapRow, maNV);
    }

    public int insert(NhanVien nv) {
        String sql = "INSERT INTO NHANVIEN (MANV, HOTEN, NGSINH, GIOITINH, SDT, NGAYVL, CHUCVU, LUONG, MAQL, MATK) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                nv.getMaNV(), nv.getHoTen(), nv.getNgaySinh(), nv.getGioiTinh(), nv.getSdt(),
                nv.getNgayVaoLam(), nv.getChucVu(), nv.getLuong(), nv.getMaQL(), nv.getMaTK());
    }

    public int update(NhanVien nv) {
        String sql = "UPDATE NHANVIEN SET HOTEN = ?, NGSINH = ?, GIOITINH = ?, SDT = ?, NGAYVL = ?, CHUCVU = ?, LUONG = ?, MAQL = ?, MATK = ? WHERE MANV = ?";
        return jdbcTemplate.update(sql,
                nv.getHoTen(), nv.getNgaySinh(), nv.getGioiTinh(), nv.getSdt(),
                nv.getNgayVaoLam(), nv.getChucVu(), nv.getLuong(), nv.getMaQL(), nv.getMaTK(), nv.getMaNV());
    }

    public int delete(String maNV) {
        String sql = "DELETE FROM NHANVIEN WHERE MANV = ?";
        return jdbcTemplate.update(sql, maNV);
    }
}