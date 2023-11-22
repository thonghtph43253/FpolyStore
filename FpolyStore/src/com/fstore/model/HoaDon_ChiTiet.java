
package com.fstore.model;

/**
 *
 * @author ThongHT
 */
public class HoaDon_ChiTiet {
    private int Id_HoaDonCT;
    private int Id_HoaDon;
    private int Id_SanPhamChiTiet;
    private int soLuong;
    private double gia;
    private int trangThai;

    public HoaDon_ChiTiet() {
    }
    
     public HoaDon_ChiTiet( int Id_HoaDon, int Id_SanPhamChiTiet, int soLuong, double gia,int trangThai) {
        this.Id_HoaDon = Id_HoaDon;
        this.Id_SanPhamChiTiet = Id_SanPhamChiTiet;
        this.soLuong = soLuong;
        this.gia = gia;
        this.trangThai = trangThai;
    }
    
    public HoaDon_ChiTiet(int Id_HoaDonCT, int Id_HoaDon, int Id_SanPhamChiTiet, int soLuong, double gia , int trangThai) {
        this.Id_HoaDonCT = Id_HoaDonCT;
        this.Id_HoaDon = Id_HoaDon;
        this.Id_SanPhamChiTiet = Id_SanPhamChiTiet;
        this.soLuong = soLuong;
        this.gia = gia;
        this.trangThai = trangThai;
    }

    public int getId_HoaDonCT() {
        return Id_HoaDonCT;
    }

    public void setId_HoaDonCT(int Id_HoaDonCT) {
        this.Id_HoaDonCT = Id_HoaDonCT;
    }

    public int getId_HoaDon() {
        return Id_HoaDon;
    }

    public void setId_HoaDon(int Id_HoaDon) {
        this.Id_HoaDon = Id_HoaDon;
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

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
    
}
