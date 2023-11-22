
package com.fstore.service;

import com.fstore.model.HoaDon_ChiTiet;
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
public class HoaDonChiTiet_Service implements Inf_Service<HoaDon_ChiTiet, Integer>{
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;
    @Override
    public int insert(HoaDon_ChiTiet model) {
        sql = """
                INSERT INTO HOADON_CT( ID_HOADON, ID_SANPHAMCHITIET,GIA,SOLUONG)
                VALUES(?,?,?,?)
            """;
        try {
             conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, model.getId_HoaDon());
            ps.setObject(2, model.getId_SanPhamChiTiet());
            ps.setObject(3, model.getGia());
            ps.setObject(4, model.getSoLuong());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(HoaDon_ChiTiet model, Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<HoaDon_ChiTiet> selectAll() {
         sql = """
              SELECT ID_HOADON_CT,HDCT.ID_HOADON,HDCT.ID_SANPHAMCHITIET,HDCT.GIA, HDCT.SOLUONG 
              FROM HOADON_CT HDCT JOIN HOADON HD ON HDCT.ID_HOADON = HD.ID_HOADON
              JOIN SANPHAMCHITIET SPCT ON HDCT.ID_SANPHAMCHITIET = SPCT.ID_SANPHAMCHITIET 
              """;
            List<HoaDon_ChiTiet> list = new ArrayList<>();
            try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                HoaDon_ChiTiet hd = new HoaDon_ChiTiet();
                hd.setId_HoaDonCT(rs.getInt(1));
                hd.setId_HoaDon(rs.getInt(2));
                hd.setId_SanPhamChiTiet(rs.getInt(3));
                hd.setGia(rs.getDouble(4));
                hd.setSoLuong(rs.getInt(5));
                list.add(hd);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public HoaDon_ChiTiet selectByID(Integer id) {
        sql = """
              SELECT ID_HOADON_CT,HDCT.ID_HOADON,HDCT.ID_SANPHAMCHITIET,HDCT.GIA,HDCT.SOLUONG 
              FROM HOADON_CT HDCT JOIN HOADON HD ON HDCT.ID_HOADON = HD.ID_HOADON
              JOIN SANPHAMCHITIET SPCT ON HDCT.ID_SANPHAMCHITIET = SPCT.ID_SANPHAMCHITIET 
              WHERE ID_HOADON_CT = ?
              """;
            HoaDon_ChiTiet hd = null;
            try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                 hd = new HoaDon_ChiTiet();
                hd.setId_HoaDonCT(rs.getInt(1));
                hd.setId_HoaDon(rs.getInt(2));
                hd.setId_SanPhamChiTiet(rs.getInt(3));
                hd.setGia(rs.getDouble(4));
                hd.setSoLuong(rs.getInt(5));
            }
            return hd;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
     public List<HoaDon_ChiTiet> selectByID_HoaDon(int idhd) {
         sql = """
              SELECT ID_HOADON_CT,HDCT.ID_HOADON,HDCT.ID_SANPHAMCHITIET,HDCT.GIA 
              FROM HOADON_CT HDCT JOIN HOADON HD ON HDCT.ID_HOADON = HD.ID_HOADON
              JOIN SANPHAMCHITIET SPCT ON HDCT.ID_SANPHAMCHITIET = SPCT.ID_SANPHAMCHITIET 
              WHERE HD.ID_HOADON = ?
              """;
            List<HoaDon_ChiTiet> list = new ArrayList<>();
            try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, idhd);
            rs = ps.executeQuery();
            while(rs.next()){
                HoaDon_ChiTiet hd = new HoaDon_ChiTiet();
                hd.setId_HoaDonCT(rs.getInt(1));
                hd.setId_HoaDon(rs.getInt(2));
                hd.setId_SanPhamChiTiet(rs.getInt(3));
                hd.setGia(rs.getDouble(4));
                list.add(hd);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
