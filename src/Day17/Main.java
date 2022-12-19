package Day17;

import java.io.EOFException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.awt.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Path filePath = Path.of("src/Day17/Day17Input.txt");
        String commands = "";

        try {
            commands = Files.readString(filePath);
        } catch (IOException e) {
            System.out.println(e);
        }
        Set<Point> rocksStacked = new HashSet<>();
        Integer maxY = -4;
        Integer iterations = 0;
        Integer rockShapeBuilder = 1;
        long max = 1000000000000L;
        long previousHighestY = 0;
        Boolean patternFound = false;
        Point highest = new Point();
        while (rockShapeBuilder < max) {
            Rock rockFalling = buildRock(rockShapeBuilder % 5, maxY);
            Boolean rockStacked = false;
            rockShapeBuilder++;

            while (!rockStacked) {

                if (commands.charAt(iterations % commands.length()) == '>') {

                    if (rockFalling.getMaxX() + 1 <= 6) {
                        rockFalling.moveRight(rocksStacked);
                    }
                } else {
                    if (rockFalling.getMinX() - 1 >= 0) {
                        rockFalling.moveLeft(rocksStacked);
                    }
                }
                iterations++;
                Integer rockMaxYBeforeMovingDown = rockFalling.getMaxY();
                rockFalling.moveDown(rocksStacked);
                if (rockFalling.getMaxY() == rockMaxYBeforeMovingDown) {
                    // reached another rock or floor

                    for (Point point : rockFalling.getPoints()) {
                        rocksStacked.add(point);
                    }
                    highest = rocksStacked.stream().min(Comparator.comparing(Point::getY)).get();
                    maxY = highest.y - 4;
                    rockStacked = true;
                    // not the right way to find a pattern
                    if (rockShapeBuilder > 2023
                            && iterations % commands.length() == 0) {
                        long remainder = max % rockShapeBuilder;
                        long quotient = max / rockShapeBuilder;
                        previousHighestY = highest.y * quotient;
                        System.out.println("Found Pattern");
                        System.out.println(".....");
                        System.out.println("Moving loop up");
                        rockShapeBuilder = rockShapeBuilder % 5;
                        max = remainder + 1;
                        patternFound = true;
                    }
                }

            }

            if (rockShapeBuilder == 2023 && !patternFound) {
                System.out.println("Part 1 :" + Math.abs(highest.y));
            }
            if (rockShapeBuilder == max - 1) {
                long x = Math.abs(highest.y) + Math.abs(previousHighestY);
                System.out.println("Part 2 :" + x);
            }

            // 1567840632818
            // 1567840652109

            // 1567840652106

        }

        // displayGraph(highest, rocksStacked);

    }

    static Rock buildRock(Integer rockShape, Integer maxY) {
        List<Point> points = new ArrayList<>();
        Rock rock = new Rock();
        switch (rockShape) {
            case 1 -> {
                points.addAll(List.of(new Point(2, maxY), new Point(3, maxY), new Point(4, maxY), new Point(5, maxY)));
                rock = new Rock(maxY, maxY, 5, 2, points);
            }
            case 2 -> {
                points.addAll(List.of(new Point(3, maxY - 2), new Point(2, maxY - 1), new Point(3, maxY - 1),
                        new Point(4, maxY - 1), new Point(3, maxY)));
                rock = new Rock(maxY, maxY - 2, 4, 2, points);
            }
            case 3 -> {
                points.addAll(List.of(new Point(4, maxY - 2), new Point(4, maxY - 1), new Point(2, maxY),
                        new Point(3, maxY), new Point(4, maxY)));
                rock = new Rock(maxY, maxY - 2, 4, 2, points);
            }
            case 4 -> {
                points.addAll(List.of(new Point(2, maxY - 3), new Point(2, maxY - 2), new Point(2, maxY - 1),
                        new Point(2, maxY)));
                rock = new Rock(maxY, maxY - 3, 2, 2, points);
            }
            case 0 -> {
                points.addAll(List.of(new Point(2, maxY - 1), new Point(3, maxY - 1), new Point(2, maxY),
                        new Point(3, maxY)));
                rock = new Rock(maxY, maxY - 1, 3, 2, points);
            }
        }
        return rock;
    }

    static void displayGraph(Point highest, Set<Point> rocksStacked) {
        for (int i = highest.y; i <= -1; i++) {
            System.out.println();
            for (int j = 0; j < 7; j++) {

                if (rocksStacked.contains(new Point(j, i))) {
                    System.out.print(("#"));
                } else {
                    System.out.print(("."));
                }
            }
        }

    }

}

// |..@@@@.|
// |.......|
// |.......|
// |.......|
// |....#..| // ....#..
// |....#..| // ....#..
// |....##.| // ....##.
// |##..##.| // ##..##.
// |######.| // ######.
// |.###...| // .###...
// |..#....| // ..#....
// |.####..| // .####..
// |....##.| // ....##.
// |....##.| // ....##.
// |....#..| // ....#..
// |..#.#..| // ..#.#..
// |..#.#..| // ..#.#..
// |#####..| // #####..
// |..###..| // ..###..
// |...#...| // ...#...
// |..####.| // ..####.
// +-------+
