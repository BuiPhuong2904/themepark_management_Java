package com.park.themepark.model;

public class KhuTroChoi {
    private String maKTC;
    private String tenKTC;
    private String moTa;
    private String trangThai;

    public KhuTroChoi() {}

    public KhuTroChoi(String maKTC, String tenKTC, String moTa, String trangThai) {
        this.maKTC = maKTC;
        this.tenKTC = tenKTC;
        this.moTa = moTa;
        this.trangThai = trangThai;
    }

    // Getter + Setter
    public String getMaKTC() { return maKTC; }
    public void setMaKTC(String maKTC) { this.maKTC = maKTC; }

    public String getTenKTC() { return tenKTC; }
    public void setTenKTC(String tenKTC) { this.tenKTC = tenKTC; }

    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
}