package com.park.themepark.model;

public class ChiTietHoaDonRequest {
    private String tenSanPham;
    private double donGia;
    private int soLuong;
    private String loai; // ví dụ: "combo" hoặc "ve"
    private String maLoai;

    // Constructor không tham số
    public ChiTietHoaDonRequest() {}

    // Getter - Setter
    public String getTenSanPham() {
        return tenSanPham;
    }
    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public double getDonGia() {
        return donGia;
    }
    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public int getSoLuong() {
        return soLuong;
    }
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getLoai() {
        return loai;
    }
    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getMaLoai() { return maLoai; }
    public void setMaLoai(String maLoai) { this.maLoai = maLoai; }
}