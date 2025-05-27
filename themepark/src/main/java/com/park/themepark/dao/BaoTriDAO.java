package com.park.themepark.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.park.themepark.model.BaoTri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BaoTriDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private BaoTri mapRow(ResultSet rs, int rowNum) throws SQLException {
        BaoTri bt = new BaoTri();
        bt.setMaBT(rs.getString("MABT"));
        bt.setMaKTC(rs.getString("MAKTC"));
        bt.setNgayBT(rs.getDate("NGAYBT"));
        bt.setNguoiPT(rs.getString("NGUOIPT"));
        bt.setNoiDung(rs.getString("NOIDUNG"));
        bt.setChiPhi(rs.getDouble("CHIPHI"));
        bt.setTrangThai(rs.getString("TRANGTHAI"));
        bt.setGhiChu(rs.getString("GHICHU"));
        return bt;
    }

    public List<BaoTri> findAll() {
        return jdbcTemplate.query("SELECT * FROM LICH_BAOTRI", this::mapRow);
    }

    public BaoTri findById(String maBT) {
        return jdbcTemplate.queryForObject("SELECT * FROM LICH_BAOTRI WHERE MABT = ?", this::mapRow, maBT);
    }

    public int insert(BaoTri bt) {
        String sql = "INSERT INTO LICH_BAOTRI (MABT, MAKTC, NGAYBT, NGUOIPT, NOIDUNG, CHIPHI, TRANGTHAI, GHICHU) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                bt.getMaBT(), bt.getMaKTC(), bt.getNgayBT(), bt.getNguoiPT(),
                bt.getNoiDung(), bt.getChiPhi(), bt.getTrangThai(), bt.getGhiChu());
    }

    public int update(BaoTri bt) {
        String sql = "UPDATE LICH_BAOTRI SET MAKTC=?, NGAYBT=?, NGUOIPT=?, NOIDUNG=?, CHIPHI=?, TRANGTHAI=?, GHICHU=? WHERE MABT=?";
        return jdbcTemplate.update(sql,
                bt.getMaKTC(), bt.getNgayBT(), bt.getNguoiPT(), bt.getNoiDung(),
                bt.getChiPhi(), bt.getTrangThai(), bt.getGhiChu(), bt.getMaBT());
    }

    public int delete(String maBT) {
        return jdbcTemplate.update("DELETE FROM LICH_BAOTRI WHERE MABT = ?", maBT);
    }
}