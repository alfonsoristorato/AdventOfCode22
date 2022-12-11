package Day9;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Path filePath = Path.of("src/Day9/Day9Input.txt");
        List<String> lines = new ArrayList<>();

        try {
            for (String line : Files.readAllLines(filePath)) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        Point head = new Point(0, 0);
        Point tail = new Point(0, 0);

        Point[] snake = new Point[9];
        for (int i = 0; i < 9; i++) {
            snake[i] = new Point(0, 0);
        }
        Set<Point> singTailVisited = new HashSet<>();
        Set<Point> snakeTailVisited = new HashSet<>();

        singTailVisited.add(tail);
        snakeTailVisited.add(snake[snake.length - 1]);

        for (String command : lines) {
            String direction = command.split(" ")[0];
            Integer numberOfMoves = Integer.parseInt(command.split(" ")[1]);
            switch (direction) {
                case "U":
                    for (int i = 0; i < numberOfMoves; i++) {
                        head = new Point(head.x, head.y - 1);
                        tail = moveKnots(head, tail);
                        singTailVisited.add(tail);
                        snake[0] = tail;
                        for (int y = 1; y < snake.length; y++) {
                            snake[y] = moveKnots(snake[y - 1], snake[y]);
                            if (y == snake.length - 1) {
                                snakeTailVisited.add(snake[snake.length - 1]);
                            }
                        }
                    }
                    break;

                case "D":
                    for (int i = 0; i < numberOfMoves; i++) {
                        head = new Point(head.x, head.y + 1);
                        tail = moveKnots(head, tail);
                        singTailVisited.add(tail);
                        snake[0] = tail;
                        for (int y = 1; y < snake.length; y++) {
                            snake[y] = moveKnots(snake[y - 1], snake[y]);
                            if (y == snake.length - 1) {
                                snakeTailVisited.add(snake[snake.length - 1]);
                            }
                        }
                    }
                    break;

                case "L":
                    for (int i = 0; i < numberOfMoves; i++) {
                        head = new Point(head.x - 1, head.y);
                        tail = moveKnots(head, tail);
                        singTailVisited.add(tail);
                        snake[0] = tail;
                        for (int y = 1; y < snake.length; y++) {
                            snake[y] = moveKnots(snake[y - 1], snake[y]);
                            if (y == snake.length - 1) {
                                snakeTailVisited.add(snake[snake.length - 1]);
                            }
                        }
                    }
                    break;

                case "R":
                    for (int i = 0; i < numberOfMoves; i++) {
                        head = new Point(head.x + 1, head.y);
                        tail = moveKnots(head, tail);
                        singTailVisited.add(tail);
                        snake[0] = tail;
                        for (int y = 1; y < snake.length; y++) {
                            snake[y] = moveKnots(snake[y - 1], snake[y]);
                            if (y == snake.length - 1) {
                                snakeTailVisited.add(snake[snake.length - 1]);
                            }
                        }
                    }
                    break;

            }

        }
        System.out.println(singTailVisited.size());
        System.out.println(snakeTailVisited.size());

    }

    static Point moveKnots(Point head, Point tail) {
        Integer xDiff = Math.abs(head.x - tail.x);
        Integer yDiff = Math.abs(head.y - tail.y);
        if (xDiff > yDiff) {
            if (head.x - tail.x > 1)
                tail = new Point(tail.x + 1, head.y);
            if (tail.x - head.x > 1)
                tail = new Point(tail.x - 1, head.y);
        } else if (xDiff < yDiff) {
            if (head.y - tail.y > 1)
                tail = new Point(head.x, tail.y + 1);
            if (tail.y - head.y > 1)
                tail = new Point(head.x, tail.y - 1);
        } else if (xDiff > 1) {
            if (head.x - tail.x > 1)
                tail = new Point(tail.x + 1, tail.y);
            if (tail.x - head.x > 1)
                tail = new Point(tail.x - 1, tail.y);
            if (head.y - tail.y > 1)
                tail = new Point(tail.x, tail.y + 1);
            if (tail.y - head.y > 1)
                tail = new Point(tail.x, tail.y - 1);
        }
        return tail;
    }

}
