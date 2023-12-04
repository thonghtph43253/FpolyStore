/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;

import com.fstore.untils.MsgBox;
import com.fstore.untils.XuatExcelFromTbl;
import com.fstore.model.ChatLieu;
import com.fstore.service.ChatLieu_Service;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class ChatLieu_Panel extends javax.swing.JPanel {
    
    private ChatLieu_Service service = new ChatLieu_Service();
    private int search = 0;

    public ChatLieu_Panel() {
        initComponents();
        this.init();
    }

    public void init() {
        this.loadTale(service.selectAll());
    }

    public void loadTale(List<ChatLieu> list) {
        DefaultTableModel tblMd = (DefaultTableModel) this.tblThuocTinh.getModel();
        tblMd.setRowCount(0);
        for (ChatLieu cl : list) {
            tblMd.addRow(new Object[]{
                cl.getID_ChatLieu(),
                cl.getTenChatLieu(),
                cl.getTrangThai() == 1 ? "Hoạt động" : "Không hoạt động"
            });
        }
        resetForm();
    }

    public ChatLieu getForm() {
        String ten = txtTenTT.getText().trim();
        int trangThai = 1;
        if (rdoHD.isSelected()) {
            trangThai = 1;
        } else {
            trangThai = 0;
        }
        if (ten.isEmpty()) {
            MsgBox.alert(this, "Tên thuộc tính không được bỏ trống!");
            return null;
        }
        return new ChatLieu(ten, trangThai);
    }

    public void addThuocTinh() {
        ChatLieu cl = getForm();
        if (cl == null) {
            return;
        }
        if (MsgBox.confirm(this, "Chắc chắn muốn thêm")) {
            if (service.insert(cl) != 0) {
                loadTale(service.selectAll());
                MsgBox.alert(this, "Thành công!");
            } else {
                MsgBox.alert(this, "Thất bại!");
            }
        }
    }

    public void updateThuocTinh() {
        ChatLieu cl = getForm();
        int row = tblThuocTinh.getSelectedRow();
        int id = Integer.parseInt(tblThuocTinh.getValueAt(row, 0).toString());
        if (cl == null || id < 0) {
            return;
        }
        if (MsgBox.confirm(this, "Chắc chắn muốn sửa")) {
            if (service.update(cl, id) != 0) {
                loadTale(service.selectAll());
                MsgBox.alert(this, "Thành công!");
            } else {
                MsgBox.alert(this, "Thất bại!");
            }
        }
    }

    public void deleteThuocTinh() {
        int row = tblThuocTinh.getSelectedRow();
        int id = Integer.parseInt(tblThuocTinh.getValueAt(row, 0).toString());
        if (id < 0) {
            return;
        }
        if (MsgBox.confirm(this, "Chắc chắn muốn sửa")) {
            if (service.delete(id) != 0) {
                loadTale(service.selectAll());
                MsgBox.alert(this, "Thành công!");
            } else {
                MsgBox.alert(this, "Thất bại!");
            }
        }
    }

    public void resetForm() {
        txtTenTT.setText("");
        rdoHD.setSelected(true);
    }

    public void setForm(ChatLieu cl) {
        txtTenTT.setText(cl.getTenChatLieu());
        if (cl.getTrangThai() == 1) {
            rdoHD.setSelected(true);
        } else {
            rdoKHD.setSelected(true);
        }
    }

    public void search() {
        if (search == 0) {
            try {
                int id = Integer.parseInt(txtSearch.getText().trim());
                ChatLieu cl = service.selectByID(id);
                List<ChatLieu> list = new ArrayList<>();
                loadTale(list);
            } catch (NumberFormatException numberFormatException) {
            }
        } else {
            String ten = txtSearch.getText().trim();
            loadTale(service.selectAllByName(ten));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgrTT = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel3 = new javax.swing.JLabel();
        cboSearch = new javax.swing.JComboBox<>();
        txtSearch = new javax.swing.JTextField();
        btnXuatEx = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblThuocTinh = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtTenTT = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        rdoHD = new javax.swing.JRadioButton();
        rdoKHD = new javax.swing.JRadioButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Tìm kiếm theo");

        cboSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã", "Tên" }));
        cboSearch.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboSearchItemStateChanged(evt);
            }
        });

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSearchKeyTyped(evt);
            }
        });

        btnXuatEx.setBackground(new java.awt.Color(102, 255, 102));
        btnXuatEx.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXuatEx.setText("Xuất File");
        btnXuatEx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatExActionPerformed(evt);
            }
        });

        tblThuocTinh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblThuocTinh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Tên Chất Liệu", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblThuocTinh.setRowHeight(25);
        tblThuocTinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThuocTinhMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblThuocTinh);

        jButton6.setText("|<<");

        jButton7.setText("<<");

        jButton8.setText(">>");

        jButton9.setText(">>|");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin chất liệu", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(412, 294));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Tên Chất Liệu");

        txtTenTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenTTActionPerformed(evt);
            }
        });

        jLabel7.setText("Trạng Thái");

        bgrTT.add(rdoHD);
        rdoHD.setSelected(true);
        rdoHD.setText("Đang hoạt động");

        bgrTT.add(rdoKHD);
        rdoKHD.setText("Không hoạt động");

        btnThem.setBackground(new java.awt.Color(0, 255, 0));
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThem.setText("Thêm ");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(204, 102, 0));
        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(255, 0, 0));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnReset.setBackground(new java.awt.Color(0, 255, 204));
        btnReset.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnReset.setText("Tạo mới");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rdoHD)
                                .addGap(40, 40, 40)
                                .addComponent(rdoKHD))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(28, 28, 28)
                                .addComponent(txtTenTT, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 37, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTenTT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoHD)
                    .addComponent(rdoKHD))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 122, Short.MAX_VALUE)
                        .addComponent(btnXuatEx, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton9)
                .addGap(158, 158, 158))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cboSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXuatEx, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton7)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8)
                    .addComponent(jButton9))
                .addGap(12, 12, 12))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTenTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenTTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenTTActionPerformed

    private void tblThuocTinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThuocTinhMouseClicked
        int row = tblThuocTinh.getSelectedRow();
        int id = Integer.parseInt(tblThuocTinh.getValueAt(row, 0).toString());
        ChatLieu cl = service.selectByID(id);
        setForm(cl);
    }//GEN-LAST:event_tblThuocTinhMouseClicked

    private void cboSearchItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboSearchItemStateChanged
        search = cboSearch.getSelectedIndex();
    }//GEN-LAST:event_cboSearchItemStateChanged

    private void btnXuatExActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatExActionPerformed
          try {
            XuatExcelFromTbl excelFromTbl = new XuatExcelFromTbl();
            excelFromTbl.exportExcel((DefaultTableModel) tblThuocTinh.getModel());
        } catch (Exception e) {
        }        
    }//GEN-LAST:event_btnXuatExActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        addThuocTinh();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        updateThuocTinh();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        deleteThuocTinh();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        resetForm();
    }//GEN-LAST:event_btnResetActionPerformed

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
       search();
    }//GEN-LAST:event_txtSearchKeyPressed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
       search();
    }//GEN-LAST:event_txtSearchKeyReleased

    private void txtSearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyTyped
        search();
    }//GEN-LAST:event_txtSearchKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgrTT;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXuatEx;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboSearch;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JRadioButton rdoHD;
    private javax.swing.JRadioButton rdoKHD;
    private javax.swing.JTable tblThuocTinh;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTenTT;
    // End of variables declaration//GEN-END:variables
}
