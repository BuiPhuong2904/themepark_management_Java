package com.park.themepark.model;

// import java.sql.Date;

public class Ve {
    private String maVe;
    private String tenVe;
    private String loaiVe;
    private String moTa;
    private String hinhAnh;
    private Double giaVe;
    // private Date ngaySuDung;
    private String trangThai;
    private String maKTC;

    public Ve() {}

    public Ve(String maVe, String tenVe, String loaiVe, String moTa, String hinhAnh, Double giaVe, String trangThai, String maKTC) {
        this.maVe = maVe;
        this.tenVe = tenVe;
        this.loaiVe = loaiVe;
        this.moTa = moTa;
        this.hinhAnh = hinhAnh;
        this.giaVe = giaVe;
        // this.ngaySuDung = ngaySuDung;
        this.trangThai = trangThai;
        this.maKTC = maKTC;
    }

    // Getter + Setter
    public String getMaVe() { return maVe; }
    public void setMaVe(String maVe) { this.maVe = maVe; }

    public String getTenVe() { return tenVe; }
    public void setTenVe(String tenVe) { this.tenVe = tenVe; }

    public String getLoaiVe() { return loaiVe; }
    public void setLoaiVe(String loaiVe) { this.loaiVe = loaiVe; }

    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }

    public String getHinhAnh() { return hinhAnh; }
    public void setHinhAnh(String hinhAnh) { this.hinhAnh = hinhAnh; }

    public Double getGiaVe() { return giaVe; }
    public void setGiaVe(Double giaVe) { this.giaVe = giaVe; }

    // public Date getNgaySuDung() { return ngaySuDung; }
    // public void setNgaySuDung(Date ngaySuDung) { this.ngaySuDung = ngaySuDung; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    public String getMaKTC() { return maKTC; }
    public void setMaKTC(String maKTC) { this.maKTC = maKTC; }

}