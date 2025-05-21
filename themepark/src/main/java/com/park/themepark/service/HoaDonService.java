package com.park.themepark.service;

import com.park.themepark.dao.ChiTiet_HDDAO;
import com.park.themepark.dao.HoaDonDAO;
import com.park.themepark.model.CartItem;
import com.park.themepark.model.ChiTiet_HD;
import com.park.themepark.model.HoaDon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoaDonService {

    @Autowired
    private HoaDonDAO hoaDonDAO;

    @Autowired
    private ChiTiet_HDDAO cthdDAO;

    public HoaDon getHoaDonChiTiet(String maHD) {
        HoaDon hd = hoaDonDAO.findById(maHD);
        if (hd != null) {
            List<ChiTiet_HD> ctList = cthdDAO.findByMaHD(maHD);
            hd.setCTHDList(ctList);
        }
        return hd;
    }

    /**
     * Thêm hóa đơn + danh sách chi tiết, xử lý mã hóa đơn tự sinh từ trigger
     */
    public boolean themHoaDonVaChiTiet(HoaDon hd, List<ChiTiet_HD> dsCTHD) {
        // Insert hóa đơn (MAHD được trigger sinh)
        int inserted = hoaDonDAO.insert(hd);
        if (inserted <= 0) return false;

        // Lấy lại mã hóa đơn mới nhất theo khách hàng
        String maHDMoi = hoaDonDAO.getLatestMaHDByCustomer(hd.getMaKH());
        if (maHDMoi == null) return false;

        // Gán MAHD cho từng chi tiết, rồi insert
        for (ChiTiet_HD ct : dsCTHD) {
            ct.setMaHD(maHDMoi);
            int row = cthdDAO.insert(ct);
            if (row <= 0) {
                // Có thể rollback thủ công ở đây nếu cần thiết
                return false;
            }
        }

        return true;
    }

    public String getMaHDCuoiCungTheoKH(String maKH) {
        return hoaDonDAO.getMaHDCuoiCungTheoKH(maKH);
    }

    public double tinhTongTien(List<CartItem> gioHang) {
        double tong = 0;
        for (CartItem item : gioHang) {
            tong += item.getThanhTien();
        }
        return tong;
    }
}