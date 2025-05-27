package com.park.themepark.model;

public class KhuVuc {
    private String maKV;
    private String tenKV;
    private String moTa;
    private Double giaThue;
    private String trangThai;

    public KhuVuc() {}

    public KhuVuc(String maKV, String tenKV, String moTa, Double giaThue, String trangThai) {
        this.maKV = maKV;
        this.tenKV = tenKV;
        this.moTa = moTa;
        this.giaThue = giaThue;
        this.trangThai = trangThai;
    }

    // Getter + Setter
    public String getMaKV() { return maKV; }
    public void setMaKV(String maKV) { this.maKV = maKV; }

    public String getTenKV() { return tenKV; }
    public void setTenKV(String tenKV) { this.tenKV = tenKV; }

    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }

    public Double getGiaThue() { return giaThue; }
    public void setGiaThue(Double giaThue) { this.giaThue = giaThue; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
}
