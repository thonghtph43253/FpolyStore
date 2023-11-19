
package com.fstore.service;

import com.fstore.model.SanPhamChiTiet;
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
public class SanPhamChiTiet_Service implements Inf_Service<SanPhamChiTiet, Integer>{
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;
    @Override
    public int insert(SanPhamChiTiet model) {
        sql = """
             INSERT INTO SANPHAMCHITIET(SOLUONG, GIA, TRANGTHAI,ID_SANPHAM, ID_CHATLIEU, ID_SIZE, ID_MAU)
             VALUES(?,?,?,?,?,?,?)
             """;
        try {
             conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, model.getSoLuong());
            ps.setObject(2, model.getGia());
            ps.setObject(3, model.getTrangThai());
            ps.setObject(4, model.getId_SanPham());
            ps.setObject(5, model.getId_ChatLieu());
            ps.setObject(6, model.getId_Size());
            ps.setObject(7, model.getId_Mau());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(SanPhamChiTiet model, Integer id) {
        sql = """
             INSERT INTO SANPHAMCHITIET(SOLUONG, GIA, TRANGTHAI,ID_SANPHAM, ID_CHATLIEU, ID_SIZE, ID_MAU)
             VALUES(?,?,?,?,?,?,?)
             """;
        try {
             conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, model.getSoLuong());
            ps.setObject(2, model.getGia());
            ps.setObject(3, model.getTrangThai());
            ps.setObject(4, model.getId_SanPham());
            ps.setObject(5, model.getId_ChatLieu());
            ps.setObject(6, model.getId_Size());
            ps.setObject(7, model.getId_Mau());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<SanPhamChiTiet> selectAll() {
            sql ="""
                 SELECT ID_SANPHAMCHITIET, SOLUONG,GIA,SPCT.TRANGTHAI,ID_CHATLIEU,ID_SIZE,ID_MAU, SPCT.ID_SANPHAM
                 FROM SANPHAMCHITIET AS SPCT JOIN SANPHAM AS SP ON SPCT.ID_SANPHAM = SP.ID_SANPHAM 
                 
                 """;
            List<SanPhamChiTiet> list = new ArrayList<>();
            try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setId_SanPhamChiTiet(rs.getInt(1));
                spct.setSoLuong(rs.getInt(2));
                spct.setGia(rs.getDouble(3));
                spct.setTrangThai(rs.getInt(4));
                spct.setId_ChatLieu(rs.getInt(5));
                spct.setId_Size(rs.getInt(6));
                spct.setId_Mau(rs.getInt(7));
                spct.setId_SanPhamChiTiet(rs.getInt(8));
                list.add(spct);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    

    @Override
    public SanPhamChiTiet selectByID(Integer id) {
        sql ="""
                 SELECT ID_SANPHAMCHITIET, SOLUONG,GIA,SPCT.TRANGTHAI,ID_CHATLIEU,ID_SIZE,ID_MAU, SPCT.ID_SANPHAM
                 FROM SANPHAMCHITIET AS SPCT JOIN SANPHAM AS SP ON SPCT.ID_SANPHAM = SP.ID_SANPHAM 
                 WHERE SPCT.ID_SANPHAMCHITIET = ?
                 """;
            SanPhamChiTiet spct = null;
            try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                spct = new SanPhamChiTiet();
                spct.setId_SanPhamChiTiet(rs.getInt(1));
                spct.setSoLuong(rs.getInt(2));
                spct.setGia(rs.getDouble(3));
                spct.setTrangThai(rs.getInt(4));
                spct.setId_ChatLieu(rs.getInt(5));
                spct.setId_Size(rs.getInt(6));
                spct.setId_Mau(rs.getInt(7));
                spct.setId_SanPhamChiTiet(rs.getInt(8));
            }
            return spct;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public SanPhamChiTiet selectByID_SP(Integer id) {
        sql ="""
                 SELECT ID_SANPHAMCHITIET, SOLUONG,GIA,SPCT.TRANGTHAI,ID_CHATLIEU,ID_SIZE,ID_MAU, SPCT.ID_SANPHAM
                 FROM SANPHAMCHITIET AS SPCT JOIN SANPHAM AS SP ON SPCT.ID_SANPHAM = SP.ID_SANPHAM 
                 WHERE SPCT.ID_SANPHAM = ?
                 """;
            SanPhamChiTiet spct = null;
            try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                spct = new SanPhamChiTiet();
                spct.setId_SanPhamChiTiet(rs.getInt(1));
                spct.setSoLuong(rs.getInt(2));
                spct.setGia(rs.getDouble(3));
                spct.setTrangThai(rs.getInt(4));
                spct.setId_ChatLieu(rs.getInt(5));
                spct.setId_Size(rs.getInt(6));
                spct.setId_Mau(rs.getInt(7));
                spct.setId_SanPhamChiTiet(rs.getInt(8));
            }
            return spct;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}