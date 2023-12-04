/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.raven.form;

import com.fstore.untils.MsgBox;
import com.fstore.model.KhachHang;
import com.fstore.service.KhachHang_Service;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ThemKhachHangJDialog extends javax.swing.JDialog {

    private int id_Kh;
    private KhachHang_Service khachHang_Service = new KhachHang_Service();
    KhachHang_Panel khPnl;

    public ThemKhachHangJDialog(java.awt.Frame parent, boolean modal, KhachHang_Panel khPnl) {
        super(parent, modal);
        initComponents();
        init();
        this.khPnl = khPnl;
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
    }

    public ThemKhachHangJDialog(java.awt.Frame parent, boolean modal, int id_Kh, KhachHang_Panel khPnl) {
        super(parent, modal);
        this.id_Kh = id_Kh;
        initComponents();
        init();
        KhachHang kh = khachHang_Service.selectByID(this.id_Kh);
        setForm(kh);
        this.khPnl = khPnl;
        btnThem.setEnabled(false);
    }

    public void init() {
        setHiden();
    }

    public void setHiden() {
        txtNgaySinh.setEditable(false);
    }

    public KhachHang getForm(int check) {
        List<KhachHang> listKH = khachHang_Service.selectAll();
        String ten = txtTenKH.getText();
        String sdt = txtSDT.getText();
        String diaChi = txtDiaChi.getText();
        String ngaySinh = txtNgaySinh.getText();
        Date d = new Date();
        StringBuffer er = new StringBuffer();
        boolean gt = true;
        int tt = 1;
        if (rdoNam.isSelected()) {
            gt = true;
        } else if (rdoNu.isSelected()) {
            gt = false;
        }
        if (rdoHD.isSelected()) {
            tt = 1;
        } else if (rdoHD.isSelected()) {
            tt = 0;
        }

        if (ten.isEmpty()) {
            er.append("Tên khách hàng không được bỏ trống!\n");
        }
        if (check == 1) {
            if (sdt.isEmpty()) {
                er.append("Số điện thoại không được bỏ trống!\n");
            } else if (!sdt.matches("((0\\d{9})|(\\+84|84)\\d{9})")) {
                er.append("Số điện thoại phải bắt đầu là 0|+84!\n");
            } else {
                for (KhachHang kh : listKH) {
                    if (sdt.equals(kh.getsDT())) {
                        er.append("Khách hàng đã tồn tại!\n");
                    }
                }
            }
        } else if (check == 2) {
            if (sdt.isEmpty()) {
                er.append("Số điện thoại không được bỏ trống!\n");
            } else if (!sdt.matches("((0\\d{9})|(\\+84|84)\\d{9})")) {
                er.append("Số điện thoại phải bắt đầu là 0|+84!\n");
            }
        }

        SimpleDateFormat dateFormat1 = new SimpleDateFormat("MM-dd-yyyy");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd-MM-yyyy");
        try {
            d = dateFormat2.parse(ngaySinh);
        } catch (Exception e) {
            er.append("Ngày sinh sai định dạng(dd-mm-yyyy)!\n");
        }
        if (er.length() > 0) {
            MsgBox.alert(this, er.toString());
            return null;
        }
        return new KhachHang(ten, sdt, dateFormat1.format(d), diaChi, diaChi, gt, tt);
    }

    public void insertKH() {
        KhachHang kh = getForm(1);
        if (kh == null) {
            return;
        }
        if (MsgBox.confirm(this, "Bạn có chắc chắn muốn thêm khách hàng?")) {
            if (khachHang_Service.insert(kh) != 0) {
                MsgBox.alert(this, "Thêm khách hàng thành công!");
                khPnl.loadTale(khachHang_Service.selectAll());
                resetForm();
            } else {
                MsgBox.alert(this, "Thêm khách hàng thất bại!");
            }
        }
    }

    public void setForm(KhachHang kh) {
        txtTenKH.setText(kh.getTen());
        txtSDT.setText(kh.getsDT());
        txtNgaySinh.setText(kh.getNgaySinh());
        txtDiaChi.setText(kh.getDiaChi());
        lblMaKH.setText(String.valueOf(kh.getId_KhachHang()));
        if (kh.isGioiTinh()) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }

        if (kh.getTrangThai() == 1) {
            rdoHD.setSelected(true);
        } else if (kh.getTrangThai() == 0) {
            rdoKHD.setSelected(true);
        }
    }

    public void updateKH() {
        KhachHang kh = getForm(2);

        if (kh == null) {
            return;
        }
        if (MsgBox.confirm(this, "Bạn có chắc chắn muốn sửa khách hàng?")) {
            if (khachHang_Service.update(kh, id_Kh) != 0) {
                MsgBox.alert(this, "Sửa khách hàng thành công!");
                khPnl.loadTale(khachHang_Service.selectAll());
                resetForm();

            } else {
                MsgBox.alert(this, "Sửa khách hàng thất bại!");
            }
        }
    }

    public void resetForm() {
        txtDiaChi.setText("");
        txtSDT.setText("");
        txtTenKH.setText("");
        rdoNam.setSelected(true);
        rdoHD.setSelected(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser1 = new com.raven.datechooserr.DateChooser();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel7 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txtNgaySinh = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtDiaChi = new javax.swing.JTextArea();
        jLabel34 = new javax.swing.JLabel();
        rdoHD = new javax.swing.JRadioButton();
        rdoKHD = new javax.swing.JRadioButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        rdoNam = new javax.swing.JRadioButton();
        jLabel35 = new javax.swing.JLabel();
        rdoNu = new javax.swing.JRadioButton();
        lblMaKH = new javax.swing.JLabel();

        dateChooser1.setTextRefernce(txtNgaySinh);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel28.setText("Mã khách hàng");

        jLabel29.setText("Tên khách hàng");

        jLabel30.setText("Số điện thoại");

        jLabel31.setText("Ngày sinh");

        jLabel33.setText("Địa chỉ");

        txtDiaChi.setColumns(20);
        txtDiaChi.setRows(5);
        jScrollPane5.setViewportView(txtDiaChi);

        jLabel34.setText("Trạng thái");

        buttonGroup2.add(rdoHD);
        rdoHD.setSelected(true);
        rdoHD.setText("Đang hoạt động");

        buttonGroup2.add(rdoKHD);
        rdoKHD.setText("Không hoạt động");

        btnThem.setBackground(new java.awt.Color(0, 255, 0));
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(0, 255, 204));
        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
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

        btnReset.setBackground(new java.awt.Color(255, 255, 0));
        btnReset.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnReset.setText("Tạo mới");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoNam);
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");

        jLabel35.setText("Giới tính");

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                                .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtTenKH, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jLabel30)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel33)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel31)
                                .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(lblMaKH))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(rdoNam)
                                .addGap(43, 43, 43)
                                .addComponent(rdoNu))
                            .addComponent(jLabel35)
                            .addComponent(jLabel34)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(rdoHD)
                                .addGap(18, 18, 18)
                                .addComponent(rdoKHD))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblMaKH)
                                .addGap(8, 8, 8))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel35)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rdoNam)
                                    .addComponent(rdoNu))))
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel30))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel34)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rdoHD)
                                    .addComponent(rdoKHD))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(176, 176, 176)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel33)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 578, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 439, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 32, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        insertKH();
        this.dispose();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        updateKH();
        this.dispose();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        if (MsgBox.confirm(this, "Bạn có chắc chắn muốn xóa khách hàng!")) {
            if (khachHang_Service.delete(id_Kh) != 0) {
                MsgBox.alert(this, "Xóa thành công khách hàng!");
                khPnl.loadTale(khachHang_Service.selectAll());
            } else {
                MsgBox.alert(this, "Không thể xóa vì khách hàng đã mua hàng\nĐã chuyển trạng thái về không hoạt động!");
                KhachHang kh = khachHang_Service.selectByID(id_Kh);
                if (kh != null) {
                    kh.setTrangThai(0);
                    khachHang_Service.update(kh, id_Kh);
                    khPnl.loadTale(khachHang_Service.selectAll());
                }
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        resetForm();
    }//GEN-LAST:event_btnResetActionPerformed

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
            java.util.logging.Logger.getLogger(ThemKhachHangJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThemKhachHangJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThemKhachHangJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThemKhachHangJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ThemKhachHangJDialog dialog = new ThemKhachHangJDialog(new javax.swing.JFrame(), true, null);
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
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private com.raven.datechooserr.DateChooser dateChooser1;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblMaKH;
    private javax.swing.JRadioButton rdoHD;
    private javax.swing.JRadioButton rdoKHD;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTextArea txtDiaChi;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenKH;
    // End of variables declaration//GEN-END:variables
}
