CREATE DATABASE Xstore
USE Xstore

USE master
Drop DATABASE Xstore


select * from nhanvien
select * from TaiKhoan

CREATE TABLE CaLam(
	MaCL varchar(10) primary key,
	TenCL nvarchar(100),
	GioBatDau varchar(100),
	GioKetThuc varchar(100),
	GhiChu nvarchar(100)
)

CREATE TABLE NhanVien (
    MaNV varchar(10) primary key,
    TenNV nvarchar(100),
	NgayVaoLam date,
	CCCD varchar(100),
	Email varchar(100),
    Sdt varchar(15),
    VaiTro nvarchar(100),
    MaCL varchar(10) references CaLam(MaCL),
	NgaySinh date,
    GioiTinh bit,
	DiaChi nvarchar(500),
    TrangThai nvarchar(50),
	VerificationCode VARCHAR(10)
)

CREATE TABLE TaiKhoan (
    MaTK varchar(10) primary key,
    TenDangNhap varchar(100),
    MatKhau varchar(100),
    MaNV varchar(10) unique,
    foreign key (MaNV) references NhanVien(MaNV)
)
CREATE TABLE KhachHang(
	MaKH varchar(10) primary key,
	TenKH nvarchar(100),
	NgayTao date,
	NgaySinh date,
	GioiTinh bit,
	Sdt varchar(15),
	DiaChi nvarchar(100),
	TrangThai bit
)

CREATE TABLE MauSac(
	IdMauSac int identity(1,1) primary key,
	TenMauSac nvarchar(100)
)

CREATE TABLE Size(
	IdSize int identity(1,1) primary key,
	TenSize nvarchar(100)
)

CREATE TABLE ChatLieu(
	IdChatLieu int identity(1,1) primary key,
	TenChatLieu nvarchar(100)
)

CREATE TABLE Hang(
	IdHang int identity(1,1) primary key,
	TenHang nvarchar(100)
)

CREATE TABLE SanPham(
	MaSP varchar(10) primary key,
	TenSP nvarchar(100),
	SoLuongTong int,
	TrangThai bit
)

CREATE TABLE CTSP(
	IdSP int identity(1,1) primary key,
	MaSP varchar(10) references SanPham(MaSP),
	NgayNhap date,
	IdMauSac int references MauSac(IdMauSac),
	IdSize int references Size(IdSize), 
	IdChatLieu int references ChatLieu(IdChatLieu),
	IdHang int references Hang(IdHang),
	SoLuong int,
	GiaNhap float,
	HinhAnh nvarchar(100)
)

CREATE TABLE Gia(
	IdGia int identity(1,1) primary key,
	IdSP int references CTSP(IdSP),
	NgayBatDau datetime,
	NgayKetThuc datetime,
	GiaBan float
)

CREATE TABLE Coupon(
	MaCP int identity(1,1) primary key,
	TenCP nvarchar(100),
	PhanTram float,
	NgayBatDau datetime,
	NgayKetThuc datetime,
	NgayQuyetDinh datetime,
	TrangThai nvarchar(50)
)

CREATE TABLE GiamGiaSP(
	PRIMARY KEY (MaCP, IdSP),
	IdSP int references CTSP(IdSP),	
	MaCP int references Coupon(MaCP)
)

CREATE TABLE Voucher(
	MaVC varchar(10) primary key,
	TenVC nvarchar(100),
	GiamGia float,
	NgayBatDau datetime,
	NgayKetThuc datetime,
	DieuKien int,
	NgayQuyetDinh datetime,
	TrangThai nvarchar(50)
)

CREATE TABLE UuDai(
	PRIMARY KEY(MaVC, MaKH),
	MaVC varchar(10) references Voucher(MaVC),
	MaKH varchar(10) references KhachHang(MaKH)
)

CREATE TABLE HoaDon(
	MaHD varchar(10) primary key,
	NgayTao date,
	MaKH varchar(10) references KhachHang(MaKH),
	MaNV varchar(10) references NhanVien(MaNV),
	MaVC varchar(10) references Voucher(MaVC),
	TongTien float,
	TrangThai nvarchar(100)
)

CREATE TABLE CTHD(
	MaHD varchar(10) references HoaDon(MaHD),
	IdSP int references CTSP(IdSP),
	SoLuong int,
	MaCP int references Coupon(MaCP),
	PhanTram float,
	GiaSau float,
	PRIMARY KEY (MaHD, IdSP)
)
INSERT INTO CaLam (MaCL, TenCL, GioBatDau, GioKetThuc, GhiChu) VALUES
('CL001', N'Ca 1', '04:00', '08:00', N''),
('CL002', N'Ca 2', '08:00', '12:00', N''),
('CL003', N'Ca 3', '12:00', '16:00', N''),
('CL004', N'Ca 4', '16:00', '20:00', N''),
('CL005', N'Ca 5', '20:00', '00:00', N''),
('CL006', N'Ca 6', '4:00', '12:00', N''),
('CL007', N'Ca 7', '14:00', '22:00', N'')
INSERT INTO NhanVien (MaNV, TenNV, NgayVaoLam, CCCD, Email, NgaySinh, GioiTinh, DiaChi, Sdt, VaiTro, MaCL, TrangThai) VALUES
('QL001', N'Lê Thành Long', '2022-01-02','001204056516','longltph45498@fpt.edu.vn', '1990-05-15', 1, N'Hà Nội', '0987654321', N'Quản lý', 'CL006', N'Đang làm việc'),
('NV001', N'Hoàng Văn Tuấn','2022-01-02','001204056517','tuanhv@gamil.com', '1995-10-20', 0, N'Hồ Chí Minh', '0123456789', N'Nhân viên', 'CL002', N'Đang làm việc'),
('NV002', N'Nguyễn Văn Khánh','2022-01-02','001204056518','khanhnn@gamil.com', '1988-12-01', 1, N'Đà Nẵng', '0912345678', N'Nhân viên', 'CL001', N'Đang làm việc'),
('NV003', N'Bùi Bảo Tiến','2022-01-02','025204000323','tienbuibao303@gmail.com', '1988-12-01', 1, N'Phú Thọ', '0365142537', N'Nhân viên', 'CL001', N'Đang làm việc'),
('NV004', N'Lê ','2022-01-02','025204000323','tienbuibaotest01@gmail.com', '1988-12-01', 1, N'Phú Thọ', '0365142537', N'Nhân viên', 'CL001', N'Đang làm việc')

INSERT INTO KhachHang (MaKH, TenKH, NgayTao, Sdt, GioiTinh, DiaChi, TrangThai) VALUES 
('KH001', N'Nguyễn Văn A', '2023-06-19', '0987654321', 1, N'Hà Nội', 1),
('KH002', N'Nguyễn Thị B','2024-02-23' ,'0123456789', 0, N'Hà Nội', 1),
('KH003', N'Lê Văn C', '2024-01-13' ,'0912345678', 1,N'Bắc Giang', 0)
INSERT INTO TaiKhoan (MaTK, TenDangNhap, MatKhau, MaNV) VALUES
('TK001', 'admin', '123', 'QL001'),
('TK002', 'nv1', '123', 'NV001'),
('TK003', 'nv2', '123', 'NV003'),
('TK004', 'nv3', '123', 'NV004')
INSERT INTO MauSac (TenMauSac) VALUES
(N'Trắng'),
(N'Đen'),
(N'Xanh'),
(N'Đỏ'),
(N'Nâu'),
(N'Xám')
INSERT INTO Size (TenSize) VALUES
(N'38'),
(N'39'),
(N'40'),
(N'41'),
(N'42')
INSERT INTO ChatLieu (TenChatLieu) VALUES
(N'Vai'),
(N'Da')
INSERT INTO Hang (TenHang) VALUES
(N'Nike'),
(N'Adidas'),
(N'MLB'),
(N'XY'),
(N'Avivas'),
(N'Neuis'),
(N'Louis'),
(N'Prada')
INSERT INTO SanPham (MaSP, TenSP, SoLuongTong, TrangThai) VALUES
('SP001', N'Giày A', 100, 1),
('SP002', N'Giày B', 100, 1),
('SP003', N'Giày C', 100, 1),
('SP004', N'Giày D', 0, 0),
('SP005', N'Giày D', 120, 1)
INSERT INTO CTSP (MaSP, NgayNhap, IdMauSac, IdSize, IdChatLieu, IdHang, SoLuong, GiaNhap, HinhAnh) VALUES 
('SP001', '2024-04-01', 1, 1, 1, 1, 50, 600000,'a.jpg'),
('SP001', '2024-04-01', 2, 2, 2, 1, 50, 600000,'b.jpg'),
('SP002', '2024-04-02', 1, 1, 1, 2, 50, 500000,'c.jpg'),
('SP002', '2024-04-02', 2, 2, 2, 2, 50, 300000,'d.jpg'),
('SP003', '2024-04-03', 1, 2, 1, 3, 100, 600000,'e.jpg'),
('SP003', '2024-04-03', 2, 2, 2, 3, 0, 200000,'No_img'),
('SP004', '2024-04-03', 1, 1, 1, 4, 0, 300000,'No_img'),
('SP005', '2024-04-01', 1, 1, 1, 5, 40, 1000000,'No_img'),
('SP005', '2024-04-01', 1, 2, 1, 5, 40, 150000,'No_img'),
('SP005', '2024-04-01', 1, 3, 2, 5, 40, 2000000,'No_img')
INSERT INTO Gia(IdSP, NgayBatDau, NgayKetThuc, GiaBan) VALUES
(1, '2024-04-01 23:00:00','2024-04-2 10:00:00', 800000),
(2, '2024-04-01 23:00:00',null, 900000),
(3, '2024-04-02 12:00:00',null, 700000),
(4, '2024-04-02 12:00:00',null, 500000),
(5, '2024-04-03 12:00:00',null, 800000),
(6, '2024-04-03 12:00:00',null, 350000),
(1, '2024-04-02 10:00:00',null, 900000),
(7, '2024-04-03 12:00:00',null, 350000),
(8, '2024-04-01 12:00:00',null, 1150000),
(9, '2024-04-01 12:00:00',null, 1750000),
(10, '2024-04-01 12:00:00',null, 2200000),
(7, '2024-04-04 16:00:00',null, 320000)
INSERT INTO Coupon (TenCP, PhanTram, NgayBatDau, NgayKetThuc, NgayQuyetDinh, TrangThai) VALUES
(N'Coupon 1', 10, '2024-04-01 00:00:00', '2024-05-01 23:59:00','2024-04-04 13:50:00', N'Hết hạn'),
(N'Coupon 2', 5, '2024-03-01 00:00:00', '2024-04-01 23:59:00', null, N'Hết hạn'),
(N'Coupon 1', 12, '2024-04-04 13:50:00', '2024-06-01 23:59:00', null, N'Hoạt động'),
(N'Coupon 4', 15, '2024-04-01 00:00:00', '2024-06-01 23:59:00', null, N'Hoạt động'),
(N'Coupon 5', 20, '2024-05-05 00:00:00', '2024-06-01 23:59:00', null, N'Chưa kích hoạt')
INSERT INTO GiamGiaSP (MaCP, IdSP) VALUES
(1, 1),
(1, 2),
(2, 5),
(3, 1),
(3, 2)
INSERT INTO Voucher (MaVC, TenVC, GiamGia, NgayBatDau, NgayKetThuc, DieuKien, TrangThai) VALUES 
('VC001', N'Voucher 1', 100000, '2024-04-01 00:00:00', '2024-05-01 23:59:00',1000000, N'Hoạt động'),
('VC002', N'Voucher 2', 50000, '2024-01-05 00:00:00', '2024-02-20 23:59:00',2000000, N'Hết hạn'),
('VC003', N'Voucher 3', 50000, '2024-04-05 00:00:00', '2024-04-25 23:59:00',300000, N'Hoạt động'),
('VC004', N'Voucher 4', 60000, '2024-04-15 00:00:00', '2024-04-30 23:59:00',4000000, N'Chưa kích hoạt'),
('VC005', N'Voucher 5', 150000, '2024-04-02 00:00:00', '2024-05-05 23:59:00',5000000, N'Hoạt động')
INSERT INTO UuDai (MaVC, MaKH) VALUES 
('VC001', 'KH001'),
('VC001', 'KH002'),
('VC005', 'KH001'),
('VC005', 'KH002')
INSERT INTO HoaDon (MaHD, NgayTao, MaKH, MaNV, MaVC, TongTien, TrangThai) VALUES
('HD1', '2024-04-01', 'KH001', 'NV001', 'VC001', 1530000, N'Đã thanh toán'),
('HD2', '2024-04-01', 'KH002', 'NV001', 'VC005', 2230000, N'Đã thanh toán'),
('HD3', '2024-04-01', 'KH003', 'NV001', null, null, N'Đã hủy'),
('HD4', '2024-04-02', 'KH001', 'NV001', null, null, N'Đã hủy'),
('HD5', '2024-04-03', 'KH003', 'NV001', null, 700000, N'Đã thanh toán')
INSERT INTO CTHD(MaHD,IdSP,SoLuong,MaCP,PhanTram,GiaSau) VALUES
('HD1', 1, 1, 1, 10, 720000),
('HD1', 2, 1, 1, 10, 810000),
('HD2', 1, 1, 1, 10, 720000),
('HD2', 2, 1, 1, 10, 810000),
('HD2', 3, 1, null, 0, 700000),
('HD5', 3, 1, null, 0, 700000)

select a.IdSP,d.MaSP,TenSP,a.SoLuong,(GiaSau/(1-PhanTram/100)) AS GiaBan,a.PhanTram,a.GiaSau, TongTien from CTHD a
join HoaDon b on b.MaHD = a.MaHD
join CTSP c on c.IdSP=a.IdSP
join SanPham d on d.MaSP=c.MaSP
where a.MaHD='HD1'
SELECT PhanTram, NgayBatDau, NgayKetThuc FROM Coupon JOIN GiamGiaSP ON GiamGiaSP.MaCP = Coupon.MaCP WHERE NgayQuyetDinh IS NULL AND IdSP = 10 AND Coupon.MaCP <> 6

SELECT count(MaHD) FROM HoaDon 
WHERE CONVERT(date, NgayTao) = CONVERT(date, GETDATE()) AND TrangThai LIKE N'Đã thanh toán'

SELECT * FROM HoaDon
SELECT 720000+810000+700000
SELECT MaHD, NgayTao, MaKH, MaNV, MaVC, TongTien, TrangThai FROM HoaDon
SELECT MaHD,IdSP,SoLuong,MaCP,PhanTram,GiaBan FROM CTHD
SELECT (sum(GiaSau*CTHD.SoLuong)-sum(GiaNhap*CTHD.SoLuong))/sum(TongTien)*100  FROM CTHD
JOIN CTSP ON CTSP.IDSP = CTHD.IdSP
JOIN HoaDon ON HoaDon.MaHD = CTHD.MaHD
SELECT CTHD.MaHD, CTHD.IdSP, (GiaSau/(1-PhanTram/100)) AS GiaBan , MaCP, PhanTram, GiaSau FROM CTHD
SELECT * FROM Gia
SELECT * FROM CTHD
SELECT * FROM HoaDon
SELECT * FROM CTSP
SELECT sum(TongTien) AS TongTien FROM HoaDon WHERE CONVERT(date, NgayTao) = CONVERT(date, GETDATE()) AND TrangThai LIKE N'Đã thanh toán'
SELECT MaHD, NgayTao, TongTien FROM HoaDon WHERE TrangThai = N'Đã thanh toán'

SELECT NgayBatDau, NgayKetThuc FROM Coupon JOIN GiamGiaSP ON GiamGiaSP.MaCP = Coupon.MaCP WHERE Coupon.MaCP = 9 AND IdSP = 5 AND NgayQuyetDinh IS NULL

SELECT CTSP.idsp,ctsp.MaSP,TenSP,TenMauSac,TenSize,TenChatLieu,TenHang,CTSP.SoLuong,Gia.GiaBan,Coupon.PhanTram,coupon.TrangThai FROM CTSP 
JOIN MauSac  on MauSac.IdMauSac = CTSP.IdMauSac
JOIN Size  on Size.IdSize = CTSP.IdSize
JOIN ChatLieu  on ChatLieu.IdChatLieu = CTSP.IdChatLieu
JOIN Hang  on  Hang.IdHang = CTSP.IdHang
join SanPham  on SanPham.MaSP=CTSP.MaSP
JOIN (SELECT IdSP ,GiaBan FROM Gia WHERE NgayKetThuc IS NULL) AS Gia ON Gia.IdSP = CTSP.IdSP
left join GiamGiaSP  on CTSP.idsp=GiamGiaSP.idsp
left join  Coupon  on Coupon.macp=GiamGiaSP.macp
WHERE CTSP.SoLuong > 0
ORDER BY CTSP.IdSP asc

SELECT g.IdSP, g.GiaBan, g.NgayBatDau FROM Gia AS g 
WHERE g.NgayBatDau <= GETDATE() 
AND g.IdSP IN (SELECT g2.IdSP FROM Gia AS g2 WHERE g2.NgayBatDau <= GETDATE() GROUP BY g2.IdSP HAVING MAX(g2.NgayBatDau) = g.NgayBatDau)
                    

UPDATE CTSP SET SoLuong = 49 WHERE IdSP = 1
SELECT * FROM CTSP
SELECT * FROM Coupon
SELECT * FROM Voucher
SELECT * FROM UuDai
Select Coupon.MaCP, TenCP , GiamGiaSP.IdSP, Coupon.PhanTram, NgayBatDau, NgayKetThuc, TrangThai 
FROM Coupon
LEFT JOIN GiamGiaSP ON GiamGiaSP.MaCP = Coupon.MaCP
WHERE NgayQuyetDinh IS NULL

SELECT CTSP.idsp,ctsp.MaSP,TenSP,TenMauSac,TenSize,TenChatLieu,TenHang,CTSP.SoLuong,Gia.GiaBan,PhanTram,coupon.TrangThai FROM CTSP
JOIN MauSac  on MauSac.IdMauSac = CTSP.IdMauSac
JOIN Size  on Size.IdSize = CTSP.IdSize
JOIN ChatLieu  on ChatLieu.IdChatLieu = CTSP.IdChatLieu
JOIN Hang  on  Hang.IdHang = CTSP.IdHang
join SanPham  on SanPham.MaSP=CTSP.MaSP
JOIN (SELECT IdSP ,GiaBan FROM Gia WHERE NgayKetThuc IS NULL) AS Gia ON Gia.IdSP = CTSP.IdSP
left join GiamGiaSP  on CTSP.idsp=GiamGiaSP.idsp
left join  Coupon  on Coupon.macp=GiamGiaSP.macp
WHERE Coupon.NgayQuyetDinh IS NULL AND CouPon.TrangThai LIKE N'Hoạt động' OR CouPon.TrangThai IS NULL 
ORDER BY CTSP.IdSP asc

select c.IdSP,d.MaSP,TenSP,a.SoLuong,a.PhanTram,a.GiaSau from CTHD a
join HoaDon b on a.MaHD=b.MaHD
join CTSP c on c.IdSP=a.IdSP
join SanPham d on d.MaSP=c.MaSP
left join GiamGiaSP e on a.idsp=e.idsp
where a.MaHD='HD6'
group by c.IdSP,d.MaSP,TenSP,a.SoLuong,a.PhanTram,a.GiaSau

SELECT Voucher.MaVC, TenVC, GiamGia, NgayBatDau, NgayKetThuc, DieuKien, KhachHang.TrangThai AS KHTrangThai, Voucher.TrangThai AS VCTrangThai FROM Voucher\n"
left join UuDai on UuDai.MaVC = Voucher.MaVC\n" 
left join KhachHang on KhachHang.MaKH = UuDai.MaKH\n" 
WHERE NgayQuyetDinh IS NULL AND Voucher.MaVC <> 
group by Voucher.MaVC, TenVC, GiamGia, NgayBatDau, NgayKetThuc, DieuKien, KhachHang.TrangThai, Voucher.TrangThai