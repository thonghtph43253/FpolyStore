/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;

import com.fstore.untils.Auth;
import com.fstore.model.NhanVien;
import com.fstore.service.NhanVien_Service;
import com.fstore.untils.MsgBox;
import java.awt.Color;

/**
 *
 * @author Admin
 */
public class DoiMatKhauPanel extends javax.swing.JPanel {

    private NhanVien_Service service = new NhanVien_Service();

    public DoiMatKhauPanel() {
        initComponents();
        lblMaNv.setText(Auth.nv.getMaNV());
        setHiden();
    }

    public void setHiden() {
        lblErMkC.setVisible(false);
        lblErMkM.setVisible(false);
        lblErReMk.setVisible(false);
        btnDoiMk.setEnabled(false);
        txtMkM.setEnabled(false);
        txtReMk.setEnabled(false);
    }

    public void doiMatKhau() {
        NhanVien nv = Auth.nv;
        String mkc = new String(txtMkC.getPassword()).trim();
        String mkm = new String(txtMkM.getPassword()).trim();
        String reMK = new String(txtReMk.getPassword()).trim();

    }

    public void checkMkC() {
        String mkC = new String(txtMkC.getPassword()).trim();
        NhanVien nv = service.selectByID(Auth.nv.getMaNV());
        if (mkC.isEmpty()) {
            lblErMkC.setText("Không bỏ trống mật khẩu cũ!");
            lblErMkC.setForeground(Color.red);
            lblErMkC.setVisible(true);
            txtMkM.setEnabled(false);
            txtReMk.setEnabled(false);
        } else if (!mkC.equals(nv.getMatKhau())) {
            lblErMkC.setText("Mật khẩu cũ không đúng!");
            lblErMkC.setForeground(Color.red);
            lblErMkC.setVisible(true);
            txtMkM.setEnabled(false);
            txtReMk.setEnabled(false);
        } else {
            lblErMkC.setText("Mật khẩu đã khớp, hãy nhập mật khẩu mới!");
            lblErMkC.setForeground(Color.green);
            lblErMkC.setVisible(true);
            txtMkM.setEnabled(true);
            txtReMk.setEnabled(true);
        }
    }

    public void checkMKM() {
        String matKhau = new String(txtMkM.getPassword()).trim();
        String reMatKhau = new String(txtReMk.getPassword()).trim();
        if (matKhau.isEmpty()) {
            lblErMkM.setText("Vui lòng nhập mât khẩu mới!");
            txtMkM.setBackground(Color.white);
            lblErMkM.setVisible(true);
        } else if (!matKhau.matches("^(?=.*[0-9]).{8,}$")) {
            lblErMkM.setText("Mật khẩu phải bào gồm chữ số và có ít nhất 8 kĩ tự!");
            txtMkM.setBackground(Color.white);
            lblErMkM.setVisible(true);
        } else {
            lblErMkM.setVisible(false);
            txtMkM.setBackground(Color.green);
        }
        if (!reMatKhau.equals(matKhau)) {
            lblErReMk.setText("Nhập lại mật khẩu không khớp!");
            txtReMk.setBackground(Color.white);
            lblErReMk.setVisible(true);
            btnDoiMk.setEnabled(false);
        } else {
            lblErReMk.setVisible(false);
            txtReMk.setBackground(Color.green);
            btnDoiMk.setEnabled(true);
        }
    }
    public void resetForm(){
        txtMkC.setText("");
        txtMkM.setText("");
        txtReMk.setText("");
        btnDoiMk.setEnabled(false);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblMaNv = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnDoiMk = new javax.swing.JButton();
        txtMkC = new javax.swing.JPasswordField();
        txtMkM = new javax.swing.JPasswordField();
        txtReMk = new javax.swing.JPasswordField();
        lblErMkC = new javax.swing.JLabel();
        lblErMkM = new javax.swing.JLabel();
        lblErReMk = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Nhập mật khẩu cũ");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setText("ĐỔI MẬT KHẨU");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Mã nhân viên");

        lblMaNv.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblMaNv.setText("jLabel4");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Mật khẩu mới");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Nhập lại mật khẩu mới");

        btnDoiMk.setBackground(new java.awt.Color(51, 255, 0));
        btnDoiMk.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDoiMk.setText("Đổi mật khẩu");
        btnDoiMk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiMkActionPerformed(evt);
            }
        });

        txtMkC.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMkC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMkCKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMkCKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMkCKeyTyped(evt);
            }
        });

        txtMkM.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMkM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMkMKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMkMKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMkMKeyTyped(evt);
            }
        });

        txtReMk.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtReMk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtReMkKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtReMkKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtReMkKeyTyped(evt);
            }
        });

        lblErMkC.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblErMkC.setForeground(new java.awt.Color(255, 0, 0));
        lblErMkC.setText("jLabel6");

        lblErMkM.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblErMkM.setForeground(new java.awt.Color(255, 0, 0));
        lblErMkM.setText("jLabel7");

        lblErReMk.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblErReMk.setForeground(new java.awt.Color(255, 0, 0));
        lblErReMk.setText("jLabel8");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(lblMaNv)
                    .addComponent(jLabel1)
                    .addComponent(lblErMkC)
                    .addComponent(txtMkC, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(162, 162, 162)
                            .addComponent(btnDoiMk))
                        .addComponent(lblErReMk, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(txtMkM)
                            .addComponent(txtReMk, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblErMkM))
                .addContainerGap(76, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(lblMaNv)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMkC, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(lblErMkC))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMkM, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblErMkM)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtReMk, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(lblErReMk)))
                .addGap(21, 21, 21)
                .addComponent(btnDoiMk, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(255, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtMkCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMkCKeyPressed
        checkMkC();
    }//GEN-LAST:event_txtMkCKeyPressed

    private void txtMkCKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMkCKeyReleased
        checkMkC();
    }//GEN-LAST:event_txtMkCKeyReleased

    private void txtMkCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMkCKeyTyped
        checkMkC();
    }//GEN-LAST:event_txtMkCKeyTyped

    private void txtMkMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMkMKeyPressed
        checkMKM();
    }//GEN-LAST:event_txtMkMKeyPressed

    private void txtMkMKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMkMKeyReleased
        checkMKM();
    }//GEN-LAST:event_txtMkMKeyReleased

    private void txtMkMKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMkMKeyTyped
        checkMKM();
    }//GEN-LAST:event_txtMkMKeyTyped

    private void txtReMkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReMkKeyPressed
        checkMKM();
    }//GEN-LAST:event_txtReMkKeyPressed

    private void txtReMkKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReMkKeyReleased
        checkMKM();
    }//GEN-LAST:event_txtReMkKeyReleased

    private void txtReMkKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReMkKeyTyped
        checkMKM();
    }//GEN-LAST:event_txtReMkKeyTyped

    private void btnDoiMkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiMkActionPerformed
       String mkM = new String(txtReMk.getPassword()).trim();
       NhanVien nv = service.selectByID(Auth.nv.getMaNV());
       nv.setMatKhau(mkM);
       if(service.update(nv, Auth.nv.getMaNV()) != 0){
           MsgBox.alert(this, "Đổi mật khẩu thành công!");
           resetForm();
       }else{
           MsgBox.alert(this, "Đổi mật khẩu không thành công!");
       }
    }//GEN-LAST:event_btnDoiMkActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDoiMk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lblErMkC;
    private javax.swing.JLabel lblErMkM;
    private javax.swing.JLabel lblErReMk;
    private javax.swing.JLabel lblMaNv;
    private javax.swing.JPasswordField txtMkC;
    private javax.swing.JPasswordField txtMkM;
    private javax.swing.JPasswordField txtReMk;
    // End of variables declaration//GEN-END:variables
}
