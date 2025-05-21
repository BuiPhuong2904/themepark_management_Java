window.addEventListener('load', function () {
    document.body.classList.add('loaded');
    updateCartIcon();
});

// Lấy giỏ hàng từ localStorage hoặc khởi tạo mới
let cart = JSON.parse(localStorage.getItem("cart")) || [];

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

// Hàm cập nhật giỏ hàng cho một combo
function updateCartItem(name, price, quantity) {
    quantity = parseInt(quantity);
    if (isNaN(quantity) || quantity < 0) quantity = 0;

    const existingItem = cart.find(item => item.name === name);
    if (existingItem) {
        if (quantity === 0) {
            cart = cart.filter(item => item.name !== name);
        } else {
            existingItem.quantity = quantity;
        }
    } else {
        if (quantity > 0) {
            cart.push({ name, price, quantity });
        }
    }
    saveCart();
    updateCartIcon();
}

document.addEventListener("DOMContentLoaded", () => {
    const comboCards = document.querySelectorAll(".combo-card");

    // Khởi tạo giá trị input từ giỏ hàng đã lưu
    comboCards.forEach(card => {
        const comboTitle = card.querySelector(".combo-title").innerText.trim();
        const quantityInput = card.querySelector(".quantity-input");
        const cartItem = cart.find(item => item.name === comboTitle);
        if (cartItem) {
            quantityInput.value = cartItem.quantity;
        } else {
            quantityInput.value = 0;
        }
    });

    comboCards.forEach(card => {
        const decreaseBtn = card.querySelector(".btn-decrease");
        const increaseBtn = card.querySelector(".btn-increase");
        const quantityInput = card.querySelector(".quantity-input");
        const comboTitle = card.querySelector(".combo-title").innerText.trim();

        // Lấy giá combo từ data-price (kiểu số)
        const price = parseFloat(card.getAttribute("data-price")) || 0;

        // Giảm số lượng khi bấm nút -
        decreaseBtn.addEventListener("click", () => {
            let currentValue = parseInt(quantityInput.value) || 0;
            if (currentValue > 0) {
                currentValue--;
                quantityInput.value = currentValue;
                updateCartItem(comboTitle, price, currentValue);
            }
        });

        // Tăng số lượng khi bấm nút +
        increaseBtn.addEventListener("click", () => {
            let currentValue = parseInt(quantityInput.value) || 0;
            currentValue++;
            quantityInput.value = currentValue;
            updateCartItem(comboTitle, price, currentValue);
            zoomCartIcon();
        });

        // Xử lý khi người dùng nhập số trực tiếp
        quantityInput.addEventListener("change", () => {
            let currentValue = parseInt(quantityInput.value);
            if (isNaN(currentValue) || currentValue < 0) {
                currentValue = 0;
                quantityInput.value = 0;
            }
            updateCartItem(comboTitle, price, currentValue);
        });
    });

    // Cập nhật số lượng badge lúc đầu khi load trang
    updateCartIcon();
});