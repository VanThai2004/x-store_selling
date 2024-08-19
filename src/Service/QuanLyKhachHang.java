/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.KhachHang;
import Repository.DbConnect;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author KhanhCT
 */
public class QuanLyKhachHang {

    ArrayList<KhachHang> list = new ArrayList<>();
    ArrayList<KhachHang> listKhachVip = new ArrayList<>();

    public ArrayList<KhachHang> getAllKhachHang() {
        list.clear();
        try {
            String sql = "select KhachHang.MaKH,TenKH,Sdt,KhachHang.NgayTao,NgaySinh,gioiTinh,KhachHang.TrangThai,DiaChi,COUNT(HoaDon.MaHD) from KhachHang\n"
                    + "left join HoaDon on HoaDon.MaKH = KhachHang.MaKH\n"
                    + "group by KhachHang.MaKH,TenKH,Sdt,KhachHang.NgayTao,NgaySinh,gioiTinh,KhachHang.TrangThai,DiaChi";
            Connection con = DbConnect.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                String ma = rs.getString(1);
                String ten = rs.getString(2);
                String sdt = rs.getString(3);
                String NgayTao = rs.getString(4);
                String NgaySinh = rs.getString(5);
                Boolean gioiTinh = rs.getBoolean(6);
                Boolean trangThai = rs.getBoolean(7);
                String diaChi = rs.getString(8);
                Integer LuotMua = rs.getInt(9);
                list.add(new KhachHang(ma, ten, sdt, NgayTao, NgaySinh, gioiTinh, trangThai, diaChi, LuotMua));
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<KhachHang> loadSeachKhachHang(String maKH) {
        list.clear();
        try {
            String sql = "select KhachHang.MaKH,TenKH,Sdt,KhachHang.NgayTao,NgaySinh,gioiTinh,KhachHang.TrangThai,DiaChi,COUNT(HoaDon.MaHD) from KhachHang\n"
                    + "left join HoaDon on HoaDon.MaKH = KhachHang.MaKH\n"
                    + "WHERE KhachHang.MaKH = ? \n"
                    + "group by KhachHang.MaKH,TenKH,Sdt,KhachHang.NgayTao,NgaySinh,gioiTinh,KhachHang.TrangThai,DiaChi";
            Connection con = DbConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maKH);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String ma = rs.getString(1);
                String ten = rs.getString(2);
                String sdt = rs.getString(3);
                String NgayTao = rs.getString(4);
                String NgaySinh = rs.getString(5);
                Boolean gioiTinh = rs.getBoolean(6);
                Boolean trangThai = rs.getBoolean(7);
                String diaChi = rs.getString(8);
                Integer LuotMua = rs.getInt(9);
                list.add(new KhachHang(ma, ten, sdt, NgayTao, NgaySinh, gioiTinh, trangThai, diaChi, LuotMua));
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<KhachHang> getAllKhachVip() {
        listKhachVip.clear();
        try {
            String sql = "select MaKH,TenKH,Sdt,NgayTao,NgaySinh,gioiTinh,TrangThai,DiaChi from KhachHang where TrangThai= 1";

            Connection con = DbConnect.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                String ma = rs.getString(1);
                String ten = rs.getString(2);
                String sdt = rs.getString(3);
                String NgayTao = rs.getString(4);
                String NgaySinh = rs.getString(5);
                Boolean gioiTinh = rs.getBoolean(6);
                Boolean trangThai = rs.getBoolean(7);
                String diaChi = rs.getString(8);
                listKhachVip.add(new KhachHang(ma, ten, sdt, NgayTao, NgaySinh, gioiTinh, trangThai, diaChi, null));

            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listKhachVip;
    }

    public ArrayList<KhachHang> loadSeachKhachVip(String maKH) {
        listKhachVip.clear();
        try {
            String sql = "select MaKH,TenKH,Sdt,NgayTao,NgaySinh,gioiTinh,TrangThai,DiaChi from KhachHang where MaKH =? and TrangThai= 1";

            Connection con = DbConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maKH);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String ma = rs.getString(1);
                String ten = rs.getString(2);
                String sdt = rs.getString(3);
                String NgayTao = rs.getString(4);
                String NgaySinh = rs.getString(5);
                Boolean gioiTinh = rs.getBoolean(6);
                Boolean trangThai = rs.getBoolean(7);
                String diaChi = rs.getString(8);
                listKhachVip.add(new KhachHang(ma, ten, sdt, NgayTao, NgaySinh, gioiTinh, trangThai, diaChi, null));

            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listKhachVip;
    }

    public boolean ADDKhachHang(KhachHang khachHang) {
        try {
            String sql = "insert into KhachHang(MaKH,TenKH,Sdt,NgayTao,NgaySinh,gioiTinh,TrangThai,DiaChi)\n"
                    + "values (?,?,?,?,?,?,?,?);";
            Connection con = DbConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, khachHang.getMaKH());
            ps.setString(2, khachHang.getTenKH());
            ps.setString(3, khachHang.getSĐT());
            ps.setString(4, khachHang.getNgayTao());
            ps.setString(5, khachHang.getNgaySinh());
            ps.setBoolean(6, khachHang.isGioiTinh());
            ps.setBoolean(7, khachHang.isTrangThai());
            ps.setString(8, khachHang.getDiaChi());
            ps.executeUpdate();
            con.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

//    public boolean deleteKhachHang(String maKh) {
//        try {
//            String sql = "delete from UuDai where MaVC in (select MaVC from UuDai \n"
//                    + "join KhachHang on KhachHang.MaKH = UuDai.MaKH\n"
//                    + "where KhachHang.MaKH = ?);\n"
//                    + "delete from CTHD where MaHD in (SELECT MaHD from HoaDon\n"
//                    + "join KhachHang on KhachHang.MaKH = HoaDon.MaKH\n"
//                    + "where KhachHang.MaKH = ?);\n"
//                    + "delete from HoaDon where MaHD in (SELECT MaHD from HoaDon\n"
//                    + "join KhachHang on KhachHang.MaKH = HoaDon.MaKH\n"
//                    + "where KhachHang.MaKH = ?);\n"
//                    + "delete from KhachHang where MaKH = ?;";
//            Connection con = DbConnect.getConnection();
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setString(1, maKh);
//            ps.setString(2, maKh);
//            ps.setString(3, maKh);
//            ps.setString(4, maKh);
//            ps.executeUpdate();
//            con.close();
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
    public boolean upDate(KhachHang khachHang) {
        try {
            String sql = "update KhachHang set TenKH= ? , Sdt= ?,NgayTao = ?,NgaySinh=? , gioiTinh= ? , TrangThai= ? , DiaChi= ? where MaKH = ?";
            Connection con = DbConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, khachHang.getTenKH());
            ps.setString(2, khachHang.getSĐT());
            ps.setString(3, khachHang.getNgayTao());
            ps.setString(4, khachHang.getNgaySinh());
            ps.setBoolean(5, khachHang.isGioiTinh());
            ps.setBoolean(6, khachHang.isTrangThai());
            ps.setString(7, khachHang.getDiaChi());
            ps.setString(8, khachHang.getMaKH());
            ps.executeUpdate();
            con.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
