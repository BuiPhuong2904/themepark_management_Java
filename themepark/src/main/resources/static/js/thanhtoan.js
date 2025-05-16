const comboList = [
    { name: 'COMBO NGÀY HÈ VUI VẺ', soLuongNL: 2, soLuongTE: 1, price: 300000 },
    { name: 'COMBO KHÁM PHÁ ĐẠI DƯƠNG XANH', soLuongNL: 1, soLuongTE: 2, price: 250000 },
];

let tongTien = 0;

function renderComboList() {
    const comboContainer = document.getElementById('combo-list');
    comboContainer.innerHTML = '';
    tongTien = 0;

    comboList.forEach(combo => {
    const comboDiv = document.createElement('div');
    comboDiv.classList.add('mb-3');
    comboDiv.innerHTML = `
        <p><strong>${combo.name}</strong></p>
        <p>Số lượng người lớn: ${combo.soLuongNL}, Trẻ em: ${combo.soLuongTE}</p>
        <p>Giá: ${combo.price.toLocaleString()} VND</p>
    `;
    comboContainer.appendChild(comboDiv);
    tongTien += combo.price;
    });

    capNhatTien();
}

function capNhatTien() {
    const discountCode = document.getElementById('discount-code').value.trim().toLowerCase();
    let discount = 0;

    // Tính giảm giá nếu có mã hợp lệ
    if (discountCode === 'giam10') {
    discount = tongTien * 0.10;
    } else if (discountCode === 'giam20') {
    discount = tongTien * 0.20;
    }

    // Luôn hiển thị Tiền giảm giá, kể cả = 0
    document.getElementById('discount-amount-box').style.display = 'block';
    document.getElementById('discount-amount').innerText = discount.toLocaleString();
    document.getElementById('total-price').innerText = tongTien.toLocaleString();
    document.getElementById('final-total').innerText = (tongTien - discount).toLocaleString();
}

// Gọi lại mỗi lần người dùng gõ trong ô mã giảm giá
document.addEventListener('DOMContentLoaded', function () {
    renderComboList();
    document.getElementById('discount-code').addEventListener('input', capNhatTien);
});

document.getElementById('confirm-payment').addEventListener('click', function (e) {
    e.preventDefault();
    alert("Xác nhận thanh toán thành công!");
});

document.addEventListener("DOMContentLoaded", function() {
      document.body.classList.add("loaded");
});