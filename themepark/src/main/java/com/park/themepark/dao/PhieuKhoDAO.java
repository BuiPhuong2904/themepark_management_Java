package com.park.themepark.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.park.themepark.model.PhieuKho;

@Repository
public class PhieuKhoDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private PhieuKho mapRow(ResultSet rs, int rowNum) throws SQLException {
        PhieuKho pk = new PhieuKho();
        pk.setMaPhieu(rs.getString("MAPHIEU"));
        pk.setNgayGiaoDich(rs.getDate("NGAYGIAODICH"));
        pk.setLoaiPhieu(rs.getString("LOAIPHIEU"));
        pk.setMaNV(rs.getString("MANV"));
        pk.setGhiChu(rs.getString("GHICHU"));
        return pk;
    }

    public List<PhieuKho> findAll() {
        return jdbcTemplate.query("SELECT * FROM PHIEUKHO", this::mapRow);
    }

    public int insert(PhieuKho pk) {
        String sql = "INSERT INTO PHIEUKHO VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
            pk.getMaPhieu(), pk.getNgayGiaoDich(), pk.getLoaiPhieu(),
            pk.getMaNV(), pk.getGhiChu());
    }

    public PhieuKho findById(String maPhieu) {
        String sql = "SELECT * FROM PHIEUKHO WHERE MAPHIEU = ?";
        return jdbcTemplate.queryForObject(sql, this::mapRow, maPhieu);
    }

}