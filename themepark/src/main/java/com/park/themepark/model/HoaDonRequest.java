package com.park.themepark.model;

import java.util.List;

public class HoaDonRequest {
    private String tenKhachHang;
    private String hinhThucThanhToan;
    private double thanhTien;
    private double tienGiamGia;
    private double tongTien;
    private String maGiamGia;

    private List<ChiTietHoaDonRequest> chiTietHoaDon;

    public HoaDonRequest() {}

    // Getter - Setter
    public String getTenKhachHang() {
        return tenKhachHang;
    }
    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getHinhThucThanhToan() {
        return hinhThucThanhToan;
    }
    public void setHinhThucThanhToan(String hinhThucThanhToan) {
        this.hinhThucThanhToan = hinhThucThanhToan;
    }

    public double getThanhTien() {
        return thanhTien;
    }
    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    public double getTienGiamGia() {
        return tienGiamGia;
    }
    public void setTienGiamGia(double tienGiamGia) {
        this.tienGiamGia = tienGiamGia;
    }

    public double getTongTien() {
        return tongTien;
    }
    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public String getMaGiamGia() {
        return maGiamGia;
    }
    public void setMaGiamGia(String maGiamGia) {
        this.maGiamGia = maGiamGia;
    }

    public List<ChiTietHoaDonRequest> getChiTietHoaDon() {
        return chiTietHoaDon;
    }
    public void setChiTietHoaDon(List<ChiTietHoaDonRequest> chiTietHoaDon) {
        this.chiTietHoaDon = chiTietHoaDon;
    }
}