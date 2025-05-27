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