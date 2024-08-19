/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Repository.DbConnect;
import java.sql.*;

/**
 *
 * @author TienBB
 */
public class QuanLyDangNhap {

    private Connection cn;

    public QuanLyDangNhap() {
        try {
            cn = DbConnect.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getMaNhanVienByEmail(String email) {
        String maNV = null;
        try {
            String sql = "SELECT MaNV FROM NhanVien WHERE Email = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                maNV = rs.getString("MaNV");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maNV;
    }

    public String getMaNhanVienByMaXacNhan(String maXN) {
        String maNV = null;
        try {
            String sql = "SELECT MaNV FROM NhanVien WHERE VerificationCode = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, maXN);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                maNV = rs.getString("MaNV");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maNV;
    }

    public void luuMaXacNhan(String maNV, String verificationCode) {
        try {
            String sql = "UPDATE NhanVien SET VerificationCode = ? WHERE MaNV = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, verificationCode);
            ps.setString(2, maNV);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean xacNhanMa(String maNV, String verificationCode) {
        try {
            String sql = "SELECT * FROM NhanVien WHERE MaNV = ? AND VerificationCode = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, maNV);
            ps.setString(2, verificationCode);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // Trả về true nếu có kết quả, tức là mã xác nhận đúng
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Trả về false nếu có lỗi xảy ra
        }
    }

    public String xacNhanEmail(String ma) {
        String email = null;
        try {
            String sql = "SELECT Email FROM NhanVien WHERE VerificationCode = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, ma);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                email = rs.getString("Email");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return email;
    }

    public String xacNhanNhanVien(String ma, String email) {
        String maNV = null;
        try {
            String sql = "SELECT MaNV FROM NhanVien WHERE VerificationCode = ? and Email = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, ma);
            ps.setString(2, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                maNV = rs.getString("MaNV");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maNV;
    }

    public boolean updateMatKhau(String maNV, String matKhauMoi) {
        try {
            String sql = "UPDATE TaiKhoan SET MatKhau = ? WHERE MaNV = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, matKhauMoi);
            ps.setString(2, maNV);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Trả về false nếu có lỗi xảy ra
        }
    }
}
