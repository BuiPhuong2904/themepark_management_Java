package com.park.themepark.dao;

import com.park.themepark.model.TaiKhoan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TaiKhoanDAO {

    private final JdbcTemplate jdbcTemplate;

    public TaiKhoanDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<TaiKhoan> rowMapper = new RowMapper<>() {
        @Override
        public TaiKhoan mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
            TaiKhoan tk = new TaiKhoan();
            tk.setMaTK(rs.getString("MATK"));
            tk.setEmail(rs.getString("EMAIL"));
            tk.setMatKhau(rs.getString("MATKHAU"));
            tk.setLoaiTK(rs.getString("LOAITK"));
            tk.setTrangThai(rs.getString("TRANGTHAI"));
            return tk;
        }
    };

    public boolean emailDaTonTai(String email) {
        String sql = "SELECT COUNT(*) FROM TAIKHOAN WHERE EMAIL = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count != null && count > 0;
    }

    public TaiKhoan timTheoEmail(String email) {
        String sql = "SELECT * FROM TAIKHOAN WHERE EMAIL = ?";
        List<TaiKhoan> list = jdbcTemplate.query(sql, rowMapper, email);
        return list.isEmpty() ? null : list.get(0);
    }

    public String luuTaiKhoan(TaiKhoan tk) {
        String sql = "INSERT INTO TAIKHOAN (EMAIL, MATKHAU, LOAITK, TRANGTHAI) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, tk.getEmail(), tk.getMatKhau(), tk.getLoaiTK(), tk.getTrangThai());

        String matk = jdbcTemplate.queryForObject(
            "SELECT MATK FROM TAIKHOAN WHERE EMAIL = ?",
            new Object[]{tk.getEmail()},
            String.class
        );
        return matk;
    }

    public boolean dangNhap(String email, String matKhau) {
        String sql = "SELECT COUNT(*) FROM TAIKHOAN WHERE EMAIL = ? AND MATKHAU = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email, matKhau);
        return count != null && count > 0;
    }

    public TaiKhoan timTheoMaTK(String maTK) {
        String sql = "SELECT * FROM TAIKHOAN WHERE MATK = ?";
        List<TaiKhoan> list = jdbcTemplate.query(sql, rowMapper, maTK);
        return list.isEmpty() ? null : list.get(0);
    }

}