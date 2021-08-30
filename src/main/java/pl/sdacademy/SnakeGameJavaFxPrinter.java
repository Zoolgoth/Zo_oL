package pl.sdacademy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SnakeGameJavaFxPrinter implements SnakeGamePrinter {
    private GraphicsContext graphicsContext;
    private int pointSize;

    public SnakeGameJavaFxPrinter(GraphicsContext graphicsContext, int pointSize) {
        this.graphicsContext = graphicsContext;
        this.pointSize = pointSize;
    }

    private void drawPoint(Point point, Color color) {
        graphicsContext.setFill(color);
        graphicsContext.fillRect(
                point.getX() * pointSize,
                point.getY() * pointSize,
                pointSize,
                pointSize
        );
    }

    @Override
    public void print(SnakeGame snakeGame) {
        clearBoard(snakeGame);
        drawPoint(snakeGame.getApple(), Color.RED);
        drawSnake(snakeGame.getSnake());
    }

    private void clearBoard(SnakeGame snakeGame) {
        int boardWidth  = snakeGame.getXBound() * pointSize;
        int boardHeight  = snakeGame.getYBound() * pointSize;
        graphicsContext.clearRect(0, 0, boardWidth, boardHeight);
    }

    private void drawSnake(Snake snake) {
        drawPoint(snake.getHead(), Color.DARKGREEN);
        snake.getBody()
                .forEach(point -> drawPoint(point, Color.GREEN));
    }
}
