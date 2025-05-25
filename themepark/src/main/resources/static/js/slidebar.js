// document.addEventListener("DOMContentLoaded", () => {
//   const links = document.querySelectorAll(".sidebar a[data-page]");
//   const mainContent = document.querySelector(".main-content");

//   links.forEach(link => {
//     link.addEventListener("click", (e) => {
//       e.preventDefault(); // Ngăn chuyển trang
//       const page = link.getAttribute("data-page");

//       // Thay nội dung theo từng giá trị page
//       switch (page) {
//         case "trang-chu":
//           mainContent.innerHTML = `
//             <h2>Trang chủ</h2>
//             <p>Chào mừng bạn đến với công viên giải trí!</p>
//           `;
//           break;

//         //Ve
//         case "ban-ve":
//           mainContent.innerHTML = `
//             <h2>Bán vé</h2>
//             <p>Đây là giao diện bán vé.</p>
//           `;
//           break;
//         case "kiem-tra-ve":
//           mainContent.innerHTML = `
//             <h2>Kiểm tra vé</h2>
//             <p>Đây là giao diện kiểm tra vé hợp lệ.</p>
//           `;
//           break;
//         case "lich-su-giao-dich":
//           mainContent.innerHTML = `
//             <h2>Lịch sử giao dịch</h2>
//             <p>Danh sách giao dịch gần đây.</p>
//           `;
//           break;

//         //khachhang
//         case "danh-sach-khach-hang":
//           mainContent.innerHTML = `
//             <h2>Khách hàng</h2>
//             <p>Danh sách khách hàng đã đăng ký.</p>
//           `;
//           break;
//         case "them-khach-hang":
//           mainContent.innerHTML = `
//             <h2>Thêm khách hàng</h2>
//             <p>Form để thêm khách hàng mới.</p>
//           `;
//           break;
//         case "ghi-chu-khach-hang":
//           mainContent.innerHTML = `
//             <h2>Ghi chú khách hàng</h2>
//             <p>Ghi chú về khách hàng.</p>
//           `;
//           break;

//         //trochoi 
//         case "danh-sach-tro-choi":
//           mainContent.innerHTML = `
//             <h2>Trò chơi</h2>
//             <p>Danh sách trò chơi hiện có.</p>
//           `;
//           break;       
//         case "trang-thai-hoat-dong":
//           mainContent.innerHTML = `
//             <h2>Trạng thái hoạt động</h2>
//             <p>Thông tin về trạng thái hoạt động của các trò chơi.</p>
//           `;
//           break;
//         case "them-tro-choi":
//           mainContent.innerHTML = `
//             <h2>Thêm trò chơi</h2>
//             <p>Form để thêm trò chơi mới.</p>
//           `;
//           break;
        
//         //sukien
//         case "danh-sach-su-kien":
//           mainContent.innerHTML = `
//             <h2>Sự kiện</h2>
//             <p>Danh sách các sự kiện sắp diễn ra.</p>
//           `;
//           break;
//         case "lich-su-kien":
//           mainContent.innerHTML = `
//             <h2>Lịch sử sự kiện</h2>
//             <p>Thông tin về các sự kiện đã diễn ra.</p>
//           `;
//           break;
//         case "them-su-kien":
//           mainContent.innerHTML = `
//             <h2>Thêm sự kiện</h2>
//             <p>Form để thêm sự kiện mới.</p>
//           `;
//           break;

//         //khuyenmai
//         case "danh-sach-khuyen-mai":
//           mainContent.innerHTML = `
//             <h2>Khuyến mãi</h2>
//             <p>Danh sách các chương trình khuyến mãi hiện có.</p>
//           `;
//           break;
//         case "them-khuyen-mai":
//           mainContent.innerHTML = `  
//             <h2>Thêm khuyến mãi</h2>
//             <p>Form để thêm chương trình khuyến mãi mới.</p>
//           `;
//           break;

//         //nhansu
//         case "danh-sach-nhan-su":
//           mainContent.innerHTML = `
//             <h2>Nhân sự</h2>
//             <p>Danh sách nhân viên công viên giải trí.</p>
//           `;
//           break;
//         case "lich-lam-viec":
//             mainContent.innerHTML = `
//             <h2>Lịch làm việc</h2>
//             <p>Thông tin về lịch làm việc của nhân viên.</p>
//           `;
//           break;
//         case "cham-cong":
//           mainContent.innerHTML = `
//             <h2>Chấm công</h2>
//             <p>Giao diện chấm công cho nhân viên.</p>
//           `;
//           break;
        
//         //qly baocao
//         case "doanh-thu":
//           mainContent.innerHTML = `
//             <h2>Doanh thu</h2>
//             <p>Báo cáo doanh thu của công viên giải trí.</p>
//           `;
//           break;
//         case "luot-khach-hang":
//           mainContent.innerHTML = `
//             <h2>Lượt khách hàng</h2>
//             <p>Báo cáo lượt khách hàng đã tham quan.</p>
//           `;
//           break;
//         case "chi-tiet-theo-ngay":
//           mainContent.innerHTML = `
//             <h2>Chi tiết theo ngày</h2>
//             <p>Báo cáo chi tiết doanh thu và lượt khách hàng theo từng ngày.</p>
//           `;
//           break;

//         //caidat
//         case "duong-link":
//           mainContent.innerHTML = `
//             <h2>Đường link</h2>
//             <p>Thiết lập đường link cho các trang.</p>
//           `;
//           break;

//         //hoso
//         case "ho-so":
//           mainContent.innerHTML = `
//             <h2>Hồ sơ</h2>
//             <p>Thông tin hồ sơ cá nhân.</p>
//           `;
//           break;
//         default:
//           mainContent.innerHTML = `<h2>Trang không tồn tại</h2>`;
//       }
//     });
//   });
// });


document.addEventListener("DOMContentLoaded", () => {
  const links = document.querySelectorAll(".sidebar a[data-page]");
  const mainContent = document.querySelector(".main-content");

  links.forEach(link => {
    link.addEventListener("click", (e) => {
      e.preventDefault();
      const page = link.getAttribute("data-page");

      fetch(page)
        .then(response => {
          if (!response.ok) throw new Error("Không tìm thấy trang");
          return response.text();
        })
        .then(html => {
          mainContent.innerHTML = html;
        })
        .catch(err => {
          mainContent.innerHTML = `<h2>Lỗi</h2><p>${err.message}</p>`;
        });
    });
  });
});
