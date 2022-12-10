package Day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Path filePath = Path.of("src/Day10/Day10Input.txt");
        List<String> lines = new ArrayList<>();

        try {
            for (String line : Files.readAllLines(filePath)) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        Map<Integer, Integer> cyclesMap = new HashMap<>();
        cyclesMap.put(0, 1);
        String[] crt = new String[6];
        for (int i = 0; i < crt.length; i++) {
            crt[i] = " ".repeat(40);
        }
        Integer cyclesCounter = 0;
        Integer register = 1;
        // check crt
        checkCRT(crt, register, cyclesCounter);
        cyclesCounter = 1;
        Object[] cyclesCounterAndSprite = new Object[] { cyclesCounter, register };

        for (String line : lines) {

            String command = line.split(" ")[0];
            Integer value = command.equals("noop") ? 0 : Integer.parseInt(line.split(" ")[1]);
            Integer registerChanged = ((Integer) cyclesCounterAndSprite[1]);
            Integer cyclesCounterChanged = ((Integer) cyclesCounterAndSprite[0]);
            cyclesCounterAndSprite = runCycle(command, value, cyclesMap,
                    cyclesCounterChanged, crt, registerChanged);
        }
        System.out.println((cyclesMap.get(19) * 20) + (cyclesMap.get(59) * 60)
                + (cyclesMap.get(99) * 100) + (cyclesMap.get(139) * 140) + (cyclesMap.get(179) * 180)
                + (cyclesMap.get(219) * 220));
        for (String x : crt) {
            System.out.println(x);
        }
    }

    static Object[] runCycle(String command, Integer value, Map<Integer, Integer> cyclesMap, Integer cyclesCounter,
            String[] crt, Integer register) {
        switch (command) {
            case "addx":
                cyclesMap.put(cyclesCounter, cyclesMap.get(cyclesCounter - 1));
                checkCRT(crt, register, cyclesCounter);
                cyclesCounter++;
                cyclesMap.put(cyclesCounter, cyclesMap.get(cyclesCounter - 1) + value);
                checkCRT(crt, register, cyclesCounter);
                cyclesCounter++;
                register += value;
                break;

            default:
                cyclesMap.put(cyclesCounter, cyclesMap.get(cyclesCounter - 1));
                checkCRT(crt, register, cyclesCounter);
                cyclesCounter++;
                break;
        }
        return new Object[] { cyclesCounter, register };
    }

    static void checkCRT(String[] crt, Integer register, Integer cyclesCounter) {
        var col = (cyclesCounter - 1) % 40;
        var row = (cyclesCounter - 1) / 40;
        if (col >= register - 1 && col <= register + 1) {
            StringBuilder crtBuilder = new StringBuilder(crt[row]);
            crtBuilder.setCharAt(col, '#');
            crt[row] = crtBuilder.toString();
        }
    }
}
