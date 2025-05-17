// Biến lưu trữ giỏ hàng
let cart = JSON.parse(localStorage.getItem("cart")) || [];

function addToCart(itemName, price) {
    const existingItem = cart.find(item => item.name === itemName);
    if (existingItem) {
        existingItem.quantity += 1;
    } else {
        cart.push({ name: itemName, price, quantity: 1 });
    }
    localStorage.setItem("cart", JSON.stringify(cart));
    updateCart();
    updateCartIcon();
}

function updateCart() {
    const cartItemsContainer = document.getElementById('cart-items');
    if (!cartItemsContainer) return;
    cartItemsContainer.innerHTML = '';

    cart.forEach(item => {
        const itemDiv = document.createElement('div');
        itemDiv.className = 'cart-item';
        itemDiv.innerHTML = `
            <span>${item.name} x${item.quantity}</span>
            <span>${(item.price * item.quantity).toLocaleString()}đ</span>
        `;
        cartItemsContainer.appendChild(itemDiv);
    });
}

function zoomCartIcon() {
    const cartIcon = document.querySelector('.cart-icon-fixed');
    if (!cartIcon) return;
    cartIcon.style.transform = 'scale(1.5)';

    setTimeout(() => {
        cartIcon.style.transform = 'scale(1)';
    }, 300);
}

function updateCartIcon() {
    const totalItems = cart.reduce((sum, item) => sum + item.quantity, 0);
    const badge = document.querySelector('.cart-icon-fixed .badge');
    if (badge) badge.innerText = totalItems;
}

window.addEventListener('load', function () {
    document.body.classList.add('loaded');
    updateCartIcon();
});

// Xử lý tăng giảm số lượng
document.addEventListener("DOMContentLoaded", () => {
    const comboCards = document.querySelectorAll(".combo-card");

    comboCards.forEach((card) => {
        const decreaseBtn = card.querySelector(".btn-decrease");
        const increaseBtn = card.querySelector(".btn-increase");
        const quantityInput = card.querySelector(".quantity-input");
        const comboTitle = card.querySelector(".combo-title").innerText;
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
                localStorage.setItem("cart", JSON.stringify(cart));
                updateCart();
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
            localStorage.setItem("cart", JSON.stringify(cart));
            updateCart();
            updateCartIcon();
        });
    });

    // Khi bấm vào biểu tượng giỏ hàng
    const cartIcon = document.querySelector('.cart-icon-fixed');
    if (cartIcon) {
        cartIcon.addEventListener('click', () => {
            window.location.href = "/thanhtoan";
        });
    }
});