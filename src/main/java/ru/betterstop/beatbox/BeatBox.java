package ru.betterstop.beatbox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BeatBox {

    JFrame frame;
    JLabel jLabel;
    Color colorOval = Color.BLACK;
    int x = 50;
    int y = 50;
    int xx = 3;
    int yy = 3;

    public static void main(String[] args) {
        BeatBox beatBox = new BeatBox();
        beatBox.go();
    }


    public void go() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(640, 480);

        JButton buttonColor = new JButton("Change colors");
        buttonColor.addActionListener(new ColorListener());
        frame.getContentPane().add(BorderLayout.SOUTH, buttonColor);

        JButton buttonBeep = new JButton("Beep");
        buttonBeep.addActionListener(new BeepListener());
        frame.getContentPane().add(BorderLayout.EAST, buttonBeep);

        jLabel = new JLabel("Тестовай Строка");
        frame.getContentPane().add(BorderLayout.NORTH, jLabel);

        DrawPanel myDrawPanel = new DrawPanel();
        frame.getContentPane().add(BorderLayout.CENTER, myDrawPanel);

        frame.setVisible(true);

        while(true){
            if (x > frame.getWidth()-120) xx = -3;
            if (x < 5) xx = 3;
            if (y > frame.getHeight()-120) yy = -3;
            if (y < 5) yy = 3;
            x += xx;
            y += yy;

            myDrawPanel.repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    class ColorListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int red = (int) (Math.random() * 255);
            int green = (int) (Math.random() * 255);
            int blue = (int) (Math.random() * 255);
            colorOval = new Color(red, green, blue);

            frame.repaint();
            jLabel.setText("Сменили цвет");
        }
    }

    class BeepListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            MidiPlayer.play();
            jLabel.setText("БИП");
        }
    }

    class DrawPanel extends JPanel{
        public void paintComponent(Graphics g){
            g.setColor(Color.WHITE);
            g.fillRect(0,0, this.getWidth(), this.getHeight());
            g.setColor(colorOval);
            g.fillOval(x, y, 40, 40);
        }
    }

}
