/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.TaiKhoan;
import Repository.DbConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author TienBB
 */
public class DangKyService {

    private Connection cn;

    public DangKyService() {
        try {
            cn = DbConnect.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    public String add(TaiKhoan tk) {
        try {
            String sql = "INSERT INTO TaiKhoan (TenDangNhap,MatKhau)" + "VALUES(?,?)";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, tk.getTenDN());
            ps.setString(2, tk.getMatKhau());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Thêm thành công";
    }
}
