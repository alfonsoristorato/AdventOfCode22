package Day16;

import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Valve {
    private String name;
    private int flowRate;
    private List<Valve> canReachValves = new ArrayList<>();
    private List<String> canReachValvesString = new ArrayList<>();
    private Boolean valveOpen;
    private Map<Valve, Integer> minutesNeededToReach;

    public Valve(String name, int flowRate) {
        this.name = name;
        this.flowRate = flowRate;
        this.valveOpen = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFlowRate() {
        return flowRate;
    }

    public void setFlowRate(int flowRate) {
        this.flowRate = flowRate;
    }

    public List<Valve> getCanReachValves() {
        return canReachValves;
    }

    public void setCanReachValves(List<String> canReachValvesString, List<Valve> allValves) {
        for (String valveString : canReachValvesString) {
            Valve canReachValve = allValves.stream().filter(e -> e.getName().equals(valveString)).findFirst().get();
            this.canReachValves.add(canReachValve);
        }
    }

    public List<String> getCanReachValvesString() {
        return canReachValvesString;
    }

    public void setCanReachValvesString(String valveString) {
        this.canReachValvesString.add(valveString);
    }

    public Boolean getValveOpen() {
        return valveOpen;
    }

    public void setValveOpen() {
        this.valveOpen = true;
    }

    public Integer getMinutesNeededToReachByValve(Valve toValve) {
        return minutesNeededToReach.get(toValve);
    }

    public Map<Valve, Integer> getMinutesNeededToReachMap() {
        return minutesNeededToReach;
    }

    public void setMinutesNeededToReach(List<Valve> allValves) {
        Map<Valve, Integer> minutesNeededToReach = new HashMap<>();
        Boolean allMinutesFound = false;
        Integer minutesNeeded = 0;
        while (!allMinutesFound) {
            List<Valve> valveNotFound = new ArrayList<>();
            for (Valve valve : allValves) {
                if (valve.getName().equals(this.getName()))
                    continue;
                if (valve.getCanReachValvesString().contains(this.getName())
                        && !minutesNeededToReach.containsKey(valve)) {
                    minutesNeeded = 1;
                    minutesNeededToReach.put(valve, minutesNeeded);
                } else {
                    if (valve.getCanReachValves().stream()
                            .anyMatch(v -> v.getCanReachValvesString().contains(this.getName()))) {
                        minutesNeeded = 2;
                        minutesNeededToReach.put(valve, minutesNeeded);
                    } else {
                        // nesting further than 2 minutes
                        valveNotFound.add(valve);
                    }
                }
            }
            for (Valve valveNotFoundYet : valveNotFound) {
                if (valveNotFoundYet.getCanReachValves().stream()
                        .anyMatch(v -> minutesNeededToReach.containsKey(v))) {
                    int prevMinutes = minutesNeededToReach.get(valveNotFoundYet.getCanReachValves().stream()
                            .filter(v -> minutesNeededToReach.containsKey(v)).findFirst().get());
                    minutesNeeded = prevMinutes + 1;
                    minutesNeededToReach.put(valveNotFoundYet, minutesNeeded);
                }
            }
            if (minutesNeededToReach.size() == allValves.size() - 1) {
                allMinutesFound = true;
                break;
            }
        }
        // below sorts the map by value by creating a collection stream,
        // comparing and reversing, and then creating a new map with the KV orderd by
        // value
        // Map<Valve, Integer> minutesNeededToReach2 =
        // minutesNeededToReach.entrySet().stream()
        // .sorted(Map.Entry.<Valve, Integer>comparingByValue().reversed())
        // .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) ->
        // e1, LinkedHashMap::new));

        // below sorts the map by value by creating a collection stream,
        // comparing without reversing, and then creating a new map with the KV orderd
        // by value
        Map<Valve, Integer> minutesNeededToReach2 = minutesNeededToReach.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        this.minutesNeededToReach = minutesNeededToReach2;

    }
    // #region not in use for now
    // private Integer recursivelyFindValve(Valve valve, Integer minutesNeeded,
    // Map<Valve, Integer> minutesNeededToBeReachedFrom) {
    // Boolean valveFound = false;
    // Valve currValve = valve;
    // Integer innerMinutesNeeded = minutesNeeded;
    // while (!valveFound) {
    // if (currValve.getCanReachValves().stream()
    // .anyMatch(v -> v.getCanReachValvesString().contains(this.getName()))) {
    // innerMinutesNeeded++;
    // valveFound = true;
    // break;
    // } else {
    // if (currValve.getCanReachValves().stream()
    // .anyMatch(v -> minutesNeededToBeReachedFrom.containsKey(v))) {
    // int prevMinutes =
    // minutesNeededToBeReachedFrom.get(currValve.getCanReachValves().stream()
    // .filter(v -> minutesNeededToBeReachedFrom.containsKey(v)).findFirst().get());
    // innerMinutesNeeded = prevMinutes + 1;
    // valveFound = true;
    // break;
    // } else {
    // for (int i = 0; i < currValve.getCanReachValves().size(); i++) {
    // innerMinutesNeeded++;
    // if
    // (currValve.getCanReachValves().get(i).getCanReachValves().get(i).equals(currValve))
    // continue;
    // recursivelyFindValve(currValve.getCanReachValves().get(i),
    // innerMinutesNeeded, minutesNeededToBeReachedFrom);
    // }
    // }

    // }

    // }

    // // List<Valve> x = allValves.stream().filter(v ->
    // // v.getCanReachValvesString().contains(this.getName())).toList();
    // return innerMinutesNeeded;
    // }
    // #endregion

}
