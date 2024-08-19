/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author KhanhCT
 */
public class KhachHang {

    private String maKH;
    private String tenKH;
    private String SĐT;
    private String NgayTao;
    private String NgaySinh;
    private boolean gioiTinh;
    private boolean trangThai;
    private String diaChi;
    private Integer LuotMua;

    public KhachHang() {
    }

    public KhachHang(String maKH, String tenKH, String SĐT, String NgayTao, String NgaySinh, boolean gioiTinh, boolean trangThai, String diaChi, Integer LuotMua) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.SĐT = SĐT;
        this.NgayTao = NgayTao;
        this.NgaySinh = NgaySinh;
        this.gioiTinh = gioiTinh;
        this.trangThai = trangThai;
        this.diaChi = diaChi;
        this.LuotMua = LuotMua;
    }

    
    

   
    


    
    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getSĐT() {
        return SĐT;
    }

    public void setSĐT(String SĐT) {
        this.SĐT = SĐT;
    }

    public String getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(String NgayTao) {
        this.NgayTao = NgayTao;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Integer getLuotMua() {
        return LuotMua;
    }

    public void setLuotMua(Integer LuotMua) {
        this.LuotMua = LuotMua;
    }

    
}
