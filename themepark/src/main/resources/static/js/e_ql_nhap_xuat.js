function xemChiTietPhieuFromButton(button) {
    const maPhieu = button.getAttribute('data-maPhieu');
    xemChiTietPhieu(maPhieu);
}

function xemChiTietPhieu(maPhieu) {
    fetch('/api/chi-tiet-phieu/' + maPhieu)
        .then(response => {
            if (!response.ok) throw new Error('Lỗi HTTP: ' + response.status);
            return response.json();
        })
        .then(data => {
            // Thông tin phiếu
            document.getElementById('maPhieuModal').innerText = data.phieu.maPhieu;
            document.getElementById('loaiPhieuModal').innerText = data.phieu.loaiPhieu;
            document.getElementById('ngayGiaoDichModal').innerText = data.phieu.ngayGiaoDich;
            document.getElementById('maNVModal').innerText = data.phieu.maNV;
            document.getElementById('ghiChuModal').innerText = data.phieu.ghiChu;

            // Danh sách chi tiết sản phẩm
            const table = document.getElementById('danhSachSanPhamTable');
            table.innerHTML = '';
            data.dsChiTiet.forEach(sp => {
                const row = `
                    <tr>
                        <td>${sp.maSP}</td>
                        <td>${sp.soLuong}</td>
                        <td>${sp.donGia.toLocaleString()} VNĐ</td>
                    </tr>`;
                table.innerHTML += row;
            });

            // Hiện modal
            const modal = new bootstrap.Modal(document.getElementById('chiTietPhieuModal'));
            modal.show();
        })
        .catch(error => console.error('Lỗi khi tải chi tiết phiếu:', error));
}

// Load danh sách sản phẩm từ API khi mở modal
document.getElementById('modalThemPhieuKho').addEventListener('shown.bs.modal', loadDanhSachSanPham);

// Biến lưu danh sách sản phẩm (map theo maSP)
let danhSachSanPham = [];

function loadDanhSachSanPham() {
  fetch('/api/san-pham')
    .then(res => res.json())
    .then(data => {
      danhSachSanPham = data;
      // Tự động thêm 1 dòng khi mở modal
      document.getElementById('tableSanPhamBody').innerHTML = '';
      themDongSanPham();
    })
    .catch(err => {
      console.error("Lỗi tải sản phẩm:", err);
      alert("Không thể tải danh sách sản phẩm.");
    });
}

function themDongSanPham() {
  const tbody = document.getElementById('tableSanPhamBody');
  const row = document.createElement('tr');

  row.innerHTML = `
    <td>
      <select class="form-select" required>
        <option value="">-- Chọn sản phẩm --</option>
        ${danhSachSanPham.map(sp => `<option value="${sp.maSP}">${sp.maSP} - ${sp.tenSP}</option>`).join('')}
      </select>
    </td>
    <td><input type="number" class="form-control" min="1" required></td>
    <td><input type="number" class="form-control" step="0.01" min="0" required></td>
    <td><button type="button" class="btn btn-sm btn-danger" onclick="this.closest('tr').remove()">Xóa</button></td>
  `;

  tbody.appendChild(row);
}

function submitPhieuKho() {
  const maPhieu = document.getElementById('maPhieuInput').value;
  const ngayGiaoDich = document.getElementById('ngayGiaoDichInput').value;
  const loaiPhieu = document.getElementById('loaiPhieuInput').value;
  const maNV = document.getElementById('maNVInput').value;
  const ghiChu = document.getElementById('ghiChuInput').value;

  // Lấy danh sách chi tiết
  const chiTiet = [];
  const rows = document.querySelectorAll('#tableSanPhamBody tr');
  rows.forEach(row => {
    const select = row.querySelector('select');
    const soLuong = row.querySelectorAll('input')[0].value;
    const donGia = row.querySelectorAll('input')[1].value;

    if (select.value && soLuong > 0 && donGia >= 0) {
      chiTiet.push({
        maSP: select.value,
        soLuong: parseInt(soLuong),
        donGia: parseFloat(donGia)
      });
    }
  });

  if (chiTiet.length === 0) {
    alert("Vui lòng thêm ít nhất một sản phẩm.");
    return;
  }

  const body = {
    maPhieu,
    ngayGiaoDich,
    loaiPhieu,
    maNV,
    ghiChu,
    chiTiet
  };

  fetch('/api/phieu-kho', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(body)
  })
    .then(res => {
      if (!res.ok) throw new Error('Lỗi khi thêm phiếu kho');
      return res.json();
    })
    .then(data => {
      alert("Thêm phiếu thành công!");
      document.getElementById('modalThemPhieuKho').querySelector('form').reset();
      document.getElementById('modalThemPhieuKho').querySelector('tbody').innerHTML = '';
      const modal = bootstrap.Modal.getInstance(document.getElementById('modalThemPhieuKho'));
      modal.hide();
      // Gọi hàm reload lại danh sách nếu có
    })
    .catch(err => {
      console.error("Lỗi:", err);
      alert("Không thể thêm phiếu kho.");
    });
}
