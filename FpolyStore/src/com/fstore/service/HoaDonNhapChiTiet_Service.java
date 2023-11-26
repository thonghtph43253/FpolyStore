
package com.fstore.service;

import com.fstore.model.HoaDonNhap_ChiTiet;
import com.fstore.model.HoaDon_ChiTiet;
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
public class HoaDonNhapChiTiet_Service implements Inf_Service<HoaDonNhap_ChiTiet, Integer>{
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;
    @Override
    public int insert(HoaDonNhap_ChiTiet model) {
        sql = """
                INSERT INTO HOADONNHAP_CT( ID_HOADONNHAP, ID_SANPHAMCHITIET,GIANHAP,SOLUONGNHAP, TRANGTHAI)
                VALUES((SELECT TOP 1 ID_HOADONNHAP from HOADONNHAP ORDER BY ID_HOADONNHAP DESC),?,?,?,?)
            """;
        try {
             conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, model.getId_SanPhamChiTiet());
            ps.setObject(2, model.getGiaNhap());
            ps.setObject(3, model.getSoLuong());
            ps.setObject(4, model.getTrangThai());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(HoaDonNhap_ChiTiet model, Integer id) {
         sql = """
                UPDATE HOADONNHAP_CT SET ID_HOADONNHAP = ?, ID_SANPHAMCHITIET = ?,
                GIANHAP = ?,SOLUONGNHAP = ?, TRANGTHAI = ?
                WHERE ID_HOADONNHAP_CT = ?
            """;
        try {
             conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, model.getId_HoaDonNhap());
            ps.setObject(2, model.getId_SanPhamChiTiet());
            ps.setObject(3, model.getGiaNhap());
            ps.setObject(4, model.getSoLuong());
            ps.setObject(5, model.getTrangThai());
            ps.setObject(6, id);
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
    public List<HoaDonNhap_ChiTiet> selectAll() {
        sql = """
              SELECT ID_HOADONNHAP_CT,HDCT.ID_HOADONNHAP,HDCT.ID_SANPHAMCHITIET,HDCT.GIANHAP,
                            HDCT.SOLUONGNHAP, HDCT.TRANGTHAI 
                            FROM HOADONNHAP_CT HDCT JOIN HOADONNHAP HD ON HDCT.ID_HOADONNHAP = HD.ID_HOADONNHAP
                            JOIN SANPHAMCHITIET SPCT ON HDCT.ID_SANPHAMCHITIET = SPCT.ID_SANPHAMCHITIET 
              """;
            List<HoaDonNhap_ChiTiet> list = new ArrayList<>();
            try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                HoaDonNhap_ChiTiet hdnct = new HoaDonNhap_ChiTiet();
                hdnct.setId_HoaDonNhapCT(rs.getInt(1));
                hdnct.setId_HoaDonNhap(rs.getInt(2));
                hdnct.setId_SanPhamChiTiet(rs.getInt(3));
                hdnct.setGiaNhap(rs.getDouble(4));
                hdnct.setSoLuong(rs.getInt(5));
                hdnct.setTrangThai(rs.getInt(6));
                list.add(hdnct);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public HoaDonNhap_ChiTiet selectByID(Integer id) {
        sql = """
              SELECT ID_HOADONNHAP_CT,HDCT.ID_HOADONNHAP,HDCT.ID_SANPHAMCHITIET,HDCT.GIANHAP,
                            HDCT.SOLUONGNHAP, HDCT.TRANGTHAI 
                            FROM HOADONNHAP_CT HDCT JOIN HOADONNHAP HD ON HDCT.ID_HOADONNHAP = HD.ID_HOADONNHAP
                            JOIN SANPHAMCHITIET SPCT ON HDCT.ID_SANPHAMCHITIET = SPCT.ID_SANPHAMCHITIET 
              WHERE ID_HOADONNHAP_CT = ?
              """;
            HoaDonNhap_ChiTiet hdnct = null;
            try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                hdnct = new HoaDonNhap_ChiTiet();
                hdnct.setId_HoaDonNhapCT(rs.getInt(1));
                hdnct.setId_HoaDonNhap(rs.getInt(2));
                hdnct.setId_SanPhamChiTiet(rs.getInt(3));
                hdnct.setGiaNhap(rs.getDouble(4));
                hdnct.setSoLuong(rs.getInt(5));
                hdnct.setTrangThai(rs.getInt(6));
            }
            return hdnct;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
     public List<HoaDonNhap_ChiTiet> selectAllByID_HDN( int id_Hdn) {
        sql = """
              SELECT ID_HOADONNHAP_CT,HDCT.ID_HOADONNHAP,HDCT.ID_SANPHAMCHITIET,HDCT.GIANHAP,
                            HDCT.SOLUONGNHAP, HDCT.TRANGTHAI 
                            FROM HOADONNHAP_CT HDCT JOIN HOADONNHAP HD ON HDCT.ID_HOADONNHAP = HD.ID_HOADONNHAP
                            JOIN SANPHAMCHITIET SPCT ON HDCT.ID_SANPHAMCHITIET = SPCT.ID_SANPHAMCHITIET 
              WHERE HD.ID_HOADONNHAP = ?
              """;
            List<HoaDonNhap_ChiTiet> list = new ArrayList<>();
            try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, id_Hdn);
            rs = ps.executeQuery();
            while(rs.next()){
                HoaDonNhap_ChiTiet hdnct = new HoaDonNhap_ChiTiet();
                hdnct.setId_HoaDonNhapCT(rs.getInt(1));
                hdnct.setId_HoaDonNhap(rs.getInt(2));
                hdnct.setId_SanPhamChiTiet(rs.getInt(3));
                hdnct.setGiaNhap(rs.getDouble(4));
                hdnct.setSoLuong(rs.getInt(5));
                hdnct.setTrangThai(rs.getInt(6));
                list.add(hdnct);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
