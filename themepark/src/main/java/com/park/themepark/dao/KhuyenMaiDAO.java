package com.park.themepark.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.park.themepark.model.KhuyenMai;

@Repository
public class KhuyenMaiDAO {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private KhuyenMai mapRow(ResultSet rs, int rowNum) throws SQLException {
        KhuyenMai km = new KhuyenMai();
        km.setMaKM(rs.getString("MAKM"));
        km.setTenKM(rs.getString("TENKM"));
        km.setLoaiKM(rs.getString("LOAIKM"));
        km.setGiaTriGiam(rs.getDouble("GIATRIGIAM"));
        km.setDieuKien(rs.getString("DIEUKIEN"));
        km.setNgayBD(rs.getDate("NGAYBD"));
        km.setNgayKT(rs.getDate("NGAYKT"));
        km.setTrangThai(rs.getString("TRANGTHAI"));
        return km;
    }

    public List<KhuyenMai> findAll() {
        String sql = "SELECT * FROM KHUYENMAI";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    public KhuyenMai findById(String maKM) {
        String sql = "SELECT * FROM KHUYENMAI WHERE MAKM = ?";
        return jdbcTemplate.queryForObject(sql, this::mapRow, maKM);
    }

    public int insert(KhuyenMai km) {
        String sql = "INSERT INTO KHUYENMAI (MAKM, TENKM, LOAIKM, GIATRIGIAM, DIEUKIEN, NGAYBD, NGAYKT, TRANGTHAI) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                km.getMaKM(), km.getTenKM(), km.getLoaiKM(), km.getGiaTriGiam(),
                km.getDieuKien(), km.getNgayBD(), km.getNgayKT(), km.getTrangThai());
    }

    public int update(KhuyenMai km) {
        String sql = "UPDATE KHUYENMAI SET TENKM = ?, LOAIKM = ?, GIATRIGIAM = ?, DIEUKIEN = ?, NGAYBD = ?, NGAYKT = ?, TRANGTHAI = ? WHERE MAKM = ?";
        return jdbcTemplate.update(sql,
                km.getTenKM(), km.getLoaiKM(), km.getGiaTriGiam(), km.getDieuKien(),
                km.getNgayBD(), km.getNgayKT(), km.getTrangThai(), km.getMaKM());
    }

    public int delete(String maKM) {
        String sql = "DELETE FROM KHUYENMAI WHERE MAKM = ?";
        return jdbcTemplate.update(sql, maKM);
    }
}