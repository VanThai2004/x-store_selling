/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author KhanhCT
 */
public class SanPham {

    private String maSanPham;
    private String idspct;
    private String tenSanPham;
    private String ngayNhap;
    private String idMauSac;
    private String idSize;
    private String idChatLieu;
    private String idHang;
    private String MauSac;
    private String Size;
    private String ChatLieu;
    private String Hang;
    private String soLuong;
    private String giaNhap;
    private String giaBan;
    private String trangThai;
    private String phanTram;
    private String hinhAnh;
    private String maCP;

    public SanPham() {
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getIdspct() {
        return idspct;
    }

    public void setIdspct(String idspct) {
        this.idspct = idspct;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(String ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public String getIdMauSac() {
        return idMauSac;
    }

    public void setIdMauSac(String idMauSac) {
        this.idMauSac = idMauSac;
    }

    public String getIdSize() {
        return idSize;
    }

    public void setIdSize(String idSize) {
        this.idSize = idSize;
    }

    public String getIdChatLieu() {
        return idChatLieu;
    }

    public void setIdChatLieu(String idChatLieu) {
        this.idChatLieu = idChatLieu;
    }

    public String getIdHang() {
        return idHang;
    }

    public void setIdHang(String idHang) {
        this.idHang = idHang;
    }

    public String getMauSac() {
        return MauSac;
    }

    public void setMauSac(String MauSac) {
        this.MauSac = MauSac;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String Size) {
        this.Size = Size;
    }

    public String getChatLieu() {
        return ChatLieu;
    }

    public void setChatLieu(String ChatLieu) {
        this.ChatLieu = ChatLieu;
    }

    public String getHang() {
        return Hang;
    }

    public void setHang(String Hang) {
        this.Hang = Hang;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public String getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(String giaNhap) {
        this.giaNhap = giaNhap;
    }

    public String getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(String giaBan) {
        this.giaBan = giaBan;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getPhanTram() {
        return phanTram;
    }

    public void setPhanTram(String phanTram) {
        this.phanTram = phanTram;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getMaCP() {
        return maCP;
    }

    public void setMaCP(String maCP) {
        this.maCP = maCP;
    }

    public SanPham(String maSanPham, String idspct, String tenSanPham, String ngayNhap, String idMauSac, String idSize, String idChatLieu, String idHang, String MauSac, String Size, String ChatLieu, String Hang, String soLuong, String giaNhap, String giaBan, String trangThai, String phanTram, String hinhAnh, String maCP) {
        this.maSanPham = maSanPham;
        this.idspct = idspct;
        this.tenSanPham = tenSanPham;
        this.ngayNhap = ngayNhap;
        this.idMauSac = idMauSac;
        this.idSize = idSize;
        this.idChatLieu = idChatLieu;
        this.idHang = idHang;
        this.MauSac = MauSac;
        this.Size = Size;
        this.ChatLieu = ChatLieu;
        this.Hang = Hang;
        this.soLuong = soLuong;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.trangThai = trangThai;
        this.phanTram = phanTram;
        this.hinhAnh = hinhAnh;
        this.maCP = maCP;
    }

    

    
    

    public Integer giaSau() {
        float giaBan = Float.parseFloat(this.giaBan);
        float phanTram = Float.parseFloat(this.phanTram);
        int giaSau = (int) (giaBan - (giaBan  *  phanTram/100));
        return giaSau;
    }
    

}
