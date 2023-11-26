
package com.fstore.service;

import com.fstore.model.Voucher_ChiTiet;
import com.fstore.repository.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Admin
 */
public class Voucher_CT_Service implements  Inf_Service<Voucher_ChiTiet, Integer>{
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;
    @Override
    public int insert(Voucher_ChiTiet model) {
        sql = """
                INSERT INTO VOUCHER_CT(ID_VOUCHER,ID_KHACHHANG, ID_HOADON, TRANGTHAI,SOTIENGIAM )
                VALUES(?,?,?,?,?)
                """;
        try {
             conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, model.getId_Voucher());
            ps.setObject(2, model.getId_KhachHang());
            ps.setDouble(3, model.getId_HoaDon());
            ps.setObject(4, model.getTrangThai());
            ps.setObject(5, model.getSoTienGiam());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(Voucher_ChiTiet model, Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Voucher_ChiTiet> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Voucher_ChiTiet selectByID(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public Voucher_ChiTiet selectByIDHD(Integer idHD){
         sql = """
             SELECT  ID_VOUCHER_CT, ID_VOUCHER, ID_HOADON, TRANGTHAI, SOTIENGIAM 
             FROM VOUCHER_CT
              WHERE ID_HOADON = ?
              """;
            Voucher_ChiTiet vct = null;
            try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, idHD);
            rs = ps.executeQuery();
            while(rs.next()){
                 vct = new Voucher_ChiTiet() ;
                 vct.setId_Voucher_CT(rs.getInt(1));
                 vct.setId_Voucher(rs.getInt(2));
                 vct.setId_HoaDon(rs.getInt(3));
                 vct.setTrangThai(rs.getInt(4));
                 vct.setSoTienGiam(rs.getDouble(5));
            }
            return vct;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }
}
