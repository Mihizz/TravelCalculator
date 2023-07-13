/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softproc.nemanjastepic3725;

import java.io.IOException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SoftProcNemanjaStepic3725 extends Application {

    final Label title = new Label("Refleksija");
    final Button easyMode = new Button("Lako");
    final Button normalMode = new Button("Srednje");
    final Button hardMode = new Button("Tesko");
    final Button customMode = new Button("Custom");
    final TextField customInput = new TextField();

    VBox vb = new VBox(10);
    HBox hb = new HBox(10);

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(new Group(), 330, 290);
        primaryStage.setTitle("Igrica refleksije");

        title.setStyle("-fx-font: 24 arial;");
        easyMode.setPrefSize(100, 30);
        normalMode.setPrefSize(100, 30);
        hardMode.setPrefSize(100, 30);

        customMode.setPrefSize(60, 30);
        customInput.setPrefSize(30, 30);

        hb.getChildren().addAll(customMode, customInput);
        vb.getChildren().addAll(title, easyMode, normalMode, hardMode, hb);

        vb.setSpacing(30);
        Insets margin = new Insets(0, 0, 0, 120);

        VBox.setMargin(title, margin);
        VBox.setMargin(easyMode, margin);
        VBox.setMargin(normalMode, margin);
        VBox.setMargin(hardMode, margin);
        VBox.setMargin(hb, margin);

        easyMode.setOnAction(event -> {
            try {
                GameFX.startGame(4);
            } catch (IOException e) {
            }
        });

        normalMode.setOnAction(event -> {
            try {
                GameFX.startGame(6);
            } catch (IOException e) {
            }
        });

        hardMode.setOnAction(event -> {
            try {
                GameFX.startGame(7);
            } catch (IOException e) {
            }
        });

        customMode.setOnAction(event -> {
            try {
                System.out.println(customInput.getText());
                int num = Integer.parseInt(customInput.getText());
                if (num != 0 && num > 0 && num < 20) {
                    GameFX.startGame(num);
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        });

        Group root = (Group) scene.getRoot();
        root.getChildren().addAll(vb);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
