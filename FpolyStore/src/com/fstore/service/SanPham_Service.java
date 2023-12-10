
package com.fstore.service;

import com.fstore.model.DanhMuc;
import com.fstore.model.SanPham;
import com.fstore.repository.DBConnect;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author ThongHT
 */
public class SanPham_Service implements Inf_Service<SanPham, Integer>{
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;
    @Override
    public int insert(SanPham model) {
       sql = """
             INSERT INTO SANPHAM(TENSANPHAM, MOTA, TRANGTHAI,ID_DANHMUC) VALUES(?,?,?,?)
             """;
        try {
             conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, model.getTenSP());
            ps.setObject(2, model.getMoTa());
            ps.setObject(3, model.getTrangThai());
            ps.setObject(4, model.getDm().getID_DanhMuc());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(SanPham model, Integer id) {
        sql = """
             UPDATE SANPHAM SET TENSANPHAM = ?, MOTA = ?, TRANGTHAI = ?, ID_DANHMUC = ? 
             WHERE ID_SANPHAM = ? 
             """;
        try {
             conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, model.getTenSP());
            ps.setObject(2, model.getMoTa());
            ps.setObject(3, model.getTrangThai());
            ps.setObject(4, model.getDm().getID_DanhMuc());
            ps.setObject(5, model.getID_SanPham());
            return ps.executeUpdate();
        } catch (Exception e) {
          e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(Integer id) {
         sql = """
             DELETE FROM SANPHAM WHERE ID_SANPHAM = ? 
             """;
        try {
             conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            
            return 0;
        }
    }

    @Override
    public List<SanPham> selectAll() {
            sql ="""
                 SELECT ID_SANPHAM, TENSANPHAM, MOTA, SANPHAM.TRANGTHAI,
                 SANPHAM.ID_DANHMUC, TENDANHMUC
                 FROM SANPHAM join DANHMUC 
                 ON SANPHAM.ID_DANHMUC = DANHMUC.ID_DANHMUC 
                 """;
            List<SanPham> list = new ArrayList<>();
            try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                SanPham sp = new SanPham();
                sp.setID_SanPham(rs.getInt(1));
                sp.setTenSP(rs.getString(2));
                sp.setMoTa(rs.getString(3));
                sp.setTrangThai(rs.getInt(4));
                sp.setDm(new DanhMuc(rs.getInt(5), rs.getString(6)));
                list.add(sp);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public SanPham selectByID(Integer id) {
         sql ="""
                 SELECT ID_SANPHAM, TENSANPHAM, MOTA, SANPHAM.TRANGTHAI,
                 SANPHAM.ID_DANHMUC, TENDANHMUC
                 FROM SANPHAM join DANHMUC 
                 ON SANPHAM.ID_DANHMUC = DANHMUC.ID_DANHMUC 
                 WHERE ID_SANPHAM = ?
                 """;
            SanPham sp = null; 
            try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
               sp = new SanPham();
                sp.setID_SanPham(rs.getInt(1));
                sp.setTenSP(rs.getString(2));
                sp.setMoTa(rs.getString(3));
                sp.setTrangThai(rs.getInt(4));
                sp.setDm(new DanhMuc(rs.getInt(5), rs.getString(6)));
                
            }
            return sp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
     public List<SanPham> selectByName(String ten) {
            sql ="""
                 SELECT ID_SANPHAM, TENSANPHAM, MOTA, SANPHAM.TRANGTHAI,
                 SANPHAM.ID_DANHMUC, TENDANHMUC
                 FROM SANPHAM join DANHMUC 
                 ON SANPHAM.ID_DANHMUC = DANHMUC.ID_DANHMUC 
                 WHERE TENSANPHAM like ?
                 """;
            List<SanPham> list = new ArrayList<>();
            try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, "%"+ten+"%");
            rs = ps.executeQuery();
            while(rs.next()){
                SanPham sp = new SanPham();
                sp.setID_SanPham(rs.getInt(1));
                sp.setTenSP(rs.getString(2));
                sp.setMoTa(rs.getString(3));
                sp.setTrangThai(rs.getInt(4));
                sp.setDm(new DanhMuc(rs.getInt(5), rs.getString(6)));
                list.add(sp);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
      public int updateTrangThai(SanPham model, Integer id) {
        sql = """
             UPDATE SANPHAM SET  TRANGTHAI = ?
             WHERE ID_SANPHAM = ? 
             """;
        try {
             conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, model.getTrangThai());
            ps.setObject(2, model.getID_SanPham());
            return ps.executeUpdate();
        } catch (Exception e) {
          e.printStackTrace();
            return 0;
        }
    }
    public List<SanPham> selectByPage(int page) {
            sql ="""
                 SELECT ID_SANPHAM, TENSANPHAM, MOTA, SANPHAM.TRANGTHAI,
                 SANPHAM.ID_DANHMUC, TENDANHMUC
                 FROM SANPHAM join DANHMUC 
                 ON SANPHAM.ID_DANHMUC = DANHMUC.ID_DANHMUC 
                 ORDER BY ID_SANPHAM
                 OFFSET (? - 1) * 15 ROWS
                 FETCH NEXT 15 ROWS ONLY;
                 """;
            List<SanPham> list = new ArrayList<>();
            try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1,page);
            rs = ps.executeQuery();
            while(rs.next()){
                SanPham sp = new SanPham();
                sp.setID_SanPham(rs.getInt(1));
                sp.setTenSP(rs.getString(2));
                sp.setMoTa(rs.getString(3));
                sp.setTrangThai(rs.getInt(4));
                sp.setDm(new DanhMuc(rs.getInt(5), rs.getString(6)));
                list.add(sp);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
