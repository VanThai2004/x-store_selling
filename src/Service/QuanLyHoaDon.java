/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.HoaDon;
import Repository.DbConnect;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author KhanhCT
 */
public class QuanLyHoaDon {

    ArrayList<HoaDon> listHD = new ArrayList<>();
    ArrayList<HoaDon> listHDCT = new ArrayList<>();

    public ArrayList<HoaDon> loadSeachMaHoaDon(String maHD) {
        listHD.clear();
        try {
            String sql = "SELECT MaHD,MaNV,MaKH,MaVC,NgayTao,TongTien,TrangThai FROM HoaDon WHERE MaHD =? ";
            Connection con = DbConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maHD);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String MaHD = rs.getString(1);
                String MaNV = rs.getString(2);
                String MaKH = rs.getString(3);
                String MaVC = rs.getString(4);
                String NgayTao = rs.getString(5);
                String TongTien = rs.getString(6);
                String TrangThai = rs.getString(7);
                listHD.add(new HoaDon(MaHD, NgayTao, MaKH, MaNV, MaVC, TongTien, TrangThai, null, null, null, null, null, null, null, null));
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHD;
    }

    public ArrayList<HoaDon> loadSeachNgayBD(String NgayTaoo) {
        listHD.clear();
        try {
            String sql = "SELECT MaHD,MaNV,MaKH,MaVC,NgayTao,TongTien,TrangThai FROM HoaDon WHERE NgayTao >= ? ";
            Connection con = DbConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, NgayTaoo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String MaHD = rs.getString(1);
                String MaNV = rs.getString(2);
                String MaKH = rs.getString(3);
                String MaVC = rs.getString(4);
                String NgayTao = rs.getString(5);
                String TongTien = rs.getString(6);
                String TrangThai = rs.getString(7);
                listHD.add(new HoaDon(MaHD, NgayTao, MaKH, MaNV, MaVC, TongTien, TrangThai, null, null, null, null, null, null, null, null));
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHD;
    }

    public ArrayList<HoaDon> loadSeachNgayKT(String NgayKT) {
        listHD.clear();
        try {
            String sql = "SELECT MaHD,MaNV,MaKH,MaVC,NgayTao,TongTien,TrangThai FROM HoaDon WHERE NgayTao >= ? ";
            Connection con = DbConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, NgayKT);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String MaHD = rs.getString(1);
                String MaNV = rs.getString(2);
                String MaKH = rs.getString(3);
                String MaVC = rs.getString(4);
                String NgayTao = rs.getString(5);
                String TongTien = rs.getString(6);
                String TrangThai = rs.getString(7);
                listHD.add(new HoaDon(MaHD, NgayTao, MaKH, MaNV, MaVC, TongTien, TrangThai, null, null, null, null, null, null, null, null));
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHD;
    }

    public ArrayList<HoaDon> loadSeachMaNhanVien(String maNV) {
        listHD.clear();
        try {
            String sql = "SELECT MaHD,MaNV,MaKH,MaVC,NgayTao,TongTien,TrangThai FROM HoaDon WHERE MaNV =? ";
            Connection con = DbConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maNV);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String MaHD = rs.getString(1);
                String MaNV = rs.getString(2);
                String MaKH = rs.getString(3);
                String MaVC = rs.getString(4);
                String NgayTao = rs.getString(5);
                String TongTien = rs.getString(6);
                String TrangThai = rs.getString(7);
                listHD.add(new HoaDon(MaHD, NgayTao, MaKH, MaNV, MaVC, TongTien, TrangThai, null, null, null, null, null, null, null, null));
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHD;
    }

    public ArrayList<HoaDon> loadSeachDaThanhToan() {
        listHD.clear();
        try {
            String sql = "SELECT MaHD,MaNV,MaKH,MaVC,NgayTao,TongTien,TrangThai FROM HoaDon WHERE TrangThai = N'Đã Thanh Toán' ";
            Connection con = DbConnect.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                String MaHD = rs.getString(1);
                String MaNV = rs.getString(2);
                String MaKH = rs.getString(3);
                String MaVC = rs.getString(4);
                String NgayTao = rs.getString(5);
                String TongTien = rs.getString(6);
                String TrangThai = rs.getString(7);
                listHD.add(new HoaDon(MaHD, NgayTao, MaKH, MaNV, MaVC, TongTien, TrangThai, null, null, null, null, null, null, null, null));
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHD;
    }

    public ArrayList<HoaDon> loadSeachDaHuy() {
        listHD.clear();
        try {
            String sql = "SELECT MaHD,MaNV,MaKH,MaVC,NgayTao,TongTien,TrangThai FROM HoaDon WHERE TrangThai = N'Đã hủy' ";
            Connection con = DbConnect.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                String MaHD = rs.getString(1);
                String MaNV = rs.getString(2);
                String MaKH = rs.getString(3);
                String MaVC = rs.getString(4);
                String NgayTao = rs.getString(5);
                String TongTien = rs.getString(6);
                String TrangThai = rs.getString(7);
                listHD.add(new HoaDon(MaHD, NgayTao, MaKH, MaNV, MaVC, TongTien, TrangThai, null, null, null, null, null, null, null, null));
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHD;
    }

    public ArrayList<HoaDon> loadSeachChoThanhToan() {
        listHD.clear();
        try {
            String sql = "SELECT MaHD,MaNV,MaKH,MaVC,NgayTao,TongTien,TrangThai FROM HoaDon WHERE TrangThai = N'Chờ thanh toán' ";
            Connection con = DbConnect.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                String MaHD = rs.getString(1);
                String MaNV = rs.getString(2);
                String MaKH = rs.getString(3);
                String MaVC = rs.getString(4);
                String NgayTao = rs.getString(5);
                String TongTien = rs.getString(6);
                String TrangThai = rs.getString(7);
                listHD.add(new HoaDon(MaHD, NgayTao, MaKH, MaNV, MaVC, TongTien, TrangThai, null, null, null, null, null, null, null, null));
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHD;
    }

    public ArrayList<HoaDon> loadMaHoaDon() {
        listHD.clear();
        try {
            String sql = "SELECT MaHD,MaNV,MaKH,MaVC,NgayTao,TongTien,TrangThai FROM HoaDon order by CAST(SUBSTRING(MaHD, 3, LEN(MaHD)) AS INT) asc";
            Connection con = DbConnect.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                String MaHD = rs.getString(1);
                String MaNV = rs.getString(2);
                String MaKH = rs.getString(3);
                String MaVC = rs.getString(4);
                String NgayTao = rs.getString(5);
                String TongTien = rs.getString(6);
                String TrangThai = rs.getString(7);
                listHD.add(new HoaDon(MaHD, NgayTao, MaKH, MaNV, MaVC, TongTien, TrangThai, null, null, null, null, null, null, null, null));
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHD;
    }

    public ArrayList<HoaDon> loadHoaDonChiTiet() {
        listHDCT.clear();
        listHD.clear();
        try {
            String sql = "select a.IdSP,d.MaSP,TenSP,a.SoLuong,(GiaSau/(1-a.PhanTram/100)) AS GiaBan,a.PhanTram,a.GiaSau from CTHD a\n"
                    + "join HoaDon b on b.MaHD = a.MaHD\n"
                    + "join CTSP c on c.IdSP=a.IdSP\n"
                    + "join SanPham d on d.MaSP=c.MaSP\n"
                    + "where b.TrangThai = N'Đã thanh toán' ";
            Connection con = DbConnect.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                HoaDon bh = new HoaDon();
                bh.setIdSP(rs.getString("IdSP"));
                bh.setMaSP(rs.getString("MaSP"));
                bh.setTenSP(rs.getString("TenSp"));
                bh.setSoLuong(rs.getString("SoLuong"));
                bh.setGiaBan(rs.getString("GiaBan"));
                String phamTram = rs.getString("PhanTram");
                if (phamTram == null || phamTram.isEmpty()) {
                    bh.setPhanTram("0");
                } else {
                    bh.setPhanTram(phamTram);
                }
                bh.setGiaSau(rs.getString("GiaSau"));
                listHDCT.add(bh);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHDCT;
    }

    public ArrayList<HoaDon> loadMaHDSeachHDCT(String mhd) {
        listHDCT.clear();
        listHD.clear();
        try {
            String sql = "select a.IdSP,d.MaSP,TenSP,a.SoLuong,(GiaSau/(1-a.PhanTram/100)) AS GiaBan,a.PhanTram,a.GiaSau from CTHD a\n"
                    + "join HoaDon b on b.MaHD = a.MaHD\n"
                    + "join CTSP c on c.IdSP=a.IdSP\n"
                    + "join SanPham d on d.MaSP=c.MaSP\n"
                    + "where b.TrangThai = N'Đã thanh toán' AND b.MaHD = " + "'" + mhd + "'";

            Connection con = DbConnect.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                HoaDon bh = new HoaDon();
                bh.setIdSP(rs.getString("IdSP"));
                bh.setMaSP(rs.getString("MaSP"));
                bh.setTenSP(rs.getString("TenSp"));
                bh.setSoLuong(rs.getString("SoLuong"));
                bh.setGiaBan(rs.getString("GiaBan"));
                String phamTram = rs.getString("PhanTram");
                if (phamTram == null || phamTram.isEmpty()) {
                    bh.setPhanTram("0");
                } else {
                    bh.setPhanTram(phamTram);
                }
                bh.setGiaSau(rs.getString("GiaSau"));
                listHDCT.add(bh);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHDCT;
    }

    public ArrayList<HoaDon> loadIDSPSeachHDCT(String IDSP) {
        listHDCT.clear();
        listHD.clear();
        try {
            String sql = "select a.IdSP,d.MaSP,TenSP,a.SoLuong,(GiaSau/(1-PhanTram/100)) AS GiaBan,a.PhanTram,a.GiaSau  from CTHD a\n"
                    + "join HoaDon b on a.MaHD=b.MaHD\n"
                    + "join CTSP c on c.IdSP=a.IdSP\n"
                    + "join SanPham d on d.MaSP=c.MaSP\n"
                    + "where a.IdSP = '" + IDSP + "'";
            Connection con = DbConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon bh = new HoaDon();
                bh.setIdSP(rs.getString("IdSP"));
                bh.setMaSP(rs.getString("MaSP"));
                bh.setTenSP(rs.getString("TenSp"));
                bh.setSoLuong(rs.getString("SoLuong"));
                bh.setGiaBan(rs.getString("GiaBan"));
                String phamTram = rs.getString("PhanTram");
                if (phamTram == null || phamTram.isEmpty()) {
                    bh.setPhanTram("0");
                } else {
                    bh.setPhanTram(phamTram);
                }
                bh.setGiaSau(rs.getString("GiaSau"));
                listHDCT.add(bh);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHDCT;
    }

    public ArrayList<HoaDon> timKiemTheoNgay(String ngayBatDau, String ngayKetThuc) {
        listHDCT.clear();
        listHD.clear();

        try {
            String sql = "SELECT MaHD,MaNV,MaKH,MaVC,NgayTao,TongTien,TrangThai FROM HoaDon a\n"
                    + "WHERE (NgayTao IS NULL OR NgayTao >= ?) AND (NgayTao IS NULL OR NgayTao <= ?)";
            Connection con = DbConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ngayBatDau);
            ps.setString(2, ngayKetThuc);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String MaHD = rs.getString(1);
                String MaNV = rs.getString(2);
                String MaKH = rs.getString(3);
                String MaVC = rs.getString(4);
                String NgayTao = rs.getString(5);
                String TongTien = rs.getString(6);
                String TrangThai = rs.getString(7);
                listHD.add(new HoaDon(MaHD, NgayTao, MaKH, MaNV, MaVC, TongTien, TrangThai, null, null, null, null, null, null, null, null));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHD;
    }
}
