package Day11;

import java.io.IOException;
import java.math.BigInteger;
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
        List<Monkey> monkeys1 = new ArrayList<>();
        List<Monkey> monkeys2 = new ArrayList<>();
        for (int i = 1; i < input.split("Monkey").length; i++) {
            Monkey monkeyFor1 = buildMonkey(input.split("Monkey")[i].split("\n+"));
            Monkey monkeyFor2 = buildMonkey(input.split("Monkey")[i].split("\n+"));
            monkeys1.add(monkeyFor1);
            monkeys2.add(monkeyFor2);
        }
        doRounds(monkeys1, 20, true);
        doRounds(monkeys2, 10000, false);
        Collections.sort(monkeys1, Comparator.comparingLong(Monkey::getItemsInspected).reversed());
        Collections.sort(monkeys2, Comparator.comparingLong(Monkey::getItemsInspected).reversed());
        System.out.println("Part1: " + monkeys1.get(0).itemsInspected * monkeys1.get(1).itemsInspected);
        System.out.println("Part2: " + monkeys2.get(0).itemsInspected * monkeys2.get(1).itemsInspected);

    }

    static Monkey buildMonkey(String[] monkeyLines) {
        Integer id = Character.getNumericValue(monkeyLines[0].charAt(1));
        List<BigInteger> itemsHeld = new ArrayList<>();
        String[] startingItemsLine = monkeyLines[1].split(":")[1].replaceAll("\\s", "").split(",");
        for (String item : startingItemsLine) {
            itemsHeld.add(new BigInteger(item));
        }
        String operation = monkeyLines[2].split("old ")[1];
        Integer test = Integer.parseInt(monkeyLines[3].split("by ")[1].replaceAll("\\s", ""));
        Integer ifTrue = Integer.parseInt(monkeyLines[4].split("monkey ")[1].replaceAll("\\s", ""));
        Integer ifFalse = Integer.parseInt(monkeyLines[5].split("monkey ")[1].replaceAll("\\s", ""));
        return new Monkey(id, itemsHeld, operation, test, ifTrue, ifFalse);
    }

    static void doRounds(List<Monkey> monkeys, Integer rounds, Boolean divideWorry) {
        while (rounds > 0) {
            for (Monkey monkey : monkeys) {
                for (BigInteger itemHeld : monkey.itemsHeld) {
                    itemHeld = monkey.doOperation(itemHeld, monkey.operationCommand, divideWorry);
                    if (divideWorry) {
                        itemHeld = itemHeld.divide(BigInteger.valueOf(3));
                    }
                    Integer monkeyId = itemHeld.remainder(BigInteger.valueOf(monkey.test)) == BigInteger.valueOf(0)
                            ? monkey.ifTrue
                            : monkey.ifFalse;
                    monkeys.get(monkeyId).itemsHeld.add(itemHeld);
                    monkey.itemsInspected++;
                }
                monkey.itemsHeld.clear();
            }
            rounds--;
        }
    }

}