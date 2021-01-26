package ru.betterstop.beatbox;

import javax.swing.*;
import java.awt.*;

public class MyDrawPanel extends JPanel {
    public void paintComponent(Graphics graphics){
        Graphics2D g2d = (Graphics2D) graphics;

        int red = (int) (Math.random() * 255);
        int green = (int) (Math.random() * 255);
        int blue = (int) (Math.random() * 255);
        Color randomColor1 = new Color(red, green, blue);

        red = (int) (Math.random() * 255);
        green = (int) (Math.random() * 255);
        blue = (int) (Math.random() * 255);
        Color randomColor2 = new Color(red, green, blue);


        GradientPaint gradientPaint = new GradientPaint(70, 70, randomColor1, 150,150, randomColor2);

        g2d.setPaint(gradientPaint);
        g2d.fillOval(300, 50, 100, 100);
        g2d.fillRect(20,50,120,120);
        g2d.setPaint(gradientPaint);


        red = (int) (Math.random() * 255);
        green = (int) (Math.random() * 255);
        blue = (int) (Math.random() * 255);

        Color randomColor = new Color(red, green, blue);
        graphics.setColor(randomColor);
        graphics.fillOval(50, 200, 100, 100);

        g2d.fill3DRect(150, 50, 100, 100, true);

    }
}
