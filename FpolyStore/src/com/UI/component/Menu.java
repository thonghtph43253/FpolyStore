package com.UI.component;

import com.UI.event.EventMenu;
import com.UI.event.EventMenuSelected;
import com.UI.event.EventShowPopupMenu;
import com.fsore.untils.Auth;
import com.ui.model.ModelMenu;
import com.ui.swing.MenuAnimation;
import com.ui.swing.MenuItem;
import com.ui.swing.scrollbar.ScrollBarCustom;
import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import net.miginfocom.swing.MigLayout;

public class Menu extends javax.swing.JPanel {

    public boolean isShowMenu() {
        return showMenu;
    }

    public void addEvent(EventMenuSelected event) {
        this.event = event;
    }

    public void setEnableMenu(boolean enableMenu) {
        this.enableMenu = enableMenu;
    }

    public void setShowMenu(boolean showMenu) {
        this.showMenu = showMenu;
    }

    public void addEventShowPopup(EventShowPopupMenu eventShowPopup) {
        this.eventShowPopup = eventShowPopup;
    }

    private final MigLayout layout;
    private EventMenuSelected event;
    private EventShowPopupMenu eventShowPopup;
    private boolean enableMenu = true;
    private boolean showMenu = true;

    public Menu() {
        initComponents();
        setOpaque(false);
        sp.getViewport().setOpaque(false);
        sp.setVerticalScrollBar(new ScrollBarCustom());
        layout = new MigLayout("wrap, fillx, insets 0", "[fill]", "[]0[]");
        panel.setLayout(layout);
    }

    public void initMenuItem() {
        if (Auth.isManager()) {
            addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/raven/icon/icons8-home-20.png")), "Trang chủ"));
            addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/raven/icon/icons8-product-20 (1).png")), "QUẢN LÝ SẢN PHẨM", "SẢN PHẨM", "CHI TIẾT SẢN PHẨM", "THUỘC TÍNH SẢN PHẨM"));
            addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/raven/icon/3.png")), "QUẢN LÝ BÁN HÀNG", "BÁN HÀNG", "NHẬP HÀNG"));
            addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/raven/icon/4.png")), "QUẢN LÝ HÓA ĐƠN", "HÓA ĐƠN BÁN HÀNG", "HÓA ĐƠN NHẬP HÀNG"));
            addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/raven/icon/5.png")), "QUẨN LÝ THÔNG KÊ", "THEO DOANH THU BÁN HÀNG", "THEO DOANH SỐ BÁN HÀNG"));
            addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/raven/icon/6.png")), "QUẢN LÝ NHÂN VIÊN"));
            addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/raven/icon/icons8-client-20 (1).png")), "QUẢN LÝ KHÁCH HÀNG"));
            addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/raven/icon/7.png")), "THÔNG TIN CÁ NHÂN", "THÔNG TIN", "ĐỔI MẬT KHẨU"));
            addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/raven/icon/8.png")), "KHUYÊN MÃI", "QUẢN LÝ SALE", "QUẢN LÝ VOUCHER"));
            addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/raven/icon/icons8-exit-20_1.png")), "ĐĂNG XUẤT"));            
        } else {
            addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/raven/icon/icons8-home-20.png")), "Trang chủ"));
            addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/raven/icon/3.png")), "QUẢN LÝ BÁN HÀNG"));
            addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/raven/icon/4.png")), "QUẢN LÝ HÓA ĐƠN"));
            addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/raven/icon/5.png")), "QUẨN LÝ THÔNG KÊ", "THEO DOANH SỐ BÁN HÀNG"));
            addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/raven/icon/icons8-client-20 (1).png")), "QUẢN LÝ KHÁCH HÀNG"));
            addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/raven/icon/7.png")), "THÔNG TIN CÁ NHÂN", "THÔNG TIN", "ĐỔI MẬT KHẨU"));
            addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/com/raven/icon/icons8-exit-20_1.png")), "ĐĂNG XUẤT")); 
        }
    }

    private void addMenu(ModelMenu menu) {
        panel.add(new MenuItem(menu, getEventMenu(), event, panel.getComponentCount()), "h 40!");
    }

    private EventMenu getEventMenu() {
        return new EventMenu() {
            @Override
            public boolean menuPressed(Component com, boolean open) {
                if (enableMenu) {
                    if (isShowMenu()) {
                        if (open) {
                            new MenuAnimation(layout, com).openMenu();
                        } else {
                            new MenuAnimation(layout, com).closeMenu();
                        }
                        return true;
                    } else {
                        eventShowPopup.showPopup(com);
                    }
                }
                return false;
            }
        };
    }

    public void hideallMenu() {
        for (Component com : panel.getComponents()) {
            MenuItem item = (MenuItem) com;
            if (item.isOpen()) {
                new MenuAnimation(layout, com, 500).closeMenu();
                item.setOpen(false);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sp = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        profile1 = new com.UI.component.Profile();

        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setViewportBorder(null);

        panel.setOpaque(false);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 312, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 523, Short.MAX_VALUE)
        );

        sp.setViewportView(panel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
            .addComponent(profile1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(profile1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gra = new GradientPaint(0, 0, new Color(0, 0, 4), getWidth(), 0, new Color(58, 55, 55));
        g2.setPaint(gra);
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(grphcs);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panel;
    private com.UI.component.Profile profile1;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
