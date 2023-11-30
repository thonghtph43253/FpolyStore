
package com.fstore.service;

import com.fstore.model.Voucher;
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
public class Voucher_Service implements Inf_Service<Voucher, Integer>{
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;
    @Override
    public int insert(Voucher model) {
        sql = """
                INSERT INTO VOUCHERS(TENCHIENDICH, HINHTHUCGIAM,GIATRIGIAM, NGAYBD, NGAYKT,SOLUONG,TRANGTHAI )
                VALUES(?,?,?,?,?,?,?)
                """;
        try {
             conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, model.getTenChienDich());
            ps.setObject(2, model.getHinhThucGiam());
            ps.setDouble(3, model.getGiaTriGiam());
            ps.setObject(4, model.getThoiGianBD());
            ps.setObject(5, model.getThoiGianKT());
            ps.setObject(6, model.getSoLuong());
            ps.setObject(7, model.getTrangThai());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(Voucher model, Integer id) {
         sql = """
                UPDATE  VOUCHERS SET TENCHIENDICH = ?, HINHTHUCGIAM =?,GIATRIGIAM=?,
                NGAYBD=?, NGAYKT=?,SOLUONG= ?,TRANGTHAI = ? 
                WHERE ID_VOUCHER = ?
                """;
        try {
             conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, model.getTenChienDich());
            ps.setObject(2, model.getHinhThucGiam());
            ps.setDouble(3, model.getGiaTriGiam());
            ps.setObject(4, model.getThoiGianBD());
            ps.setObject(5, model.getThoiGianKT());
            ps.setObject(6, model.getSoLuong());
            ps.setObject(7, model.getTrangThai());
            ps.setObject(8, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(Integer id) {
        sql = """
                DELETE FROM VOUCHERS 
                WHERE ID_VOUCHER = ?
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
    public List<Voucher> selectAll() {
         sql = """
              SELECT ID_VOUCHER, TENCHIENDICH, NGAYBD, NGAYKT, HINHTHUCGIAM,
              GIATRIGIAM, SOLUONG, TRANGTHAI 
              FROM VOUCHERS
              """;
            List<Voucher> list = new ArrayList<>();
            try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Voucher v = new Voucher();
                v.setId_Voucher(rs.getInt(1));
                v.setTenChienDich(rs.getString(2));
                v.setThoiGianBD(rs.getDate(3));
                v.setThoiGianKT(rs.getDate(4));
                v.setHinhThucGiam(rs.getInt(5));
                v.setGiaTriGiam(rs.getDouble(6));
                v.setSoLuong(rs.getInt(7));
                v.setTrangThai(rs.getInt(8));
                list.add(v);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Voucher selectByID(Integer id) {
        sql = """
              SELECT ID_VOUCHER, TENCHIENDICH, NGAYBD, NGAYKT, HINHTHUCGIAM,
              GIATRIGIAM, SOLUONG, TRANGTHAI 
              FROM VOUCHERS
              WHERE ID_VOUCHER = ?
              """;
            Voucher v = null;
            try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                 v = new Voucher();
                v.setId_Voucher(rs.getInt(1));
                v.setTenChienDich(rs.getString(2));
                v.setThoiGianBD(rs.getDate(3));
                v.setThoiGianKT(rs.getDate(4));
                v.setHinhThucGiam(rs.getInt(5));
                v.setGiaTriGiam(rs.getDouble(6));
                v.setSoLuong(rs.getInt(7));
                v.setTrangThai(rs.getInt(8));
            }
            return v;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Voucher> selectByDate( Date d) {
         sql = """
              SELECT ID_VOUCHER, TENCHIENDICH, NGAYBD, NGAYKT, HINHTHUCGIAM,
              GIATRIGIAM, SOLUONG, TRANGTHAI 
              FROM VOUCHERS
              WHERE NGAYBD <= ? AND NGAYKT >= DATEADD(DAY, 1, ?) 
              """;
            List<Voucher> list = new ArrayList<>();
            try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, d);
            ps.setObject(2, d);
            rs = ps.executeQuery();
            while(rs.next()){
                Voucher v = new Voucher();
                v.setId_Voucher(rs.getInt(1));
                v.setTenChienDich(rs.getString(2));
                v.setThoiGianBD(rs.getDate(3));
                v.setThoiGianKT(rs.getDate(4));
                v.setHinhThucGiam(rs.getInt(5));
                v.setGiaTriGiam(rs.getDouble(6));
                v.setSoLuong(rs.getInt(7));
                v.setTrangThai(rs.getInt(8));
                list.add(v);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Voucher> selectByName(String name) {
         sql = """
              SELECT ID_VOUCHER, TENCHIENDICH, NGAYBD, NGAYKT, HINHTHUCGIAM,
              GIATRIGIAM, SOLUONG, TRANGTHAI 
              FROM VOUCHERS
              WHERE TENCHIENDICH LIKE ?
              """;
            List<Voucher> list = new ArrayList<>();
            try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, "%"+name+"%");
            rs = ps.executeQuery();
            while(rs.next()){
                Voucher v = new Voucher();
                v.setId_Voucher(rs.getInt(1));
                v.setTenChienDich(rs.getString(2));
                v.setThoiGianBD(rs.getDate(3));
                v.setThoiGianKT(rs.getDate(4));
                v.setHinhThucGiam(rs.getInt(5));
                v.setGiaTriGiam(rs.getDouble(6));
                v.setSoLuong(rs.getInt(7));
                v.setTrangThai(rs.getInt(8));
                list.add(v);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Voucher> selectByDateTT( Date bd, Date kt , int tt) {
         sql = """
              SELECT ID_VOUCHER, TENCHIENDICH, NGAYBD, NGAYKT, HINHTHUCGIAM,
              GIATRIGIAM, SOLUONG, TRANGTHAI 
              FROM VOUCHERS
              WHERE NGAYBD <= ? AND NGAYKT >= DATEADD(DAY, 1, ?) AND TRANGTHAI = ?
              """;
            List<Voucher> list = new ArrayList<>();
            try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, bd);
            ps.setObject(2, kt);
            ps.setObject(3, tt);
            rs = ps.executeQuery();
            while(rs.next()){
                Voucher v = new Voucher();
                v.setId_Voucher(rs.getInt(1));
                v.setTenChienDich(rs.getString(2));
                v.setThoiGianBD(rs.getDate(3));
                v.setThoiGianKT(rs.getDate(4));
                v.setHinhThucGiam(rs.getInt(5));
                v.setGiaTriGiam(rs.getDouble(6));
                v.setSoLuong(rs.getInt(7));
                v.setTrangThai(rs.getInt(8));
                list.add(v);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public int updateSoLuong(Integer id) {
         sql = """
                UPDATE  VOUCHERS SET SOLUONG= SOLUONG - 1
                WHERE ID_VOUCHER = ?
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
}
