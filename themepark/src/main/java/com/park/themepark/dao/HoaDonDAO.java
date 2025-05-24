package com.park.themepark.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.park.themepark.model.HoaDon;
import com.park.themepark.model.KhachHang;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class HoaDonDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private HoaDon mapRow(ResultSet rs, int rowNum) throws SQLException {
        HoaDon hd = new HoaDon();
        hd.setMaHD(rs.getString("MAHD"));
        hd.setTongTienTruoc(rs.getDouble("TONGTIENTRUOC"));
        hd.setTienGiamGia(rs.getDouble("TIENGIAMGIA"));
        hd.setTongTienSau(rs.getDouble("TONGTIENSAU"));
        hd.setHinhThucTT(rs.getString("HINHTHUCTT"));
        hd.setNgayLap(rs.getDate("NGAYLAP"));
        hd.setMaKH(rs.getString("MAKH"));
        hd.setMaNV(rs.getString("MANV"));
        hd.setMaKM(rs.getString("MAKM"));
        return hd;
    }

    public List<HoaDon> findAll() {
        String sql = "SELECT * FROM HOADON";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    public HoaDon findById(String maHD) {
        String sql = "SELECT * FROM HOADON WHERE MAHD = ?";
        return jdbcTemplate.queryForObject(sql, this::mapRow, maHD);
    }

    public int insert(HoaDon hd) {
        String sql = "INSERT INTO HOADON (TONGTIENTRUOC, TIENGIAMGIA, TONGTIENSAU, HINHTHUCTT, NGAYLAP, MAKH, MANV, MAKM) " +
                     "VALUES (?, ?, ?, ?, SYSDATE, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                hd.getTongTienTruoc(), hd.getTienGiamGia(), hd.getTongTienSau(),
                hd.getHinhThucTT(), hd.getMaKH(), hd.getMaNV(), hd.getMaKM());
    }

    public String getLatestMaHDByCustomer(String maKH) {
        String sql = "SELECT MAHD FROM HOADON WHERE MAKH = ? ORDER BY NGAYLAP DESC FETCH FIRST 1 ROWS ONLY";
        return jdbcTemplate.queryForObject(sql, String.class, maKH);
    }

    public String getMaHDCuoiCungTheoKH(String maKH) {
        String sql = "SELECT MAHD FROM HOADON WHERE MAKH = ? ORDER BY NGAYLAP DESC FETCH FIRST 1 ROWS ONLY";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{maKH}, String.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // Thêm update, delete nếu cần
    public HoaDon getHoaDonById(String maHD) {
        String sql = "SELECT hd.MAHD, hd.TONGTIENTRUOC, hd.TIENGIAMGIA, hd.TONGTIENSAU, hd.HINHTHUCTT, hd.NGAYLAP, " +
                    "hd.MAKH, hd.MANV, hd.MAKM, " +
                    "kh.HOTEN, kh.NGAYSINH, kh.GIOITINH, kh.SDT, kh.MATK " +
                    "FROM HOADON hd " +
                    "LEFT JOIN KHACHHANG kh ON hd.MAKH = kh.MAKH " +
                    "WHERE hd.MAHD = ?";

        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            HoaDon hd = new HoaDon();
            hd.setMaHD(rs.getString("MAHD"));
            hd.setTongTienTruoc(rs.getDouble("TONGTIENTRUOC"));
            hd.setTienGiamGia(rs.getDouble("TIENGIAMGIA"));
            hd.setTongTienSau(rs.getDouble("TONGTIENSAU"));
            hd.setHinhThucTT(rs.getString("HINHTHUCTT"));
            hd.setNgayLap(rs.getDate("NGAYLAP"));
            hd.setMaKH(rs.getString("MAKH"));
            hd.setMaNV(rs.getString("MANV"));
            hd.setMaKM(rs.getString("MAKM"));

            // Tạo đối tượng KhachHang gắn vào HoaDon
            KhachHang kh = new KhachHang();
            kh.setMakh(rs.getString("MAKH"));
            kh.setHoten(rs.getString("HOTEN"));
            kh.setNgaysinh(rs.getDate("NGAYSINH"));
            kh.setGioitinh(rs.getString("GIOITINH"));
            kh.setSdt(rs.getString("SDT"));
            kh.setMatk(rs.getString("MATK"));

            hd.setKhachHang(kh);  // giả sử HoaDon có thuộc tính KhachHang và setter tương ứng

            return hd;
        }, maHD);
    }
}