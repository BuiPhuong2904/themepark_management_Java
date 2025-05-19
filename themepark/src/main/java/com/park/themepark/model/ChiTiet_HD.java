package com.park.themepark.model;

public class ChiTiet_HD {
    private String maHD;
    private String loai;
    private String maLoai;
    private int soLuong;
    private Double thanhTien;
    
    public ChiTiet_HD() {}

    public ChiTiet_HD(String maHD, String loai, String maLoai, int soLuong, Double thanhTien) {
        this.maHD = maHD;
        this.loai = loai;
        this.maLoai = maLoai;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
    }

    // Getters and Setters
    public String getMaHD() { return maHD; }
    public void setMaHD(String maHD) { this.maHD = maHD; }

    public String getLoai() { return loai; }
    public void setLoai(String loai) { this.loai = loai; }

    public String getMaLoai() { return maLoai; }
    public void setMaLoai(String maLoai) { this.maLoai = maLoai; }

    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }
    
    public Double getThanhTien() { return thanhTien; }
    public void setThanhTien(Double thanhTien) { this.thanhTien = thanhTien; }
}
