/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.Coupon;
import Model.KhachHang;
import Model.SanPham;
import Model.Voucher;
import Repository.DbConnect;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KhanhCT
 */
public class QuanLyKhuyenMai {

    private Connection cn;

    public QuanLyKhuyenMai() {
        try {
            cn = DbConnect.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    ArrayList<Voucher> listvc = new ArrayList<>();
    public ArrayList<Voucher> getAllVC() {
        listvc.clear();
        try {
            String sql = "SELECT Voucher.MaVC, TenVC, GiamGia, NgayBatDau, NgayKetThuc, DieuKien, KhachHang.TrangThai AS KHTrangThai, Voucher.TrangThai AS VCTrangThai FROM Voucher\n"
                    + "left join UuDai on UuDai.MaVC = Voucher.MaVC\n" 
                    + "left join KhachHang on KhachHang.MaKH = UuDai.MaKH\n" 
                    + "WHERE NgayQuyetDinh IS NULL\n"
                    + "group by Voucher.MaVC, TenVC, GiamGia, NgayBatDau, NgayKetThuc, DieuKien, KhachHang.TrangThai, Voucher.TrangThai\n";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                Voucher vc = new Voucher();
                vc.setMaVC(rs.getString("MaVC"));
                vc.setTenVC(rs.getString("TenVC"));
                vc.setGiamGia(rs.getString("GiamGia"));
                vc.setNgayBatDau(rs.getString("NgayBatDau"));
                vc.setNgayKetThuc(rs.getString("NgayKetThuc"));
                vc.setDieuKien(rs.getString("DieuKien"));
                int khachHangTrangThai = rs.getInt("KHTrangThai");
                vc.setUuDai(khachHangTrangThai == 1 ? "Khách VIP" : "Không có");
                vc.setTrangThai(rs.getString("VCTrangThai"));
                listvc.add(vc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listvc;
    }
    public ArrayList<Voucher> getAllVCAdd( Float dk) {
        listvc.clear();
        try {
            String sql = "SELECT Voucher.MaVC, TenVC, GiamGia, NgayBatDau, NgayKetThuc, DieuKien, KhachHang.TrangThai AS KHTrangThai, Voucher.TrangThai AS VCTrangThai FROM Voucher\n"
                    + "left join UuDai on UuDai.MaVC = Voucher.MaVC\n" 
                    + "left join KhachHang on KhachHang.MaKH = UuDai.MaKH\n" 
                    + "WHERE NgayQuyetDinh IS NULL AND DieuKien = "+dk+"\n"
                    + "group by Voucher.MaVC, TenVC, GiamGia, NgayBatDau, NgayKetThuc, DieuKien, KhachHang.TrangThai, Voucher.TrangThai\n";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                Voucher vc = new Voucher();
                vc.setMaVC(rs.getString("MaVC"));
                vc.setTenVC(rs.getString("TenVC"));
                vc.setGiamGia(rs.getString("GiamGia"));
                vc.setNgayBatDau(rs.getString("NgayBatDau"));
                vc.setNgayKetThuc(rs.getString("NgayKetThuc"));
                vc.setDieuKien(rs.getString("DieuKien"));
                int khachHangTrangThai = rs.getInt("KHTrangThai");
                vc.setUuDai(khachHangTrangThai == 1 ? "Khách VIP" : "Không có");
                vc.setTrangThai(rs.getString("VCTrangThai"));
                listvc.add(vc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listvc;
    }
    public ArrayList<Voucher> getListVCUpdate(String ma, Float dk) {
        listvc.clear();
        try {
            String sql = "SELECT Voucher.MaVC, TenVC, GiamGia, NgayBatDau, NgayKetThuc, DieuKien, KhachHang.TrangThai AS KHTrangThai, Voucher.TrangThai AS VCTrangThai FROM Voucher\n"
                    + "left join UuDai on UuDai.MaVC = Voucher.MaVC\n" 
                    + "left join KhachHang on KhachHang.MaKH = UuDai.MaKH\n" 
                    + "WHERE NgayQuyetDinh IS NULL AND Voucher.MaVC <> "+ "'"+ma+"' AND DieuKien = "+dk+"\n"
                    + "group by Voucher.MaVC, TenVC, GiamGia, NgayBatDau, NgayKetThuc, DieuKien, KhachHang.TrangThai, Voucher.TrangThai\n";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                Voucher vc = new Voucher();
                vc.setMaVC(rs.getString("MaVC"));
                vc.setTenVC(rs.getString("TenVC"));
                vc.setGiamGia(rs.getString("GiamGia"));
                vc.setNgayBatDau(rs.getString("NgayBatDau"));
                vc.setNgayKetThuc(rs.getString("NgayKetThuc"));
                vc.setDieuKien(rs.getString("DieuKien"));
                int khachHangTrangThai = rs.getInt("KHTrangThai");
                vc.setUuDai(khachHangTrangThai == 1 ? "Khách VIP" : "Không có");
                vc.setTrangThai(rs.getString("VCTrangThai"));
                listvc.add(vc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listvc;
    }

    public void addKhachVIP(String maVC) {
        try {
            String sql = "INSERT INTO UuDai(MaVC, MaKH) \n"
                    + "SELECT ?, MaKH from KhachHang "
                    + "WHERE TrangThai = 1";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, maVC);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dltKhachVIP(String maVC) {
        try {
            String sql = "DELETE FROM UuDai "
                    + "WHERE MaVC = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, maVC);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void themVoucher(Voucher vc) {
        try {
            String sql = "INSERT INTO Voucher(MaVC, TenVC, GiamGia, NgayBatDau, NgayKetThuc, DieuKien, TrangThai)" + "VALUES(?,?,?,?,?,?,?)";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, vc.getMaVC());
            ps.setString(2, vc.getTenVC());
            ps.setString(3, vc.getGiamGia());
            ps.setString(4, vc.getNgayBatDau());
            ps.setString(5, vc.getNgayKetThuc());
            ps.setString(6, vc.getDieuKien());
            ps.setString(7, vc.getTrangThai());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addVoucher(Voucher vc, String ma){
        try {
            String sql1 = "INSERT INTO Voucher (MaVC, TenVC, GiamGia, NgayBatDau, NgayKetThuc, DieuKien, NgayQuyetDinh, TrangThai) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps1 = cn.prepareStatement(sql1);
            ps1.setString(1, vc.getMaVC());
            ps1.setString(2, vc.getTenVC());
            ps1.setString(3, vc.getGiamGia());
            ps1.setString(4, vc.getNgayBatDau());
            ps1.setString(5, vc.getNgayKetThuc());
            ps1.setString(6, vc.getDieuKien());
            ps1.setString(7, null);
            ps1.setString(8, vc.getTrangThai());
            ps1.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyKhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public void suaVoucher(Voucher vc, String ngayKT, String mavc) {
        LocalDateTime ngayKetThuc = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String ngayQD = ngayKetThuc.format(formatter);
        try {
            String sql = "UPDATE Voucher SET NgayKetThuc = ?, NgayQuyetDinh = ? WHERE MaVC = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setObject(1, ngayKT);
            ps.setObject(2, ngayQD);
            ps.setString(3, mavc);
            ps.executeUpdate();
            
            String newMaVC = generateNewMaVC(); 
            String sql1 = "INSERT INTO Voucher (MaVC, TenVC, GiamGia, NgayBatDau, NgayKetThuc, DieuKien, NgayQuyetDinh, TrangThai) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps1 = cn.prepareStatement(sql1);
            ps1.setString(1, newMaVC);
            ps1.setString(2, vc.getTenVC());
            ps1.setString(3, vc.getGiamGia());
            ps1.setString(4, vc.getNgayBatDau());
            ps1.setString(5, vc.getNgayKetThuc());
            ps1.setString(6, vc.getDieuKien());
            ps1.setString(7, null);
            ps1.setString(8, vc.getTrangThai());
            ps1.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static final String ALLOWED_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String generateNewMaVC() {
        String prefix = "VC";
        int randomLength = 6;

        Random random = new Random();
        StringBuilder sb = new StringBuilder(prefix);

        for (int i = 0; i < randomLength; i++) {
            int randomIndex = random.nextInt(ALLOWED_CHARACTERS.length());
            char randomChar = ALLOWED_CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }

//===============================Coupon===================

    public ArrayList<Coupon> getAllCP() {
        list.clear();
        try {
            String sql = "Select Coupon.MaCP, TenCP , GiamGiaSP.IdSP, Coupon.PhanTram, NgayBatDau, NgayKetThuc, TrangThai FROM Coupon\n"
                    + "LEFT JOIN GiamGiaSP ON GiamGiaSP.MaCP = Coupon.MaCP\n"
                    + "WHERE NgayQuyetDinh IS NULL";
            Statement stm = cn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                Coupon cp = new Coupon();
                cp.setMaCP(rs.getString("MaCP"));
                cp.setTenCP(rs.getString("TenCP"));
                cp.setIdSP(rs.getString("IdSP"));
                cp.setPhanTram(rs.getString("PhanTram"));
                cp.setNgayBatDau(rs.getString("NgayBatDau"));
                cp.setNgayKetThuc(rs.getString("NgayKetThuc"));
                cp.setTrangThai(rs.getString("TrangThai"));
                list.add(cp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<SanPham> getAllSP() {
        List<SanPham> listsp = new ArrayList<>();
        try {
            String sql = "SELECT CTSP.IdSP, SanPham.MaSP, SanPham.TenSP, SoLuong, Gia.GiaBan "
                    + "FROM CTSP "
                    + "LEFT JOIN SanPham ON CTSP.MaSP = SanPham.MaSP "
                    + "JOIN (SELECT IdSP, MAX(GiaBan) AS GiaBan FROM Gia WHERE NgayBatDau <= GETDATE() GROUP BY IdSP) AS Gia ON Gia.IdSP = CTSP.IdSP";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setIdspct(rs.getString("IdSP"));
                sp.setMaSanPham(rs.getString("MaSP"));
                sp.setTenSanPham(rs.getString("TenSP"));
                sp.setSoLuong(rs.getString("SoLuong"));
                sp.setGiaBan(rs.getString("GiaBan"));
                listsp.add(sp);
            }
        } catch (Exception e) {
        }
        return listsp;
    }

    public void addCoupontoSP(int maCP, int idSP) {
        try {
//            String sql = "UPDATE GiamGiaSP SET IdSP = ? WHERE MaCP = ?";
            String sql = "INSERT INTO GiamGiaSP (MaCP, IdSP) "
                    + "VALUES (?, ?)";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, maCP);
            ps.setInt(2, idSP);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkCouponAppliedToProduct(int idSP, int ma) {
        try {
            String sql = "SELECT * FROM GiamGiaSP WHERE IdSP = ? AND MaCP = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, idSP);
            ps.setInt(2, ma);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // Trả về true nếu sản phẩm đã áp dụng coupon, ngược lại false
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void addCP(Coupon cp) {
        try {
            String sql = "INSERT INTO Coupon (TenCP, PhanTram, NgayBatDau, NgayKetThuc, TrangThai) "
                    + "VALUES\n"
                    + "(?, ?, ?, ?, ?)";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, cp.getTenCP());
            ps.setString(2, cp.getPhanTram());
            ps.setString(3, cp.getNgayBatDau());
            ps.setString(4, cp.getNgayKetThuc());
            ps.setString(5, cp.getTrangThai());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<SanPham> selectSPbyID(int id) {
        List<SanPham> listsp = new ArrayList<>();
        try {
            String sql = "SELECT CTSP.IdSP, SanPham.MaSP, SanPham.TenSP, CTSP.SoLuong, Gia.GiaBan \n"
                    + "FROM CTSP \n"
                    + "JOIN SanPham ON SanPham.MaSP = CTSP.MaSP \n"
                    + "JOIN Gia ON Gia.IdSP = CTSP.IdSP "
                    + "WHERE CTSP.IdSP = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSanPham(rs.getString("MaSP"));
                sp.setTenSanPham(rs.getString("TenSP"));
                sp.setSoLuong(rs.getString("SoLuong"));
                sp.setGiaBan(rs.getString("GiaBan"));
                listsp.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listsp;
    }

    public void updCP(Coupon cp, int ma, String ngay){
        try {
            String sql2 = "UPDATE Coupon SET NgayQuyetDinh = ? WHERE MaCP = " + ma;
            PreparedStatement ps2 = cn.prepareStatement(sql2);
            ps2.setObject(1, ngay);
            ps2.executeUpdate();
            
            String sql = "INSERT INTO Coupon (TenCP, PhanTram, NgayBatDau, NgayKetThuc, TrangThai) VALUES (?,?,?,?,?)";
            PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, cp.getTenCP());
            ps.setFloat(2, Float.valueOf(cp.getPhanTram()));
            ps.setString(3, cp.getNgayBatDau());
            ps.setString(4, cp.getNgayKetThuc());
            ps.setString(5, cp.getTrangThai());
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int MaCP = rs.getInt(1);
                String sql1 = "INSERT INTO GiamGiaSP (MaCP, IdSP)\n"
                        + "SELECT ?, IdSP FROM GiamGiaSP WHERE MaCP = ?";
                PreparedStatement ps1 = cn.prepareStatement(sql1);
                ps1.setInt(1, MaCP);
                ps1.setInt(2, ma);

                ps1.executeUpdate();
                ps1.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyKhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updCP1(Coupon cp, int ma){
        try {
            String sql = "UPDATE Coupon SET TenCP = ?, PhanTram = ?, NgayBatDau = ?, NgayKetThuc = ? WHERE MaCP = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, cp.getTenCP());
            ps.setString(2, cp.getPhanTram());
            ps.setString(3, cp.getNgayBatDau());
            ps.setString(4, cp.getNgayKetThuc());
            ps.setInt(5, ma);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyKhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    ArrayList<Coupon> list = new ArrayList<>();
    public ArrayList<Coupon> getListTheoIdSP(int id){
        list.clear();
        try {
            String sql = "SELECT PhanTram, NgayBatDau, NgayKetThuc FROM Coupon JOIN GiamGiaSP ON GiamGiaSP.MaCP = Coupon.MaCP WHERE NgayQuyetDinh IS NULL AND IdSP = "+id;
            Statement stm = cn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                Coupon cp = new Coupon();
                cp.setPhanTram(rs.getString("PhanTram"));
                cp.setNgayBatDau(rs.getString("NgayBatDau"));
                cp.setNgayKetThuc(rs.getString("NgayKetThuc"));
                list.add(cp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyKhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public ArrayList<Coupon> getListTheoIdSPVaMaVC(int id, int ma){
        list.clear();
        try {
            String sql = "SELECT PhanTram, NgayBatDau, NgayKetThuc FROM Coupon JOIN GiamGiaSP ON GiamGiaSP.MaCP = Coupon.MaCP WHERE NgayQuyetDinh IS NULL AND Coupon.MaCP <> "+ ma+" AND IdSP = "+id;
            Statement stm = cn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                Coupon cp = new Coupon();
                cp.setPhanTram(rs.getString("PhanTram"));
                cp.setNgayBatDau(rs.getString("NgayBatDau"));
                cp.setNgayKetThuc(rs.getString("NgayKetThuc"));
                list.add(cp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyKhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public ArrayList<Coupon> getListNgay(int ma){
        list.clear();
        try {
            String sql = "SELECT NgayBatDau, NgayKetThuc FROM Coupon JOIN GiamGiaSP ON GiamGiaSP.MaCP = Coupon.MaCP WHERE Coupon.MaCP = "+ma;
            Statement stm = cn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                Coupon cp = new Coupon();
                cp.setNgayBatDau(rs.getString("NgayBatDau"));
                cp.setNgayKetThuc(rs.getString("NgayKetThuc"));
                list.add(cp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyKhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
