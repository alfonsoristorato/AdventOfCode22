package Day16;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        Path filePath = Path.of("src/Day16/Day16Input.txt");
        List<String> input = new ArrayList<>();

        try {
            input = Files.readAllLines(filePath);
        } catch (IOException e) {
            System.out.println(e);
        }
        List<Valve> allValves = new ArrayList<>();
        List<Valve> openValves = new ArrayList<>();
        List<Valve> orderedByMostPressure = new ArrayList<>();
        for (String line : input) {
            Valve valve = buildInput(line);
            allValves.add(valve);
        }
        allValves.forEach(v -> v.setCanReachValves(v.getCanReachValvesString(), allValves));
        allValves.forEach(v -> v.setMinutesNeededToReach(allValves));
        for (Valve valve : allValves) {
            if (valve.getFlowRate() == 0) {
                valve.setValveOpen();
                openValves.add(valve);
            } else {
                orderedByMostPressure.add(valve);
            }
        }
        // List<Valve> orderedByMostPressure = allValves.stream().filter(v ->
        // v.getFlowRate() > 0)
        // .sorted(Comparator.comparing(Valve::getFlowRate).reversed()).toList();
        Integer flowRate = 0;
        Integer minutesLeft = 30;
        Valve currentValve = allValves.stream().filter(v -> v.getName().equals("AA")).findFirst().get();
        while (minutesLeft > 0) {
            if (openValves.size() == allValves.size()) {
                break;
            }
            if (currentValve.getFlowRate() > 0 && !currentValve.getValveOpen()) {
                currentValve.setValveOpen();
                openValves.add(currentValve);
                orderedByMostPressure.remove(currentValve);
                minutesLeft--;
                flowRate += minutesLeft * currentValve.getFlowRate();
            } else {
                int minutesNeededToClosesValve = currentValve.getMinutesNeededToReachMap()
                        .get(currentValve.getMinutesNeededToReachMap().keySet().toArray()[0]);
                Object checker = 0;

                while (checker instanceof Integer) {
                    if (openValves.size() == allValves.size()) {
                        break;
                    }
                    checker = checkWhichValveToMoveTo(currentValve,
                            orderedByMostPressure, minutesLeft, openValves, minutesNeededToClosesValve);
                }
                currentValve = (Valve) checker;

                // int minutesNeededToClosesValve = currentValve.getMinutesNeededToReachMap()
                // .get(currentValve.getMinutesNeededToReachMap().keySet().toArray()[0]);

                // for (Valve valveWithMostPressure : orderedByMostPressure) {
                // if (valveWithMostPressure.equals(currentValve))
                // continue;

                // if (currentValve
                // .getMinutesNeededToReachByValve(valveWithMostPressure) <=
                // minutesNeededToClosesValve
                // && !openValves.contains(valveWithMostPressure)) {
                // minutesLeft = minutesLeft - minutesNeededToClosesValve;
                // currentValve = valveWithMostPressure;

                // break;
                // }
                // }
                // int currentMinutesNeededToClosesValve = minutesNeededToClosesValve;
                // int nextMinutesNeededToClosesValve =
                // currentValve.getMinutesNeededToReachMap().values().stream()
                // .filter(e -> e > currentMinutesNeededToClosesValve).findFirst().get();
                // minutesNeededToClosesValve = nextMinutesNeededToClosesValve;

            }

        }

        System.out.println(flowRate);
    }

    static Object checkWhichValveToMoveTo(Valve currentValve, List<Valve> orderedByMostPressure, Integer minutesLeft,
            List<Valve> openValves, Integer minutesNeededToClosesValve) {

        for (Valve valveWithMostPressure : orderedByMostPressure) {
            if (valveWithMostPressure.equals(currentValve))
                continue;

            if (currentValve
                    .getMinutesNeededToReachByValve(valveWithMostPressure) <= minutesNeededToClosesValve
                    && !openValves.contains(valveWithMostPressure)) {
                minutesLeft = minutesLeft - minutesNeededToClosesValve;
                currentValve = valveWithMostPressure;
                return currentValve;
            }
        }
        int currentMinutesNeededToClosesValve = minutesNeededToClosesValve;
        int nextMinutesNeededToClosesValve = currentValve.getMinutesNeededToReachMap().values().stream()
                .filter(e -> e > currentMinutesNeededToClosesValve).findFirst().get();
        minutesNeededToClosesValve = nextMinutesNeededToClosesValve;
        return minutesNeededToClosesValve;
    }

    static Valve buildInput(String line) {
        String firstPart = line.split(";")[0];
        String secondPart = line.split(";")[1];
        String valveName = firstPart.substring(6, 8);
        Integer flowRate = Integer.parseInt(firstPart.split("=")[1]);
        String canReachValvesString = secondPart.split("valves |valve ")[1].replaceAll(" ", "");
        List<String> canReachValves = new ArrayList<>();
        if (canReachValvesString.length() > 2) {
            canReachValves.addAll(List.of(canReachValvesString.split(",")));
        } else {
            canReachValves.add(canReachValvesString);
        }
        Valve valve = new Valve(valveName, flowRate);
        for (String valveInList : canReachValves) {
            valve.setCanReachValvesString(valveInList);
        }
        return valve;
    }

}
