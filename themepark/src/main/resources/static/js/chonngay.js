document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("ngayForm");
    form.addEventListener("submit", function(event) {
      event.preventDefault(); // Ngăn reload trang

      const input = document.getElementById("ngayThamQuan");
      const ngayValue = input.value;

      if (!ngayValue) {
        alert("Vui lòng chọn ngày tham quan.");
        return;
      }

      // Lưu ngày vào localStorage
      localStorage.setItem("ngayThamQuan", ngayValue);

      // Chuyển hướng sang /chonve
      window.location.href = "/chonve";
    });
});