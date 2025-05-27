function xemChiTietHoaDonFromButton(button) {
    const maHD = button.getAttribute('data-maHD');
    xemChiTietHoaDon(maHD);
}

function xemChiTietHoaDon(maHD) {
    fetch('/api/chi-tiet-hoa-don/' + maHD)
        .then(response => {
            if (!response.ok) throw new Error('Lỗi HTTP: ' + response.status);
            return response.json();
        })
        .then(data => {
            // Thông tin hóa đơn
            const hd = data.hoaDon;
            document.getElementById('maHDModal').innerText = hd.maHD;
            document.getElementById('ngayLapModal').innerText = new Date(hd.ngayLap).toLocaleDateString('vi-VN');
            document.getElementById('hinhThucTTModal').innerText = hd.hinhThucTT;
            document.getElementById('maKHModal').innerText = hd.maKH;
            document.getElementById('maNVModal').innerText = hd.maNV;
            document.getElementById('tongTienTruocModal').innerText = hd.tongTienTruoc?.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'}) || '';
            document.getElementById('tienGiamGiaModal').innerText = hd.tienGiamGia?.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'}) || '';
            document.getElementById('tongTienSauModal').innerText = hd.tongTienSau?.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'}) || '';
            document.getElementById('maKMModal').innerText = hd.maKM || '';

            // Danh sách chi tiết hóa đơn
            const table = document.getElementById('danhSachChiTietHDTable');
            table.innerHTML = '';
            data.dsChiTiet.forEach(item => {
                const row = `
                    <tr>
                        <td>${item.loai}</td>
                        <td>${item.maLoai}</td>
                        <td>${item.tenLoai}</td>
                        <td>${item.soLuong}</td>
                        <td>${item.thanhTien?.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'}) || ''}</td>
                    </tr>`;
                table.innerHTML += row;
            });

            // Hiện modal
            const modal = new bootstrap.Modal(document.getElementById('chiTietHoaDonModal'));
            modal.show();
        })
        .catch(error => console.error('Lỗi khi tải chi tiết hóa đơn:', error));
}
