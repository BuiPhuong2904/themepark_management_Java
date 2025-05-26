
--Bảng khuyến mãi
-- Mã khuyến mãi hợp lệ với loại PERCENT
INSERT INTO KHUYENMAI VALUES ('KM001', 'Giảm 10%', 'PERCENT', 10, 'TOTAL >= 500000', TO_DATE('2025-05-01', 'YYYY-MM-DD'), TO_DATE('2025-06-30', 'YYYY-MM-DD'), 'Available');
INSERT INTO KHUYENMAI VALUES ('KM002', 'Giảm 15%', 'PERCENT', 15, 'TOTAL >= 1000000', TO_DATE('2025-05-15', 'YYYY-MM-DD'), TO_DATE('2025-06-15', 'YYYY-MM-DD'), 'Available');
INSERT INTO KHUYENMAI VALUES ('KM003', 'Giảm 5% cho đơn nhỏ', 'PERCENT', 5, 'TOTAL >= 200000', TO_DATE('2025-04-01', 'YYYY-MM-DD'), TO_DATE('2025-06-01', 'YYYY-MM-DD'), 'Available');

-- Mã khuyến mãi hợp lệ với loại AMOUNT
INSERT INTO KHUYENMAI VALUES ('KM004', 'Giảm 50k', 'AMOUNT', 50000, 'TOTAL >= 300000', TO_DATE('2025-05-01', 'YYYY-MM-DD'), TO_DATE('2025-06-15', 'YYYY-MM-DD'), 'Available');
INSERT INTO KHUYENMAI VALUES ('KM005', 'Giảm 100k cho đơn lớn', 'AMOUNT', 100000, 'TOTAL >= 1500000', TO_DATE('2025-05-10', 'YYYY-MM-DD'), TO_DATE('2025-06-10', 'YYYY-MM-DD'), 'Available');

-- Mã khuyến mãi hợp lệ với điều kiện số lượng
INSERT INTO KHUYENMAI VALUES ('KM006', 'Giảm 20k khi mua nhiều', 'AMOUNT', 20000, 'QUANTITY >= 3', TO_DATE('2025-05-20', 'YYYY-MM-DD'), TO_DATE('2025-06-30', 'YYYY-MM-DD'), 'Available');
INSERT INTO KHUYENMAI VALUES ('KM007', 'Giảm 10% khi mua combo', 'PERCENT', 10, 'QUANTITY >= 2', TO_DATE('2025-05-10', 'YYYY-MM-DD'), TO_DATE('2025-06-20', 'YYYY-MM-DD'), 'Available');

-- Mã khuyến mãi hợp lệ với điều kiện kết hợp
INSERT INTO KHUYENMAI VALUES ('KM008', 'Giảm 12% đặc biệt', 'PERCENT', 12, 'TOTAL >= 800000 AND QUANTITY >= 2', TO_DATE('2025-05-01', 'YYYY-MM-DD'), TO_DATE('2025-06-30', 'YYYY-MM-DD'), 'Available');

-- Một số mã hết hạn, không còn dùng được (sẽ bị từ chối)
INSERT INTO KHUYENMAI VALUES ('KM009', 'Mừng lễ 30/4', 'PERCENT', 10, 'TOTAL >= 300000', TO_DATE('2025-04-29', 'YYYY-MM-DD'), TO_DATE('2025-05-02', 'YYYY-MM-DD'), 'Unavailable');
INSERT INTO KHUYENMAI VALUES ('KM010', 'Mừng lễ Giỗ tỗ Hùng Vương', 'AMOUNT', 30000, 'TOTAL >= 400000', TO_DATE('2025-04-07', 'YYYY-MM-DD'), TO_DATE('2025-04-08', 'YYYY-MM-DD'), 'Unavailable');

-- Các mã luôn hợp lệ (không điều kiện)
INSERT INTO KHUYENMAI VALUES ('KM011', 'Tặng ngay 30k', 'AMOUNT', 30000, NULL, TO_DATE('2025-05-01', 'YYYY-MM-DD'), TO_DATE('2025-06-30', 'YYYY-MM-DD'), 'Available');
INSERT INTO KHUYENMAI VALUES ('KM012', 'Giảm 5% mọi hóa đơn', 'PERCENT', 5, NULL, TO_DATE('2025-05-01', 'YYYY-MM-DD'), TO_DATE('2025-06-30', 'YYYY-MM-DD'), 'Available');

-- Các mã khác hợp lệ
INSERT INTO KHUYENMAI VALUES ('KM013', 'Ưu đãi thành viên', 'AMOUNT', 40000, 'TOTAL >= 700000', TO_DATE('2025-05-15', 'YYYY-MM-DD'), TO_DATE('2025-06-30', 'YYYY-MM-DD'), 'Available');
INSERT INTO KHUYENMAI VALUES ('KM014', 'Flash sale giảm 20%', 'PERCENT', 20, 'TOTAL >= 1000000', TO_DATE('2025-05-25', 'YYYY-MM-DD'), TO_DATE('2025-05-27', 'YYYY-MM-DD'), 'Available');
INSERT INTO KHUYENMAI VALUES ('KM015', 'Combo Deal', 'PERCENT', 15, 'QUANTITY >= 4', TO_DATE('2025-05-01', 'YYYY-MM-DD'), TO_DATE('2025-07-01', 'YYYY-MM-DD'), 'Available');
INSERT INTO KHUYENMAI VALUES ('KM016', 'Giảm sốc', 'AMOUNT', 60000, 'TOTAL >= 900000', TO_DATE('2025-05-10', 'YYYY-MM-DD'), TO_DATE('2025-06-10', 'YYYY-MM-DD'), 'Available');
INSERT INTO KHUYENMAI VALUES ('KM017', 'Khuyến mãi hè', 'PERCENT', 8, 'TOTAL >= 400000 AND QUANTITY >= 2', TO_DATE('2025-05-01', 'YYYY-MM-DD'), TO_DATE('2025-06-30', 'YYYY-MM-DD'), 'Available');

COMMIT;