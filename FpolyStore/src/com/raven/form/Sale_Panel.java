package com.raven.form;

import com.fstore.untils.MsgBox;
import com.fstore.untils.XDate;
import com.fstore.model.Sale;
import com.fstore.model.Sale_ChiTiet;
import com.fstore.model.SanPhamChiTiet;
import com.fstore.service.Sale_CT_Service;
import com.fstore.service.Sale_Service;
import com.fstore.service.SanPhamChiTiet_Service;
import com.ui.main.Main;
import java.util.ArrayList;
import java.util.Calendar;
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
    private int search = 0;

    public Sale_Panel() {
        initComponents();
        init();
    }

    public void init() {
        setHidenBtn();
        autoUpdateSale();
        fillTable(sale_Service.selectAll());
        setBtn();
        txtNgayBD.setEditable(false);
        txtThoiGianKT.setEditable(false);
    }

    public void autoUpdateSale() {
        for (Sale s : sale_Service.selectAll()) {
            s.setTrangThai(checkDatẹKT(s.getThoiGianKT()));
            sale_Service.update(s, s.getId_Sale());
        }
    }

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
            Sale_ChiTiet sct = null;
            if(!sale_CT_Service.selectByIDSale(sale.getId_Sale()).isEmpty()){
                sct = sale_CT_Service.selectByIDSale(sale.getId_Sale()).get(0);
            }else{
                sct = new Sale_ChiTiet();
                sct.setHinhThucGiam(0);
                sct.setGiaTriGiam(0);
            }
            
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
         Sale_ChiTiet sct = null;
            if(!sale_CT_Service.selectByIDSale(s.getId_Sale()).isEmpty()){
                sct = sale_CT_Service.selectByIDSale(s.getId_Sale()).get(0);
            }else{
                sct = new Sale_ChiTiet();
                sct.setHinhThucGiam(0);
                sct.setGiaTriGiam(0);
            }
       
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

        btnThem.setEnabled(false);
    }

    public int checkDatẹKT(Date ngayKT) {
//        Date dateNow = new Date();
//        System.out.println(ngayKT.compareTo(dateNow));
//        if(ngayKT.compareTo(dateNow) >= 0){
//           return 1;
//        }
//        return 0;
        // Tạo một đối tượng Calendar cho ngày hiện tại
        Calendar calendarNow = Calendar.getInstance();
        calendarNow.setTime(new Date());
        // Đặt phần giờ, phút, giây của ngày hiện tại là 0 để chỉ so sánh theo ngày
        calendarNow.set(Calendar.HOUR_OF_DAY, 0);
        calendarNow.set(Calendar.MINUTE, 0);
        calendarNow.set(Calendar.SECOND, 0);
        calendarNow.set(Calendar.MILLISECOND, 0);

        // Tạo một đối tượng Calendar cho ngày kết thúc
        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.setTime(ngayKT);
        // Trừ đi một ngày từ ngày kết thúc
        calendarEnd.add(Calendar.DAY_OF_MONTH, -1);
        // Đặt phần giờ, phút, giây của ngày kết thúc là 0 để chỉ so sánh theo ngày
        calendarEnd.set(Calendar.HOUR_OF_DAY, 0);
        calendarEnd.set(Calendar.MINUTE, 0);
        calendarEnd.set(Calendar.SECOND, 0);
        calendarEnd.set(Calendar.MILLISECOND, 0);

        // So sánh ngày kết thúc với ngày hiện tại
        if (calendarEnd.compareTo(calendarNow) >= 0) {
            return 1;
        }
        return 0;
    }

//    public int checkDateKT(Date ngayKT) {
//        // Tạo một đối tượng Calendar cho ngày hiện tại
//        Calendar calendarNow = Calendar.getInstance();
//        calendarNow.setTime(new Date());
//        // Đặt phần giờ, phút, giây của ngày hiện tại là 0 để chỉ so sánh theo ngày
//        calendarNow.set(Calendar.HOUR_OF_DAY, 0);
//        calendarNow.set(Calendar.MINUTE, 0);
//        calendarNow.set(Calendar.SECOND, 0);
//        calendarNow.set(Calendar.MILLISECOND, 0);
//
//        // Tạo một đối tượng Calendar cho ngày kết thúc
//        Calendar calendarEnd = Calendar.getInstance();
//        calendarEnd.setTime(ngayKT);
//        // Trừ đi một ngày từ ngày kết thúc
//        calendarEnd.add(Calendar.DAY_OF_MONTH, -1);
//        // Đặt phần giờ, phút, giây của ngày kết thúc là 0 để chỉ so sánh theo ngày
//        calendarEnd.set(Calendar.HOUR_OF_DAY, 0);
//        calendarEnd.set(Calendar.MINUTE, 0);
//        calendarEnd.set(Calendar.SECOND, 0);
//        calendarEnd.set(Calendar.MILLISECOND, 0);
//
//        // So sánh ngày kết thúc với ngày hiện tại
//        if (calendarEnd.compareTo(calendarNow) >= 0) {
//            return 1;
//        }
//        return 0;
//    }
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
        if (ngayKT.compareTo(ngayBD) <= 0) {
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
        tt = checkDatẹKT(ngayKT);
        if (er.length() > 0) {
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
        MsgBox.alert(this, "Tạo chương trình sale thành công!");
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
        MsgBox.alert(this, "Cập nhật chương trình sale thành công!");
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

    public void search() {
        List<Sale> list = new ArrayList<>();
        if (search == 0) {
            String idT = txtSearch.getText().trim();
            int id = 0;
            try {
                id = Integer.parseInt(idT);
            } catch (Exception e) {
            }
            Sale hd = sale_Service.selectByID(id);
            if (hd != null) {
                list.add(hd);
            }
        } else if (search == 1) {
            String ten = txtSearch.getText().trim();
            list = sale_Service.selectByName(ten);
        }
        fillTable(list);
    }

    public void locHD() {
        Date ngayBD = XDate.toDate(txtLocBD.getText(), "dd-MM-yyyy");
        Date ngayKT = XDate.toDate(txtLocKT.getText(), "dd-MM-yyyy");
        int tt = 1;
        if (rdoLHD.isSelected()) {
            tt = 1;
        } else if (rdoLocKHD.isSelected()) {
            tt = 0;
        }
        List<Sale> list = sale_Service.selectByDay(ngayBD, ngayKT, tt);
        fillTable(list);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        dateChooserKT = new com.raven.datechooser.DateChooser();
        dateChooserBD = new com.raven.datechooser.DateChooser();
        buttonGroup2 = new javax.swing.ButtonGroup();
        dateChooserBDL = new com.raven.datechooser.DateChooser();
        dateChooserKTL = new com.raven.datechooser.DateChooser();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSale = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        cbbTkSanPham = new javax.swing.JComboBox<>();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtLocBD = new javax.swing.JTextField();
        txtLocKT = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        rdoLHD = new javax.swing.JRadioButton();
        rdoLocKHD = new javax.swing.JRadioButton();
        btnLoc = new javax.swing.JButton();
        btnHuyLoc = new javax.swing.JButton();
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

        dateChooserBDL.setForeground(new java.awt.Color(0, 255, 0));
        dateChooserBDL.setTextRefernce(txtLocBD);

        dateChooserKTL.setForeground(new java.awt.Color(0, 255, 0));
        dateChooserKTL.setTextRefernce(txtLocKT);

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách SALE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tblSale.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblSale.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sale", "Tên chương trình", "Hình thức giảm", "Mức giảm", "Thời gian BĐ", "Thời gian KT", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSale.setRowHeight(25);
        tblSale.setSelectionBackground(new java.awt.Color(0, 0, 0));
        tblSale.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tblSale.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSaleMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblSale);

        jLabel14.setText("Tìm kiếm theo");

        cbbTkSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã", "Tên" }));
        cbbTkSanPham.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbTkSanPhamItemStateChanged(evt);
            }
        });

        btnSearch.setBackground(new java.awt.Color(153, 255, 153));
        btnSearch.setText("Tìm");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Thời gian BĐ");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Thời gian KT");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("Trạng thái");

        buttonGroup2.add(rdoLHD);
        rdoLHD.setSelected(true);
        rdoLHD.setText("Hoạt động");

        buttonGroup2.add(rdoLocKHD);
        rdoLocKHD.setText("Không hoạt động");

        btnLoc.setBackground(new java.awt.Color(102, 255, 102));
        btnLoc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnLoc.setText("Lọc");
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(btnSearch))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txtLocBD, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(txtLocKT, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel14)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(rdoLHD)
                                .addGap(64, 64, 64)
                                .addComponent(rdoLocKHD)
                                .addGap(6, 6, 6))
                            .addComponent(jLabel16)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(btnHuyLoc)
                                .addGap(31, 31, 31)
                                .addComponent(btnLoc)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 763, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbTkSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(rdoLHD)
                    .addComponent(rdoLocKHD))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHuyLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(188, Short.MAX_VALUE))
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Mã sale");

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

        jButton1.setBackground(new java.awt.Color(204, 255, 0));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setText("Sản phẩm áp dụng");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnReset.setBackground(new java.awt.Color(0, 255, 204));
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
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(btnChonNBD)
                                .addGap(5, 5, 5)
                                .addComponent(cbbHTG, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(btnChonNKT)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtGiaTriGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDonVi)))
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdoKHD, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoHD)
                    .addComponent(jLabel11)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
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
                                    .addComponent(txtGiaTriGiam)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblDonVi)
                                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        locHD();
    }//GEN-LAST:event_btnLocActionPerformed

    private void txtNgayBDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayBDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayBDActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new DanhSachSanPhamSaleDialog(new Main(), true, list, btnThem).setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (id_Sale == -1 || id_Sale == 0) {
            addSale();

        } else {
            updateSale();

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

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        search();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnHuyLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyLocActionPerformed
        fillTable(sale_Service.selectAll());
    }//GEN-LAST:event_btnHuyLocActionPerformed

    private void cbbTkSanPhamItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbTkSanPhamItemStateChanged
        search = cbbTkSanPham.getSelectedIndex();
    }//GEN-LAST:event_cbbTkSanPhamItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonNBD;
    private javax.swing.JButton btnChonNKT;
    private javax.swing.JButton btnHuyLoc;
    private javax.swing.JButton btnLoc;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnThem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbbHTG;
    private javax.swing.JComboBox<String> cbbTkSanPham;
    private com.raven.datechooser.DateChooser dateChooserBD;
    private com.raven.datechooser.DateChooser dateChooserBDL;
    private com.raven.datechooser.DateChooser dateChooserKT;
    private com.raven.datechooser.DateChooser dateChooserKTL;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblDonVi;
    private javax.swing.JLabel lblMaSale;
    private javax.swing.JRadioButton rdoHD;
    private javax.swing.JRadioButton rdoKHD;
    private javax.swing.JRadioButton rdoLHD;
    private javax.swing.JRadioButton rdoLocKHD;
    private javax.swing.JTable tblSale;
    private javax.swing.JTextField txtGiaTriGiam;
    private javax.swing.JTextField txtLocBD;
    private javax.swing.JTextField txtLocKT;
    private javax.swing.JTextField txtNgayBD;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTenSale;
    private javax.swing.JTextField txtThoiGianKT;
    // End of variables declaration//GEN-END:variables
}
