
package com.fstore.service;

import com.fstore.model.DanhMuc;
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
public class DanhMuc_Service implements Inf_Service<DanhMuc, Integer>{
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;
    @Override
    public int insert(DanhMuc model) {
        sql = """
              INSERT INTO DANHMUC(TENDANHMUC, TRANGTHAI) VALUES(?,?)
              """;
        try {
             conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, model.getTenDanhMuc());
            ps.setObject(2, model.getTrangThai());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(DanhMuc model, Integer id) {
        sql = """
              UPDATE DANHMUC SET TENDANHMUC = ?, TRANGTHAI = ?
              WHERE ID_DANHMUC = ?
              """;
        try {
             conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, model.getTenDanhMuc());
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
              DELETE FROM DANHMUC 
              WHERE ID_DANHMUC = ?
              """;
        try {
             conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            
            ps.setObject(1, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            //e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<DanhMuc> selectAll() {
        sql ="""
             SELECT ID_DANHMUC,TENDANHMUC,TRANGTHAI
             FROM DANHMUC
             """;
        List<DanhMuc> list = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                DanhMuc dm = new DanhMuc();
                dm.setID_DanhMuc(rs.getInt(1));
                dm.setTenDanhMuc(rs.getString(2));
                dm.setTrangThai(rs.getInt(3));
                list.add(dm);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public DanhMuc selectByID(Integer id) {
        sql ="""
             SELECT ID_DANHMUC,TENDANHMUC,TRANGTHAI
             FROM DANHMUC
             WHERE ID_DANHMUC = ?
             """;
       DanhMuc dm = null;
        try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                 dm = new DanhMuc();
                dm.setID_DanhMuc(rs.getInt(1));
                dm.setTenDanhMuc(rs.getString(2));
                dm.setTrangThai(rs.getInt(3));
                
            }
            return dm;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
     public List<DanhMuc> selectByName(String ten) {
        sql ="""
             SELECT ID_DANHMUC,TENDANHMUC,TRANGTHAI
             FROM DANHMUC
             WHERE TENDANHMUC LIKE ?
             """;
        List<DanhMuc> list = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, "%"+ten+"%");
            rs = ps.executeQuery();
            while(rs.next()){
                DanhMuc dm = new DanhMuc();
                dm.setID_DanhMuc(rs.getInt(1));
                dm.setTenDanhMuc(rs.getString(2));
                dm.setTrangThai(rs.getInt(3));
                list.add(dm);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
     
}
