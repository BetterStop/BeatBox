package ru.betterstop.beatbox;

import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.ShortMessage;
import javax.swing.*;
import java.awt.*;

public class MyDrawPanel extends JPanel implements ControllerEventListener {

    boolean msg = false;

    public void paintComponent(Graphics graphics){

        if (msg) {
            repaint();
            Graphics2D g2d = (Graphics2D) graphics;
            int red = (int) (Math.random() * 255);
            int green = (int) (Math.random() * 255);
            int blue = (int) (Math.random() * 255);
            Color randomColor = new Color(red, green, blue);
            g2d.setColor(randomColor);

            g2d.fill3DRect((int) (Math.random()* 40) + 100, (int) (Math.random()* 40) + 100, (int) (Math.random()* 120) + 10, (int) (Math.random()* 120) + 10, true);

            msg =false;
        }

//        g2d.setColor(Color.WHITE);
//        g2d.fillOval(BeatBox.x-BeatBox.xx, BeatBox.y-BeatBox.yy, 20, 20);
//
//        g2d.setColor(BeatBox.colorOval);
//        g2d.fillOval(BeatBox.x, BeatBox.y, 20, 20);


    }

    @Override
    public void controlChange(ShortMessage event) {
        msg = true;
        //System.out.println("````````````````````````````````");
        //repaint();
    }
}
