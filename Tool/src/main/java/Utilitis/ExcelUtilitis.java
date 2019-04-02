package Utilitis;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Model.ListFile;

import javax.swing.JTextArea;

public class ExcelUtilitis {
    
    

    public void excelCommon(List<File> listFileXlsx) throws IOException, InvalidFormatException {

        System.out.println("" + "##############  SUM   " + +listFileXlsx.size() + " file #########################");
        int i = 0;
        for (File file : listFileXlsx) {
            i = i + 1;
            System.out.println("" + "##############  FILE   " + +i + "  #########################");
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            /* OPTION */
            this.deleteSheetHide(file, workbook);

            // jProgressBar1.setValue(jProgressBar1.getValue() + 1);
            /* CLOSE */
            FileOutputStream fos = new FileOutputStream("result.txt");
            workbook.write(fos);
            fos.close();
            workbook.close();
        }

        System.out.println("\n" + "##############  Done   ###############################");
    }

    public void deleteSheetHide(File file, XSSFWorkbook workbook) throws IOException, InvalidFormatException {
        int sumSheet = workbook.getNumberOfSheets();
        System.out.println("SUM " + sumSheet);
        int index = -1;
        this.demo(file, workbook, index, sumSheet);

    }

    private void demo(File file, XSSFWorkbook workbook, int index, int sumSheet ) {

        for (int i = 0; i < sumSheet; i++) {

            System.out.println("sheet " + workbook.getSheetName(i));

            if (workbook.isSheetHidden(i)) {
                System.out.println("\n" + " DELETE SHEET   " + workbook.getSheetName(i) + "    IN FILE    " + file.getName());
                index = i;
                break;
            }
        }
        if (index > -1) {
            workbook.removeSheetAt(index);
            index = -1;
            demo(file, workbook, index, sumSheet - 1);

        }

    }

    public static void activeSheetAndZoom() throws IOException, InvalidFormatException {
        CellAddress cellAddress = new CellAddress("A1");
        for (File file : ListFile.listFileXlsx) {
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            int sumSheet = workbook.getNumberOfSheets();
            XSSFSheet sheet = workbook.getSheetAt(1);

            // A1 + 100 SHEET
            for (int j = 0; j < sumSheet; j++) {
                sheet = workbook.getSheetAt(j);
                sheet.setZoom(100);
                sheet.setActiveCell(cellAddress);
            }
            workbook.setActiveSheet(0);
            // close

            FileOutputStream fos = new FileOutputStream("result.txt");
            workbook.write(fos);
            fos.close();
            workbook.close();
        }
        System.out.println("Format OK !");
    }

}
