
package com.fstore.model;

import java.util.Date;

/**
 *
 * @author ThongPH43253
 */
public class NhanVien {
    //Test1
    private String maNV, ten, diaChi,SDT, matKhau,email, hinhAnh;
    private boolean gioiTinh,vaiTro;
    private int trangThai;
    private Date ngaySinh;

    public NhanVien() {
    }

    public NhanVien(String maNV, String ten, String diaChi, String SDT, String matKhau, String email, String hinhAnh, boolean gioiTinh, boolean vaiTro, int trangThai, Date ngaySinh) {
        this.maNV = maNV;
        this.ten = ten;
        this.diaChi = diaChi;
        this.SDT = SDT;
        this.matKhau = matKhau;
        this.email = email;
        this.hinhAnh = hinhAnh;
        this.gioiTinh = gioiTinh;
        this.vaiTro = vaiTro;
        this.trangThai = trangThai;
        this.ngaySinh = ngaySinh;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public boolean isVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(boolean vaiTro) {
        this.vaiTro = vaiTro;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

   

    
    
    
}
