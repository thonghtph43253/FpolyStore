
package com.fstore.model;

/**
 *
 * @author ThongHTPH43253
 */
public class SanPham {
    private int ID_SanPham, trangThai;
    private String tenSP, moTa;
    private DanhMuc dm;

    public SanPham() {
    }

    public SanPham(int ID_SanPham, int trangThai, String tenSP, String moTa, DanhMuc dm) {
        this.ID_SanPham = ID_SanPham;
        this.trangThai = trangThai;
        this.tenSP = tenSP;
        this.moTa = moTa;
        this.dm = dm;
    }
    public SanPham( int trangThai, String tenSP, String moTa, DanhMuc dm) {
        this.trangThai = trangThai;
        this.tenSP = tenSP;
        this.moTa = moTa;
        this.dm = dm;
    }

    public int getID_SanPham() {
        return ID_SanPham;
    }

    public void setID_SanPham(int ID_SanPham) {
        this.ID_SanPham = ID_SanPham;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public DanhMuc getDm() {
        return dm;
    }

    public void setDm(DanhMuc dm) {
        this.dm = dm;
    }

    @Override
    public String toString() {
        return  tenSP ;
    }
    
}
