package com.atos;

public class GamePiece {
    protected CheckersColor color;

    public GamePiece(CheckersColor color) {
        this.color = color;
    }

    public CheckersColor getColor() {
        return color;
    }

    public static GamePiece createGamePiece(char letter) {
        return letter == 'P' ? new GamePiece(CheckersColor.WHITE) : new GamePiece(CheckersColor.BLACK);
    }

    public char toFenChar() {
        return this.color == CheckersColor.BLACK ? 'p' : 'P';
    }

    public char getUnicode() {
//        if (isQueen) {
//            return color == CheckersColor.WHITE ? '\u2687' : '\u2689';
//        }
        return color == CheckersColor.WHITE ? '\u25CB' : '\u25CF';
    }
}
