package no.ntnu.imt3281.S2018;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Timer;
import java.util.TimerTask;

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

    @FXML
    private Label generationNum;

    @FXML
    private ChoiceBox<String> speedDropdown;

    private GameOfLife game;
    private int rows = 10;
    private int columns = 10;
    private Rectangle[][] rectangles;
    private double widthPerGrid;
    private double heightPerGrid;
    private int generations;
    private boolean active;
    private Timer timer;

    /**
     * Initializes the GUI elements to defaults
     */
    @FXML
    private void initialize() {
        rowField.setText(String.valueOf(rows));
        columnField.setText(String.valueOf(columns));
        game = new GameOfLife(rows, columns);
        rectangles = new Rectangle[rows][columns];
        gameOfLife.getChildren().clear();
        generations = 0;

        generationNum.setText(String.valueOf(generations));
        speedDropdown.setItems(FXCollections.observableArrayList("0.5 generasjoner pr. sekund", "1 generasjon pr. sekund", "2 generasjoner pr. sekund"));
        speedDropdown.setValue("1 generasjon pr. sekund");
    }

    /**
     * Draws all the rectangles for the game of life, given by amount of rows/columns
     */
    @FXML
    void drawGrid() {
        // Removes the old rectangles
        gameOfLife.getChildren().removeAll();

        // Gets the width/height per rectangle
        widthPerGrid = gameOfLife.getWidth() / rows;
        heightPerGrid = gameOfLife.getHeight() / columns;

        // Iterates through every row/column cell
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                // And creates new rectangles, making them white with black edges
                Rectangle rect = new Rectangle(widthPerGrid, heightPerGrid);
                rect.setFill(Color.TRANSPARENT);
                rect.setStroke(Color.BLACK);

                // Adds the rectangles to the anchorpane
                gameOfLife.getChildren().add(rect);
                rect.setTranslateX(i * widthPerGrid);
                rect.setTranslateY(j * heightPerGrid);

                // And adds them to the rectangles array
                rectangles[j][i] = rect;
            }
        }
    }

    /**
     * Sets a living (black) cell to a dead (white) cell, and vice verca
     * @param mouseEvent The registered click on a cell
     */
    public void updateCell(MouseEvent mouseEvent) {
        // First checks that the game is not currently actively generating new iterations
        if (!active) {
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

    /**
     * Iterates one step forward in the game of life, and syncronizes the GUI rectangles with the logic cells
     */
    public void generateStep() {
        // First checks that the game is not currently actively generating new iterations
        if (!active) {
            // Generates the next state of the game, and updates the GUI
            game.generateNextState();
            syncRectsWithLogic(game, rectangles);

            // Increments the generations # text
            generationNum.setText(String.valueOf(generations++));
        }
    }

    /**
     * Sets the GUI rectangles to the game logic cells' states
     * @param game The game logic array
     * @param rectangles The GUI rectangle array
     */
    private void syncRectsWithLogic(GameOfLife game, Rectangle[][] rectangles) {
        // Iterates through every cell
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                // If the cell at [i][j] is alive, color the rectangle black, or else color it transparent
                rectangles[i][j].setFill(game.isAlive(i, j) ? Color.BLACK : Color.TRANSPARENT);
            }
        }
    }

    /**
     * Starts generating new states for the game of life
     */
    public void startGeneration() {
        // Checks that the generation is not already in progress
        if (!active) {
            int speed = 0;

            // Gets the speed for generations per second based on the dropdown-menu's observable list-string
            switch (speedDropdown.getValue().charAt(0)) {
                case '0':
                    // String starts with 0, so it must be '0.5', set it to 2000ms
                    speed = 2000;
                    break;
                case '1':
                    // String starts with 1, so it must be '1', set it to 1000ms
                    speed = 1000;
                    break;
                case '2':
                    // String starts with 2, so it must be '2', set it to 500ms
                    speed = 500;
                    break;
                default:
                    break;
            }

            // Set the game generation to active (cannot add/remove cells or step through iterations until paused)
            active = true;

            // Sets up a timed task for generating new game of life states
            timer = new Timer();
            TimerTask animation = new TimerTask() {
                public void run() {
                    // Generates the next state of the logic array
                    game.generateNextState();
                    Platform.runLater(() -> {
                        // Increases the # generations text
                        generationNum.setText(String.valueOf(generations++));

                        // Updates the GUI to match the logic array
                        syncRectsWithLogic(game, rectangles);
                    });
                }
            };

            // Sets the task to run every 500, 1000, or 2000ms depending on 'speed'
            timer.schedule(animation, 0, speed);
        }
    }

    /**
     * Stops generating new states for the game of life
     */
    public void pauseGeneration() {
        active = false;
        timer.cancel();
    }

    /**
     * Resets the game of life-grid
     */
    public void resetGrid() {
        // First checks that the game is not actively generating new states
        if (!active) {
            // Gets the new size of the board
            rows = Integer.parseInt(rowField.getText());
            columns = Integer.parseInt(columnField.getText());

            // Sets up the GUI rectangles & logic array to the new sizes, resets other variables
            initialize();

            // Draws the new GUI
            drawGrid();
        }
    }
}
