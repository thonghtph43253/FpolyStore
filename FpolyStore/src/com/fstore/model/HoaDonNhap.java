
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
    
}
