package pl.sdacademy;

import java.util.Collections;
import java.util.Random;

public class SnakeGame {
    private int xBound;
    private int yBound;
    private Snake snake;
    private Point apple;
    private SnakeGamePrinter printer;

    public SnakeGame(int xBound, int yBound, Snake snake, SnakeGamePrinter printer) {
        this.xBound = xBound;
        this.yBound = yBound;
        this.snake = snake;
        this.printer = printer;
    }

    public SnakeGame(int xBound, int yBound, SnakeGamePrinter printer) {
        this.xBound = xBound;
        this.yBound = yBound;
        this.printer = printer;
        snake = new Snake(new Point(0, 0), Collections.emptyList(), Direction.RIGHT);
    }

    public void setSnakeDirection(Direction direction) {
        snake.setDirection(direction);
    }

    public int getXBound() {
        return xBound;
    }

    public int getYBound() {
        return yBound;
    }

    public Snake getSnake() {
        return snake;
    }

    public Point getApple() {
        return apple;
    }

    public void start() {
        randomizeApple();
        while (isSnakeInBounds()) {
            printer.print(this);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            snake.expand();
            if (snake.getHead().equals(apple)) {
                randomizeApple();
            } else {
                snake.cutTail();
            }
        }
    }

    private boolean isSnakeInBounds() {
        Point head = snake.getHead();
        int headX = head.getX();
        int headY = head.getY();
        return headX >= 0 && headX < xBound
                && headY >= 0 && headY < yBound;
    }

    private void randomizeApple() {
        Random random = new Random();
        do {
            int appleX = random.nextInt(xBound);
            int appleY = random.nextInt(yBound);
            apple = new Point(appleX, appleY);
        } while (snake.contains(apple));
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int y = 0; y < yBound; y++){
            for (int x = 0; x < xBound; x++){
                Point point = new Point(x, y);
                if (apple.equals(point)) {
                    stringBuilder.append('A');
                } else if (snake.getHead().equals(point)) {
                    stringBuilder.append('H');
                } else if (snake.getBody().contains(point)) {
                    stringBuilder.append('B');
                } else {
                    stringBuilder.append('.');
                }
            }
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }
}
