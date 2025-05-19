package com.park.themepark.model;

import java.sql.Date;

public class NhanVien {
    private String maNV;
    private String hoTen;
    private Date ngaySinh;
    private String gioiTinh;
    private String sdt;
    private Date ngayVaoLam;
    private String chucVu;
    private double luong;
    private String maQL;
    private String maTK;

    public NhanVien() {}

    public NhanVien(String maNV, String hoTen, Date ngaySinh, String gioiTinh, String sdt, Date ngayVaoLam, String chucVu, double luong, String maQL, String maTK) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.sdt = sdt;
        this.ngayVaoLam = ngayVaoLam;
        this.chucVu = chucVu;
        this.luong = luong;
        this.maQL = maQL;
        this.maTK = maTK;
    }

    // Getter + Setter
    public String getMaNV() { return maNV; }
    public void setMaNV(String maNV) { this.maNV = maNV; }

    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }

    public Date getNgaySinh() { return ngaySinh; }
    public void setNgaySinh(Date ngaySinh) { this.ngaySinh = ngaySinh; }

    public String getGioiTinh() { return gioiTinh; }
    public void setGioiTinh(String gioiTinh) { this.gioiTinh = gioiTinh; }

    public String getSdt() { return sdt; }
    public void setSdt(String sdt) { this.sdt = sdt; }

    public Date getNgayVaoLam() { return ngayVaoLam; }
    public void setNgayVaoLam(Date ngayVaoLam) { this.ngayVaoLam = ngayVaoLam; }

    public String getChucVu() { return chucVu; }
    public void setChucVu(String chucVu) { this.chucVu = chucVu; }

    public double getLuong() { return luong; }
    public void setLuong(double luong) { this.luong = luong; }

    public String getMaQL() { return maQL; }
    public void setMaQL(String maQL) { this.maQL = maQL; }

    public String getMaTK() { return maTK; }
    public void setMaTK(String maTK) { this.maTK = maTK; }
}
