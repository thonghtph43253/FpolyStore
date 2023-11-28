package com.raven.form;

import com.fsore.untils.MsgBox;
import com.fsore.untils.XImage;
import com.fstore.model.ChatLieu;
import com.fstore.model.MauSac;
import com.fstore.model.SanPham;
import com.fstore.model.SanPhamChiTiet;
import com.fstore.model.Size;
import com.fstore.service.ChatLieu_Service;
import com.fstore.service.MauSac_Service;
import com.fstore.service.SanPhamChiTiet_Service;
import com.fstore.service.SanPham_Service;
import com.fstore.service.Size_Service;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ThongHTPH43253
 */
public class ThongTinSanPham_Dialog extends javax.swing.JDialog {

    private SanPham_Panel sp_pnl = new SanPham_Panel();
    private SanPham_Service sp_Service = new SanPham_Service();
    private Size_Service size_Sv = new Size_Service();
    private ChatLieu_Service chatLieu_Sv = new ChatLieu_Service();
    private MauSac_Service mauSac_Sv = new MauSac_Service();
    private SanPhamChiTiet_Service spct_server = new SanPhamChiTiet_Service();
    private int id_sp;
    private String srcImg = null;

    public ThongTinSanPham_Dialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.init();

    }

    public ThongTinSanPham_Dialog(java.awt.Frame parent, boolean modal, int id_sp) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.id_sp = id_sp;
        init();
        this.fillTable(spct_server.selectByID_SP(id_sp));

    }
    List<Size> list_Size = size_Sv.selectAll();
    List<ChatLieu> list_ChatLieu = chatLieu_Sv.selectAll();
    List<MauSac> list_Mau = mauSac_Sv.selectAll();

    public void init() {
        this.setInforSP();
        this.fillCboSize();
        this.fillCboChatLieu();
        this.fillCboMau();
        this.hiddenSTT();
    }

    public void setInforSP() {
        SanPham sp = sp_Service.selectByID(id_sp);
        lblMaSp.setText(String.valueOf(id_sp));
        lblTenSanPham.setText(sp.getTenSP());
    }

    public void hiddenSTT() {
        txtTenChatLeu.setVisible(false);
        txtTenMau.setVisible(false);
        txtTenSize.setVisible(false);
        lblErChatLeu.setVisible(false);
        lblErMau.setVisible(false);
        lblErSize.setVisible(false);
        btnSuaChatLieu.setVisible(false);
        btnSuaMau.setVisible(false);
        btnSuaSize.setVisible(false);
        btnThemChatLieu.setVisible(false);
        btnThemMau.setVisible(false);
        btnThemSize.setVisible(false);

    }

    public void fillTable(List<SanPhamChiTiet> list) {
        DefaultTableModel tblMD = (DefaultTableModel) this.tblSanPhamChiTiet.getModel();
        tblMD.setRowCount(0);
        for (int i = 0; i < list.size(); i++) {
            SanPhamChiTiet spct = list.get(i);

            tblMD.addRow(new Object[]{
                spct.getId_SanPhamChiTiet(),
                spct.getGia(),
                mauSac_Sv.selectByID(spct.getId_Mau()).getTenMau(),
                size_Sv.selectByID(spct.getId_Size()).getTenSize(),
                chatLieu_Sv.selectByID(spct.getId_ChatLieu()).getTenChatLieu(),
                spct.getTrangThai()
            });
        }
    }

    public boolean checkItem() {
        List<SanPhamChiTiet> list = spct_server.selectAll();
        SanPhamChiTiet spctN = getForm();
        for (SanPhamChiTiet spct : list) {
            if (spct.getId_SanPham() == id_sp && spct.getId_ChatLieu() == spctN.getId_ChatLieu()
                    && spct.getId_Mau() == spctN.getId_Mau()
                    && spct.getId_Size() == spctN.getId_Size()) {
                MsgBox.alert(this, "Chi tiết sản phẩm đã tồn tại!");
                return false;
            }
        }
        return true;
    }

    public void fillCboSize() {
        DefaultComboBoxModel cboMD = (DefaultComboBoxModel) cboSize.getModel();
        cboMD.removeAllElements();
        for (Size size : list_Size) {
            cboMD.addElement(size);
        }
    }

    public void fillCboChatLieu() {
        DefaultComboBoxModel cboMD = (DefaultComboBoxModel) cboChatLieu.getModel();
        cboMD.removeAllElements();
        for (ChatLieu cl : list_ChatLieu) {
            cboMD.addElement(cl);
        }
    }

    public void fillCboMau() {
        DefaultComboBoxModel cboMD = (DefaultComboBoxModel) cboMauSac.getModel();
        cboMD.removeAllElements();
        for (MauSac ms : list_Mau) {
            cboMD.addElement(ms);
        }
    }

    public SanPhamChiTiet getForm() {
        Size s = (Size) cboSize.getSelectedItem();
        ChatLieu cl = (ChatLieu) cboChatLieu.getSelectedItem();
        MauSac ms = (MauSac) cboMauSac.getSelectedItem();
        String giaC = txtGiaSP.getText().trim();
        srcImg = lblHinhAnh.getToolTipText();
        int tt = 1;
        if (rdoDB.isSelected()) {
            tt = 1;
        } else if (rdoNB.isSelected()) {
            tt = 0;
        }
        double gia = 0;
        if (giaC.isEmpty()) {
            MsgBox.alert(this, "Giá không được bỏ trống");
            return null;
        } else {
            try {
                gia = Double.parseDouble(giaC);
                if (gia < 0) {
                    MsgBox.alert(this, "Giá phải lớn hơn 0");
                    return null;
                }
            } catch (Exception e) {
                MsgBox.alert(this, "Giá phải là số");
                return null;
            }
        }
        
        return new SanPhamChiTiet(ms.getID_MauSac(), cl.getID_ChatLieu(), s.getID_Size(),
                id_sp, 0, gia, srcImg, tt);
    }

    public void themSPCT() {
        SanPhamChiTiet spct = getForm();
        if (spct == null) {
            return;
        }
        if (checkItem() == true) {
            if (MsgBox.confirm(this, "Bạn có chăc chắn muốn thêm?")) {
                if (spct_server.insert(spct) != 0) {
                    MsgBox.alert(this, "Thành công!");
                    this.fillTable(spct_server.selectByID_SP(id_sp));
                } else {
                    MsgBox.alert(this, "Thất bại!");
                }
            }
        }
    }

    public void suaSPCT() {
        SanPhamChiTiet spct = getForm();
        int row = tblSanPhamChiTiet.getSelectedRow();
        int id = Integer.parseInt(tblSanPhamChiTiet.getValueAt(row, 0).toString());
        
        if (spct == null || row < 0) {
            return;
        }
        SanPhamChiTiet spct2 = spct_server.selectByID(id);
        spct2.setHinhAnh(srcImg);
        spct_server.update(spct2, id);
        if (checkItem() == true) {
            if (MsgBox.confirm(this, "Bạn có chăc chắn muốn sửa?")) {
                if (spct_server.update(spct, id) != 0) {
                    MsgBox.alert(this, "Thành công!");
                    this.fillTable(spct_server.selectByID_SP(id_sp));
                } else {
                    MsgBox.alert(this, "Thất bại!");
                }
            }
        }
    }

    public void xoaSPCT() {

        int row = tblSanPhamChiTiet.getSelectedRow();
        int id = Integer.parseInt(tblSanPhamChiTiet.getValueAt(row, 0).toString());
        if (row < 0) {
            return;
        }
        if (MsgBox.confirm(this, "Bạn có chăc chắn muốn xóa?")) {
            if (spct_server.delete(id) != 0) {
                MsgBox.alert(this, "Thành công!");
                this.fillTable(spct_server.selectByID_SP(id_sp));
            } else {
                MsgBox.alert(this, "Thất bại!");
            }
        }
    }

    public void setForm(SanPhamChiTiet spct) {
        cboChatLieu.getModel().setSelectedItem(chatLieu_Sv.selectByID(spct.getId_ChatLieu()));
        cboSize.getModel().setSelectedItem(size_Sv.selectByID(spct.getId_Size()));
        cboMauSac.getModel().setSelectedItem(mauSac_Sv.selectByID(spct.getId_Mau()));
        txtGiaSP.setText(String.valueOf(spct.getGia()));
        if (spct.getTrangThai() == 1) {
            rdoDB.setSelected(true);
        } else if (spct.getTrangThai() == 0) {
            rdoNB.setSelected(true);
        }
        if (spct.getHinhAnh() == null) {
           lblHinhAnh.setIcon(null);
           lblHinhAnh.setToolTipText("");
        }else{
            int w = lblHinhAnh.getWidth();
            int h = lblHinhAnh.getHeight() -lblHinhAnh.getY();
            ImageIcon icon = XImage.read(spct.getHinhAnh());
            Image img = icon.getImage();
            srcImg = spct.getHinhAnh();
            lblHinhAnh.setIcon(new ImageIcon(img.getScaledInstance(w, h, 0)));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgrTT = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lblHinhAnh = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        rdoDB = new javax.swing.JRadioButton();
        rdoNB = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        lblMaSp = new javax.swing.JLabel();
        lblTenSanPham = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtTenMau = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        cboMauSac = new javax.swing.JComboBox<>();
        btnAddMau = new javax.swing.JButton();
        btnThemMau = new javax.swing.JButton();
        lblErMau = new javax.swing.JLabel();
        btnSuaMau = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        cboSize = new javax.swing.JComboBox<>();
        btnAddSize = new javax.swing.JButton();
        txtTenSize = new javax.swing.JTextField();
        btnThemSize = new javax.swing.JButton();
        lblErSize = new javax.swing.JLabel();
        btnSuaSize = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        cboChatLieu = new javax.swing.JComboBox<>();
        btnAddChatLieu = new javax.swing.JButton();
        lblErChatLeu = new javax.swing.JLabel();
        btnThemChatLieu = new javax.swing.JButton();
        btnSuaChatLieu = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        txtTenChatLeu = new javax.swing.JTextField();
        txtGiaSP = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPhamChiTiet = new javax.swing.JTable();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        txtPage = new javax.swing.JTextField();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("THÔNG TIN CHI TIẾT SẢN PHẨM");

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel14.setText("Tên sản phẩm");

        jLabel19.setText("Hình ảnh");

        lblHinhAnh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/addimage.png"))); // NOI18N
        lblHinhAnh.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblHinhAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhAnhMouseClicked(evt);
            }
        });

        btnThem.setBackground(new java.awt.Color(0, 255, 0));
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnReset.setText("Tạo mới");

        jLabel24.setText("Trạng thái");

        bgrTT.add(rdoDB);
        rdoDB.setSelected(true);
        rdoDB.setText("Đang bán");

        bgrTT.add(rdoNB);
        rdoNB.setText("Ngừng bán");

        jLabel1.setText("Mã sản phẩm");

        jLabel15.setText("Màu sắc");

        cboMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnAddMau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/add.png"))); // NOI18N
        btnAddMau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddMauActionPerformed(evt);
            }
        });

        btnThemMau.setText("Thêm ");

        lblErMau.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblErMau.setForeground(new java.awt.Color(255, 0, 0));
        lblErMau.setText("er");

        btnSuaMau.setText("Sửa");
        btnSuaMau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaMauActionPerformed(evt);
            }
        });

        jLabel16.setText("Size");

        cboSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnAddSize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/add.png"))); // NOI18N
        btnAddSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddSizeActionPerformed(evt);
            }
        });

        btnThemSize.setText("Thêm ");

        lblErSize.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblErSize.setForeground(new java.awt.Color(255, 0, 0));
        lblErSize.setText("er");

        btnSuaSize.setText("Sửa");

        jLabel17.setText("Chất liệu");

        cboChatLieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnAddChatLieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/add.png"))); // NOI18N
        btnAddChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddChatLieuActionPerformed(evt);
            }
        });

        lblErChatLeu.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblErChatLeu.setForeground(new java.awt.Color(255, 0, 0));
        lblErChatLeu.setText("er");

        btnThemChatLieu.setText("Thêm ");
        btnThemChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemChatLieuActionPerformed(evt);
            }
        });

        btnSuaChatLieu.setText("Sửa");

        jLabel18.setText("Giá sản phẩm");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnThemMau)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSuaMau))
                    .addComponent(lblErChatLeu)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnThemChatLieu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSuaChatLieu))
                    .addComponent(jLabel18)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtGiaSP, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenChatLeu, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cboChatLieu, javax.swing.GroupLayout.Alignment.LEADING, 0, 198, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnThemSize)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnSuaSize))
                                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboSize, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblErSize, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboMauSac, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtTenMau, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblErMau, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTenSize, javax.swing.GroupLayout.Alignment.LEADING))
                                .addComponent(jLabel17)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAddMau, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAddSize, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAddChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddSize, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cboSize, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenSize, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblErSize)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThemSize, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSuaSize, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAddMau, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTenMau, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblErMau)
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemMau, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaMau, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTenChatLeu, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblErChatLeu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtGiaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblMaSp))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTenSanPham)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel19)
                        .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                        .addComponent(lblHinhAnh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel24))
                    .addComponent(rdoDB)
                    .addComponent(rdoNB))
                .addGap(26, 26, 26))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel1)
                    .addComponent(lblMaSp))
                .addGap(9, 9, 9)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoDB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoNB)
                        .addGap(46, 46, 46)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(lblTenSanPham))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblSanPhamChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID_SPCT", "Giá bán", "Màu sắc", "Size", "Chất liệu", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPhamChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamChiTietMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPhamChiTiet);

        btnFirst.setText("|<<");

        btnPrev.setText("<<");

        btnNext.setText(">>");

        btnLast.setText(">>|");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPage, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(140, 140, 140)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnFirst)
                            .addComponent(btnPrev)
                            .addComponent(txtPage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNext)
                            .addComponent(btnLast)))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSuaMauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaMauActionPerformed

    }//GEN-LAST:event_btnSuaMauActionPerformed

    private void btnThemChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemChatLieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemChatLieuActionPerformed

    private void btnAddSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddSizeActionPerformed
        if (txtTenSize.isVisible()) {
            txtTenSize.setVisible(false);
            btnThemSize.setVisible(false);
            btnSuaSize.setVisible(false);
        } else {
            txtTenSize.setVisible(true);
            btnThemSize.setVisible(true);
            btnSuaSize.setVisible(true);
        }
    }//GEN-LAST:event_btnAddSizeActionPerformed

    private void btnAddMauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddMauActionPerformed
        if (txtTenMau.isVisible()) {
            txtTenMau.setVisible(false);
            btnThemMau.setVisible(false);
            btnSuaMau.setVisible(false);
        } else {
            txtTenMau.setVisible(true);
            btnThemMau.setVisible(true);
            btnSuaMau.setVisible(true);
        }
    }//GEN-LAST:event_btnAddMauActionPerformed

    private void btnAddChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddChatLieuActionPerformed
        if (txtTenChatLeu.isVisible()) {
            txtTenChatLeu.setVisible(false);
            btnThemChatLieu.setVisible(false);
            btnSuaChatLieu.setVisible(false);
        } else {
            txtTenChatLeu.setVisible(true);
            btnThemChatLieu.setVisible(true);
            btnSuaChatLieu.setVisible(true);
        }
    }//GEN-LAST:event_btnAddChatLieuActionPerformed

    private void tblSanPhamChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamChiTietMouseClicked
        int row = tblSanPhamChiTiet.getSelectedRow();
        int id = Integer.parseInt(tblSanPhamChiTiet.getValueAt(row, 0).toString());
        SanPhamChiTiet spct = spct_server.selectByID(id);
        setForm(spct);
    }//GEN-LAST:event_tblSanPhamChiTietMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        themSPCT();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        suaSPCT();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        xoaSPCT();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void lblHinhAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhAnhMouseClicked

        try {
            JFileChooser jc = new JFileChooser();
            jc.showDialog(this, "Chọn ảnh sản phẩm!");
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
            java.util.logging.Logger.getLogger(ThongTinSanPham_Dialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThongTinSanPham_Dialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThongTinSanPham_Dialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThongTinSanPham_Dialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ThongTinSanPham_Dialog dialog = new ThongTinSanPham_Dialog(new javax.swing.JFrame(), true);
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
    private javax.swing.ButtonGroup bgrTT;
    private javax.swing.JButton btnAddChatLieu;
    private javax.swing.JButton btnAddMau;
    private javax.swing.JButton btnAddSize;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSuaChatLieu;
    private javax.swing.JButton btnSuaMau;
    private javax.swing.JButton btnSuaSize;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemChatLieu;
    private javax.swing.JButton btnThemMau;
    private javax.swing.JButton btnThemSize;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboChatLieu;
    private javax.swing.JComboBox<String> cboMauSac;
    private javax.swing.JComboBox<String> cboSize;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblErChatLeu;
    private javax.swing.JLabel lblErMau;
    private javax.swing.JLabel lblErSize;
    private javax.swing.JLabel lblHinhAnh;
    private javax.swing.JLabel lblMaSp;
    private javax.swing.JLabel lblTenSanPham;
    private javax.swing.JRadioButton rdoDB;
    private javax.swing.JRadioButton rdoNB;
    private javax.swing.JTable tblSanPhamChiTiet;
    private javax.swing.JTextField txtGiaSP;
    private javax.swing.JTextField txtPage;
    private javax.swing.JTextField txtTenChatLeu;
    private javax.swing.JTextField txtTenMau;
    private javax.swing.JTextField txtTenSize;
    // End of variables declaration//GEN-END:variables
}
