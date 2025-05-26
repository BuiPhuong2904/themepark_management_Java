package com.park.themepark.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.park.themepark.model.SuKien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SuKienDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SuKien mapRow(ResultSet rs, int rowNum) throws SQLException {
        SuKien sk = new SuKien();
        sk.setMask(rs.getString("MASK"));
        sk.setTensk(rs.getString("TENSK"));
        sk.setLoaisk(rs.getString("LOAISK"));
        sk.setNgaybd(rs.getDate("NGAYBD"));
        sk.setNgaykt(rs.getDate("NGAYKT"));
        sk.setMota(rs.getString("MOTA"));
        sk.setManv(rs.getString("MANV"));
        return sk;
    }

    public List<SuKien> findAll() {
        String sql = "SELECT * FROM SUKIEN";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    public SuKien findById(String mask) {
        String sql = "SELECT * FROM SUKIEN WHERE MASK = ?";
        return jdbcTemplate.queryForObject(sql, this::mapRow, mask);
    }

    public int insert(SuKien sk) {
        String sql = "INSERT INTO SUKIEN (MASK, TENSK, LOAISK, NGAYBD, NGAYKT, MOTA, MANV) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                sk.getMask(), sk.getTensk(), sk.getLoaisk(), sk.getNgaybd(),
                sk.getNgaykt(), sk.getMota(), sk.getManv());
    }

    public int update(SuKien sk) {
        String sql = "UPDATE SUKIEN SET TENSK = ?, LOAISK = ?, NGAYBD = ?, NGAYKT = ?, MOTA = ?, MANV = ? WHERE MASK = ?";
        return jdbcTemplate.update(sql,
                sk.getTensk(), sk.getLoaisk(), sk.getNgaybd(),
                sk.getNgaykt(), sk.getMota(), sk.getManv(), sk.getMask());
    }

    public int delete(String mask) {
        String sql = "DELETE FROM SUKIEN WHERE MASK = ?";
        return jdbcTemplate.update(sql, mask);
    }
}
