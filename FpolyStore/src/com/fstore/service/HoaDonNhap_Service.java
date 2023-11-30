
package com.fstore.service;

import com.fstore.model.HoaDonNhap;
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
public class HoaDonNhap_Service implements Inf_Service<HoaDonNhap, Integer>{
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;
    @Override
    public int insert(HoaDonNhap model) {
         sql = """
                INSERT INTO HOADONNHAP(TENNHACUNGCAP, NGAYTAO, SDT, TONGTIEN,
                                    TRANGTHAI,ID_NHACUNGCAP, MANV, GHICHU)
                VALUES(?,?,?,?,?,?,?, ?)
            """;
        try {
             conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, model.getTenNhaCungCap());
            ps.setObject(2, model.getNgayTao());
            ps.setObject(3, model.getSoDienThoai());
            ps.setObject(4, model.getTongTien());
            ps.setObject(5, model.getTrangThai());
            ps.setObject(6, model.getId_NhaCungCap());
            ps.setObject(7, model.getMaNv());
            ps.setObject(8, model.getGhiChu());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(HoaDonNhap model, Integer id) {
         sql = """
                UPDATE HOADONNHAP SET TENNHACUNGCAP = ?, NGAYTAO =?, SDT = ?,
                                  TONGTIEN = ?,TRANGTHAI = ?,ID_NHACUNGCAP = ?,
                                  MANV = ?, GHICHU =? 
                WHERE ID_HOADONNHAP = ?
            """;
        try {
             conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, model.getTenNhaCungCap());
            ps.setObject(2, model.getNgayTao());
            ps.setObject(3, model.getSoDienThoai());
            ps.setObject(4, model.getTongTien());
            ps.setObject(5, model.getTrangThai());
            ps.setObject(6, model.getId_NhaCungCap());
            ps.setObject(7, model.getMaNv());
            ps.setObject(8, model.getGhiChu());
            ps.setObject(9, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(Integer id) {
         sql = """
                DELETE FROM HOADONNHAP
                WHERE ID_HOADONNHAP = ?
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
    public List<HoaDonNhap> selectAll() {
        sql = """
                SELECT ID_HOADONNHAP,HDN.TENNHACUNGCAP, NGAYTAO, HDN.SDT, TONGTIEN,
                       HDN.TRANGTHAI, NCC.ID_NHACUNGCAP, NV.MANV
                FROM HOADONNHAP HDN 
                JOIN NHANVIEN NV ON HDN.MANV = NV.MANV
                JOIN NHACUNGCAP NCC ON HDN.ID_NHACUNGCAP = NCC.ID_NHACUNGCAP
              """;
            List<HoaDonNhap> list = new ArrayList<>();
            try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                HoaDonNhap hdn = new HoaDonNhap();
                hdn.setId_HoaDonNhap(rs.getInt(1));
                hdn.setTenNhaCungCap(rs.getString(2));
                hdn.setNgayTao(rs.getString(3));
                hdn.setSoDienThoai(rs.getString(4));
                hdn.setTongTien(rs.getDouble(5));
                hdn.setTrangThai(rs.getInt(6));
                hdn.setId_NhaCungCap(rs.getInt(7));
                hdn.setMaNv(rs.getString(8));
                list.add(hdn);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public HoaDonNhap selectByID(Integer id) {
         sql = """
                SELECT ID_HOADONNHAP,HDN.TENNHACUNGCAP, NGAYTAO, HDN.SDT, TONGTIEN,
                       HDN.TRANGTHAI, NCC.ID_NHACUNGCAP, NV.MANV
                FROM HOADONNHAP HDN 
                JOIN NHANVIEN NV ON HDN.MANV = NV.MANV
                JOIN NHACUNGCAP NCC ON HDN.ID_NHACUNGCAP = NCC.ID_NHACUNGCAP
               WHERE ID_HOADONNHAP = ?
              """;
           HoaDonNhap hdn = null;
            try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                hdn = new HoaDonNhap();
                hdn.setId_HoaDonNhap(rs.getInt(1));
                hdn.setTenNhaCungCap(rs.getString(2));
                hdn.setNgayTao(rs.getString(3));
                hdn.setSoDienThoai(rs.getString(4));
                hdn.setTongTien(rs.getDouble(5));
                hdn.setTrangThai(rs.getInt(6));
                hdn.setId_NhaCungCap(rs.getInt(7));
                hdn.setMaNv(rs.getString(8));
            }
            return hdn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public int getID_HoaDonNhap(){
        sql = """
                SELECT TOP 1 ID_HOADONNHAP from HOADONNHAP ORDER BY ID_HOADONNHAP DESC
              """;
            int id = 0;
            try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                id =rs.getInt(1);
            }
           return  id;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
     public List<HoaDonNhap> selectName(String ten) {
        sql = """
                SELECT ID_HOADONNHAP,HDN.TENNHACUNGCAP, NGAYTAO, HDN.SDT, TONGTIEN,
                       HDN.TRANGTHAI, NCC.ID_NHACUNGCAP, NV.MANV
                FROM HOADONNHAP HDN 
                JOIN NHANVIEN NV ON HDN.MANV = NV.MANV
                JOIN NHACUNGCAP NCC ON HDN.ID_NHACUNGCAP = NCC.ID_NHACUNGCAP
                WHERE HDN.TENNHACUNGCAP LIKE ?
              """;
            List<HoaDonNhap> list = new ArrayList<>();
            try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1,"%"+ten+"%");
            rs = ps.executeQuery();
            while(rs.next()){
                HoaDonNhap hdn = new HoaDonNhap();
                hdn.setId_HoaDonNhap(rs.getInt(1));
                hdn.setTenNhaCungCap(rs.getString(2));
                hdn.setNgayTao(rs.getString(3));
                hdn.setSoDienThoai(rs.getString(4));
                hdn.setTongTien(rs.getDouble(5));
                hdn.setTrangThai(rs.getInt(6));
                hdn.setId_NhaCungCap(rs.getInt(7));
                hdn.setMaNv(rs.getString(8));
                list.add(hdn);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
     
      public List<HoaDonNhap> selectSDT(String sdt) {
        sql = """
                SELECT ID_HOADONNHAP,HDN.TENNHACUNGCAP, NGAYTAO, HDN.SDT, TONGTIEN,
                       HDN.TRANGTHAI, NCC.ID_NHACUNGCAP, NV.MANV
                FROM HOADONNHAP HDN 
                JOIN NHANVIEN NV ON HDN.MANV = NV.MANV
                JOIN NHACUNGCAP NCC ON HDN.ID_NHACUNGCAP = NCC.ID_NHACUNGCAP
                WHERE HDN.SDT LIKE ?
              """;
            List<HoaDonNhap> list = new ArrayList<>();
            try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1,"%"+sdt+"%");
            rs = ps.executeQuery();
            while(rs.next()){
                HoaDonNhap hdn = new HoaDonNhap();
                hdn.setId_HoaDonNhap(rs.getInt(1));
                hdn.setTenNhaCungCap(rs.getString(2));
                hdn.setNgayTao(rs.getString(3));
                hdn.setSoDienThoai(rs.getString(4));
                hdn.setTongTien(rs.getDouble(5));
                hdn.setTrangThai(rs.getInt(6));
                hdn.setId_NhaCungCap(rs.getInt(7));
                hdn.setMaNv(rs.getString(8));
                list.add(hdn);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
      }
      public List<HoaDonNhap> selectByDay(Date ngayBd, Date ngayKt, int tt) {
        sql = """
                SELECT ID_HOADONNHAP,HDN.TENNHACUNGCAP, NGAYTAO, HDN.SDT, TONGTIEN,
                       HDN.TRANGTHAI, NCC.ID_NHACUNGCAP, NV.MANV
                FROM HOADONNHAP HDN 
                JOIN NHANVIEN NV ON HDN.MANV = NV.MANV
                JOIN NHACUNGCAP NCC ON HDN.ID_NHACUNGCAP = NCC.ID_NHACUNGCAP
                WHERE NGAYTAO >= ? AND NGAYTAO <= DATEADD(DAY, 1, ?) AND HDN.TRANGTHAI = ?
              """;
            List<HoaDonNhap> list = new ArrayList<>();
            try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1,ngayBd);
            ps.setObject(2,ngayKt);
            ps.setObject(3,tt);
            rs = ps.executeQuery();
            while(rs.next()){
                HoaDonNhap hdn = new HoaDonNhap();
                hdn.setId_HoaDonNhap(rs.getInt(1));
                hdn.setTenNhaCungCap(rs.getString(2));
                hdn.setNgayTao(rs.getString(3));
                hdn.setSoDienThoai(rs.getString(4));
                hdn.setTongTien(rs.getDouble(5));
                hdn.setTrangThai(rs.getInt(6));
                hdn.setId_NhaCungCap(rs.getInt(7));
                hdn.setMaNv(rs.getString(8));
                list.add(hdn);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
      }
            
}
