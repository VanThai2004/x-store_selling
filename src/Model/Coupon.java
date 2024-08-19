/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author TienBB
 */
public class Coupon {

    private String maCP;
    private String tenCP;
    private String idSP;
    private String phanTram;
    private String ngayBatDau;
    private String ngayKetThuc;
    private String trangThai;

    public Coupon() {
    }

    public Coupon(String maCP, String tenCP, String idSP, String phanTram, String ngayBatDau, String ngayKetThuc, String trangThai) {
        this.maCP = maCP;
        this.tenCP = tenCP;
        this.idSP = idSP;
        this.phanTram = phanTram;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.trangThai = trangThai;
    }

    public String getMaCP() {
        return maCP;
    }

    public void setMaCP(String maCP) {
        this.maCP = maCP;
    }

    public String getTenCP() {
        return tenCP;
    }

    public void setTenCP(String tenCP) {
        this.tenCP = tenCP;
    }

    public String getIdSP() {
        return idSP;
    }

    public void setIdSP(String idSP) {
        this.idSP = idSP;
    }

    public String getPhanTram() {
        return phanTram;
    }

    public void setPhanTram(String phanTram) {
        this.phanTram = phanTram;
    }

    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public String getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

}
