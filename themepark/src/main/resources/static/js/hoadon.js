
    document.addEventListener("DOMContentLoaded", function () {
    // Lấy dữ liệu từ localStorage (giả định đã lưu sau khi đặt vé)
    const khachHang = JSON.parse(localStorage.getItem("khachHang"));
    const gioHang = JSON.parse(localStorage.getItem("gioHang"));
    const phuongThuc = localStorage.getItem("phuongThuc");
    // const ghiChu = localStorage.getItem("ghiChu") || "";

    if (khachHang) {
        document.getElementById("hoTen").innerText = khachHang.hoTen;
        document.getElementById("email").innerText = khachHang.email;
        document.getElementById("sdt").innerText = khachHang.sdt;
    }

    document.getElementById("phuongThuc").innerText = phuongThuc || "Chưa chọn";
    document.getElementById("ghiChu").innerText = ghiChu;

    // Hiển thị combo
    const comboList = document.getElementById("comboList");
    let thanhTien = 0;

    if (gioHang && gioHang.length > 0) {
        gioHang.forEach(item => {
        const li = document.createElement("li");
        li.className = "list-group-item";
        li.innerText = `${item.tenCombo} - SL: ${item.soLuong} - Giá: ${item.gia} đ`;
        comboList.appendChild(li);
        thanhTien += item.soLuong * item.gia;
        });
    } else {
        comboList.innerHTML = '<li class="list-group-item">Không có sản phẩm nào.</li>';
    }

    // Giảm giá (nếu có mã)
    const giamGia = localStorage.getItem("giamGia") || 0;
    const tongThanhToan = thanhTien - giamGia;

    document.getElementById("thanhTien").innerText = thanhTien.toLocaleString() + " đ";
    document.getElementById("giamGia").innerText = parseFloat(giamGia).toLocaleString() + " đ";
    document.getElementById("tongThanhToan").innerText = tongThanhToan.toLocaleString() + " đ";
});