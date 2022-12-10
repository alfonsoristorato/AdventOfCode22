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
        String sprite = "###.....................................";
        String[] crt = new String[6];
        for (int i = 0; i < crt.length; i++) {
            crt[i] = ".".repeat(40);
        }
        Integer cyclesCounter = 0;
        // check crt
        checkCRT(crt, sprite, cyclesCounter);
        cyclesCounter = 1;
        Object[] cyclesCounterAndSprite = new Object[] { cyclesCounter, sprite };

        for (String line : lines) {

            String command = line.split(" ")[0];
            Integer value = command.equals("noop") ? 0 : Integer.parseInt(line.split(" ")[1]);
            String spriteChanged = ((String) cyclesCounterAndSprite[1]);
            Integer cyclesCounterChanged = ((Integer) cyclesCounterAndSprite[0]);
            cyclesCounterAndSprite = runCycle(command, value, cyclesMap,
                    cyclesCounterChanged, crt, spriteChanged);
            // for (String x : crt) {
            // System.out.println(x);
            // }
            // System.out.println(cyclesCounterAndSprite[0]);
        }
        System.out.println((cyclesMap.get(19) * 20) + (cyclesMap.get(59) * 60)
                + (cyclesMap.get(99) * 100) + (cyclesMap.get(139) * 140) + (cyclesMap.get(179) * 180)
                + (cyclesMap.get(219) * 220));
        for (String x : crt) {
            System.out.println(x);
        }
    }

    static Object[] runCycle(String command, Integer value, Map<Integer, Integer> cyclesMap, Integer cyclesCounter,
            String[] crt, String sprite) {
        switch (command) {
            case "addx":
                cyclesMap.put(cyclesCounter, cyclesMap.get(cyclesCounter - 1));
                checkCRT(crt, sprite, cyclesCounter);
                cyclesCounter++;
                // check crt

                cyclesMap.put(cyclesCounter, cyclesMap.get(cyclesCounter - 1) + value);
                // move sprite
                sprite = moveSprite(sprite, value);
                System.out.println(cyclesCounter);
                System.out.println(sprite);
                // check crt
                checkCRT(crt, sprite, cyclesCounter);
                cyclesCounter++;
                break;

            default:
                cyclesMap.put(cyclesCounter, cyclesMap.get(cyclesCounter - 1));
                // check crt
                checkCRT(crt, sprite, cyclesCounter);
                cyclesCounter++;

                break;
        }
        return new Object[] { cyclesCounter, sprite };
    }

    static void checkCRT(String[] crt, String sprite, Integer cyclesCounter) {
        if (cyclesCounter >= 0 && cyclesCounter < 40) {
            if (sprite.charAt(cyclesCounter) == '#') {
                StringBuilder crtBuilder = new StringBuilder(crt[0]);
                crtBuilder.setCharAt(cyclesCounter, '#');
                crt[0] = crtBuilder.toString();
            }
        } else if (cyclesCounter >= 40 && cyclesCounter < 80) {
            if (sprite.charAt(cyclesCounter - 40) == '#') {
                StringBuilder crtBuilder = new StringBuilder(crt[1]);
                crtBuilder.setCharAt(cyclesCounter - 40, '#');
                crt[1] = crtBuilder.toString();
            }
        } else if (cyclesCounter >= 80 && cyclesCounter < 120) {
            if (sprite.charAt(cyclesCounter - 80) == '#') {
                StringBuilder crtBuilder = new StringBuilder(crt[2]);
                crtBuilder.setCharAt(cyclesCounter - 80, '#');
                crt[2] = crtBuilder.toString();
            }
        } else if (cyclesCounter >= 120 && cyclesCounter < 160) {
            if (sprite.charAt(cyclesCounter - 120) == '#') {
                StringBuilder crtBuilder = new StringBuilder(crt[3]);
                crtBuilder.setCharAt(cyclesCounter - 120, '#');
                crt[3] = crtBuilder.toString();
            }
        } else if (cyclesCounter >= 160 && cyclesCounter < 200) {
            if (sprite.charAt(cyclesCounter - 160) == '#') {
                StringBuilder crtBuilder = new StringBuilder(crt[4]);
                crtBuilder.setCharAt(cyclesCounter - 160, '#');
                crt[4] = crtBuilder.toString();
            }
        } else if (cyclesCounter >= 200 && cyclesCounter < 240) {
            if (sprite.charAt(cyclesCounter - 200) == '#') {
                StringBuilder crtBuilder = new StringBuilder(crt[5]);
                crtBuilder.setCharAt(cyclesCounter - 200, '#');
                crt[5] = crtBuilder.toString();
            }
        }
    }

    static String moveSprite(String sprite, Integer register) {
        StringBuilder spriteBuilder = new StringBuilder("........................................");
        Integer previousRegister = sprite.indexOf("#") + 1;
        try {
            spriteBuilder.setCharAt(previousRegister + register, '#');
            spriteBuilder.setCharAt(previousRegister + register - 1, '#');
            spriteBuilder.setCharAt(previousRegister + register + 1, '#');
        } catch (Exception e) {
            System.out.println(e);
        }

        return spriteBuilder.toString();

    }

    // ##..##..##..##..##..##..##..##..##..##..
    // ##..##..##..##..##..##..##..##..##..##..

    // ###...###...###...###...###...###...###.
    // ###...###...###...###...###...###...###.

    // ####....####....####....####....####....
    // ####....####....####....####....####....

    // #####.....#####.....#####.....#####.....
    // #####.....#####.....#####.....#####.....

    // ######......######......######......####
    // ######......######......######......####

    // #######.........######........#.#.#.....
    // #######.......#######.......#######.....

    // ....................###.................
    // ....................###.................

    // ...................###..................
    // ...................###..................

    // ...............###......................
    // ................#.......................
    // ...............##.......................
    // ...............###......................

    // ...................###..................
    // ...................###..................
}
