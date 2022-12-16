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
        Set<Point> invalidPoints = new HashSet<>();
        Map<Point, String> grid = new HashMap<>();
        Integer y = 2000000;
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
            for (int x = 0; x < 2; x ++) {

                Integer yDiff = Math.abs(sensor.y - beacon.y);
                Integer xDiff = Math.abs(sensor.x - beacon.x);
                Integer yMin = Math.min(sensor.y, beacon.y);
                Integer yMax = Math.max(sensor.y, beacon.y);
                Integer totalDiff = yDiff + xDiff; // apparently this is called manatthan distance
                Integer remaining;

                if(sensor.y > y){
                    remaining = totalDiff - (sensor.y -y);
                }else{
                    remaining = totalDiff - (y- sensor.y);
                }
                if (remaining < 0){
                    continue;
                }

                for (int j = 0; j <= remaining; j++) {
                    if (!grid.containsKey(new Point(sensor.x + j, y))) {
                        Point pointRight = new Point(sensor.x + j, y);
                        invalidPoints.add(pointRight);
                    }

                    if (!grid.containsKey(new Point(sensor.x-j,y))) {
                        Point pointLeft = new Point(sensor.x -j,y);
                        invalidPoints.add(pointLeft);
                    }
                }
            }

        }


        System.out.println(invalidPoints.size());
    }

}
