// Hàm tải danh sách khu trò chơi và hiển thị vào <select multiple>
function loadDanhSachKhuTroChoi() {
  fetch('/api/khu-tro-choi')
    .then(res => res.json())
    .then(data => {
      const select = document.getElementById('danhSachKhuInput');
      if (!select) return;
      select.innerHTML = '';
      data.forEach(khu => {
        const option = document.createElement('option');
        option.value = khu.maKTC;
        option.textContent = `${khu.maKTC} - ${khu.tenKTC}`;
        select.appendChild(option);
      });
    })
    .catch(err => {
      console.error("Lỗi khi tải khu trò chơi:", err);
      alert("Không thể tải danh sách khu trò chơi.");
    });
}

// Hàm submit dữ liệu thêm bảo trì (tuỳ chỉnh theo backend bạn xử lý ra sao)
function submitThemBaoTri() {
  const form = document.getElementById('formThemBaoTri');

  const data = {
    maBT: document.getElementById('maBTInput').value,
    ngayBT: document.getElementById('ngayBTInput').value,
    nguoiPT: document.getElementById('nguoiPTInput').value,
    noiDung: document.getElementById('noiDungInput').value,
    chiPhi: parseFloat(document.getElementById('chiPhiInput').value || 0),
    trangThai: document.getElementById('trangThaiBTInput').value,
    ghiChu: document.getElementById('ghiChuInput').value,
    danhSachKhu: Array.from(document.getElementById('danhSachKhuInput').selectedOptions).map(opt => opt.value)
  };

  fetch('/api/baotri', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(data)
  })
    .then(res => {
      if (!res.ok) throw new Error('Thêm lịch bảo trì thất bại');
      return res.json();
    })
    .then(result => {
      alert("Đã thêm lịch bảo trì thành công!");
      form.reset();
      const modal = bootstrap.Modal.getInstance(document.getElementById('modalThemBaoTri'));
      modal.hide();
      location.reload();
    })
    .catch(err => {
      console.error(err);
      alert("Thêm lịch bảo trì thất bại.");
    });
}

// Gán sự kiện sau khi DOM đã sẵn sàng
document.addEventListener('DOMContentLoaded', function () {
  const modalEl = document.getElementById('modalThemBaoTri');
  if (modalEl) {
    modalEl.addEventListener('shown.bs.modal', loadDanhSachKhuTroChoi);
  }

  const saveBtn = document.querySelector('#modalThemBaoTri .btn-primary');
  if (saveBtn) {
    saveBtn.addEventListener('click', submitThemBaoTri);
  }
});