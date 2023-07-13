/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softproc.nemanjastepic3725;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Nemanja
 */
public class GameFX {

    private static Timeline timeline;
    private static int secondsCount = 0;
    private static int minutesCount = 0;

    private static Label timerLabel;

    private FXMLLoader fxmlLoaderGame = new FXMLLoader(getClass().getResource("game.fxml"));

    public static void startGame(int gridSize) throws IOException {
        Stage window = new Stage();

        minutesCount = 0;
        secondsCount = 0;

        FXMLLoader fxmlLoader = new FXMLLoader(GameFX.class.getResource("game.fxml"));
        FXMLLoader fxmlLoader1 = new FXMLLoader(GameFX.class.getResource("game.fxml"));

        VBox root = new VBox(10);

        HBox hbGrid = new HBox(10);
        HBox hbCheck = new HBox(10);
        HBox hbTime = new HBox(10);

        Label timerText = new Label("Vreme:");
        Label finishLabel = new Label("");
        
        final Button compareColoredBlocks = new Button("Gotovo!");

        GameController gameController = new GameController();
        PictureController pictureController = new PictureController();

        gameController.setGridSize(gridSize);
        pictureController.setGridSize(gridSize);

        fxmlLoader.setController(pictureController);
        fxmlLoader1.setController(gameController);

        Parent rootPicture = fxmlLoader.load();
        Parent rootGame = fxmlLoader1.load();

        List pictureSet = pictureController.getColoredBlockLocations();
        List gameSet = gameController.getColoredBlockLocations();

        compareColoredBlocks.setOnAction(event -> {
            if (areEqual(gameSet, pictureSet)) {
                System.out.println("BRAVO");

                finishLabel.setText("Sve je tacno!");
                stopTimer();

                compareColoredBlocks.setDisable(true);
                compareColoredBlocks.setText("BRAVO!");

            } else {
                System.out.println("LOSE");
                finishLabel.setText("Imas gresku!");
            }
        });

        timerLabel = new Label("00:00");
        startTimer();

        hbGrid.getChildren().addAll(rootPicture, rootGame);
        hbCheck.getChildren().addAll(compareColoredBlocks, finishLabel);
        hbTime.getChildren().addAll(timerText, timerLabel);
        root.getChildren().addAll(hbGrid, hbCheck, hbTime);

        Scene scene;

        switch (gridSize) {
            case 4:
                scene = new Scene(root, 450, 280);
                break;
            case 6:
                scene = new Scene(root, 640, 380);
                break;
            case 7:
                scene = new Scene(root, 750, 430);
                break;
            default:
                scene = new Scene(root,(gridSize * 106),(gridSize * 62));
        }

        window.setTitle("Game start");
        window.setScene(scene);
        window.showAndWait();
    }

    public static boolean areEqual(List<int[]> list1, List<int[]> list2) {
        if (list1.size() != list2.size()) {
            return false;
        }
        
        for (int[] array : list1) {
            if (!contains(array, list2)) {
                return false;
            }
        }
        
        return true;
    }

    private static boolean contains(int[] array, List<int[]> list) {
        for (int[] other : list) {
            if (Arrays.equals(array, other)) {
                return true;
            }
        }
        return false;
    }

    private static void startTimer() {
        if (timeline != null) {
            timeline.stop();
        }
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), (ActionEvent event) -> {
            secondsCount++;
            if (secondsCount == 60) {
                secondsCount = 0;
                minutesCount++;
            }
            timerLabel.setText(String.format("%02d:%02d", minutesCount, secondsCount));
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private static void stopTimer() {
        if (timeline != null) {
            timeline.stop();
        }
    }

}
