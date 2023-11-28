package com.raven.form;

import com.UI.dialog.Message;
import com.fstore.service.ThongKe_Service;
import com.ui.main.Main;
import com.ui.model.ModelCard;
import com.ui.model.ModelStudent;
import com.raven.swing.icon.GoogleMaterialDesignIcons;
import com.raven.swing.icon.IconFontSwing;
import com.ui.swing.noticeboard.ModelNoticeBoard;
import com.ui.swing.table.EventAction;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.border.MatteBorder;

public class Form_Home extends javax.swing.JPanel implements Runnable{
    private ThongKe_Service thongKe_Service =  new ThongKe_Service();
    public Form_Home() {
        initComponents();
        
        setOpaque(false);
        initData();
    }

    private void initData() {
        initCardData();
        //initNoticeBoard();
        Thread t = new Thread(this);
        t.start();
        
    }

    
    private void initCardData() {
        Icon icon1 = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.SHOPPING_BASKET, 60, new Color(255, 255, 255, 100), new Color(255, 255, 255, 15));
        card1.setData(new ModelCard("Sản phẩm/Ngày",thongKe_Service.getSoLuongInDay(),0,icon1));
        Icon icon2 = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.MONETIZATION_ON, 50, Color.yellow, Color.black);
        card2.setData(new ModelCard("Doanh Thu/Ngay",thongKe_Service.getDoanhThuInDay(),0,icon2));
        Icon icon3 = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.PEOPLE, 50, Color.yellow, Color.black);
        card3.setData(new ModelCard("Tổng khách hàng/ngày",thongKe_Service.getKhachHangInDay(),0,icon3));
        Icon icon4 = IconFontSwing.buildIcon(GoogleMaterialDesignIcons.PAYMENT, 50, Color.yellow, Color.black);
        card4.setData(new ModelCard("Đơn hàng/ngày",thongKe_Service.getDonHangInDay(),0,icon4));
    }

//    private void initNoticeBoard() {
//        noticeBoard.addDate("04/10/2021");
//        noticeBoard.addNoticeBoard(new ModelNoticeBoard(new Color(94, 49, 238), "Thời gian hoạt động của shop", "Now", "Shop mở cửa lúc 9h và đóng cửa lúc 21h từ thứ 2 đến chủ nhật.(Trừ các ngày lê tết)"));
//        noticeBoard.addNoticeBoard(new ModelNoticeBoard(new Color(218, 49, 238), "Các lưu ý", "2h ago", "- Quy khách vui lòng kiểm tra kĩ sản phẩm\n-Nếu quý khách không hài lòng về chất lượng phục vụ hay thái độ của nhân viên vui lòng phản hồi với shop qua số: 0333002864 (Mr.Thong)\nXin cảm ơn quý khách!"));
//       
//        noticeBoard.scrollToTop();
//    }

    private boolean showMessage(String message) {
        Message obj = new Message(Main.getFrames()[0], true);
        obj.showMessage(message);
        return obj.isOk();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblKinhChao = new javax.swing.JLabel();
        lblDongHo = new javax.swing.JLabel();
        card1 = new com.UI.component.Card();
        card2 = new com.UI.component.Card();
        card3 = new com.UI.component.Card();
        card4 = new com.UI.component.Card();

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(4, 72, 210));
        jLabel1.setText("Dashboard / Home");

        lblKinhChao.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblKinhChao.setForeground(new java.awt.Color(0, 0, 102));
        lblKinhChao.setText("FSTORE XIN KÍNH CHÀO QUÝ KHÁCH");
        lblKinhChao.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 10, 10, 10, new java.awt.Color(0, 0, 255)));

        lblDongHo.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        lblDongHo.setForeground(new java.awt.Color(0, 204, 204));
        lblDongHo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDongHo.setBorder(javax.swing.BorderFactory.createMatteBorder(10, 10, 10, 10, new java.awt.Color(0, 102, 204)));

        card1.setBackground(new java.awt.Color(102, 0, 102));
        card1.setColorGradient(new java.awt.Color(0, 0, 0));

        card2.setBackground(new java.awt.Color(0, 204, 0));
        card2.setColorGradient(new java.awt.Color(0, 0, 0));

        card3.setBackground(new java.awt.Color(0, 51, 51));
        card3.setColorGradient(new java.awt.Color(51, 51, 51));

        card4.setColorGradient(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(card1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(card2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(card3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(card4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(68, 68, 68))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addComponent(lblKinhChao))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(254, 254, 254)
                        .addComponent(lblDongHo, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(226, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(card1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(card4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
                .addComponent(lblKinhChao)
                .addGap(75, 75, 75)
                .addComponent(lblDongHo, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(154, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.UI.component.Card card1;
    private com.UI.component.Card card2;
    private com.UI.component.Card card3;
    private com.UI.component.Card card4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblDongHo;
    private javax.swing.JLabel lblKinhChao;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        Color[] color = {Color.BLACK,Color.BLUE,Color.GRAY,Color.ORANGE,Color.RED,Color.PINK};
        int i = 0;
        while (true) {            
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            i++;
            if(i>color.length-1){
                i = 0;
            }
            Date d = new Date();
            SimpleDateFormat format1 = new SimpleDateFormat("hh:mm:ss aa");
            lblDongHo.setText(format1.format(d));
            lblDongHo.setForeground(color[i]);
            lblDongHo.setBorder(new MatteBorder(10, 10, 10, 10, color[i]));
            lblKinhChao.setForeground(color[i]);
            lblKinhChao.setBorder(new MatteBorder(10, 10, 10, 10, color[i]));
        }
    }
}
