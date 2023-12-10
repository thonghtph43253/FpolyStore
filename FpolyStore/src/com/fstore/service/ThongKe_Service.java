package com.fstore.service;

import com.fstore.repository.DBConnect;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ThongKe_Service {

    private Connection conn = null;
    private CallableStatement call = null;
    private PreparedStatement ps = null;
    private String sql = null;
    private ResultSet rs = null;

    public List<Object[]> getDoanhSo(int nam, int thang) {
        sql = """
              {call SP_THONGKEDOANHSO(?,?)}
              """;
        List<Object[]> list = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            call = conn.prepareCall(sql);
            call.setObject(1, nam);
            call.setObject(2, thang);
            rs = call.executeQuery();
            while (rs.next()) {
                int column = rs.getMetaData().getColumnCount();
                Object[] row = new Object[column];
                for (int i = 1; i <= column; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                list.add(row);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Integer> getYear() {
        sql = """
                    SELECT DISTINCT YEAR(NGAYTAO) FROM HOADON
                    ORDER BY YEAR(NGAYTAO) DESC
                 """;
        List<Integer> list = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Integer> getMonth(int year) {
        sql = """
                    SELECT MONTH(NGAYTAO) FROM HOADON
                    WHERE YEAR(NGAYTAO) = ?
                    GROUP BY  MONTH(NGAYTAO)
                 """;
        List<Integer> list = new ArrayList<>();
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, year);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getSoLuongInDay() {
        sql = """
             SELECT SUM(SOLUONG) FROM HOADON_CT HDCT JOIN HOADON HD ON HDCT.ID_HOADON = HD.ID_HOADON
             WHERE  YEAR(HD.NGAYTAO) = YEAR(GETDATE()) AND MONTH(HD.NGAYTAO) = MONTH(GETDATE()) 
                    AND DAY(HD.NGAYTAO) = DAY(GETDATE()) AND HD.TRANGTHAI = 1
             """;
        int soLuong = 0;
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
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

    public double getDoanhThuInDay() {
        sql = """
             SELECT  SUM(TONGTIEN) FROM  HOADON HD 
                          WHERE  YEAR(HD.NGAYTAO) = YEAR(GETDATE()) AND MONTH(HD.NGAYTAO) = MONTH(GETDATE()) 
                                AND DAY(HD.NGAYTAO) = DAY(GETDATE()) AND HD.TRANGTHAI = 1
             """;
        double doanhThu = 0;
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                doanhThu = rs.getInt(1);
            }
            return doanhThu;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int getKhachHangInDay() {
        sql = """
            SELECT  COUNT(DISTINCT HD.ID_KHACHHANG) AS SoLuongKhachHang  
            FROM HOADON_CT HDCT JOIN HOADON HD ON HDCT.ID_HOADON = HD.ID_HOADON
            WHERE YEAR(HD.NGAYTAO) = YEAR(GETDATE()) AND MONTH(HD.NGAYTAO) = MONTH(GETDATE()) 
                               AND DAY(HD.NGAYTAO) = DAY(GETDATE()) AND HD.TRANGTHAI = 1
             """;
        int soLuong = 0;
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
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

    public int getDonHangInDay() {
        sql = """
            SELECT  COUNT(DISTINCT HD.ID_HOADON) AS SoLuongDonHang
            FROM HOADON_CT HDCT JOIN HOADON HD ON HDCT.ID_HOADON = HD.ID_HOADON
            WHERE YEAR(HD.NGAYTAO) = YEAR(GETDATE()) AND MONTH(HD.NGAYTAO) = MONTH(GETDATE()) 
                               AND DAY(HD.NGAYTAO) = DAY(GETDATE()) AND HD.TRANGTHAI = 1
             """;
        int soLuong = 0;
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
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

    // các phương thức để thông kê doanh thu
    public int getSoLuongInThang(int nam, int thang) {
        sql = """
             SELECT SUM(hdct.SOLUONG)
             FROM HOADON_CT hdct  JOIN HOADON hd ON hdct.ID_HOADON =hd.ID_HOADON 
             WHERE YEAR(hd.NGAYTAO) = ?
                   AND MONTH(hd.NGAYTAO) = ?
             	   AND hd.TRANGTHAI = 1 AND hdct.TRANGTHAI = 1
             """;
        int soLuong = 0;
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, nam);
            ps.setObject(2, thang);
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

    public double getDoanhThuInThang(int nam, int thang) {
        sql = """
              SELECT  SUM(hdct.SOLUONG*hdct.GIA)
              FROM HOADON_CT hdct  JOIN HOADON hd ON hdct.ID_HOADON =hd.ID_HOADON 
              WHERE YEAR(hd.NGAYTAO) = ?
              AND MONTH(hd.NGAYTAO) = ?
              AND hd.TRANGTHAI = 1 AND hdct.TRANGTHAI = 1
             """;
        double doanhThu = 0;
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, nam);
            ps.setObject(2, thang);
            rs = ps.executeQuery();
            while (rs.next()) {
                doanhThu = rs.getInt(1);
            }
            return doanhThu;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public double getTongNhapInThang(int nam, int thang) {
        sql = """
              select   SUM(hdnct.SOLUONGNHAP*hdnct.GIANHAP) 
              from HOADONNHAP_CT hdnct  JOIN HOADONNHAP hdn ON hdnct.ID_HOADONNHAP =hdn.ID_HOADONNHAP 
              where YEAR(hdn.NGAYTAO) = ?
              	and month(hdn.NGAYTAO)=? 	
                and hdn.TRANGTHAI = 1 
             """;
        double doanhThu = 0;
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, nam);
            ps.setObject(2, thang);
            rs = ps.executeQuery();
            while (rs.next()) {
                doanhThu = rs.getInt(1);
            }
            return doanhThu;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public double getVoucherInThang(int nam, int thang) {
        sql = """
              SELECT SUM(VCCT.SOTIENGIAM)
              FROM VOUCHER_CT VCCT JOIN HOADON HD ON VCCT.ID_HOADON = HD.ID_HOADON
              WHERE YEAR(hd.NGAYTAO) = ?
                     AND MONTH(hd.NGAYTAO)=? 
                     AND HD.TRANGTHAI = 1
             """;
        double doanhThu = 0;
        try {
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1, nam);
            ps.setObject(2, thang);
            rs = ps.executeQuery();
            while (rs.next()) {
                doanhThu = rs.getInt(1);
            }
            return doanhThu;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
