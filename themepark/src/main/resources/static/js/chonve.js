window.addEventListener('load', function () {
    document.body.classList.add('loaded');
    updateCartIcon();
});

// Lấy giỏ hàng từ localStorage hoặc khởi tạo mới
let cart = JSON.parse(localStorage.getItem("cart")) || [];

// Thêm hoặc cập nhật sản phẩm trong giỏ hàng
function addToCart(itemName, price) {
    const existingItem = cart.find(item => item.name === itemName);
    if (existingItem) {
        existingItem.quantity += 1;
    } else {
        cart.push({ name: itemName, price, quantity: 1 });
    }
    saveCart();
    updateCartIcon();
    zoomCartIcon();
}

// Lưu giỏ hàng lên localStorage
function saveCart() {
    localStorage.setItem("cart", JSON.stringify(cart));
}

// Cập nhật số lượng hiển thị trên biểu tượng giỏ hàng
function updateCartIcon() {
    const totalItems = cart.reduce((sum, item) => sum + item.quantity, 0);
    const badge = document.querySelector('.cart-icon-fixed .badge');
    if (badge) badge.innerText = totalItems;
}

// Hiệu ứng zoom icon giỏ hàng khi click
function zoomCartIcon() {
    const cartIcon = document.querySelector('.cart-icon-fixed');
    if (!cartIcon) return;
    cartIcon.style.transform = 'scale(1.5)';
    setTimeout(() => {
        cartIcon.style.transform = 'scale(1)';
    }, 300);
}

// Khi DOM load xong
document.addEventListener("DOMContentLoaded", () => {
    const comboCards = document.querySelectorAll(".combo-card");

    // Khởi tạo giá trị input từ giỏ hàng đã lưu
    comboCards.forEach((card) => {
        const comboTitle = card.querySelector(".combo-title").innerText;
        const quantityInput = card.querySelector(".quantity-input");
        const cartItem = cart.find(item => item.name === comboTitle);
        if (cartItem) {
            quantityInput.value = cartItem.quantity;
        } else {
            quantityInput.value = 0;
        }
    });

    comboCards.forEach((card) => {
        const decreaseBtn = card.querySelector(".btn-decrease");
        const increaseBtn = card.querySelector(".btn-increase");
        const quantityInput = card.querySelector(".quantity-input");
        const comboTitle = card.querySelector(".combo-title").innerText;

        // Lấy giá combo từ data-price (kiểu số)
        const price = parseFloat(card.getAttribute("data-price")) || 0;

        // Xử lý giảm số lượng
        decreaseBtn.addEventListener("click", () => {
            let currentValue = parseInt(quantityInput.value) || 0;
            if (currentValue > 0) {
                currentValue -= 1;
                quantityInput.value = currentValue;

                // Cập nhật giỏ hàng
                const existingItem = cart.find(item => item.name === comboTitle);
                if (existingItem) {
                    existingItem.quantity = currentValue;
                    if (currentValue === 0) {
                        cart = cart.filter(item => item.name !== comboTitle);
                    }
                }
                saveCart();
                updateCartIcon();
            }
        });

        // Xử lý tăng số lượng
        increaseBtn.addEventListener("click", () => {
            let currentValue = parseInt(quantityInput.value) || 0;
            currentValue += 1;
            quantityInput.value = currentValue;

            // Cập nhật giỏ hàng
            const existingItem = cart.find(item => item.name === comboTitle);
            if (existingItem) {
                existingItem.quantity = currentValue;
            } else {
                cart.push({ name: comboTitle, price, quantity: currentValue });
            }
            saveCart();
            updateCartIcon();
            zoomCartIcon();
        });
    });

    // Cập nhật số lượng badge lúc đầu khi load trang
    updateCartIcon();
});