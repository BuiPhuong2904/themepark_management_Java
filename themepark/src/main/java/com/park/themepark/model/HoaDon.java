package com.park.themepark.model;

import java.util.Date;
import java.util.List;

public class HoaDon {
    private String maHD;
    private Double tongTienTruoc;
    private Double tienGiamGia;
    private Double tongTienSau;
    private String hinhThucTT;
    private Date ngayLap;
    private String maKH;
    private String maNV;
    private String maKM;

    private KhachHang khachHang;

    private List<ChiTiet_HD> CTHDList;

    public HoaDon() {}

    public HoaDon(String maHD, Double tongTienTruoc, Double tienGiamGia, Double tongTienSau, 
                  String hinhThucTT, Date ngayLap, String maKH, String maNV, String maKM, 
                  List<ChiTiet_HD> CTHDList) {
        this.maHD = maHD;
        this.tongTienTruoc = tongTienTruoc;
        this.tienGiamGia = tienGiamGia;
        this.tongTienSau = tongTienSau;
        this.hinhThucTT = hinhThucTT;
        this.ngayLap = ngayLap;
        this.maKH = maKH;
        this.maNV = maNV;
        this.maKM = maKM;
        this.CTHDList = CTHDList;
    }

    // Getters + Setters
    public String getMaHD() { return maHD; }
    public void setMaHD(String maHD) { this.maHD = maHD; }

    public Double getTongTienTruoc() { return tongTienTruoc; }
    public void setTongTienTruoc(Double tongTienTruoc) { this.tongTienTruoc = tongTienTruoc; }

    public Double getTienGiamGia() { return tienGiamGia; }
    public void setTienGiamGia(Double tienGiamGia) { this.tienGiamGia = tienGiamGia; }

    public Double getTongTienSau() { return tongTienSau; }
    public void setTongTienSau(Double tongTienSau) { this.tongTienSau = tongTienSau; }

    public String getHinhThucTT() { return hinhThucTT; }
    public void setHinhThucTT(String hinhThucTT) { this.hinhThucTT = hinhThucTT; }

    public Date getNgayLap() { return ngayLap; }
    public void setNgayLap(Date ngayLap) { this.ngayLap = ngayLap;}

    public String getMaKH() { return maKH; }
    public void setMaKH(String maKH) { this.maKH = maKH; }

    public String getMaNV() { return maNV; }
    public void setMaNV(String maNV) { this.maNV = maNV; }

    public String getMaKM() { return maKM; }
    public void setMaKM(String maKM) { this.maKM = maKM; }

    public List<ChiTiet_HD> getCTHDList() { return CTHDList;}
    public void setCTHDList(List<ChiTiet_HD> CTHDList) { this.CTHDList = CTHDList; }

    
    public KhachHang getKhachHang() {
        return khachHang;
    }
    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

}
