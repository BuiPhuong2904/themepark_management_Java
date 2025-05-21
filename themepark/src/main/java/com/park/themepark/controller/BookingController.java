package com.park.themepark.controller;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.park.themepark.dao.Combo_VeDAO;
import com.park.themepark.dao.KhachHangDAO;
import com.park.themepark.dao.KhuyenMaiDAO;
import com.park.themepark.model.CartItem;
import com.park.themepark.model.ChiTiet_HD;
import com.park.themepark.model.Combo_Ve;
import com.park.themepark.model.HoaDon;
import com.park.themepark.model.KhachHang;
import com.park.themepark.model.KhuyenMai;
import com.park.themepark.model.TaiKhoan;
import com.park.themepark.service.HoaDonService;
import com.park.themepark.service.TaiKhoanService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BookingController {

    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private Combo_VeDAO comboVeDAO;

    @Autowired
    private KhachHangDAO khachHangDAO;

    @Autowired
    private KhuyenMaiDAO khuyenMaiDAO;

    @GetMapping("/chonve")
    public String chonVe(@RequestParam(required = false) String ngay, HttpSession session, Model model) {
        String maTK = (String) session.getAttribute("maTK");

        if (maTK == null) {
            System.out.println("Chưa đăng nhập, reset giỏ hàng");
            session.removeAttribute("gioHang");
        } else {
            System.out.println("Đã đăng nhập, giữ giỏ hàng");
        }

        model.addAttribute("isLoggedIn", maTK != null);

        List<Combo_Ve> comboList = comboVeDAO.findAll();
        model.addAttribute("combos", comboList);
        model.addAttribute("ngayThamQuan", ngay);

        return "chonve";
    }


    @PostMapping("/themvaogio")
    public String themVaoGio(@RequestParam("maCB") String maCB,
                            @RequestParam("soLuong") int soLuong,
                            HttpSession session) {
        // Lấy danh sách giỏ hàng từ session, nếu chưa có thì tạo mới
        List<CartItem> gioHang = (List<CartItem>) session.getAttribute("gioHang");
        if (gioHang == null) {
            gioHang = new ArrayList<>();
        }

        // Tìm combo trong DB
        Combo_Ve combo = comboVeDAO.findById(maCB);
        if (combo == null) {
            return "redirect:/chonve";
        }

        // Kiểm tra combo đã có trong giỏ chưa
        boolean daCo = false;
        for (CartItem item : gioHang) {
            if (item.getCombo().getMaCB().equals(maCB)) {
                item.setSoLuong(item.getSoLuong() + soLuong);
                daCo = true;
                break;
            }
        }

        if (!daCo) {
            gioHang.add(new CartItem(combo, soLuong));
        }

        // Cập nhật lại session
        session.setAttribute("gioHang", gioHang);

        return "redirect:/chonve"; // hoặc /thanhtoan nếu muốn chuyển thẳng tới trang thanh toán
    }

    @GetMapping("/thanhtoan")
    public String thanhToan(HttpSession session, Model model) {
        String maTK = (String) session.getAttribute("maTK");
        if (maTK != null) {
            TaiKhoan taiKhoan = TaiKhoanService.timTheoMaTK(maTK);
            model.addAttribute("taiKhoan", taiKhoan);
        }

        List<CartItem> gioHang = (List<CartItem>) session.getAttribute("gioHang");
        if (gioHang == null) {
            gioHang = new ArrayList<>();
        }
        model.addAttribute("gioHang", gioHang);

        KhachHang khachHang = (KhachHang) session.getAttribute("khachHang");
        model.addAttribute("khachHang", khachHang);

        // Tính tiền cho lần đầu hiển thị
        double tongTienTruoc = hoaDonService.tinhTongTien(gioHang);
        double tienGiamGia = 0.0;
        double tongTienSau = tongTienTruoc;

        model.addAttribute("tongTienTruoc", tongTienTruoc);
        model.addAttribute("tienGiamGia", tienGiamGia);
        model.addAttribute("tongTienSau", tongTienSau);

        model.addAttribute("hoaDon", new HoaDon());
        return "thanhtoan";
    }


    @PostMapping("/thanhtoan")
    public String xuLyThanhToan(@RequestParam("hinhThucTT") String hinhThucTT,
                                @RequestParam(value = "maGiamGia", required = false) String maGiamGia,
                                HttpSession session, Model model) {

        String maTK = (String) session.getAttribute("maTK");
        if (maTK == null) {
            return "redirect:/dangnhap";
        }

        String maKH = khachHangDAO.findMaKHByMaTK(maTK);
        if (maKH == null) {
            model.addAttribute("message", "Không tìm thấy khách hàng!");
            return "thanhtoan";
        }

        List<CartItem> gioHang = (List<CartItem>) session.getAttribute("gioHang");
        if (gioHang == null || gioHang.isEmpty()) {
            model.addAttribute("message", "Giỏ hàng trống!");
            return "thanhtoan";
        }

        // Tính tổng tiền và tổng số lượng
        double tongTienTruoc = hoaDonService.tinhTongTien(gioHang);
        int tongSoLuong = gioHang.stream().mapToInt(CartItem::getSoLuong).sum();

        double tienGiamGia = 0.0;
        String maKM = null;

        if (maGiamGia != null && !maGiamGia.trim().isEmpty()) {
            try {
                KhuyenMai km = khuyenMaiDAO.findById(maGiamGia.trim());
                Date today = new Date();

                if (km != null
                        && km.getTrangThai().equalsIgnoreCase("Available")
                        && !km.getNgayBD().after(today)
                        && !km.getNgayKT().before(today)) {

                    // Kiểm tra điều kiện DIEUKIEN
                    if (checkDieuKien(km.getDieuKien(), tongTienTruoc, tongSoLuong)) {
                        if (km.getLoaiKM().equalsIgnoreCase("PERCENT")) {
                            tienGiamGia = tongTienTruoc * km.getGiaTriGiam() / 100;
                        } else if (km.getLoaiKM().equalsIgnoreCase("AMOUNT")) {
                            tienGiamGia = km.getGiaTriGiam();
                        }
                        maKM = km.getMaKM();
                        model.addAttribute("kmHopLe", km);
                    } else {
                        model.addAttribute("maGiamGiaSai", "Mã giảm giá không thỏa điều kiện áp dụng.");
                    }
                } else {
                    model.addAttribute("maGiamGiaSai", "Mã khuyến mãi không hợp lệ hoặc đã hết hạn.");
                }

            } catch (Exception e) {
                model.addAttribute("maGiamGiaSai", "Không tìm thấy mã khuyến mãi.");
            }
        }

        double tongTienSau = Math.max(0, tongTienTruoc - tienGiamGia);

        // Tạo hóa đơn
        HoaDon hoaDon = new HoaDon();
        hoaDon.setMaKH(maKH);
        hoaDon.setNgayLap(new java.sql.Date(System.currentTimeMillis()));
        hoaDon.setTongTienTruoc(tongTienTruoc);
        hoaDon.setTienGiamGia(tienGiamGia);
        hoaDon.setTongTienSau(tongTienSau);
        hoaDon.setHinhThucTT(hinhThucTT);
        hoaDon.setMaNV(null);      // Không có nhân viên (online)
        hoaDon.setMaKM(maKM); 

        // Chi tiết hóa đơn từ giỏ hàng
        List<ChiTiet_HD> dsCTHD = new ArrayList<>();
        for (CartItem item : gioHang) {
            ChiTiet_HD ct = new ChiTiet_HD();
            ct.setLoai("COMBO");
            ct.setMaLoai(item.getCombo().getMaCB());
            ct.setSoLuong(item.getSoLuong());
            ct.setThanhTien(item.getThanhTien());
            dsCTHD.add(ct);
        }

        // Lưu hóa đơn và chi tiết
        boolean success = hoaDonService.themHoaDonVaChiTiet(hoaDon, dsCTHD);
        if (!success) {
            model.addAttribute("message", "Đặt vé thất bại! Vui lòng thử lại.");
            return "thanhtoan";
        }

        // Xóa giỏ hàng
        session.removeAttribute("gioHang");

        // Lấy mã hóa đơn cuối
        String maHDMoi = hoaDonService.getMaHDCuoiCungTheoKH(maKH);
        model.addAttribute("message", "Đặt vé thành công! Mã hóa đơn: " + maHDMoi);
        model.addAttribute("tongTienTruoc", tongTienTruoc);
        model.addAttribute("tienGiamGia", tienGiamGia);
        model.addAttribute("tongTienSau", tongTienSau);

        return "thanhtoan";
    }

    private boolean checkDieuKien(String dieuKien, double tongTien, int tongSoLuong) {
        if (dieuKien == null || dieuKien.trim().isEmpty()) {
            return true; // Không có điều kiện => luôn hợp lệ
        }

        dieuKien = dieuKien.toUpperCase().trim();

        String[] conditions = dieuKien.split("AND");

        for (String cond : conditions) {
            cond = cond.trim();
            if (cond.startsWith("TOTAL")) {
                if (!evaluateCondition(cond, tongTien)) return false;
            } else if (cond.startsWith("QUANTITY")) {
                if (!evaluateCondition(cond, tongSoLuong)) return false;
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean evaluateCondition(String condition, double value) {
        condition = condition.replaceAll("\\s+", "");
        if (condition.contains(">=")) {
            String[] parts = condition.split(">=");
            return value >= Double.parseDouble(parts[1]);
        } else if (condition.contains("<=")) {
            String[] parts = condition.split("<=");
            return value <= Double.parseDouble(parts[1]);
        } else if (condition.contains(">")) {
            String[] parts = condition.split(">");
            return value > Double.parseDouble(parts[1]);
        } else if (condition.contains("<")) {
            String[] parts = condition.split("<");
            return value < Double.parseDouble(parts[1]);
        } else if (condition.contains("=")) {
            String[] parts = condition.split("=");
            return value == Double.parseDouble(parts[1]);
        }
        return false;
    }

    @GetMapping("/hoadon/{maHD}")
    public String chiTietHoaDon(@PathVariable String maHD, Model model) {
        HoaDon hoaDon = hoaDonService.getHoaDonChiTiet(maHD);
        model.addAttribute("hoaDon", hoaDon);
        return "hoadon";
    }
}