
package com.fstore.service;

import com.fstore.model.NhaCungCap;
import com.fstore.repository.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ThongHT
 */
public class NhaCungCap_Service implements Inf_Service<NhaCungCap, Integer>{
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;
    @Override
    public int insert(NhaCungCap model) {
        sql = """
              INSERT INTO NHACUNGCAP(TENNHACUNGCAP, SDT, DIACHI, TRANGTHAI)
              VALUES(?,?,?,?)
              """;
        try {
             conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, model.getTenNCC());
            ps.setObject(2, model.getSDT());
            ps.setObject(3, model.getDiaChi());
            ps.setObject(4, model.getTrangThai());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(NhaCungCap model, Integer id) {
        sql = """
              UPDATE NHACUNGCAP SET TENNHACUNGCAP = ?, SDT = ? , DIACHI = ?,
              TRANGTHAI = ?
              WHERE ID_NHACUNGCAP = ?
              """;
        try {
             conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, model.getTenNCC());
            ps.setObject(2, model.getSDT());
            ps.setObject(3, model.getDiaChi());
            ps.setObject(4, model.getTrangThai());
            ps.setObject(5, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(Integer id) {
         sql = """
              DELETE FROM NHACUNGCAP 
              WHERE ID_NHACUNGCAP = ?
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
    public List<NhaCungCap> selectAll() {
         sql ="""
             SELECT ID_NHACUNGCAP,TENNHACUNGCAP,SDT, DIACHI,TRANGTHAI
             FROM NHACUNGCAP
             """;
        List<NhaCungCap> list = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                NhaCungCap ncc = new NhaCungCap();
                ncc.setID_NCC(rs.getInt(1));
                ncc.setTenNCC(rs.getString(2));
                ncc.setSDT(rs.getString(3));
                ncc.setDiaChi(rs.getString(4));
                ncc.setTrangThai(rs.getInt(5));
                list.add(ncc);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public NhaCungCap selectByID(Integer id) {
        sql ="""
             SELECT ID_NHACUNGCAP,TENNHACUNGCAP,SDT, DIACHI,TRANGTHAI
             FROM NHACUNGCAP
             WHERE ID_NHACUNGCAP = ?
             """;
        NhaCungCap ncc = null;
        try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                ncc = new NhaCungCap();
                ncc.setID_NCC(rs.getInt(1));
                ncc.setTenNCC(rs.getString(2));
                ncc.setSDT(rs.getString(3));
                ncc.setDiaChi(rs.getString(4));
                ncc.setTrangThai(rs.getInt(5));
            }
            return ncc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<NhaCungCap> selectByName(String ten) {
         sql ="""
             SELECT ID_NHACUNGCAP,TENNHACUNGCAP,SDT, DIACHI,TRANGTHAI
             FROM NHACUNGCAP
             WHERE TENNHACUNGCAP LIKE ?
             """;
        List<NhaCungCap> list = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1,"%"+ten+"%");
            rs = ps.executeQuery();
            while(rs.next()){
                NhaCungCap ncc = new NhaCungCap();
                ncc.setID_NCC(rs.getInt(1));
                ncc.setTenNCC(rs.getString(2));
                ncc.setSDT(rs.getString(3));
                ncc.setDiaChi(rs.getString(4));
                ncc.setTrangThai(rs.getInt(5));
                list.add(ncc);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
     public List<NhaCungCap> selectBySdt(String sdt) {
         sql ="""
             SELECT ID_NHACUNGCAP,TENNHACUNGCAP,SDT, DIACHI,TRANGTHAI
             FROM NHACUNGCAP
             WHERE SDT LIKE ?
             """;
        List<NhaCungCap> list = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1,"%"+sdt+"%");
            rs = ps.executeQuery();
            while(rs.next()){
                NhaCungCap ncc = new NhaCungCap();
                ncc.setID_NCC(rs.getInt(1));
                ncc.setTenNCC(rs.getString(2));
                ncc.setSDT(rs.getString(3));
                ncc.setDiaChi(rs.getString(4));
                ncc.setTrangThai(rs.getInt(5));
                list.add(ncc);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
