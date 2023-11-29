/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.raven.form;

import java.awt.Color;
import java.awt.HeadlessException;
import javax.swing.table.DefaultTableModel;
import raven.chart.CurveLineChart;
import raven.chart.ModelChart;
import raven.panel.PanelGradient;

/**
 *
 * @author Admin
 */
public class BieuDoDuong extends javax.swing.JFrame {

    private DefaultTableModel tblModel;
    private int setBieuDo;

    public BieuDoDuong() {
        initComponents();
        chart.setTitle(" Thống Kê Doanh Thu");
        chart.addLegend("Tổng doanh thu", new Color(198, 254, 219), new Color(251, 211, 134));
        chart.addLegend("Tổng nhập", new Color(26, 190, 233), new Color(222, 95, 160));
        chart.addLegend("Tổng tiền voucher",new Color(109, 225, 132), new Color(242, 121, 32) );
        chart.addLegend("Lợi nhuận",new Color(130, 97, 195), new Color(99, 132, 176) );

    }

    public BieuDoDuong(DefaultTableModel tblModel, int setBieuDo) throws HeadlessException {
        this.tblModel = tblModel;
        this.setBieuDo = setBieuDo;
        initComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        if (this.setBieuDo == 1) {
            chart.setTitle(" THỐNG KÊ DOANH THU");
            chart.addLegend("Tổng doanh thu", new Color(198, 254, 219), new Color(251, 211, 134));
            chart.addLegend("Tổng nhập", new Color(26, 190, 233), new Color(222, 95, 160) );
            chart.addLegend("Tổng tiền voucher",new Color(109, 225, 132), new Color(242, 121, 32) );
            chart.addLegend("Lợi nhuận",new Color(130, 97, 195), new Color(99, 132, 176) );
            setDataDoanhThu();
        }else if(this.setBieuDo ==2){
            chart.setTitle(" THÔNG KÊ DOANH SỐ");
            chart.addLegend("Số lượng bán", new Color(198, 254, 219), new Color(251, 211, 134));
            setDataDoanhSo();
        }
    }

    public String fomartDouble(String txt) {
        String pattern = txt;
        return pattern = pattern.replaceAll(",", "");
    }

    public void setDataDoanhThu() {
        int row = tblModel.getRowCount();
        for (int i = 0; i < row; i++) {
            chart.addData(new ModelChart(tblModel.getValueAt(i, 0).toString(),
                    new double[]{
                        Double.parseDouble(fomartDouble(tblModel.getValueAt(i, 2).toString())),
                        Double.parseDouble(fomartDouble(tblModel.getValueAt(i, 3).toString())),
                        Double.parseDouble(fomartDouble(tblModel.getValueAt(i, 4).toString())),
                        Double.parseDouble(fomartDouble(tblModel.getValueAt(i, 5).toString()))
                    }
            ));
        }
        chart.start();
    }

    public void setDataDoanhSo() {
        int row = tblModel.getRowCount();
        for (int i = 0; i < row; i++) {
            chart.addData(new ModelChart(tblModel.getValueAt(i, 1).toString(),
                    new double[]{
                        Double.parseDouble(fomartDouble(tblModel.getValueAt(i, 2).toString())),}
            ));
        }
        chart.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelGradient1 = new raven.panel.PanelGradient();
        chart = new raven.chart.CurveLineChart();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelGradient1.setBackground(new java.awt.Color(3, 10, 23));
        panelGradient1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelGradient1.setColorGradient(new java.awt.Color(5, 57, 47));

        chart.setForeground(new java.awt.Color(255, 255, 255));
        panelGradient1.add(chart);
        chart.setBounds(10, 10, 770, 490);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelGradient1, javax.swing.GroupLayout.DEFAULT_SIZE, 792, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelGradient1, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(BieuDoDuong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BieuDoDuong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BieuDoDuong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BieuDoDuong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BieuDoDuong().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private raven.chart.CurveLineChart chart;
    private raven.panel.PanelGradient panelGradient1;
    // End of variables declaration//GEN-END:variables
}
