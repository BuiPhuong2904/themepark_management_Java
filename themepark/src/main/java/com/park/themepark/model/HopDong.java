package com.park.themepark.model;

import java.util.Date;

public class HopDong {
    private String maHDong;
    private String tenHDong;
    private Date ngayBD;
    private Date ngayKT;
    private double tongTien;
    private String maKH;
    private String maNV;
    private String maKV;
    private String ghiChu;

    public HopDong() {}

    public HopDong(String maHDong, String tenHDong, Date ngayBD, Date ngayKT, double tongTien,
                   String maKH, String maNV, String maKV, String ghiChu) {
        this.maHDong = maHDong;
        this.tenHDong = tenHDong;
        this.ngayBD = ngayBD;
        this.ngayKT = ngayKT;
        this.tongTien = tongTien;
        this.maKH = maKH;
        this.maNV = maNV;
        this.maKV = maKV;
        this.ghiChu = ghiChu;
    }

    // Getter + Setter
    public String getMaHDong() {
        return maHDong;
    }

    public void setMaHDong(String maHDong) {
        this.maHDong = maHDong;
    }

    public String getTenHDong() {
        return tenHDong;
    }

    public void setTenHDong(String tenHDong) {
        this.tenHDong = tenHDong;
    }

    public Date getNgayBD() {
        return ngayBD;
    }

    public void setNgayBD(Date ngayBD) {
        this.ngayBD = ngayBD;
    }

    public Date getNgayKT() {
        return ngayKT;
    }

    public void setNgayKT(Date ngayKT) {
        this.ngayKT = ngayKT;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getMaKV() {
        return maKV;
    }

    public void setMaKV(String maKV) {
        this.maKV = maKV;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}
