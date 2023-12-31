
/**
 * Author:  ThongHTPH43253
 * Created: Nov 12, 2023
 */

CREATE DATABASE FPOLYSTORE
GO

USE FPOLYSTORE
GO

-- TẠO CÁC BẢNG
CREATE TABLE DANHMUC
(
	ID_DANHMUC INT IDENTITY(1,1)  NOT NULL PRIMARY KEY,
	TENDANHMUC NVARCHAR(255) NOT NULL,
	TRANGTHAI INT NULL
)

CREATE TABLE NHACUNGCAP
(
	ID_NHACUNGCAP INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	TENNHACUNGCAP NVARCHAR(255) NOT NULL,
	TRANGTHAI INT NULL
)

CREATE TABLE SANPHAM
(
	ID_SANPHAM INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	TENSANPHAM NVARCHAR(255) NOT NULL,
	MOTA NVARCHAR(255) NULL,
	TRANGTHAI INT NULL,
	ID_DANHMUC INT  NOT NULL FOREIGN KEY REFERENCES DANHMUC(ID_DANHMUC),
	ID_NHACUNGCAP INT NOT NULL FOREIGN KEY REFERENCES NHACUNGCAP(ID_NHACUNGCAP)
)

CREATE TABLE LOAISANPHAM
(
	ID_LOAISANPHAM INT IDENTITY(1,1)  NOT NULL PRIMARY KEY,
	TENLOAISANPHAM NVARCHAR(255) NOT NULL,
	TRANGTHAI INT NULL
)

CREATE TABLE MAU
(
	ID_MAU INT IDENTITY(1,1)  NOT NULL PRIMARY KEY,
	TENMAU NVARCHAR(255) NOT NULL,
	TRANGTHAI INT NULL
)

CREATE TABLE SIZE
(
	ID_SIZE INT IDENTITY(1,1)  NOT NULL PRIMARY KEY,
	TENSIZE NVARCHAR(255) NOT NULL,
	TRANGTHAI INT NULL
)
 
CREATE TABLE CHATLIEU
(
	ID_CHATLIEU INT IDENTITY(1,1)  NOT NULL PRIMARY KEY,
	TENCHATLIEU NVARCHAR(255) NOT NULL,
	TRANGTHAI INT NULL
)

CREATE TABLE SANPHAMCHITIET
(
	ID_SANPHAMCHITIET INT IDENTITY(1,1) NOT NULL  PRIMARY KEY,
	SOLUONG INT NOT NULL, 
	GIA MONEY NOT NULL,
	TRANGTHAI INT,
	ID_SANPHAM INT FOREIGN KEY REFERENCES SANPHAM(ID_SANPHAM),
	ID_LOAISANPHAM INT FOREIGN KEY REFERENCES LOAISANPHAM(ID_LOAISANPHAM),
	ID_CHATLIEU INT FOREIGN KEY REFERENCES CHATLIEU(ID_CHATLIEU),
	ID_SIZE INT FOREIGN KEY REFERENCES SIZE(ID_SIZE),
	ID_MAU INT FOREIGN KEY REFERENCES MAU(ID_MAU)
)

CREATE TABLE SALE
(
	ID_SALE INT IDENTITY(1,1) NOT NULL PRIMARY KEY ,
	TENCHIENDICH NVARCHAR(255) NOT NULL,
	THOIGIANBD DATE NOT NULL,
	THOIGIANKT DATE NOT NULL,
	TRANGTHAI INT NULL
)

CREATE TABLE SALE_CHITIET
(
	ID_SALE_CHITIET INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	HINHTHUCGIAM INT NOT NULL,
	GIATRIGIAM FLOAT NOT NULL,
	ID_SALE INT FOREIGN KEY REFERENCES SALE(ID_SALE),
	ID_SANPHAMCHITIET INT FOREIGN KEY REFERENCES SANPHAMCHITIET(ID_SANPHAMCHITIET)
)

CREATE TABLE NHANVIEN
(
	MANV VARCHAR(20) NOT NULL PRIMARY KEY,
	TENNV NVARCHAR(255) NOT NULL,
	SDT VARCHAR(15) NULL,
	EMAIL VARCHAR(100) NULL,
	MATKHAU VARCHAR(20) NOT NULL,
	DIACHI NVARCHAR(255) NULL,
	GIOITINH BIT,
	VAITRO BIT,
	TRANGTHAI INT
)

CREATE TABLE KHACHHANG
(
	ID_KHACHHANG INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	TENKH NVARCHAR(255) NOT NULL,
	SDT VARCHAR(15) NOT NULL,
	NGAYSINH DATE,
	GIOITINH BIT, 
	DIACHI NVARCHAR(255) NULL,
	TRANGTHAI INT
)

CREATE TABLE VOUCHERS
(
	ID_VOUCHER INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	TENCHIENDICH NVARCHAR(255) ,
	NGAYBD DATE,
	NGAYKT DATE, 
	TRANGTHAI INT
)

CREATE TABLE HOADON
(
	ID_HOADON INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	NGAYTAO DATE,
	TENKHACHHANG NVARCHAR(255) NOT NULL,
	DIACHI NVARCHAR(255) NOT NULL,
	SDT VARCHAR(15) NOT NULL,
	TONGTIEN MONEY,
	MAGIAMGIA VARCHAR(15) NULL,
	HINHTHUCTHANHTOAN INT,
	TRANGTHAI INT,
	ID_KHACHHANG INT NOT NULL FOREIGN KEY REFERENCES KHACHHANG(ID_KHACHHANG),
	MANV VARCHAR(20) NOT NULL FOREIGN KEY REFERENCES NHANVIEN(MANV)
)

CREATE TABLE HOADON_CT
(
	ID_HOADON_CT INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	ID_SANPHAMCHITIET INT NOT NULL FOREIGN KEY REFERENCES SANPHAMCHITIET(ID_SANPHAMCHITIET),
	ID_HOADON INT NOT NULL FOREIGN KEY REFERENCES HOADON(ID_HOADON),
	SOLUONG INT NOT NULL,
	GIA MONEY NOT NULL
)
CREATE TABLE VOUCHER_CT
(
	ID_VOUCHER_CT INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	MAVOUCHER VARCHAR(20) NOT NULL,
	HINHTHUCGIAM BIT NOT NULL,
	GIATRIGIAM FLOAT,
	ID_VOUCHER INT NOT NULL FOREIGN KEY REFERENCES VOUCHERS(ID_VOUCHER),
	ID_KHACHHANG INT NOT NULL FOREIGN KEY REFERENCES KHACHHANG(ID_KHACHHANG),
	ID_HOADON INT NOT NULL FOREIGN KEY REFERENCES HOADON(ID_HOADON),
	TRANGTHAI INT 
)

CREATE TABLE ANHSANPHAM
(
	ID_ANH INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	SRC NVARCHAR(255) NOT NULL,
	ID_SANPHAMCHITIET INT NOT NULL FOREIGN KEY REFERENCES SANPHAMCHITIET(ID_SANPHAMCHITIET),
	TRANGTHAI INT
)

-- update 20-11
INSERT INTO KHACHHANG(TENKH,SDT,NGAYSINH,GIOITINH,DIACHI, TRANGTHAI)
VALUES(N'Vui lòng chọn!','0900000000','05-21-2002',1,'YB',1)
INSERT INTO NHANVIEN(MANV,TENNV, SDT, EMAIL, MATKHAU, DIACHI, GIOITINH, VAITRO, TRANGTHAI)
VALUES('THONGHT',N'HOÀNG TRUNG THÔNG','0333002864','hoangthong2105@gmail.com','THONG2105',N'YÊN BÁI',1,1,1)

ALTER TABLE HOADON
ALTER COLUMN TENKHACHHANG NVARCHAR(255)  NULL

ALTER TABLE HOADON
ALTER COLUMN DIACHI NVARCHAR(255)  NULL

ALTER TABLE HOADON
ALTER COLUMN SDT VARCHAR(15)  NULL


ALTER TABLE HOADON
ALTER COLUMN NGAYTAO DATETIME  NULL
--update 24-11
ALTER TABLE VOUCHERS
ADD HINHTHUCGIAM INT
ALTER TABLE VOUCHERS
ADD GIATRIGIAM FLOAT
ALTER TABLE VOUCHERS
ADD SOLUONG INT

ALTER TABLE VOUCHER_CT
DROP COLUMN MAVOUCHER
ALTER TABLE VOUCHER_CT
DROP COLUMN HINHTHUCGIAM
ALTER TABLE VOUCHER_CT
DROP COLUMN GIATRIGIAM
ALTER TABLE VOUCHER_CT
ADD SOTIENGIAM MONEY

--update 25-11-2023
CREATE TABLE HOADONNHAP
(
	ID_HOADONNHAP INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	NGAYTAO DATETIME NOT NULL,
	TENNHACUNGCAP NVARCHAR(255),
	SDT VARCHAR(15),
	TONGTIEN MONEY,
	GHICHU NVARCHAR(255) NULL,
	TRANGTHAI INT,
	MANV VARCHAR(20) NOT NULL FOREIGN KEY REFERENCES NHANVIEN(MANV),
	ID_NHACUNGCAP INT NOT NULL FOREIGN KEY REFERENCES NHACUNGCAP(ID_NHACUNGCAP)
)

CREATE TABLE HOADONNHAP_CT
(
	ID_HOADONNHAP_CT INT IDENTITY(1,1) NOT NULL PRIMARY KEY,
	SOLUONGNHAP INT,
	GIANHAP MONEY,
	ID_HOADONNHAP INT NOT NULL FOREIGN KEY REFERENCES HOADONNHAP(ID_HOADONNHAP),
	ID_SANPHAMCHITIET INT NOT NULL FOREIGN KEY REFERENCES SANPHAMCHITIET(ID_SANPHAMCHITIET),
	TRANGTHAI INT
)

 SELECT ID_HOADONNHAP,HDN.TENNHACUNGCAP, NGAYTAO, HDN.SDT, TONGTIEN,
                       HDN.TRANGTHAI,
                      NCC.ID_NHACUNGCAP, NV.MANV
               FROM HOADONNHAP HDN 
               JOIN NHANVIEN NV ON HDN.MANV = NV.MANV
               JOIN NHACUNGCAP NCC ON HDN.ID_NHACUNGCAP = NCC.ID_NHACUNGCAP

IF OBJECT_ID('SP_THONGKEDOANHSO') IS NOT NULL
DROP PROC SP_THONGKEDOANHSO
GO
CREATE PROC SP_THONGKEDOANHSO(@NAM INT, @THANG INT)
AS
	BEGIN
	SELECT SPCT.ID_SANPHAM, TENSANPHAM,SUM(HDCT.SOLUONG) TONGSOLUONG
	FROM SANPHAMCHITIET SPCT JOIN HOADON_CT HDCT ON SPCT.ID_SANPHAMCHITIET = HDCT.ID_SANPHAMCHITIET
						 JOIN HOADON HD ON HDCT.ID_HOADON = HD.ID_HOADON
						 JOIN SANPHAM SP ON SP.ID_SANPHAM = SPCT.ID_SANPHAM
	WHERE  YEAR(NGAYTAO) = @NAM AND MONTH(NGAYTAO) = @THANG AND  HD.TRANGTHAI =1 AND HDCT.TRANGTHAI =1
	GROUP BY SPCT.ID_SANPHAM, TENSANPHAM
	ORDER BY TONGSOLUONG DESC
	END

EXEC SP_THONGKEDOANHSO @NAM=2023,@THANG=11

SELECT DISTINCT YEAR(NGAYTAO) FROM HOADON
ORDER BY YEAR(NGAYTAO) DESC

SELECT  MONTH(NGAYTAO) FROM HOADON