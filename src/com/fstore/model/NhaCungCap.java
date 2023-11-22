
package com.fstore.model;

/**
 *
 * @author ThongHT
 */
public class NhaCungCap {
    private int ID_NCC;
    private String tenNCC,SDT, diaChi;
    private int trangThai;

    public NhaCungCap() {
    }
    
     public NhaCungCap( String tenNCC, String SDT, String diaChi, int trangThai) {
        this.tenNCC = tenNCC;
        this.SDT = SDT;
        this.diaChi = diaChi;
        this.trangThai = trangThai;
    }
    public NhaCungCap(int ID_NCC, String tenNCC, String SDT, String diaChi, int trangThai) {
        this.ID_NCC = ID_NCC;
        this.tenNCC = tenNCC;
        this.SDT = SDT;
        this.diaChi = diaChi;
        this.trangThai = trangThai;
    }

    public int getID_NCC() {
        return ID_NCC;
    }

    public void setID_NCC(int ID_NCC) {
        this.ID_NCC = ID_NCC;
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return tenNCC ;
    }
    
}
