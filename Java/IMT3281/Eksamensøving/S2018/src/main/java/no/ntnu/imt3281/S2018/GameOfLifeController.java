package no.ntnu.imt3281.S2018;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Optional;

/**
 *
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

    @FXML
    private void initialize() {
        rowField.setText(String.valueOf(rows));
        columnField.setText(String.valueOf(columns));
        game = new GameOfLife(rows, columns);
        rectangles = new Rectangle[rows][columns];
    }

    @FXML
    void drawGrid() {
        double widthPerGrid = gameOfLife.getWidth() / rows;
        double heightPerGrid = gameOfLife.getHeight() / columns;

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

    public void updateCell(MouseEvent mouseEvent) {

    }
}
