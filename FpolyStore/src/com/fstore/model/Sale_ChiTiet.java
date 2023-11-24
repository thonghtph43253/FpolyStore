
package com.fstore.model;

/**
 *
 * @author Admin
 */
public class Sale_ChiTiet {
    private int id_Sale_CT;
    private int hinhThucGiam;
    private double giaTriGiam;
    private int id_Sale;
    private int id_SanPham_CT;
    private int trangThai;

    public Sale_ChiTiet() {
    }

    public Sale_ChiTiet(int id_Sale_CT, int hinhThucGiam, double giaTriGiam, int id_Sale, int id_SanPham_CT, int trangThai) {
        this.id_Sale_CT = id_Sale_CT;
        this.hinhThucGiam = hinhThucGiam;
        this.giaTriGiam = giaTriGiam;
        this.id_Sale = id_Sale;
        this.id_SanPham_CT = id_SanPham_CT;
        this.trangThai = trangThai;
    }

    public Sale_ChiTiet(int hinhThucGiam, double giaTriGiam, int id_Sale, int id_SanPham_CT, int trangThai) {
        this.hinhThucGiam = hinhThucGiam;
        this.giaTriGiam = giaTriGiam;
        this.id_Sale = id_Sale;
        this.id_SanPham_CT = id_SanPham_CT;
        this.trangThai = trangThai;
    }

    public Sale_ChiTiet(int id_SanPham_CT) {
        this.id_SanPham_CT = id_SanPham_CT;
    }
  
    
    
    public int getId_Sale_CT() {
        return id_Sale_CT;
    }

    public void setId_Sale_CT(int id_Sale_CT) {
        this.id_Sale_CT = id_Sale_CT;
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

    public int getId_Sale() {
        return id_Sale;
    }

    public void setId_Sale(int id_Sale) {
        this.id_Sale = id_Sale;
    }

    public int getId_SanPham_CT() {
        return id_SanPham_CT;
    }

    public void setId_SanPham_CT(int id_SanPham_CT) {
        this.id_SanPham_CT = id_SanPham_CT;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
}
