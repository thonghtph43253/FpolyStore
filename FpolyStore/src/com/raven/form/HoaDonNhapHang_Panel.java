/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;

import com.fstore.untils.XDate;
import com.fstore.untils.XuatExcelFromTbl;
import com.fstore.model.HoaDonNhap;
import com.fstore.service.HoaDonNhap_Service;
import com.ui.main.Main;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class HoaDonNhapHang_Panel extends javax.swing.JPanel {

    private HoaDonNhap_Service hoaDonNhap_Service = new HoaDonNhap_Service();
    private int search = 0;
    public HoaDonNhapHang_Panel() {
        initComponents();
        init();
    }

    public void init(){
        fillTable(hoaDonNhap_Service.selectAll());
    }
    public void fillTable(List<HoaDonNhap> list) {
        DefaultTableModel tblMd = (DefaultTableModel) tblHoaDonNhap.getModel();
        tblMd.setRowCount(0);
        for (HoaDonNhap hdn : list) {
            String trangThai = "";
            if(hdn.getTrangThai() == 1){
                trangThai ="Thành công";
            }else if(hdn.getTrangThai() == 2){
                 trangThai ="Đã hủy";
            }
            
            
                tblMd.addRow(new Object[]{
                    hdn.getId_HoaDonNhap(),
                    hdn.getTenNhaCungCap(),
                    hdn.getSoDienThoai(),
                    hdn.getMaNv(),
                    hdn.getTongTien(),
                    hdn.getGhiChu(),
                    trangThai
                });
            
        }
    }
    
    public void search(){
        List<HoaDonNhap> list = new ArrayList<>();
        if(search == 0){
            String idT = txtSearch.getText().trim();
            int id = 0;
            try {
                id = Integer.parseInt(idT);
            } catch (Exception e) {
            }
            HoaDonNhap hd = hoaDonNhap_Service.selectByID(id);
            if(hd!= null){
                list.add(hd);
            }
        }else if(search == 1){
            String ten = txtSearch.getText().trim();
            list = hoaDonNhap_Service.selectName(ten);
        }else if(search ==2){
            String sdt = txtSearch.getText().trim();
            list = hoaDonNhap_Service.selectSDT(sdt);
        }
        fillTable(list);
    }
    
    public void locHD(){
        Date ngayBD = XDate.toDate(txtNgayBd.getText(), "dd-MM-yyyy");
        Date ngayKT = XDate.toDate(txtNgayKt.getText(), "dd-MM-yyyy");
        int tt = 1;
        if(rdoTC.isSelected()){
            tt = 1;
        }else if(rdoHuy.isSelected()){
            tt = 2;
        }
        List<HoaDonNhap> list = hoaDonNhap_Service.selectByDay(ngayBD, ngayKT, tt);
        fillTable(list);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        dateChooser1 = new com.raven.datechooserr.DateChooser();
        dateChooser2 = new com.raven.datechooserr.DateChooser();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNgayBd = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNgayKt = new javax.swing.JTextField();
        btnHuyLoc = new javax.swing.JButton();
        btnLoc = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        rdoTC = new javax.swing.JRadioButton();
        rdoHuy = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDonNhap = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbbTkSanPham = new javax.swing.JComboBox<>();
        txtSearch = new javax.swing.JTextField();
        btnTimSP = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        dateChooser1.setForeground(new java.awt.Color(51, 255, 0));
        dateChooser1.setTextRefernce(txtNgayBd);

        dateChooser2.setForeground(new java.awt.Color(0, 204, 204));
        dateChooser2.setTextRefernce(txtNgayKt);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lọc hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Ngày bắt đầu");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Ngày kết thúc");

        btnHuyLoc.setBackground(new java.awt.Color(255, 153, 153));
        btnHuyLoc.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnHuyLoc.setText("Hủy lọc");
        btnHuyLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyLocActionPerformed(evt);
            }
        });

        btnLoc.setBackground(new java.awt.Color(102, 255, 51));
        btnLoc.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLoc.setText("Lọc");
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Trạng thái");

        buttonGroup1.add(rdoTC);
        rdoTC.setSelected(true);
        rdoTC.setText("Thành công");

        buttonGroup1.add(rdoHuy);
        rdoHuy.setText("Hủy");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnHuyLoc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLoc))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)
                        .addComponent(txtNgayKt, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                        .addComponent(txtNgayBd))
                    .addComponent(jLabel3)
                    .addComponent(rdoTC)
                    .addComponent(rdoHuy))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNgayBd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNgayKt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdoTC)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdoHuy)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnHuyLoc, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addGap(19, 19, 19))
        );

        tblHoaDonNhap.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblHoaDonNhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hóa đơn", "Tên nhà cung cấp", "Số điện thoại", "Nhân viên nhập", "Tổng tiền", "Ghi chú", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDonNhap.setRowHeight(25);
        tblHoaDonNhap.setSelectionBackground(new java.awt.Color(0, 0, 0));
        tblHoaDonNhap.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tblHoaDonNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonNhapMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDonNhap);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setText("HÓA ĐƠN NHẬP HÀNG");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Tìm kiếm theo");

        cbbTkSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã", "Tên", "Số điện thoại" }));

        btnTimSP.setBackground(new java.awt.Color(204, 255, 0));
        btnTimSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTimSP.setText("Tìm");

        jButton6.setText("|<<");

        jButton7.setText("<<");

        jButton8.setText(">>");

        jButton9.setText(">>|");

        jButton1.setBackground(new java.awt.Color(102, 255, 102));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setText("Xuất File");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbbTkSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(btnTimSP)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 163, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton9)
                .addGap(237, 237, 237))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbTkSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimSP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton7)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8)
                    .addComponent(jButton9))
                .addContainerGap(10, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblHoaDonNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonNhapMouseClicked
       int row = tblHoaDonNhap.getSelectedRow();
       int idHDN = Integer.parseInt(tblHoaDonNhap.getValueAt(row, 0).toString());
       new ChiTietHoaDonNhap_Jdialog(new Main(), true, idHDN).setVisible(true);
    }//GEN-LAST:event_tblHoaDonNhapMouseClicked

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        locHD();
    }//GEN-LAST:event_btnLocActionPerformed

    private void btnHuyLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyLocActionPerformed
        fillTable(hoaDonNhap_Service.selectAll());
    }//GEN-LAST:event_btnHuyLocActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            XuatExcelFromTbl xuatEx = new XuatExcelFromTbl();
            xuatEx.exportExcel((DefaultTableModel) tblHoaDonNhap.getModel());
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuyLoc;
    private javax.swing.JButton btnLoc;
    private javax.swing.JButton btnTimSP;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbTkSanPham;
    private com.raven.datechooserr.DateChooser dateChooser1;
    private com.raven.datechooserr.DateChooser dateChooser2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JRadioButton rdoHuy;
    private javax.swing.JRadioButton rdoTC;
    private javax.swing.JTable tblHoaDonNhap;
    private javax.swing.JTextField txtNgayBd;
    private javax.swing.JTextField txtNgayKt;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
