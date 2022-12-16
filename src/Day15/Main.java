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
    public static void main(String[] args) throws Exception {
        Path filePath = Path.of("src/Day15/Day15Input.txt");
        List<String> input = new ArrayList<>();

        try {
            input = Files.readAllLines(filePath);
        } catch (IOException e) {
            System.out.println(e);
        }
        List<Point> pointsList = new ArrayList<>();
        Set<Point> invalidPoints = new HashSet<>();
        Map<Point, String> grid = new HashMap<>();
        Integer y = 2000000;
        for (String line : input) {
            Point[] sensorAndBeacon = buildPoints(line);

            Point sensor = sensorAndBeacon[0];
            pointsList.add(sensor);
            Point beacon = sensorAndBeacon[1];
            pointsList.add(beacon);
            grid.put(sensor, "S");
            grid.put(beacon, "B");
            invalidPoints.remove(sensor);
            invalidPoints.remove(beacon);


            Integer yDiff = Math.abs(sensor.y - beacon.y);
            Integer xDiff = Math.abs(sensor.x - beacon.x);
            Integer totalDiff = yDiff + xDiff;
            Integer remaining;

            if (sensor.y > y) {
                remaining = totalDiff - (sensor.y - y);
            } else {
                remaining = totalDiff - (y - sensor.y);
            }
            if (remaining < 0) {
                continue;
            }

            for (int j = 0; j <= remaining; j++) {
                if (!grid.containsKey(new Point(sensor.x + j, y))) {
                    Point pointRight = new Point(sensor.x + j, y);
                    invalidPoints.add(pointRight);
                }

                if (!grid.containsKey(new Point(sensor.x - j, y))) {
                    Point pointLeft = new Point(sensor.x - j, y);
                    invalidPoints.add(pointLeft);
                }
            }
        }
        System.out.println("Part 1: " + invalidPoints.size());

        int max = 4000000;
        for (y = 0; y < max; y++) {

            int x = 0;

            while (x <= max) {

                int newX = x;
                for (String line : input) {
                    Point[] sensorAndBeacon = buildPoints(line);
                    newX = moveToEndOfLine(sensorAndBeacon[0], sensorAndBeacon[1], x, y);
                    if (x != newX)
                        break;
                }
                if (newX == x) {
                    long result = ((long)x * max) + y;
                    System.out.println("Part 2: " + result);
                    y = max + 1;
                    break;
                }
                x = newX;
            }
        }
    }

    static Point[] buildPoints(String line) {
        String sensorInput = line.split(":")[0];
        String beaconInput = line.split(":")[1];
        String sensorX = sensorInput.substring(sensorInput.indexOf("x=") + 2, sensorInput.indexOf(","));
        String sensorY = sensorInput.substring(sensorInput.indexOf("y=") + 2);
        String beaconX = beaconInput.substring(beaconInput.indexOf("x=") + 2, beaconInput.indexOf(","));
        String beaconY = beaconInput.substring(beaconInput.indexOf("y=") + 2);
        Point sensor = new Point(Integer.parseInt(sensorX), Integer.parseInt(sensorY));
        Point beacon = new Point(Integer.parseInt(beaconX), Integer.parseInt(beaconY));
        return new Point[]{sensor, beacon};
    }

    static int moveToEndOfLine(Point sensor, Point beacon, int x, int y) {
        Integer yDiff = Math.abs(sensor.y - beacon.y);
        Integer xDiff = Math.abs(sensor.x - beacon.x);
        Integer totalDiff = yDiff + xDiff;
        int remaining;
        if (sensor.y > y) {
            remaining = totalDiff - (sensor.y - y);
        } else {
            remaining = totalDiff - (y - sensor.y);
        }
        if (x >= sensor.x - remaining && x <= sensor.x + remaining) {
            return sensor.x + remaining + 1;
        }
        return x;

    }

}
