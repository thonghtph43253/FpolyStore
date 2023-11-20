/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fstore.model;

/**
 *
 * @author Dell
 */
public class KhachHang {
    private int id_KhachHang;
    private String ten,sDT,ngaySinh,diaChi,email;
    private boolean gioiTinh;
    private int trangThai;

    public KhachHang() {
    }

    public KhachHang(int id_KhachHang, String ten, String sDT, String ngaySinh, String diaChi, String email, boolean gioiTinh, int trangThai) {
        this.id_KhachHang = id_KhachHang;
        this.ten = ten;
        this.sDT = sDT;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.email = email;
        this.gioiTinh = gioiTinh;
        this.trangThai = trangThai;
    }

    public int getId_KhachHang() {
        return id_KhachHang;
    }

    public void setId_KhachHang(int id_KhachHang) {
        this.id_KhachHang = id_KhachHang;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getsDT() {
        return sDT;
    }

    public void setsDT(String sDT) {
        this.sDT = sDT;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
}
