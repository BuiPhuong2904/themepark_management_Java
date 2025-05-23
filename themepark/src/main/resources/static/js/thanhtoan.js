document.addEventListener("DOMContentLoaded", function () {
  const form = document.querySelector("form");
  if (form) {
    form.addEventListener("submit", async function (e) {
      e.preventDefault(); // Ngăn form submit mặc định

      const cart = JSON.parse(localStorage.getItem("cart") || "[]");

      if (cart.length === 0) {
        alert("Giỏ hàng của bạn đang trống!");
        return;
      }

      try {
        const res = await fetch("/api/hoadon/thanhtoan", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          credentials: "include", // THÊM DÒNG NÀY để giữ session
          body: JSON.stringify(cart)
        });

        if (!res.ok) throw new Error("Lỗi khi gửi thanh toán");

        const hoaDonId = await res.text(); // hoặc res.json() nếu backend trả về JSON

        localStorage.removeItem("cart"); // Xóa giỏ hàng
        window.location.href = `/hoadon/${hoaDonId}`; // Chuyển đến trang hóa đơn

      } catch (err) {
        console.error("Thanh toán thất bại:", err);
        alert("Đã có lỗi xảy ra khi thanh toán");
      }
    });
  }
});
