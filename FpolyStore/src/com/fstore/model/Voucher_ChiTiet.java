
package com.fstore.model;

/**
 *
 * @author Admin
 */
public class Voucher_ChiTiet {
    private int id_Voucher_CT;
    private double soTienGiam;
    private int id_Voucher;
    private int id_HoaDon;
    private int id_KhachHang;

    public Voucher_ChiTiet() {
    }

    public Voucher_ChiTiet(int id_Voucher_CT, double soTienGiam, int id_Voucher, int id_HoaDon, int id_KhachHang) {
        this.id_Voucher_CT = id_Voucher_CT;
        this.soTienGiam = soTienGiam;
        this.id_Voucher = id_Voucher;
        this.id_HoaDon = id_HoaDon;
        this.id_KhachHang = id_KhachHang;
    }

    public Voucher_ChiTiet(double soTienGiam, int id_Voucher, int id_HoaDon, int id_KhachHang) {
        this.soTienGiam = soTienGiam;
        this.id_Voucher = id_Voucher;
        this.id_HoaDon = id_HoaDon;
        this.id_KhachHang = id_KhachHang;
    }

    public int getId_Voucher_CT() {
        return id_Voucher_CT;
    }

    public void setId_Voucher_CT(int id_Voucher_CT) {
        this.id_Voucher_CT = id_Voucher_CT;
    }

    public double getSoTienGiam() {
        return soTienGiam;
    }

    public void setSoTienGiam(double soTienGiam) {
        this.soTienGiam = soTienGiam;
    }

    public int getId_Voucher() {
        return id_Voucher;
    }

    public void setId_Voucher(int id_Voucher) {
        this.id_Voucher = id_Voucher;
    }

    public int getId_HoaDon() {
        return id_HoaDon;
    }

    public void setId_HoaDon(int id_HoaDon) {
        this.id_HoaDon = id_HoaDon;
    }

    public int getId_KhachHang() {
        return id_KhachHang;
    }

    public void setId_KhachHang(int id_KhachHang) {
        this.id_KhachHang = id_KhachHang;
    }
    
}
