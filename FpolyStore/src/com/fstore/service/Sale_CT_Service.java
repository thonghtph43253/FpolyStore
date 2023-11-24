
package com.fstore.service;

import com.fstore.model.Sale_ChiTiet;
import com.fstore.repository.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public class Sale_CT_Service implements Inf_Service<Sale_ChiTiet, Integer>{
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;
    @Override
    public int insert(Sale_ChiTiet model) {
         sql = """
                INSERT INTO SALE_CHITIET(HINHTHUCGIAM, GIATRIGIAM, ID_SALE, ID_SANPHAMCHITIET,TRANGTHAI)
                VALUES(?,?,(SELECT TOP 1 ID_SALE FROM SALE ORDER BY ID_SALE DESC),?,?)
                """;
        try {
             conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, model.getHinhThucGiam());
            ps.setObject(2, model.getGiaTriGiam());
            ps.setObject(3, model.getId_SanPham_CT());
            ps.setObject(4, model.getTrangThai());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(Sale_ChiTiet model, Integer id) {
        sql = """
                UPDATE  SALE_CHITIET SET HINHTHUCGIAM = ?, GIATRIGIAM = ?,
                 ID_SALE = ?, ID_SANPHAMCHITIET = ?, TRANGTHAI = ?
                 WHERE ID_SALE_CHITIET = ?
                """;
        try {
             conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, model.getHinhThucGiam());
            ps.setObject(2, model.getGiaTriGiam());
            ps.setObject(3, model.getId_Sale());
            ps.setObject(4, model.getId_SanPham_CT());
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
        sql = """
                DELETE FROM  SALE_CHITIET 
                WHERE ID_SALE_CHITIET
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
    public List<Sale_ChiTiet> selectAll() {
       sql = """
               SELECT ID_SALE_CHITIET, HINHTHUCGIAM, GIATRIGIAM, ID_SALE,
               ID_SANPHAMCHITIET,TRANGTHAI 
               FROM SALE_CHITIET
              """;
            List<Sale_ChiTiet> list = new ArrayList<>();
            try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Sale_ChiTiet sct = new Sale_ChiTiet();
                sct.setId_Sale_CT(rs.getInt(1));
                sct.setHinhThucGiam(rs.getInt(2));
                sct.setGiaTriGiam(rs.getDouble(3));
                sct.setId_Sale(rs.getInt(4));
                sct.setId_SanPham_CT(rs.getInt(5));
                sct.setTrangThai(rs.getInt(6));
                list.add(sct);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Sale_ChiTiet selectByID(Integer id) {
        sql = """
               SELECT ID_SALE_CHITIET, HINHTHUCGIAM, GIATRIGIAM, ID_SALE,
               ID_SANPHAMCHITIET,TRANGTHAI 
               FROM SALE_CHITIET
               WHERE ID_SALE_CHITIET = ?
              """;
            Sale_ChiTiet sct = null;
            try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                sct = new Sale_ChiTiet();
                sct.setId_Sale_CT(rs.getInt(1));
                sct.setHinhThucGiam(rs.getInt(2));
                sct.setGiaTriGiam(rs.getDouble(3));
                sct.setId_Sale(rs.getInt(4));
                sct.setId_SanPham_CT(rs.getInt(5));
                sct.setTrangThai(rs.getInt(6));
               
            }
            return sct;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
     public List<Sale_ChiTiet> selectByIDSale( int id_sale) {
       sql = """
               SELECT ID_SALE_CHITIET, HINHTHUCGIAM, GIATRIGIAM, SCT.ID_SALE,
               ID_SANPHAMCHITIET,SCT.TRANGTHAI
               FROM SALE_CHITIET SCT JOIN SALE S ON SCT.ID_SALE = S.ID_SALE
               WHERE SCT.ID_SALE = ? 
              """;
            List<Sale_ChiTiet> list = new ArrayList<>();
            try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, id_sale);
            rs = ps.executeQuery();
            while(rs.next()){
                Sale_ChiTiet sct = new Sale_ChiTiet();
                sct.setId_Sale_CT(rs.getInt(1));
                sct.setHinhThucGiam(rs.getInt(2));
                sct.setGiaTriGiam(rs.getDouble(3));
                sct.setId_Sale(rs.getInt(4));
                sct.setId_SanPham_CT(rs.getInt(5));
                sct.setTrangThai(rs.getInt(6));
                list.add(sct);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
     public List<Sale_ChiTiet> selectByIDSaleAndTT( int id_sale) {
       sql = """
               SELECT ID_SALE_CHITIET, HINHTHUCGIAM, GIATRIGIAM, SCT.ID_SALE,
               ID_SANPHAMCHITIET,SCT.TRANGTHAI
               FROM SALE_CHITIET SCT JOIN SALE S ON SCT.ID_SALE = S.ID_SALE
               WHERE SCT.ID_SALE = ? AND SCT.TRANGTHAI = 1
              """;
            List<Sale_ChiTiet> list = new ArrayList<>();
            try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, id_sale);
            rs = ps.executeQuery();
            while(rs.next()){
                Sale_ChiTiet sct = new Sale_ChiTiet();
                sct.setId_Sale_CT(rs.getInt(1));
                sct.setHinhThucGiam(rs.getInt(2));
                sct.setGiaTriGiam(rs.getDouble(3));
                sct.setId_Sale(rs.getInt(4));
                sct.setId_SanPham_CT(rs.getInt(5));
                sct.setTrangThai(rs.getInt(6));
                list.add(sct);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
     
     public Sale_ChiTiet selectByID_SPCT(Integer id_SPCT, Date d) {
        sql = """
              SELECT HINHTHUCGIAM,GIATRIGIAM FROM SALE_CHITIET  SCT JOIN SALE S ON SCT.ID_SALE = S.ID_SALE
              WHERE THOIGIANBD <= ? AND THOIGIANKT >= ? 
                    AND SCT.ID_SANPHAMCHITIET = ?
                    AND S.TRANGTHAI = 1 
                    AND SCT.TRANGTHAI = 1
              """;
            Sale_ChiTiet sct = null;
            try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, d);
            ps.setObject(2, d);
            ps.setObject(3, id_SPCT);
            rs = ps.executeQuery();
            while(rs.next()){
                sct = new Sale_ChiTiet();
                sct.setHinhThucGiam(rs.getInt(1));
                sct.setGiaTriGiam(rs.getDouble(2));
            }
            return sct;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
     
}
