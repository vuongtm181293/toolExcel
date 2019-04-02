package View;

import Utilitis.ExcelUtilitis;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author MinhVuong
 */
class BarThread extends Thread {

    private static int DELAY = 500;

    JProgressBar progressBar;
    List<File> listFileXlsx;
    JTextArea jTextArea;
    JFrame frame;

    public BarThread(JProgressBar bar, List<File> listFileXlsx, JTextArea jTextArea, JFrame frame) {
        this.progressBar = bar;
        this.listFileXlsx = listFileXlsx;
        this.jTextArea = jTextArea;
        this.frame = frame;
    }

    public void run() {

        System.out.println("" + "##############  SUM   " + +listFileXlsx.size() + " file #########################");
        jTextArea.append("" + "##############  SUM   " + +listFileXlsx.size() + " file #########################");
        progressBar.setMinimum(0);
        progressBar.setMaximum(listFileXlsx.size());
        progressBar.setVisible(true);

        int i = 0;
        for (File file : listFileXlsx) {
            try {
                i = i + 1;
                System.out.println("" + "##############  FILE   " + +i + "  #########################");
                jTextArea.append("\n" + "       ######  FILE   " + file.getName() + "  ####");
                XSSFWorkbook workbook = new XSSFWorkbook(file);
                /* OPTION */
                this.deleteSheetHide(file, workbook, jTextArea);

                progressBar.setValue(progressBar.getValue() + 1);

                /* CLOSE */
                FileOutputStream fos = new FileOutputStream("result.txt");
                workbook.write(fos);
                fos.close();
                workbook.close();
            } catch (IOException ex) {
                Logger.getLogger(BarThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidFormatException ex) {
                Logger.getLogger(BarThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        progressBar.setVisible(false);
        JOptionPane.showMessageDialog(frame, "Done!");

        System.out.println("\n" + "##############  Done   ###############################");
        jTextArea.append("\n" + "##############  Done   ###############################");
    }

    public void deleteSheetHide(File file, XSSFWorkbook workbook, JTextArea jTextArea) throws IOException, InvalidFormatException {
        int sumSheet = workbook.getNumberOfSheets();
        System.out.println("SUM " + sumSheet);
        int index = -1;
        this.demo(file, workbook, index, sumSheet, jTextArea);

    }

    private void demo(File file, XSSFWorkbook workbook, int index, int sumSheet, JTextArea jTextArea) {
        for (int i = 0; i < sumSheet; i++) {
            System.out.println("sheet " + workbook.getSheetName(i));
            if (workbook.isSheetHidden(i)) {
                System.out.println("\n" + " DELETE SHEET   " + workbook.getSheetName(i) + "    IN FILE    " + file.getName());
                jTextArea.append("\n" + "               DELETE SHEET   " + workbook.getSheetName(i) + "    IN FILE    " + file.getName());
                index = i;
                break;
            }
        }
        if (index > -1) {
            workbook.removeSheetAt(index);
            index = -1;
            demo(file, workbook, index, sumSheet - 1, jTextArea);
        }
    }
}
