package com.fstore.service;

import com.fstore.model.HoaDon;
import com.fstore.repository.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ThongHT
 */
public class HoaDon_Service implements Inf_Service<HoaDon,Integer>{
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;
    @Override
    public int insert(HoaDon model) {
      sql = """
                INSERT INTO HOADON(TENKHACHHANG, NGAYTAO, SDT, TONGTIEN,
                                   MAGIAMGIA,HINHTHUCTHANHTOAN, TRANGTHAI,
                                   ID_KHACHHANG, MANV)
                VALUES(?,?,?,?,?,?,?,?,?)
            """;
        try {
             conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, model.getTenKH());
            ps.setObject(2, model.getNgayTao());
            ps.setObject(3, model.getSdt());
            ps.setObject(4, model.getTongTien());
            ps.setObject(5, model.getVoucher());
            ps.setObject(6, model.getHinhThucThanhToan());
            ps.setObject(7, model.getTrangThai());
            ps.setObject(8, model.getId_KhachHang());
            ps.setObject(9, model.getId_NhanVien());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(HoaDon model, Integer id) {
        sql = """
                UPDATE HOADON SET TENKHACHHANG = ?, NGAYTAO = ?, SDT = ?,
                                  TONGTIEN = ?,MAGIAMGIA = ?,HINHTHUCTHANHTOAN = ?,
                                  TRANGTHAI = ?,ID_KHACHHANG = ?, MANV = ?
                              WHERE ID_HOADON = ?
            """;
        try {
             conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, model.getTenKH());
            ps.setObject(2, model.getNgayTao());
            ps.setObject(3, model.getSdt());
            ps.setObject(4, model.getTongTien());
            ps.setObject(5, model.getVoucher());
            ps.setObject(6, model.getHinhThucThanhToan());
            ps.setObject(7, model.getTrangThai());
            ps.setObject(8, model.getId_KhachHang());
            ps.setObject(9, model.getId_NhanVien());
            ps.setObject(10, id);
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
    public List<HoaDon> selectAll() {
        sql = """
               SELECT ID_HOADON, TENKHACHHANG, NGAYTAO, HD.SDT, TONGTIEN,
                      MAGIAMGIA,HINHTHUCTHANHTOAN, HD.TRANGTHAI,
                      KH.ID_KHACHHANG, NV.MANV
               FROM HOADON HD 
               JOIN NHANVIEN NV ON HD.MANV = NV.MANV
               JOIN KHACHHANG KH ON HD.ID_KHACHHANG = KH.ID_KHACHHANG
              """;
            List<HoaDon> list = new ArrayList<>();
            try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                HoaDon hd = new HoaDon();
                hd.setId_HoaDon(rs.getInt(1));
                hd.setTenKH(rs.getString(2));
                hd.setNgayTao(rs.getString(3));
                hd.setSdt(rs.getString(4));
                hd.setTongTien(rs.getDouble(5));
                hd.setVoucher(rs.getInt(6));
                hd.setHinhThucThanhToan(rs.getInt(7));
                hd.setTrangThai(rs.getInt(8));
                hd.setId_KhachHang(rs.getInt(9));
                hd.setId_NhanVien(rs.getString(10));
                list.add(hd);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public HoaDon selectByID(Integer id) {
         sql = """
               SELECT ID_HOADON, TENKHACHHANG, NGAYTAO, HD.SDT, TONGTIEN,
                      MAGIAMGIA,HINHTHUCTHANHTOAN, HD.TRANGTHAI,
                      KH.ID_KHACHHANG, NV.MANV
               FROM HOADON HD 
               JOIN NHANVIEN NV ON HD.MANV = NV.MANV
               JOIN KHACHHANG KH ON HD.ID_KHACHHANG = KH.ID_KHACHHANG
               WHERE ID_HOADON = ?
              """;
            HoaDon hd = null;
            try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                hd = new HoaDon();
                hd.setId_HoaDon(rs.getInt(1));
                hd.setTenKH(rs.getString(2));
                hd.setNgayTao(rs.getString(3));
                hd.setSdt(rs.getString(4));
                hd.setTongTien(rs.getDouble(5));
                hd.setVoucher(rs.getInt(6));
                hd.setHinhThucThanhToan(rs.getInt(7));
                hd.setTrangThai(rs.getInt(8));
                hd.setId_KhachHang(rs.getInt(9));
                hd.setId_NhanVien(rs.getString(10));
                
            }
            return hd;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public HoaDon selectByIDIsNV(Integer id, String maNv) {
         sql = """
               SELECT ID_HOADON, TENKHACHHANG, NGAYTAO, HD.SDT, TONGTIEN,
                      MAGIAMGIA,HINHTHUCTHANHTOAN, HD.TRANGTHAI,
                      KH.ID_KHACHHANG, NV.MANV
               FROM HOADON HD 
               JOIN NHANVIEN NV ON HD.MANV = NV.MANV
               JOIN KHACHHANG KH ON HD.ID_KHACHHANG = KH.ID_KHACHHANG
               WHERE ID_HOADON = ? AND HD.MANV = ?
              """;
            HoaDon hd = null;
            try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, id);
            ps.setObject(2, maNv);
            rs = ps.executeQuery();
            while(rs.next()){
                hd = new HoaDon();
                hd.setId_HoaDon(rs.getInt(1));
                hd.setTenKH(rs.getString(2));
                hd.setNgayTao(rs.getString(3));
                hd.setSdt(rs.getString(4));
                hd.setTongTien(rs.getDouble(5));
                hd.setVoucher(rs.getInt(6));
                hd.setHinhThucThanhToan(rs.getInt(7));
                hd.setTrangThai(rs.getInt(8));
                hd.setId_KhachHang(rs.getInt(9));
                hd.setId_NhanVien(rs.getString(10));
                
            }
            return hd;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<HoaDon> selectByTrangThai( int tt) {
        sql = """
               SELECT ID_HOADON, TENKHACHHANG, NGAYTAO, HD.SDT, TONGTIEN,
                      MAGIAMGIA,HINHTHUCTHANHTOAN, HD.TRANGTHAI,
                      KH.ID_KHACHHANG, NV.MANV
               FROM HOADON HD 
               JOIN NHANVIEN NV ON HD.MANV = NV.MANV
               JOIN KHACHHANG KH ON HD.ID_KHACHHANG = KH.ID_KHACHHANG
               WHERE HD.TRANGTHAI = ?
              """;
            List<HoaDon> list = new ArrayList<>();
            try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, tt);
            rs = ps.executeQuery();
            while(rs.next()){
                HoaDon hd = new HoaDon();
                hd.setId_HoaDon(rs.getInt(1));
                hd.setTenKH(rs.getString(2));
                hd.setNgayTao(rs.getString(3));
                hd.setSdt(rs.getString(4));
                hd.setTongTien(rs.getDouble(5));
                hd.setVoucher(rs.getInt(6));
                hd.setHinhThucThanhToan(rs.getInt(7));
                hd.setTrangThai(rs.getInt(8));
                hd.setId_KhachHang(rs.getInt(9));
                hd.setId_NhanVien(rs.getString(10));
                list.add(hd);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
     public List<HoaDon> selectByID_KH( int id_KH) {
        sql = """
               SELECT ID_HOADON, TENKHACHHANG, NGAYTAO, HD.SDT, TONGTIEN,
                      MAGIAMGIA,HINHTHUCTHANHTOAN, HD.TRANGTHAI,
                      KH.ID_KHACHHANG, NV.MANV
               FROM HOADON HD 
               JOIN NHANVIEN NV ON HD.MANV = NV.MANV
               JOIN KHACHHANG KH ON HD.ID_KHACHHANG = KH.ID_KHACHHANG
               WHERE HD.ID_KHACHHANG = ?
              """;
            List<HoaDon> list = new ArrayList<>();
            try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, id_KH);
            rs = ps.executeQuery();
            while(rs.next()){
                HoaDon hd = new HoaDon();
                hd.setId_HoaDon(rs.getInt(1));
                hd.setTenKH(rs.getString(2));
                hd.setNgayTao(rs.getString(3));
                hd.setSdt(rs.getString(4));
                hd.setTongTien(rs.getDouble(5));
                hd.setVoucher(rs.getInt(6));
                hd.setHinhThucThanhToan(rs.getInt(7));
                hd.setTrangThai(rs.getInt(8));
                hd.setId_KhachHang(rs.getInt(9));
                hd.setId_NhanVien(rs.getString(10));
                list.add(hd);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
     public List<HoaDon> selectByID_NV( String maNv) {
        sql = """
               SELECT ID_HOADON, TENKHACHHANG, NGAYTAO, HD.SDT, TONGTIEN,
                      MAGIAMGIA,HINHTHUCTHANHTOAN, HD.TRANGTHAI,
                      KH.ID_KHACHHANG, NV.MANV
               FROM HOADON HD 
               JOIN NHANVIEN NV ON HD.MANV = NV.MANV
               JOIN KHACHHANG KH ON HD.ID_KHACHHANG = KH.ID_KHACHHANG
               WHERE HD.MANV = ?
              """;
            List<HoaDon> list = new ArrayList<>();
            try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, maNv);
            rs = ps.executeQuery();
            while(rs.next()){
                HoaDon hd = new HoaDon();
                hd.setId_HoaDon(rs.getInt(1));
                hd.setTenKH(rs.getString(2));
                hd.setNgayTao(rs.getString(3));
                hd.setSdt(rs.getString(4));
                hd.setTongTien(rs.getDouble(5));
                hd.setVoucher(rs.getInt(6));
                hd.setHinhThucThanhToan(rs.getInt(7));
                hd.setTrangThai(rs.getInt(8));
                hd.setId_KhachHang(rs.getInt(9));
                hd.setId_NhanVien(rs.getString(10));
                list.add(hd);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<HoaDon> selectByName(String ten) {
        sql = """
               SELECT ID_HOADON, TENKHACHHANG, NGAYTAO, HD.SDT, TONGTIEN,
                      MAGIAMGIA,HINHTHUCTHANHTOAN, HD.TRANGTHAI,
                      KH.ID_KHACHHANG, NV.MANV
               FROM HOADON HD 
               JOIN NHANVIEN NV ON HD.MANV = NV.MANV
               JOIN KHACHHANG KH ON HD.ID_KHACHHANG = KH.ID_KHACHHANG
               WHERE TENKHACHHANG LIKE ? 
              """;
            List<HoaDon> list = new ArrayList<>();
            try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1,"%"+ten+"%");
            rs = ps.executeQuery();
            while(rs.next()){
                 HoaDon hd = new HoaDon();
                hd.setId_HoaDon(rs.getInt(1));
                hd.setTenKH(rs.getString(2));
                hd.setNgayTao(rs.getString(3));
                hd.setSdt(rs.getString(4));
                hd.setTongTien(rs.getDouble(5));
                hd.setVoucher(rs.getInt(6));
                hd.setHinhThucThanhToan(rs.getInt(7));
                hd.setTrangThai(rs.getInt(8));
                hd.setId_KhachHang(rs.getInt(9));
                hd.setId_NhanVien(rs.getString(10));
                list.add(hd);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<HoaDon> selectByNameIsNv(String ten, String maNv) {
        sql = """
               SELECT ID_HOADON, TENKHACHHANG, NGAYTAO, HD.SDT, TONGTIEN,
                      MAGIAMGIA,HINHTHUCTHANHTOAN, HD.TRANGTHAI,
                      KH.ID_KHACHHANG, NV.MANV
               FROM HOADON HD 
               JOIN NHANVIEN NV ON HD.MANV = NV.MANV
               JOIN KHACHHANG KH ON HD.ID_KHACHHANG = KH.ID_KHACHHANG
               WHERE TENKHACHHANG LIKE ? AND HD.MANV = ?
              """;
            List<HoaDon> list = new ArrayList<>();
            try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1,"%"+ten+"%");
            ps.setObject(2, maNv);
            rs = ps.executeQuery();
            while(rs.next()){
                 HoaDon hd = new HoaDon();
                hd.setId_HoaDon(rs.getInt(1));
                hd.setTenKH(rs.getString(2));
                hd.setNgayTao(rs.getString(3));
                hd.setSdt(rs.getString(4));
                hd.setTongTien(rs.getDouble(5));
                hd.setVoucher(rs.getInt(6));
                hd.setHinhThucThanhToan(rs.getInt(7));
                hd.setTrangThai(rs.getInt(8));
                hd.setId_KhachHang(rs.getInt(9));
                hd.setId_NhanVien(rs.getString(10));
                list.add(hd);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<HoaDon> selectBySDT(String SDT) {
        sql = """
               SELECT ID_HOADON, TENKHACHHANG, NGAYTAO, HD.SDT, TONGTIEN,
                      MAGIAMGIA,HINHTHUCTHANHTOAN, HD.TRANGTHAI,
                      KH.ID_KHACHHANG, NV.MANV
               FROM HOADON HD 
               JOIN NHANVIEN NV ON HD.MANV = NV.MANV
               JOIN KHACHHANG KH ON HD.ID_KHACHHANG = KH.ID_KHACHHANG
               WHERE HD.SDT LIKE ?
              """;
            List<HoaDon> list = new ArrayList<>();
            try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1,"%"+SDT+"%");
            rs = ps.executeQuery();
            while(rs.next()){
                 HoaDon hd = new HoaDon();
                hd.setId_HoaDon(rs.getInt(1));
                hd.setTenKH(rs.getString(2));
                hd.setNgayTao(rs.getString(3));
                hd.setSdt(rs.getString(4));
                hd.setTongTien(rs.getDouble(5));
                hd.setVoucher(rs.getInt(6));
                hd.setHinhThucThanhToan(rs.getInt(7));
                hd.setTrangThai(rs.getInt(8));
                hd.setId_KhachHang(rs.getInt(9));
                hd.setId_NhanVien(rs.getString(10));
                list.add(hd);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<HoaDon> selectBySDTIsNv(String SDT, String maNv) {
        sql = """
               SELECT ID_HOADON, TENKHACHHANG, NGAYTAO, HD.SDT, TONGTIEN,
                      MAGIAMGIA,HINHTHUCTHANHTOAN, HD.TRANGTHAI,
                      KH.ID_KHACHHANG, NV.MANV
               FROM HOADON HD 
               JOIN NHANVIEN NV ON HD.MANV = NV.MANV
               JOIN KHACHHANG KH ON HD.ID_KHACHHANG = KH.ID_KHACHHANG
               WHERE HD.SDT LIKE ? AND HD.MANV = ?
              """;
            List<HoaDon> list = new ArrayList<>();
            try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1,"%"+SDT+"%");
            ps.setObject(2, maNv);
            rs = ps.executeQuery();
            while(rs.next()){
                 HoaDon hd = new HoaDon();
                hd.setId_HoaDon(rs.getInt(1));
                hd.setTenKH(rs.getString(2));
                hd.setNgayTao(rs.getString(3));
                hd.setSdt(rs.getString(4));
                hd.setTongTien(rs.getDouble(5));
                hd.setVoucher(rs.getInt(6));
                hd.setHinhThucThanhToan(rs.getInt(7));
                hd.setTrangThai(rs.getInt(8));
                hd.setId_KhachHang(rs.getInt(9));
                hd.setId_NhanVien(rs.getString(10));
                list.add(hd);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<HoaDon> selectByDay(Date ngayBD, Date ngayKT ,int tt) {
        sql = """
               SELECT ID_HOADON, TENKHACHHANG, NGAYTAO, HD.SDT, TONGTIEN,
                      MAGIAMGIA,HINHTHUCTHANHTOAN, HD.TRANGTHAI,
                      KH.ID_KHACHHANG, NV.MANV
               FROM HOADON HD 
               JOIN NHANVIEN NV ON HD.MANV = NV.MANV
               JOIN KHACHHANG KH ON HD.ID_KHACHHANG = KH.ID_KHACHHANG
               WHERE NGAYTAO >= ? AND NGAYTAO <= DATEADD(DAY, 1, ?) AND HD.TRANGTHAI = ?
              """;
            List<HoaDon> list = new ArrayList<>();
            try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1,ngayBD);
            ps.setObject(2,ngayKT);
            ps.setObject(3,tt);
            rs = ps.executeQuery();
            while(rs.next()){
                 HoaDon hd = new HoaDon();
                hd.setId_HoaDon(rs.getInt(1));
                hd.setTenKH(rs.getString(2));
                hd.setNgayTao(rs.getString(3));
                hd.setSdt(rs.getString(4));
                hd.setTongTien(rs.getDouble(5));
                hd.setVoucher(rs.getInt(6));
                hd.setHinhThucThanhToan(rs.getInt(7));
                hd.setTrangThai(rs.getInt(8));
                hd.setId_KhachHang(rs.getInt(9));
                hd.setId_NhanVien(rs.getString(10));
                list.add(hd);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<HoaDon> selectByDayIsNv(Date ngayBD, Date ngayKT ,int tt, String maNv) {
        sql = """
               SELECT ID_HOADON, TENKHACHHANG, NGAYTAO, HD.SDT, TONGTIEN,
                      MAGIAMGIA,HINHTHUCTHANHTOAN, HD.TRANGTHAI,
                      KH.ID_KHACHHANG, NV.MANV
               FROM HOADON HD 
               JOIN NHANVIEN NV ON HD.MANV = NV.MANV
               JOIN KHACHHANG KH ON HD.ID_KHACHHANG = KH.ID_KHACHHANG
               WHERE NGAYTAO >= ? AND NGAYTAO <= DATEADD(DAY, 1, ?) AND HD.TRANGTHAI = ? AND HD.MANV = ?
              """;
            List<HoaDon> list = new ArrayList<>();
            try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1,ngayBD);
            ps.setObject(2,ngayKT);
            ps.setObject(3,tt);
            ps.setObject(4, maNv);
            rs = ps.executeQuery();
            while(rs.next()){
                 HoaDon hd = new HoaDon();
                hd.setId_HoaDon(rs.getInt(1));
                hd.setTenKH(rs.getString(2));
                hd.setNgayTao(rs.getString(3));
                hd.setSdt(rs.getString(4));
                hd.setTongTien(rs.getDouble(5));
                hd.setVoucher(rs.getInt(6));
                hd.setHinhThucThanhToan(rs.getInt(7));
                hd.setTrangThai(rs.getInt(8));
                hd.setId_KhachHang(rs.getInt(9));
                hd.setId_NhanVien(rs.getString(10));
                list.add(hd);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
