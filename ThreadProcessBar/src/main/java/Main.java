
 
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
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
public class Main {
  public static void main(String args[]) {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
    JProgressBar aJProgressBar = new JProgressBar(0, 50);
    aJProgressBar.setStringPainted(true);

    JButton aJButton = new JButton("Start");

    ActionListener actionListener = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        aJButton.setEnabled(false);
        Thread stepper = new BarThread(aJProgressBar);
        stepper.start();
      }
    };
    aJButton.addActionListener(actionListener);
    frame.add(aJProgressBar, BorderLayout.NORTH);
    frame.add(aJButton, BorderLayout.SOUTH);
    frame.setSize(300, 200);
    frame.setVisible(true);
  }
}