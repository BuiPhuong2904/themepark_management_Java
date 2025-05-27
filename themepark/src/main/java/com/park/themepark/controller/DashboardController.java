package com.park.themepark.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.park.themepark.dao.BaoTriDAO;
import com.park.themepark.dao.ChiTiet_HDDAO;
import com.park.themepark.dao.Combo_VeDAO;
import com.park.themepark.dao.HoaDonDAO;
import com.park.themepark.dao.KhuTroChoiDAO;
import com.park.themepark.dao.KhuyenMaiDAO;
import com.park.themepark.dao.VeDAO;
import com.park.themepark.dao.KhachHangDAO;
import com.park.themepark.model.BaoTri;
import com.park.themepark.model.ChamCong;
import com.park.themepark.model.ChiTiet_HD;
import com.park.themepark.model.Combo_Ve;
import com.park.themepark.model.HoaDon;
import com.park.themepark.model.HopDong;
import com.park.themepark.model.KhachHang;
import com.park.themepark.dao.NhanVienDAO;
import com.park.themepark.dao.ChamCongDAO;
import com.park.themepark.dao.PhieuKhoDAO;
import com.park.themepark.dao.SanPhamDAO;
import com.park.themepark.dao.KhuVucDAO;
import com.park.themepark.model.KhuTroChoi;
import com.park.themepark.model.KhuVuc;
import com.park.themepark.dao.HopDongDao;
import com.park.themepark.model.KhuyenMai;
import com.park.themepark.model.NhanVien;
import com.park.themepark.model.PhieuKho;
import com.park.themepark.model.SanPham;
import com.park.themepark.dao.SuKienDAO;
import com.park.themepark.model.SuKien;
import com.park.themepark.model.Ve;

@Controller
public class DashboardController {

    @Autowired
    private KhuTroChoiDAO khuTroChoiDAO;

    @Autowired
    private BaoTriDAO baoTriDAO;

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
    private ChamCongDAO chamCongDAO;

    @Autowired
    private SuKienDAO suKienDAO;

    @Autowired
    private KhuVucDAO khuVucDAO;

    @Autowired
    private HopDongDao hopDongDao;

    @Autowired
    private SanPhamDAO sanPhamDAO;

    @Autowired
    private PhieuKhoDAO phieuKhoDAO;

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
        return "e_ql_khuyenmai"; 
    }

    @GetMapping("/bao_tri")
    public String hienThiDSBaoTri(Model model) {
        List<BaoTri> list = baoTriDAO.findAll();
        model.addAttribute("danhSachBaoTri", list);
        return "e_baotri"; 
    }

    @GetMapping("/cham_cong")
    public String hienThiDSChamCong(Model model) {
        List<ChamCong> list = chamCongDAO.findAll();
        model.addAttribute("danhSachChamCong", list);
        return "e_chamcong"; 
    }


    @GetMapping("/doanh_thu")
    public String hienThiDSDoanhThu(Model model) {
        // Lấy số lượng khách hàng, hóa đơn, khu vực
        int tongKhachHang = khachHangDAO.findAll().size();
        int tongHoaDon = hoaDonDAO.findAll().size();
        int tongKhuVuc = khuVucDAO.findAll().size();

        model.addAttribute("tongKhachHang", tongKhachHang);
        model.addAttribute("tongHoaDon", tongHoaDon);
        model.addAttribute("tongKhuVuc", tongKhuVuc);

        // Doanh thu theo tháng
        List<HoaDon> danhSachHoaDon = hoaDonDAO.findAll();
        double[] doanhThuTheoThang = new double[12]; // tháng 0-11

        for (HoaDon hd : danhSachHoaDon) {
            if (hd.getNgayLap() != null) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(hd.getNgayLap());
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH); // 0-11

                if (year == 2025) {
                    doanhThuTheoThang[month] += (hd.getTongTienSau() != null ? hd.getTongTienSau() : 0);
                }
            }
        }

        model.addAttribute("doanhThuThang", doanhThuTheoThang);
        return "e_doanhthu"; 
    }

    @GetMapping("/ds_khu_vuc")
    public String hienThiDSKhuVuc(Model model) {
        List<KhuVuc> list = khuVucDAO.findAll();
        model.addAttribute("danhSachKV", list);
        return "e_ds_khuvuc"; 
    }

    @GetMapping("/ds_san_pham")
    public String hienThiDSSanPham(Model model) {
        List<SanPham> list = sanPhamDAO.findAll();
        model.addAttribute("danhSachSP", list);
        return "e_ds_sanpham"; 
    }

    @GetMapping("/luot_kh")
    public String hienThiDSLuotKH(Model model) {
        return "e_luot_kh"; 
    }

    @GetMapping("/ql_hop_dong")
    public String hienThiDSQLHopDong(Model model) {
        List<HopDong> list = hopDongDao.findAll();
        model.addAttribute("danhSachHDong", list);
        return "e_ql_hopdong"; 
    }

    @GetMapping("/ql_nhap_xuat")
    public String hienThiDSQLNhap(Model model) {
        List<PhieuKho> list = phieuKhoDAO.findAll();
        model.addAttribute("danhSachPK", list);

        return "e_ql_nhap_xuat"; 
    }

}
