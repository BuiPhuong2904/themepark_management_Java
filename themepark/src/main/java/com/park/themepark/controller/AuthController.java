package com.park.themepark.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.park.themepark.model.KhachHang;
import com.park.themepark.model.TaiKhoan;
import com.park.themepark.service.KhachHangService;
import com.park.themepark.service.TaiKhoanService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    private final TaiKhoanService taiKhoanService;
    private final KhachHangService khachHangService;

    public AuthController(TaiKhoanService taiKhoanService, KhachHangService khachHangService) {
        this.taiKhoanService = taiKhoanService;
        this.khachHangService = khachHangService;
    }

    @GetMapping("/dangnhap")
    public String loginPage() {
        return "dangnhap";
    }

    @GetMapping("/dangky")
    public String registerPage() {
        return "dangky";
    }

    @PostMapping("/dangky")
    public String handleRegister(@RequestParam String hoten, @RequestParam String email, 
                                @RequestParam String matkhau, @RequestParam String xacnhan, Model model) {
        
        if (!matkhau.equals(xacnhan)) {
        model.addAttribute("error", "Mật khẩu xác nhận không khớp");
        return "dangky";
    }                            
        if (taiKhoanService.emailDaTonTai(email)) {
            model.addAttribute("error", "Email đã được sử dụng");
            return "dangky";
        }

        // Tạo tài khoản mới
        TaiKhoan tk = new TaiKhoan(null, email, matkhau, "KHACHHANG", "HOATDONG");
        String matk = (String) taiKhoanService.luuTaiKhoan(tk);

        // Tạo khách hàng mới
        KhachHang kh = new KhachHang(null, hoten, null, null, null, matk); // có thể bổ sung các field khác nếu cần
        try {
            khachHangService.luuKhachHang(kh);
        } catch (Exception e) {
            System.out.println("Lỗi khi lưu khách hàng: " + e.getMessage());
            e.printStackTrace();
        }

        return "redirect:/dangnhap";
    }

    @PostMapping("/dangnhap")
    public String handleLogin(@RequestParam String email, @RequestParam String matkhau,
                            Model model, HttpSession session) {
        TaiKhoan taiKhoan = taiKhoanService.timTheoEmail(email);

        if (taiKhoan != null && taiKhoan.getMatKhau().equals(matkhau)) {
            session.setAttribute("user", taiKhoan); // Lưu phiên đăng nhập

            KhachHang khachHang = khachHangService.timTheoTaiKhoanId(taiKhoan.getMaTK());
            System.out.println("Login thành công, khachHang: " + khachHang);
            session.setAttribute("khachHang", khachHang);

            return "redirect:/";
        } else {
            model.addAttribute("error", "Email hoặc mật khẩu không đúng");
            return "dangnhap";
        }
    }

    @GetMapping("/dangxuat")
    public String logout(HttpSession session) {
        session.invalidate();  // Xóa hết session
        return "redirect:/";
    }

}