package com.sharaga.gg.client;

import com.sharaga.gg.utill.Settings;

import javax.swing.*;

public class Window extends JFrame {

    private static final String TITLE = "GOOD GAME";
    private static final int WINDOW_WIDTH = Settings.W * Settings.SIZE + Settings.SIZE / 2;
    private static final int WINDOW_HEIGHT = Settings.H * Settings.SIZE + Settings.SIZE + Settings.SIZE / 2;

    public Window() {
        super(TITLE);

        super.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void init(View view, Controller control) {
        super.add(view);
        super.addKeyListener(control);
        super.setVisible(true);
    }
}
