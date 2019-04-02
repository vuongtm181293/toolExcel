/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitis;

import static Model.ListFile.listFileXlsx;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileUtilitis {

    /**
     * @param dir List all file Xlsx in Folder add to listFileXlsx
     */
    public static List<File> getAllFileXlsxAddToList(File dir) throws IOException {
       listFileXlsx.clear();
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
              //  System.out.println("DIRACTORY:" + file.getCanonicalPath());
                getAllFileXlsxAddToList(file);
            } else {
               //  System.out.println("    FILE " + file.getCanonicalPath());
                if (isFileXlsx(file)) {
                    listFileXlsx.add(file);
                }
            }
        }
        return listFileXlsx;
    }

    /**
     * @param file is file need check
     * @return true if file has subText is xlsx
     */
    private static boolean isFileXlsx(File file) {
        String fileName = file.getName();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0
                && (fileName.substring(fileName.lastIndexOf(".") + 1).equals("xlsx"))) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        String URL = "F:\\DATA TEST";
        File currentDir = new File(URL); // current
        // directory

        System.out.println("==============List file =====================");
        for (File file :  listFileXlsx) {
            System.out.println(file.getCanonicalPath());
        }

    }
}
