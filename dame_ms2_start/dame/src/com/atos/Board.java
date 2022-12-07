package com.atos;

import com.atos.exceptions.InvalidFenStringException;

public class Board {
    protected int size;

    protected Field[][] fields;

    public Board(int size) throws InvalidFenStringException {
        this.size = size;
        this.fields = new Field[size][size];

        // Array mit Objekten initialisieren
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                int shift = y % 2 == 0 ? 0 : 1;
                CheckersColor color = (x + shift) % 2 == 0 ? CheckersColor.WHITE : CheckersColor.BLACK;
                this.fields[y][x] = new Field(color, x, y);
            }
        }

        setDefaultGamePieces();
    }

    public void parseFenString(String fenString) throws InvalidFenStringException {
        if (!fenString.matches("^([pPqQ1-8/])+$")) {
            throw new InvalidFenStringException();
        }

        String[] fenParts = fenString.split("/");

        if (fenParts.length != this.size) {
            throw new InvalidFenStringException();
        }

        for (int y = 0; y < fenParts.length; y++) {
            String row = fenParts[y];
            char[] rowParts = row.toCharArray();

            int col = 0;
            for (char symbol : rowParts) {
                if (Character.isDigit(symbol)) {
                    int emptyColsCount = Integer.parseInt(String.valueOf(symbol));
                    for (int x = col; x < emptyColsCount+col; x++) {
                        this.fields[y][x].setGamePiece(null);
                    }
                    col += emptyColsCount;
                } else {
                    GamePiece gamePiece = GamePiece.createGamePiece(symbol);
                    this.fields[y][col].setGamePiece(gamePiece);
                    col++;
                }
            }
        }
    }

    public void setDefaultGamePieces() throws InvalidFenStringException {
        this.parseFenString("1p1p1p1p/p1p1p1p1/1p1p1p1p/8/8/P1P1P1P1/1P1P1P1P/P1P1P1P1");
    }

    public Field getField(String coordinates) {
        char[] parts = coordinates.toCharArray();

        int index = Integer.parseInt(String.valueOf(parts[1]));
        int y = this.size - index;
        int x = parts[0] - 'a';

        return this.fields[y][x];
    }

    public Field getField(int x, int y) {
        return this.fields[y][x];
    }

    public String toFenString() {
        StringBuilder result = new StringBuilder();

        for (int y = 0; y < size; y++) {
            int emptyCount = 0;

            for (int x = 0; x < size; x++) {
                if (!this.fields[y][x].isEmpty()) {
                    if (emptyCount > 0) {
                        result.append(emptyCount);
                        emptyCount = 0;
                    }

                    result.append(this.fields[y][x].getGamePiece().toFenChar());
                } else {
                    emptyCount++;
                }
            }

            if (emptyCount > 0) {
                result.append(emptyCount);
            }

            if (y < (size - 1)) {
                result.append("/");
            }
        }

        return result.toString();
    }

    @Override
    public String toString() {
        return this.toFenString();
    }
}
