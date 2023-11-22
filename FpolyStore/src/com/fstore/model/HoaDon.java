
package com.fstore.model;



/**
 *
 * @author ThongHT
 */
public class HoaDon {
    private int Id_HoaDon;
    private String tenKH;
    private String sdt;
    private double tongTien;
    private String voucher;
    private String ghiChu;
    private int hinhThucThanhToan;
    private int trangThai;
    private int Id_KhachHang;
    private String Id_NhanVien;
    private String ngayTao;

    public HoaDon() {
    }
    
     public HoaDon( String tenKH, int trangThai, int Id_KhachHang, String Id_NhanVien, String ngayTao) {
        this.tenKH = tenKH;
        this.trangThai = trangThai;
        this.Id_KhachHang = Id_KhachHang;
        this.Id_NhanVien = Id_NhanVien;
        this.ngayTao = ngayTao;
    }
    
    public HoaDon( String tenKH, String sdt, double tongTien, String voucher, String ghiChu, int hinhThucThanhToan, int trangThai, int Id_KhachHang, String Id_NhanVien, String ngayTao) {
        this.tenKH = tenKH;
        this.sdt = sdt;
        this.tongTien = tongTien;
        this.voucher = voucher;
        this.ghiChu = ghiChu;
        this.hinhThucThanhToan = hinhThucThanhToan;
        this.trangThai = trangThai;
        this.Id_KhachHang = Id_KhachHang;
        this.Id_NhanVien = Id_NhanVien;
        this.ngayTao = ngayTao;
    }
    
    public HoaDon(int Id_HoaDon, String tenKH, String sdt, double tongTien, String voucher, String ghiChu, int hinhThucThanhToan, int trangThai, int Id_KhachHang, String Id_NhanVien, String ngayTao) {
        this.Id_HoaDon = Id_HoaDon;
        this.tenKH = tenKH;
        this.sdt = sdt;
        this.tongTien = tongTien;
        this.voucher = voucher;
        this.ghiChu = ghiChu;
        this.hinhThucThanhToan = hinhThucThanhToan;
        this.trangThai = trangThai;
        this.Id_KhachHang = Id_KhachHang;
        this.Id_NhanVien = Id_NhanVien;
        this.ngayTao = ngayTao;
    }

    public int getId_HoaDon() {
        return Id_HoaDon;
    }

    public void setId_HoaDon(int Id_HoaDon) {
        this.Id_HoaDon = Id_HoaDon;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public int getHinhThucThanhToan() {
        return hinhThucThanhToan;
    }

    public void setHinhThucThanhToan(int hinhThucThanhToan) {
        this.hinhThucThanhToan = hinhThucThanhToan;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public int getId_KhachHang() {
        return Id_KhachHang;
    }

    public void setId_KhachHang(int Id_KhachHang) {
        this.Id_KhachHang = Id_KhachHang;
    }

    public String getId_NhanVien() {
        return Id_NhanVien;
    }

    public void setId_NhanVien(String Id_NhanVien) {
        this.Id_NhanVien = Id_NhanVien;
    }

    

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }


    
}
