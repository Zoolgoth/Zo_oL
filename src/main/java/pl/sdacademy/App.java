package pl.sdacademy;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Arrays;


/**
 * JavaFX App
 */
public class App extends Application {

    public static final int CANVAS_WIDTH = 400;
    public static final int CANVAS_HEIGHT = 400;
    public static final int POINT_SIZE = 20;
    private GraphicsContext graphicsContext;
    private SnakeGame snakeGame;

    @Override
    public void start(Stage stage) {
        initializeUserInterface(stage);
        initializeSnakeGame();
    }

    private void initializeSnakeGame() {
        int xBound = CANVAS_WIDTH / POINT_SIZE;
        int yBound = CANVAS_HEIGHT / POINT_SIZE;
        SnakeGameJavaFxPrinter snakeGamePrinter = new SnakeGameJavaFxPrinter(graphicsContext, POINT_SIZE);
        snakeGame = new SnakeGame(xBound, yBound, snakeGamePrinter);
        Thread snakeGameThread = new Thread(snakeGame::start);// new Thread(() -> snakeGame.start());
        snakeGameThread.setDaemon(true);
        snakeGameThread.start();
    }

    private void initializeUserInterface(Stage stage) {
        Button btnUp = new Button("góra");
        btnUp.setOnAction(event -> snakeGame.setSnakeDirection(Direction.UP));
        Button btnLeft = new Button("lewo");
        btnLeft.setOnAction(event -> snakeGame.setSnakeDirection(Direction.LEFT));
        Button btnRight = new Button("prawo");
        btnRight.setOnAction(event -> snakeGame.setSnakeDirection(Direction.RIGHT));
        Button btnDown = new Button("dół");
        btnDown.setOnAction(event -> snakeGame.setSnakeDirection(Direction.DOWN));
        HBox hBox = new HBox(
                btnUp,
                btnLeft,
                btnRight,
                btnDown
        );
        Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        graphicsContext = canvas.getGraphicsContext2D();
        VBox vBox = new VBox(
                canvas,
                hBox
        );
        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.show();

        //////////////////////////////////////////
        /////////// obsługa klawiszy /////////////
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case UP:
                    snakeGame.setSnakeDirection(Direction.UP);
                    break;
                case RIGHT:
                    snakeGame.setSnakeDirection(Direction.RIGHT);
                    break;
                case LEFT:
                    snakeGame.setSnakeDirection(Direction.LEFT);
                    break;
                case DOWN:
                    snakeGame.setSnakeDirection(Direction.DOWN);
                    break;
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }

}
