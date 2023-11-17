
package com.fstore.model;

/**
 *
 * @author ThongHT
 */
public class SanPhamChiTiet {
    private int id_SanPhamChiTiet;
    private int id_Mau;
    private int id_ChatLieu;
    private int id_Size;
    private int id_SanPham;
    private int soLuong;
    private double gia;
    private String hinhAnh;
    private int trangThai;

    public SanPhamChiTiet() {
    }
    
    public SanPhamChiTiet( int id_Mau, int id_ChatLieu, int id_Size, int id_SanPham, int soLuong, double gia, String hinhAnh, int trangThai) {
        this.id_Mau = id_Mau;
        this.id_ChatLieu = id_ChatLieu;
        this.id_Size = id_Size;
        this.id_SanPham = id_SanPham;
        this.soLuong = soLuong;
        this.gia = gia;
        this.hinhAnh = hinhAnh;
        this.trangThai = trangThai;
    }
    
    public SanPhamChiTiet(int id_SanPhamChiTiet, int id_Mau, int id_ChatLieu, int id_Size, int id_SanPham, int soLuong, double gia, String hinhAnh, int trangThai) {
        this.id_SanPhamChiTiet = id_SanPhamChiTiet;
        this.id_Mau = id_Mau;
        this.id_ChatLieu = id_ChatLieu;
        this.id_Size = id_Size;
        this.id_SanPham = id_SanPham;
        this.soLuong = soLuong;
        this.gia = gia;
        this.hinhAnh = hinhAnh;
        this.trangThai = trangThai;
    }

    public int getId_SanPhamChiTiet() {
        return id_SanPhamChiTiet;
    }

    public void setId_SanPhamChiTiet(int id_SanPhamChiTiet) {
        this.id_SanPhamChiTiet = id_SanPhamChiTiet;
    }

    public int getId_Mau() {
        return id_Mau;
    }

    public void setId_Mau(int id_Mau) {
        this.id_Mau = id_Mau;
    }

    public int getId_ChatLieu() {
        return id_ChatLieu;
    }

    public void setId_ChatLieu(int id_ChatLieu) {
        this.id_ChatLieu = id_ChatLieu;
    }

    public int getId_Size() {
        return id_Size;
    }

    public void setId_Size(int id_Size) {
        this.id_Size = id_Size;
    }

    public int getId_SanPham() {
        return id_SanPham;
    }

    public void setId_SanPham(int id_SanPham) {
        this.id_SanPham = id_SanPham;
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

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
    
}
