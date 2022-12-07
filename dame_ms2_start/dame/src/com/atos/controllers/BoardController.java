package com.atos.controllers;

import com.atos.Game;
import com.atos.components.FieldStack;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class BoardController {
    @FXML
    private GridPane grid;

    public void drawGame(Game game) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                FieldStack stack = new FieldStack(
                        game,
                        game.getBoard().getField(col, row),
                        grid,
                        ((row + col) % 2 == 0) ? Color.WHITE : Color.LIGHTGRAY
                );


                grid.add(stack, col, row);
            }
        }
    }
}
