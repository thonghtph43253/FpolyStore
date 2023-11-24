
package com.fstore.model;

/**
 *
 * @author Admin
 */
public class HoaDonNhap {
    private int Id_HoaDonNhap;
    private String ngayTao;
    private String tenNhaCungCap, soDienThoai, ghiChu;
    private double tongTien;
    private  int Id_NhaCungCap, Id_NhanVien,trangThai;

    public HoaDonNhap() {
    }
    
    public HoaDonNhap( String ngayTao, String tenNhaCungCap, String soDienThoai, String ghiChu, double tongTien, int Id_NhaCungCap, int Id_NhanVien, int trangThai) {
        
        this.ngayTao = ngayTao;
        this.tenNhaCungCap = tenNhaCungCap;
        this.soDienThoai = soDienThoai;
        this.ghiChu = ghiChu;
        this.tongTien = tongTien;
        this.Id_NhaCungCap = Id_NhaCungCap;
        this.Id_NhanVien = Id_NhanVien;
        this.trangThai = trangThai;
    }
    
    public HoaDonNhap(int Id_HoaDonNhap, String ngayTao, String tenNhaCungCap, String soDienThoai, String ghiChu, double tongTien, int Id_NhaCungCap, int Id_NhanVien, int trangThai) {
        this.Id_HoaDonNhap = Id_HoaDonNhap;
        this.ngayTao = ngayTao;
        this.tenNhaCungCap = tenNhaCungCap;
        this.soDienThoai = soDienThoai;
        this.ghiChu = ghiChu;
        this.tongTien = tongTien;
        this.Id_NhaCungCap = Id_NhaCungCap;
        this.Id_NhanVien = Id_NhanVien;
        this.trangThai = trangThai;
    }

    public int getId_HoaDonNhap() {
        return Id_HoaDonNhap;
    }

    public void setId_HoaDonNhap(int Id_HoaDonNhap) {
        this.Id_HoaDonNhap = Id_HoaDonNhap;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getTenNhaCungCap() {
        return tenNhaCungCap;
    }

    public void setTenNhaCungCap(String tenNhaCungCap) {
        this.tenNhaCungCap = tenNhaCungCap;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public int getId_NhaCungCap() {
        return Id_NhaCungCap;
    }

    public void setId_NhaCungCap(int Id_NhaCungCap) {
        this.Id_NhaCungCap = Id_NhaCungCap;
    }

    public int getId_NhanVien() {
        return Id_NhanVien;
    }

    public void setId_NhanVien(int Id_NhanVien) {
        this.Id_NhanVien = Id_NhanVien;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
}
