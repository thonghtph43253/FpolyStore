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
public class SanPhamChiTiet_Service implements Inf_Service<SanPhamChiTiet, Integer> {

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    @Override
    public int insert(SanPhamChiTiet model) {
        sql = """
             INSERT INTO SANPHAMCHITIET(SOLUONG, GIA, TRANGTHAI,ID_SANPHAM, ID_CHATLIEU, ID_SIZE, ID_MAU,HINHANH)
             VALUES(?,?,?,?,?,?,?,?)
             """;
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, model.getSoLuong());
            ps.setObject(2, model.getGia());
            ps.setObject(3, model.getTrangThai());
            ps.setObject(4, model.getId_SanPham());
            ps.setObject(5, model.getId_ChatLieu());
            ps.setObject(6, model.getId_Size());
            ps.setObject(7, model.getId_Mau());
            ps.setObject(8, model.getHinhAnh());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(SanPhamChiTiet model, Integer id) {
        sql = """
             UPDATE SANPHAMCHITIET SET SOLUONG = ?, GIA = ? , TRANGTHAI = ?,
             ID_SANPHAM= ?, ID_CHATLIEU = ?, ID_SIZE = ?, ID_MAU = ?, HINHANH = ?
             WHERE ID_SANPHAMCHITIET = ?
             """;
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, model.getSoLuong());
            ps.setObject(2, model.getGia());
            ps.setObject(3, model.getTrangThai());
            ps.setObject(4, model.getId_SanPham());
            ps.setObject(5, model.getId_ChatLieu());
            ps.setObject(6, model.getId_Size());
            ps.setObject(7, model.getId_Mau());
            ps.setObject(8, model.getHinhAnh());
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
             DELETE FROM SANPHAMCHITIET
             WHERE ID_SANPHAMCHITIET = ?
             """;
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<SanPhamChiTiet> selectAll() {
        sql = """
                 SELECT ID_SANPHAMCHITIET, SOLUONG,GIA,SPCT.TRANGTHAI,ID_CHATLIEU,ID_SIZE,ID_MAU, SPCT.ID_SANPHAM, HINHANH
                 FROM SANPHAMCHITIET AS SPCT JOIN SANPHAM AS SP ON SPCT.ID_SANPHAM = SP.ID_SANPHAM   
                 """;
        List<SanPhamChiTiet> list = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setId_SanPhamChiTiet(rs.getInt(1));
                spct.setSoLuong(rs.getInt(2));
                spct.setGia(rs.getDouble(3));
                spct.setTrangThai(rs.getInt(4));
                spct.setId_ChatLieu(rs.getInt(5));
                spct.setId_Size(rs.getInt(6));
                spct.setId_Mau(rs.getInt(7));
                spct.setId_SanPham(rs.getInt(8));
                spct.setHinhAnh(rs.getString(9));
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
        sql = """
                 SELECT ID_SANPHAMCHITIET, SOLUONG,GIA,SPCT.TRANGTHAI,ID_CHATLIEU,ID_SIZE,ID_MAU, SPCT.ID_SANPHAM, HINHANH
                 FROM SANPHAMCHITIET AS SPCT JOIN SANPHAM AS SP ON SPCT.ID_SANPHAM = SP.ID_SANPHAM 
                 WHERE SPCT.ID_SANPHAMCHITIET = ?
                 """;
        SanPhamChiTiet spct = null;
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                spct = new SanPhamChiTiet();
                spct.setId_SanPhamChiTiet(rs.getInt(1));
                spct.setSoLuong(rs.getInt(2));
                spct.setGia(rs.getDouble(3));
                spct.setTrangThai(rs.getInt(4));
                spct.setId_ChatLieu(rs.getInt(5));
                spct.setId_Size(rs.getInt(6));
                spct.setId_Mau(rs.getInt(7));
                spct.setId_SanPham(rs.getInt(8));
                spct.setHinhAnh(rs.getString(9));
            }
            return spct;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<SanPhamChiTiet> selectByID_SP(Integer id) {
        sql = """
                 SELECT ID_SANPHAMCHITIET, SOLUONG,GIA,SPCT.TRANGTHAI,ID_CHATLIEU,ID_SIZE,ID_MAU, SPCT.ID_SANPHAM, HINHANH
                 FROM SANPHAMCHITIET AS SPCT JOIN SANPHAM AS SP ON SPCT.ID_SANPHAM = SP.ID_SANPHAM 
                 WHERE SPCT.ID_SANPHAM = ?
                 """;
        List<SanPhamChiTiet> list = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setId_SanPhamChiTiet(rs.getInt(1));
                spct.setSoLuong(rs.getInt(2));
                spct.setGia(rs.getDouble(3));
                spct.setTrangThai(rs.getInt(4));
                spct.setId_ChatLieu(rs.getInt(5));
                spct.setId_Size(rs.getInt(6));
                spct.setId_Mau(rs.getInt(7));
                spct.setId_SanPham(rs.getInt(8));
                spct.setHinhAnh(rs.getString(9));
                list.add(spct);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<SanPhamChiTiet> selectByName(String ten) {
        sql = """
                 SELECT ID_SANPHAMCHITIET, SOLUONG,GIA,SPCT.TRANGTHAI,ID_CHATLIEU,ID_SIZE,ID_MAU, SPCT.ID_SANPHAM, HINHANH
                 FROM SANPHAMCHITIET AS SPCT JOIN SANPHAM AS SP ON SPCT.ID_SANPHAM = SP.ID_SANPHAM 
                 WHERE SP.TENSANPHAM like ?
                 """;
        List<SanPhamChiTiet> list = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, "%"+ten+"%");
            rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setId_SanPhamChiTiet(rs.getInt(1));
                spct.setSoLuong(rs.getInt(2));
                spct.setGia(rs.getDouble(3));
                spct.setTrangThai(rs.getInt(4));
                spct.setId_ChatLieu(rs.getInt(5));
                spct.setId_Size(rs.getInt(6));
                spct.setId_Mau(rs.getInt(7));
                spct.setId_SanPham(rs.getInt(8));
                spct.setHinhAnh(rs.getString(9));
                list.add(spct);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<SanPhamChiTiet> selectByDanhMuc(int id_dm) {
        sql = """
                 SELECT ID_SANPHAMCHITIET, SOLUONG,GIA,SPCT.TRANGTHAI,ID_CHATLIEU,ID_SIZE,ID_MAU, SPCT.ID_SANPHAM, HINHANH
                 FROM SANPHAMCHITIET AS SPCT JOIN SANPHAM AS SP ON SPCT.ID_SANPHAM = SP.ID_SANPHAM 
                 WHERE SP.ID_DANHMUC  = ?
                 """;
        List<SanPhamChiTiet> list = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, id_dm);
            rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setId_SanPhamChiTiet(rs.getInt(1));
                spct.setSoLuong(rs.getInt(2));
                spct.setGia(rs.getDouble(3));
                spct.setTrangThai(rs.getInt(4));
                spct.setId_ChatLieu(rs.getInt(5));
                spct.setId_Size(rs.getInt(6));
                spct.setId_Mau(rs.getInt(7));
                spct.setId_SanPham(rs.getInt(8));
                spct.setHinhAnh(rs.getString(9));
                list.add(spct);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<SanPhamChiTiet> selectByFilter(String dau, int soLuong, int tt, String sapXepTen, String sapXepGia) {
        sql = "	SELECT ID_SANPHAMCHITIET, SOLUONG, GIA, SPCT.TRANGTHAI, ID_CHATLIEU,\n"
                + "            ID_SIZE, ID_MAU, SPCT.ID_SANPHAM\n"
                + "            FROM SANPHAMCHITIET SPCT JOIN SANPHAM SP ON SPCT.ID_SANPHAM = SP.ID_SANPHAM\n"
                + "            WHERE SOLUONG" + dau + " ? AND SPCT.TRANGTHAI = ?\n"
                + "            ORDER BY SP.TENSANPHAM " + sapXepTen + ", GIA " + sapXepGia;
        List<SanPhamChiTiet> list = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, soLuong);
            ps.setObject(2, tt);

            rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setId_SanPhamChiTiet(rs.getInt(1));
                spct.setSoLuong(rs.getInt(2));
                spct.setGia(rs.getDouble(3));
                spct.setTrangThai(rs.getInt(4));
                spct.setId_ChatLieu(rs.getInt(5));
                spct.setId_Size(rs.getInt(6));
                spct.setId_Mau(rs.getInt(7));
                spct.setId_SanPham(rs.getInt(8));

                list.add(spct);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getSoLuongSanPhamHDC(int tt, int spct) {
        sql = """
                    SELECT HDCT.SOLUONG FROM HOADON HD JOIN HOADON_CT HDCT ON HD.ID_HOADON = HDCT.ID_HOADON
                    JOIN SANPHAMCHITIET SPCT ON HDCT.ID_SANPHAMCHITIET = SPCT.ID_SANPHAMCHITIET
                    WHERE HD.TRANGTHAI = ? AND SPCT.ID_SANPHAMCHITIET = ?
                 """;
        int soLuong = 0;
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, tt);
            ps.setObject(2, spct);

            rs = ps.executeQuery();
            while (rs.next()) {
                soLuong = rs.getInt(1);
            }
            return soLuong;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int updateSoLuong(int soLuong, Integer id) {
        sql = """
             UPDATE SANPHAMCHITIET SET SOLUONG = SOLUONG - ?
             WHERE ID_SANPHAMCHITIET = ?
             """;
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, soLuong);
            ps.setObject(2, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int updateSoLuongSauKhiNhap(int soLuong, Integer id) {
        sql = """
             UPDATE SANPHAMCHITIET SET SOLUONG = SOLUONG +?
             WHERE ID_SANPHAMCHITIET = ?
             """;
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, soLuong);
            ps.setObject(2, id);

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
