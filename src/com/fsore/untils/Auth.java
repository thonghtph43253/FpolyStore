/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fsore.untils;

import com.fstore.model.NhanVien;



/**
 *
 * @author ThongHTPH43253
 */
public class Auth {

    public static NhanVien nv = null ;

    public static void clear() {
        Auth.nv = null;
    }

    public static boolean isLogin() {
        return Auth.nv != null;
    }

    public static boolean isManager() {
        return Auth.isLogin() && nv.isVaiTro();
    }
}
