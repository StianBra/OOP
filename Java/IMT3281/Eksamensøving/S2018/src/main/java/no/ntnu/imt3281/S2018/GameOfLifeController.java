package no.ntnu.imt3281.S2018;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Optional;

/**
 * Controller-class for the game of life GUI
 */
public class GameOfLifeController {

    @FXML
    private AnchorPane gameOfLife;

    @FXML
    private TextField rowField;

    @FXML
    private TextField columnField;

    private GameOfLife game;
    private int rows = 10;
    private int columns = 10;
    private Rectangle[][] rectangles;
    private double widthPerGrid;
    private double heightPerGrid;

    /**
     * Initializes some of the GUI elements to sane defaults
     */
    @FXML
    private void initialize() {
        rowField.setText(String.valueOf(rows));
        columnField.setText(String.valueOf(columns));
        game = new GameOfLife(rows, columns);
        rectangles = new Rectangle[rows][columns];
    }

    /**
     * Draws all the rectangles for the game of life, given by amount of rows/columns
     */
    @FXML
    void drawGrid() {
        widthPerGrid = gameOfLife.getWidth() / rows;
        heightPerGrid = gameOfLife.getHeight() / columns;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Rectangle rect = new Rectangle(widthPerGrid, heightPerGrid);
                rect.setFill(Color.TRANSPARENT);
                rect.setStroke(Color.BLACK);
                gameOfLife.getChildren().add(rect);
                rect.setTranslateX(i * widthPerGrid);
                rect.setTranslateY(j * heightPerGrid);
                
                rectangles[j][i] = rect;
            }
        }
    }

    @FXML
    private void updateBoardSize() {
        Optional<Integer> rowCountText = Optional.of(Integer.parseInt(rowField.getText()));
        Optional<Integer> columnCountText = Optional.of(Integer.parseInt(columnField.getText()));

        int rowCount = 10;
        int columnCount = 10;

        if (rowCountText.isPresent()) {
            rowCount = rowCountText.get();
        }

        if (columnCountText.isPresent()) {
            columnCount = columnCountText.get();
        }
    }

    /**
     * Sets a living (black) cell to a dead (white) cell, and vice verca
     * @param mouseEvent The registered click on a cell
     */
    public void updateCell(MouseEvent mouseEvent) {
        // First finds the x/y coordinates for the source, divided by per-grid size to get the array-indexes
        // Also, uses getX and getY in order to get Y and X respectively, due to arrays/GUI coordinates being opposite
        int y = (int) (mouseEvent.getX() / widthPerGrid);
        int x = (int) (mouseEvent.getY() / heightPerGrid);

        // If the rectangle we have located was white
        if (rectangles[x][y].getFill() == Color.TRANSPARENT) {
            // Then we will register it as a living cell in the game of life
            game.setLivingCell(x, y);

            // And color it black
            rectangles[x][y].setFill(Color.BLACK);
        } else {
            // The rectangle was black, so we will register it as a dead cell in the game of life
            game.setDeadCell(x, y);

            // And color it white
            rectangles[x][y].setFill(Color.TRANSPARENT);
        }
    }
}
