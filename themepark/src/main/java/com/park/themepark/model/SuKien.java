package com.park.themepark.model;

import java.sql.Date;

public class SuKien {
    private String mask;
    private String tensk;
    private String loaisk;
    private Date ngaybd;
    private Date ngaykt;
    private String mota;
    private String manv;

    public SuKien() {}

    public SuKien(String mask, String tensk, String loaisk, Date ngaybd, Date ngaykt, String mota, String manv) {
        this.mask = mask;
        this.tensk = tensk;
        this.loaisk = loaisk;
        this.ngaybd = ngaybd;
        this.ngaykt = ngaykt;
        this.mota = mota;
        this.manv = manv;
    }
    
    // Getter + Setter
    public String getMask() { return mask; }
    public void setMask(String mask) { this.mask = mask; }

    public String getTensk() { return tensk; }
    public void setTensk(String tensk) { this.tensk = tensk; }

    public String getLoaisk() { return loaisk; }
    public void setLoaisk(String loaisk) { this.loaisk = loaisk; }

    public Date getNgaybd() { return ngaybd; }
    public void setNgaybd(Date ngaybd) { this.ngaybd = ngaybd; }

    public Date getNgaykt() { return ngaykt; }
    public void setNgaykt(Date ngaykt) { this.ngaykt = ngaykt; }

    public String getMota() { return mota; }
    public void setMota(String mota) { this.mota = mota; }

    public String getManv() { return manv; }
    public void setManv(String manv) { this.manv = manv; }
}
