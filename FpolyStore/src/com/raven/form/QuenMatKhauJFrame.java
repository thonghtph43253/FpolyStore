/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.raven.form;

import com.fstore.model.NhanVien;
import com.fstore.service.NhanVien_Service;
import com.fstore.untils.MsgBox;
import com.ui.main.Main;
import java.awt.Color;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
/**
 *
 * @author Admin
 */
public class QuenMatKhauJFrame extends javax.swing.JFrame {

   private NhanVien_Service service = new NhanVien_Service();
   private int ma;
   Thread t;
    public QuenMatKhauJFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.black);
        this.setHiden();
    }

    public void setHiden(){
        lblErEm.setVisible(false);
        lblErMa.setVisible(false);
        lblErReMk.setVisible(false);
        lblErmk.setVisible(false);
        btnDoiMK.setVisible(false);
        txtMk.setEnabled(false);
        txtMa.setEnabled(false);
        txtReMk.setEnabled(false);
        lblTime.setVisible(false);
    }
    public boolean checkEmail(String email){
        List<NhanVien> list = service.selectAll();
        for (NhanVien nv : list) {
            if((nv.getEmail().trim()).equals(email)){
                return true;
            }
        }
        return false;
    }
    public void layMa(){
        try {
            Random rd = new Random();
            ma = rd.nextInt(99999999);
            String host = "smtp.gmail.com";
            String  user = "fpolystore2105@gmail.com";
            String mk  = "jrvc xmbk msgh ybgj";
            String denEmail = txtEmail.getText();
            String tieuDe = "Mã code để thay đổi mật khẩu";
            String noiDung = "Mã code để thay đổi mật khẩu:"+ ma;
            boolean ssDebug = false;
            Properties pros = System.getProperties();
            pros.put("mail.smtp.starttls.enale", "true");
            pros.put("mail.smtp.starttls.required", "true");
            pros.put("mail.smtp.host", host);
            pros.put("mail.smtp.port", "587");
            pros.put("mail.smtp.auth", "true");
            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailss = Session.getDefaultInstance(pros, null);
            mailss.setDebug(ssDebug);
            Message msg = new MimeMessage(mailss);
            msg.setFrom(new InternetAddress(user));
            InternetAddress[] addr = {new InternetAddress(denEmail)};
            msg.setRecipients(Message.RecipientType.TO, addr);
            msg.setSubject(tieuDe);
            msg.setText(noiDung);
            Transport transport = mailss.getTransport("smtp");
            transport.connect(host, user, mk);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            MsgBox.alert(this, "Mã code đã đươc gửi đến Email!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void checkMa(){
        String code = txtMa.getText().trim();
        String code2 = String.valueOf(ma);
        if(code.equals(code2)){
            lblErMa.setText("Mã đã khớp, vui lòng nhập mât khẩu mới!");
            lblErMa.setForeground(Color.green);
            lblErMa.setVisible(true);
            txtMk.setEnabled(true);
            txtReMk.setEnabled(true);
            btnDoiMK.setVisible(true);
            btnDoiMK.setEnabled(false);
        }else{
            lblErMa.setText("Mã đã không khớp, vui lòng nhập lại mã!");
            lblErMa.setForeground(Color.red);
            lblErMa.setVisible(true);
             txtMk.setEnabled(false);
            txtReMk.setEnabled(false);
            btnDoiMK.setVisible(false);
        }
    }
    public void demNguoc(){
        t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 60; i >= 0; i--) {
                    lblTime.setText(i+"s");
                   try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                txtMa.setEnabled(false);
                btnLayMa.setEnabled(true);
            }
        });
        t.start();
    }
    
    public void doiMatKhau(){
        String matKhau = new String(txtMk.getPassword()).trim();
        String reMatKhau = new String(txtReMk.getPassword()).trim();
        if(matKhau.isEmpty()){
            lblErmk.setText("Vui lòng nhập mât khẩu mới!");
            txtMk.setBackground(Color.white);
            lblErmk.setVisible(true);
        }else if(!matKhau.matches("^(?=.*[0-9]).{8,}$")){
            lblErmk.setText("Mật khẩu phải bào gồm chữ số và có ít nhất 8 kĩ tự!");
            txtMk.setBackground(Color.white);
            lblErmk.setVisible(true);
        }else{
            lblErmk.setVisible(false);
            txtMk.setBackground(Color.green);
        }
        if(!reMatKhau.equals(matKhau)){
            lblErReMk.setText("Nhập lại mật khẩu không khớp!");
            txtReMk.setBackground(Color.white);
            lblErReMk.setVisible(true);
            btnDoiMK.setEnabled(false);
        }else{
            lblErReMk.setVisible(false);
            txtReMk.setBackground(Color.green);
            btnDoiMK.setEnabled(true);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnLayMa = new javax.swing.JButton();
        txtMa = new javax.swing.JTextField();
        lblErEm = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtMk = new javax.swing.JPasswordField();
        txtReMk = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        lblErmk = new javax.swing.JLabel();
        lblErReMk = new javax.swing.JLabel();
        lblErMa = new javax.swing.JLabel();
        btnDoiMK = new javax.swing.JButton();
        btnBackDn = new javax.swing.JButton();
        lblTime = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("QUÊN MẬT KHẨU");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nhập email của bạn ");

        btnLayMa.setBackground(new java.awt.Color(51, 255, 51));
        btnLayMa.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnLayMa.setText("Lấy mã");
        btnLayMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLayMaActionPerformed(evt);
            }
        });

        txtMa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMaKeyTyped(evt);
            }
        });

        lblErEm.setForeground(new java.awt.Color(204, 0, 0));
        lblErEm.setText("jLabel6");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Mật khẩu mới");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nhâp lại mật khẩu");

        txtMk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMkKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMkKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMkKeyTyped(evt);
            }
        });

        txtReMk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtReMkActionPerformed(evt);
            }
        });
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

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Nhập mã xác thực");

        lblErmk.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblErmk.setForeground(new java.awt.Color(255, 0, 0));
        lblErmk.setText("jLabel8");

        lblErReMk.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblErReMk.setForeground(new java.awt.Color(255, 0, 0));
        lblErReMk.setText("jLabel9");

        lblErMa.setForeground(new java.awt.Color(204, 0, 0));
        lblErMa.setText("jLabel10");

        btnDoiMK.setBackground(new java.awt.Color(51, 255, 51));
        btnDoiMK.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDoiMK.setForeground(new java.awt.Color(255, 255, 255));
        btnDoiMK.setText("Đổi mật khẩu");
        btnDoiMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiMKActionPerformed(evt);
            }
        });

        btnBackDn.setBackground(new java.awt.Color(255, 204, 0));
        btnBackDn.setText("Trở lại form đăng nhập");
        btnBackDn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackDnActionPerformed(evt);
            }
        });

        lblTime.setForeground(new java.awt.Color(204, 204, 0));
        lblTime.setText("jLabel11");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(btnBackDn, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDoiMK))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(224, 224, 224)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(txtMk, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnLayMa))
                                .addComponent(lblErEm)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(lblTime))
                                .addComponent(jLabel7)
                                .addComponent(lblErMa))
                            .addGap(36, 36, 36)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblErReMk)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtReMk)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblErmk)
                                            .addComponent(jLabel4))
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addComponent(jLabel1))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLayMa))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblErEm))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtMk, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblErmk)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel4))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTime))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtReMk, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblErMa)
                    .addComponent(lblErReMk))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(btnBackDn, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnDoiMK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackDnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackDnActionPerformed
        this.dispose();
        new Main();
    }//GEN-LAST:event_btnBackDnActionPerformed

    private void txtReMkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtReMkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtReMkActionPerformed

    private void btnLayMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLayMaActionPerformed
        String email = txtEmail.getText().trim();
        if(email.isEmpty()){
            lblErEm.setText("Email không được bỏ trống");
            lblErEm.setVisible(true);
            
        }else if(!email.matches("\\w+@\\w+(\\.\\w+){1,2}")){
             lblErEm.setText("Email không đúng định dạng!");
            lblErEm.setVisible(true);
           
        }else if(!checkEmail(email)){
            lblErEm.setText("Nhân viên không tồn tại!");
            lblErEm.setVisible(true);
           
        }else{
            lblErEm.setVisible(false);
            btnLayMa.setEnabled(false);
            txtMa.setEnabled(true);
            lblTime.setVisible(true);
            layMa();
            demNguoc();
        }
        
        
    }//GEN-LAST:event_btnLayMaActionPerformed

    private void txtMaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaKeyPressed
       checkMa();
    }//GEN-LAST:event_txtMaKeyPressed

    private void txtMaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaKeyReleased
        checkMa();
    }//GEN-LAST:event_txtMaKeyReleased

    private void txtMaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaKeyTyped
       checkMa();
    }//GEN-LAST:event_txtMaKeyTyped

    private void txtMkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMkKeyPressed
        doiMatKhau();
    }//GEN-LAST:event_txtMkKeyPressed

    private void txtMkKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMkKeyReleased
      doiMatKhau();
    }//GEN-LAST:event_txtMkKeyReleased

    private void txtMkKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMkKeyTyped
      doiMatKhau();
    }//GEN-LAST:event_txtMkKeyTyped

    private void txtReMkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReMkKeyPressed
       doiMatKhau();
    }//GEN-LAST:event_txtReMkKeyPressed

    private void txtReMkKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReMkKeyReleased
        doiMatKhau();
    }//GEN-LAST:event_txtReMkKeyReleased

    private void txtReMkKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtReMkKeyTyped
       doiMatKhau();
    }//GEN-LAST:event_txtReMkKeyTyped

    private void btnDoiMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiMKActionPerformed
       String email = txtEmail.getText().trim();
       String matKhauMoi = new String(txtReMk.getPassword()).trim();
       List<NhanVien> list = service.selectAll();
        for (NhanVien nv  : list) {
            if(email.equals(nv.getEmail())){
                nv.setMatKhau(matKhauMoi);
              if( service.update(nv, nv.getMaNV())!= 0){
                  MsgBox.alert(this, "Đã thay đổi mật khẩu, vui lòng đăng nhập!");
                  this.dispose();
                  new Main();
              }else{
                   MsgBox.alert(this, "Thay đổi mật khẩu khồng thành cồng!");
              }
            }
        }
        
    }//GEN-LAST:event_btnDoiMKActionPerformed

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
            java.util.logging.Logger.getLogger(QuenMatKhauJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuenMatKhauJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuenMatKhauJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuenMatKhauJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuenMatKhauJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBackDn;
    private javax.swing.JButton btnDoiMK;
    private javax.swing.JButton btnLayMa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblErEm;
    private javax.swing.JLabel lblErMa;
    private javax.swing.JLabel lblErReMk;
    private javax.swing.JLabel lblErmk;
    private javax.swing.JLabel lblTime;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMa;
    private javax.swing.JPasswordField txtMk;
    private javax.swing.JPasswordField txtReMk;
    // End of variables declaration//GEN-END:variables
}
