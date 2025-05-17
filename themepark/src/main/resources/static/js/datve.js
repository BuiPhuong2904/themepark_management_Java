// // JavaScript để thêm hiệu ứng cho các combo

// function capNhatTieuDeCombo() {
//   const combos = document.querySelectorAll('.combo-item');
//   combos.forEach((combo, index) => {
//     const tieuDe = combo.querySelector('h5');
//     tieuDe.textContent = 'Combo ' + (index + 1);
//   });
// }

// function themCombo() {
//   const container = document.getElementById('comboContainer');
//   const firstCombo = container.querySelector('.combo-item');
//   const newCombo = firstCombo.cloneNode(true);

//   // Reset nội dung input/select
//   newCombo.querySelectorAll('input').forEach(input => input.value = 0);
//   newCombo.querySelector('select').selectedIndex = 0;

//   // Thêm class hiệu ứng
//   newCombo.classList.remove('show', 'removing');
//   container.appendChild(newCombo);

//   setTimeout(() => {
//     newCombo.classList.add('show');
//     capNhatTieuDeCombo(); // Cập nhật số thứ tự
//   }, 10);
// }

// function xoaCombo(btn) {
//   const comboItem = btn.closest('.combo-item');
//   const container = document.getElementById('comboContainer');
//   const combos = container.querySelectorAll('.combo-item');

//   if (combos.length > 1) {
//     comboItem.classList.add('removing');
//     setTimeout(() => {
//       comboItem.remove();
//       capNhatTieuDeCombo(); // Cập nhật lại số sau khi xóa
//     }, 400);
//   } else {
//     alert('Phải có ít nhất một combo!');
//   }
// }

// document.addEventListener('DOMContentLoaded', () => {
//   document.querySelectorAll('.combo-item').forEach(item => {
//     item.classList.add('show');
//   });

//   document.body.classList.add('loaded');
// });