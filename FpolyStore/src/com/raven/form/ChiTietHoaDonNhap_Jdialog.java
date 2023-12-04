/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.raven.form;

import com.fstore.untils.MsgBox;
import com.fstore.untils.XDate;
import static com.fstore.untils.convertKey.removeAccent;
import com.fstore.model.HoaDon;
import com.fstore.model.HoaDonNhap;
import com.fstore.model.HoaDonNhap_ChiTiet;
import com.fstore.model.HoaDon_ChiTiet;
import com.fstore.model.SanPham;
import com.fstore.model.SanPhamChiTiet;
import com.fstore.model.Voucher_ChiTiet;
import com.fstore.service.ChatLieu_Service;
import com.fstore.service.HoaDonChiTiet_Service;
import com.fstore.service.HoaDonNhapChiTiet_Service;
import com.fstore.service.HoaDonNhap_Service;
import com.fstore.service.HoaDon_Service;
import com.fstore.service.MauSac_Service;
import com.fstore.service.SanPhamChiTiet_Service;
import com.fstore.service.SanPham_Service;
import com.fstore.service.Size_Service;
import com.fstore.service.Voucher_CT_Service;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.text.BadElementException;
import com.ui.main.Main;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class ChiTietHoaDonNhap_Jdialog extends javax.swing.JDialog {

    private int id_HoaDon;
    private HoaDonChiTiet_Service hdct_Service = new HoaDonChiTiet_Service();
    private HoaDon_Service hoaDon_Service = new HoaDon_Service();
    private SanPham_Service sp_Service = new SanPham_Service();
    private SanPhamChiTiet_Service spct_Service = new SanPhamChiTiet_Service();
    private Size_Service size_Service = new Size_Service();
    private ChatLieu_Service chatLieu_Service = new ChatLieu_Service();
    private MauSac_Service mauSac_Service = new MauSac_Service();
    private Voucher_CT_Service voucher_CT_Service = new Voucher_CT_Service();
    private HoaDonNhap_Service hoaDonNhap_Service = new HoaDonNhap_Service();
    private HoaDonNhapChiTiet_Service hdnct_Service = new  HoaDonNhapChiTiet_Service();

    public ChiTietHoaDonNhap_Jdialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

    }

    public ChiTietHoaDonNhap_Jdialog(java.awt.Frame parent, boolean modal, int id_HoaDon) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.id_HoaDon = id_HoaDon;
        init();
    }

    public void init() {
        fillTable(hdnct_Service.selectAllByID_HDN(id_HoaDon));
        lblMaHD.setText(String.valueOf(id_HoaDon));
        HoaDonNhap hd = hoaDonNhap_Service.selectByID(id_HoaDon);
        
        lblTenKH.setText(hd.getTenNhaCungCap());
        lblSdt.setText(hd.getSoDienThoai());
        lblTongTien1.setText(hd.getTongTien()+ " VNĐ");
        lblThanhToan.setText(hd.getTrangThai()==1?"Đã thanh toán":"Chưa thanh tóan");
        lblThoiGian.setText(hd.getNgayTao());
        lblNhanVien.setText(hd.getMaNv());
        fillTable(hdnct_Service.selectAllByID_HDN(id_HoaDon));
    }

    public void fillTable(List<HoaDonNhap_ChiTiet> list) {
        DefaultTableModel tblMd = (DefaultTableModel) tblHoaDon_CT.getModel();
        tblMd.setRowCount(0);
        for (HoaDonNhap_ChiTiet hdct : list) {
//            int id_Sp = spct_Service.selectByID(hdct.getId_SanPhamChiTiet()).getId_SanPham();
            // System.out.println(id_Sp);
            SanPhamChiTiet spct = spct_Service.selectByID(hdct.getId_SanPhamChiTiet());
            tblMd.addRow(new Object[]{
                hdct.getId_HoaDonNhapCT(),
                sp_Service.selectByID(spct.getId_SanPham()).getTenSP(),
                size_Service.selectByID(spct.getId_Size()).getTenSize(),
                mauSac_Service.selectByID(spct.getId_ChatLieu()).getTenMau(),
                chatLieu_Service.selectByID(spct.getId_ChatLieu()).getTenChatLieu(),
                hdct.getSoLuong(),
                hdct.getGiaNhap()
            });
        }
    }

    public double tongTien() {
        double tongTien = 0;
        int row = tblHoaDon_CT.getRowCount();
        for (int i = 0; i < row; i++) {
            int soLuong = Integer.parseInt(tblHoaDon_CT.getValueAt(i, 5).toString());
            double gia = Double.parseDouble(tblHoaDon_CT.getValueAt(i, 6).toString());
            tongTien += soLuong * gia;
        }
        return tongTien;
    }
    public void xuatHoaDon() throws IOException, BadElementException{
//        String pathName = XDate.toString(new Date(), "hh-mm-ss aa dd-MM-yyyy");
//        pathName = pathName.replaceAll(" ", "+");
 //     String path = "J:\\Code_PRO_1041\\HoaDon"+pathName+".pdf";
//        PdfWriter
        HoaDonNhap hd1 = hoaDonNhap_Service.selectByID(id_HoaDon);
        DecimalFormat nf = new DecimalFormat("#,##0");
       String pathnn = XDate.toString(new Date(), " hh-mm-ss aa dd-MM-yyyy");
        pathnn = pathnn.replaceAll(" ", "_");
        System.out.println(pathnn);
        String path = "J:\\Code_PRO_1041\\HoaDon\\"+pathnn+".pdf";
        com.itextpdf.kernel.pdf.PdfWriter pdfWriter = new com.itextpdf.kernel.pdf.PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        com.itextpdf.layout.Document document = new com.itextpdf.layout.Document(pdfDocument);
        pdfDocument.setDefaultPageSize(PageSize.A4);
        Document doc = new Document(pdfDocument);
        float col = 280f;
        float columnWidth[] = {col, col};
        com.itextpdf.layout.element.Table table = new com.itextpdf.layout.element.Table(columnWidth);
        table.setBackgroundColor(new DeviceRgb(255, 69, 0)).setFontColor(Color.WHITE);
        String file = "J:\\Code_PRO_1041\\FpolyStore\\FpolyStore\\src\\com\\raven\\icon\\logo_FStore 1 (1).png";
        ImageData date = ImageDataFactory.create(file);
        com.itextpdf.layout.element.Image image = new com.itextpdf.layout.element.Image(date);
//        doc.add(image);
        table.addCell(new Cell().add(image).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add("FSTORE").setFontSize(30f).setBorder(Border.NO_BORDER));

        table.addCell(new Cell().add("D401 FPT POLYTECHNIC Kieu Mai \n SDT:0333002864")
                .setTextAlignment(TextAlignment.RIGHT).setMarginTop(30f).setMarginBottom(30f).setBorder(Border.NO_BORDER).setMarginRight(10f)
        );

        float colWidth[] = {100, 250, 120, 150};

        com.itextpdf.layout.element.Table customerInfor = new com.itextpdf.layout.element.Table(colWidth);
        customerInfor.addCell(new Cell(0, 4).add("HOA DON NHAP HANG").setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));

        customerInfor.addCell(new Cell(0, 4).add("Thong tin").setBold().setBorder(Border.NO_BORDER));
        customerInfor.addCell(new Cell().add("Nha Cung Cap: ").setBorder(Border.NO_BORDER));
        customerInfor.addCell(new Cell().add(removeAccent(hd1.getTenNhaCungCap())).setBorder(Border.NO_BORDER));
        customerInfor.addCell(new Cell().add("Ma Hoa Don: ").setBorder(Border.NO_BORDER));
        customerInfor.addCell(new Cell().add(id_HoaDon+"").setBorder(Border.NO_BORDER));
        customerInfor.addCell(new Cell().add("SDT: ").setBorder(Border.NO_BORDER)); //
        customerInfor.addCell(new Cell().add(removeAccent(hd1.getSoDienThoai())).setBorder(Border.NO_BORDER)); //

        customerInfor.addCell(new Cell().add("Nhan vien nhap: ").setBorder(Border.NO_BORDER)); //
        customerInfor.addCell(new Cell().add(removeAccent(hd1.getMaNv())).setBorder(Border.NO_BORDER)); //
        customerInfor.addCell(new Cell().add("Date: ").setBorder(Border.NO_BORDER));
        customerInfor.addCell(new Cell().add(hd1.getNgayTao()).setBorder(Border.NO_BORDER));

        float iteamInforColWidth[] = {140,140, 140, 140, 140};
        com.itextpdf.layout.element.Table itemInforTable = new com.itextpdf.layout.element.Table(iteamInforColWidth);
        itemInforTable.addCell(new Cell().add("ID_SPCT").setBackgroundColor(new DeviceRgb(255, 69, 0)).setFontColor(Color.WHITE));
        itemInforTable.addCell(new Cell().add("San Pham").setBackgroundColor(new DeviceRgb(255, 69, 0)).setFontColor(Color.WHITE));
        itemInforTable.addCell(new Cell().add("So luong").setBackgroundColor(new DeviceRgb(255, 69, 0)).setFontColor(Color.WHITE));
        itemInforTable.addCell(new Cell().add("Gia").setBackgroundColor(new DeviceRgb(255, 69, 0)).setFontColor(Color.WHITE).setTextAlignment(TextAlignment.RIGHT));
        itemInforTable.addCell(new Cell().add("Thanh Tien").setBackgroundColor(new DeviceRgb(255, 69, 0)).setFontColor(Color.WHITE).setTextAlignment(TextAlignment.RIGHT));

        int total = 0;
        int quantitySum = 0;
        List<HoaDonNhap_ChiTiet> list = hdnct_Service.selectAllByID_HDN(id_HoaDon);
        for (HoaDonNhap_ChiTiet hdct : list) {
            SanPhamChiTiet spct = spct_Service.selectByID(hdct.getId_SanPhamChiTiet());
            SanPham sp = sp_Service.selectByID(spct.getId_SanPham());
            HoaDon hd = hoaDon_Service.selectByID(hdct.getId_HoaDonNhap());
            int id = hdct.getId_HoaDonNhapCT();
            int id_spct = hdct.getId_SanPhamChiTiet();
            String nameProduct = sp.getTenSP();
            String nameCustomer = hd.getTenKH();
            String Size = size_Service.selectByID(spct.getId_Size()).getTenSize();
            String Color = mauSac_Service.selectByID(spct.getId_Mau()).getTenMau();
            String Material = chatLieu_Service.selectByID(spct.getId_ChatLieu()).getTenChatLieu();
            int quantity = (int)hdct.getSoLuong();
            double price = (double) hdct.getGiaNhap();
            itemInforTable.addCell(new Cell().add(id_spct+""));
            itemInforTable.addCell(new Cell().add(removeAccent(nameProduct)));
            itemInforTable.addCell(new Cell().add(quantity + ""));
            itemInforTable.addCell(new Cell().add(nf.format(price) + " VND").setTextAlignment(TextAlignment.RIGHT));
            itemInforTable.addCell(new Cell().add(nf.format(price * quantity) + " VND").setTextAlignment(TextAlignment.RIGHT));
            total += price * quantity;
            quantitySum += quantity;
        }

        itemInforTable.addCell(new Cell().add("Tong So Luong").setBackgroundColor(new DeviceRgb(255, 69, 0)).setBorder(Border.NO_BORDER));
        itemInforTable.addCell(new Cell().add( "").setBackgroundColor(new DeviceRgb(255, 69, 0)).setBorder(Border.NO_BORDER));
        itemInforTable.addCell(new Cell().add(quantitySum + "").setBackgroundColor(new DeviceRgb(255, 69, 0)).setBorder(Border.NO_BORDER));
        itemInforTable.addCell(new Cell().add("Tong Tien").setTextAlignment(TextAlignment.RIGHT).setBackgroundColor(new DeviceRgb(255, 69, 0)).setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));
        itemInforTable.addCell(new Cell().add(nf.format(total) + " VND").setTextAlignment(TextAlignment.RIGHT).setBackgroundColor(new DeviceRgb(255, 69, 0)).setBorder(Border.NO_BORDER).setFontColor(Color.WHITE));

        float colWidthNote[] = {560};
        
        HoaDonNhap hd = hoaDonNhap_Service.selectByID(id_HoaDon);
       
        com.itextpdf.layout.element.Table customerInforNote = new com.itextpdf.layout.element.Table(colWidthNote);
        
        
       
             customerInforNote.addCell(new Cell().add("Tong tien: " + nf.format((hd.getTongTien())) + " VND").
                setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER).setBold().setFontSize(20).setFontColor(new DeviceRgb(0, 0, 0)));
          
        
        customerInforNote.addCell(new Cell().add("Thanh toan: " + nf.format((hd.getTongTien())) + " VND").
                setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER).setBold().setFontSize(20).setFontColor(new DeviceRgb(0, 0, 0)));
        customerInforNote.addCell(new Cell().add("Luu y: Vui long kiem tra du hang!").
                setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER).setItalic().setFontColor(Color.RED));
        customerInforNote.addCell(new Cell().add("Xin cam on!\n").
                setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER).setItalic().setFontColor(Color.BLACK));
        document.add(table);
        document.add(new Paragraph("\n"));
        document.add(customerInfor);
        document.add(new Paragraph("\n"));
        document.add(itemInforTable);
        document.add(new Paragraph("\n"));
        document.add(customerInforNote);
        document.close();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon_CT = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        lblTenKH = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblThanhToan = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        lblMaHD = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblSdt = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblThoiGian = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblTongTien1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblNhanVien = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("HÓA ĐƠN NHẬP CHI TIẾT");

        tblHoaDon_CT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID_HDCT_CT", "TÊN SP", "SIZE", "MÀU SẮC", "CHẤT LIỆU", "SỐ LƯỢNG NHẬP", "GIÁ NHẬP"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon_CT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDon_CTMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDon_CT);

        jLabel2.setText("Tên nhà cung cấp");

        lblTenKH.setText("jLabel3");

        jLabel3.setText("Trạng thái thanh  toán");

        lblThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblThanhToan.setForeground(new java.awt.Color(255, 0, 0));
        lblThanhToan.setText("0.0");

        jButton1.setText("Xuất hóa đơn");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Hủy");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel5.setText("Mã hóa đơn:");

        lblMaHD.setText("jLabel6");

        jLabel4.setText("Số điện thoại");

        lblSdt.setText("jLabel6");

        jLabel6.setText("Ngày tạo");

        lblThoiGian.setText("t");

        jLabel7.setText("Tổng tiền");

        lblTongTien1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTongTien1.setForeground(new java.awt.Color(255, 0, 0));
        lblTongTien1.setText("0.0");

        jLabel8.setText("Nhân viên nhập");

        lblNhanVien.setText("jLabel9");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblSdt)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblMaHD)
                                            .addComponent(lblTenKH))
                                        .addGap(227, 227, 227)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel6))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblThoiGian)
                                            .addComponent(lblNhanVien))))))
                        .addGap(0, 170, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblThanhToan))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(37, 37, 37)
                                        .addComponent(jButton1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton2))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(78, 78, 78)
                                        .addComponent(lblTongTien1)))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblMaHD)
                    .addComponent(jLabel6)
                    .addComponent(lblThoiGian))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblTenKH)
                    .addComponent(jLabel8)
                    .addComponent(lblNhanVien))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblSdt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblTongTien1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblThanhToan))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblHoaDon_CTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDon_CTMouseClicked
      
    }//GEN-LAST:event_tblHoaDon_CTMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            xuatHoaDon();
            MsgBox.alert(this, "Xuất hóa đơn nhập hàng thành công!");
        } catch (IOException ex) {
            Logger.getLogger(ChiTietHoaDonNhap_Jdialog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadElementException ex) {
            Logger.getLogger(ChiTietHoaDonNhap_Jdialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(ChiTietHoaDonNhap_Jdialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChiTietHoaDonNhap_Jdialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChiTietHoaDonNhap_Jdialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChiTietHoaDonNhap_Jdialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ChiTietHoaDonNhap_Jdialog dialog = new ChiTietHoaDonNhap_Jdialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMaHD;
    private javax.swing.JLabel lblNhanVien;
    private javax.swing.JLabel lblSdt;
    private javax.swing.JLabel lblTenKH;
    private javax.swing.JLabel lblThanhToan;
    private javax.swing.JLabel lblThoiGian;
    private javax.swing.JLabel lblTongTien1;
    private javax.swing.JTable tblHoaDon_CT;
    // End of variables declaration//GEN-END:variables
}
