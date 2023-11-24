
package com.fstore.model;

/**
 *
 * @author ThongHT
 */
public class HoaDonNhap_ChiTiet {
    private int Id_HoaDonNhapCT;
    private int Id_HoaDonNhap;
    private int Id_SanPhamChiTiet;
    private int soLuong;
    private double giaNhap;
    private int trangThai;

    public HoaDonNhap_ChiTiet() {
    }

    public HoaDonNhap_ChiTiet( int Id_HoaDonNhap, int Id_SanPhamChiTiet, int soLuong, double giaNhap, int trangThai) {
        
        this.Id_HoaDonNhap = Id_HoaDonNhap;
        this.Id_SanPhamChiTiet = Id_SanPhamChiTiet;
        this.soLuong = soLuong;
        this.giaNhap = giaNhap;
        this.trangThai = trangThai;
    }
    public HoaDonNhap_ChiTiet(int Id_HoaDonNhapCT, int Id_HoaDonNhap, int Id_SanPhamChiTiet, int soLuong, double giaNhap, int trangThai) {
        this.Id_HoaDonNhapCT = Id_HoaDonNhapCT;
        this.Id_HoaDonNhap = Id_HoaDonNhap;
        this.Id_SanPhamChiTiet = Id_SanPhamChiTiet;
        this.soLuong = soLuong;
        this.giaNhap = giaNhap;
        this.trangThai = trangThai;
    }

    public int getId_HoaDonNhapCT() {
        return Id_HoaDonNhapCT;
    }

    public void setId_HoaDonNhapCT(int Id_HoaDonNhapCT) {
        this.Id_HoaDonNhapCT = Id_HoaDonNhapCT;
    }

    public int getId_HoaDonNhap() {
        return Id_HoaDonNhap;
    }

    public void setId_HoaDonNhap(int Id_HoaDonNhap) {
        this.Id_HoaDonNhap = Id_HoaDonNhap;
    }

    public int getId_SanPhamChiTiet() {
        return Id_SanPhamChiTiet;
    }

    public void setId_SanPhamChiTiet(int Id_SanPhamChiTiet) {
        this.Id_SanPhamChiTiet = Id_SanPhamChiTiet;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(double giaNhap) {
        this.giaNhap = giaNhap;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
     
    
    
}
