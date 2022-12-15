package Day15;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<Point, String> grid = new HashMap<>();
        for (String line : input) {
            String sensorInput = line.split(":")[0];
            String beaconInput = line.split(":")[1];
            String sensorX = sensorInput.substring(sensorInput.indexOf("x=")+2,sensorInput.indexOf(","));
            String sensorY = sensorInput.substring(sensorInput.indexOf("y=")+2);
            String beaconX = beaconInput.substring(beaconInput.indexOf("x=")+2,beaconInput.indexOf(","));
            String beaconY = beaconInput.substring(beaconInput.indexOf("y=")+2);

            Point sensor = new Point(Integer.parseInt(sensorX),Integer.parseInt(sensorY));
            pointsList.add(sensor);
            Point beacon = new Point(Integer.parseInt(beaconX),Integer.parseInt(beaconY));
            pointsList.add(beacon);
        }
        for (int i = 0; i < pointsList.size(); i+=2) {
            Point sensor = pointsList.get(i);
            Point beacon = pointsList.get(i+1);
            grid.put(sensor,"S");
            grid.put(beacon,"B");
            Integer yDiff = Math.abs(sensor.y-beacon.y);
            Integer xDiff = Math.abs(sensor.x-beacon.x);
            Integer totalDiff = yDiff+xDiff;
//            Integer diffMultiplied = yDiff*xDiff;
            Integer downCounter = 0;

            for (int y = sensor.y - totalDiff; y <= sensor.y + totalDiff; y++) {

                for (int x = sensor.x - totalDiff; x <= sensor.x + totalDiff; x++) {

                    Point actualPoint = new Point(x,y);
                    if (y == sensor.y && x == sensor.x){
                        for (int right = 0; right <= totalDiff; right++) {
//                            if(!grid.containsKey(new Point(x+right,y))){
                                Point addHashRight = new Point(x+right,y);
                                grid.put(addHashRight, "#");
//                            }
                        }
                        for (int left = 0; left <= totalDiff; left++) {
//                            if(!grid.containsKey(new Point(x-left,y))) {
                                Point addHashLeft = new Point(x - left, y);
                                grid.put(addHashLeft, "#");
//                            }

                        }
                    }
//                    else if(x == sensor.x && y != sensor.y){
//                        for (int right = 0; right <= downCounter; right++) {
//                            if(!grid.containsKey(new Point(x+right,y))) {
//                                Point addHashRight = new Point(x + right, y);
//                                grid.put(addHashRight, "#");
//                            }
//                        }
//                        for (int left = 0; left <= downCounter; left++) {
//                            if(!grid.containsKey(new Point(x-left,y))) {
//                                Point addHashLeft = new Point(x - left, y);
//                                grid.put(addHashLeft, "#");
//                            }
//                        }
//                    }else{
//                        if(!grid.containsKey(actualPoint)) {
//                            grid.put(actualPoint, ".");
//                        }
//                    }
                    else{
                        if(x == sensor.x){
                            for (int right = 0; right <= downCounter; right++) {
//                                if(!grid.containsKey(new Point(x+right,y))) {
                                    Point addHashRight = new Point(x + right, y);
                                    grid.put(addHashRight, "#");
//                                }
                            }
                            for (int left = 0; left <= downCounter; left++) {
//                                if(!grid.containsKey(new Point(x-left,y))) {
                                    Point addHashLeft = new Point(x - left, y);
                                    grid.put(addHashLeft, "#");
//                                }
                            }
                        }
//                        else{
//                            if(!grid.containsKey(actualPoint)) {
//                                grid.put(actualPoint, ".");
//                            }
//                        }
                    }

                }
                if(y <= sensor.y)
                    downCounter++;
                if(y >= sensor.y)
                    downCounter--;
            }
            for (int y = sensor.y - totalDiff; y <= sensor.y + totalDiff; y++) {

                for (int x = sensor.x - totalDiff; x <= sensor.x + totalDiff; x++) {
                    Point actualPoint = new Point(x,y);
                    if(!grid.containsKey(actualPoint)) {
                        grid.put(actualPoint, ".");
                    }
                }
                }
//            System.out.println(downCounter);
        }

Integer counter = 0;
        for (var x:grid.entrySet()) {
            if (x.getKey().y == 2000000)
                if(x.getValue() == "#"){
                    counter++;
                }
                System.out.println(counter);
        }
    }


}
