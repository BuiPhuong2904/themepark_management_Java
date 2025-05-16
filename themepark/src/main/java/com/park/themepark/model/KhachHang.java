package com.park.themepark.model;

import java.util.Date;

public class KhachHang {
    private String makh;
    private String hoten;
    private Date ngaysinh;
    private String gioitinh;
    private String sdt;
    private String matk;

    public KhachHang() {}

    public KhachHang(String makh, String hoten, Date ngaysinh, String gioitinh, String sdt, String matk) {
        this.makh = makh;
        this.hoten = hoten;
        this.ngaysinh = ngaysinh;
        this.gioitinh = gioitinh;
        this.sdt = sdt;
        this.matk = matk;
    }

    // Getter + Setter
    public String getMakh() { return makh; }
    public void setMakh(String makh) { this.makh = makh; }

    public String getHoten() { return hoten; }
    public void setHoten(String hoten) { this.hoten = hoten; }

    public Date getNgaysinh() { return ngaysinh; }
    public void setNgaysinh(Date ngaysinh) { this.ngaysinh = ngaysinh; }

    public String getGioitinh() { return gioitinh; }
    public void setGioitinh(String gioitinh) { this.gioitinh = gioitinh; }

    public String getSdt() { return sdt; }
    public void setSdt(String sdt) { this.sdt = sdt; }

    public String getMatk() { return matk; }
    public void setMatk(String matk) { this.matk = matk; }
}
