
package com.fstore.model;

/**
 *
 * @author Admin
 */
public class MauSac {
    private int ID_MauSac;
    private String tenMau;
    private int trangThai;

    public MauSac() {
    }
    public MauSac( String tenMau, int trangThai) {
       
        this.tenMau = tenMau;
        this.trangThai = trangThai;
    }
    public MauSac(int ID_MauSac, String tenMau, int trangThai) {
        this.ID_MauSac = ID_MauSac;
        this.tenMau = tenMau;
        this.trangThai = trangThai;
    }

    public int getID_MauSac() {
        return ID_MauSac;
    }

    public void setID_MauSac(int ID_MauSac) {
        this.ID_MauSac = ID_MauSac;
    }

    public String getTenMau() {
        return tenMau;
    }

    public void setTenMau(String tenMau) {
        this.tenMau = tenMau;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return tenMau ;
    }
    
    
    
}
