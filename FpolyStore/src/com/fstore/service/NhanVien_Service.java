
package com.fstore.service;

import com.fstore.model.KhachHang;
import com.fstore.model.NhaCungCap;
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
public class NhanVien_Service implements Inf_Service<NhanVien,String>{
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;
   
    

    @Override
    public int insert(NhanVien model) {
        sql = """
              INSERT INTO NHANVIEN(MANV, TENNV, SDT, EMAIL, MATKHAU, DIACHI, GIOITINH, VAITRO, TRANGTHAI)
              VALUES(?,?,?,?,?,?,?,?,?)
              """;
        try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, model.getMaNV());
            ps.setObject(2, model.getTen());
            ps.setObject(3, model.getEmail());
            ps.setObject(4, model.getMatKhau());
            ps.setObject(5, model.getDiaChi());
            ps.setObject(6, model.isGioiTinh());
            ps.setObject(7, model.isVaiTro());
            ps.setObject(8, model.getTrangThai());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(NhanVien model, String id) {
        sql = """
              UPDATE NHANVIEN SET TENNV = ?, SDT = ?, EMAIL = ?, MATKHAU = ?, DIACHI = ?, GIOITINH = ?, VAITRO = ?, TRANGTHAI = ?
              WHERE MANV = ?
              """;
        try {
             conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, model.getTen());
            ps.setObject(2, model.getEmail());
            ps.setObject(3, model.getMatKhau());
            ps.setObject(4, model.getDiaChi());
            ps.setObject(5, model.isGioiTinh());
            ps.setObject(6, model.isVaiTro());
            ps.setObject(7, model.getTrangThai());
            ps.setObject(8, id);
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
            ps= conn.prepareStatement(sql);
            ps.setObject(1, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<NhanVien> selectAll() {
        sql ="""
             SELECT MANV, TENNV, SDT, EMAIL, MATKHAU, DIACHI, GIOITINH, VAITRO, TRANGTHAI
             FROM NHANVIEN
             """;
        List<NhanVien> list = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getString(1));
                nv.setTen(rs.getString(2));
                nv.setSDT(rs.getString(3));
                nv.setEmail(rs.getString(4));
                nv.setMatKhau(rs.getString(5));
                nv.setDiaChi(rs.getString(6));
                nv.setGioiTinh(rs.getBoolean(7));
                nv.setVaiTro(rs.getBoolean(8));
                nv.setTrangThai(rs.getInt(9));
                list.add(nv);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public NhanVien selectByID(String id) {
        sql ="""
             SELECT MANV, TENNV, SDT, EMAIL, MATKHAU, DIACHI, GIOITINH, VAITRO, TRANGTHAI
             FROM NHANVIEN
             WHERE MANV = ?
             """;
        NhanVien nv = null;
        try {
            conn = DBConnect.getConnection();
            ps= conn.prepareStatement(sql);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                nv = new NhanVien();
                nv.setMaNV(rs.getString(1));
                nv.setTen(rs.getString(2));
                nv.setSDT(rs.getString(3));
                nv.setEmail(rs.getString(4));
                nv.setMatKhau(rs.getString(5));
                nv.setDiaChi(rs.getString(6));
                nv.setGioiTinh(rs.getBoolean(7));
                nv.setVaiTro(rs.getBoolean(8));
                nv.setTrangThai(rs.getInt(9));
            }
            return nv;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    }

    

