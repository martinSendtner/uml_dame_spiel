package com.atos;

public class Move {
    protected Player player;

    protected Field from;

    protected Field to;

    protected GamePiece gamePiece;

    public Move(Player player, Field from, Field to) {
        this.player = player;
        this.from = from;
        this.to = to;

        this.gamePiece = from.getGamePiece();
    }
}
