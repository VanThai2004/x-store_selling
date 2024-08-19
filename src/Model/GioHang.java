/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ADMIN
 */
public class GioHang {
    private String maHD,idSP,soLuong,idCP,phanTram,giaSau;

    public GioHang() {
    }

    public GioHang(String maHD, String idSP, String soLuong, String idCP, String phanTram, String giaSau) {
        this.maHD = maHD;
        this.idSP = idSP;
        this.soLuong = soLuong;
        this.idCP = idCP;
        this.phanTram = phanTram;
        this.giaSau = giaSau;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getIdSP() {
        return idSP;
    }

    public void setIdSP(String idSP) {
        this.idSP = idSP;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public String getIdCP() {
        return idCP;
    }

    public void setIdCP(String idCP) {
        this.idCP = idCP;
    }

    public String getPhanTram() {
        return phanTram;
    }

    public void setPhanTram(String phanTram) {
        this.phanTram = phanTram;
    }

    public String getGiaSau() {
        return giaSau;
    }

    public void setGiaSau(String giaSau) {
        this.giaSau = giaSau;
    }

    
}
