package ru.betterstop.beatbox;

import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;

public class BeatBox {
    public static void main(String[] args){
       //play();
        System.out.println("Hello");
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(640, 480);

        JButton button = new JButton("click me");
        TestListener testListener = new TestListener();
        button.addActionListener(testListener);
        frame.getContentPane().add(BorderLayout.SOUTH, button);

        MyDrawPanel myDrawPanel = new MyDrawPanel();

        frame.getContentPane().add(myDrawPanel);


        frame.setVisible(true);
    }

    public static void play(){

        int instrument = 1;  {
            for (int note = 70; note < 71; note+=5) {

                Sequencer sequenser = null;

                try {
                    sequenser = MidiSystem.getSequencer();
                    sequenser.open();
                    Sequence seq = new Sequence(Sequence.PPQ, 4);
                    Track track = seq.createTrack();

                    ShortMessage first = new ShortMessage();
                    first.setMessage(192, 1, instrument,0);
                    MidiEvent changeInstrument = new MidiEvent(first, 1);
                    track.add(changeInstrument);


                    ShortMessage a = new ShortMessage();
                    a.setMessage(144, 1, note, 100);
                    MidiEvent noteOn = new MidiEvent(a, 1);
                    track.add(noteOn);

                    ShortMessage b = new ShortMessage();
                    b.setMessage(128, 1, note, 100);
                    MidiEvent noteOff = new MidiEvent(b, 16);
                    track.add(noteOff);
                    sequenser.setSequence(seq);

                    sequenser.start();
                    Thread.sleep(300);
                    System.out.printf("Instrument: %d  Nota %d \n", instrument, note);


                } catch (MidiUnavailableException | InvalidMidiDataException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }

    }
}
