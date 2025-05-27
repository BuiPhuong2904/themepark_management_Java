package com.park.themepark.model;

import java.util.Date;

public class BaoTri {
    private String maBT;
    private String maKTC;
    private Date ngayBT;
    private String nguoiPT;
    private String noiDung;
    private Double chiPhi;
    private String trangThai;
    private String ghiChu;

    // Constructors
    public BaoTri() {
    }

    public BaoTri(String maBT, String maKTC, Date ngayBT, String nguoiPT, String noiDung,
                  Double chiPhi, String trangThai, String ghiChu) {
        this.maBT = maBT;
        this.maKTC = maKTC;
        this.ngayBT = ngayBT;
        this.nguoiPT = nguoiPT;
        this.noiDung = noiDung;
        this.chiPhi = chiPhi;
        this.trangThai = trangThai;
        this.ghiChu = ghiChu;
    }

    // Getters and Setters
    public String getMaBT() {
        return maBT;
    }

    public void setMaBT(String maBT) {
        this.maBT = maBT;
    }

    public String getMaKTC() {
        return maKTC;
    }

    public void setMaKTC(String maKTC) {
        this.maKTC = maKTC;
    }

    public Date getNgayBT() {
        return ngayBT;
    }

    public void setNgayBT(Date ngayBT) {
        this.ngayBT = ngayBT;
    }

    public String getNguoiPT() {
        return nguoiPT;
    }

    public void setNguoiPT(String nguoiPT) {
        this.nguoiPT = nguoiPT;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public Double getChiPhi() {
        return chiPhi;
    }

    public void setChiPhi(Double chiPhi) {
        this.chiPhi = chiPhi;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

}