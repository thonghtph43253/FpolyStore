/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.raven.form;

import com.fstore.untils.MsgBox;
import com.fstore.untils.XDate;
import com.fstore.untils.XImage;
import com.fstore.model.NhanVien;
import com.fstore.service.NhanVien_Service;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class ThemNhanVienJdialog extends javax.swing.JDialog {

    private NhanVien_Service nhanVien_Service = new NhanVien_Service();
    private String srcImg;
    private NhanVien_Panel nhanVien_Panel;
    private String maNV;
    private MainForm main = new MainForm();

    public ThemNhanVienJdialog(java.awt.Frame parent, boolean modal, NhanVien_Panel nhanVien_Panel) {
        super(parent, modal);
        initComponents();
        btnSua.setEnabled(false);
        this.nhanVien_Panel = nhanVien_Panel;
    }

    public ThemNhanVienJdialog(java.awt.Frame parent, boolean modal, String maNV, NhanVien_Panel nhanVien_Panel) {
        super(parent, modal);
        this.maNV = maNV;
        initComponents();
        btnThem.setEnabled(false);
        txtMaNV.setEditable(false);
        NhanVien nv = nhanVien_Service.selectByID(maNV);
        setForm(nv);
        this.nhanVien_Panel = nhanVien_Panel;
    }

    public NhanVien getForm(int check) {
        List<NhanVien> list = nhanVien_Service.selectAll();
        String maNVF = txtMaNV.getText();
        String tenNV = txtTenNV.getText();
        String sdt = txtSDT.getText();
        String email = txtEmail.getText();
        String matKhau = String.valueOf(txtPass.getPassword());
        String reMatKhau = String.valueOf(txtRePass.getPassword());
        String diaChi = txtDiaChi.getText();
        Date ngaySinhD = XDate.toDate(txtNgaySinh.getText(), "dd-MM-yyyy");
        String ngaySinh = XDate.toString(ngaySinhD, "dd-MM-yyyy");
        boolean checkMa = true;
        boolean checkSdt = true;
        boolean checkEmail = true;
        boolean gt = true;
        boolean vt = true;
        int tt = 1;
        StringBuilder er = new StringBuilder();
        if (rdoNam.isSelected()) {
            gt = true;
        } else if (rdoNu.isSelected()) {
            gt = false;
        }
        if (rdoDangLam.isSelected()) {
            tt = 1;
        } else if (rdoNghiLam.isSelected()) {
            tt = 0;
        }

        if (rdoAdmin.isSelected()) {
            vt = true;
        } else if (rdoNhanVien.isSelected()) {
            vt = false;
        }
        if (maNVF.isEmpty()) {
            er.append("Mã nhân viên không đươc bỏ trống!\n");
        }
        if (check == 1) {

            for (NhanVien nv : list) {
                if (maNVF.equalsIgnoreCase(nv.getMaNV())) {
                    checkMa = false;
                }
                if (sdt.equalsIgnoreCase(nv.getSDT())) {
                    checkSdt = false;
                }
                if (email.equalsIgnoreCase(nv.getEmail())) {
                    checkEmail = false;
                }
            }

            if (!checkEmail) {
                er.append("Trùng email!\n");
            }
            if (!checkMa) {
                er.append("Trùng mã nhân viên!\n");
            }

            if (!checkSdt) {
                er.append("Trùng số điên thoại!\n");
            }

        }
        if (!checkTuoi()) {
            er.append("Chưa đủ 18 tuổi!\n");
        }
        if (matKhau.isEmpty()) {
            er.append("Mât khẩu không đươc bỏ trống!\n");
        } else if (!matKhau.matches("^(?=.*[0-9]).{8,}$")) {
            er.append("Mật khẩu phải bào gồm chữ số và có ít nhất 8 kĩ tự!\n");
        }
        if (!reMatKhau.equals(matKhau)) {
            er.append("Mât khẩu nhập lại không khớp!\n");
        }
        if (diaChi.isEmpty()) {
            er.append("Địa chỉ không được bỏ trống!\n");
        }
        if (srcImg == null) {
            er.append("Vui lòng chọn ảnh nhân viên!\n");
        }
        if (email.isEmpty()) {
            er.append("Email không được bỏ trống!\n");
        } else if (!email.matches("\\w+@\\w+(\\.\\w+){1,2}")) {
            er.append("Email không đúng đinh dạng!\n");
        }
        if (sdt.isEmpty()) {
            er.append("Số điên thoại không được bỏ trống!\n");
        } else if (!sdt.matches("((0\\d{9})|(\\+84|84)\\d{9})")) {
            er.append("Số điên thoại không đúng định dạng!\n");
        }
        if (er.length() > 0) {
            MsgBox.alert(this, er.toString());
            return null;
        }
        return new NhanVien(maNVF, tenNV, diaChi, sdt, matKhau, email, srcImg, gt, vt, tt, ngaySinh);
    }

    public void setForm(NhanVien nv) {
        txtMaNV.setText(nv.getMaNV());
        txtTenNV.setText(nv.getTen());
        txtEmail.setText(nv.getEmail());
        txtNgaySinh.setText(nv.getNgaySinh());
        txtDiaChi.setText(nv.getDiaChi());
        txtPass.setText(nv.getMatKhau());
        txtRePass.setText(nv.getMatKhau());
        txtSDT.setText(nv.getSDT());
        if (nv.isVaiTro()) {
            rdoAdmin.setSelected(true);
        } else {
            rdoNhanVien.setSelected(true);
        }

        if (nv.isGioiTinh()) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }
        if (nv.getTrangThai() == 1) {
            rdoDangLam.setSelected(true);
        } else {
            rdoNghiLam.setSelected(true);
        }
        if (nv.getHinhAnh() == null) {
            lblHinhAnh.setIcon(null);
            lblHinhAnh.setToolTipText("");
        } else {
            int w = lblHinhAnh.getWidth();
            int h = lblHinhAnh.getHeight() - lblHinhAnh.getY();
            ImageIcon icon = XImage.read(nv.getHinhAnh());
            Image img = icon.getImage();
            srcImg = nv.getHinhAnh();
            lblHinhAnh.setIcon(new ImageIcon(img.getScaledInstance(w, h, 0)));
        }
    }

    public void insertNhanVien() {
        NhanVien nv = getForm(1);
        if (nv == null) {
            return;
        }
        if (nhanVien_Service.insert(nv) != 0) {
            MsgBox.alert(this, "Thêm nhân viên thành công!");
            this.nhanVien_Panel.fillTable(nhanVien_Service.selectAll());
            this.dispose();
        } else {
            MsgBox.alert(this, "Thêm nhân viên không thành công!");
        }
    }

    public boolean checkTuoi() {
        LocalDate today = LocalDate.now();
        LocalDate date = LocalDate.parse(txtNgaySinh.getText(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        int years = Period.between(date, today).getYears();
        if (years < 18) {
            return false;
        }
        return true;
    }

    public void updateNhanVien() {
        NhanVien nv = getForm(2);
        NhanVien nvck = nhanVien_Service.selectByID(maNV);
        List<NhanVien> listCheck = nhanVien_Service.selectAll();
        if (nv == null) {
            return;
        }

        if (nv.getSDT().equalsIgnoreCase(nvck.getSDT()) && nv.getEmail().equalsIgnoreCase(nvck.getEmail())) {
            if (nhanVien_Service.update(nv, maNV) != 0) {
                MsgBox.alert(this, "Cập nhật nhân viên thành công!");
                this.nhanVien_Panel.fillTable(nhanVien_Service.selectAll());
                 this.dispose();
            } else {
                MsgBox.alert(this, "Câp nhât thất bại");
            }
        }else if(!nv.getSDT().equalsIgnoreCase(nvck.getSDT()) && nv.getEmail().equalsIgnoreCase(nvck.getEmail())){
            for (NhanVien nhanVien : listCheck) {
                if(nv.getSDT().equalsIgnoreCase(nhanVien.getSDT())){
                    MsgBox.alert(this, "Số điện thoại đã tồn tại!");
                    return;
                }
            }
            if (nhanVien_Service.update(nv, maNV) != 0) {
                MsgBox.alert(this, "Cập nhật nhân viên thành công!");
                this.nhanVien_Panel.fillTable(nhanVien_Service.selectAll());
                this.dispose();
            } else {
                MsgBox.alert(this, "Câp nhât thất bại");
            }
        }else if(nv.getSDT().equalsIgnoreCase(nvck.getSDT()) && !nv.getEmail().equalsIgnoreCase(nvck.getEmail())){
            for (NhanVien nhanVien : listCheck) {
                if(nv.getEmail().equalsIgnoreCase(nhanVien.getEmail())){
                    MsgBox.alert(this, "Email đã tồn tại!");
                    return;
                }
            }
            if (nhanVien_Service.update(nv, maNV) != 0) {
                MsgBox.alert(this, "Cập nhật nhân viên thành công!");
                this.nhanVien_Panel.fillTable(nhanVien_Service.selectAll());
                this.dispose();
            } else {
                MsgBox.alert(this, "Câp nhât thất bại");
            }
        }else{
             for (NhanVien nhanVien : listCheck) {
                if(nv.getEmail().equalsIgnoreCase(nhanVien.getEmail())||nv.getSDT().equalsIgnoreCase(nhanVien.getSDT())){
                    MsgBox.alert(this, "Email || Số điện thoại đã tồn tại!");
                    return;
                }
            }
            if (nhanVien_Service.update(nv, maNV) != 0) {
                MsgBox.alert(this, "Cập nhật nhân viên thành công!");
                 this.dispose();
                this.nhanVien_Panel.fillTable(nhanVien_Service.selectAll());
            } else {
                MsgBox.alert(this, "Câp nhât thất bại");
            }
        }

    }
    public void resetForm(){
        NhanVien nv = new NhanVien();
        setForm(nv);
        btnThem.setEnabled(true);
        btnSua.setEnabled(false);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        dateChooser1 = new com.raven.datechooser.DateChooser();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTenNV = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtNgaySinh = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDiaChi = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        rdoAdmin = new javax.swing.JRadioButton();
        rdoNhanVien = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        rdoDangLam = new javax.swing.JRadioButton();
        rdoNghiLam = new javax.swing.JRadioButton();
        jLabel12 = new javax.swing.JLabel();
        lblHinhAnh = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        txtPass = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        txtRePass = new javax.swing.JPasswordField();

        dateChooser1.setTextRefernce(txtNgaySinh);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel2.setText("Mã nhân viên");

        txtMaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNVActionPerformed(evt);
            }
        });

        jLabel3.setText("Tên nhân viên");

        jLabel4.setText("Mật khẩu");

        jLabel5.setText("Số điện thoại");

        jLabel6.setText("Ngày sinh");

        jLabel7.setText("Email");

        jLabel8.setText("Địa chỉ");

        txtDiaChi.setColumns(20);
        txtDiaChi.setRows(5);
        jScrollPane1.setViewportView(txtDiaChi);

        jLabel9.setText("Giới tính");

        buttonGroup2.add(rdoNam);
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");

        buttonGroup2.add(rdoNu);
        rdoNu.setText("Nữ");

        jLabel10.setText("Vai trò");

        buttonGroup1.add(rdoAdmin);
        rdoAdmin.setSelected(true);
        rdoAdmin.setText("Chủ cửa hàng");

        buttonGroup1.add(rdoNhanVien);
        rdoNhanVien.setText("Nhân viên");

        jLabel11.setText("Trạng thái");

        buttonGroup3.add(rdoDangLam);
        rdoDangLam.setSelected(true);
        rdoDangLam.setText("Đang làm");

        buttonGroup3.add(rdoNghiLam);
        rdoNghiLam.setText("Nghỉ làm");

        jLabel12.setText("Hình Ảnh");

        lblHinhAnh.setText("+ ADD IMAGE");
        lblHinhAnh.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblHinhAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhAnhMouseClicked(evt);
            }
        });

        btnThem.setBackground(new java.awt.Color(51, 255, 0));
        btnThem.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(255, 255, 0));
        btnSua.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnReset.setBackground(new java.awt.Color(0, 255, 204));
        btnReset.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnReset.setText("Tạo mới");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        jLabel1.setText("Nhập lại mật khẩu");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtNgaySinh, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtMaNV, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtTenNV, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rdoNam)
                        .addGap(91, 91, 91)
                        .addComponent(rdoNu))
                    .addComponent(jLabel10)
                    .addComponent(jLabel9)
                    .addComponent(jLabel1)
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rdoAdmin)
                        .addGap(43, 43, 43)
                        .addComponent(rdoNhanVien))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(268, 268, 268)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(rdoDangLam)
                                        .addGap(43, 43, 43)
                                        .addComponent(rdoNghiLam))
                                    .addComponent(jLabel11)
                                    .addComponent(lblHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel8)
                            .addComponent(txtRePass, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(23, 23, 23))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(4, 4, 4)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoDangLam)
                            .addComponent(rdoNghiLam)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRePass, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rdoAdmin)
                                    .addComponent(rdoNhanVien))
                                .addGap(10, 10, 10)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdoNam)
                            .addComponent(rdoNu))))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNVActionPerformed

    private void lblHinhAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhAnhMouseClicked
        try {
            JFileChooser jc = new JFileChooser();
            jc.showDialog(this, "Chọn ảnh nhân viên!");
            File f = jc.getSelectedFile();
            if (f != null) {
                XImage.save(f);
                Image img = ImageIO.read(f);
                srcImg = f.getName();
                int w = lblHinhAnh.getWidth();
                int h = lblHinhAnh.getHeight();
                lblHinhAnh.setText("");
                lblHinhAnh.setIcon(new ImageIcon(img.getScaledInstance(w, h, 0)));
                lblHinhAnh.setToolTipText(f.getName());
            }

        } catch (IOException ex) {
            Logger.getLogger(ThongTinSanPham_Dialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lblHinhAnhMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        insertNhanVien();


    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        updateNhanVien();

       

    }//GEN-LAST:event_btnSuaActionPerformed

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
            java.util.logging.Logger.getLogger(ThemNhanVienJdialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThemNhanVienJdialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThemNhanVienJdialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThemNhanVienJdialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ThemNhanVienJdialog dialog = new ThemNhanVienJdialog(new javax.swing.JFrame(), true, null);
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
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private com.raven.datechooser.DateChooser dateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHinhAnh;
    private javax.swing.JRadioButton rdoAdmin;
    private javax.swing.JRadioButton rdoDangLam;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNghiLam;
    private javax.swing.JRadioButton rdoNhanVien;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTextArea txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JPasswordField txtRePass;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenNV;
    // End of variables declaration//GEN-END:variables
}
