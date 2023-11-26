package com.raven.form;

import com.fsore.untils.MsgBox;
import com.fsore.untils.XDate;
import com.fstore.model.Sale;
import com.fstore.model.Sale_ChiTiet;
import com.fstore.model.SanPhamChiTiet;
import com.fstore.service.Sale_CT_Service;
import com.fstore.service.Sale_Service;
import com.fstore.service.SanPhamChiTiet_Service;
import com.ui.main.Main;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class Sale_Panel extends javax.swing.JPanel {

    private List<Sale_ChiTiet> list = new ArrayList<>();
    private Sale_Service sale_Service = new Sale_Service();
    private Sale_CT_Service sale_CT_Service = new Sale_CT_Service();
    private SanPhamChiTiet_Service spct_Service = new SanPhamChiTiet_Service();
    private int id_Sale = 0;

    public Sale_Panel() {
        initComponents();
        init();
    }

    public void init() {
        setHidenBtn();
        fillTable(sale_Service.selectAll());
        setBtn();
        txtNgayBD.setEditable(false);
        txtThoiGianKT.setEditable(false);
    }

//    public void show() {
//        MsgBox.alert(this, "Số lượng sản phẩm trong list " + list.size());
//    }
    public void setBtn() {
        if (list.size() >= 0) {
            btnThem.setEnabled(true);

        } else {
            btnThem.setEnabled(false);

        }
    }

    public void fillTable(List<Sale> list) {
        DefaultTableModel tblMd = (DefaultTableModel) tblSale.getModel();
        tblMd.setRowCount(0);
        for (Sale sale : list) {
            Sale_ChiTiet sct = sale_CT_Service.selectByIDSale(sale.getId_Sale()).get(0);
            tblMd.addRow(new Object[]{
                sale.getId_Sale(),
                sale.getTenChienDich(),
                sct.getHinhThucGiam() == 0 ? "Theo tiền mặt" : "Theo phần trăm",
                sct.getGiaTriGiam(),
                sale.getThoiGianBD(),
                sale.getThoiGianKT(),
                sale.getTrangThai() == 0 ? "Không hoạt động" : "Đang hoạt động"
            });
        }
    }

    public void setForm(Sale s) {
        Sale_ChiTiet sct = sale_CT_Service.selectByIDSale(s.getId_Sale()).get(0);
        txtTenSale.setText(s.getTenChienDich());
        lblMaSale.setText(String.valueOf(s.getId_Sale()));
        txtNgayBD.setText(XDate.toString(s.getThoiGianBD(), "dd-MM-yyyy"));
        txtThoiGianKT.setText(XDate.toString(s.getThoiGianKT(), "dd-MM-yyyy"));
        if (sct != null) {
            cbbHTG.setSelectedIndex(sct.getHinhThucGiam());
            txtGiaTriGiam.setText(String.valueOf(sct.getGiaTriGiam()));
        }
        if (s.getTrangThai() == 1) {
            rdoHD.setSelected(true);
        } else {
            rdoKHD.setSelected(true);
        }

    }

    public void setHidenBtn() {
        btnReset.setEnabled(false);
        btnThem.setEnabled(false);
    }

    public void clearForm() {
        txtTenSale.setText("");
        txtNgayBD.setText("");
        txtThoiGianKT.setText("");
        lblMaSale.setText("");
        cbbHTG.setSelectedIndex(0);
        txtGiaTriGiam.setText("");
    }

    public Sale getSale() {
        Sale s = new Sale();
        String tenSale = txtTenSale.getText();

        String ngayBatDau = txtNgayBD.getText();
        String ngayKetThuc = txtThoiGianKT.getText();
        StringBuffer er = new StringBuffer();
        Date ngayBD = XDate.toDate(ngayBatDau, "dd-MM-yyyy");
        Date ngayKT = XDate.toDate(ngayKetThuc, "dd-MM-yyyy");
        if (ngayKT.compareTo(ngayBD) <= 0 ) {
            er.append("Ngày kết thúc phải lớn hơn này bắt đầu!\n");
        }
        if (ngayBatDau.isEmpty()) {
            er.append("Ngày bắt đầu không được bỏ trống!\n");
        }
        if (ngayKetThuc.isEmpty()) {
            er.append("Ngày kết thúc không được bỏ trống!\n");
        }
        if (tenSale.isEmpty()) {
            er.append("Tên sale không được bỏ trống!\n");
        }
        s.setTenChienDich(tenSale);
        s.setThoiGianBD(ngayBD);
        s.setThoiGianKT(ngayKT);

        int tt = 1;
        if (rdoHD.isSelected()) {
            tt = 1;
        } else if (rdoKHD.isSelected()) {
            tt = 0;
        }
        if(er.length()>0){
            MsgBox.alert(this, er.toString());
            return null;
        }
        s.setTrangThai(tt);
        return s;
    }

    public void addSale() {
        Sale s = getSale();
        if (s == null) {
            return;
        }
        int hinhThucGiam = cbbHTG.getSelectedIndex();
        String giaTriGiamT = txtGiaTriGiam.getText();
        double giaTriGiam = 0;
        if (giaTriGiamT.isEmpty()) {
            MsgBox.alert(this, "Giá trị giảm không được bỏ trống!");
            return;
        } else {
            try {
                giaTriGiam = Double.parseDouble(giaTriGiamT);
                if (giaTriGiam <= 0) {
                    MsgBox.alert(this, "Giá trị giảm phải lớn hơn 0!");
                    return;
                }
                if (hinhThucGiam == 1 && giaTriGiam > 100) {
                    MsgBox.alert(this, "Giá trị giảm phải trong khoảng 0-100%!");
                    return;
                }
            } catch (Exception e) {
                MsgBox.alert(this, "Giá trị giảm phải là số!");
                return;
            }
        }

        sale_Service.insert(s);
        for (int i = 0; i < list.size(); i++) {
            Sale_ChiTiet sct = list.get(i);
            SanPhamChiTiet spct = spct_Service.selectByID(sct.getId_SanPham_CT());
            if (hinhThucGiam == 0) {
                if (spct.getGia() <= giaTriGiam) {
                    MsgBox.alert(this, "Giá trị giảm cao hơn giá bán sản phẩm!");
                    return;
                }
            }

            sct.setHinhThucGiam(hinhThucGiam);
            sct.setGiaTriGiam(giaTriGiam);
            sct.setTrangThai(1);
            sale_CT_Service.insert(sct);

        }
        clearForm();
        list.clear();

    }

    public void updateSale() {
        Sale s = getSale();

        if (s == null) {
            return;
        }
        int hinhThucGiam = cbbHTG.getSelectedIndex();
        String giaTriGiamT = txtGiaTriGiam.getText();
        double giaTriGiam = 0;
        if (giaTriGiamT.isEmpty()) {
            MsgBox.alert(this, "Giá trị giảm không được bỏ trống!");
            return;
        } else {
            try {
                giaTriGiam = Double.parseDouble(giaTriGiamT);
                if (giaTriGiam <= 0) {
                    MsgBox.alert(this, "Giá trị giảm phải lớn hơn 0!");
                    return;
                }
                if (hinhThucGiam == 1 && giaTriGiam > 100) {
                    MsgBox.alert(this, "Giá trị giảm phải trong khoảng 0-100%!");
                    return;
                }
            } catch (Exception e) {
                MsgBox.alert(this, "Giá trị giảm phải là số!");
                return;
            }
        }

        sale_Service.update(s, id_Sale);

        List<Sale_ChiTiet> list_Service = sale_CT_Service.selectByIDSale(id_Sale);
        for (int i = 0; i < list_Service.size(); i++) {
            Sale_ChiTiet sct = list_Service.get(i);
            Sale_ChiTiet sct2 = getExistingDetail(list, sct);
            if (sct2 == null) {
                SanPhamChiTiet spct = spct_Service.selectByID(sct.getId_SanPham_CT());
                sct.setHinhThucGiam(hinhThucGiam);
                sct.setGiaTriGiam(giaTriGiam);
                sct.setTrangThai(0);
                if (hinhThucGiam == 0) {
                    if (spct.getGia() <= giaTriGiam) {
                        MsgBox.alert(this, "Giá trị giảm cao hơn giá bán sản phẩm!");
                        return;
                    }
                }
                sale_CT_Service.update(sct, sct.getId_Sale_CT());
            }

        }
        for (Sale_ChiTiet sct : list) {
            Sale_ChiTiet sct2 = getExistingDetail(list_Service, sct);
            if (sct2 != null) {
                SanPhamChiTiet spct = spct_Service.selectByID(sct.getId_SanPham_CT());
                sct.setHinhThucGiam(hinhThucGiam);
                sct.setGiaTriGiam(giaTriGiam);
                sct.setTrangThai(1);
                if (hinhThucGiam == 0) {
                    if (spct.getGia() <= giaTriGiam) {
                        MsgBox.alert(this, "Giá trị giảm cao hơn giá bán sản phẩm!");
                        return;
                    }
                }
                sale_CT_Service.update(sct, sct.getId_Sale_CT());
            } else {
                SanPhamChiTiet spct = spct_Service.selectByID(sct.getId_SanPham_CT());
                sct.setHinhThucGiam(hinhThucGiam);
                sct.setGiaTriGiam(giaTriGiam);
                if (hinhThucGiam == 0) {
                    if (spct.getGia() <= giaTriGiam) {
                        MsgBox.alert(this, "Giá trị giảm cao hơn giá bán sản phẩm!");
                        return;
                    }
                }
                sale_CT_Service.insert(sct);
            }
        }
        clearForm();
        list.clear();

    }

    private Sale_ChiTiet getExistingDetail(List<Sale_ChiTiet> existingDetails, Sale_ChiTiet target) {
        for (Sale_ChiTiet existingDetail : existingDetails) {
            if (existingDetail.getId_SanPham_CT() == (target.getId_SanPham_CT())) {
                //System.out.println("trùng"+existingDetail.getId_HoaDonCT());
                return existingDetail;
            }
        }
        return null; // Trả về null nếu không tìm thấy
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        dateChooserKT = new com.raven.datechooserr.DateChooser();
        dateChooserBD = new com.raven.datechooserr.DateChooser();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSale = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        cboTkSanPham2 = new javax.swing.JComboBox<>();
        txtTimKiemSp2 = new javax.swing.JTextField();
        btnTimSP2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jTextField7 = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblMaSale = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTenSale = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtNgayBD = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtThoiGianKT = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cbbHTG = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txtGiaTriGiam = new javax.swing.JTextField();
        lblDonVi = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        rdoHD = new javax.swing.JRadioButton();
        rdoKHD = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnChonNKT = new javax.swing.JButton();
        btnChonNBD = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        dateChooserKT.setForeground(new java.awt.Color(0, 204, 204));
        dateChooserKT.setTextRefernce(txtThoiGianKT);

        dateChooserBD.setForeground(new java.awt.Color(0, 255, 0));
        dateChooserBD.setTextRefernce(txtNgayBD);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách SALE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tblSale.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã sale", "Tên chương trình", "Hình thức giảm", "Mức giảm", "Thời gian BĐ", "Thời gian KT", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSale.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSaleMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblSale);

        jLabel14.setText("Tìm kiếm theo");

        cboTkSanPham2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã", "Tên" }));

        btnTimSP2.setText("Tìm");

        jLabel2.setText("Thời gian BĐ");

        jLabel15.setText("Thời gian KT");

        jLabel16.setText("Trạng thái");

        jRadioButton3.setText("Hoạt động");

        jRadioButton4.setText("Không hoạt động");

        jButton7.setText("Lọc");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Hủy lọc");

        jButton6.setText("|<<");

        jButton11.setText("<<");

        jButton12.setText(">>");

        jButton13.setText(">>|");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(cboTkSanPham2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTimKiemSp2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(btnTimSP2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jTextField6)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(jRadioButton3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jRadioButton4)
                                    .addGap(6, 6, 6))
                                .addComponent(jLabel16)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGap(104, 104, 104)
                                    .addComponent(jButton8)
                                    .addGap(18, 18, 18)
                                    .addComponent(jButton7))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 763, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton13)
                .addGap(239, 239, 239))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboTkSanPham2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTimKiemSp2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTimSP2))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton3)
                            .addComponent(jRadioButton4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton11)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12)
                    .addComponent(jButton13))
                .addGap(12, 12, 12))
        );

        jLabel3.setText("Mã sale");

        jLabel5.setText("Tên chương trình");

        jLabel7.setText("Thời gian bắt đầu");

        txtNgayBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgayBDActionPerformed(evt);
            }
        });

        jLabel8.setText("Thời gian kết thúc");

        jLabel6.setText("Hình thức giảm");

        cbbHTG.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Theo tiền mặt", "Theo phần trăm" }));
        cbbHTG.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbHTGItemStateChanged(evt);
            }
        });

        jLabel9.setText("Giá trị giảm");

        lblDonVi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDonVi.setForeground(new java.awt.Color(255, 0, 0));
        lblDonVi.setText("VNĐ");

        jLabel11.setText("Trạng thái");

        buttonGroup1.add(rdoHD);
        rdoHD.setSelected(true);
        rdoHD.setText("Hoạt động");

        buttonGroup1.add(rdoKHD);
        rdoKHD.setText("Không hoạt động");

        jButton1.setText("Sản phẩm áp dụng");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnReset.setText("Thêm mới");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnThem.setBackground(new java.awt.Color(51, 204, 0));
        btnThem.setText("Hoàn thành");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnChonNKT.setText("...");
        btnChonNKT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonNKTActionPerformed(evt);
            }
        });

        btnChonNBD.setText("...");
        btnChonNBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonNBDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(lblMaSale)
                    .addComponent(jLabel3)
                    .addComponent(txtTenSale, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtThoiGianKT, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(txtNgayBD))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel9)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(btnChonNBD)
                                .addGap(5, 5, 5)
                                .addComponent(cbbHTG, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(btnChonNKT)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtGiaTriGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblDonVi)))))
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdoKHD, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoHD)
                    .addComponent(jLabel11)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbbHTG, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnChonNBD, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                                    .addComponent(rdoHD)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel9)))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(rdoKHD)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblDonVi)
                                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                                        .addComponent(txtGiaTriGiam))
                                    .addComponent(txtThoiGianKT)
                                    .addComponent(btnChonNKT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(lblMaSale)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTenSale, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("SALE");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void txtNgayBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayBDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayBDActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new DanhSachSanPhamSaleDialog(new Main(), true, list, btnThem).setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (id_Sale == -1 || id_Sale == 0) {
            addSale();
            MsgBox.alert(this, "Tạo chương trình sale thành công!");
        } else {
            updateSale();
            MsgBox.alert(this, "Cập nhật chương trình sale thành công!");
        }
        fillTable(sale_Service.selectAll());

    }//GEN-LAST:event_btnThemActionPerformed

    private void cbbHTGItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbHTGItemStateChanged
        int lc = cbbHTG.getSelectedIndex();
        if (lc == 0) {
            lblDonVi.setText("VNĐ");
        } else {
            lblDonVi.setText("%");
        }
    }//GEN-LAST:event_cbbHTGItemStateChanged

    private void tblSaleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSaleMouseClicked
        int row = tblSale.getSelectedRow();
        id_Sale = Integer.parseInt(tblSale.getValueAt(row, 0).toString());
        Sale s = sale_Service.selectByID(id_Sale);
        setForm(s);
        list = sale_CT_Service.selectByIDSaleAndTT(id_Sale);

    }//GEN-LAST:event_tblSaleMouseClicked

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        clearForm();
        list.clear();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnChonNBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonNBDActionPerformed
      dateChooserBD.showPopup();
    }//GEN-LAST:event_btnChonNBDActionPerformed

    private void btnChonNKTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonNKTActionPerformed
        dateChooserKT.showPopup();
    }//GEN-LAST:event_btnChonNKTActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonNBD;
    private javax.swing.JButton btnChonNKT;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimSP2;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbHTG;
    private javax.swing.JComboBox<String> cboTkSanPham2;
    private com.raven.datechooserr.DateChooser dateChooserBD;
    private com.raven.datechooserr.DateChooser dateChooserKT;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JLabel lblDonVi;
    private javax.swing.JLabel lblMaSale;
    private javax.swing.JRadioButton rdoHD;
    private javax.swing.JRadioButton rdoKHD;
    private javax.swing.JTable tblSale;
    private javax.swing.JTextField txtGiaTriGiam;
    private javax.swing.JTextField txtNgayBD;
    private javax.swing.JTextField txtTenSale;
    private javax.swing.JTextField txtThoiGianKT;
    private javax.swing.JTextField txtTimKiemSp2;
    // End of variables declaration//GEN-END:variables
}
