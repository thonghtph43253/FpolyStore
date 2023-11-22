/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fstore.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class test {

    static List<HoaDon_ChiTiet> list1 = new ArrayList<>();
    static List<HoaDon_ChiTiet> list2 = new ArrayList<>();
    static HoaDon_ChiTiet hdct1 = new HoaDon_ChiTiet(1, 10, 3, 4, 3, 1);
    static HoaDon_ChiTiet hdct2 = new HoaDon_ChiTiet(2, 10, 8, 4, 3, 1);
    static HoaDon_ChiTiet hdct3 = new HoaDon_ChiTiet(3, 10, 11, 4, 3, 1);
    static HoaDon_ChiTiet hdct4 = new HoaDon_ChiTiet(4, 10, 6, 4, 3, 1);
    static HoaDon_ChiTiet hdct5 = new HoaDon_ChiTiet(5, 10, 11, 4, 3, 1);
    static HoaDon_ChiTiet hdct6 = new HoaDon_ChiTiet(6, 10, 12, 4, 3, 1);

    public test() {
        list1.add(hdct1);
        list1.add(hdct2);
        list1.add(hdct3);
        list1.add(hdct4);
        list2.add(hdct5);
        list2.add(hdct6);
    }

    public void insert(int idHD) {

        List<HoaDon_ChiTiet> existingDetails = list1;

        for (HoaDon_ChiTiet hdct : list2) {
            HoaDon_ChiTiet existingDetail = getExistingDetail(existingDetails, hdct);

            if (existingDetail != null) {
                // Phần tử đã tồn tại trong cơ sở dữ liệu, cập nhật nó
                hdct.setTrangThai(1);
                //System.out.println("idspct trùng nhau" + existingDetail.getId_SanPhamChiTiet());
                System.out.println("up" + hdct.getId_SanPhamChiTiet());
            } else {
                // Phần tử không tồn tại, thêm mới nó
                // hdct_Service.insert(hdct);
                System.out.println("in" + hdct.getId_SanPhamChiTiet());
            }
        }

        for (HoaDon_ChiTiet hdct : existingDetails) {
            HoaDon_ChiTiet hdct2 = getExistingDetail(list2, hdct);
            if (hdct2 != null) {
                hdct.setTrangThai(3);
                // đây là hàm xóa hdct_Service.update(hdct, hdct.getId_HoaDonCT());
                System.out.println("de" + hdct.getId_SanPhamChiTiet());
            }
        }
    }
    // Hàm để lấy phần tử từ danh sách existingDetails

    private static HoaDon_ChiTiet getExistingDetail(List<HoaDon_ChiTiet> existingDetails, HoaDon_ChiTiet target) {
        for (HoaDon_ChiTiet existingDetail : existingDetails) {
            if ((existingDetail.getId_SanPhamChiTiet())==(target.getId_SanPhamChiTiet())) {
                System.out.println("trùng" + existingDetail.getId_SanPhamChiTiet());
                return existingDetail;
            }
        }
        return null; // Trả về null nếu không tìm thấy
    }

    public static void main(String[] args) {
        list1.add(hdct1);
        list1.add(hdct2);
        list1.add(hdct3);
        list1.add(hdct4);
        list2.add(hdct5);
        list2.add(hdct6);
        List<HoaDon_ChiTiet> existingDetails = list1;
        for (HoaDon_ChiTiet hdct : existingDetails) {
            HoaDon_ChiTiet hdct2 = getExistingDetail(list2, hdct);
            if (hdct2 != null) {
                hdct.setTrangThai(3);
                // đây là hàm xóa hdct_Service.update(hdct, hdct.getId_HoaDonCT());
                System.out.println("de" + hdct.getId_SanPhamChiTiet());
            }
        }
        for (HoaDon_ChiTiet hdct : list2) {
            HoaDon_ChiTiet existingDetail = getExistingDetail(existingDetails, hdct);

            if (existingDetail != null) {
                // Phần tử đã tồn tại trong cơ sở dữ liệu, cập nhật nó
                hdct.setTrangThai(1);
                //System.out.println("idspct trùng nhau" + existingDetail.getId_SanPhamChiTiet());
                System.out.println("up" + hdct.getId_SanPhamChiTiet());
            } else {
                // Phần tử không tồn tại, thêm mới nó
                // hdct_Service.insert(hdct);
                System.out.println("in" + hdct.getId_SanPhamChiTiet());
            }
        }

        

//            for (Integer integer : list1) {
//            Integer i = getkt(list2, integer);
//                if(i != null){
//                    System.out.println("de"+ integer);
//                }
//        }
//
//            for (Integer integer : list2) {
//                Integer i = getkt(list1, integer);
//                if(i != -1){
//                     System.out.println("up"+integer);
//                }else{
//                      System.out.println("in"+integer);
//                }
//        }
//        
//
//    }
//    public static Integer getkt(List<Integer> l, Integer kt){
//        for (Integer integer : l) {
//            if(integer == kt){
//               return integer;
//            }
//        }
//            return -1;
//    }
    }
}
