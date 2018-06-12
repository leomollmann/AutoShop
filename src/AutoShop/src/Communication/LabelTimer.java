/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Communication;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author LeoFuking
 */
public class LabelTimer {

    private JLabel label;
    private int waitSeconds;

    public LabelTimer(int waitSeconds, JLabel label) {
        this.label = label;
        this.waitSeconds = waitSeconds;
        Timer timer = new Timer(waitSeconds * 1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                label.setVisible(false);
            }
        });
        timer.start();
    }
}