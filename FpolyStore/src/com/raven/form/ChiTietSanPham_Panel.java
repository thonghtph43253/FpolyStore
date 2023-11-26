/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;

import com.fsore.untils.MsgBox;
import com.fstore.model.DanhMuc;
import com.fstore.model.SanPhamChiTiet;
import com.fstore.service.ChatLieu_Service;
import com.fstore.service.DanhMuc_Service;
import com.fstore.service.MauSac_Service;
import com.fstore.service.SanPhamChiTiet_Service;
import com.fstore.service.SanPham_Service;
import com.fstore.service.Size_Service;
import com.ui.main.Main;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ThongHTPH43253
 */
public class ChiTietSanPham_Panel extends javax.swing.JPanel {

    private SanPhamChiTiet_Service spct_Service = new SanPhamChiTiet_Service();
    private SanPham_Service sp_Service = new SanPham_Service();
    private Size_Service size_Sv = new Size_Service();
    private ChatLieu_Service chatLieu_Sv = new ChatLieu_Service();
    private MauSac_Service mauSac_Sv = new MauSac_Service();

    private DanhMuc_Service dm_Service = new DanhMuc_Service();
    private int tt = 1, soLuong = 0;
    private String dau = "<", sapXepTen = "desc", sapXepGia = "desc";

    public ChiTietSanPham_Panel() {
        initComponents();
        init();

    }

    public void init() {
        fillTable(spct_Service.selectAll());
        fillCbbDanhMuc();
    }

    public void fillTable(List<SanPhamChiTiet> list) {
        DefaultTableModel tblMD = (DefaultTableModel) this.tblSanPhamChiTiet.getModel();
        tblMD.setRowCount(0);
        for (int i = 0; i < list.size(); i++) {
            SanPhamChiTiet spct = list.get(i);

            tblMD.addRow(new Object[]{
                spct.getId_SanPhamChiTiet(),
                sp_Service.selectByID(spct.getId_SanPham()).getTenSP(),
                spct.getGia(),
                mauSac_Sv.selectByID(spct.getId_Mau()).getTenMau(),
                size_Sv.selectByID(spct.getId_Size()).getTenSize(),
                chatLieu_Sv.selectByID(spct.getId_ChatLieu()).getTenChatLieu(),
                spct.getSoLuong(),
                sp_Service.selectByID(spct.getId_SanPham()).getDm().getTenDanhMuc(),
                spct.getTrangThai()
            });
        }
    }

    public void filter() {

        if (rdoNhoHon.isSelected()) {
           
            if (txtDinhMuc.getText().trim().isEmpty()) {
                MsgBox.alert(this, "Vui lòng nhập định mức!");
                return;
            } else {
                try {
                    soLuong = Integer.parseInt(txtDinhMuc.getText().trim());
                } catch (Exception e) {
                    MsgBox.alert(this, "Định mức phải là số!");
                    return;
                }
                 dau = "<" +soLuong;
            }
        } else if (rdoLonHon.isSelected()) {
           
            if (txtDinhMuc.getText().trim().isEmpty()) {
                MsgBox.alert(this, "Vui lòng nhập định mức!");
                return;
            } else {
                try {
                    soLuong = Integer.parseInt(txtDinhMuc.getText().trim());
                } catch (Exception e) {
                    MsgBox.alert(this, "Định mức phải là số!");
                    return;
                }
                 dau = ">"+ soLuong;
            }
        } else if (rdoConHang.isSelected()) {
            dau = "> 0";
            soLuong = 0;
        } else if (rdoHetHang.isSelected()) {
            dau = "< 0";
            soLuong = 0;
        }
        if (rdoGiaCao.isSelected()) {
            sapXepGia = "asc";
        } else if (rdoGiaThap.isSelected()) {
            sapXepGia = "desc";
        }
        if (rdoA_Z.isSelected()) {
            sapXepTen = "asc";
        } else if (rdoZ_A.isSelected()) {
            sapXepTen = "desc";
        }
        if (rdoDB.isSelected()) {
            tt = 1;
        } else if (rdoNB.isSelected()) {
            tt = 0;
        }
    }

    public void fillCbbDanhMuc() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbbDanhMuc.getModel();
        model.removeAllElements();
        for (DanhMuc dm : dm_Service.selectAll()) {
            model.addElement(dm);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgrDinhMuc = new javax.swing.ButtonGroup();
        bgrTrangThai = new javax.swing.ButtonGroup();
        bgrGia = new javax.swing.ButtonGroup();
        bgrTen = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        cbbDanhMuc = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtDinhMuc = new javax.swing.JTextField();
        rdoNhoHon = new javax.swing.JRadioButton();
        rdoLonHon = new javax.swing.JRadioButton();
        rdoConHang = new javax.swing.JRadioButton();
        rdoHetHang = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        rdoDB = new javax.swing.JRadioButton();
        rdoNB = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        rdoGiaThap = new javax.swing.JRadioButton();
        rdoGiaCao = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        rdoA_Z = new javax.swing.JRadioButton();
        rdoZ_A = new javax.swing.JRadioButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPhamChiTiet = new javax.swing.JTable();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();

        jLabel2.setText("Tìm kiếm theo:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã", "Tên", " " }));
        jComboBox1.setPreferredSize(new java.awt.Dimension(72, 30));

        jTextField1.setPreferredSize(new java.awt.Dimension(64, 30));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/search.png"))); // NOI18N
        jButton1.setPreferredSize(new java.awt.Dimension(36, 30));

        jButton2.setText("Xuất File");
        jButton2.setPreferredSize(new java.awt.Dimension(75, 30));

        cbbDanhMuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbDanhMuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbDanhMucActionPerformed(evt);
            }
        });

        jLabel7.setText("Danh mục");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 186, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7)
                                    .addComponent(cbbDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(10, 10, 10))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("CHI TIẾT SẢN PHẨM");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP)), "Lọc chi tiết sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Định mức tồn kho");

        bgrDinhMuc.add(rdoNhoHon);
        rdoNhoHon.setSelected(true);
        rdoNhoHon.setText("Dưới định mức tồn kho");

        bgrDinhMuc.add(rdoLonHon);
        rdoLonHon.setText("Trên định mức tồn");

        bgrDinhMuc.add(rdoConHang);
        rdoConHang.setText("Còn hàng trong kho");

        bgrDinhMuc.add(rdoHetHang);
        rdoHetHang.setText("Hết hàng trong kho");

        jLabel3.setText("Trạng thái");

        bgrTrangThai.add(rdoDB);
        rdoDB.setSelected(true);
        rdoDB.setText("Đang bán");

        bgrTrangThai.add(rdoNB);
        rdoNB.setText("Ngừng bán");

        jLabel5.setText("Giá bán");

        bgrGia.add(rdoGiaThap);
        rdoGiaThap.setSelected(true);
        rdoGiaThap.setText("Từ thấp đến cao");

        bgrGia.add(rdoGiaCao);
        rdoGiaCao.setText("Từ cao đến thấp");

        jLabel6.setText("Theo tên");

        bgrTen.add(rdoA_Z);
        rdoA_Z.setSelected(true);
        rdoA_Z.setText("Từ A-Z");

        bgrTen.add(rdoZ_A);
        rdoZ_A.setText("Từ Z-A");

        jButton3.setText("Lọc");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Hủy lọc");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDinhMuc)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(rdoNhoHon)
                            .addComponent(rdoLonHon)
                            .addComponent(rdoConHang)
                            .addComponent(rdoHetHang)
                            .addComponent(jLabel3)
                            .addComponent(rdoDB)
                            .addComponent(rdoNB)
                            .addComponent(jLabel5)
                            .addComponent(rdoGiaThap)
                            .addComponent(rdoGiaCao)
                            .addComponent(jLabel6)
                            .addComponent(rdoA_Z)
                            .addComponent(rdoZ_A))
                        .addGap(0, 80, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDinhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdoNhoHon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdoLonHon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdoConHang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdoHetHang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdoDB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdoNB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdoGiaThap)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdoGiaCao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdoA_Z)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdoZ_A)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblSanPhamChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Sản phẩ_CT", "Tên sản phẩm", "Giá bán", "Màu sắc", "Size", "Chất liệu", "Số lượng", "Loại sản phẩm", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblSanPhamChiTiet);

        jButton9.setText("|<<");

        jButton10.setText("<<");

        jButton11.setText(">>");

        jButton12.setText(">>|");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 51, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(228, 228, 228))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton9)
                            .addComponent(jButton10)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton11)
                            .addComponent(jButton12)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbbDanhMucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbDanhMucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbDanhMucActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        filter();
        if (spct_Service.selectByFilter(dau, tt, sapXepTen, sapXepGia) != null) {
            fillTable(spct_Service.selectByFilter(dau, tt, sapXepTen, sapXepGia));
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        fillTable(spct_Service.selectAll());
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgrDinhMuc;
    private javax.swing.ButtonGroup bgrGia;
    private javax.swing.ButtonGroup bgrTen;
    private javax.swing.ButtonGroup bgrTrangThai;
    private javax.swing.JComboBox<String> cbbDanhMuc;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JRadioButton rdoA_Z;
    private javax.swing.JRadioButton rdoConHang;
    private javax.swing.JRadioButton rdoDB;
    private javax.swing.JRadioButton rdoGiaCao;
    private javax.swing.JRadioButton rdoGiaThap;
    private javax.swing.JRadioButton rdoHetHang;
    private javax.swing.JRadioButton rdoLonHon;
    private javax.swing.JRadioButton rdoNB;
    private javax.swing.JRadioButton rdoNhoHon;
    private javax.swing.JRadioButton rdoZ_A;
    private javax.swing.JTable tblSanPhamChiTiet;
    private javax.swing.JTextField txtDinhMuc;
    // End of variables declaration//GEN-END:variables
}
