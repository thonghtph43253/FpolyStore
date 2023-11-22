/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fstore.model;

/**
 *
 * @author ThongHTPH43253
 */
public class ChatLieu {
    private int ID_ChatLieu;
    private String tenChatLieu;
    private int trangThai;

    public ChatLieu() {
    }

    public ChatLieu(int ID_ChatLieu, String tenChatLieu, int trangThai) {
        this.ID_ChatLieu = ID_ChatLieu;
        this.tenChatLieu = tenChatLieu;
        this.trangThai = trangThai;
    }
    public ChatLieu( String tenChatLieu, int trangThai) {
        
        this.tenChatLieu = tenChatLieu;
        this.trangThai = trangThai;
    }

    public int getID_ChatLieu() {
        return ID_ChatLieu;
    }

    public void setID_ChatLieu(int ID_ChatLieu) {
        this.ID_ChatLieu = ID_ChatLieu;
    }

    public String getTenChatLieu() {
        return tenChatLieu;
    }

    public void setTenChatLieu(String tenChatLieu) {
        this.tenChatLieu = tenChatLieu;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return tenChatLieu ;
    }
    
}
