package Day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Character> stack1 = new ArrayList<>(Arrays.asList('M', 'F', 'C', 'W', 'T', 'D', 'L', 'B'));
        List<Character> stack2 = new ArrayList<>(Arrays.asList('L', 'B', 'N'));
        List<Character> stack3 = new ArrayList<>(Arrays.asList('V', 'L', 'T', 'H', 'C', 'J'));
        List<Character> stack4 = new ArrayList<>(Arrays.asList('W', 'J', 'P', 'S'));
        List<Character> stack5 = new ArrayList<>(Arrays.asList('R', 'L', 'T', 'F', 'C', 'S', 'Z'));
        List<Character> stack6 = new ArrayList<>(Arrays.asList('Z', 'N', 'H', 'B', 'G', 'D', 'W'));
        List<Character> stack7 = new ArrayList<>(Arrays.asList('N', 'C', 'G', 'V', 'P', 'S', 'M', 'F'));
        List<Character> stack8 = new ArrayList<>(Arrays.asList('Z', 'C', 'V', 'F', 'J', 'R', 'Q', 'W'));
        List<Character> stack9 = new ArrayList<>(Arrays.asList('H', 'L', 'M', 'P', 'R'));
        List<List<Character>> stacksListPart1 = new ArrayList<>(
                Arrays.asList(stack1, stack2, stack3, stack4, stack5, stack6, stack7, stack8, stack9));
        List<Character> stack1Part2 = new ArrayList<>(Arrays.asList('M', 'F', 'C', 'W', 'T', 'D', 'L', 'B'));
        List<Character> stack2Part2 = new ArrayList<>(Arrays.asList('L', 'B', 'N'));
        List<Character> stack3Part2 = new ArrayList<>(Arrays.asList('V', 'L', 'T', 'H', 'C', 'J'));
        List<Character> stack4Part2 = new ArrayList<>(Arrays.asList('W', 'J', 'P', 'S'));
        List<Character> stack5Part2 = new ArrayList<>(Arrays.asList('R', 'L', 'T', 'F', 'C', 'S', 'Z'));
        List<Character> stack6Part2 = new ArrayList<>(Arrays.asList('Z', 'N', 'H', 'B', 'G', 'D', 'W'));
        List<Character> stack7Part2 = new ArrayList<>(Arrays.asList('N', 'C', 'G', 'V', 'P', 'S', 'M', 'F'));
        List<Character> stack8Part2 = new ArrayList<>(Arrays.asList('Z', 'C', 'V', 'F', 'J', 'R', 'Q', 'W'));
        List<Character> stack9Part2 = new ArrayList<>(Arrays.asList('H', 'L', 'M', 'P', 'R'));
        List<List<Character>> stacksListPart2 = new ArrayList<>(Arrays.asList(stack1Part2, stack2Part2, stack3Part2,
                stack4Part2, stack5Part2, stack6Part2, stack7Part2, stack8Part2, stack9Part2));

        Path filePath = Path.of("src/Day5/Day5Input.txt");
        String input = "";

        try {
            input = Files.readString(filePath).split("\\s+\n+")[1];
        } catch (IOException e) {
            System.out.println(e.getCause());
        }
        String[] movesArray = input.replaceAll("\n+", ",").split(",");
        for (String move : movesArray) {
            String[] digitsString = move.replaceAll("\\D+", ",").split(",");
            Integer itemsToMove = Integer.parseInt(digitsString[1]);
            Integer removeFromList = Integer.parseInt(digitsString[2]);
            Integer addToList = Integer.parseInt(digitsString[3]);
            // Part 1
            for (int i = 0; i < itemsToMove; i++) {
                Character itemToAdd = stacksListPart1.get(removeFromList - 1).get(0);
                stacksListPart1.get(addToList - 1).add(0, itemToAdd);
                stacksListPart1.get(removeFromList - 1).remove(0);
            }
            // Part 2
            stacksListPart2.get(addToList - 1).addAll(0,
                    stacksListPart2.get(removeFromList - 1).subList(0, itemsToMove));
            for (int i = 0; i < itemsToMove; i++) {
                stacksListPart2.get(removeFromList - 1).remove(0);
            }

        }
        // Part1
        for (List<Character> list : stacksListPart1) {
            System.out.println(list.get(0));
        }
        // Part2
        for (List<Character> list : stacksListPart2) {
            System.out.println(list.get(0));
        }

    }
}
