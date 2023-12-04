package com.ui.main;

import com.UI.component.Header;
import com.UI.component.Menu;
import com.UI.event.EventMenuSelected;
import com.UI.event.EventShowPopupMenu;
import com.fstore.untils.Auth;
import com.fstore.untils.MsgBox;
import com.raven.form.BanHang_Panel;
import com.raven.form.ChiTietSanPham_Panel;
import com.raven.form.MainLoginJdialog;
import com.raven.form.DoiMatKhauPanel;
import com.raven.form.HoaDonBanHang_Panel;
import com.raven.form.HoaDonNhapHang_Panel;
import com.raven.form.KhachHang_Panel;
import com.raven.form.NhanVien_Panel;
import com.raven.form.NhapHang_Panel;
import com.raven.form.QLThuocTinhSanPham;
import com.raven.form.Sale_Panel;
import com.raven.form.SanPham_Panel;
import com.raven.form.ThongKeDoanhSo_Panel;
import com.raven.form.ThongKeDoanhThu_Panel;
import com.raven.form.ThongTinCaNhan_Panel;
import com.raven.form.Voucher_Panel;
import com.raven.form.Form1;
import com.raven.form.Form_Home;
import com.raven.form.MainForm;
import com.ui.swing.MenuItem;
import com.ui.swing.PopupMenu;
import com.raven.swing.icon.GoogleMaterialDesignIcons;
import com.raven.swing.icon.IconFontSwing;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class Main extends javax.swing.JFrame {

    private MigLayout layout;
    private Menu menu;
    private Header header;
    private MainForm main;
    private Animator animator;

    public Main() {
        initComponents();
        if (!Auth.isLogin()) {
            this.openDN();
            if (Auth.nv != null) {
                this.setVisible(true);
            } else {
                this.setVisible(false);
            }
        }
        init();
    }

    private void init() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        layout = new MigLayout("fill", "0[]0[100%, fill]0", "0[fill, top]0");

        bg.setLayout(layout);
        menu = new Menu();
        header = new Header();
        main = new MainForm();

        menu.addEvent(new EventMenuSelected() {
            @Override
            public void menuSelected(int menuIndex, int subMenuIndex) {
                System.out.println("Menu Index : " + menuIndex + " SubMenu Index " + subMenuIndex);
                if (Auth.isManager()) {
                    if (menuIndex == 0) {
                        if (subMenuIndex == -1) {
                            main.showForm(new Form_Home());
                        } else if (subMenuIndex == 1) {
                            main.showForm(new Form1());
                        }
                    }
                    if (menuIndex == 1) {
                        if (subMenuIndex == 2) {
                            main.showForm(new QLThuocTinhSanPham());
                        } else if (subMenuIndex == 0) {
                            main.showForm(new SanPham_Panel());
                        } else if (subMenuIndex == 1) {
                            main.showForm(new ChiTietSanPham_Panel());
                        }
                    } else if (menuIndex == 2) {
                        if (subMenuIndex == 0) {
                            main.showForm(new BanHang_Panel());
                        } else if (subMenuIndex == 1) {
                            main.showForm(new NhapHang_Panel());
                        }

                    } else if (menuIndex == 3) {
                        if (subMenuIndex == 0) {
                            main.showForm(new HoaDonBanHang_Panel());
                        } else if (subMenuIndex == 1) {
                            main.showForm(new HoaDonNhapHang_Panel());
                        }
                    } else if (menuIndex == 4) {
                        if (subMenuIndex == 0) {
                            main.showForm(new ThongKeDoanhSo_Panel());
                        } else if (subMenuIndex == 1) {
                            main.showForm(new ThongKeDoanhThu_Panel());
                        }
                    } else if (menuIndex == 5) {
                        main.showForm(new NhanVien_Panel());
                    } else if (menuIndex == 6) {
                        main.showForm(new KhachHang_Panel());
                    } else if (menuIndex == 7) {
                        if (subMenuIndex == 0) {
                            main.showForm(new ThongTinCaNhan_Panel());
                        }else if(subMenuIndex ==1){
                            main.showForm(new DoiMatKhauPanel());
                        }
                    } else if (menuIndex == 8) {
                        if (subMenuIndex == 0) {
                            main.showForm(new Sale_Panel());
                        } else if (subMenuIndex == 1) {
                            main.showForm(new Voucher_Panel());
                        }
                    } else if (menuIndex == 9) {
                        if (MsgBox.confirm(rootPane, "Đằng xuất ứng dụng?")) {
                            Auth.clear();
                            dispose();
                            openDN();
                            if (Auth.nv != null) {
                                new Main().setVisible(true);
                            } else {
                               new Main().setVisible(false);
                            }
                        } else {
                            return;
                        }
                    }
                } else {
                    if (menuIndex == 0) {
                        if (subMenuIndex == -1) {
                            main.showForm(new Form_Home());
                        } else if (subMenuIndex == 1) {
                            main.showForm(new Form1());
                        }
                    }
                    if (menuIndex == 1) {
                        main.showForm(new BanHang_Panel());
                    } else if (menuIndex == 2) {
                        main.showForm(new HoaDonBanHang_Panel(Auth.nv.getMaNV()));

                    } else if (menuIndex == 3) {
                        if (subMenuIndex == 0) {
                            main.showForm(new ThongKeDoanhThu_Panel());
                        }
                    } else if (menuIndex == 4) {
                        main.showForm(new KhachHang_Panel());
                    } else if (menuIndex == 5) {
                        if (subMenuIndex == 0) {
                            main.showForm(new ThongTinCaNhan_Panel());
                        }else if(subMenuIndex ==1){
                            main.showForm(new DoiMatKhauPanel());
                        }
                    } else if (menuIndex == 6) {
                        if (MsgBox.confirm(rootPane, "Đằng xuất ứng dụng?")) {
                            Auth.clear();
                            dispose();
                            openDN();
                            if (Auth.nv != null) {
                                new Main().setVisible(true);
                            } else {
                               new Main().setVisible(false);
                            }
                        } else {
                            return;
                        }
                    }

                }
            }

        });
        menu.addEventShowPopup(new EventShowPopupMenu() {
            @Override
            public void showPopup(Component com) {
                MenuItem item = (MenuItem) com;
                PopupMenu popup = new PopupMenu(Main.this, item.getIndex(), item.getEventSelected(), item.getMenu().getSubMenu());
                int x = Main.this.getX() + 52;
                int y = Main.this.getY() + com.getY() + 86;
                popup.setLocation(x, y);
                popup.setVisible(true);
            }
        });
        menu.initMenuItem();
        bg.add(menu, "w 230!, spany 2");    // Span Y 2cell
        bg.add(header, "h 50!, wrap");
        bg.add(main, "w 100%, h 100%");
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double width;
                if (menu.isShowMenu()) {
                    width = 60 + (170 * (1f - fraction));
                } else {
                    width = 60 + (170 * fraction);
                }
                layout.setComponentConstraints(menu, "w " + width + "!, spany2");
                menu.revalidate();
            }

            @Override
            public void end() {
                menu.setShowMenu(!menu.isShowMenu());
                menu.setEnableMenu(true);
            }

        };
        animator = new Animator(500, target);
        animator.setResolution(0);
        animator.setDeceleration(0.5f);
        animator.setAcceleration(0.5f);
        header.addMenuEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!animator.isRunning()) {
                    animator.start();
                }
                menu.setEnableMenu(false);
                if (menu.isShowMenu()) {
                    menu.hideallMenu();
                }
            }
        });
        //  Init google icon font
        IconFontSwing.register(GoogleMaterialDesignIcons.getIconFont());
        //  Start with this form
        main.showForm(new Form_Home());
    }

    public void openDN() {
        new MainLoginJdialog(this, true).setVisible(true);
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        bg.setBackground(new java.awt.Color(245, 245, 245));
        bg.setOpaque(true);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1366, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 783, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane bg;
    // End of variables declaration//GEN-END:variables
}
