package ru.betterstop.beatbox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestListener implements ActionListener {

    BeatBox beatBox;

    public TestListener(){

    }

    public TestListener(BeatBox beatBox){
        this.beatBox = beatBox;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       // beatBox.frameRepaint();
       // BeatBox.play();
    }
}
