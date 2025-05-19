package com.park.themepark.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.park.themepark.dao.Combo_VeDAO;
import com.park.themepark.model.ChiTiet_HD;
import com.park.themepark.model.Combo_Ve;
import com.park.themepark.model.HoaDon;
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

    @GetMapping("/chonve")
    public String chonVe(@RequestParam(required = false) String ngay, Model model) {
        // Gọi findAll() từ instance comboVeDAO
        List<Combo_Ve> comboList = comboVeDAO.findAll();
        model.addAttribute("combos", comboList);
        model.addAttribute("ngayThamQuan", ngay);

        return "chonve";
    }

    @GetMapping("/thanhtoan")
    public String thanhToan(HttpSession session, Model model) {
        String maTK = (String) session.getAttribute("maTK");

        if (maTK != null) {
            TaiKhoan taiKhoan = TaiKhoanService.timTheoMaTK(maTK);
            model.addAttribute("taiKhoan", taiKhoan);
        }

        model.addAttribute("hoaDon", new HoaDon());

        return "thanhtoan"; 
    }

    @PostMapping("/thanhtoan")
    public String xuLyThanhToan(@ModelAttribute HoaDon hoaDon,
                            @RequestParam("loai[]") List<String> loaiList,
                            @RequestParam("maLoai[]") List<String> maLoaiList,
                            @RequestParam("soLuong[]") List<Integer> soLuongList,
                            @RequestParam("thanhTien[]") List<Double> thanhTienList,
                            Model model) {

        List<ChiTiet_HD> dsCTHD = new ArrayList<>();
        for (int i = 0; i < loaiList.size(); i++) {
            ChiTiet_HD ct = new ChiTiet_HD();
            ct.setLoai(loaiList.get(i));
            ct.setMaLoai(maLoaiList.get(i));
            ct.setSoLuong(soLuongList.get(i));
            ct.setThanhTien(thanhTienList.get(i));
            dsCTHD.add(ct);
        }

        boolean thanhCong = hoaDonService.themHoaDonVaChiTiet(hoaDon, dsCTHD);
        if (thanhCong) {
            String maHD = hoaDonService.getMaHDCuoiCungTheoKH(hoaDon.getMaKH());
            return "redirect:/hoadon/" + maHD;
        } else {
            model.addAttribute("message", "Thanh toán thất bại.");
            return "thanhtoan";
        }
    }

    @GetMapping("/hoadon/{maHD}")
    public String chiTietHoaDon(@PathVariable String maHD, Model model) {
        HoaDon hoaDon = hoaDonService.getHoaDonChiTiet(maHD);
        model.addAttribute("hoaDon", hoaDon);
        return "hoadon";
    }
}