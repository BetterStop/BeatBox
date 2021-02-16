package ru.betterstop.beatbox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BeatBox {
    static MyDrawPanel myDrawPanel = new MyDrawPanel();
    JFrame jFrame = new JFrame("Музыкальный калейдоскоп");;
    JLabel jLabel;
    static Color colorOval = Color.BLACK;
    static int x = 50;
    static int y = 50;
    static int xx = 3;
    static int yy = 3;

    public static void main(String[] args) {
        BeatBox beatBox = new BeatBox();
        beatBox.go();
    }


    public void go() {
        JPanel jPanel = new JPanel();

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(640, 480);

        JButton buttonColor = new JButton("Change colors");
        buttonColor.addActionListener(new BeatBox.ColorListener());
        jPanel.add(BorderLayout.SOUTH, buttonColor);

        JButton buttonBeep = new JButton("Beep");
        buttonBeep.addActionListener(new BeatBox.BeepListener());
        jPanel.add(BorderLayout.EAST, buttonBeep);

        JTextArea fielCopy = new JTextArea(10, 20);
        JScrollPane scrollPane = new JScrollPane(fielCopy);
        fielCopy.setLineWrap(true);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);


        JTextField field = new JTextField(21);

        field.addActionListener(action ->  {
                fielCopy.append(field.getText() + "\n");
                field.setText("");
        });



        jPanel.setBackground(Color.DARK_GRAY);
        jFrame.getContentPane().add(BorderLayout.EAST, jPanel);

        JPanel drawPanel = new JPanel();
        drawPanel.setBackground(Color.GRAY);
        drawPanel.add(field);
        drawPanel.add(scrollPane);
        jFrame.getContentPane().add(BorderLayout.CENTER, drawPanel);

        jFrame.setVisible(true);

//        while(true){
//            if (x > jFrame.getWidth()-120) xx = -3;
//            if (x < 5) xx = 3;
//            if (y > jFrame.getHeight()-120) yy = -3;
//            if (y < 5) yy = 3;
//
//            x += xx;
//            y += yy;

           // myDrawPanel.repaint();
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
           // myDrawPanel.repaint();
    //    }
    }

    static class ColorListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int red = (int) (Math.random() * 255);
            int green = (int) (Math.random() * 255);
            int blue = (int) (Math.random() * 255);
            colorOval = new Color(red, green, blue);

            //myDrawPanel.repaint();
          //  jLabel.setText("Сменили цвет");
        }
    }

    static class BeepListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            MidiPlayer.play();
            //myDrawPanel.repaint();
           // jLabel.setText("БИП");
        }
    }

}
