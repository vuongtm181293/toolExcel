
import javax.swing.JProgressBar;

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

  public BarThread(JProgressBar bar) {
    progressBar = bar;
  }

  public void run() {
    int minimum = progressBar.getMinimum();
    int maximum = progressBar.getMaximum();
    for (int i = minimum; i < maximum; i++) {
      try {
        int value = progressBar.getValue();
        progressBar.setValue(value + 1);

        Thread.sleep(DELAY);
      } catch (InterruptedException ignoredException) {
      }
    }
  }
}