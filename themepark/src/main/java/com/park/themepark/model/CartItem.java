package com.park.themepark.model;

public class CartItem {
    private Combo_Ve combo;
    private int soLuong;

    public CartItem() {}

    public CartItem(Combo_Ve combo, int soLuong) {
        this.combo = combo;
        this.soLuong = soLuong;
    }

    public Combo_Ve getCombo() { return combo; }
    public void setCombo(Combo_Ve combo) { this.combo = combo; }

    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }  

    public double getThanhTien() {
        return combo.getGiaCB() * soLuong;
    }
}
