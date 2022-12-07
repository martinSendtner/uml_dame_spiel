package com.atos;

import com.atos.controllers.MainWindowController;
import com.atos.exceptions.CheckersException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Scanner;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
// Hallo Martin, meine Welt!
//        Scanner scanner = new Scanner(System.in);
//
//        String fenString = "1p1p1p1p/p1p1p1p1/1p1p1p1p/8/8/P1P1P1P1/1P1P1P1P/P1P1P1P1 w";
//        if (args.length == 2) {
//            fenString = args[0] + " " + args[1];
//        }
//
//        try {
//            Game game = new Game(fenString);
//
//            System.out.println(game);
//
//            String input;
//            do {
//                System.out.print("Zugfolge: ");
//                input = scanner.nextLine();
//
//                String[] moves = input.split(";");
//                for (String move : moves) {
//                    game.advance(move);
//                    System.out.println("Move " + move + ": " + game);
//                }
//            } while (!input.equals(""));
//
//        } catch (CheckersException e) {
//            System.exit(e.getCode());
//        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("views/MainWindow.fxml"));

        Pane root = fxmlLoader.load();
        MainWindowController controller = fxmlLoader.getController();

        controller.setMainStage(stage);

        int minWidth = 590;
        int minHeight = 620;

        Scene scene = new Scene(root);
        stage.setTitle("Checkers Game");
        stage.setScene(scene);
        stage.setMinWidth(minWidth);
        stage.setMinHeight(minHeight);
        stage.show();
    }
}