package ru.betterstop.beatbox;

import javax.swing.*;
import java.awt.*;

public class MyDrawPanel extends JPanel {
    public void paintComponent(Graphics graphics){
        Graphics2D g2d = (Graphics2D) graphics;

        GradientPaint gradientPaint = new GradientPaint(70, 70, Color.blue, 150,150, Color.orange);
        graphics.setColor(Color.magenta);
        g2d.setPaint(gradientPaint);
        g2d.fillRect(20,50,120,120);

        int red = (int) (Math.random() * 255);
        int green = (int) (Math.random() * 255);
        int blue = (int) (Math.random() * 255);

        Color randomColor = new Color(red, green, blue);
        graphics.setColor(randomColor);
        graphics.fillOval(50, 200, 100, 100);

        g2d.fill3DRect(150, 50, 100, 100, true);

    }
}
