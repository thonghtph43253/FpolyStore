
package com.fstore.model;

/**
 *
 * @author Admin
 */
public class DanhMuc {
    private int ID_DanhMuc,trangThai;
    private String tenDanhMuc;

    public DanhMuc() {
    }

    public DanhMuc(int ID_DanhMuc, int trangThai, String tenDanhMuc) {
        this.ID_DanhMuc = ID_DanhMuc;
        this.trangThai = trangThai;
        this.tenDanhMuc = tenDanhMuc;
    }
    public DanhMuc(int trangThai, String tenDanhMuc) {
        this.trangThai = trangThai;
        this.tenDanhMuc = tenDanhMuc;
    }

    public int getID_DanhMuc() {
        return ID_DanhMuc;
    }

    public void setID_DanhMuc(int ID_DanhMuc) {
        this.ID_DanhMuc = ID_DanhMuc;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }

    @Override
    public String toString() {
        return   tenDanhMuc ;
    }
    
}
