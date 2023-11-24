
package com.fstore.service;

import com.fstore.model.Sale;
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
public class Sale_Service implements Inf_Service<Sale, Integer>{
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;
    
    @Override
    public int insert(Sale model) {
          sql = """
                INSERT INTO SALE(TENCHIENDICH, THOIGIANBD, THOIGIANKT, TRANGTHAI)
                VALUES(?,?,?,?)
                """;
        try {
             conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, model.getTenChienDich());
            ps.setObject(2, model.getThoiGianBD());
            ps.setObject(3, model.getThoiGianKT());
            ps.setObject(4, model.getTrangThai());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(Sale model, Integer id) {
         sql = """
                UPDATE SALE SET TENCHIENDICH = ?, THOIGIANBD = ?, THOIGIANKT = ?, TRANGTHAI = ?
                WHERE ID_SALE = ?
                """;
        try {
             conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, model.getTenChienDich());
            ps.setObject(2, model.getThoiGianBD());
            ps.setObject(3, model.getThoiGianKT());
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
                DELETE  FROM SALE 
                WHERE ID_SALE = ?
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
    public List<Sale> selectAll() {
         sql = """
               SELECT ID_SALE,TENCHIENDICH, THOIGIANBD, THOIGIANKT, TRANGTHAI 
               FROM SALE
              """;
            List<Sale> list = new ArrayList<>();
            try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Sale s = new Sale();
                s.setId_Sale(rs.getInt(1));
                s.setTenChienDich(rs.getString(2));
                s.setThoiGianBD(rs.getDate(3));
                s.setThoiGianKT(rs.getDate(4));
                s.setTrangThai(rs.getInt(5));
                list.add(s);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Sale selectByID(Integer id) {
         sql = """
               SELECT ID_SALE,TENCHIENDICH, THOIGIANBD, THOIGIANKT, TRANGTHAI 
               FROM SALE
               WHERE ID_SALE = ?
              """;
            Sale s = null;
            try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                 s = new Sale();
                s.setId_Sale(rs.getInt(1));
                s.setTenChienDich(rs.getString(2));
                s.setThoiGianBD(rs.getDate(3));
                s.setThoiGianKT(rs.getDate(4));
                s.setTrangThai(rs.getInt(5));
            }
            return s;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
