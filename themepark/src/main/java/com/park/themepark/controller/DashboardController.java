package com.park.themepark.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.park.themepark.dao.ChiTiet_HDDAO;
import com.park.themepark.dao.Combo_VeDAO;
import com.park.themepark.dao.HoaDonDAO;
import com.park.themepark.dao.KhuTroChoiDAO;
import com.park.themepark.dao.KhuyenMaiDAO;
import com.park.themepark.dao.VeDAO;
import com.park.themepark.dao.KhachHangDAO;
import com.park.themepark.model.ChiTiet_HD;
import com.park.themepark.model.Combo_Ve;
import com.park.themepark.model.HoaDon;
import com.park.themepark.model.KhachHang;
import com.park.themepark.dao.NhanVienDAO;
import com.park.themepark.model.KhuTroChoi;
import com.park.themepark.model.KhuyenMai;
import com.park.themepark.model.NhanVien;
import com.park.themepark.dao.SuKienDAO;
import com.park.themepark.model.SuKien;
import com.park.themepark.model.Ve;

@Controller
public class DashboardController {

    @Autowired
    private KhuTroChoiDAO khuTroChoiDAO;

    @Autowired
    private VeDAO veDAO;

    @Autowired
    private Combo_VeDAO comboVeDAO;

    @Autowired
    private KhuyenMaiDAO khuyenMaiDAO;

    @Autowired
    private KhachHangDAO khachHangDAO;

    @Autowired
    private NhanVienDAO nhanVienDAO;

    @Autowired
    private SuKienDAO suKienDAO;

    @Autowired
    private HoaDonDAO hoaDonDAO;

    @Autowired
    private ChiTiet_HDDAO ct_HDDAO;

    @GetMapping("/ho_so")
    public String hienThiHoSo() {
        return "e_ho_so"; 
    }

    @GetMapping("/ql_khachhang")
    public String hienThiDSKhachHang(Model model) {
        List<KhachHang> list = khachHangDAO.findAll();
        model.addAttribute("danhSachKH", list);
        return "e_ql_khachhang"; 
    }

    @GetMapping("/ql_nhanvien")
    public String hienThiDSNhanVien(Model model) {
        List<NhanVien> list = nhanVienDAO.findAll();
        model.addAttribute("danhSachNV", list);
        return "e_ql_nhanvien"; 
    }

    @GetMapping("/ql_sukien")
    public String hienThiDSSuKien(Model model) {
        List<SuKien> list = suKienDAO.findAll();
        model.addAttribute("danhSachSK", list);
        return "e_ql_sukien";
    }

    @GetMapping("/ds_tro_choi")
    public String hienThiDSTroChoi(Model model) {
        List<KhuTroChoi> list = khuTroChoiDAO.findAll();
        model.addAttribute("danhSachKTC", list);
        return "e_ds_trochoi"; 
    }

    @GetMapping("/ban_ve")
    public String hienThiBanVe(Model model) {
        List<Combo_Ve> list = comboVeDAO.findAll();
        model.addAttribute("danhSachCombo", list);

        List<Ve> dsVe = veDAO.findAll();
        model.addAttribute("danhSachVe", dsVe);
        return "e_banve"; 
    }

    @GetMapping("/ql_ve")
    public String hienThiQLVe(Model model) {
        List<Ve> list = veDAO.findAll();
        model.addAttribute("danhSachVe", list);
        return "e_ql_ve"; 
    }

    @GetMapping("/ql_combo")
    public String hienThiQLCombo(Model model) {
        List<Combo_Ve> list = comboVeDAO.findAll();
        model.addAttribute("danhSachCombo", list);
        return "e_ql_combo"; 
    }

    @GetMapping("/ql_hoadon")
    public String hienThiQLHoaDon(Model model) {
        List<HoaDon> list = hoaDonDAO.findAll();
        model.addAttribute("danhSachHoaDon", list);

        List<ChiTiet_HD> ctHD = ct_HDDAO.findAll();
        model.addAttribute("danhSachCTHD", ctHD);
        return "e_ql_hoadon"; 
    }

    @GetMapping("/ql_khuyenmai")
    public String hienThiQLKhuyenMai(Model model) {
        List<KhuyenMai> list = khuyenMaiDAO.findAll();
        model.addAttribute("danhSachKhuyenMai", list);
        System.out.println("Số khuyến mãi lấy được: " + list.size());
        return "e_ql_khuyenmai"; 
    }

    @GetMapping("/bao_tri")
    public String hienThiDSBaoTri(Model model) {
        return "e_baotri"; 
    }

    @GetMapping("/cham_cong")
    public String hienThiDSChamCong(Model model) {
        return "e_chamcong"; 
    }

    @GetMapping("/doanh_thu")
    public String hienThiDSDoanhThu(Model model) {
        return "e_doanhthu"; 
    }

    @GetMapping("/ds_khu_vuc")
    public String hienThiDSKhuVuc(Model model) {
        return "e_ds_khu_vuc"; 
    }

    @GetMapping("/ds_san_pham")
    public String hienThiDSSanPham(Model model) {
        return "e_ds_san_pham"; 
    }

    @GetMapping("/luot_kh")
    public String hienThiDSLuotKH(Model model) {
        return "e_luot_kh"; 
    }

    @GetMapping("/ql_hop_dong")
    public String hienThiDSQLHopDong(Model model) {
        return "e_ql_hop_dong"; 
    }

    @GetMapping("/ql_nhap_xuat")
    public String hienThiDSQLNhap(Model model) {
        return "e_ql_nhap_xuat"; 
    }

}
