package com.sharaga.gg.server.model;

public class Message {

    private String text;
    private String player;

    public Message() {
    }

    public Message(String text, String player) {
        this.text = text;
        this.player = player;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (text != null ? !text.equals(message.text) : message.text != null) return false;
        return player != null ? player.equals(message.player) : message.player == null;
    }

    @Override
    public int hashCode() {
        int result = text != null ? text.hashCode() : 0;
        result = 31 * result + (player != null ? player.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "text='" + text + '\'' +
                ", nick='" + player + '\'' +
                '}';
    }
}
