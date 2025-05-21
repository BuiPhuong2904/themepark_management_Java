document.addEventListener("DOMContentLoaded", function () {
  // Ẩn alert sau vài giây
  const alertBox = document.querySelector(".alert");
  if (alertBox) {
    setTimeout(() => {
      alertBox.style.display = "none";
    }, 3000);
  }

  // Nếu bạn có nút toggle mã giảm giá thì xử lý ở đây (nếu muốn)
  // Ví dụ: ẩn/hiện ô nhập mã
  const toggleDiscount = document.getElementById("toggle-discount");
  const discountBox = document.getElementById("discount-code-box");
  if (toggleDiscount && discountBox) {
    toggleDiscount.addEventListener("click", () => {
      discountBox.classList.toggle("d-none");
    });
  }

  // Hiệu ứng khi load xong
  document.body.classList.add("loaded");

  // Nếu muốn alert xác nhận trước khi submit
  const form = document.querySelector("form");
  if (form) {
    form.addEventListener("submit", function (e) {
      // e.preventDefault(); // Chỉ dùng nếu bạn KHÔNG muốn submit thật
      // alert("Xác nhận thanh toán thành công!");
      // window.location.href = "/hoadon";
    });
  }
});