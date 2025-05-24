package com.park.themepark.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CartItemDTO {
    @JsonProperty("name")
    private String tenCB;

    @JsonProperty("price")
    private double giaCB;

    @JsonProperty("quantity")
    private int soLuong;

    public CartItemDTO() {}

    public CartItemDTO(String tenCB, double giaCB, int soLuong) {
        this.tenCB = tenCB;
        this.giaCB = giaCB;
        this.soLuong = soLuong;
    }

    public String getTenCB() { return tenCB; }
    public void setTenCB(String tenCB) { this.tenCB = tenCB; }

    public double getGiaCB() { return giaCB; }
    public void setGiaCB(double giaCB) { this.giaCB = giaCB; }

    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }
}