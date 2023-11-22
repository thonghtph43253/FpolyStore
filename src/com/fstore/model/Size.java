/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fstore.model;

/**
 *
 * @author Admin
 */
public class Size {
    private int ID_Size, trangThai;
    private String tenSize;

    public Size() {
    }
    
    public Size( int trangThai, String tenSize) {
        this.trangThai = trangThai;
        this.tenSize = tenSize;
    }
    
    public Size(int ID_Size, int trangThai, String tenSize) {
        this.ID_Size = ID_Size;
        this.trangThai = trangThai;
        this.tenSize = tenSize;
    }

    public int getID_Size() {
        return ID_Size;
    }

    public void setID_Size(int ID_Size) {
        this.ID_Size = ID_Size;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getTenSize() {
        return tenSize;
    }

    public void setTenSize(String tenSize) {
        this.tenSize = tenSize;
    }
    
    

    @Override
    public String toString() {
        return tenSize ;
    }
}
