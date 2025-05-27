package com.park.themepark.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.park.themepark.dao.ChiTiet_HDDAO;
import com.park.themepark.model.ChiTietHoaDonRequest;
import com.park.themepark.model.ChiTiet_HD;
import com.park.themepark.model.HoaDon;
import com.park.themepark.model.HoaDonRequest;
import com.park.themepark.service.HoaDonService;

@RestController
@RequestMapping("/api")
public class HoaDonController {
    
    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private ChiTiet_HDDAO chiTietHdDao;

    @PostMapping("/hoa-don")
    public ResponseEntity<?> themHoaDon(@RequestBody HoaDonRequest request) {
        try {
            HoaDon hd = new HoaDon();
            hd.setTongTienTruoc(request.getThanhTien());
            hd.setTienGiamGia(request.getTienGiamGia());
            hd.setTongTienSau(request.getTongTien());
            hd.setHinhThucTT(request.getHinhThucThanhToan());
            hd.setMaKM(request.getMaGiamGia());
            
            hd.setMaNV(null); // nếu không có

            List<ChiTiet_HD> dsCTHD = new ArrayList<>();
            for (ChiTietHoaDonRequest ctReq : request.getChiTietHoaDon()) {
                ChiTiet_HD ct = new ChiTiet_HD();
                ct.setMaLoai(ctReq.getMaLoai());  // Bắt buộc phải đúng mã loại hợp lệ

                System.out.println("maLoai: " + ctReq.getMaLoai() + ", loai: " + ctReq.getLoai());

                ct.setLoai(ctReq.getLoai().toUpperCase());  // convert 'combo' -> 'COMBO', 've' -> 'VE'
                ct.setSoLuong(ctReq.getSoLuong());
                ct.setThanhTien(ctReq.getDonGia() * ctReq.getSoLuong()); // Ví dụ tính tiền
                ct.setTenLoai(ctReq.getTenSanPham()); // Tên combo hoặc vé
                
                dsCTHD.add(ct);
            }

            boolean result = hoaDonService.themHoaDonVaChiTietTheoTenKH(request.getTenKhachHang(), hd, dsCTHD);
            if (result) {
                return ResponseEntity.ok("Lưu hóa đơn thành công");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lưu hóa đơn thất bại");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi: " + e.getMessage());
        }
    }

    @GetMapping("/chi-tiet-hoa-don/{maHD}")
    public ResponseEntity<?> getChiTietHoaDon(@PathVariable String maHD) {
        HoaDon hd = hoaDonService.findById(maHD);
        if (hd == null) {
            return ResponseEntity.notFound().build();
        }

        List<ChiTiet_HD> dsChiTiet = chiTietHdDao.findByMaHD(maHD);

        Map<String, Object> response = new HashMap<>();
        response.put("hoaDon", hd);
        response.put("dsChiTiet", dsChiTiet);

        return ResponseEntity.ok(response);
    }
}
