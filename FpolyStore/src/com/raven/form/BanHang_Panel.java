package com.raven.form;

import com.fsore.untils.MsgBox;
import com.fsore.untils.XDate;
import com.fstore.model.DanhMuc;
import com.fstore.model.HoaDon;
import com.fstore.model.HoaDon_ChiTiet;
import com.fstore.model.KhachHang;
import com.fstore.model.Sale_ChiTiet;
import com.fstore.model.SanPham;
import com.fstore.model.SanPhamChiTiet;
import com.fstore.model.Voucher;
import com.fstore.model.Voucher_ChiTiet;
import com.fstore.service.ChatLieu_Service;
import com.fstore.service.DanhMuc_Service;
import com.fstore.service.HoaDonChiTiet_Service;
import com.fstore.service.HoaDon_Service;
import com.fstore.service.KhachHang_Service;
import com.fstore.service.MauSac_Service;
import com.fstore.service.Sale_CT_Service;
import com.fstore.service.SanPhamChiTiet_Service;
import com.fstore.service.SanPham_Service;
import com.fstore.service.Size_Service;
import com.fstore.service.Voucher_CT_Service;
import com.fstore.service.Voucher_Service;
import com.ui.main.Main;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class BanHang_Panel extends javax.swing.JPanel {

    private HoaDon_Service hd_Service = new HoaDon_Service();
    private HoaDonChiTiet_Service hdct_Service = new HoaDonChiTiet_Service();
    private SanPham_Service sanPham_Service = new SanPham_Service();
    private KhachHang_Service khachHang_Service = new KhachHang_Service();
    private SanPhamChiTiet_Service spct_Service = new SanPhamChiTiet_Service();
    private ChatLieu_Service chatLieu_Service = new ChatLieu_Service();
    private MauSac_Service mauSac_Service = new MauSac_Service();
    private Size_Service size_Service = new Size_Service();
    private List<HoaDon_ChiTiet> listGioHang = new ArrayList<>();
    private Sale_CT_Service scT_Service = new Sale_CT_Service();
    private Voucher_Service voucher_Service = new Voucher_Service();
    private Voucher_CT_Service voucher_CT_Service = new Voucher_CT_Service();
    private int search = 0;
    private DanhMuc_Service dm_Service = new DanhMuc_Service();

    public BanHang_Panel() {
        initComponents();
        init();
    }

    public void init() {
        setHiden();
        fillTableHoaDonCho(hd_Service.selectByTrangThai(3));
        fillTableSanPham(spct_Service.selectAll());
        fillCbbVoucher();
        fillCbbDanhMuc();
    }

    public void setHiden() {
        lblMaVoucher.setVisible(false);
        cbbVoucher.setVisible(false);
        btnChonKH.setEnabled(false);
        btnThanhToan.setEnabled(false);
        btnHuyHD.setEnabled(false);
        lblSoTienGiam.setVisible(false);
        lblTitleSoTienGiam.setVisible(false);
        lblErTienKhach.setVisible(false);
        btnThemGH.setEnabled(false);
        btnXoaGH.setEnabled(false);
    }

    public void fillTableHoaDonCho(List<HoaDon> list) {
        DefaultTableModel tblMd = (DefaultTableModel) tblHoaDonCho.getModel();
        tblMd.setRowCount(0);
        for (HoaDon hd : list) {
            tblMd.addRow(new Object[]{
                hd.getId_HoaDon(),
                hd.getNgayTao(),
                hd.getId_NhanVien(),
                hd.getTenKH()
            });
        }
    }

    public void fillTableSanPham(List<SanPhamChiTiet> list) {
        DefaultTableModel tblMd = (DefaultTableModel) tblSanPham.getModel();
        tblMd.setRowCount(0);
        for (SanPhamChiTiet spct : list) {
            tblMd.addRow(new Object[]{
                spct.getId_SanPhamChiTiet(),
                sanPham_Service.selectByID(spct.getId_SanPham()).getTenSP(),
                spct.getGia(),
                getGiamGia(spct.getId_SanPhamChiTiet(), new Date()),
                size_Service.selectByID(spct.getId_Size()).getTenSize(),
                mauSac_Service.selectByID(spct.getId_Mau()).getTenMau(),
                chatLieu_Service.selectByID(spct.getId_ChatLieu()).getTenChatLieu(),
                spct.getSoLuong() - spct_Service.getSoLuongSanPhamHDC(3, spct.getId_SanPhamChiTiet())
            });

        }
    }

    public void fillTableGioHang(List<HoaDon_ChiTiet> list) {
        DefaultTableModel tblMd = (DefaultTableModel) tblGioHang.getModel();
        tblMd.setRowCount(0);
        for (HoaDon_ChiTiet hdct : list) {
            tblMd.addRow(new Object[]{
                hdct.getId_SanPhamChiTiet(),
                //"sp",
                lblTenSanPham.getText(),
                hdct.getGia(),
                hdct.getSoLuong()
            });
        }
    }

    public HoaDon_ChiTiet getHoaDon_CT() {
        int row = tblSanPham.getSelectedRow();
        int id = Integer.parseInt(tblSanPham.getValueAt(row, 0).toString());

        SanPhamChiTiet spct = spct_Service.selectByID(id);
        String soLuongT = txtSoLuong.getText().trim();
        int soLuong = 0;
        if (soLuongT.isEmpty()) {
            MsgBox.alert(this, "Số lượng không dược bỏ trống!");
            return null;
        } else {
            try {
                soLuong = Integer.parseInt(soLuongT);
                if (soLuong <= 0) {
                    MsgBox.alert(this, "Số lượng phải phải lớn hơn 0!");
                    return null;
                }
            } catch (Exception e) {
                MsgBox.alert(this, "Số lượng phải là số!");
                return null;
            }
            if (soLuong > Integer.parseInt(tblSanPham.getValueAt(row, 7).toString())) {
                MsgBox.alert(this, "Số lượng bán nhiều hơn số lượng trong kho!");
                return null;
            } else {
                for (int i = 0; i < listGioHang.size(); i++) {
                    HoaDon_ChiTiet get = listGioHang.get(i);
                    if (get.getId_SanPhamChiTiet() == id) {
                        get.setSoLuong(get.getSoLuong() + soLuong);
                        int soLuongTon = Integer.parseInt(tblSanPham.getValueAt(row, 7).toString()) - soLuong;
                        tblSanPham.setValueAt(soLuongTon, row, 7);
                        return null;
                    }

                }
            }
        }

        double gia = spct.getGia() - Double.parseDouble(tblSanPham.getValueAt(row, 3).toString());
        return new HoaDon_ChiTiet(Integer.parseInt(lblMaHD.getText()), id, soLuong, gia, 1);

    }

    public HoaDon newHoaDon() {
        Calendar calendar = Calendar.getInstance();
        String tenKH = "Vui lòng chọn!";
        String maNhanVien = "ThongHT";
        String ngayTao = XDate.toString(calendar.getTime(), "hh:mm:ss aa yyyy-MM-dd");
        return new HoaDon(tenKH, 3, 1, maNhanVien, ngayTao);
    }

    public void taoHoaDon() {
        HoaDon hd = newHoaDon();
        if (MsgBox.confirm(this, "Bạn muốn tạo hóa đơn mới?")) {
            if (hd_Service.insert(hd) != 0) {
                MsgBox.alert(this, "Tạo hóa đơn thành công");
                this.fillTableHoaDonCho(hd_Service.selectByTrangThai(3));
            }
        }
    }

    public void setID_HoaDon(HoaDon hd) {
        lblMaHD.setText(String.valueOf(hd.getId_HoaDon()));
        if (lblMaHD.getText().length() > 0) {
            btnChonKH.setEnabled(true);
            btnHuyHD.setEnabled(true);
            btnThanhToan.setEnabled(true);
        } else {
            btnChonKH.setEnabled(false);
            btnHuyHD.setEnabled(false);
            btnThanhToan.setEnabled(false);
        }
    }

    public double tongTien() {
        double tong = 0;
        int soDong = tblGioHang.getRowCount();
        for (int i = 0; i < soDong; i++) {
            tong += Double.parseDouble(tblGioHang.getValueAt(i, 2).toString())
                    * Integer.parseInt(tblGioHang.getValueAt(i, 3).toString());
        }
        return tong;
    }

    public double tongTienTT() {

        double tienGiam = Double.parseDouble(lblSoTienGiam.getText());

        return tongTien() - tienGiam;
    }

    public void insert(int idHD) {

        for (HoaDon_ChiTiet hdct : hdct_Service.selectByID_HoaDon(idHD)) {
            HoaDon_ChiTiet hdct2 = getExistingDetail(listGioHang, hdct);
            if (hdct2 == null) {
                hdct.setTrangThai(3);
                hdct_Service.update(hdct, hdct.getId_HoaDonCT());
                System.out.println("de" + hdct.getId_SanPhamChiTiet());
            }
        }

        for (HoaDon_ChiTiet hdct : listGioHang) {
            HoaDon_ChiTiet existingDetail = getExistingDetail(hdct_Service.selectByID_HoaDon(idHD), hdct);

            if (existingDetail != null) {
                // Phần tử đã tồn tại trong cơ sở dữ liệu, cập nhật nó
                hdct.setTrangThai(1);
                hdct_Service.update(hdct, hdct_Service.selectID_HDCT(idHD, hdct.getId_SanPhamChiTiet()));
            } else {
                // Phần tử không tồn tại, thêm mới nó
                hdct_Service.insert(hdct);
            }
        }

    }

    private HoaDon_ChiTiet getExistingDetail(List<HoaDon_ChiTiet> existingDetails, HoaDon_ChiTiet target) {
        for (HoaDon_ChiTiet existingDetail : existingDetails) {
            if (existingDetail.getId_SanPhamChiTiet() == (target.getId_SanPhamChiTiet())) {
                //System.out.println("trùng"+existingDetail.getId_HoaDonCT());
                return existingDetail;
            }
        }
        return null; // Trả về null nếu không tìm thấy
    }

    public void delete() {
        DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();
        int selectedRow = tblGioHang.getSelectedRow();

        if (selectedRow >= 0) {
            int productId = (int) tblGioHang.getValueAt(selectedRow, 0);

            for (int i = 0; i < tblSanPham.getRowCount(); i++) {
                // Kiểm tra điều kiện so sánh giá trị tương ứng trong tblSanPham và tblGioHang
                if (tblSanPham.getValueAt(i, 0).equals(tblGioHang.getValueAt(selectedRow, 0))) {
                    int soLuong = Integer.parseInt(tblSanPham.getValueAt(i, 7).toString())
                            + Integer.parseInt(tblGioHang.getValueAt(selectedRow, 3).toString());
                    tblSanPham.setValueAt(soLuong, i, 7);
                }
            }

            for (int j = 0; j < listGioHang.size(); j++) {
                HoaDon_ChiTiet hdct = listGioHang.get(j);
                if (hdct.getId_SanPhamChiTiet() == productId) {
                    model.removeRow(selectedRow);
                    listGioHang.remove(hdct);
                    break;
                }
            }
        }
    }

    public void updatSoLuong(int soLuongNew) {
        DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();
        int selectedRow = tblGioHang.getSelectedRow();

        if (selectedRow >= 0) {
            int productId = (int) tblGioHang.getValueAt(selectedRow, 0);
            int soLuongCu = (int) tblGioHang.getValueAt(selectedRow, 3);
            for (int i = 0; i < tblSanPham.getRowCount(); i++) {
                // Kiểm tra điều kiện so sánh giá trị tương ứng trong tblSanPham và tblGioHang
                if (tblSanPham.getValueAt(i, 0).equals(tblGioHang.getValueAt(selectedRow, 0))) {
                    if (soLuongNew > soLuongCu) {
                        int soLuong = Integer.parseInt(tblSanPham.getValueAt(i, 7).toString())
                                - (soLuongNew - soLuongCu);
                        tblSanPham.setValueAt(soLuong, i, 7);
                    } else {
                        int soLuong = Integer.parseInt(tblSanPham.getValueAt(i, 7).toString())
                                + (soLuongCu - soLuongNew);
                        tblSanPham.setValueAt(soLuong, i, 7);
                    }

                }
            }

            for (int j = 0; j < listGioHang.size(); j++) {
                HoaDon_ChiTiet hdct = listGioHang.get(j);
                hdct.setSoLuong(soLuongNew);
                if (hdct.getId_SanPhamChiTiet() == productId) {
                    listGioHang.set(j, hdct);
                    break;
                }
            }
        }
    }

    public void fillCbbVoucher() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbbVoucher.getModel();
        model.removeAllElements();
        model.addElement(null);
        List<Voucher> list = voucher_Service.selectByDate(new Date());
        for (Voucher v : list) {
            model.addElement(v);
        }
    }

    public HoaDon thanhToan() {
        HoaDon hd = hd_Service.selectByID(Integer.parseInt(lblMaHD.getText()));
        int ID_KH = Integer.parseInt(lblMaKH.getText());
        if (ID_KH == 1) {
            MsgBox.alert(this, "Vui lòng chọn khách hàng!");
            return null;
        }
        if (listGioHang.size() < 0) {
            MsgBox.alert(this, "Bạn chưa chọn sản phẩm nào!");
            return null;
        }
        KhachHang kh = khachHang_Service.selectByID(ID_KH);
        String tienKDuaT = txtTenKDua.getText();
        double tienKDua = 0;
        if (tienKDuaT.isEmpty()) {
            MsgBox.alert(this, "Vui lòng nhập tiền khách đưa!");
            return null;
        } else {
            try {
                tienKDua = Double.parseDouble(tienKDuaT);
            } catch (Exception e) {
                MsgBox.alert(this, " Tiền khách đưa phải là số!");
                return null;
            }
        }

        double tongTien = Double.parseDouble(lblTienThanhToan.getText());
        if (!cbApVoucher.isSelected()) {
            hd.setVoucher(0);
        } else {

            Voucher v = (Voucher) cbbVoucher.getSelectedItem();
            hd.setVoucher(v.getId_Voucher());
            Voucher_ChiTiet vct = new Voucher_ChiTiet();
            vct.setId_KhachHang(ID_KH);
            vct.setId_HoaDon(hd.getId_HoaDon());
            vct.setId_Voucher(v.getId_Voucher());
            vct.setTrangThai(1);
            vct.setSoTienGiam(Double.parseDouble(lblSoTienGiam.getText()));
            voucher_CT_Service.insert(vct);
            voucher_Service.updateSoLuong(v.getId_Voucher());
        }

        int hinhThucTT = cbbHTTT.getSelectedIndex();
        hd.setId_KhachHang(ID_KH);
        hd.setTenKH(kh.getTen());
        hd.setSdt(kh.getsDT());
        hd.setTongTien(tongTien);
        hd.setHinhThucThanhToan(hinhThucTT);
        hd.setGhiChu(txtGhiChu.getText());
        hd.setTrangThai(1);
        for (HoaDon_ChiTiet hdct : listGioHang) {
            spct_Service.updateSoLuong(hdct.getSoLuong(), hdct.getId_SanPhamChiTiet());
        }
        return hd;
    }

    public void huyHoaDon() {
        HoaDon hd = hd_Service.selectByID(Integer.parseInt(lblMaHD.getText()));
        hd.setTrangThai(2);
        hd_Service.update(hd, hd.getId_HoaDon());
        fillTableHoaDonCho(hd_Service.selectByTrangThai(3));
    }

    public void resetFormHD() {
        lblMaHD.setText("");
        txtTenKDua.setText("");
        txtGhiChu.setText("");
    }

    public void keyNhapTienKhach() {
        String tienKDuaT = txtTenKDua.getText();
        String thanhToanT = String.format("%.0f", Double.parseDouble(lblTienThanhToan.getText()));
        double tienKDua = 0;
        double thanhToan = Double.parseDouble(thanhToanT);
        try {
            tienKDua = Double.parseDouble(tienKDuaT);
            lblErTienKhach.setVisible(false);
        } catch (Exception e) {
            lblErTienKhach.setText("Tiền khách đưa phải là só!");
            lblErTienKhach.setVisible(true);
        }
        if (tienKDua < thanhToan) {
            lblErTienKhach.setText("Tiền khách đưa thấp hơn số tiền phải thanh toán!");
            lblErTienKhach.setVisible(true);
        } else {
            lblErTienKhach.setVisible(false);
        }
        DecimalFormat decimalFormat = new DecimalFormat("#,##0");

        lblTienThua.setText(decimalFormat.format(tienKDua - thanhToan));
    }

    //Lấy ra số tiền giảm
    public double getGiamGia(int id_SPCT, Date d) {
        Sale_ChiTiet sct = scT_Service.selectByID_SPCT(id_SPCT, d);
        SanPhamChiTiet spct = spct_Service.selectByID(id_SPCT);
        double giaGiam = 0;
        if (sct == null) {
            return 0;
        }
        if (sct.getHinhThucGiam() == 0) {
            giaGiam = sct.getGiaTriGiam();
        } else if (sct.getHinhThucGiam() == 1) {
            giaGiam = spct.getGia() * (sct.getGiaTriGiam() / 100);
        }
        return giaGiam;
    }

    public double getTienThanhToan() {
        double tongTien = Double.parseDouble(lblTongTien.getText());
        double giamGia = Double.parseDouble(lblSoTienGiam.getText());
        return tongTien - giamGia;
    }

    public void search() {
        List<SanPhamChiTiet> list = new ArrayList<>();
        if (search == 0) {
            String idT = txtSearch.getText().trim();
            int id = 0;
            try {
                id = Integer.parseInt(idT);
            } catch (Exception e) {
            }
            SanPhamChiTiet sp = spct_Service.selectByID(id);
            if (sp != null) {
                list.add(sp);
            }
        } else if (search == 1) {
            String ten = txtSearch.getText().trim();
            list = spct_Service.selectByName(ten);
        }
        fillTableSanPham(list);
    }

    public void fillCbbDanhMuc() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbbDanhMuc.getModel();
        model.removeAllElements();
        model.addElement("Tất cả");
        for (DanhMuc dm : dm_Service.selectAll()) {
            model.addElement(dm);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        lblMaKH = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblTenKH = new javax.swing.JLabel();
        btnChonKH = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        lblMaHD = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        cbbHTTT = new javax.swing.JComboBox<>();
        lblTienThanhToan = new javax.swing.JLabel();
        txtTenKDua = new javax.swing.JTextField();
        lblTienThua = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        btnHuyHD = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();
        txtTenHD = new javax.swing.JButton();
        lblTongTien = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        lblMaVoucher = new javax.swing.JLabel();
        cbbVoucher = new javax.swing.JComboBox<>();
        lblTitleSoTienGiam = new javax.swing.JLabel();
        lblSoTienGiam = new javax.swing.JLabel();
        cbApVoucher = new javax.swing.JCheckBox();
        lblErTienKhach = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        btnThemGH = new javax.swing.JButton();
        btnXoaGH = new javax.swing.JButton();
        lblTenSanPham = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        cbbTkSanPham = new javax.swing.JComboBox<>();
        txtSearch = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        cbbDanhMuc = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHoaDonCho = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("BÁN HÀNG");

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setText("Mã khách hàng:");

        lblMaKH.setText("1");

        jLabel10.setText("Tên khách hàng:");

        lblTenKH.setText("Vui lòng chọn!");

        btnChonKH.setBackground(new java.awt.Color(204, 204, 0));
        btnChonKH.setFont(new java.awt.Font("Segoe UI", 1, 8)); // NOI18N
        btnChonKH.setText("Chọn");
        btnChonKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonKHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblMaKH))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTenKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnChonKH, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnChonKH, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(lblMaKH))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(lblTenKH))))
                .addGap(31, 31, 31))
        );

        jLabel12.setText("Mã hóa đơn");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Tổng tiền");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setText("Thanh toán");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("Tiền khách đưa");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("Tiền thừa");

        jLabel20.setText("Hình thức thanh toán");

        cbbHTTT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Thanh toán tiền mặt", "Chuyển khoản" }));

        lblTienThanhToan.setText("0.0");

        txtTenKDua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTenKDuaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTenKDuaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTenKDuaKeyTyped(evt);
            }
        });

        lblTienThua.setText("0.0");

        jLabel24.setText("Ghi chú");

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane4.setViewportView(txtGhiChu);

        btnHuyHD.setBackground(new java.awt.Color(255, 0, 0));
        btnHuyHD.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnHuyHD.setForeground(new java.awt.Color(255, 255, 255));
        btnHuyHD.setText("Hủy hóa đơn");
        btnHuyHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyHDActionPerformed(evt);
            }
        });

        btnThanhToan.setBackground(new java.awt.Color(51, 204, 0));
        btnThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        txtTenHD.setBackground(new java.awt.Color(51, 255, 51));
        txtTenHD.setText("Tạo hóa đơn");
        txtTenHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenHDActionPerformed(evt);
            }
        });

        lblTongTien.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTongTien.setForeground(new java.awt.Color(255, 0, 0));
        lblTongTien.setText("0.0");

        lblMaVoucher.setText("Mã voucher");

        cbbVoucher.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbVoucher.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbVoucherItemStateChanged(evt);
            }
        });
        cbbVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbVoucherActionPerformed(evt);
            }
        });

        lblTitleSoTienGiam.setText("Số tiền giảm");

        lblSoTienGiam.setText("0.0");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMaVoucher)
                    .addComponent(lblTitleSoTienGiam))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(lblSoTienGiam)
                        .addGap(132, 132, 132))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(cbbVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaVoucher)
                    .addComponent(cbbVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitleSoTienGiam)
                    .addComponent(lblSoTienGiam))
                .addGap(0, 6, Short.MAX_VALUE))
        );

        cbApVoucher.setText("Sử dụng voucher? ");
        cbApVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbApVoucherActionPerformed(evt);
            }
        });

        lblErTienKhach.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        lblErTienKhach.setForeground(new java.awt.Color(255, 0, 0));
        lblErTienKhach.setText("jLabel6");

        jButton1.setBackground(new java.awt.Color(204, 204, 0));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setText("Lưu tạm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbApVoucher)
                            .addComponent(lblErTienKhach))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(lblMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTenHD)
                        .addGap(15, 15, 15))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addComponent(btnHuyHD, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel17)
                                            .addComponent(jLabel18))
                                        .addGap(75, 75, 75))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel20)
                                            .addComponent(jLabel19))
                                        .addGap(42, 42, 42)))
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbbHTTT, 0, 150, Short.MAX_VALUE)
                                    .addComponent(lblTienThanhToan)
                                    .addComponent(txtTenKDua)
                                    .addComponent(lblTienThua))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(lblMaHD))
                    .addComponent(txtTenHD, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(lblTongTien))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbApVoucher)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(lblTienThanhToan))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtTenKDua, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblErTienKhach)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTienThua)
                    .addComponent(jLabel19))
                .addGap(28, 28, 28)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(cbbHTTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHuyHD, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID_SPCT", "Tên sản phẩm", "Đơn giá", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblGioHang.setSelectionBackground(new java.awt.Color(0, 0, 0));
        tblGioHang.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tblGioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGioHangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblGioHang);

        jLabel3.setText("Tên sản phẩm");

        jLabel4.setText("Số lượng");

        btnThemGH.setBackground(new java.awt.Color(0, 255, 0));
        btnThemGH.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnThemGH.setText("Thêm vào dánh sách mua");
        btnThemGH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemGHActionPerformed(evt);
            }
        });

        btnXoaGH.setBackground(new java.awt.Color(204, 0, 0));
        btnXoaGH.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnXoaGH.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaGH.setText("Xóa khỏi danh sách");
        btnXoaGH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaGHActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Danh sách sản phẩm mua");

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID_SPCT", "Tên sản phẩm", "Giá bán", "Giảm giá", "Size", "Màu", "Chất liệu", "Số lượng "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.setSelectionBackground(new java.awt.Color(0, 0, 0));
        tblSanPham.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Tìm kiếm theo");

        cbbTkSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã", "Tên" }));
        cbbTkSanPham.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbTkSanPhamItemStateChanged(evt);
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

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel25.setText("Danh mục");

        cbbDanhMuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbDanhMuc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbDanhMucItemStateChanged(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Danh sách sản phẩm");

        tblHoaDonCho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hóa đơn", "Ngày tạo", "Nhân viên tạo", "Khách hàng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDonCho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonChoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblHoaDonCho);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Đơn hàng chờ");

        jButton2.setText("Tìm");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnXoaGH, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(lblTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(70, 70, 70)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 733, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnThemGH)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cbbTkSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton2)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel25)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbbDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 732, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(28, 28, 28)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbTkSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThemGH))
                        .addComponent(lblTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnXoaGH, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTenHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenHDActionPerformed
        if (lblMaHD.getText().length() > 0) {
            insert(Integer.parseInt(lblMaHD.getText()));
        }
        taoHoaDon();
    }//GEN-LAST:event_txtTenHDActionPerformed

    private void btnChonKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonKHActionPerformed
        new ThongTinKhachHang_Dialog(new Main(), true, lblMaKH, lblTenKH).setVisible(true);

    }//GEN-LAST:event_btnChonKHActionPerformed

    private void cbApVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbApVoucherActionPerformed
        if (cbbVoucher.isVisible()) {
            lblMaVoucher.setVisible(false);
            cbbVoucher.setVisible(false);
            lblSoTienGiam.setVisible(false);
            lblTitleSoTienGiam.setVisible(false);
        } else {
            lblMaVoucher.setVisible(true);
            cbbVoucher.setVisible(true);
            lblSoTienGiam.setVisible(true);
            lblTitleSoTienGiam.setVisible(true);
        }
        if (!cbApVoucher.isSelected()) {
            lblSoTienGiam.setText("0");
            lblTienThanhToan.setText(getTienThanhToan() + "");
        }
    }//GEN-LAST:event_cbApVoucherActionPerformed

    private void cbbVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbVoucherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbVoucherActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int id_hoaDon = Integer.parseInt(lblMaHD.getText());
        int id_Kh = Integer.parseInt(lblMaKH.getText());
        HoaDon hd = hd_Service.selectByID(id_hoaDon);
        KhachHang kh = khachHang_Service.selectByID(id_Kh);
        hd.setId_KhachHang(kh.getId_KhachHang());
        hd.setTenKH(kh.getTen());
        hd_Service.update(hd, id_hoaDon);
        insert(id_hoaDon);
        listGioHang.clear();
        fillTableHoaDonCho(hd_Service.selectByTrangThai(3));
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnHuyHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyHDActionPerformed
        if (MsgBox.confirm(this, "Bạn có chắc chắn muốn hủy")) {
            huyHoaDon();
            resetFormHD();
        }
    }//GEN-LAST:event_btnHuyHDActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        HoaDon hd = thanhToan();
        if (hd != null) {
            hd_Service.update(hd, hd.getId_HoaDon());
            insert(Integer.parseInt(lblMaHD.getText()));
            listGioHang.clear();
            resetFormHD();
            MsgBox.alert(this, "Thanh toán thành công");
            fillTableHoaDonCho(hd_Service.selectByTrangThai(3));
            new ChiTietHoaDon_Jdialog(new Main(), true, hd.getId_HoaDon()).setVisible(true);
        }

    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void txtTenKDuaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenKDuaKeyPressed
        keyNhapTienKhach();
    }//GEN-LAST:event_txtTenKDuaKeyPressed

    private void txtTenKDuaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenKDuaKeyTyped
        keyNhapTienKhach();
    }//GEN-LAST:event_txtTenKDuaKeyTyped

    private void txtTenKDuaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenKDuaKeyReleased
        keyNhapTienKhach();
    }//GEN-LAST:event_txtTenKDuaKeyReleased

    private void cbbVoucherItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbVoucherItemStateChanged
        if (cbApVoucher.isSelected()) {
            Voucher v = (Voucher) cbbVoucher.getSelectedItem();
            if (v != null) {
                double tongTien = Double.parseDouble(lblTongTien.getText());
                if (v.getHinhThucGiam() == 0) {
                    lblSoTienGiam.setText(String.valueOf(v.getGiaTriGiam()));
                    lblTienThanhToan.setText(String.valueOf(getTienThanhToan()));
                } else {
                    double tienGiam = tongTien * (v.getGiaTriGiam() / 100);
                    lblSoTienGiam.setText(String.valueOf(tienGiam));
                    lblTienThanhToan.setText(String.valueOf(getTienThanhToan()));
                }
            }
        } else {
            lblSoTienGiam.setText("0");
        }
    }//GEN-LAST:event_cbbVoucherItemStateChanged

    private void tblGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGioHangMouseClicked
        btnXoaGH.setEnabled(true);
        if (evt.getClickCount() == 2 && tblGioHang.getSelectedColumn() == 3
                && tblGioHang.getSelectedRow() != -1) {
            String soluongT = MsgBox.prompt(this, "Số lượng mới?");
            try {
                int soluong = Integer.parseInt(soluongT);
                updatSoLuong(soluong);
                fillTableGioHang(listGioHang);
                lblTongTien.setText(String.valueOf(tongTien()) + "  VNĐ");
                lblTienThanhToan.setText(String.valueOf(tongTienTT()));
            } catch (Exception e) {
                MsgBox.alert(this, "Số lương mới phải là số");
            }
        }

    }//GEN-LAST:event_tblGioHangMouseClicked

    private void btnThemGHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemGHActionPerformed
        if (lblMaHD.getText().length() > 0) {
            HoaDon_ChiTiet hdct = getHoaDon_CT();
            if (hdct == null) {
                fillTableGioHang(listGioHang);
                return;
            }
            System.out.println(hdct.getId_SanPhamChiTiet());
            listGioHang.add(hdct);
            fillTableGioHang(listGioHang);
            txtSoLuong.setText("");
            int row = tblSanPham.getSelectedRow();
            int soLuongTon = Integer.parseInt(tblSanPham.getValueAt(row, 7).toString()) - hdct.getSoLuong();
            tblSanPham.setValueAt(soLuongTon, row, 7);
            tblSanPham.clearSelection();
            lblTongTien.setText(String.valueOf(tongTien()));
            lblTienThanhToan.setText(String.valueOf(getTienThanhToan()));
            btnThemGH.setEnabled(false);
        } else {
            MsgBox.alert(this, "Vui lòng tạo hóa đơn trước!");
        }
    }//GEN-LAST:event_btnThemGHActionPerformed

    private void btnXoaGHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaGHActionPerformed
        delete();
        fillTableGioHang(listGioHang);
        lblTongTien.setText(String.valueOf(tongTien()) + "  VNĐ");
        lblTienThanhToan.setText(String.valueOf(tongTienTT()));
        btnXoaGH.setEnabled(false);
    }//GEN-LAST:event_btnXoaGHActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        int row = tblSanPham.getSelectedRow();
        lblTenSanPham.setText(tblSanPham.getValueAt(row, 1).toString());
        btnThemGH.setEnabled(true);
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void tblHoaDonChoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonChoMouseClicked
        int row = tblHoaDonCho.getSelectedRow();
        int id = Integer.parseInt(tblHoaDonCho.getValueAt(row, 0).toString());
        HoaDon hd = hd_Service.selectByID(id);
        setID_HoaDon(hd);

        listGioHang = hdct_Service.selectByID_HoaDonTT(id, 1);

        fillTableGioHang(listGioHang);
        lblTongTien.setText(String.valueOf(tongTien()));
        lblTienThanhToan.setText(String.valueOf(tongTienTT()));
        lblMaKH.setText(String.valueOf(hd.getId_KhachHang()));
        lblTenKH.setText(hd.getTenKH());
    }//GEN-LAST:event_tblHoaDonChoMouseClicked

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

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
        //search();
    }//GEN-LAST:event_txtSearchKeyPressed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        //search();
    }//GEN-LAST:event_txtSearchKeyReleased

    private void txtSearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyTyped
        //search();
    }//GEN-LAST:event_txtSearchKeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        search();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChonKH;
    private javax.swing.JButton btnHuyHD;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThemGH;
    private javax.swing.JButton btnXoaGH;
    private javax.swing.JCheckBox cbApVoucher;
    private javax.swing.JComboBox<String> cbbDanhMuc;
    private javax.swing.JComboBox<String> cbbHTTT;
    private javax.swing.JComboBox<String> cbbTkSanPham;
    private javax.swing.JComboBox<String> cbbVoucher;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblErTienKhach;
    private javax.swing.JLabel lblMaHD;
    private javax.swing.JLabel lblMaKH;
    private javax.swing.JLabel lblMaVoucher;
    private javax.swing.JLabel lblSoTienGiam;
    private javax.swing.JLabel lblTenKH;
    private javax.swing.JLabel lblTenSanPham;
    private javax.swing.JLabel lblTienThanhToan;
    private javax.swing.JLabel lblTienThua;
    private javax.swing.JLabel lblTitleSoTienGiam;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTable tblHoaDonCho;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JButton txtTenHD;
    private javax.swing.JTextField txtTenKDua;
    // End of variables declaration//GEN-END:variables
}
