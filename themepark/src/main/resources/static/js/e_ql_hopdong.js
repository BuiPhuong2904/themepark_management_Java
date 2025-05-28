// Load danh sách khu vực khi mở modal
document.getElementById('modalThemHopDong').addEventListener('shown.bs.modal', () => {
  fetch('/api/khu-vuc')  // Backend trả về danh sách khu vực
    .then(res => res.json())
    .then(data => {
      const select = document.getElementById('maKVInput');
      select.innerHTML = '';
      data.forEach(kv => {
        const option = document.createElement('option');
        option.value = kv.maKV;
        option.textContent = `${kv.tenKV} (Giá thuê: ${kv.giaThue} VND)`;
        select.appendChild(option);
      });
    })
    .catch(err => {
      console.error("Lỗi tải khu vực:", err);
      alert("Không thể tải danh sách khu vực.");
    });
});

// Submit form
function submitThemHopDong() {
  const form = document.getElementById('formThemHopDong');
  if (!form.checkValidity()) {
    form.reportValidity();
    return;
  }

  // Lấy dữ liệu hợp đồng
  const hopDong = {
    maHDong: document.getElementById('maHDInput').value.trim(),
    tenHDong: document.getElementById('tenHDInput').value.trim(),
    ngayBD: document.getElementById('ngayBDInput').value,
    ngayKT: document.getElementById('ngayKTInput').value,
    tongTien: parseFloat(document.getElementById('tongTienInput').value),
    maKV: document.getElementById('maKVInput').value,
    maNV: 'NV001', // Ví dụ: lấy mã nhân viên đang đăng nhập, hoặc hardcode tạm
    maKH: document.getElementById('maKHInput').value.trim()
  };

  // Lấy dữ liệu khách hàng mới (nếu mã khách hàng không nhập)
  const khachHangMoi = {
    maKH: hopDong.maKH || generateMaKH(), // hàm tạo mã KH mới nếu để trống
    tenKH: document.getElementById('tenKHInput').value.trim(),
    sdt: document.getElementById('sdtKHInput').value.trim(),
    diaChi: document.getElementById('diaChiKHInput').value.trim()
  };

  const payload = { hopDong, khachHangMoi };

  fetch('/api/hop-dong', {
    method: 'POST',
    headers: {'Content-Type': 'application/json'},
    body: JSON.stringify(payload)
  })
  .then(res => {
    if (res.ok) {
      alert('Thêm hợp đồng thành công');
      form.reset();
      var modal = bootstrap.Modal.getInstance(document.getElementById('modalThemHopDong'));
      modal.hide();
      // Reload danh sách hợp đồng nếu có
    } else {
      res.text().then(text => alert('Lỗi: ' + text));
    }
  })
  .catch(err => {
    console.error('Lỗi khi thêm hợp đồng:', err);
    alert('Có lỗi xảy ra khi thêm hợp đồng.');
  });
}

// Ví dụ hàm tạo mã khách hàng tự động (nếu cần)
function generateMaKH() {
  return 'KH' + Date.now();
}
