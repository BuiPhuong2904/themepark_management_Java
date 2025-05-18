package com.park.themepark.service;

import com.park.themepark.dao.TaiKhoanDAO;
import com.park.themepark.model.TaiKhoan;

import org.springframework.stereotype.Service;

@Service
public class TaiKhoanService {

    private static TaiKhoanDAO taiKhoanDAO;

    public TaiKhoanService(TaiKhoanDAO taiKhoanDAO) {
        TaiKhoanService.taiKhoanDAO = taiKhoanDAO;
    }

    public boolean emailDaTonTai(String email) {
        return taiKhoanDAO.emailDaTonTai(email);
    }

    public TaiKhoan timTheoEmail(String email) {
        return taiKhoanDAO.timTheoEmail(email);
    }

    public String luuTaiKhoan(TaiKhoan tk) {
        return taiKhoanDAO.luuTaiKhoan(tk);
    }

    public boolean dangNhap(String email, String matKhau) {
        return taiKhoanDAO.dangNhap(email, matKhau);
    }

    public static TaiKhoan timTheoMaTK(String maTK) {
        return taiKhoanDAO.timTheoMaTK(maTK);
    }
}