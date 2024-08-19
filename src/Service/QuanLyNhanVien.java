/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.HoaDon;
import Model.NhanVien;
import Model.TaiKhoan;
import Repository.DbConnect;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author KhanhCT
 */
public class QuanLyNhanVien {

    ArrayList<NhanVien> list = new ArrayList<>();

    public ArrayList<NhanVien> getAllNhanVien() {
        list.clear();
        try {
            String sql = "select MaNV, TenNV, CCCD,Email,NgaySinh, GioiTinh, DiaChi, Sdt,NgayVaoLam, VaiTro, MaCL, TrangThai from NhanVien";
            Connection con = DbConnect.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                String Ma = rs.getString(1);
                String Ten = rs.getString(2);
                String cccd = rs.getString(3);
                String Email = rs.getString(4);
                String NgaySinh = rs.getString(5);
                Boolean GioiTinh = rs.getBoolean(6);
                String DiaChi = rs.getString(7);
                String Sdt = rs.getString(8);
                String NgayVaoLam = rs.getString(9);
                String VaiTro = rs.getString(10);
                String MaCL = rs.getString(11);
                String TrangThai = rs.getString(12);
                list.add(new NhanVien(Ma, Ten, cccd, Email, NgaySinh, GioiTinh, Sdt, NgayVaoLam, VaiTro, MaCL, DiaChi, TrangThai));
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean AddNhanVien(NhanVien nhanVien) {
        try {
            String sql = "INSERT INTO NhanVien (MaNV, TenNV , CCCD , Email , NgaySinh, GioiTinh, DiaChi, Sdt,NgayVaoLam, VaiTro, MaCL, TrangThai) VALUES(?, ?, ?,?,?, ?,?, ?, ?,?, ?, ?)";
            Connection con = DbConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nhanVien.getMaNV());
            ps.setString(2, nhanVien.getTenNV());
            ps.setString(3, nhanVien.getCccd());
            ps.setString(4, nhanVien.getEmail());
            ps.setString(5, nhanVien.getNgaySinh());
            ps.setBoolean(6, nhanVien.isGioiTinh());
            ps.setString(7, nhanVien.getDiaChi());
            ps.setString(8, nhanVien.getSĐT());
            ps.setString(9, nhanVien.getNgayVaoLam());
            ps.setString(10, nhanVien.getVaiTro());
            ps.setString(11, nhanVien.getMaCL());
            ps.setString(12, nhanVien.getTrangThai());
            ps.executeUpdate();
            con.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteNhanVien(String ma) {
        try {
            String sql = "update NhanVien set TrangThai= N'Đã Nghỉ Làm' where MaNV = ?";
            Connection con = DbConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ma);
            ps.executeUpdate();
            con.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean UpDateNhanVien(NhanVien nhanVien) {
        try {
            String sql = "update NhanVien set TenNV = ? , CCCD = ?,Email=?, NgaySinh=?, GioiTinh=?, DiaChi=?, Sdt=?,NgayVaoLam = ?, VaiTro=?, MaCL=?, TrangThai=? where MaNV = ?";
            Connection con = DbConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nhanVien.getTenNV());
            ps.setString(2, nhanVien.getCccd());
            ps.setString(3, nhanVien.getEmail());
            ps.setString(4, nhanVien.getNgaySinh());
            ps.setBoolean(5, nhanVien.isGioiTinh());
            ps.setString(6, nhanVien.getDiaChi());
            ps.setString(7, nhanVien.getSĐT());
            ps.setString(8, nhanVien.getNgayVaoLam());
            ps.setString(9, nhanVien.getVaiTro());
            ps.setString(10, nhanVien.getMaCL());
            ps.setString(11, nhanVien.getTrangThai());
            ps.setString(12, nhanVien.getMaNV());
            ps.executeUpdate();
            con.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<NhanVien> seachNhanVien(String maa) {
//        ArrayList<NhanVien> listSeach = new ArrayList<>();
        list.clear();
        try {
            String sql = "select MaNV, TenNV, CCCD,Email,NgaySinh, GioiTinh, DiaChi, Sdt,NgayVaoLam, VaiTro, MaCL, TrangThai from NhanVien where MaNV like ?";
            Connection con = DbConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maa);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String Ma = rs.getString(1);
                String Ten = rs.getString(2);
                String cccd = rs.getString(3);
                String Email = rs.getString(4);
                String NgaySinh = rs.getString(5);
                Boolean GioiTinh = rs.getBoolean(6);
                String DiaChi = rs.getString(7);
                String Sdt = rs.getString(8);
                String NgayVaoLam = rs.getString(9);
                String VaiTro = rs.getString(10);
                String MaCL = rs.getString(11);
                String TrangThai = rs.getString(12);
                list.add(new NhanVien(Ma, Ten, cccd, Email, NgaySinh, GioiTinh, Sdt, NgayVaoLam, VaiTro, MaCL, DiaChi, TrangThai));
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<NhanVien> seachMaCLNhanVien(String maa) {
//        ArrayList<NhanVien> listSeach = new ArrayList<>();
        list.clear();
        try {
            String sql = "select MaNV, TenNV, CCCD,Email,NgaySinh, GioiTinh, DiaChi, Sdt,NgayVaoLam, VaiTro, MaCL, TrangThai from NhanVien where MaCL like ?";
            Connection con = DbConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maa);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String Ma = rs.getString(1);
                String Ten = rs.getString(2);
                String cccd = rs.getString(3);
                String Email = rs.getString(4);
                String NgaySinh = rs.getString(5);
                Boolean GioiTinh = rs.getBoolean(6);
                String DiaChi = rs.getString(7);
                String Sdt = rs.getString(8);
                String NgayVaoLam = rs.getString(9);
                String VaiTro = rs.getString(10);
                String MaCL = rs.getString(11);
                String TrangThai = rs.getString(12);
                list.add(new NhanVien(Ma, Ten, cccd, Email, NgaySinh, GioiTinh, Sdt, NgayVaoLam, VaiTro, MaCL, DiaChi, TrangThai));
              }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
//--------------------------------------------Phần Tai Khoan--------------------------------------------------
    ArrayList<TaiKhoan> listTaiKhoan = new ArrayList<>();

    public ArrayList<TaiKhoan> getAllTaiKhoan() {
        listTaiKhoan.clear();
        try {
            String sql = "select MaTK, TenDangNhap,MatKhau,MaNV from TaiKhoan";
            Connection con = DbConnect.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                String ma = rs.getString(1);
                String Ten = rs.getString(2);
                String matKhau = rs.getString(3);
                
                String maNV = rs.getString(4);
                listTaiKhoan.add(new TaiKhoan(ma, Ten, matKhau, maNV));
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTaiKhoan;
    }

    public ArrayList<TaiKhoan> SeachTaiKhoan(String ma) {
        listTaiKhoan.clear();
        try {
            String sql = "select MaTK, TenDangNhap,MatKhau,MaNV from TaiKhoan where MaTK = ?";
            Connection con = DbConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ma);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String Ma = rs.getString(1);
                String Ten = rs.getString(2);
                String matKhau = rs.getString(3);
                String maNV = rs.getString(4);
                listTaiKhoan.add(new TaiKhoan(Ma, Ten, matKhau, maNV));
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTaiKhoan;
    }

    public ArrayList<TaiKhoan> SeachTKhoanNhanVien(String MaNV) {
        listTaiKhoan.clear();
        try {
            String sql = "select MaTK, TenDangNhap,MatKhau,MaNV from TaiKhoan where MaNV = ?";
            Connection con = DbConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, MaNV);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String Ma = rs.getString(1);
                String Ten = rs.getString(2);
                String matKhau = rs.getString(3);
                String maNV = rs.getString(4);
                listTaiKhoan.add(new TaiKhoan(Ma, Ten, matKhau, maNV));
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTaiKhoan;
    }

    public boolean AddTaiKhoan(TaiKhoan taiKhoan) {
        try {
            String sql = "INSERT INTO TaiKhoan (MaTK, TenDangNhap, MatKhau, MaNV) VALUES(?,?,?,?)";
            Connection con = DbConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, taiKhoan.getMaTK());
            ps.setString(2, taiKhoan.getTenDN());
            ps.setString(3, taiKhoan.getMatKhau());
            ps.setString(4, taiKhoan.getMaNV());
            ps.executeUpdate();
            con.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean DeleteTaiKhoan(String ma) {
        try {
            String sql = "delete from TaiKhoan where MaTK = ?";
            Connection con = DbConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ma);
            ps.executeUpdate();
            con.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean UpDateTaiKhoan(TaiKhoan taiKhoan) {
        try {
            String sql = "UPDATE TaiKhoan set TenDangNhap = ? ,  MatKhau = ?, MaNV = ? where MaTK = ?";
            Connection con = DbConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, taiKhoan.getTenDN());
            ps.setString(2, taiKhoan.getMatKhau());
            ps.setString(3, taiKhoan.getMaNV());
            ps.setString(4, taiKhoan.getMaTK());
            ps.executeUpdate();
            con.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

//-----------------------------------------Phần Ca Làm------------------------------ 
    ArrayList<TaiKhoan> listCaLam = new ArrayList<>();

    public ArrayList<TaiKhoan> getAllCaLam() {
        listCaLam.clear();
        try {
            String sql = "SELECT MaCL , TenCL,GioBatDau,GioKetThuc,GhiChu from CaLam";
            Connection con = DbConnect.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                String ma = rs.getString(1);
                String ten = rs.getString(2);
                String gioBatDau = rs.getString(3);
                String gioKetThuc = rs.getString(4);
                String ghichu = rs.getString(5);
                listCaLam.add(new TaiKhoan(ma, ten, gioBatDau, gioKetThuc, ghichu));
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCaLam;
    }

    public ArrayList<TaiKhoan> SeachCaLam(String ma) {
        listCaLam.clear();
        try {
            String sql = "SELECT MaCL , TenCL,GioBatDau,GioKetThuc,GhiChu from CaLam where MaCL = ?";
            Connection con = DbConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ma);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String Ma = rs.getString(1);
                String ten = rs.getString(2);
                String gioBatDau = rs.getString(3);
                String gioKetThuc = rs.getString(4);
                String ghichu = rs.getString(5);
                listCaLam.add(new TaiKhoan(Ma, ten, gioBatDau, gioKetThuc, ghichu));
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCaLam;
    }

    public boolean AddCaLam(TaiKhoan taiKhoan) {
        try {
            String sql = "INSERT INTO CaLam (MaCL, TenCL, GioBatDau, GioKetThuc, GhiChu) VALUES(?,?,?,?,?)";
            Connection con = DbConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, taiKhoan.getMaCL());
            ps.setString(2, taiKhoan.getTenCL());
            ps.setString(3, taiKhoan.getGioBatDau());
            ps.setString(4, taiKhoan.getGioKetThuc());
            ps.setString(5, taiKhoan.getGhiChu());
            ps.executeUpdate();
            con.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean DeleteCaLam(String ma) {
        try {
            String sql = "UPDATE NhanVien SET MaCL = NULL WHERE MaCL = ?\n"
                    + "DELETE FROM CaLam WHERE MaCL = ?";
            Connection con = DbConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ma);
            ps.setString(2, ma);
            ps.executeUpdate();
            con.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean UpDateCaLam(TaiKhoan taiKhoan) {
        try {
            String sql = "UPDATE CaLam set  TenCL = ?, GioBatDau =? , GioKetThuc = ? , GhiChu = ?  where MaCL = ? ";
            Connection con = DbConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, taiKhoan.getTenCL());
            ps.setString(2, taiKhoan.getGioBatDau());
            ps.setString(3, taiKhoan.getGioKetThuc());
            ps.setString(4, taiKhoan.getGhiChu());
            ps.setString(5, taiKhoan.getMaCL());
            ps.executeUpdate();
            con.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
