package Day15;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Path filePath = Path.of("src/Day15/Day15Input.txt");
        List<String> input = new ArrayList<>();

        try {
            input = Files.readAllLines(filePath);
        } catch (IOException e) {
            System.out.println(e);
        }
        List<Point> pointsList = new ArrayList<>();
        Set<Point> grid2 = new HashSet<>();
        Map<Point, String> grid = new HashMap<>();
        for (String line : input) {
            String sensorInput = line.split(":")[0];
            String beaconInput = line.split(":")[1];
            String sensorX = sensorInput.substring(sensorInput.indexOf("x=") + 2, sensorInput.indexOf(","));
            String sensorY = sensorInput.substring(sensorInput.indexOf("y=") + 2);
            String beaconX = beaconInput.substring(beaconInput.indexOf("x=") + 2, beaconInput.indexOf(","));
            String beaconY = beaconInput.substring(beaconInput.indexOf("y=") + 2);

            Point sensor = new Point(Integer.parseInt(sensorX), Integer.parseInt(sensorY));
            pointsList.add(sensor);
            Point beacon = new Point(Integer.parseInt(beaconX), Integer.parseInt(beaconY));
            pointsList.add(beacon);
        }
        for (int i = 0; i < pointsList.size(); i += 2) {
            Point sensor = pointsList.get(i);
            Point beacon = pointsList.get(i + 1);
            grid.put(sensor, "S");
            grid.put(beacon, "B");
            Integer yDiff = Math.abs(sensor.y - beacon.y);
            Integer xDiff = Math.abs(sensor.x - beacon.x);
            Integer totalDiff = yDiff + xDiff; // apparently this is called manatthan distance
            Integer goingDown = 0;
            for (int y = sensor.y; y < sensor.y + totalDiff; y++) {
                for (int x = sensor.x; x <= sensor.x + totalDiff - goingDown; x++) {
                    if (!grid.containsKey(new Point(x, y))) {
                        Point pointRight = new Point(x, y);
                        grid2.add(pointRight);
                    }

                    if (!grid.containsKey(new Point(x - totalDiff + goingDown, y))) {
                        Point pointLeft = new Point(x - totalDiff + goingDown, y);
                        grid2.add(pointLeft);
                    }

                    if (y != sensor.y) {
                        if (!grid.containsKey(new Point(x, y - goingDown - goingDown))) {
                            Point pointUpRight = new Point(x, y - goingDown - goingDown);
                            grid2.add(pointUpRight);
                        }

                        if (!grid.containsKey(new Point(x - totalDiff + goingDown, y - goingDown - goingDown))) {
                            Point pointUpLeft = new Point(x - totalDiff + goingDown, y - goingDown - goingDown);
                            grid2.add(pointUpLeft);
                        }
                    }
                }
                goingDown++;

            }

        }

        Integer counter = 0;
        for (var x : grid2) {
            if (x.y == 10) {
                // 2000000
                // 1000000
                counter++;
            }
        }
        System.out.println(counter);
    }

}
