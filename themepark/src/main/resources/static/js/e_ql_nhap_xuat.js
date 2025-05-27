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