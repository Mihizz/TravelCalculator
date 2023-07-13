/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softproc.nemanjastepic3725;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Nemanja
 */
public class GameController implements Initializable {

    Random random = new Random();

    @FXML
    private GridPane gameGrid;

    private int gridSize;

    private final List<int[]> coloredBlockLocations = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Rectangle[][] rectangles = new Rectangle[gridSize][gridSize];

        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                final int finalRow = row;
                final int finalCol = col;

                Rectangle rectangle = new Rectangle(50, 50, Color.WHITE);
                rectangle.setStroke(Color.BLACK);
                gameGrid.add(rectangle, col, row);
                rectangles[row][col] = rectangle;


                rectangle.setOnMouseClicked((MouseEvent event) -> {
                    if (rectangle.getFill() == Color.WHITE) {
                        System.out.println(finalRow + "/ " + (gridSize - 1 - finalCol));
                        coloredBlockLocations.add(new int[]{finalRow, gridSize - 1 - finalCol});
                        rectangle.setFill(Color.BLACK);
                    } else {
                        int[] locationToRemove = new int[]{finalRow, gridSize - 1 - finalCol};
                        for (int[] location : coloredBlockLocations) {
                            if (Arrays.equals(location, locationToRemove)) {
                                coloredBlockLocations.remove(location);
                                break;
                            }
                        }
                        rectangle.setFill(Color.WHITE);
                    }
                });
            }
        }

        System.out.println("Colored block locations:");
        for (int[] location : coloredBlockLocations) {
            System.out.println("row: " + location[0] + ", col: " + location[1]);
        }
    }

    public List<int[]> getColoredBlockLocations() {
        Collections.sort(coloredBlockLocations, Comparator.comparingInt(a -> a[0] * 100 + a[1]));
        return coloredBlockLocations;
    }

    public void printColoredBlockLocations() {
        Collections.sort(coloredBlockLocations, Comparator.comparingInt(a -> a[0] * 100 + a[1]));
        System.out.println("Colored block locations:");
        for (int[] location : coloredBlockLocations) {
            System.out.println("row: " + location[0] + ", col: " + location[1]);
        }
    }

    public void setGridSize(int gridSize) {
        this.gridSize = gridSize;
    }
}
