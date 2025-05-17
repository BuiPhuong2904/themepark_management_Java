document.addEventListener("DOMContentLoaded", function () {
    const links = document.querySelectorAll("a[href]:not([href^='#']):not([target='_blank'])");

    links.forEach(function (link) {
      link.addEventListener("click", function (e) {
        // Nếu link là javascript:void(0) hoặc "#" thì bỏ qua
        const href = link.getAttribute("href");
        if (!href || href === "#" || href.startsWith("javascript:")) return;

        // Ngăn điều hướng mặc định
        e.preventDefault();

        // Thêm hiệu ứng mờ dần cho body
        document.body.style.transition = "opacity 0.5s ease";
        document.body.style.opacity = 0;

        // Chuyển trang sau khi hiệu ứng hoàn tất
        setTimeout(function () {
          window.location.href = href;
        }, 500);
      });
    });
});

document.addEventListener("DOMContentLoaded", function () {
    // Khi trang tải xong thì hiện dần lên
    document.body.style.opacity = 1;
});