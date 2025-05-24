package com.park.themepark.model;

public class Combo_Ve {
    private String maCB;
    private String tenCB;
    private String loaiCB;
    private String hinhAnh;
    private double giaCB;
    private String moTa;
    private String trangThai;

    public Combo_Ve() {}

    public Combo_Ve(String maCB, String tenCB, String loaiCB, String hinhAnh, double giaCB, String moTa, String trangThai) {
        this.maCB = maCB;
        this.tenCB = tenCB;
        this.loaiCB = loaiCB;
        this.hinhAnh = hinhAnh;
        this.giaCB = giaCB;
        this.moTa = moTa;
        this.trangThai = trangThai;
    }

    // Getter + Setter
    public String getMaCB() { return maCB; }
    public void setMaCB(String maCB) { this.maCB = maCB; }

    public String getTenCB() { return tenCB; }
    public void setTenCB(String tenCB) { this.tenCB = tenCB; }

    public String getLoaiCB() { return loaiCB; }
    public void setLoaiCB(String loaiCB) { this.loaiCB = loaiCB; }

    public String getHinhAnh() { return hinhAnh; }
    public void setHinhAnh(String hinhAnh) { this.hinhAnh = hinhAnh; }

    public double getGiaCB() { return giaCB; }
    public void setGiaCB(double giaCB) { this.giaCB = giaCB; }

    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

}