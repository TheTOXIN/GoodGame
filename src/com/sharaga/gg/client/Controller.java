package com.sharaga.gg.client;

import com.sharaga.gg.utill.Const;
import com.sharaga.gg.utill.State;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import javax.swing.Timer;

public class Controller implements KeyListener, ActionListener {

    private Service ser;
    private View view;

    private boolean isUp, isLeft, isRight, isDown, isSpace;

    public Controller(Service ser, View view) {
        this.ser = ser;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        State state = State.NONE;

        if (isUp) state = State.UP;
        if (isLeft) state = State.LEFT;
        if (isRight) state = State.RIGHT;
        if (isDown) state = State.DOWN;
        if (isSpace) state = State.BANG;

        ser.informer(state);
        view.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        processKey(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        processKey(e.getKeyCode(), false);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //nothing...
    }

    private void processKey(int code, boolean press) {
        switch (code) {
            case KeyEvent.VK_W -> isUp = press;
            case KeyEvent.VK_A -> isLeft = press;
            case KeyEvent.VK_S -> isDown = press;
            case KeyEvent.VK_D -> isRight = press;
            case KeyEvent.VK_SPACE -> isSpace = press;
        }
    }
}
