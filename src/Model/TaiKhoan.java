/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author TienBB
 */
public class TaiKhoan {

    private String maTK;
    private String tenDN;
    private String matKhau;
    private String MaNV;
    private String MaCL;
    private String TenCL;
    private String GioBatDau;
    private String GioKetThuc;
    private String GhiChu;
    
    public TaiKhoan() {
    }

    public TaiKhoan(String maTK, String tenDN, String matKhau, String MaNV) {
        this.maTK = maTK;
        this.tenDN = tenDN;
        this.matKhau = matKhau;
        this.MaNV = MaNV;
    }

    public TaiKhoan(String MaCL, String TenCL, String GioBatDau, String GioKetThuc, String GhiChu) {
        this.MaCL = MaCL;
        this.TenCL = TenCL;
        this.GioBatDau = GioBatDau;
        this.GioKetThuc = GioKetThuc;
        this.GhiChu = GhiChu;
    }

    public String getMaTK() {
        return maTK;
    }

    public void setMaTK(String maTK) {
        this.maTK = maTK;
    }

    public String getTenDN() {
        return tenDN;
    }

    public void setTenDN(String tenDN) {
        this.tenDN = tenDN;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getMaCL() {
        return MaCL;
    }

    public void setMaCL(String MaCL) {
        this.MaCL = MaCL;
    }

    public String getTenCL() {
        return TenCL;
    }

    public void setTenCL(String TenCL) {
        this.TenCL = TenCL;
    }

    public String getGioBatDau() {
        return GioBatDau;
    }

    public void setGioBatDau(String GioBatDau) {
        this.GioBatDau = GioBatDau;
    }

    public String getGioKetThuc() {
        return GioKetThuc;
    }

    public void setGioKetThuc(String GioKetThuc) {
        this.GioKetThuc = GioKetThuc;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }
    
    
  

}
