package com.park.themepark.model;

public class SanPham {
    private String maSP;
    private String tenSP;
    private String loaiSP;
    private int tongSL;
    private String donViTinh;
    private double giaNhap;
    private String ghiChu;
    private String trangThai;

    public SanPham() {}

    public SanPham(String maSP, String tenSP, String loaiSP, int tongSL, String donViTinh,
                   double giaNhap, String ghiChu, String trangThai) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.loaiSP = loaiSP;
        this.tongSL = tongSL;
        this.donViTinh = donViTinh;
        this.giaNhap = giaNhap;
        this.ghiChu = ghiChu;
        this.trangThai = trangThai;
    }

    // Getters and Setters
    public String getMaSP() { return maSP; }
    public void setMaSP(String maSP) { this.maSP = maSP; }

    public String getTenSP() { return tenSP; }
    public void setTenSP(String tenSP) { this.tenSP = tenSP; }

    public String getLoaiSP() { return loaiSP; }
    public void setLoaiSP(String loaiSP) { this.loaiSP = loaiSP; }

    public int getTongSL() { return tongSL; }
    public void setTongSL(int tongSL) { this.tongSL = tongSL; }

    public String getDonViTinh() { return donViTinh; }
    public void setDonViTinh(String donViTinh) { this.donViTinh = donViTinh; }

    public double getGiaNhap() { return giaNhap; }
    public void setGiaNhap(double giaNhap) { this.giaNhap = giaNhap; }

    public String getGhiChu() { return ghiChu; }
    public void setGhiChu(String ghiChu) { this.ghiChu = ghiChu; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
}