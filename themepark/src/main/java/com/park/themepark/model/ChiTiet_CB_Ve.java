package com.park.themepark.model;

public class ChiTiet_CB_Ve {
    private String maCB;
    private String maVe;

    public ChiTiet_CB_Ve() {}

    public ChiTiet_CB_Ve(String maCB, String maVe) {
        this.maCB = maCB;
        this.maVe = maVe;
    }

    // Getter + Setter
    public String getMaCB() { return maCB; }
    public void setMaCB(String maCB) { this.maCB = maCB; }

    public String getMaVe() { return maVe; }
    public void setMaVe(String maVe) { this.maVe = maVe; }
}