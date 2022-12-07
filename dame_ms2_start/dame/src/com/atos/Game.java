package com.atos;

import com.atos.exceptions.InvalidFenStringException;
import com.atos.exceptions.InvalidMoveException;
import com.atos.exceptions.InvalidPlayerException;
import com.atos.exceptions.NoMoveException;

import java.util.ArrayList;
import java.util.Observable;

public class Game extends Observable {
    protected Board board;

    protected Player[] players = new Player[2];

    protected Player currentPlayer;

    protected ArrayList<Move> moves = new ArrayList<>();

    protected Field selection;

    public Game() throws InvalidFenStringException {
        this.board = new Board(8);

        this.players[0] = new Player(CheckersColor.WHITE);
        this.players[1] = new Player(CheckersColor.BLACK);

        this.currentPlayer = this.players[0];
    }

    public Game(String fenString) throws InvalidMoveException, InvalidFenStringException {
        this();

        parseFenString(fenString);
    }

    protected void parseFenString(String fenString) throws InvalidFenStringException {
        String[] fenParts = fenString.split(" ");

        if (fenParts.length != 2) {
            throw new InvalidFenStringException();
        }

        if (!fenParts[1].matches("^([wb])$")) {
            throw new InvalidFenStringException();
        }

        this.currentPlayer = fenParts[1].equals("w") ? this.players[0] : this.players[1];

        this.board.parseFenString(fenParts[0]);
    }

    public void advance(String move) throws InvalidMoveException, InvalidPlayerException, NoMoveException {
        String[] fields = move.split("-");

        Field from = this.board.getField(fields[0]);
        Field to = this.board.getField(fields[1]);

        boolean isMoveDiagonal = Math.abs(from.getX() - to.getX()) == Math.abs(from.getY() - to.getY());
        if (from.isEmpty() || !to.isEmpty() || !isMoveDiagonal) {
            throw new InvalidMoveException();
        }

        if (from.getGamePiece().getColor() != this.currentPlayer.getColor()) {
            throw new InvalidPlayerException();
        }

        if (from.equals(to)) {
            throw new NoMoveException();
        }

        Move m = new Move(this.currentPlayer, from, to);
        this.moves.add(m);

        to.setGamePiece(from.getGamePiece());
        from.setGamePiece(null);
        // TODO check if game piece is captured

        // switch player
        this.currentPlayer = this.currentPlayer.getColor() == this.players[0].getColor() ? this.players[1] : this.players[0];
    }

    public String toFenString() {
        return this.board.toFenString() + " " + this.currentPlayer.toFenChar();
    }

    @Override
    public String toString() {
        return this.toFenString();
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public Board getBoard() {
        return this.board;
    }

    public boolean hasSelection() {
        return selection != null;
    }

    public Field getSelection() {
        return selection;
    }

    public void setSelection(Field selection) {
        this.selection = selection;

        setChanged();
        notifyObservers();
    }
}
