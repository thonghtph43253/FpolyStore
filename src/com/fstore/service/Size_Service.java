/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fstore.service;

import com.fstore.model.Size;
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
public class Size_Service implements Inf_Service<Size, Integer>{
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    @Override
    public int insert(Size model) {
        sql = """
              INSERT INTO Size(TENSIZE, TRANGTHAI) VALUES(?,?)
              """;
        try {
             conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, model.getTenSize());
            ps.setObject(2, model.getTrangThai());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(Size model, Integer id) {
        sql = """
              UPDATE SIZE SET TENSIZE = ?, TRANGTHAI = ?
              WHERE ID_SIZE = ?
              """;
        try {
             conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, model.getTenSize());
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
              DELETE FROM SIZE
              WHERE ID_SIZE = ?
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
    public List<Size> selectAll() {
        sql ="""
             SELECT ID_SIZE,TENSIZE,TRANGTHAI
             FROM SIZE
             """;
        List<Size> list = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Size md = new Size();
                md.setID_Size(rs.getInt(1));
                md.setTenSize(rs.getString(2));
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
    public Size selectByID(Integer id) {
       sql ="""
             SELECT ID_SIZE,TENSIZE,TRANGTHAI
             FROM SIZE
             WHERE ID_SIZE = ?
             """;
        Size md = null;
        try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                 md = new Size();
                md.setID_Size(rs.getInt(1));
                md.setTenSize(rs.getString(2));
                md.setTrangThai(rs.getInt(3));
               
            }
            return md;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Size> selectByName(String ten) {
        sql ="""
             SELECT ID_SIZE,TENSIZE,TRANGTHAI
             FROM SIZE
             WHERE TENSIZE LIKE ?
             """;
        List<Size> list = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, "%"+ten+"%");
            rs = ps.executeQuery();
            while(rs.next()){
                Size md = new Size();
                md.setID_Size(rs.getInt(1));
                md.setTenSize(rs.getString(2));
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
