package com.atos;

public class Field {
    protected CheckersColor color;

    protected GamePiece gamePiece;

    protected int x;

    protected int y;

    public Field(CheckersColor color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public Field(CheckersColor color, int x, int y, GamePiece gamePiece) {
        this(color, x, y);
        this.gamePiece = gamePiece;
    }

    public CheckersColor getColor() {
        return color;
    }

    public GamePiece getGamePiece() {
        return gamePiece;
    }

    public void setGamePiece(GamePiece gamePiece) {
        this.gamePiece = gamePiece;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isEmpty() {
        return this.getGamePiece() == null;
    }

    @Override
    public boolean equals(Object obj) {
        Field field = (Field)obj;
        return this.getX() == field.getX() && this.getY() == field.getY();
    }
}
