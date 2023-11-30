package com.raven.form;

import com.fsore.untils.MsgBox;
import com.fsore.untils.XDate;
import com.fstore.model.DanhMuc;
import com.fstore.model.HoaDonNhap;
import com.fstore.model.HoaDonNhap_ChiTiet;
import com.fstore.model.NhaCungCap;
import com.fstore.model.SanPhamChiTiet;
import com.fstore.service.ChatLieu_Service;
import com.fstore.service.DanhMuc_Service;
import com.fstore.service.HoaDonNhapChiTiet_Service;
import com.fstore.service.HoaDonNhap_Service;
import com.fstore.service.MauSac_Service;
import com.fstore.service.NhaCungCap_Service;
import com.fstore.service.SanPhamChiTiet_Service;
import com.fstore.service.SanPham_Service;
import com.fstore.service.Size_Service;
import com.ui.main.Main;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class NhapHang_Panel extends javax.swing.JPanel {

    private SanPhamChiTiet_Service spct_Service = new SanPhamChiTiet_Service();
    private SanPham_Service sp_Service = new SanPham_Service();
    private Size_Service size_Service = new Size_Service();
    private ChatLieu_Service chatLieu_Service = new ChatLieu_Service();
    private MauSac_Service mauSac_Service = new MauSac_Service();
    private DanhMuc_Service danhMuc_Service = new DanhMuc_Service();
    private NhaCungCap_Service nhaCungCap_Service = new NhaCungCap_Service();
    private List<HoaDonNhap_ChiTiet> list = new ArrayList<>();
    private HoaDonNhap_Service hoaDonNhap_Service = new HoaDonNhap_Service();
    private HoaDonNhapChiTiet_Service hdnct_Service = new  HoaDonNhapChiTiet_Service();
    private int search = 0;
    public NhapHang_Panel() {
        initComponents();
        init();
    }

    public void init() {
        fillTableSanPham(spct_Service.selectAll());
        fillCboNCC();
        fillCbbDanhMuc();
        setHiden();
    }

    public void fillCboNCC() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbbNhaCungCap.getModel();
        model.removeAllElements();
        List<NhaCungCap> list = nhaCungCap_Service.selectAll();
        for (NhaCungCap ncc : list) {
            model.addElement(ncc);
        }
    }

    public void fillTableSanPham(List<SanPhamChiTiet> list) {
        DefaultTableModel tblMd = (DefaultTableModel) tblSanPham.getModel();
        tblMd.setRowCount(0);
        for (SanPhamChiTiet spct : list) {
            tblMd.addRow(new Object[]{
                spct.getId_SanPhamChiTiet(),
                sp_Service.selectByID(spct.getId_SanPham()).getTenSP(),
                sp_Service.selectByID(spct.getId_SanPham()).getDm().getTenDanhMuc(),
                spct.getGia(),
                size_Service.selectByID(spct.getId_Size()).getTenSize(),
                mauSac_Service.selectByID(spct.getId_Mau()).getTenMau(),
                chatLieu_Service.selectByID(spct.getId_ChatLieu()).getTenChatLieu(),
                spct.getSoLuong()
            });
        }
    }
     public void fillCbbDanhMuc() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbbDanhMuc.getModel();
        model.removeAllElements();
        model.addElement("Tất cả");
        for (DanhMuc dm : danhMuc_Service.selectAll()) {
            model.addElement(dm);
        }
    }
    public void fillTableSanPhamNhap(List<HoaDonNhap_ChiTiet> list) {
        DefaultTableModel tblMd = (DefaultTableModel) tblSanPhamNhap.getModel();
        tblMd.setRowCount(0);
        for (HoaDonNhap_ChiTiet hdnct : list) {
            SanPhamChiTiet spct = spct_Service.selectByID(hdnct.getId_SanPhamChiTiet());
            tblMd.addRow(new Object[]{
                hdnct.getId_SanPhamChiTiet(),
                sp_Service.selectByID(spct.getId_SanPham()).getTenSP(),
                sp_Service.selectByID(spct.getId_SanPham()).getDm().getTenDanhMuc(),
                size_Service.selectByID(spct.getId_Size()).getTenSize(),
                mauSac_Service.selectByID(spct.getId_Mau()).getTenMau(),
                chatLieu_Service.selectByID(spct.getId_ChatLieu()).getTenChatLieu(),
                hdnct.getSoLuong(),
                hdnct.getGiaNhap()
            });
        }
    }

    public HoaDonNhap_ChiTiet getHoaDonNhap_CT() {
        int row = tblSanPham.getSelectedRow();
        StringBuilder er = new StringBuilder();
        if (row < 0) {
            er.append("Vui lòng chọn sản phẩm muốn nhập!\n");
        }
        int id_Spct = Integer.parseInt(tblSanPham.getValueAt(row, 0).toString());
        String soLuongT = txtSoLuong.getText();
        int soLuong = 0;
        String giaNhapT = txtGiaNhap.getText();
        double giaNhap = 0;
        if (soLuongT.isEmpty()) {
            er.append("Vui lòng điền số lượng nhập!\n");
        } else {
            try {
                soLuong = Integer.parseInt(soLuongT);
                if (soLuong <= 0) {
                    er.append("Số lượng nhập phải lớn hơn 0!\n");
                }
            } catch (Exception e) {
                er.append("Số lượng phải là số!\n");
            }
        }
        if (giaNhapT.isEmpty()) {
            er.append("Vui lòng điền giá nhập!\n");
        } else {
            try {
                giaNhap = Double.parseDouble(giaNhapT);
                if (soLuong <= 0) {
                    er.append("Giá nhập phải lớn hơn 0!\n");
                }
            } catch (Exception e) {
                er.append("Giá nhập phải là số!\n");
            }
        }
        if (er.length() > 0) {
            MsgBox.alert(this, er.toString());
            return null;
        }
        return new HoaDonNhap_ChiTiet(id_Spct, soLuong, giaNhap);
    }

    public double getTongTien() {
        double tongTien = 0;
        for (int i = 0; i < list.size(); i++) {
            HoaDonNhap_ChiTiet hdnct = list.get(i);
            tongTien += hdnct.getSoLuong() * hdnct.getGiaNhap();
        }
        return tongTien;
    }

    public void delete() {
        int row = tblSanPhamNhap.getSelectedRow();
        if (row < 0) {
            MsgBox.alert(this, "Vui lòng chọn sản phẩm cần xóa ra khỏi danh sách!");
            return;
        }
        int id_Spct = Integer.parseInt(tblSanPhamNhap.getValueAt(row, 0).toString());
        for (HoaDonNhap_ChiTiet hdnct : list) {
            if (hdnct.getId_SanPhamChiTiet() == id_Spct) {
                list.remove(hdnct);

            }
        }
        lblTongTien.setText(getTongTien() + " VNĐ");
        tblSanPhamNhap.clearSelection();
    }

    public void clear() {
        txtSoLuong.setText("");
        txtGiaNhap.setText("");
        tblSanPham.clearSelection();
    }
    
    public void setHiden(){
        if(list.size()<0){
            btnHoanThanh.setEnabled(false);
        }else{
            btnHoanThanh.setEnabled(true);
        }
        btnThemGH.setEnabled(false);
        btnXoaGH.setEnabled(false);
    }
    
    public HoaDonNhap getHoaDonNhap(){
        HoaDonNhap hdn = new HoaDonNhap();
        Calendar c = Calendar.getInstance();
        NhaCungCap ncc = (NhaCungCap) cbbNhaCungCap.getSelectedItem();
        if(cbbNhaCungCap.getSelectedIndex()<0){
            MsgBox.alert(this,"Vui lòng chọn nhà cung cấp!");
            return null;
        }
        hdn.setNgayTao(XDate.toString(c.getTime(), "hh:mm:ss aa yyyy-MM-dd"));
        hdn.setMaNv("ThongHT");
        hdn.setId_NhaCungCap(ncc.getID_NCC());
        hdn.setSoDienThoai(ncc.getSDT());
        hdn.setTenNhaCungCap(ncc.getTenNCC());
        return hdn;
    }
    public void insertHoaDonNhap(){
       HoaDonNhap hdn = getHoaDonNhap();
       if(hdn == null){
           return;
       }
       hdn.setGhiChu(txtGhiChu.getText());
       hdn.setTongTien(getTongTien());
       hdn.setTrangThai(1);
       hoaDonNhap_Service.insert(hdn);
        for (HoaDonNhap_ChiTiet hdct : list) {
            hdct.setTrangThai(1);
            hdnct_Service.insert(hdct);
            spct_Service.updateSoLuongSauKhiNhap(hdct.getSoLuong(), hdct.getId_SanPhamChiTiet());
        }
        MsgBox.alert(this, "Tạo thành công hóa đơn nhập hàng!");
        list.clear();
        fillTableSanPhamNhap(list);
        fillTableSanPham(spct_Service.selectAll());
    }
    
     public void search(){
        List<SanPhamChiTiet> list = new ArrayList<>();
        if(search == 0){
            String idT = txtSearch.getText().trim();
            int id = 0;
            try {
                id = Integer.parseInt(idT);
            } catch (Exception e) {
            }
            SanPhamChiTiet sp = spct_Service.selectByID(id);
            if(sp!= null){
                list.add(sp);
            }
        }else if(search == 1){
            String ten = txtSearch.getText().trim();
            list = spct_Service.selectByName(ten);
        }
        fillTableSanPham(list);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cbbTkSanPham = new javax.swing.JComboBox<>();
        txtSearch = new javax.swing.JTextField();
        btnTimSP = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        cbbDanhMuc = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnThemGH = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSanPhamNhap = new javax.swing.JTable();
        btnXoaGH = new javax.swing.JButton();
        lblTenSanPham = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        txtGiaNhap = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        cbbNhaCungCap = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        jButton3 = new javax.swing.JButton();
        btnHoanThanh = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1074, 671));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("NHẬP HÀNG");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách sản phẩm"));

        jLabel2.setText("Tìm kiếm theo");

        cbbTkSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã", "Tên" }));
        cbbTkSanPham.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbTkSanPhamItemStateChanged(evt);
            }
        });

        btnTimSP.setText("Tìm");
        btnTimSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimSPActionPerformed(evt);
            }
        });

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID_SPCT", "Tên sản phẩm", "Danh mục", "Giá bán", "Size", "Màu", "Chất liệu", "Số lượng trong kho"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        cbbDanhMuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbDanhMuc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbDanhMucItemStateChanged(evt);
            }
        });

        jLabel25.setText("Danh mục");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbbTkSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btnTimSP)
                .addGap(26, 26, 26)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbbDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbbTkSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimSP)
                    .addComponent(cbbDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách mua"));

        jLabel3.setText("Tên sản phẩm");

        jLabel4.setText("Số lượng");

        jLabel5.setText("Giá nhập");

        btnThemGH.setText("Thêm vào danh sách nhập");
        btnThemGH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemGHActionPerformed(evt);
            }
        });

        tblSanPhamNhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID_SPCT", "Tên sản phẩm", "Danh mục", "Size", "Màu sắc", "Chất liệu", "Giá nhập", "Số lượng nhập"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPhamNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamNhapMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSanPhamNhap);

        btnXoaGH.setText("Xóa khỏi danh sách");
        btnXoaGH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaGHActionPerformed(evt);
            }
        });

        lblTenSanPham.setText("jLabel7");

        jButton1.setText("Xóa tất cả");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnXoaGH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addGap(40, 40, 40)
                        .addComponent(lblTenSanPham)
                        .addGap(59, 59, 59)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addComponent(btnThemGH)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(lblTenSanPham))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(btnThemGH)
                        .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(btnXoaGH, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin đơn nhập", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel6.setText("Nhà cung cấp");

        cbbNhaCungCap.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setText("Tổng tiền");

        lblTongTien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTongTien.setForeground(new java.awt.Color(255, 0, 0));
        lblTongTien.setText("0.0");

        jLabel9.setText("Ghi chú");

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane3.setViewportView(txtGhiChu);

        jButton3.setBackground(new java.awt.Color(255, 0, 0));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Hủy đơn");

        btnHoanThanh.setBackground(new java.awt.Color(51, 255, 51));
        btnHoanThanh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnHoanThanh.setForeground(new java.awt.Color(255, 255, 255));
        btnHoanThanh.setText("Hoàn thành đơn");
        btnHoanThanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoanThanhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(btnHoanThanh, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(jLabel6)
                            .addComponent(cbbNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbbNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTongTien)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHoanThanh, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemGHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemGHActionPerformed
        HoaDonNhap_ChiTiet hdnct = getHoaDonNhap_CT();
        if (hdnct == null) {
            return;
        }
        int vt = 0;
        boolean check = false;
        for (int i = 0; i < list.size(); i++) {
            HoaDonNhap_ChiTiet hdnctl = list.get(i);
            if (hdnctl.getId_SanPhamChiTiet() == hdnct.getId_SanPhamChiTiet()) {
                vt = i;
                check = true;
            } else {
                check = false;
            }
        }

        if (check) {
            list.set(vt, hdnct);
        } else {
            list.add(hdnct);
        }

        fillTableSanPhamNhap(list);
        lblTongTien.setText(getTongTien() + " VNĐ");
        clear();
        setHiden();
    }//GEN-LAST:event_btnThemGHActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (MsgBox.confirm(this, "Bạn có chắc chắn muốn xóa hét sản phẩm nhập?")) {
            list.clear();
            fillTableSanPhamNhap(list);
            lblTongTien.setText(getTongTien() + " VNĐ");
            setHiden();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnXoaGHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaGHActionPerformed
        delete();
        setHiden();
    }//GEN-LAST:event_btnXoaGHActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        btnThemGH.setEnabled(true);
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void tblSanPhamNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamNhapMouseClicked
       btnXoaGH.setEnabled(true);
    }//GEN-LAST:event_tblSanPhamNhapMouseClicked

    private void btnHoanThanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoanThanhActionPerformed
       insertHoaDonNhap();
       new ChiTietHoaDonNhap_Jdialog(new Main(), true, hoaDonNhap_Service.getID_HoaDonNhap()).setVisible(true);
    }//GEN-LAST:event_btnHoanThanhActionPerformed

    private void cbbDanhMucItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbDanhMucItemStateChanged
        if (cbbDanhMuc.getSelectedIndex() == 0) {
            fillTableSanPham(spct_Service.selectAll());
        } else {
            DanhMuc dm = (DanhMuc) cbbDanhMuc.getSelectedItem();
            if (dm != null) {
                fillTableSanPham(spct_Service.selectByDanhMuc(dm.getID_DanhMuc()));
            }
        }
    }//GEN-LAST:event_cbbDanhMucItemStateChanged

    private void cbbTkSanPhamItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbTkSanPhamItemStateChanged
       search = cbbTkSanPham.getSelectedIndex();
    }//GEN-LAST:event_cbbTkSanPhamItemStateChanged

    private void btnTimSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimSPActionPerformed
       search();
    }//GEN-LAST:event_btnTimSPActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHoanThanh;
    private javax.swing.JButton btnThemGH;
    private javax.swing.JButton btnTimSP;
    private javax.swing.JButton btnXoaGH;
    private javax.swing.JComboBox<String> cbbDanhMuc;
    private javax.swing.JComboBox<String> cbbNhaCungCap;
    private javax.swing.JComboBox<String> cbbTkSanPham;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblTenSanPham;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTable tblSanPhamNhap;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSoLuong;
    // End of variables declaration//GEN-END:variables
}
