/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.raven.form;

import com.fsore.untils.XDate;
import com.fstore.model.HoaDon;
import com.fstore.model.HoaDon_ChiTiet;
import com.fstore.model.SanPhamChiTiet;
import com.fstore.model.Voucher_ChiTiet;
import com.fstore.service.ChatLieu_Service;
import com.fstore.service.HoaDonChiTiet_Service;
import com.fstore.service.HoaDon_Service;
import com.fstore.service.MauSac_Service;
import com.fstore.service.SanPhamChiTiet_Service;
import com.fstore.service.SanPham_Service;
import com.fstore.service.Size_Service;
import com.fstore.service.Voucher_CT_Service;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class ChiTietHoaDon_Jdialog extends javax.swing.JDialog {

    private int id_HoaDon;
    private HoaDonChiTiet_Service hdct_Service = new HoaDonChiTiet_Service();
    private HoaDon_Service hoaDon_Service = new HoaDon_Service();
    private SanPham_Service sp_Service = new SanPham_Service();
    private SanPhamChiTiet_Service spct_Service = new SanPhamChiTiet_Service();
    private Size_Service size_Service = new Size_Service();
    private ChatLieu_Service chatLieu_Service = new ChatLieu_Service();
    private MauSac_Service mauSac_Service = new MauSac_Service();
    private Voucher_CT_Service voucher_CT_Service = new Voucher_CT_Service();

    public ChiTietHoaDon_Jdialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

    }

    public ChiTietHoaDon_Jdialog(java.awt.Frame parent, boolean modal, int id_HoaDon) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.id_HoaDon = id_HoaDon;
        init();
    }

    public void init() {
        fillTable(hdct_Service.selectByID_HoaDonTT(id_HoaDon, 1));
        lblMaHD.setText(String.valueOf(id_HoaDon));
        HoaDon hd = hoaDon_Service.selectByID(id_HoaDon);
        Voucher_ChiTiet vct = voucher_CT_Service.selectByIDHD(hd.getId_HoaDon());
        lblTenKH.setText(hd.getTenKH());
        lblSdt.setText(hd.getSdt());
        if (vct != null) {
            lblTongTien1.setText(hd.getTongTien() + vct.getSoTienGiam() + " VNĐ");
            lblGiamGia.setText(vct.getSoTienGiam() + "VNĐ");
        }else{
            lblTongTien1.setText(hd.getTongTien()+ " VNĐ");
            lblGiamGia.setText( "0 VNĐ");
        }

        lblThanhToan.setText(hd.getTongTien() + " VND");
        lblThoiGian.setText(hd.getNgayTao());
    }

    public void fillTable(List<HoaDon_ChiTiet> list) {
        DefaultTableModel tblMd = (DefaultTableModel) tblHoaDon_CT.getModel();
        tblMd.setRowCount(0);
        for (HoaDon_ChiTiet hdct : list) {
//            int id_Sp = spct_Service.selectByID(hdct.getId_SanPhamChiTiet()).getId_SanPham();
            // System.out.println(id_Sp);
            SanPhamChiTiet spct = spct_Service.selectByID(hdct.getId_SanPhamChiTiet());
            tblMd.addRow(new Object[]{
                hdct.getId_HoaDonCT(),
                sp_Service.selectByID(spct.getId_SanPham()).getTenSP(),
                size_Service.selectByID(spct.getId_Size()).getTenSize(),
                mauSac_Service.selectByID(spct.getId_ChatLieu()).getTenMau(),
                chatLieu_Service.selectByID(spct.getId_ChatLieu()).getTenChatLieu(),
                hdct.getSoLuong(),
                hdct.getGia()
            });
        }
    }

    public double tongTien() {
        double tongTien = 0;
        int row = tblHoaDon_CT.getRowCount();
        for (int i = 0; i < row; i++) {
            int soLuong = Integer.parseInt(tblHoaDon_CT.getValueAt(i, 5).toString());
            double gia = Double.parseDouble(tblHoaDon_CT.getValueAt(i, 6).toString());
            tongTien += soLuong * gia;
        }
        return tongTien;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon_CT = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        lblTenKH = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblThanhToan = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        lblMaHD = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblSdt = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblThoiGian = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblTongTien1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblGiamGia = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("HÓA ĐƠN CHI TIẾT");

        tblHoaDon_CT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID_HDCT", "TÊN SP", "SIZE", "MÀU SẮC", "CHẤT LIỆU", "SỐ LƯỢNG", "GIÁ TIỀN"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblHoaDon_CT);

        jLabel2.setText("TÊN KHÁCH HÀNG");

        lblTenKH.setText("jLabel3");

        jLabel3.setText("Thanh toán");

        lblThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblThanhToan.setForeground(new java.awt.Color(255, 0, 0));
        lblThanhToan.setText("0.0");

        jButton1.setText("Xuất hóa đơn");

        jButton2.setText("Hủy");

        jLabel5.setText("Mã hóa đơn:");

        lblMaHD.setText("jLabel6");

        jLabel4.setText("Số điện thoại");

        lblSdt.setText("jLabel6");

        jLabel6.setText("Ngày tạo");

        lblThoiGian.setText("t");

        jLabel7.setText("Tổng tiền");

        lblTongTien1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTongTien1.setForeground(new java.awt.Color(255, 0, 0));
        lblTongTien1.setText("0.0");

        jLabel8.setText("Giảm giá");

        lblGiamGia.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblGiamGia.setForeground(new java.awt.Color(255, 0, 0));
        lblGiamGia.setText("0.0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblSdt)
                                    .addComponent(lblTenKH)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblMaHD)
                                        .addGap(227, 227, 227)
                                        .addComponent(jLabel6)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblThoiGian)))))
                        .addGap(0, 225, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblTongTien1))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblGiamGia))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(lblThanhToan))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblMaHD)
                    .addComponent(jLabel6)
                    .addComponent(lblThoiGian))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblTenKH))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblSdt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblTongTien1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lblGiamGia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblThanhToan))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChiTietHoaDon_Jdialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChiTietHoaDon_Jdialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChiTietHoaDon_Jdialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChiTietHoaDon_Jdialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ChiTietHoaDon_Jdialog dialog = new ChiTietHoaDon_Jdialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblGiamGia;
    private javax.swing.JLabel lblMaHD;
    private javax.swing.JLabel lblSdt;
    private javax.swing.JLabel lblTenKH;
    private javax.swing.JLabel lblThanhToan;
    private javax.swing.JLabel lblThoiGian;
    private javax.swing.JLabel lblTongTien1;
    private javax.swing.JTable tblHoaDon_CT;
    // End of variables declaration//GEN-END:variables
}
