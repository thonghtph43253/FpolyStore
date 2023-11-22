
package com.fstore.service;

import com.fstore.model.ChatLieu;
import com.fstore.repository.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ChatLieu_Service implements Inf_Service<ChatLieu, Integer>{
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    @Override
    public int insert(ChatLieu model) {
         sql = """
              INSERT INTO CHATLIEU(TENCHATLIEU, TRANGTHAI) VALUES(?,?)
              """;
        try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, model.getTenChatLieu());
            ps.setObject(2, model.getTrangThai());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(ChatLieu model, Integer id) {
         sql = """
              UPDATE CHATLIEU SET TENCHATLIEU = ?, TRANGTHAI = ?
              WHERE ID_CHATLIEU = ?
              """;
        try {
             conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, model.getTenChatLieu());
            ps.setObject(2, model.getTrangThai());
            ps.setObject(3, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(Integer id) {
         sql = """
              DELETE FROM CHATLIEU
              WHERE ID_CHATLIEU = ?
              """;
        try {
             conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            
            ps.setObject(1, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<ChatLieu> selectAll() {
        sql ="""
             SELECT ID_CHATLIEU,TENCHATLIEU,TRANGTHAI
             FROM CHATLIEU
             """;
        List<ChatLieu> list = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                ChatLieu md = new ChatLieu();
                md.setID_ChatLieu(rs.getInt(1));
                md.setTenChatLieu(rs.getString(2));
                md.setTrangThai(rs.getInt(3));
                list.add(md);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ChatLieu selectByID(Integer id) {
        sql ="""
             SELECT ID_CHATLIEU,TENCHATLIEU,TRANGTHAI
             FROM CHATLIEU
             WHERE ID_CHATLIEU = ?
             """;
        ChatLieu md = null;
        try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                 md = new ChatLieu();
                md.setID_ChatLieu(rs.getInt(1));
                md.setTenChatLieu(rs.getString(2));
                md.setTrangThai(rs.getInt(3));
                
            }
            return md;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
     public List<ChatLieu> selectAllByName(String ten) {
        sql ="""
             SELECT ID_CHATLIEU,TENCHATLIEU,TRANGTHAI
             FROM CHATLIEU
             WHERE TENCHATLIEU LIKE ?
             """;
        List<ChatLieu> list = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, "%"+ten+"%");
            rs = ps.executeQuery();
            while(rs.next()){
                ChatLieu md = new ChatLieu();
                md.setID_ChatLieu(rs.getInt(1));
                md.setTenChatLieu(rs.getString(2));
                md.setTrangThai(rs.getInt(3));
                list.add(md);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
