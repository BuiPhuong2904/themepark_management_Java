function xemChiTietComboFromButton(button) {
    const maCB = button.getAttribute('data-maCB');
    xemChiTietCombo(maCB);
}

function xemChiTietCombo(maCB) {
    fetch('/api/chi-tiet-combo/' + maCB)
        .then(response => {
            if (!response.ok) {
                throw new Error('Lỗi HTTP: ' + response.status);
            }
            return response.json();
        })
        .then(data => {
            document.getElementById('maCB').innerText = data.combo.maCB;
            document.getElementById('tenCB').innerText = data.combo.tenCB;
            document.getElementById('loaiCB').innerText = data.combo.loaiCB;
            document.getElementById('giaCB').innerText = data.combo.giaCB + ' VNĐ';
            document.getElementById('moTa').innerText = data.combo.moTa ?? '';
            document.getElementById('trangThai').innerText = data.combo.trangThai;

            const veTable = document.getElementById('danhSachVeTable');
            veTable.innerHTML = '';

            data.dsVe.forEach(ve => {
                veTable.innerHTML += `
                <tr>
                    <td>${ve.maVe}</td>
                    <td>${ve.tenVe}</td>
                    <td>${ve.loaiVe}</td>
                    <td>${ve.moTa ?? ''}</td>
                    <td>${ve.giaVe} VNĐ</td>
                    <td>${ve.trangThai}</td>
                </tr>`;
            });

            const modal = new bootstrap.Modal(document.getElementById('chiTietComboModal'));
            modal.show();
        })
        .catch(error => console.error('Lỗi khi tải chi tiết combo:', error));
}


// Hàm load vé từ backend đổ vào checkbox list
function loadVeCheckbox() {
  fetch('/api/ve')
    .then(res => res.json())
    .then(veList => {
      const container = document.getElementById('veCheckboxList');
      container.innerHTML = ''; // reset
      veList.forEach(ve => {
        const div = document.createElement('div');
        div.classList.add('form-check');
        div.innerHTML = `
          <input class="form-check-input" type="checkbox" value="${ve.maVe}" id="ve_${ve.maVe}" name="maVeSelect">
          <label class="form-check-label" for="ve_${ve.maVe}">${ve.maVe} - ${ve.tenVe}</label>
        `;
        container.appendChild(div);
      });
    })
    .catch(console.error);
}

// Gọi load checkbox khi mở modal
const addComboModalEl = document.getElementById('addComboModal');
addComboModalEl.addEventListener('show.bs.modal', loadVeCheckbox);

// Xử lý submit form
document.getElementById('comboForm').addEventListener('submit', function (e) {
  e.preventDefault();

  const comboData = {
    maCB: document.getElementById('maCBInput').value.trim(),
    tenCB: document.getElementById('tenCBInput').value.trim(),
    loaiCB: document.getElementById('loaiCBInput').value.trim(),
    hinhAnh: document.getElementById('hinhAnhInput').value.trim(),
    giaCB: parseFloat(document.getElementById('giaCBInput').value),
    moTa: document.getElementById('moTaInput').value.trim(),
    trangThai: document.getElementById('trangThaiInput').value
 };


  // Lấy mã vé đã chọn (checkbox)
  const selectedCheckboxes = [...document.querySelectorAll('input[name="maVeSelect"]:checked')];
  const maVeList = selectedCheckboxes.map(cb => cb.value);

  const payload = {
    combo: comboData,
    chiTietVe: maVeList.map(maVe => ({
      maCB: comboData.maCB,
      maVe: maVe
    }))
  };

  fetch('/api/combos/full', {  // backend xử lý combo + chi tiết combo-ve
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(payload)
  })
  .then(res => {
    if (!res.ok) throw new Error('Lỗi khi thêm combo');
    return res.json();
  })
  .then(data => {
    alert('Thêm combo thành công!');
    const modal = bootstrap.Modal.getInstance(addComboModalEl);
    modal.hide();
    this.reset();
    // nếu có danh sách combo hiện lên, bạn reload nó ở đây

    loadComboList(); // Cập nhật bảng combo
  })
  .catch(err => {
    console.error(err);
    alert('Thêm combo thất bại: ' + err.message);
  });
});


function loadComboList() {
  fetch('/api/combos') // endpoint trả về danh sách combo
    .then(res => res.json())
    .then(comboList => {
      const tableBody = document.getElementById('comboTableBody');
      tableBody.innerHTML = ''; // Xóa nội dung cũ

      comboList.forEach(cb => {
        const row = document.createElement('tr');
        row.innerHTML = `
          <td>${cb.maCB}</td>
          <td>${cb.tenCB}</td>
          <td>${cb.loaiCB}</td>
          <td>${cb.moTa ?? ''}</td>
          <td>${cb.giaCB} VNĐ</td>
          <td>
            <span class="badge ${cb.trangThai === 'Available' ? 'bg-success' : 'bg-secondary'}">
              ${cb.trangThai}
            </span>
          </td>
          <td class="text-center">
            <a href="#" class="btn btn-sm btn-outline-primary"><i class="bi bi-pencil-square"></i></a>
            <a href="#" class="btn btn-sm btn-outline-danger"><i class="bi bi-trash3"></i></a>
            <button class="btn btn-sm btn-outline-info"
              data-maCB="${cb.maCB}"
              onclick="xemChiTietComboFromButton(this)">
              <i class="bi bi-info-circle"></i>
            </button>
          </td>
        `;
        tableBody.appendChild(row);
      });
    })
    .catch(err => console.error('Lỗi khi tải danh sách combo:', err));
}
