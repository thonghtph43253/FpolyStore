package com.fstore.service;

import com.fsore.untils.XDate;
import com.fstore.model.NhanVien;
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
public class NhanVien_Service implements Inf_Service<NhanVien, String> {

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    @Override
    public int insert(NhanVien model) {
        sql = """
              INSERT INTO NHANVIEN(MANV, TENNV, SDT, EMAIL,MATKHAU,DIACHI,GIOITINH,VAITRO,TRANGTHAI, NGAYSINH,HINHANH )
              VALUES(?,?,?,?,?,?,?,?,?,?,?)
              """;
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, model.getMaNV());
            ps.setObject(2, model.getTen());
            ps.setObject(3, model.getSDT());
            ps.setObject(4, model.getEmail());
            ps.setObject(5, model.getMatKhau());
            ps.setObject(6, model.getDiaChi());
            ps.setObject(7, model.isGioiTinh());
            ps.setObject(8, model.isVaiTro());
            ps.setObject(9, model.getTrangThai());
            ps.setObject(10, XDate.toDate(model.getNgaySinh(), "MM-dd-yyyy"));
            ps.setObject(11, model.getHinhAnh());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(NhanVien model, String id) {
        sql = """
              UPDATE  NHANVIEN SET TENNV = ?, SDT = ?, EMAIL = ?,MATKHAU= ?,
               DIACHI = ?,GIOITINH = ?,VAITRO = ?,TRANGTHAI= ?, NGAYSINH= ?,
               HINHANH = ? 
              WHERE MANV = ?
              """;
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, model.getTen());
            ps.setObject(2, model.getSDT());
            ps.setObject(3, model.getEmail());
            ps.setObject(4, model.getMatKhau());
            ps.setObject(5, model.getDiaChi());
            ps.setObject(6, model.isGioiTinh());
            ps.setObject(7, model.isVaiTro());
            ps.setObject(8, model.getTrangThai());
            ps.setObject(9, XDate.toDate(model.getNgaySinh(), "dd-MM-yyyy"));
            ps.setObject(10, model.getHinhAnh());
            ps.setObject(11, model.getMaNV());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(String id) {
        sql = """
              DELETE FROM NHANVIEN 
              WHERE MANV = ?
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
    public List<NhanVien> selectAll() {
        sql = """
             SELECT  MANV, TENNV, SDT, EMAIL, MATKHAU, DIACHI, GIOITINH, VAITRO, TRANGTHAI, NGAYSINH, HINHANH
             FROM   NHANVIEN
             """;
        List<NhanVien> list = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien md = new NhanVien();
                md.setMaNV(rs.getString(1));
                md.setTen(rs.getString(2));
                md.setSDT(rs.getString(3));
                md.setEmail(rs.getString(4));
                md.setMatKhau(rs.getString(5));
                md.setDiaChi(rs.getString(6));
                md.setGioiTinh(rs.getBoolean(7));
                md.setVaiTro(rs.getBoolean(8));
                md.setTrangThai(rs.getInt(9));
                md.setNgaySinh(XDate.toString(rs.getDate(10), "dd-MM-yyyy"));
                md.setHinhAnh(rs.getString(11));
                list.add(md);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public NhanVien selectByID(String id) {
        sql = """
             SELECT  MANV, TENNV, SDT, EMAIL, MATKHAU, DIACHI, GIOITINH, VAITRO, TRANGTHAI, NGAYSINH, HINHANH
             FROM   NHANVIEN
             WHERE MANV = ?
             """;
        NhanVien md = null;
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                md = new NhanVien();
                md.setMaNV(rs.getString(1));
                md.setTen(rs.getString(2));
                md.setSDT(rs.getString(3));
                md.setEmail(rs.getString(4));
                md.setMatKhau(rs.getString(5));
                md.setDiaChi(rs.getString(6));
                md.setGioiTinh(rs.getBoolean(7));
                md.setVaiTro(rs.getBoolean(8));
                md.setTrangThai(rs.getInt(9));
                md.setNgaySinh(XDate.toString(rs.getDate(10), "MM-dd-yyyy"));
                md.setHinhAnh(rs.getString(11));
            }
            return md;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<NhanVien> selectByName(String ten) {
        sql = """
             SELECT  MANV, TENNV, SDT, EMAIL, MATKHAU, DIACHI, GIOITINH, VAITRO, TRANGTHAI, NGAYSINH, HINHANH
             FROM   NHANVIEN
             WHERE TENNV LIKE ?
             """;
        List<NhanVien> list = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, "%"+ten+"%");
            rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien md = new NhanVien();
                md.setMaNV(rs.getString(1));
                md.setTen(rs.getString(2));
                md.setSDT(rs.getString(3));
                md.setEmail(rs.getString(4));
                md.setMatKhau(rs.getString(5));
                md.setDiaChi(rs.getString(6));
                md.setGioiTinh(rs.getBoolean(7));
                md.setVaiTro(rs.getBoolean(8));
                md.setTrangThai(rs.getInt(9));
                md.setNgaySinh(XDate.toString(rs.getDate(10), "dd-MM-yyyy"));
                md.setHinhAnh(rs.getString(11));
                list.add(md);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<NhanVien> selectBySDT(String sdt) {
        sql = """
             SELECT  MANV, TENNV, SDT, EMAIL, MATKHAU, DIACHI, GIOITINH, VAITRO, TRANGTHAI, NGAYSINH, HINHANH
             FROM   NHANVIEN
             WHERE SDT LIKE ?
             """;
        List<NhanVien> list = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, "%"+sdt+"%");
            rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien md = new NhanVien();
                md.setMaNV(rs.getString(1));
                md.setTen(rs.getString(2));
                md.setSDT(rs.getString(3));
                md.setEmail(rs.getString(4));
                md.setMatKhau(rs.getString(5));
                md.setDiaChi(rs.getString(6));
                md.setGioiTinh(rs.getBoolean(7));
                md.setVaiTro(rs.getBoolean(8));
                md.setTrangThai(rs.getInt(9));
                md.setNgaySinh(XDate.toString(rs.getDate(10), "dd-MM-yyyy"));
                md.setHinhAnh(rs.getString(11));
                list.add(md);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<NhanVien> selectByTT(int tt) {
        sql = """
             SELECT  MANV, TENNV, SDT, EMAIL, MATKHAU, DIACHI, GIOITINH, VAITRO, TRANGTHAI, NGAYSINH, HINHANH
             FROM   NHANVIEN
             WHERE TRANGTHAI = ?
             """;
        List<NhanVien> list = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1,tt);
            rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien md = new NhanVien();
                md.setMaNV(rs.getString(1));
                md.setTen(rs.getString(2));
                md.setSDT(rs.getString(3));
                md.setEmail(rs.getString(4));
                md.setMatKhau(rs.getString(5));
                md.setDiaChi(rs.getString(6));
                md.setGioiTinh(rs.getBoolean(7));
                md.setVaiTro(rs.getBoolean(8));
                md.setTrangThai(rs.getInt(9));
                md.setNgaySinh(XDate.toString(rs.getDate(10), "dd-MM-yyyy"));
                md.setHinhAnh(rs.getString(11));
                list.add(md);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
