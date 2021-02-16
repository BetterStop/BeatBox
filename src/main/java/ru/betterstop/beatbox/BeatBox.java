package ru.betterstop.beatbox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BeatBox {
    static MyDrawPanel myDrawPanel = new MyDrawPanel();
    JFrame jFrame = new JFrame("Музыкальный калейдоскоп");;
    static Color colorOval = Color.BLACK;


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
    }

    static class ColorListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int red = (int) (Math.random() * 255);
            int green = (int) (Math.random() * 255);
            int blue = (int) (Math.random() * 255);
            colorOval = new Color(red, green, blue);
        }
    }

    static class BeepListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            MidiPlayer.play();
        }
    }

}
