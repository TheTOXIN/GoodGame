package com.sharaga.gg.client;

import com.sharaga.gg.utill.Const;

import javax.swing.*;

import static com.sharaga.gg.utill.Const.*;

public class Window extends JFrame {

    private static final String TITLE = "GOOD GAME";
    private static final int FUCK = 45;

    private static final int WINDOW_WIDTH = W * SIZE + SIZE / 2;
    private static final int WINDOW_HEIGHT = H * SIZE + (SIZE + FUCK) / 2;

    public Window() {
        super(TITLE);

        super.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void init(View view, Controller control) {
        super.add(view);
        super.addKeyListener(control);
        super.setVisible(true);

        this.timer(control);
    }

    private void timer(Controller control) {
        Timer timer = new Timer(SPEED, control);

        timer.setInitialDelay(0);
        timer.start();
    }
}
