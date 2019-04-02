/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacedemo;

/**
 *
 * @author MinhVuong
 */
public class Download {

    private CapNhat capNhat;

    /**
     * @param capNhat lang nghe su kien download
     */
    public void addDownloadListener(CapNhat capNhat) {
        this.capNhat = capNhat;
    }
    
    public void download() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            System.out.println("Dang download...");

            /**
             * Khi goi capNhat.capNhatGiaoDien(i); thi phuong thuc nay se tu
             * dong duoc goi trong giao dien
             *
             */
            Thread.sleep(1000);
            capNhat.capNhatGiaoDien(i);
        }
        System.out.println("Ket thuc download");
    }
}
