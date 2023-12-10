/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.raven.form;

import com.fstore.untils.MsgBox;
import com.fstore.untils.XuatExcelFromTbl;
import com.fstore.model.DanhMuc;
import com.fstore.model.SanPham;
import com.fstore.model.SanPhamChiTiet;
import com.fstore.service.DanhMuc_Service;
import com.fstore.service.SanPhamChiTiet_Service;
import com.fstore.service.SanPham_Service;
import com.ui.main.Main;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class SanPham_Panel extends javax.swing.JPanel {

    private SanPham_Service sps = new SanPham_Service();
    private DanhMuc_Service dms = new DanhMuc_Service();
    private SanPhamChiTiet_Service spct_Service = new SanPhamChiTiet_Service();
    private int search = 0;
    private int id_sp = -1;
   

    public SanPham_Panel() {
        initComponents();
        this.setHidden();
        this.loadTable(sps.selectAll());
        this.fillCboDanhMuc();
      
    }

    public void setHidden() {
        this.txtTenLoaiSP.setVisible(false);
        lblTenLoaiSP.setVisible(false);
        this.lblEr.setVisible(false);
        this.btnThemL.setVisible(false);
        this.btnSuaL.setVisible(false);
        btnThemCTSP.setEnabled(false);

    }

    public void loadTable(List<SanPham> list) {
        DefaultTableModel tblMD = (DefaultTableModel) tblSanPham.getModel();
        tblMD.setRowCount(0);
        for (SanPham sp : list) {
            tblMD.addRow(new Object[]{
                sp.getID_SanPham(),
                sp.getTenSP(),
                sp.getDm().getTenDanhMuc(),
                sp.getMoTa(),
                sp.getTrangThai() == 1 ? "Đang bán" : "Ngừng bán"});
        }
    }

  

    public SanPham getForm() {
        String tenDm = (DanhMuc) cboDanhMuc.getSelectedItem() + "";
        int idDm = 0;
        List<DanhMuc> list_Dm = dms.selectAll();
        String tenSP = txtTenSP.getText().trim();
        String moTa = txtMoTa.getText().trim();
        StringBuffer er = new StringBuffer();
        int tt = 1;
        if (rdoHD.isSelected()) {
            tt = 1;
        } else if (rdoKHD.isSelected()) {
            tt = 0;
        }

        if (tenSP.isEmpty()) {
            er.append("Tên sản phẩm không được bỏ trống!\n");
        }
        if (moTa.isEmpty()) {
            er.append("Mô tả khồng được bỏ trống!\n");
        }
        for (DanhMuc dm : list_Dm) {
            if (dm.getTenDanhMuc().equals(tenDm)) {
                idDm = dm.getID_DanhMuc();
            }
        }
        if (er.length() > 0) {
            MsgBox.alert(this, er.toString());
            return null;
        }

        return new SanPham(tt, tenSP, moTa, new DanhMuc(idDm, 1, tenDm));
    }

    public void addSanPham() {
        SanPham sp = this.getForm();
        if (sp == null) {
            return;
        }
        List<SanPham> list = sps.selectAll();
        for (SanPham sanPham : list) {
            if (sanPham.getTenSP().equalsIgnoreCase(sp.getTenSP())) {
                MsgBox.alert(this, "Sản phẩm đã tồn tại!");
                return;

            }
        }
        if (MsgBox.confirm(this, "Bạn có chắc chắn muốn thêm?")) {
            if (sps.insert(sp) != 0) {
                MsgBox.alert(this, "Thêm thành công!");
                this.loadTable(sps.selectAll());
                clearForm();
            } else {
                MsgBox.alert(this, "Thêm không thành công!");
            }
        }
    }

    public DanhMuc getDanhMuc() {
        DanhMuc dm = new DanhMuc();
        dm.setTenDanhMuc(txtTenLoaiSP.getText());
        dm.setTrangThai(1);
        if (txtTenLoaiSP.getText().isEmpty()) {
            MsgBox.alert(this, "Vui lòng điền danh mục!");
            return null;
        }
        return dm;
    }

    public void setFormDanhMuc() {
        DanhMuc dm = (DanhMuc) cboDanhMuc.getSelectedItem();
        if (dm == null) {
            return;
        }
        txtTenLoaiSP.setText(dm.getTenDanhMuc());
    }

    public void insertDanhMuc() {
        DanhMuc dm = getDanhMuc();
        if (dm == null) {
            return;
        }
        List<DanhMuc> list = dms.selectAll();
        for (DanhMuc danhMuc : list) {
            if (danhMuc.getTenDanhMuc().equalsIgnoreCase(dm.getTenDanhMuc())) {
                MsgBox.alert(this, "Danh mục đã tồn tại");
                txtTenLoaiSP.setText("");
                return;
            }
        }
        if (dms.insert(dm) != 0) {
            MsgBox.alert(this, "Thêm thành công!");
            txtTenLoaiSP.setText("");
        } else {
            return;
        }
        txtTenLoaiSP.setVisible(false);
        lblTenLoaiSP.setVisible(false);
        //lblEr.setVisible(false);
        btnSuaL.setVisible(false);
        btnThemL.setVisible(false);

        fillCboDanhMuc();

    }

    public void updateDanhMuc() {
        DanhMuc dmu = getDanhMuc();
        DanhMuc dm = (DanhMuc) cboDanhMuc.getSelectedItem();
        if (dm == null) {
            return;
        }
        List<DanhMuc> list = dms.selectAll();
        for (DanhMuc danhMuc : list) {
            if (danhMuc.getTenDanhMuc().equalsIgnoreCase(dm.getTenDanhMuc())) {
                MsgBox.alert(this, "Danh mục đã tồn tại");
                txtTenLoaiSP.setText("");
                return;
            }
        }
        if (dms.update(dmu, dm.getID_DanhMuc()) != 0) {
            MsgBox.alert(this, "Sửa thành công!");
            txtTenLoaiSP.setText("");
        } else {
            return;
        }
        txtTenLoaiSP.setVisible(false);
        lblTenLoaiSP.setVisible(false);
        //lblEr.setVisible(false);
        btnSuaL.setVisible(false);
        btnThemL.setVisible(false);

        fillCboDanhMuc();
    }

    public void updateSanPham() {
        SanPham sp = this.getForm();

        int row = tblSanPham.getSelectedRow();
        int id = Integer.parseInt(tblSanPham.getValueAt(row, 0).toString());
        List<SanPhamChiTiet> list = spct_Service.selectByID_SP(id);
        SanPham spc = sps.selectByID(id);
        sp.setID_SanPham(id);
        if (sp == null) {
            return;
        }

        if (sp.getTenSP().equalsIgnoreCase(spc.getTenSP())) {
            if (MsgBox.confirm(this, "Bạn có chắc chắn muốn sửa?")) {
                if (sps.update(sp, sp.getID_SanPham()) != 0) {
                    MsgBox.alert(this, "Cập nhật thành công!");
                    if (sp.getTrangThai() == 0) {

                        for (SanPhamChiTiet spct : list) {
                            spct.setTrangThai(0);
                            spct_Service.update(spct, spct.getId_SanPhamChiTiet());
                        }
                    } else {
                        for (SanPhamChiTiet spct : list) {
                            spct.setTrangThai(1);
                            spct_Service.update(spct, spct.getId_SanPhamChiTiet());
                        }

                    }
                    this.loadTable(sps.selectAll());
                    clearForm();
                } else {
                    MsgBox.alert(this, "Cập nhật không thành công!");
                }
            }
        } else {
            List<SanPham> list_SP = sps.selectAll();
            for (SanPham sanPham : list_SP) {
                if (sanPham.getTenSP().equalsIgnoreCase(sp.getTenSP())) {
                    MsgBox.alert(this, "Sản phẩm đã tồn tại!");
                    return;

                }
            }
            if (MsgBox.confirm(this, "Bạn có chắc chắn muốn sửa?")) {
                if (sps.update(sp, sp.getID_SanPham()) != 0) {
                    MsgBox.alert(this, "Cập nhật thành công!");
                    if (sp.getTrangThai() == 0) {
                        for (SanPhamChiTiet spct : list) {
                            spct.setTrangThai(0);
                            spct_Service.update(spct, spct.getId_SanPhamChiTiet());
                        }
                    } else {
                        for (SanPhamChiTiet spct : list) {
                            spct.setTrangThai(1);
                            spct_Service.update(spct, spct.getId_SanPhamChiTiet());
                        }

                    }
                    this.loadTable(sps.selectAll());
                    clearForm();
                } else {
                    MsgBox.alert(this, "Cập nhật không thành công!");
                }
            }

        }

    }

    public void deleteSanPham() {
        int row = tblSanPham.getSelectedRow();
        int id = Integer.parseInt(tblSanPham.getValueAt(row, 0).toString());
        if (MsgBox.confirm(this, "Bạn có chắc chắn muốn xóa?")) {
            if (sps.delete(id) != 0) {
                MsgBox.alert(this, "Xóa thành cồng!");
                this.loadTable(sps.selectAll());
            } else {
                MsgBox.alert(this, "Xóa không thành cồng!\nĐã set trạng thái về ngừng bán!");
                SanPham sp = sps.selectByID(id);
                System.out.println(sp.getTenSP());
                sp.setTrangThai(0);
                if (sps.updateTrangThai(sp, id) != 0) {
                    System.out.println("Thành công!");
                    List<SanPhamChiTiet> list = spct_Service.selectByID_SP(sp.getID_SanPham());
                    for (SanPhamChiTiet spct : list) {
                        spct.setTrangThai(0);
                        spct_Service.update(spct, spct.getId_SanPhamChiTiet());
                    }
                }

                this.loadTable(sps.selectAll());
            }
        }
        clearForm();

    }

    public void fillCboDanhMuc() {
        cboDanhMuc.removeAllItems();
        DefaultComboBoxModel cboMD = (DefaultComboBoxModel) cboDanhMuc.getModel();
        for (DanhMuc dm : dms.selectAll()) {
            if (dm.getTrangThai() == 1) {
                cboMD.addElement(dm);
            }

        }
    }

    public void setForm(SanPham sp) {
        txtTenSP.setText(sp.getTenSP());
        txtMoTa.setText(sp.getMoTa());
        cboDanhMuc.getModel().setSelectedItem(sp.getDm());
        if (sp.getTrangThai() == 1) {
            rdoHD.setSelected(true);
        } else {
            rdoKHD.setSelected(true);
        }
    }

    public void search() {
        List<SanPham> list = new ArrayList<>();
        if (search == 0) {
            String idT = txtSearch.getText().trim();
            int id = 0;
            try {
                id = Integer.parseInt(idT);
            } catch (Exception e) {
            }
            SanPham sp = sps.selectByID(id);
            if (sp != null) {
                list.add(sp);
            }
        } else if (search == 1) {
            String ten = txtSearch.getText().trim();
            list = sps.selectByName(ten);
        }
        loadTable(list);
    }

    public void clearForm() {
        txtTenSP.setText("");
        txtMoTa.setText("");
        rdoHD.setSelected(true);
        cboDanhMuc.setSelectedIndex(0);
        tblSanPham.clearSelection();
        btnThemCTSP.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnAddLSP = new javax.swing.JButton();
        cboDanhMuc = new javax.swing.JComboBox<>();
        lblTenLoaiSP = new javax.swing.JLabel();
        txtTenLoaiSP = new javax.swing.JTextField();
        lblEr = new javax.swing.JLabel();
        btnThemL = new javax.swing.JButton();
        btnSuaL = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        rdoHD = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        rdoKHD = new javax.swing.JRadioButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cboSearch = new javax.swing.JComboBox<>();
        txtSearch = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        btnThemCTSP = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setText("Loại sản phẩm");

        btnAddLSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/add.png"))); // NOI18N
        btnAddLSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddLSPActionPerformed(evt);
            }
        });

        cboDanhMuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboDanhMuc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboDanhMucItemStateChanged(evt);
            }
        });

        lblTenLoaiSP.setText("Tên loại sản phẩm");

        lblEr.setForeground(new java.awt.Color(255, 0, 0));
        lblEr.setText("jLabel4");

        btnThemL.setBackground(new java.awt.Color(51, 255, 51));
        btnThemL.setText("Thêm");
        btnThemL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemLActionPerformed(evt);
            }
        });

        btnSuaL.setBackground(new java.awt.Color(255, 204, 0));
        btnSuaL.setText("Sửa");
        btnSuaL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaLActionPerformed(evt);
            }
        });

        jLabel5.setText("Tên sản phẩm");

        jLabel6.setText("Mô tả");

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane1.setViewportView(txtMoTa);

        buttonGroup1.add(rdoHD);
        rdoHD.setSelected(true);
        rdoHD.setText("Đang hoạt động");

        jLabel7.setText("Trạng Thái");

        buttonGroup1.add(rdoKHD);
        rdoKHD.setText("Không hoạt động");

        btnThem.setBackground(new java.awt.Color(102, 255, 0));
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/add.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(204, 153, 0));
        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/edit.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnReset.setBackground(new java.awt.Color(0, 255, 255));
        btnReset.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnReset.setForeground(new java.awt.Color(255, 255, 255));
        btnReset.setText("Tạo mới");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(255, 51, 51));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2)
                        .addComponent(lblTenLoaiSP)
                        .addComponent(lblEr)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(btnThemL)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnSuaL))
                        .addComponent(jLabel5)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(cboDanhMuc, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnAddLSP, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel6)
                        .addComponent(txtTenLoaiSP)
                        .addComponent(txtTenSP)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rdoHD)
                        .addGap(48, 48, 48)
                        .addComponent(rdoKHD))
                    .addComponent(jLabel7)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnThem, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(8, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAddLSP, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboDanhMuc))
                .addGap(18, 18, 18)
                .addComponent(lblTenLoaiSP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTenLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblEr)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemL)
                    .addComponent(btnSuaL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoHD)
                    .addComponent(rdoKHD))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        tblSanPham.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Sản phẩm", "Tên sản phẩm", "Loại sản phẩm", "Mô tả", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.setRowHeight(25);
        tblSanPham.setSelectionBackground(new java.awt.Color(0, 0, 0));
        tblSanPham.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSanPham);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitle.setText("SẢN PHẨM");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Tìm kiếm theo");

        cboSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã", "Tên sản phẩm" }));
        cboSearch.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboSearchItemStateChanged(evt);
            }
        });
        cboSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSearchActionPerformed(evt);
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

        jButton2.setBackground(new java.awt.Color(0, 204, 204));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setText("Xuất File");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnThemCTSP.setBackground(new java.awt.Color(0, 255, 0));
        btnThemCTSP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThemCTSP.setText("Thêm chi tiết sản phẩm");
        btnThemCTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemCTSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitle)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(cboSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 235, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(btnThemCTSP)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThemCTSP, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtSearch, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(cboSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane2)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddLSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddLSPActionPerformed
        if (txtTenLoaiSP.isVisible()) {
            txtTenLoaiSP.setVisible(false);
            lblTenLoaiSP.setVisible(false);
            //lblEr.setVisible(false);
            btnSuaL.setVisible(false);
            btnThemL.setVisible(false);

        } else {
            txtTenLoaiSP.setVisible(true);
            lblTenLoaiSP.setVisible(true);
            //lblEr.setVisible(true);
            btnSuaL.setVisible(true);
            btnThemL.setVisible(true);

        }
    }//GEN-LAST:event_btnAddLSPActionPerformed

    private void cboSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboSearchActionPerformed

    private void cboSearchItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboSearchItemStateChanged
        search = cboSearch.getSelectedIndex();
    }//GEN-LAST:event_cboSearchItemStateChanged

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        addSanPham();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        updateSanPham();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        int row = tblSanPham.getSelectedRow();
        id_sp = Integer.parseInt(tblSanPham.getValueAt(row, 0).toString());

        SanPham sp = sps.selectByID(id_sp);
        setForm(sp);
        if (row >= 0) {
            btnThemCTSP.setEnabled(true);
        } else {
            btnThemCTSP.setEnabled(false);
        }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnThemCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemCTSPActionPerformed
        if (id_sp != -1) {
            new ThongTinSanPham_Dialog(new Main(), true, id_sp).setVisible(true);
        }
    }//GEN-LAST:event_btnThemCTSPActionPerformed

    private void btnThemLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemLActionPerformed
        insertDanhMuc();
    }//GEN-LAST:event_btnThemLActionPerformed

    private void cboDanhMucItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboDanhMucItemStateChanged
        setFormDanhMuc();
    }//GEN-LAST:event_cboDanhMucItemStateChanged

    private void btnSuaLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaLActionPerformed
        updateDanhMuc();
    }//GEN-LAST:event_btnSuaLActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            XuatExcelFromTbl excelFromTbl = new XuatExcelFromTbl();
            excelFromTbl.exportExcel((DefaultTableModel) tblSanPham.getModel());
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
        search();
    }//GEN-LAST:event_txtSearchKeyPressed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        search();
    }//GEN-LAST:event_txtSearchKeyReleased

    private void txtSearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyTyped
        search();
    }//GEN-LAST:event_txtSearchKeyTyped

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        clearForm();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        deleteSanPham();
    }//GEN-LAST:event_btnXoaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddLSP;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSuaL;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemCTSP;
    private javax.swing.JButton btnThemL;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboDanhMuc;
    private javax.swing.JComboBox<String> cboSearch;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblEr;
    private javax.swing.JLabel lblTenLoaiSP;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JRadioButton rdoHD;
    private javax.swing.JRadioButton rdoKHD;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTenLoaiSP;
    private javax.swing.JTextField txtTenSP;
    // End of variables declaration//GEN-END:variables
}
