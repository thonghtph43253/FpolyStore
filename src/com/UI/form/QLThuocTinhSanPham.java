/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.UI.form;

import java.awt.CardLayout;

/**
 *
 * @author ThongHTPH43253
 */
public class QLThuocTinhSanPham extends javax.swing.JPanel {
    private static CardLayout cardLayout = new CardLayout();
    private DanhMuc_Panel loaiSPPnl = new DanhMuc_Panel();
    private Size_Panel size_Panel = new Size_Panel();
    private ChatLieu_Panel chatLieu_Panel = new ChatLieu_Panel();
    private MauSac_Panel mauSac_Panel = new MauSac_Panel();
    private NhaCungCap_Panel nhaCungCap_Panel = new NhaCungCap_Panel();
    private String loaiThuocTinh = null;
    public QLThuocTinhSanPham() {
        initComponents();
        this.setLayout();
    }
    public void setLayout(){
        pnlThuocTinh.setLayout(cardLayout);
        pnlThuocTinh.add(loaiSPPnl, "loaisanpham");
        pnlThuocTinh.add(size_Panel,"size");
        pnlThuocTinh.add(chatLieu_Panel, "chatlieu");
        pnlThuocTinh.add(mauSac_Panel,"mausac");
        pnlThuocTinh.add(nhaCungCap_Panel,"nhacungcap");
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cboThuocTinh = new javax.swing.JComboBox<>();
        pnlThuocTinh = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();

        jLabel1.setText("THUỘC TÍNH SẢN PHẨM");

        jLabel2.setText("Loại thuộc tính");

        cboThuocTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Loại Sản Phẩm", "Size", "Màu Sắc", "Chất Liệu", "Nhà Cung Cấp" }));
        cboThuocTinh.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboThuocTinhItemStateChanged(evt);
            }
        });

        pnlThuocTinh.setPreferredSize(new java.awt.Dimension(990, 510));

        javax.swing.GroupLayout pnlThuocTinhLayout = new javax.swing.GroupLayout(pnlThuocTinh);
        pnlThuocTinh.setLayout(pnlThuocTinhLayout);
        pnlThuocTinhLayout.setHorizontalGroup(
            pnlThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThuocTinhLayout.createSequentialGroup()
                .addComponent(jLabel6)
                .addGap(0, 1046, Short.MAX_VALUE))
        );
        pnlThuocTinhLayout.setVerticalGroup(
            pnlThuocTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThuocTinhLayout.createSequentialGroup()
                .addGap(155, 155, 155)
                .addComponent(jLabel6)
                .addContainerGap(420, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 1046, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(cboThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cboThuocTinhItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboThuocTinhItemStateChanged
        loaiThuocTinh = cboThuocTinh.getSelectedItem().toString();
        if(loaiThuocTinh.equalsIgnoreCase("Loại Sản Phẩm")){
            cardLayout.show(pnlThuocTinh, "loaisanpham");
        }else if(loaiThuocTinh.equalsIgnoreCase("Size")){
            cardLayout.show(pnlThuocTinh, "size");
        }else if(loaiThuocTinh.equalsIgnoreCase("Chất Liệu")){
            cardLayout.show(pnlThuocTinh, "chatlieu");
        }else if(loaiThuocTinh.equalsIgnoreCase("Màu sắc")){
            cardLayout.show(pnlThuocTinh, "mausac");
        }else if(loaiThuocTinh.equalsIgnoreCase("Nhà cung cấp")){
            cardLayout.show(pnlThuocTinh, "nhacungcap");
        }
        System.out.println(loaiThuocTinh);
    }//GEN-LAST:event_cboThuocTinhItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboThuocTinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel pnlThuocTinh;
    // End of variables declaration//GEN-END:variables
}
