package com.park.themepark.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.park.themepark.dao.Combo_VeDAO;
import com.park.themepark.model.CartItem;
import com.park.themepark.model.CartItemDTO;
import com.park.themepark.model.Combo_Ve;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/cart")
public class CartRestController {

    @Autowired
    private Combo_VeDAO comboVeDAO;

    @PostMapping("/update")
    public ResponseEntity<?> updateCart(@RequestBody List<CartItemDTO> cartItemDTOs, HttpSession session) {
        List<CartItem> cartItems = new ArrayList<>();

        for (CartItemDTO dto : cartItemDTOs) {
            System.out.println("Received combo in cart DTO: tenCB = " + dto.getTenCB() + ", soLuong = " + dto.getSoLuong());

            Combo_Ve combo = comboVeDAO.findByTenCB(dto.getTenCB());
            if (combo != null) {
                CartItem item = new CartItem(combo, dto.getSoLuong());
                cartItems.add(item);
            } else {
                System.out.println("Không tìm thấy combo với tên: " + dto.getTenCB());
            }
        }

        session.setAttribute("gioHang", cartItems);

        System.out.println("Giỏ hàng trong session: " + cartItems.size() + " items");

        return ResponseEntity.ok("Đã cập nhật giỏ hàng");
    }


    @GetMapping
    public List<CartItem> getCart(HttpSession session) {
        List<CartItem> gioHang = (List<CartItem>) session.getAttribute("gioHang");
        if (gioHang == null) {
            gioHang = new ArrayList<>();
            session.setAttribute("gioHang", gioHang);
        }
        return gioHang;
    }
}