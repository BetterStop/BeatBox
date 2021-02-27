package ru.betterstop.beatbox;

import javax.sound.midi.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;


public class BeatBox {
    JPanel panel;
    ArrayList<JCheckBox> checkBoxList;
    Sequencer sequencer;
    Sequence sequence;
    Track track;
    JFrame frame;
    String[] instrumentNames = {"Bass Drum", "Closed Hi-Hat", "Open Hi-Hat", "Acoustic Snare", "Crash Cymbal",
        "Hand Clap", "High Tom", "Hi Bongo", "Maracas", "Whistle", "Low Conga", "Cowbell", "Vibraslap",
        "Low-mid Tom", "High Agogo", "Open Hi Conga"};
    int[] instruments = {35, 42, 46, 38, 49, 39, 50, 60, 70, 72, 64, 56, 58, 47, 67, 63};
    private boolean start = false;

    static MyDrawPanel myDrawPanel = new MyDrawPanel();
    JFrame jFrame = new JFrame("Музыкальный калейдоскоп");;
    static Color colorOval = Color.BLACK;


    public static void main(String[] args) {
        BeatBox beatBox = new BeatBox();
        beatBox.buildGui();
    }

    public boolean isStarted() {
        return  start;
    }

    public void startOn() {
        start = true;
    }

    public void startOff() {
        start = false;
    }

    public void buildGui() {
        frame = new JFrame("Бит Бокс");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BorderLayout layout = new BorderLayout();
        JPanel background = new JPanel(layout);
        background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        checkBoxList = new ArrayList<JCheckBox>();
        Box buttonBox = new Box(BoxLayout.Y_AXIS);

        JButton start = new JButton("Start");
        start.addActionListener(listener -> {
            startOn();
            buildTrackAndStart();
        });
        buttonBox.add(start);

        JButton stop = new JButton("Stop");
        stop.addActionListener(listener -> {
            startOff();
            sequencer.stop();
        });
        buttonBox.add(stop);

        JButton upTempo = new JButton("Tempo Up");
        upTempo.addActionListener(listener -> {
            System.out.println("upTempo");
        });
        buttonBox.add(upTempo);

        JButton downTempo = new JButton("Tempo Down");
        downTempo.addActionListener(listener -> {
            System.out.println("downTempo");
        });
        buttonBox.add(downTempo);

        Box nameBox = new Box(BoxLayout.Y_AXIS);
        Arrays.stream(instrumentNames).forEach(instName -> nameBox.add(new Label(instName)));

        background.add(BorderLayout.EAST, buttonBox);
        background.add(BorderLayout.WEST, nameBox);

        frame.getContentPane().add(background);

        GridLayout grid = new GridLayout(16, 16);
        grid.setVgap(1);
        grid.setHgap(2);
        panel = new JPanel(grid);
        background.add(BorderLayout.CENTER, panel);

        for (int i = 0; i < 256; i++) {
            JCheckBox checkBox = new JCheckBox();
            checkBox.setSelected(false);
                checkBox.addActionListener(listener -> {
                    if (isStarted()) {
                        buildTrackAndStart();
                    }
                });
            checkBoxList.add(checkBox);
            panel.add(checkBox);
        }

        setUpMidi();

        frame.setBounds(50, 50, 300, 300);
        frame.pack();
        frame.setVisible(true);
    }

    public void setUpMidi() {
        try {
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequence = new Sequence(Sequence.PPQ, 4);
            track = sequence.createTrack();
            sequencer.setTempoInBPM(120);
        } catch (MidiUnavailableException | InvalidMidiDataException e) {
            e.printStackTrace();
        }
    }

    public void buildTrackAndStart() {
        int[] trackList = null;

        sequence.deleteTrack(track);
        track = sequence.createTrack();

        for (int i = 0; i < 16; i++) {
            trackList = new int[16];
            int key = instruments[i];

            for (int j = 0; j < 16; j++) {
                JCheckBox checkBox = checkBoxList.get(j + (16 * i));
                if (checkBox.isSelected()) trackList[j] = key;
                else trackList[j] = 0;
            }
            makeTracks(trackList);
        }
        track.add(makeEvent(192, 9, 1, 0, 15));
        try {
            sequencer.setSequence(sequence);
            sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
            sequencer.start();
            sequencer.setTempoInBPM(120);
        } catch(Exception e) {e.printStackTrace();}
    }

    public void makeTracks(int[] list) {
        for (int i = 0; i < 16; i++) {
            int key = list[i];
            if (key != 0) {
                track.add(makeEvent(144, 9, key, 100, i));
                track.add(makeEvent(128, 9, key, 100, i + 1));
            }
        }
        track.add(makeEvent(176, 1,127, 0, 16));
    }

    public MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
        MidiEvent event = null;
        try {
            ShortMessage shortMessage = new ShortMessage();
            shortMessage.setMessage(comd, chan, one, two);
            event = new MidiEvent(shortMessage, tick);
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        }
        return event;
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

        JTextArea fielCopy = new JTextArea(10, 28);
        JScrollPane scrollPane = new JScrollPane(fielCopy);
        fielCopy.setLineWrap(true);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JTextField field = new JTextField(21);

        field.addActionListener(action ->  {
                fielCopy.append(field.getText() + "\n");
                field.setText("");
        });
        JCheckBox checkBox = new JCheckBox("Выделить");
        checkBox.addActionListener(action -> {
            if (checkBox.isSelected()) {
                fielCopy.requestFocus();
                fielCopy.selectAll();
            } else field.requestFocus();
        });



        jPanel.setBackground(Color.DARK_GRAY);
        jFrame.getContentPane().add(BorderLayout.EAST, jPanel);

        JPanel drawPanel = new JPanel();
        drawPanel.setBackground(Color.GRAY);
        drawPanel.add(field);
        drawPanel.add(checkBox);
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
