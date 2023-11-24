
package com.fstore.model;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class Voucher {
    private int id_Voucher;
    private String tenChienDich;
    private int hinhThucGiam;
    private double giaTriGiam;
    private int soLuong;
    private Date thoiGianBD, thoiGianKT;
    private int trangThai;

    public Voucher() {
    }

    public Voucher(int id_Voucher, String tenChienDich, int hinhThucGiam, double giaTriGiam, int soLuong, Date thoiGianBD, Date thoiGianKT, int trangThai) {
        this.id_Voucher = id_Voucher;
        this.tenChienDich = tenChienDich;
        this.hinhThucGiam = hinhThucGiam;
        this.giaTriGiam = giaTriGiam;
        this.soLuong = soLuong;
        this.thoiGianBD = thoiGianBD;
        this.thoiGianKT = thoiGianKT;
        this.trangThai = trangThai;
    }

    public Voucher(String tenChienDich, int hinhThucGiam, double giaTriGiam, int soLuong, Date thoiGianBD, Date thoiGianKT, int trangThai) {
        this.tenChienDich = tenChienDich;
        this.hinhThucGiam = hinhThucGiam;
        this.giaTriGiam = giaTriGiam;
        this.soLuong = soLuong;
        this.thoiGianBD = thoiGianBD;
        this.thoiGianKT = thoiGianKT;
        this.trangThai = trangThai;
    }

    public int getId_Voucher() {
        return id_Voucher;
    }

    public void setId_Voucher(int id_Voucher) {
        this.id_Voucher = id_Voucher;
    }

    public String getTenChienDich() {
        return tenChienDich;
    }

    public void setTenChienDich(String tenChienDich) {
        this.tenChienDich = tenChienDich;
    }

    public int getHinhThucGiam() {
        return hinhThucGiam;
    }

    public void setHinhThucGiam(int hinhThucGiam) {
        this.hinhThucGiam = hinhThucGiam;
    }

    public double getGiaTriGiam() {
        return giaTriGiam;
    }

    public void setGiaTriGiam(double giaTriGiam) {
        this.giaTriGiam = giaTriGiam;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Date getThoiGianBD() {
        return thoiGianBD;
    }

    public void setThoiGianBD(Date thoiGianBD) {
        this.thoiGianBD = thoiGianBD;
    }

    public Date getThoiGianKT() {
        return thoiGianKT;
    }

    public void setThoiGianKT(Date thoiGianKT) {
        this.thoiGianKT = thoiGianKT;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return  tenChienDich ;
    }
    
}
