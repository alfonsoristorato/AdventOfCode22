package Day11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        Path filePath = Path.of("src/Day11/Day11Input.txt");
        String input = "";

        try {
            input = Files.readString(filePath);
        } catch (IOException e) {
            System.out.println(e);
        }
        List<Monkey> monkeys = new ArrayList<>();
        for (int i = 1; i < input.split("Monkey").length; i++) {
            Monkey monkey = buildMonkey(input.split("Monkey")[i].split("\n+"));
            monkeys.add(monkey);
        }
        doRounds(monkeys, 20);
        Collections.sort(monkeys, Comparator.comparingInt(Monkey::getItemsInspected).reversed());
        System.out.println("Part1: " + monkeys.get(0).itemsInspected * monkeys.get(1).itemsInspected);

    }

    static Monkey buildMonkey(String[] monkeyLines) {
        Integer id = Character.getNumericValue(monkeyLines[0].charAt(1));
        List<Long> itemsHeld = new ArrayList<>();
        String[] startingItemsLine = monkeyLines[1].split(":")[1].replaceAll("\\s", "").split(",");
        for (String item : startingItemsLine) {
            itemsHeld.add(Long.parseLong(item));
        }
        String operation = monkeyLines[2].split("old ")[1];
        Integer test = Integer.parseInt(monkeyLines[3].split("by ")[1].replaceAll("\\s", ""));
        Integer ifTrue = Integer.parseInt(monkeyLines[4].split("monkey ")[1].replaceAll("\\s", ""));
        Integer ifFalse = Integer.parseInt(monkeyLines[5].split("monkey ")[1].replaceAll("\\s", ""));
        return new Monkey(id, itemsHeld, operation, test, ifTrue, ifFalse);
    }

    static void doRounds(List<Monkey> monkeys, Integer rounds) {
        while (rounds > 0) {
            for (Monkey monkey : monkeys) {
                for (Long itemHeld : monkey.itemsHeld) {
                    itemHeld = monkey.doOperation(itemHeld, monkey.operationCommand);

                    itemHeld = itemHeld / 3;
                    Integer monkeyId = itemHeld % monkey.test == 0 ? monkey.ifTrue : monkey.ifFalse;
                    monkeys.get(monkeyId).itemsHeld.add(itemHeld);
                    monkey.itemsInspected++;
                }
                monkey.itemsHeld.clear();
            }
            rounds--;
        }
    }

}