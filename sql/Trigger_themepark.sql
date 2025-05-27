-- Bảng Tài khoản
CREATE SEQUENCE seq_taikhoan START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER trg_taikhoan_pk
BEFORE INSERT ON TAIKHOAN
FOR EACH ROW
BEGIN
    SELECT 'TK' || LPAD(seq_taikhoan.NEXTVAL, 3, '0') INTO :NEW.MATK FROM dual;
END;

-- Bảng Khách hàng 
CREATE SEQUENCE seq_khachhang START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER trg_khachhang_pk
BEFORE INSERT ON KHACHHANG
FOR EACH ROW
BEGIN
    SELECT 'KH' || LPAD(seq_khachhang.NEXTVAL, 3, '0') INTO :NEW.MAKH FROM dual;
END;

-- Bảng hóa đơn
CREATE SEQUENCE seq_hoadon START WITH 1 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER trg_hoadon_pk
BEFORE INSERT ON HOADON
FOR EACH ROW
BEGIN
    SELECT 'HD' || LPAD(seq_hoadon.NEXTVAL, 3, '0') INTO :NEW.MAHD FROM dual;
END;

CREATE OR REPLACE TRIGGER trg_check_maloai
BEFORE INSERT OR UPDATE ON CHITIET_HD
FOR EACH ROW
DECLARE
    v_count NUMBER;
BEGIN
    IF :NEW.LOAI = 'VE' THEN
        SELECT COUNT(*) INTO v_count FROM VE WHERE MAVE = :NEW.MALOAI;
        IF v_count = 0 THEN
            RAISE_APPLICATION_ERROR(-20001, 'Mã VE không tồn tại.');
        END IF;
        
    ELSIF :NEW.LOAI = 'COMBO' THEN
        SELECT COUNT(*) INTO v_count FROM COMBO_VE WHERE MACB = :NEW.MALOAI;
        IF v_count = 0 THEN
            RAISE_APPLICATION_ERROR(-20002, 'Mã COMBO không tồn tại.');
        END IF;
        
    ELSE
        RAISE_APPLICATION_ERROR(-20003, 'LOAI phải là VE hoặc COMBO.');
    END IF;
END;


-- SANPHAM, PHIEUKHO 
CREATE OR REPLACE TRIGGER trg_update_tonkho
AFTER INSERT ON CT_PHIEUKHO
FOR EACH ROW
DECLARE
    v_loai_phieu VARCHAR2(10);
    v_tongsl     NUMBER;
BEGIN
    -- Lấy loại phiếu từ bảng PHIEUKHO
    SELECT LOAIPHIEU INTO v_loai_phieu
    FROM PHIEUKHO 
    WHERE MAPHIEU = :NEW.MAPHIEU;

    -- Nếu phiếu nhập → cộng tồn kho
    IF v_loai_phieu = 'NHAP' THEN
        UPDATE SANPHAM
        SET TONGSL = NVL(TONGSL, 0) + :NEW.SOLUONG
        WHERE MASP = :NEW.MASP;

    -- Nếu phiếu xuất → kiểm tra trước khi trừ
    ELSIF v_loai_phieu = 'XUAT' THEN
        -- Lấy số lượng tồn hiện tại
        SELECT TONGSL INTO v_tongsl
        FROM SANPHAM
        WHERE MASP = :NEW.MASP;

        -- Kiểm tra nếu không đủ hàng thì báo lỗi
        IF v_tongsl < :NEW.SOLUONG THEN
            RAISE_APPLICATION_ERROR(-20010, 
                'Không thể xuất hàng: tồn kho hiện tại (' || v_tongsl || ') < số lượng cần xuất (' || :NEW.SOLUONG || ').');
        ELSE
            -- Nếu đủ hàng thì cho phép trừ
            UPDATE SANPHAM
            SET TONGSL = TONGSL - :NEW.SOLUONG
            WHERE MASP = :NEW.MASP;
        END IF;
    END IF;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20001, 'Không tìm thấy phiếu kho hoặc sản phẩm.');
    WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20002, 'Lỗi trigger: ' || SQLERRM);
END;
