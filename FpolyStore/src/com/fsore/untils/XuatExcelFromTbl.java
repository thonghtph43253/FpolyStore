package com.fsore.untils;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Admin
 */
public class XuatExcelFromTbl {

    public void exportExcel(DefaultTableModel tblMd) throws IOException, FileNotFoundException {
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        XSSFWorkbook fWorkbook = null;
        JFileChooser jfc = new JFileChooser("J:\\Code_PRO_1041\\Excel");
        jfc.setDialogTitle("Save As");
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("Excel Files", "xls", "xlsx", "xlsm");
        jfc.setFileFilter(fnef);
        int choose = jfc.showSaveDialog(null);
        if (choose == JFileChooser.APPROVE_OPTION) {
            try {
                fWorkbook = new XSSFWorkbook();
                XSSFSheet fSheet = fWorkbook.createSheet("sheet");
                XSSFRow row = fSheet.createRow(0);
                XSSFCell cell;
                for (int i = 0; i < tblMd.getColumnCount(); i++) {
                    cell = row.createCell(i);
                    cell.setCellValue(tblMd.getColumnName(i));
                }
                for (int i = 0; i < tblMd.getRowCount(); i++) {
                    row = fSheet.createRow(i + 1);
                    for (int j = 0; j < tblMd.getColumnCount(); j++) {
                        cell = row.createCell(j);
                        if (tblMd.getValueAt(i, j) != null) {
                            cell.setCellValue(tblMd.getValueAt(i, j).toString());
                        }
                    }
                }
                fos = new FileOutputStream(jfc.getSelectedFile() + ".xlsx");
                bos = new BufferedOutputStream(fos);
                fWorkbook.write(bos);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bos != null) {
                        bos.close();
                    }
                    if (fos != null) {
                        fos.close();
                    }

                    if (fWorkbook != null) {
                        fWorkbook.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    
}
