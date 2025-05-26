const danhSachDonHang = {}; // key: ma + loại (vd: "123-combo")

function tangSoLuong(id) {
    const span = document.getElementById(`soLuong-${id}`);
    let soLuong = parseInt(span.innerText);
    soLuong++;
    span.innerText = soLuong;
    capNhatDonHang(id, soLuong, 'combo');
}

function giamSoLuong(id) {
    const span = document.getElementById(`soLuong-${id}`);
    let soLuong = parseInt(span.innerText);
    if (soLuong > 0) {
        soLuong--;
        span.innerText = soLuong;
        capNhatDonHang(id, soLuong, 'combo');
    }
}

function tangSoLuongVe(id) {
    const span = document.getElementById(`soLuongVe-${id}`);
    let soLuong = parseInt(span.innerText);
    soLuong++;
    span.innerText = soLuong;
    capNhatDonHang(id, soLuong, 've');
}

function giamSoLuongVe(id) {
    const span = document.getElementById(`soLuongVe-${id}`);
    let soLuong = parseInt(span.innerText);
    if (soLuong > 0) {
        soLuong--;
        span.innerText = soLuong;
        capNhatDonHang(id, soLuong, 've');
    }
}

function capNhatDonHang(id, soLuong, loai) {
    const selector = loai === 'combo' ? `tr[data-combo-id="${id}"]` : `tr[data-ve-id="${id}"]`;
    const row = document.querySelector(selector);

    if (!row) return; // không tìm thấy row thì dừng

    const ten = row.querySelector("td:nth-child(1)").innerText;
    const giaStr = row.querySelector(loai === 'combo' ? "td:nth-child(2)" : "td:nth-child(3)").innerText
        .replace(" VNĐ", "")
        .trim();
    const gia = parseFloat(giaStr); // Dùng parseFloat để giữ số thập phân.

    const key = `${id}-${loai}`;

    if (soLuong > 0) {
        danhSachDonHang[key] = { ten, gia, soLuong, loai, maLoai: id };
    } else {
        delete danhSachDonHang[key];
    }

    renderDonHang();
}

function renderDonHang() {
    const list = document.getElementById("donHangList");
    list.innerHTML = "";
    let thanhTien = 0;

    for (let key in danhSachDonHang) {
        const { ten, gia, soLuong } = danhSachDonHang[key];

        const item = document.createElement("li");
        item.className = "list-group-item d-flex justify-content-between align-items-center";
        // item.innerHTML = `${ten} x${soLuong} <span>${(gia * soLuong).toLocaleString()} VNĐ</span>`;
        item.innerHTML = `
        <div style="width: 100%;" class="d-flex justify-content-between">
            <div>${ten} x${soLuong}</div>
            <div><strong>${(gia * soLuong).toLocaleString()} VNĐ</strong></div>
        </div>
        `;


        list.appendChild(item);
        thanhTien += gia * soLuong;
    }

    const tienGiamGia = 0; // tạm thời chưa xử lý mã giảm giá
    const tongTien = thanhTien - tienGiamGia;

    document.getElementById("thanhTien").innerText = thanhTien.toLocaleString() + " VNĐ";
    document.getElementById("tienGiamGia").innerText = tienGiamGia.toLocaleString() + " VNĐ";
    document.getElementById("tongTien").innerText = tongTien.toLocaleString() + " VNĐ";
}


// Lắng nghe sự kiện submit của form
document.getElementById('formThanhToan').addEventListener('submit', async function(e) {
  e.preventDefault();

  const tenKH = document.getElementById('tenKhachHang').value.trim();
  const hinhThuc = document.getElementById('hinhThucThanhToan').value;

  if (!tenKH) {
    alert("Vui lòng nhập họ tên khách hàng.");
    return;
  }

  if (!hinhThuc) {
    alert("Vui lòng chọn hình thức thanh toán.");
    return;
  }

  // Lấy thông tin hóa đơn
  const tenKhachHang = tenKH;
  const maGiamGia = document.getElementById("maGiamGia").value || null;
  const thanhTien = document.getElementById("thanhTien").textContent.replace(/[^0-9]/g, ''); // bỏ VNĐ, dấu phẩy
  const tienGiamGia = document.getElementById("tienGiamGia").textContent.replace(/[^0-9]/g, '');
  const tongTien = document.getElementById("tongTien").textContent.replace(/[^0-9]/g, '');

  const donHangList = document.getElementById("donHangList").children;
  if (donHangList.length === 0) {
    alert("Giỏ hàng trống. Vui lòng chọn ít nhất một vé/combo.");
    return;
  }

  // Chuẩn bị danh sách chi tiết đơn hàng gửi backend
    const chiTiet = [];
    for (const key in danhSachDonHang) {
    const { ten, gia, soLuong, loai, maLoai } = danhSachDonHang[key];  // bổ sung maLoai
    chiTiet.push({
        tenSanPham: ten,
        donGia: gia,
        soLuong: soLuong,
        loai: loai,
        maLoai: maLoai  // gửi lên backend luôn
    });
    }

  // Gửi dữ liệu lên backend Spring Boot
  try {
    const res = await fetch('/api/hoa-don', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        tenKhachHang,
        maGiamGia,
        hinhThucThanhToan: hinhThuc,
        thanhTien: parseInt(thanhTien),
        tienGiamGia: parseInt(tienGiamGia),
        tongTien: parseInt(tongTien),
        chiTietHoaDon: chiTiet
      })
    });

    if (!res.ok) {
      const errorText = await res.text();
      alert("Lưu hóa đơn thất bại: " + errorText);
      return;
    }
  } catch (error) {
    alert("Lỗi kết nối tới server: " + error.message);
    return;
  }

  // Nếu thành công thì hiển thị modal như cũ
  let danhSachHang = `
    <table style="width: 100%; border-collapse: collapse;">
      <thead>
        <tr>
          <th style="text-align: left; border-bottom: 1px solid #ccc;">Sản phẩm</th>
          <th style="text-align: right; border-bottom: 1px solid #ccc;">Số lượng</th>
          <th style="text-align: right; border-bottom: 1px solid #ccc;">Đơn giá</th>
        </tr>
      </thead>
      <tbody>
  `;

  for (const item of donHangList) {
    const match = item.textContent.match(/(.+?) x(\d+)\s+([\d.,]+ VNĐ)/);
    if (match) {
      const tenSanPham = match[1].trim();
      const soLuong = match[2].trim();
      const donGia = match[3].trim();

      danhSachHang += `
        <tr>
          <td>${tenSanPham}</td>
          <td style="text-align: right;">${soLuong}</td>
          <td style="text-align: right;">${donGia}</td>
        </tr>
      `;
    } else {
      danhSachHang += `<tr><td colspan="3">${item.textContent}</td></tr>`;
    }
  }

  danhSachHang += `
      </tbody>
    </table>
  `;

  const hoaDonHTML = `
    <p><strong>Họ tên khách hàng:</strong> ${tenKhachHang}</p>
    <p><strong>Mã giảm giá:</strong> ${maGiamGia || "Không áp dụng"}</p>
    <p><strong>Hình thức thanh toán:</strong> ${hinhThuc}</p>
    <hr>
    <h6>Danh sách vé/combo:</h6>
    ${danhSachHang}
    <hr>
    <p><strong>Thành tiền:</strong> ${parseInt(thanhTien).toLocaleString()} VNĐ</p>
    <p><strong>Tiền giảm giá:</strong> ${parseInt(tienGiamGia).toLocaleString()} VNĐ</p>
    <p><strong>Tổng tiền:</strong> ${parseInt(tongTien).toLocaleString()} VNĐ</p>
  `;

  document.getElementById("hoaDonContent").innerHTML = hoaDonHTML;
  document.getElementById("hoaDonModal").style.display = "block";
});

// Hàm đóng modal
function closeModal() {
  document.getElementById("hoaDonModal").style.display = "none";
}
