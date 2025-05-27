package com.park.themepark.model;

import java.util.Date;

public class PhieuKho {
    private String maPhieu;
    private Date ngayGiaoDich;
    private String loaiPhieu; // 'NHAP' hoặc 'XUAT'
    private String maNV;
    private String ghiChu;

    public PhieuKho() {}

    public PhieuKho(String maPhieu, Date ngayGiaoDich, String loaiPhieu, String maNV, String ghiChu) {
        this.maPhieu = maPhieu;
        this.ngayGiaoDich = ngayGiaoDich;
        this.loaiPhieu = loaiPhieu;
        this.maNV = maNV;
        this.ghiChu = ghiChu;
    }

    public String getMaPhieu() { return maPhieu; }
    public void setMaPhieu(String maPhieu) { this.maPhieu = maPhieu; }

    public Date getNgayGiaoDich() { return ngayGiaoDich; }
    public void setNgayGiaoDich(Date ngayGiaoDich) { this.ngayGiaoDich = ngayGiaoDich; }

    public String getLoaiPhieu() { return loaiPhieu; }
    public void setLoaiPhieu(String loaiPhieu) { this.loaiPhieu = loaiPhieu; }

    public String getMaNV() { return maNV; }
    public void setMaNV(String maNV) { this.maNV = maNV; }

    public String getGhiChu() { return ghiChu; }
    public void setGhiChu(String ghiChu) { this.ghiChu = ghiChu; }
}