package com.atos.controllers;

import com.atos.CheckersColor;
import com.atos.Game;
import com.atos.exceptions.InvalidFenStringException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MainWindowController {
    @FXML
    private StackPane boardPane;

    @FXML
    private Label playerLabel;

    private BoardController boardController;

    private Stage mainStage;

    private Game game;
    
    public void initialize() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/Board.fxml"));
        AnchorPane board = fxmlLoader.load();

        boardController = fxmlLoader.getController();
        boardPane.getChildren().add(board);

        playerLabel.setText("Bitte ein neues Spiel starten");
    }

    public void newGame(final ActionEvent actionEvent) throws InvalidFenStringException {
        game = new Game();

        updateInfoLabel();

        // TODO observer

        boardController.drawGame(game);
    }

    public void loadGame(final ActionEvent actionEvent) throws IOException {

    }

    public void saveGame(final ActionEvent actionEvent) {

    }

    public void updateInfoLabel() {
        playerLabel.setText((game.getCurrentPlayer().getColor() == CheckersColor.WHITE ? "Weiß" : "Schwarz") + " ist dran");
    }

    public void setMainStage(final Stage stage) {
        mainStage = stage;
    }
}
