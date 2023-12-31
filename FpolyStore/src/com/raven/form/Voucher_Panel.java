package com.raven.form;

import com.fstore.untils.MsgBox;
import com.fstore.untils.XDate;
import com.fstore.model.Sale;
import com.fstore.model.Sale_ChiTiet;
import com.fstore.model.SanPhamChiTiet;
import com.fstore.model.Voucher;
import com.fstore.service.Sale_CT_Service;
import com.fstore.service.Sale_Service;
import com.fstore.service.SanPhamChiTiet_Service;
import com.fstore.service.Voucher_Service;
import com.ui.main.Main;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class Voucher_Panel extends javax.swing.JPanel {

    private SanPhamChiTiet_Service spct_Service = new SanPhamChiTiet_Service();
    private Voucher_Service voucher_Service = new Voucher_Service();
    private int row = -1;
    private int id_Voucher = 0;
    private int search = 0;

    public Voucher_Panel() {
        initComponents();
        init();
    }

    public void init() {
        autoUpdateVoucher();
        fillTable(voucher_Service.selectAll());

    }
    public void autoUpdateVoucher(){
        for (Voucher v : voucher_Service.selectAll()) {
            v.setTrangThai(checkDatẹKT(v.getThoiGianKT()));
            voucher_Service.update(v, v.getId_Voucher());
        }
    }
//    public void show() {
//        MsgBox.alert(this, "Số lượng sản phẩm trong list " + list.size());
//    }
    public void fillTable(List<Voucher> list) {
        DefaultTableModel tblMd = (DefaultTableModel) tblVoucher.getModel();
        tblMd.setRowCount(0);
        for (Voucher v : list) {
            tblMd.addRow(new Object[]{
                v.getId_Voucher(),
                v.getTenChienDich(),
                v.getHinhThucGiam() == 0 ? "Theo tiền mặt" : "Theo phần trăm",
                v.getGiaTriGiam(),
                v.getSoLuong(),
                v.getThoiGianBD(),
                v.getThoiGianKT(),
                v.getTrangThai() == 1 ? "Hoạt động" : "Không hoạt động"
            });
        }
    }

    public Voucher getForm() {
        //Voucher v = new Voucher();
        String ten = txtTenVoucher.getText();
        String ngayBatDau = txtNgayBD.getText();
        String ngayKetThuc = txtThoiGianKT.getText();
        StringBuilder er = new StringBuilder();
        int hinhThucGiam = cbbHTG.getSelectedIndex();
        String giaTriGiamT = txtGiaTriGiam.getText();
        String soLuongT = txtSoLuong.getText();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        int soLuong = 0;
        double giaTriGiam = 0;
        if (giaTriGiamT.isEmpty()) {
            er.append("Giá trị giảm không được bỏ trống!\n");
        } else {
            try {
                giaTriGiam = Double.parseDouble(giaTriGiamT);
                if (giaTriGiam <= 0) {
                    er.append("Giá trị giảm phải lớn hơn 0!\n");

                }
                if (hinhThucGiam == 1 && giaTriGiam > 100) {
                    er.append("Giá trị giảm phải trong khoảng 0-100%!\n");
                }
            } catch (Exception e) {
                er.append("Giá trị giảm phải là số!\n");
            }
        }
        Date ngayKT = null;
        Date ngayBD = null;

        try {
            ngayBD = format.parse(ngayBatDau);
            ngayKT = format.parse(ngayKetThuc);
            //ngayBD = XDate.toDate(ngayBatDau, "dd-MM-yyyy");
            //ngayKT = XDate.toDate(ngayKetThuc, "dd-MM-yyyy");
        } catch (Exception e) {
            er.append("Ngày không đúng định dạng!\n");
        }

        if (ngayBatDau.isEmpty()) {
            er.append("Ngày bắt đầu không được bỏ trống!\n");
        } else {
            try {
                ngayBD = XDate.toDate(ngayBatDau, "dd-MM-yyyy");
                ngayKT = XDate.toDate(ngayKetThuc, "dd-MM-yyyy");
            } catch (Exception e) {
                er.append("Ngày không đúng định dạng!\n");
            }
        }
        if (ngayKetThuc.isEmpty()) {
            er.append("Ngày kết thúc không được bỏ trống!\n");
        }
        if (ten.isEmpty()) {
            er.append("Tên voucher không được bỏ trống!\n");
        }
        if (ngayBD == null || ngayKT == null) {
            return null;
        } else {
            if (ngayKT.compareTo(ngayBD) <= 0) {
                er.append("Ngày kết thúc phải lớn hơn này bắt đầu!\n");
            }
        }
        if (soLuongT.isEmpty()) {
            er.append("Số lượng không được bỏ trống!\n");
        } else {
            try {
                soLuong = Integer.parseInt(soLuongT);
                if (soLuong <= 0) {
                    er.append("Số lượng phải lớn hơn 0!\n");
                }
            } catch (Exception e) {
                er.append("Số lượng phải là số!\n");
            }
        }
        int tt = 1;
        if (rdoHD.isSelected()) {
            tt = 1;
        } else if (rdoKHD.isSelected()) {
            tt = 0;
        }
        tt = checkDatẹKT(ngayKT);
        if (er.length() > 0) {
            MsgBox.alert(this, er.toString());
            return null;
        }
        return new Voucher(ten, hinhThucGiam, giaTriGiam, soLuong, ngayBD, ngayKT, tt);
    }
    public int checkDatẹKT(Date ngayKT){
        Date dateNow = new Date();
       
        if(ngayKT.compareTo(dateNow) < 0){
           return 0;
        }
        return 1;
    }
    public void clearForm() {
        txtGiaTriGiam.setText("");
        txtNgayBD.setText("");
        txtThoiGianKT.setText("");
        txtSoLuong.setText("");
        txtTenVoucher.setText("");
        lblMaSale.setText("");
        cbbHTG.setSelectedIndex(0);
        rdoHD.setSelected(true);
    }

    public void setForm(Voucher v) {
        txtGiaTriGiam.setText(String.valueOf(v.getGiaTriGiam()));
        txtNgayBD.setText(XDate.toString(v.getThoiGianBD(), "dd-MM-yyyy"));
        txtThoiGianKT.setText(XDate.toString(v.getThoiGianKT(), "dd-MM-yyyy"));
        txtSoLuong.setText(String.valueOf(v.getSoLuong()));
        txtTenVoucher.setText(v.getTenChienDich());
        lblMaSale.setText(String.valueOf(v.getId_Voucher()));
        cbbHTG.setSelectedIndex(v.getHinhThucGiam());
        if (v.getTrangThai() == 1) {
            rdoHD.setSelected(true);
        } else {
            rdoKHD.setSelected(true);
        }
    }

    public void addVoucher() {
        Voucher v = getForm();
        if (v != null) {
            if (MsgBox.confirm(this, "Bạn có muốn thêm chương trình?")) {
                if (voucher_Service.insert(v) != 0) {
                    MsgBox.alert(this, "Thêm chương trình thành công!");
                    fillTable(voucher_Service.selectAll());
                    clearForm();
                }
            }
        }

    }

    public void updateVoucher() {
        Voucher v = getForm();
        if (v == null) {
            return;
        }
        if (MsgBox.confirm(this, "Bạn có muốn cập nhật chương trình?")) {
            if (voucher_Service.update(v, id_Voucher) != 0) {
                MsgBox.alert(this, "Cập nhật chương trình thành công!");
                fillTable(voucher_Service.selectAll());
                clearForm();
            }
        }
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
    
     public void search(){
        List<Voucher> list = new ArrayList<>();
        if(search == 0){
            String idT = txtSearch.getText().trim();
            int id = 0;
            try {
                id = Integer.parseInt(idT);
            } catch (Exception e) {
            }
            Voucher hd = voucher_Service.selectByID(id);
            if(hd!= null){
                list.add(hd);
            }
        }else if(search == 1){
            String ten = txtSearch.getText().trim();
            list = voucher_Service.selectByName(ten);
        }
        fillTable(list);
    }
    
    public void locHD(){
        Date ngayBD = XDate.toDate(txtLocBD.getText(), "dd-MM-yyyy");
        Date ngayKT = XDate.toDate(txtLocKT.getText(), "dd-MM-yyyy");
        int tt = 1;
        if(rdoLocHD.isSelected()){
            tt = 1;
        }else if(rdoLocKHD.isSelected()){
            tt = 0;
        }
        List<Voucher> list = voucher_Service.selectByDateTT(ngayBD, ngayKT, tt);
        fillTable(list);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        dateChooserBD = new com.raven.datechooser.DateChooser();
        dateChooserKT = new com.raven.datechooser.DateChooser();
        dateChooserLBD = new com.raven.datechooser.DateChooser();
        dateChooserLKT = new com.raven.datechooser.DateChooser();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblVoucher = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        cbbTkSanPham = new javax.swing.JComboBox<>();
        txtSearch = new javax.swing.JTextField();
        btnTim = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtLocBD = new javax.swing.JTextField();
        txtLocKT = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        rdoLocHD = new javax.swing.JRadioButton();
        rdoLocKHD = new javax.swing.JRadioButton();
        jButton7 = new javax.swing.JButton();
        btnHuyLoc = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jTextField7 = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblMaSale = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTenVoucher = new javax.swing.JTextField();
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
        btnReset = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        dateChooserBD.setForeground(new java.awt.Color(0, 255, 51));
        dateChooserBD.setTextRefernce(txtNgayBD);

        dateChooserKT.setForeground(new java.awt.Color(51, 255, 51));
        dateChooserKT.setTextRefernce(txtThoiGianKT);

        dateChooserLBD.setForeground(new java.awt.Color(0, 204, 0));
        dateChooserLBD.setTextRefernce(txtLocBD);

        dateChooserLKT.setForeground(new java.awt.Color(0, 255, 0));
        dateChooserLKT.setTextRefernce(txtLocKT);

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách VOUCHER", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tblVoucher.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblVoucher.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Voucher", "Tên chương trình", "Hình thức giảm", "Mức giảm", "Số lượng", "Thời gian BĐ", "Thời gian KT", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblVoucher.setRowHeight(25);
        tblVoucher.setSelectionBackground(new java.awt.Color(0, 0, 0));
        tblVoucher.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tblVoucher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVoucherMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblVoucher);

        jLabel14.setText("Tìm kiếm theo");

        cbbTkSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã", "Tên" }));
        cbbTkSanPham.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbTkSanPhamItemStateChanged(evt);
            }
        });

        btnTim.setBackground(new java.awt.Color(153, 255, 153));
        btnTim.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnTim.setText("Tìm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Thời gian BĐ");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Thời gian KT");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("Trạng thái");

        buttonGroup2.add(rdoLocHD);
        rdoLocHD.setSelected(true);
        rdoLocHD.setText("Hoạt động");

        buttonGroup2.add(rdoLocKHD);
        rdoLocKHD.setText("Không hoạt động");

        jButton7.setBackground(new java.awt.Color(102, 255, 102));
        jButton7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton7.setText("Lọc");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        btnHuyLoc.setBackground(new java.awt.Color(255, 153, 153));
        btnHuyLoc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnHuyLoc.setText("Hủy lọc");
        btnHuyLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyLocActionPerformed(evt);
            }
        });

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
                        .addComponent(cbbTkSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addComponent(btnTim))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtLocBD, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtLocKT)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(rdoLocHD)
                                    .addGap(68, 68, 68)
                                    .addComponent(rdoLocKHD)
                                    .addGap(6, 6, 6))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGap(78, 78, 78)
                                    .addComponent(btnHuyLoc)
                                    .addGap(18, 18, 18)
                                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel16)))
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
                            .addComponent(cbbTkSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtLocBD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLocKT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoLocHD)
                            .addComponent(rdoLocKHD))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnHuyLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Mã Voucher");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Tên chương trình");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Thời gian bắt đầu");

        txtNgayBD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgayBDActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Thời gian kết thúc");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Hình thức giảm");

        cbbHTG.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Theo tiền mặt", "Theo phần trăm" }));
        cbbHTG.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbHTGItemStateChanged(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Giá trị giảm");

        lblDonVi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDonVi.setForeground(new java.awt.Color(255, 0, 0));
        lblDonVi.setText("VNĐ");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Trạng thái");

        buttonGroup1.add(rdoHD);
        rdoHD.setSelected(true);
        rdoHD.setText("Hoạt động");

        buttonGroup1.add(rdoKHD);
        rdoKHD.setText("Không hoạt động");

        btnReset.setBackground(new java.awt.Color(153, 255, 204));
        btnReset.setText("Reset Form");
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

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Số lượng");

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
                    .addComponent(txtTenVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(75, 75, 75)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(txtNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabel6))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel9))
                                    .addComponent(cbbHTG, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtThoiGianKT, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtGiaTriGiam)))
                .addGap(34, 34, 34)
                .addComponent(lblDonVi)
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdoKHD, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoHD)
                    .addComponent(jLabel11)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
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
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                        .addComponent(cbbHTG, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(rdoHD)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(rdoKHD))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel8))))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(17, 17, 17)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtThoiGianKT, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtTenVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lblDonVi)
                                                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel4)))
                                                .addGap(0, 0, Short.MAX_VALUE))))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtGiaTriGiam))))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(lblMaSale)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(50, 50, 50)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        jLabel1.setText("VOUCHER");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        locHD();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void txtNgayBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayBDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayBDActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (lblMaSale.getText().isEmpty()) {
            addVoucher();

        } else {
            updateVoucher();
        }

    }//GEN-LAST:event_btnThemActionPerformed

    private void cbbHTGItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbHTGItemStateChanged
        int lc = cbbHTG.getSelectedIndex();
        if (lc == 0) {
            lblDonVi.setText("VNĐ");
        } else {
            lblDonVi.setText("%");
        }
    }//GEN-LAST:event_cbbHTGItemStateChanged

    private void tblVoucherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVoucherMouseClicked
        row = tblVoucher.getSelectedRow();
        id_Voucher = Integer.parseInt(tblVoucher.getValueAt(row, 0).toString());
        Voucher v = voucher_Service.selectByID(id_Voucher);
        setForm(v);
    }//GEN-LAST:event_tblVoucherMouseClicked

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        clearForm();
    }//GEN-LAST:event_btnResetActionPerformed

    private void cbbTkSanPhamItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbTkSanPhamItemStateChanged
       search = cbbTkSanPham.getSelectedIndex();
    }//GEN-LAST:event_cbbTkSanPhamItemStateChanged

    private void btnHuyLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyLocActionPerformed
        fillTable(voucher_Service.selectAll());
    }//GEN-LAST:event_btnHuyLocActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        search();
    }//GEN-LAST:event_btnTimActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuyLoc;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTim;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbbHTG;
    private javax.swing.JComboBox<String> cbbTkSanPham;
    private com.raven.datechooser.DateChooser dateChooserBD;
    private com.raven.datechooser.DateChooser dateChooserKT;
    private com.raven.datechooser.DateChooser dateChooserLBD;
    private com.raven.datechooser.DateChooser dateChooserLKT;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JLabel lblDonVi;
    private javax.swing.JLabel lblMaSale;
    private javax.swing.JRadioButton rdoHD;
    private javax.swing.JRadioButton rdoKHD;
    private javax.swing.JRadioButton rdoLocHD;
    private javax.swing.JRadioButton rdoLocKHD;
    private javax.swing.JTable tblVoucher;
    private javax.swing.JTextField txtGiaTriGiam;
    private javax.swing.JTextField txtLocBD;
    private javax.swing.JTextField txtLocKT;
    private javax.swing.JTextField txtNgayBD;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenVoucher;
    private javax.swing.JTextField txtThoiGianKT;
    // End of variables declaration//GEN-END:variables
}
