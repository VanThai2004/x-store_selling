/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.HoaDon;
import Repository.DbConnect;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LongThank
 */
public class QuanLyDoanhThu {
    
    int i = 0;
    
    public Integer HDToday() {
        try {
            Connection conn = DbConnect.getConnection();
            String sql = "SELECT COUNT(MaHD) AS SoHoaDon FROM HoaDon WHERE CONVERT(date, NgayTao) = CONVERT(date, GETDATE()) AND TrangThai LIKE N'Đã thanh toán'";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                i = Integer.valueOf(rs.getString("SoHoaDon")) == null ? 0 : Integer.valueOf(rs.getString("SoHoaDon"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyDoanhThu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }
    
    float j = 0;
    
    public Float DoanhThuToday() {
        try {
            Connection conn = DbConnect.getConnection();
            String sql = "SELECT sum(TongTien) AS TongTien FROM HoaDon WHERE CONVERT(date, NgayTao) = CONVERT(date, GETDATE()) AND TrangThai LIKE N'Đã thanh toán'";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                String a = rs.getString("TongTien");
                if (a == null || a.isEmpty()) {
                    j = 0;
                } else {
                    j = Float.valueOf(a);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyDoanhThu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return j;
    }
    
    float k = 0;
    
    public Float loiNhuan() {
        try {
            Connection conn = DbConnect.getConnection();
            String sql = "SELECT (sum(GiaSau*CTHD.SoLuong)-sum(GiaNhap*CTHD.SoLuong)) AS LoiNhuan FROM CTHD\n"
                    + "JOIN CTSP ON CTSP.IDSP = CTHD.IdSP\n"
                    + "JOIN HoaDon ON HoaDon.MaHD = CTHD.MaHD";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                String a = rs.getString("LoiNhuan");
                if (a == null && a.isEmpty()) {
                    k = 0;
                } else {
                    k = Float.valueOf(a);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyDoanhThu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return k;
    }
    float LNTK = 0;
    
    public Float loiNhuanTK(String NgayBD, String NgayKT) {
        try {
            Connection conn = DbConnect.getConnection();
            String sql = "SELECT (sum(GiaSau*CTHD.SoLuong)-sum(GiaNhap*CTHD.SoLuong)) AS LoiNhuann FROM CTHD\n"
                    + "JOIN CTSP ON CTSP.IDSP = CTHD.IdSP\n"
                    + "JOIN HoaDon ON HoaDon.MaHD = CTHD.MaHD\n"
                    + "WHERE HoaDon.NgayTao >= ? and HoaDon.NgayTao <= ? and TrangThai LIKE N'Đã thanh toán'";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, NgayBD);
            ps.setString(2, NgayKT);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String a = rs.getString("LoiNhuann");
                if (a == null || a.isEmpty()) {
                    LNTK = 0;
                } else {
                    LNTK = Float.valueOf(a);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyDoanhThu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return LNTK;
    }
    float h = 0;
    
    public Float laiToDay() {
        try {
            Connection conn = DbConnect.getConnection();
            String sql = "SELECT (sum(TongTien) - (SELECT sum(GiaNhap*CTHD.SoLuong) FROM CTHD JOIN CTSP ON CTSP.IdSP = CTHD.IdSP JOIN HoaDon ON HoaDon.MaHD = CTHD.MaHD \n"
                    + "						WHERE CONVERT(date, NgayTao) = CONVERT(date, GETDATE()) AND TrangThai LIKE N'Đã thanh toán'\n"
                    + "						)) AS Lai FROM HoaDon\n"
                    + "WHERE CONVERT(date, NgayTao) = CONVERT(date, GETDATE()) AND TrangThai LIKE N'Đã thanh toán'";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                String a = rs.getString("Lai");
                if (a == null || a.isEmpty()) {
                    h = 0;
                } else {
                    h = Float.valueOf(a);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyDoanhThu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return h;
    }
    float T = 0;
    
    public Float TongDoanhThu() {
        try {
            Connection conn = DbConnect.getConnection();
            String sql = "SELECT sum(TongTien) AS TongDoanhThu FROM HoaDon WHERE TrangThai LIKE N'Đã thanh toán'";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                String a = rs.getString("TongDoanhThu");
                if (a == null || a.isEmpty()) {
                    T = 0;
                } else {
                    T = Float.valueOf(a);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyDoanhThu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return T;
    }
    float TK = 0;
    
    public Float TongDoanhThuTimKiem(String NgayBD, String NgayKT) {
        try {
            Connection conn = DbConnect.getConnection();
            String sql = "SELECT sum(TongTien) AS TongDoanhThuu FROM HoaDon WHERE NgayTao >= ? and NgayTao <= ? and   TrangThai LIKE N'Đã thanh toán'";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, NgayBD);
            ps.setString(2, NgayKT);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String a = rs.getString("TongDoanhThuu");
                if (a == null || a.isEmpty()) {
                    TK = 0;
                } else {
                    TK = Float.valueOf(a);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyDoanhThu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return TK;
    }
    float DTT = 0;
    
    public Float TongDoanhThuThang() {
        try {
            Connection conn = DbConnect.getConnection();
            String sql = "SELECT sum(TongTien) AS TongDoanhThuu FROM HoaDon WHERE MONTH(NgayTao) = 4 and   TrangThai LIKE N'Đã thanh toán'";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                String a = rs.getString("TongDoanhThuu");
                if (a == null || a.isEmpty()) {
                    DTT = 0;
                } else {
                    DTT = Float.valueOf(a);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyDoanhThu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return DTT;
    }
    
    float DTTKT = 0;
    
    public Float TimKiemTongDoanhThuThang(String Thang) {
        try {
            Connection conn = DbConnect.getConnection();
            String sql = "SELECT sum(TongTien) AS TongDoanhThuu FROM HoaDon WHERE MONTH(NgayTao) = ? and   TrangThai LIKE N'Đã thanh toán'";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, Thang);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String a = rs.getString("TongDoanhThuu");
                if (a == null || a.isEmpty()) {
                    DTTKT = 0;
                } else {
                    DTTKT = Float.valueOf(a);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyDoanhThu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return DTTKT;
    }
    
    ArrayList<HoaDon> listHD = new ArrayList<>();
    
    public ArrayList<HoaDon> getListHD() {
        listHD.clear();
        try {
            Connection conn = DbConnect.getConnection();
            String sql = "SELECT MaHD, NgayTao, TongTien FROM HoaDon WHERE   TrangThai = N'Đã thanh toán'";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getString("MaHD"));
                hd.setNgayTao(rs.getString("NgayTao"));
                hd.setTongTien(rs.getString("TongTien"));
                listHD.add(hd);
            }
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyDoanhThu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listHD;
    }
    
    public ArrayList<HoaDon> getListHDTK(String NgayBD, String NgayKT) {
        listHD.clear();
        try {
            Connection conn = DbConnect.getConnection();
            String sql = "SELECT MaHD, NgayTao, TongTien FROM HoaDon WHERE NgayTao >= ? and NgayTao <= ? and TrangThai = N'Đã thanh toán'";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, NgayBD);
            ps.setString(2, NgayKT);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getString("MaHD"));
                hd.setNgayTao(rs.getString("NgayTao"));
                hd.setTongTien(rs.getString("TongTien"));
                listHD.add(hd);
            }
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyDoanhThu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listHD;
    }
    
    public ArrayList<HoaDon> getListHDThaang(String Thang) {
        listHD.clear();
        try {
            Connection conn = DbConnect.getConnection();
            String sql = "SELECT MaHD, NgayTao, TongTien FROM HoaDon WHERE  MONTH(HoaDon.NgayTao) = ? and TrangThai = N'Đã thanh toán'";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, Thang);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getString("MaHD"));
                hd.setNgayTao(rs.getString("NgayTao"));
                hd.setTongTien(rs.getString("TongTien"));
                listHD.add(hd);
            }
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyDoanhThu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listHD;
    }
    
    public Float laiXuat() {
        float m = 0;
        try {
            Connection conn = DbConnect.getConnection();
            String sql = "SELECT (sum(GiaSau*CTHD.SoLuong)-sum(GiaNhap*CTHD.SoLuong))/sum(TongTien)*100 AS LaiXuat FROM CTHD\n"
                    + "JOIN CTSP ON CTSP.IDSP = CTHD.IdSP\n"
                    + "JOIN HoaDon ON HoaDon.MaHD = CTHD.MaHD";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                String a = rs.getString("LaiXuat");
                if (a == null || a.isEmpty()) {
                    m = 0;
                } else {
                    m = Float.valueOf(a);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyDoanhThu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }
    
    public Float laiXuatTK(String NgayBD, String NgayKT) {
        float LXTK = 0;
        try {
            Connection conn = DbConnect.getConnection();
            String sql = "SELECT (sum(GiaSau*CTHD.SoLuong)-sum(GiaNhap*CTHD.SoLuong))/sum(TongTien)*100 AS LaiXuatt FROM CTHD\n"
                    + "JOIN CTSP ON CTSP.IDSP = CTHD.IdSP\n"
                    + "JOIN HoaDon ON HoaDon.MaHD = CTHD.MaHD\n"
                    + "WHERE NgayTao >= ? and NgayTao <= ? and TrangThai = N'Đã thanh toán'";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, NgayBD);
            ps.setString(2, NgayKT);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String a = rs.getString("LaiXuatt");
                if (a == null || a.isEmpty()) {
                    LXTK = 0;
                } else {
                    LXTK = Float.valueOf(a);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyDoanhThu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return LXTK;
    }
    
    public Float laiXuatTKThang(String Thang) {
        float LXThang = 0;
        try {
            Connection conn = DbConnect.getConnection();
            String sql = "SELECT (sum(GiaSau*CTHD.SoLuong)-sum(GiaNhap*CTHD.SoLuong))/sum(TongTien)*100 AS LaiXuatt FROM CTHD\n"
                    + "JOIN CTSP ON CTSP.IDSP = CTHD.IdSP\n"
                    + "JOIN HoaDon ON HoaDon.MaHD = CTHD.MaHD\n"
                    + "WHERE MONTH(HoaDon.NgayTao) = ? and TrangThai = N'Đã thanh toán'";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, Thang);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String a = rs.getString("LaiXuatt");
                if (a == null || a.isEmpty()) {
                    LXThang = 0;
                } else {
                    LXThang = Float.valueOf(a);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyDoanhThu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return LXThang;
    }
    float LNThang = 0;
    
    public Float loiNhuanTKThang(String Thang) {
        try {
            Connection conn = DbConnect.getConnection();
            String sql = "SELECT (sum(GiaSau*CTHD.SoLuong)-sum(GiaNhap*CTHD.SoLuong)) AS LoiNhuant FROM CTHD\n"
                    + "JOIN CTSP ON CTSP.IDSP = CTHD.IdSP\n"
                    + "JOIN HoaDon ON HoaDon.MaHD = CTHD.MaHD\n"
                    + "WHERE MONTH(HoaDon.NgayTao) = ? and TrangThai LIKE N'Đã thanh toán'";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, Thang);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String a = rs.getString("LoiNhuant");
                if (a == null || a.isEmpty()) {
                    LNThang = 0;
                } else {
                    LNThang = Float.valueOf(a);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyDoanhThu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return LNThang;
    }
    
    public ArrayList<HoaDon> getListSP(String ngayBD, String ngayKT) {
        ArrayList<HoaDon> listSP = new ArrayList<>();
        try {
            Connection con = DbConnect.getConnection();
            String sql = "select b.idsp,d.masp,d.tensp,sum(b.soluong) soluong,sum(giasau) tong from hoadon a\n"
                    + "join cthd b on a.mahd=b.mahd\n"
                    + "join ctsp c on c.idsp=b.idsp\n"
                    + "join sanpham d on d.MaSP=c.MaSP\n"
                    + "WHERE NgayTao >= '"+ngayBD+"' and NgayTao <= '"+ngayKT+"'\n"
                    + "group by b.idsp,d.masp,d.tensp";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setIdSP(rs.getString("idsp"));
                hd.setMaSP(rs.getString("masp"));
                hd.setTenSP(rs.getString("tensp"));
                hd.setSoLuong(rs.getString("soluong"));
                hd.setTongTien(rs.getString("tong"));
                listSP.add(hd);
            }
            con.close();
        } catch (Exception e) {
        }
        return listSP;
    }
     public ArrayList<HoaDon> getListSPT() {
        ArrayList<HoaDon> listSPT = new ArrayList<>();
        try {
            Connection con = DbConnect.getConnection();
            String sql = "select b.idsp,d.masp,d.tensp,sum(b.soluong) soluong,sum(giasau) tong from hoadon a\n"
                    + "join cthd b on a.mahd=b.mahd\n"
                    + "join ctsp c on c.idsp=b.idsp\n"
                    + "join sanpham d on d.MaSP=c.MaSP\n"
                    
                    + "group by b.idsp,d.masp,d.tensp";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setIdSP(rs.getString("idsp"));
                hd.setMaSP(rs.getString("masp"));
                hd.setTenSP(rs.getString("tensp"));
                hd.setSoLuong(rs.getString("soluong"));
                hd.setTongTien(rs.getString("tong"));
                listSPT.add(hd);
            }
            con.close();
        } catch (Exception e) {
        }
        return listSPT;
    }
}
