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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

/**
 *
 * @author Nemanja
 */
public class PictureController implements Initializable {

    Random random = new Random();

    @FXML
    private GridPane gameGrid;

    private int gridSize;

    private List<int[]> coloredBlockLocations = new ArrayList<>(); // ArrayList to store locations

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Rectangle[][] rectangles = new Rectangle[gridSize][gridSize];

        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                Rectangle rectangle = new Rectangle(50, 50, Color.WHITE);
                rectangle.setStroke(Color.BLACK); // set the outline color
                gameGrid.add(rectangle, col, row);
                rectangles[row][col] = rectangle;
            }
        }

        int numColored = (int) (gridSize * gridSize * 0.45);
        for (int i = 0; i < numColored; i++) {
            int row, col;
            do {
                row = random.nextInt(gridSize);
                col = random.nextInt(gridSize);
            } while (rectangles[row][col].getFill() == Color.BLACK); // make sure the rectangle is not already colored
            rectangles[row][col].setFill(Color.BLACK);

            coloredBlockLocations.add(new int[]{row, col});
        }

        Collections.sort(coloredBlockLocations, Comparator.comparingInt(a -> a[0] * 100 + a[1]));

        System.out.println("Colored block locations:");
        for (int[] location : coloredBlockLocations) {
            System.out.println("row: " + location[0] + ", col: " + location[1]);
        }
    }

    public List<int[]> getColoredBlockLocations() {
        return coloredBlockLocations;
    }

    public void printColoredBlockLocations() {
        System.out.println("Colored block locations:");
        for (int[] location : coloredBlockLocations) {
            System.out.println("row: " + location[0] + ", col: " + location[1]);
        }
    }

    public void setGridSize(int gridSize) {
        this.gridSize = gridSize;
    }
}
