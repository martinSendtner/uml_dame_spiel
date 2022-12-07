package com.atos;

public class Player {
    protected CheckersColor color;

    public Player(CheckersColor color) {
        this.color = color;
    }

    public CheckersColor getColor() {
        return color;
    }

    public char toFenChar() {
        return this.getColor() == CheckersColor.WHITE ? 'w' : 'b';
    }

    @Override
    public String toString() {
        return String.valueOf(this.toFenChar());
    }
}
