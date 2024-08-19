/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author KhanhCT
 */
public class NhanVien {
    private String maNV;
    private String tenNV;
    private String Cccd;
    private String Email;
    private String ngaySinh;
    private boolean gioiTinh;
    private String SĐT;
    private String NgayVaoLam;
    private String vaiTro;
    private String maCL;
    private String diaChi; 
    private String trangThai;

    public NhanVien() {
    }

    public NhanVien(String maNV, String tenNV, String Cccd, String Email, String ngaySinh, boolean gioiTinh, String SĐT, String NgayVaoLam, String vaiTro, String maCL, String diaChi, String trangThai) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.Cccd = Cccd;
        this.Email = Email;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.SĐT = SĐT;
        this.NgayVaoLam = NgayVaoLam;
        this.vaiTro = vaiTro;
        this.maCL = maCL;
        this.diaChi = diaChi;
        this.trangThai = trangThai;
    }

    

    
    

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getCccd() {
        return Cccd;
    }

    public void setCccd(String Cccd) {
        this.Cccd = Cccd;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getSĐT() {
        return SĐT;
    }

    public void setSĐT(String SĐT) {
        this.SĐT = SĐT;
    }

    public String getNgayVaoLam() {
        return NgayVaoLam;
    }

    public void setNgayVaoLam(String NgayVaoLam) {
        this.NgayVaoLam = NgayVaoLam;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }

  

    public String getMaCL() {
        return maCL;
    }

    public void setMaCL(String maCL) {
        this.maCL = maCL;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    
    
    

    
}
