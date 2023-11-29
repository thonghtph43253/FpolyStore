/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fstore.service;

import com.fsore.untils.XDate;
import com.fstore.model.KhachHang;
import com.fstore.model.NhaCungCap;
import com.fstore.repository.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.UIManager;

/**
 *
 * @author hnc0801
 */
public class KhachHang_Service implements Inf_Service<KhachHang, Integer> {

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;

    @Override
    public int insert(KhachHang model) {
        sql = """
              INSERT INTO KHACHHANG( TENKH , SDT , NGAYSINH , GIOITINH , DIACHI , TRANGTHAI )
              VALUES(?,?,?,?,?,?)
              """;
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, model.getTen());
            ps.setObject(2, model.getsDT());
            ps.setObject(3, model.getNgaySinh());
            ps.setObject(4, model.isGioiTinh());
            ps.setObject(5, model.getDiaChi());
            ps.setObject(6, model.getTrangThai());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int update(KhachHang model, Integer id) {
        sql = """
              UPDATE KHACHHANG SET  TENKH = ? , SDT = ? , NGAYSINH = ? , GIOITINH = ? , DIACHI = ? , TRANGTHAI = ?
              WHERE ID_KHACHHANG = ?
              """;
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, model.getTen());
            ps.setObject(2, model.getsDT());
            ps.setObject(3, model.getNgaySinh());
            ps.setObject(4, model.isGioiTinh());
            ps.setObject(5, model.getDiaChi());
            ps.setObject(6, model.getTrangThai());
            ps.setObject(7, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(Integer id) {
        sql = """
              DELETE FROM KHACHHANG 
              WHERE ID_KHACHHANG = ?
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
    public List<KhachHang> selectAll() {
        sql = """
             SELECT ID_KHACHHANG, TENKH , SDT , NGAYSINH , GIOITINH , DIACHI , TRANGTHAI 
             FROM   KHACHHANG
             """;
        List<KhachHang> list = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang md = new KhachHang();
                md.setId_KhachHang(rs.getInt(1));
                md.setTen(rs.getString(2));
                md.setsDT(rs.getString(3));
                md.setNgaySinh(XDate.toString(rs.getDate(4), "MM-dd-yyyy"));
                md.setGioiTinh(rs.getBoolean(5));
                md.setDiaChi(rs.getString(6));
                md.setTrangThai(rs.getInt(7));
                list.add(md);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public KhachHang selectByID(Integer id) {
        sql = """
             SELECT ID_KHACHHANG, TENKH , SDT , NGAYSINH , GIOITINH , DIACHI , TRANGTHAI 
             FROM KHACHHANG
             WHERE ID_KHACHHANG = ?
             """;
        KhachHang kh = null;
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                kh = new KhachHang();
                kh.setId_KhachHang(rs.getInt(1));
                kh.setTen(rs.getString(2));
                kh.setsDT(rs.getString(3));
                kh.setNgaySinh(rs.getString(4));
                kh.setGioiTinh(rs.getBoolean(5));
                kh.setDiaChi(rs.getString(6));
                kh.setTrangThai(rs.getInt(7));
            }
            return kh;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<KhachHang> selectByName(String ten) {
        sql = """
             SELECT ID_KHACHHANG, TENKH , SDT , NGAYSINH , GIOITINH , DIACHI , TRANGTHAI 
             FROM   KHACHHANG
             WHERE TENKH LIKE ?
             """;
        List<KhachHang> list = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, "%"+ten+"%");
            rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang md = new KhachHang();
                md.setId_KhachHang(rs.getInt(1));
                md.setTen(rs.getString(2));
                md.setsDT(rs.getString(3));
                md.setNgaySinh(XDate.toString(rs.getDate(4), "MM-dd-yyyy"));
                md.setGioiTinh(rs.getBoolean(5));
                md.setDiaChi(rs.getString(6));
                md.setTrangThai(rs.getInt(7));
                list.add(md);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<KhachHang> selectBySDT(String sdt) {
        sql = """
             SELECT ID_KHACHHANG, TENKH , SDT , NGAYSINH , GIOITINH , DIACHI , TRANGTHAI 
             FROM   KHACHHANG
             WHERE SDT LIKE ?
             """;
        List<KhachHang> list = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, "%"+sdt+"%");
            rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang md = new KhachHang();
                md.setId_KhachHang(rs.getInt(1));
                md.setTen(rs.getString(2));
                md.setsDT(rs.getString(3));
                md.setNgaySinh(XDate.toString(rs.getDate(4), "MM-dd-yyyy"));
                md.setGioiTinh(rs.getBoolean(5));
                md.setDiaChi(rs.getString(6));
                md.setTrangThai(rs.getInt(7));
                list.add(md);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<KhachHang> selectByTT(int tt) {
        sql = """
             SELECT ID_KHACHHANG, TENKH , SDT , NGAYSINH , GIOITINH , DIACHI , TRANGTHAI 
             FROM   KHACHHANG
             WHERE TRANGTHAI = ?
             """;
        List<KhachHang> list = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, tt);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang md = new KhachHang();
                md.setId_KhachHang(rs.getInt(1));
                md.setTen(rs.getString(2));
                md.setsDT(rs.getString(3));
                md.setNgaySinh(XDate.toString(rs.getDate(4), "MM-dd-yyyy"));
                md.setGioiTinh(rs.getBoolean(5));
                md.setDiaChi(rs.getString(6));
                md.setTrangThai(rs.getInt(7));
                list.add(md);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
