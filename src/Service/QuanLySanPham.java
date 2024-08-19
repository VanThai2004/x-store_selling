/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.SanPham;
import Repository.DbConnect;
import java.util.ArrayList;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KhanhCT
 */
public class QuanLySanPham {

    ArrayList<SanPham> listSanPham = new ArrayList<>();
    ArrayList<SanPham> listCTCP = new ArrayList<>();
    ArrayList<SanPham> listThuocTinh = new ArrayList<>();

    public ArrayList<SanPham> getListSanPham() {
        listSanPham.clear();
        try {
            Connection conn = DbConnect.getConnection();
            String sql = "SELECT * FROM SanPham";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSanPham(rs.getString("MaSP"));
                sp.setTenSanPham(rs.getString("TenSP"));
                String a = rs.getString("SoLuongTong");
                if (a == null || a.isEmpty() || Integer.valueOf(a) == 0) {
                    sp.setTrangThai("Hết hàng");
                } else {
                    sp.setTrangThai("Còn hàng");
                }
                listSanPham.add(sp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listSanPham;
    }

    String soLuongTong;

    public String getSoLuongTong(String a) {
        try {
            Connection conn = DbConnect.getConnection();
            String sql = "SELECT SoLuongTong FROM SanPham where MaSP = " + "'" + a + "'";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                String b = rs.getString("SoLuongTong");
                if (b == null || b.isEmpty()) {
                    soLuongTong = "0";
                } else {
                    soLuongTong = b;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
        return soLuongTong;
    }

//    public void conHang(String ma, String b) {
//        try {
//            Connection conn = DbConnect.getConnection();
//            String sql = "UPDATE SanPham SET TrangThai =  ? WHERE MaSP = "+"'"+ma+"'";
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setInt(1, Integer.valueOf(b) == 0 ? 0 : 1);
//            ps.executeUpdate();
//            conn.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public void AddSP(SanPham sp) {
        try {
            Connection conn = DbConnect.getConnection();
            String sql = "INSERT INTO SanPham (MaSP, TenSP, SoLuongTong, TrangThai) VALUES (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, sp.getMaSanPham());
            ps.setString(2, sp.getTenSanPham());
            ps.setInt(3, 0);
            ps.setInt(4, Integer.valueOf(sp.getTrangThai().equals("Còn hàng") ? "1" : "0"));
            ps.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void TongSoLuongSP(String a) {
        try {
            Connection conn = DbConnect.getConnection();
            String sql = "UPDATE SanPham SET SoLuongTong = (SELECT sum(SoLuong) FROM CTSP WHERE MaSP = ?) WHERE MaSP = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, a);
            ps.setString(2, a);
            ps.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public void UpSoLuongTong(String a, String b) {
//        try {
//            Connection conn = DbConnect.getConnection();
//            String sql = "UPDATE SanPham SET SoLuongTong = ? where MaSP = " + "'" + a + "'";
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setInt(1, Integer.valueOf(b));
//            ps.executeUpdate();
//            conn.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public ArrayList<SanPham> getListCTSPTT() {
        listCTCP.clear();
        try {
            Connection conn = DbConnect.getConnection();
            String sql = "SELECT CTSP.IdSP, CTSP.MaSP, NgayNhap, TenMauSac, TenSize, TenChatLieu, TenHang, HinhAnh\n"
                    + "FROM CTSP\n"
                    + "JOIN MauSac ON MauSac.IdMauSac = CTSP.IdMauSac\n"
                    + "JOIN Size ON Size.IdSize = CTSP.IdSize\n"
                    + "JOIN ChatLieu ON ChatLieu.IdChatLieu = CTSP.IdChatLieu\n"
                    + "JOIN Hang ON Hang.IdHang = CTSP.IdHang";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setIdspct(rs.getString("IdSP"));
                sp.setMaSanPham(rs.getString("MaSP"));
                sp.setNgayNhap(rs.getString("NgayNhap"));
                sp.setMauSac(rs.getString("TenMauSac"));
                sp.setSize(rs.getString("TenSize"));
                sp.setChatLieu(rs.getString("TenChatLieu"));
                sp.setHang(rs.getString("TenHang"));
                sp.setHinhAnh(rs.getString("HinhAnh"));
                listCTCP.add(sp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCTCP;
    }

    public ArrayList<SanPham> getListCTSP() {
        listCTCP.clear();
        try {
            Connection conn = DbConnect.getConnection();
            String sql = "SELECT CTSP.IdSP, CTSP.MaSP, NgayNhap, TenMauSac, TenSize, TenChatLieu, TenHang, SoLuong, GiaNhap, Gia.GiaBan, HinhAnh\n"
                    + "FROM CTSP\n"
                    + "JOIN MauSac ON MauSac.IdMauSac = CTSP.IdMauSac\n"
                    + "JOIN Size ON Size.IdSize = CTSP.IdSize\n"
                    + "JOIN ChatLieu ON ChatLieu.IdChatLieu = CTSP.IdChatLieu\n"
                    + "JOIN Hang ON Hang.IdHang = CTSP.IdHang\n"
                    + "JOIN (\n"
                    + "		SELECT g.IdSP, g.GiaBan, g.NgayBatDau FROM Gia AS g WHERE g.NgayBatDau <= GETDATE() AND g.IdSP IN (\n"
                    + "			SELECT g2.IdSP FROM Gia AS g2 WHERE g2.NgayBatDau <= GETDATE() GROUP BY g2.IdSP HAVING MAX(g2.NgayBatDau) = g.NgayBatDau)\n"
                    + ") AS Gia ON Gia.IdSP = CTSP.IdSP\n"
                    + "WHERE SoLuong > 0\n"
                    + "ORDER BY CTSP.IdSP asc";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setIdspct(rs.getString("IdSP"));
                sp.setMaSanPham(rs.getString("MaSP"));
                sp.setNgayNhap(rs.getString("NgayNhap"));
                sp.setMauSac(rs.getString("TenMauSac"));
                sp.setSize(rs.getString("TenSize"));
                sp.setChatLieu(rs.getString("TenChatLieu"));
                sp.setHang(rs.getString("TenHang"));
                sp.setSoLuong(rs.getString("SoLuong"));
                sp.setGiaNhap(rs.getString("GiaNhap"));
                sp.setGiaBan(rs.getString("GiaBan"));
                sp.setHinhAnh(rs.getString("HinhAnh"));
                listCTCP.add(sp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCTCP;
    }

    public ArrayList<SanPham> getListCTSPDaHet() {
        listCTCP.clear();
        try {
            Connection conn = DbConnect.getConnection();
            String sql = "SELECT CTSP.IdSP, CTSP.MaSP, NgayNhap, TenMauSac, TenSize, TenChatLieu, TenHang, SoLuong, GiaNhap, Gia.GiaBan, HinhAnh\n"
                    + "FROM CTSP\n"
                    + "JOIN MauSac ON MauSac.IdMauSac = CTSP.IdMauSac\n"
                    + "JOIN Size ON Size.IdSize = CTSP.IdSize\n"
                    + "JOIN ChatLieu ON ChatLieu.IdChatLieu = CTSP.IdChatLieu\n"
                    + "JOIN Hang ON Hang.IdHang = CTSP.IdHang\n"
                    + "JOIN (\n"
                    + "		SELECT g.IdSP, g.GiaBan, g.NgayBatDau FROM Gia AS g WHERE g.NgayBatDau <= GETDATE() AND g.IdSP IN (\n"
                    + "			SELECT g2.IdSP FROM Gia AS g2 WHERE g2.NgayBatDau <= GETDATE() GROUP BY g2.IdSP HAVING MAX(g2.NgayBatDau) = g.NgayBatDau)\n"
                    + ") AS Gia ON Gia.IdSP = CTSP.IdSP\n"
                    + "WHERE SoLuong = 0\n"
                    + "ORDER BY CTSP.IdSP asc";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setIdspct(rs.getString("IdSP"));
                sp.setMaSanPham(rs.getString("MaSP"));
                sp.setNgayNhap(rs.getString("NgayNhap"));
                sp.setMauSac(rs.getString("TenMauSac"));
                sp.setSize(rs.getString("TenSize"));
                sp.setChatLieu(rs.getString("TenChatLieu"));
                sp.setHang(rs.getString("TenHang"));
                sp.setSoLuong(rs.getString("SoLuong"));
                sp.setGiaNhap(rs.getString("GiaNhap"));
                sp.setGiaBan(rs.getString("GiaBan"));
                sp.setHinhAnh(rs.getString("HinhAnh"));
                listCTCP.add(sp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCTCP;
    }

    public ArrayList<SanPham> getListCTSPTheoMaSP(String a) {
        listCTCP.clear();
        try {
            Connection conn = DbConnect.getConnection();
            String sql = "SELECT CTSP.IdSP, CTSP.MaSP, NgayNhap, TenMauSac, TenSize, TenChatLieu, TenHang, SoLuong, GiaNhap, Gia.GiaBan, HinhAnh\n"
                    + "FROM CTSP\n"
                    + "JOIN MauSac ON MauSac.IdMauSac = CTSP.IdMauSac\n"
                    + "JOIN Size ON Size.IdSize = CTSP.IdSize\n"
                    + "JOIN ChatLieu ON ChatLieu.IdChatLieu = CTSP.IdChatLieu\n"
                    + "JOIN Hang ON Hang.IdHang = CTSP.IdHang\n"
                    + "JOIN (\n"
                    + "		SELECT g.IdSP, g.GiaBan, g.NgayBatDau FROM Gia AS g WHERE g.NgayBatDau <= GETDATE() AND g.IdSP IN (\n"
                    + "			SELECT g2.IdSP FROM Gia AS g2 WHERE g2.NgayBatDau <= GETDATE() GROUP BY g2.IdSP HAVING MAX(g2.NgayBatDau) = g.NgayBatDau)\n"
                    + ") AS Gia ON Gia.IdSP = CTSP.IdSP\n"
                    + "WHERE CTSP.MaSP = " + "'" + a + "' AND SoLuong > 0\n"
                    + "ORDER BY CTSP.IdSP asc\n";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setIdspct(rs.getString("IdSP"));
                sp.setMaSanPham(rs.getString("MaSP"));
                sp.setNgayNhap(rs.getString("NgayNhap"));
                sp.setMauSac(rs.getString("TenMauSac"));
                sp.setSize(rs.getString("TenSize"));
                sp.setChatLieu(rs.getString("TenChatLieu"));
                sp.setHang(rs.getString("TenHang"));
                sp.setSoLuong(rs.getString("SoLuong"));
                sp.setGiaNhap(rs.getString("GiaNhap"));
                sp.setGiaBan(rs.getString("GiaBan"));
                sp.setHinhAnh(rs.getString("HinhAnh"));
                listCTCP.add(sp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCTCP;
    }

    public ArrayList<SanPham> getListCTSPDaHetTheoMaSP(String a) {
        listCTCP.clear();
        try {
            Connection conn = DbConnect.getConnection();
            String sql = "SELECT CTSP.IdSP, CTSP.MaSP, NgayNhap, TenMauSac, TenSize, TenChatLieu, TenHang, SoLuong, GiaNhap, Gia.GiaBan, HinhAnh\n"
                    + "FROM CTSP\n"
                    + "JOIN MauSac ON MauSac.IdMauSac = CTSP.IdMauSac\n"
                    + "JOIN Size ON Size.IdSize = CTSP.IdSize\n"
                    + "JOIN ChatLieu ON ChatLieu.IdChatLieu = CTSP.IdChatLieu\n"
                    + "JOIN Hang ON Hang.IdHang = CTSP.IdHang\n"
                    + "JOIN (\n"
                    + "		SELECT g.IdSP, g.GiaBan, g.NgayBatDau FROM Gia AS g WHERE g.NgayBatDau <= GETDATE() AND g.IdSP IN (\n"
                    + "			SELECT g2.IdSP FROM Gia AS g2 WHERE g2.NgayBatDau <= GETDATE() GROUP BY g2.IdSP HAVING MAX(g2.NgayBatDau) = g.NgayBatDau)\n"
                    + ") AS Gia ON Gia.IdSP = CTSP.IdSP\n"
                    + "WHERE CTSP.MaSP = " + "'" + a + "' AND SoLuong = 0\n"
                    + "ORDER BY CTSP.IdSP asc\n";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setIdspct(rs.getString("IdSP"));
                sp.setMaSanPham(rs.getString("MaSP"));
                sp.setNgayNhap(rs.getString("NgayNhap"));
                sp.setMauSac(rs.getString("TenMauSac"));
                sp.setSize(rs.getString("TenSize"));
                sp.setChatLieu(rs.getString("TenChatLieu"));
                sp.setHang(rs.getString("TenHang"));
                sp.setSoLuong(rs.getString("SoLuong"));
                sp.setGiaNhap(rs.getString("GiaNhap"));
                sp.setGiaBan(rs.getString("GiaBan"));
                sp.setHinhAnh(rs.getString("HinhAnh"));
                listCTCP.add(sp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCTCP;
    }

    public ArrayList<SanPham> getSelectMauSac() {
        listThuocTinh.clear();
        try {
            Connection conn = DbConnect.getConnection();
            String sql = "SELECT IdMauSac, TenMauSac FROM MauSac";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setIdMauSac(rs.getString("IdMauSac"));
                sp.setMauSac(rs.getString("TenMauSac"));
                listThuocTinh.add(sp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listThuocTinh;
    }

    public ArrayList<SanPham> getSelectSize() {
        listThuocTinh.clear();
        try {
            Connection conn = DbConnect.getConnection();
            String sql = "SELECT IdSize, TenSize FROM Size";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setIdSize(rs.getString("IdSize"));
                sp.setSize(rs.getString("TenSize"));
                listThuocTinh.add(sp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listThuocTinh;
    }

    public ArrayList<SanPham> getSelectChatLieu() {
        listThuocTinh.clear();
        try {
            Connection conn = DbConnect.getConnection();
            String sql = "SELECT IdChatLieu, TenChatLieu FROM ChatLieu";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setIdChatLieu(rs.getString("IdChatLieu"));
                sp.setChatLieu(rs.getString("TenChatLieu"));
                listThuocTinh.add(sp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listThuocTinh;
    }

    public ArrayList<SanPham> getSelectHang() {
        listThuocTinh.clear();
        try {
            Connection conn = DbConnect.getConnection();
            String sql = "SELECT IdHang, TenHang FROM Hang";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setIdHang(rs.getString("IdHang"));
                sp.setHang(rs.getString("TenHang"));
                listThuocTinh.add(sp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listThuocTinh;
    }

    public void AddCTSP(SanPham sp) {
        try {
            Connection conn = DbConnect.getConnection();
            String sql = "INSERT INTO CTSP (MaSP, NgayNhap, IdMauSac, IdSize, IdChatLieu, IdHang, SoLuong, GiaNhap, HinhAnh) VALUES (?,?,?,?,?,?,?,?,?);";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, sp.getMaSanPham());
            ps.setString(2, sp.getNgayNhap());
            ps.setInt(3, Integer.valueOf(sp.getMauSac()));
            ps.setInt(4, Integer.valueOf(sp.getSize()));
            ps.setInt(5, Integer.valueOf(sp.getChatLieu()));
            ps.setInt(6, Integer.valueOf(sp.getHang()));
            ps.setInt(7, Integer.valueOf(sp.getSoLuong()));
            ps.setFloat(8, Float.valueOf(sp.getGiaNhap()));
            ps.setString(9, sp.getHinhAnh() == null ? "No_img" : sp.getHinhAnh());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int idSP = rs.getInt(1);
                String sql1 = "INSERT INTO Gia (IdSP, NgayBatDau, NgayKetThuc, GiaBan) VALUES (?,?,?,?);";
                PreparedStatement ps1 = conn.prepareStatement(sql1);
                ps1.setInt(1, idSP);
                ps1.setString(2, sp.getNgayNhap());
                ps1.setString(3, null);
                ps1.setFloat(4, Float.valueOf(sp.getGiaBan()));

                ps1.executeUpdate();
                ps1.close();
            }

            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void UpdateSP(SanPham sp, String a, String slt) {
        try {
            Connection conn = DbConnect.getConnection();
            String sql = "UPDATE SanPham SET MaSP = ?, TenSP = ?, SoLuongTong = ?, TrangThai = ? WHERE MaSP = " + "'" + a + "'";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, sp.getMaSanPham());
            ps.setString(2, sp.getTenSanPham());
            ps.setInt(3, Integer.valueOf(slt));
            ps.setInt(4, sp.getTrangThai().equals("Còn hàng") ? 1 : 0);
            ps.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void UpdateCTSP(SanPham sp, int a) {
        LocalDateTime ngayKetThuc = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String ngayKetThucFM = ngayKetThuc.format(formatter);
        try {
            Connection conn = DbConnect.getConnection();
            String sql = "UPDATE CTSP SET MaSP = ?, NgayNhap = ?, IdMauSac = ?, IdSize = ?, IdChatLieu = ?, IdHang = ?,SoLuong = ?, GiaNhap = ?, HinhAnh = ? WHERE IdSP = " + a;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, sp.getMaSanPham());
            ps.setString(2, sp.getNgayNhap());
            ps.setInt(3, Integer.valueOf(sp.getMauSac()));
            ps.setInt(4, Integer.valueOf(sp.getSize()));
            ps.setInt(5, Integer.valueOf(sp.getChatLieu()));
            ps.setInt(6, Integer.valueOf(sp.getHang()));
            ps.setInt(7, Integer.valueOf(sp.getSoLuong()));
            ps.setFloat(8, Float.valueOf(sp.getGiaNhap()));
            ps.setString(9, sp.getHinhAnh());
            ps.executeUpdate();

            String sql1 = "UPDATE Gia SET NgayKetThuc = ? WHERE IdSP = ? ";
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setObject(1, ngayKetThucFM);
            ps1.setInt(2, a);
            ps1.executeUpdate();

            String sql2 = "INSERT INTO Gia (IdSP, NgayBatDau, NgayKetThuc, GiaBan) VALUES (?,?,?,?)";
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setInt(1, a);
            ps2.setObject(2, ngayKetThucFM);
            ps2.setString(3, null);
            ps2.setFloat(4, Float.valueOf(sp.getGiaBan()));
            ps2.executeUpdate();

            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void dltSP(String a) {
        try {
            Connection conn = DbConnect.getConnection();
            String sql = "DELETE FROM CTSP \n"
                    + "WHERE MaSP = ?\n"
                    + "DELETE FROM SanPham \n"
                    + "WHERE MaSP = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, a);
            ps.setString(2, a);
            ps.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void dltCTSP(int a) {
        try {
            Connection conn = DbConnect.getConnection();
            String sql = "DELETE FROM CTSP \n"
                    + "WHERE IdSP = ?\n";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, a);
            ps.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addMauSac(SanPham sp) {
        try {
            Connection conn = DbConnect.getConnection();
            String sql = "INSERT INTO MauSac (TenMauSac) VALUES (?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, sp.getMauSac());
            ps.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addSize(SanPham sp) {
        try {
            Connection conn = DbConnect.getConnection();
            String sql = "INSERT INTO Size (TenSize) VALUES (?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, sp.getMauSac());
            ps.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addChatLieu(SanPham sp) {
        try {
            Connection conn = DbConnect.getConnection();
            String sql = "INSERT INTO ChatLieu (TenChatLieu) VALUES (?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, sp.getChatLieu());
            ps.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addHang(SanPham sp) {
        try {
            Connection conn = DbConnect.getConnection();
            String sql = "INSERT INTO Hang (TenHang) VALUES (?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, sp.getHang());
            ps.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void UpMauSac(SanPham sp, String a) {
        try {
            Connection conn = DbConnect.getConnection();
            String sql = "UPDATE MauSac SET TenMauSac = ? WHERE IdMauSac = " + a;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, sp.getMauSac());
            ps.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void UpMSize(SanPham sp, String a) {
        try {
            Connection conn = DbConnect.getConnection();
            String sql = "UPDATE Size SET TenSize = ? WHERE IdSize = " + a;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, sp.getSize());
            ps.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void UpChatLieu(SanPham sp, String a) {
        try {
            Connection conn = DbConnect.getConnection();
            String sql = "UPDATE ChatLieu SET TenChatLieu = ? WHERE IdChatLieu= " + a;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, sp.getChatLieu());
            ps.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void UpHang(SanPham sp, String a) {
        try {
            Connection conn = DbConnect.getConnection();
            String sql = "UPDATE Hang SET TenHang = ? WHERE IdHang = " + a;
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, sp.getHang());
            ps.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<SanPham> timKiem(String ma) {
        listSanPham.clear();
        try {
            Connection conn = DbConnect.getConnection();
            String sql = "SELECT * FROM SanPham WHERE MaSP like  " + "'%" + ma + "%'";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSanPham(rs.getString("MaSP"));
                sp.setTenSanPham(rs.getString("TenSP"));
                String a = rs.getString("SoLuongTong");
                if (a == null || a.isEmpty()) {
                    sp.setTrangThai("Hết hàng");
                } else {
                    sp.setTrangThai("Còn hàng");
                }
                listSanPham.add(sp);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listSanPham;
    }

    public ArrayList<SanPham> timKiemMauSac(String id) {
        listThuocTinh.clear();
        try {
            Connection conn = DbConnect.getConnection();
            String sql = "SELECT * FROM MauSac WHERE IdMauSac like  " + "'%" + id + "%' or TenMauSac like " + "N'%" + id + "%'";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setIdMauSac(rs.getString("IdMauSac"));
                sp.setMauSac(rs.getString("TenMauSac"));
                listThuocTinh.add(sp);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listThuocTinh;
    }

    public ArrayList<SanPham> timKiemSize(String id) {
        listThuocTinh.clear();
        try {
            Connection conn = DbConnect.getConnection();
            String sql = "SELECT * FROM Size WHERE IdSize like  " + "'%" + id + "%'  or TenSize like " + "N'%" + id + "%'";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setIdSize(rs.getString("IdSize"));
                sp.setSize(rs.getString("TenSize"));
                listThuocTinh.add(sp);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listThuocTinh;
    }

    public ArrayList<SanPham> timKiemChatLieu(String id) {
        listThuocTinh.clear();
        try {
            Connection conn = DbConnect.getConnection();
            String sql = "SELECT * FROM ChatLieu WHERE IdChatLieu like  " + "'%" + id + "%' or TenChatLieu like " + "N'%" + id + "%'";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setIdChatLieu(rs.getString("IdChatLieu"));
                sp.setChatLieu(rs.getString("TenChatlieu"));
                listThuocTinh.add(sp);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listThuocTinh;
    }

    public ArrayList<SanPham> timKiemHang(String id) {
        listThuocTinh.clear();
        try {
            Connection conn = DbConnect.getConnection();
            String sql = "SELECT * FROM Hang WHERE IdHang like  " + "'%" + id + "%' or TenHang like " + "N'%" + id + "%'";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setIdHang(rs.getString("IdHang"));
                sp.setHang(rs.getString("TenHang"));
                listThuocTinh.add(sp);
            }
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(QuanLySanPham.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listThuocTinh;
    }
}
