package Day14;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Path filePath = Path.of("src/Day14/Day14Input.txt");
        List<String> input = new ArrayList<>();

        try {
            input = Files.readAllLines(filePath);
        } catch (IOException e) {
            System.out.println(e);
        }
        Map<Point, String> grid = new HashMap<>();
        Integer lowestX = Integer.MAX_VALUE;
        Integer highestX = Integer.MIN_VALUE;
        Integer highestY = Integer.MIN_VALUE; // lowest would have been more appropriate, as we are going down
        for (String line : input) {
            line = line.replaceAll(" ", "");
            String[] combinations = line.split("->");

            for (int i = 0; i < combinations.length - 1; i++) {
                Integer xStart = Integer.parseInt(combinations[i].split(",")[0]);
                Integer yStart = Integer.parseInt(combinations[i].split(",")[1]);
                Integer xEnd = Integer.parseInt(combinations[i + 1].split(",")[0]);
                Integer yEnd = Integer.parseInt(combinations[i + 1].split(",")[1]);
                if (xStart > highestX)
                    highestX = xStart;
                if (xStart < lowestX)
                    lowestX = xStart;
                if (xEnd > highestX)
                    highestX = xEnd;
                if (xStart < lowestX)
                    lowestX = xStart;
                if (yEnd > highestY)
                    highestY = yEnd;
                // same x
                if (Integer.compare(xStart, xEnd) == 0) {
                    // going down
                    if (Integer.compare(yStart, yEnd) < 0) {
                        for (int y = yStart; y <= yEnd; y++) {
                            grid.put(new Point(xStart, y), "Rock");
                        }
                    }
                    // going up
                    else {
                        for (int y = yStart; y >= yEnd; y--) {
                            grid.put(new Point(xStart, y), "Rock");
                        }
                    }
                }
                // same y
                else {
                    // going right
                    if (Integer.compare(xStart, xEnd) < 0) {
                        for (int x = xStart; x <= xEnd; x++) {
                            grid.put(new Point(x, yStart), "Rock");
                        }
                    }
                    // going left
                    else {
                        for (int x = xStart; x >= xEnd; x--) {
                            grid.put(new Point(x, yStart), "Rock");
                        }
                    }
                }
            }
        }

        Boolean sandCanDrop = true;
        Point sandStart = new Point(500, 0);
        Point stepDown = sandStart;
        Integer placements = 0;
        System.out.println(highestX);
        System.out.println(lowestX);
        System.out.println(highestY);
        while (sandCanDrop) {
            // if stepDown.x < lowest point.x
            // going into abyss, stop loop
            if (stepDown.x <= lowestX || stepDown.x >= highestX || stepDown.y >= highestY) {
                sandCanDrop = false;
            }

            // if down is empty, reassign and loop again
            if (!grid.containsKey(new Point(stepDown.x, stepDown.y + 1))) {
                stepDown = new Point(stepDown.x, stepDown.y + 1);
                continue;
            } else {
                // down occupied, move down-left or down-right

                // if down-left is empty, reassign and loop again
                if (!grid.containsKey(new Point(stepDown.x - 1, stepDown.y + 1))) {
                    stepDown = new Point(stepDown.x - 1, stepDown.y + 1);
                    continue;
                }
                // down-left occupied, check down-right
                else {
                    // if down-right is empty, reassign and loop again
                    if (!grid.containsKey(new Point(stepDown.x + 1, stepDown.y + 1))) {
                        stepDown = new Point(stepDown.x + 1, stepDown.y + 1);
                        continue;
                    }
                    // no more possible moves, place sand
                    else {
                        // place sand, count the placements
                        // reassign to sandStart
                        grid.put(stepDown, "Sand");
                        placements++;
                        stepDown = sandStart;
                    }
                }

            }
        }
        System.out.println(placements);

    }

}
