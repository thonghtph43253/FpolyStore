
package com.fstore.service;

import com.fstore.model.MauSac;
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
public class MauSac_Service implements Inf_Service<MauSac, Integer>{
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    @Override
    public int insert(MauSac model) {
          sql = """
              INSERT INTO MAU(TENMAU, TRANGTHAI) VALUES(?,?)
              """;
        try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, model.getTenMau());
            ps.setObject(2, model.getTrangThai());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(MauSac model, Integer id) {
         sql = """
              UPDATE MAU SET TENMAU = ?, TRANGTHAI = ?
              WHERE ID_CHATLIEU = ?
              """;
        try {
             conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, model.getTenMau());
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
              DELETE FROM MAU
              WHERE ID_MAU = ?
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
    public List<MauSac> selectAll() {
         sql ="""
             SELECT ID_MAU,TENMAU,TRANGTHAI
             FROM MAU
             """;
        List<MauSac> list = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                MauSac md = new MauSac();
                md.setID_MauSac(rs.getInt(1));
                md.setTenMau(rs.getString(2));
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
    public MauSac selectByID(Integer id) {
         sql ="""
             SELECT ID_MAU,TENMAU,TRANGTHAI
             FROM MAU
             WHERE ID_MAU = ?
             """;
        MauSac md = null;
        try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                md = new MauSac();
                md.setID_MauSac(rs.getInt(1));
                md.setTenMau(rs.getString(2));
                md.setTrangThai(rs.getInt(3));
                
            }
            return md;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<MauSac> selectByName(String ten) {
         sql ="""
             SELECT ID_MAU,TENMAU,TRANGTHAI
             FROM MAU
             WHERE TENMAU LIKE ?
             """;
        List<MauSac> list = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, "%"+ten+"%");
            rs = ps.executeQuery();
            while(rs.next()){
                MauSac md = new MauSac();
                md.setID_MauSac(rs.getInt(1));
                md.setTenMau(rs.getString(2));
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
