
package com.fstore.model;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class Sale {
    private int id_Sale;
    private String tenChienDich;
    private Date thoiGianBD, thoiGianKT;
    private int trangThai;

    public Sale() {
    }

    public Sale(int id_Sale, String tenChienDich, Date thoiGianBD, Date thoiGianKT, int trangThai) {
        this.id_Sale = id_Sale;
        this.tenChienDich = tenChienDich;
        this.thoiGianBD = thoiGianBD;
        this.thoiGianKT = thoiGianKT;
        this.trangThai = trangThai;
    }

    

    public Sale(String tenChienDich, Date thoiGianBD, Date thoiGianKT, int trangThai) {
        this.tenChienDich = tenChienDich;
        this.thoiGianBD = thoiGianBD;
        this.thoiGianKT = thoiGianKT;
        this.trangThai = trangThai;
    }

    public int getId_Sale() {
        return id_Sale;
    }

    public void setId_Sale(int id_Sale) {
        this.id_Sale = id_Sale;
    }

    public String getTenChienDich() {
        return tenChienDich;
    }

    public void setTenChienDich(String tenChienDich) {
        this.tenChienDich = tenChienDich;
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
    
}
