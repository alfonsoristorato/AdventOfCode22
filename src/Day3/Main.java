package Day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Path filePath = Path.of("src/Day3/Day3Input.txt");
        String[] inputArray = new String[0];
        try {
            inputArray = Files.readString(filePath).replaceAll("\\s+\n+", ",").split(",");
        } catch (IOException e) {
            System.out.println(e.getCause());
        }
        // PART1
        List<Character> dupes = new ArrayList<>();
        List<Integer> dupesIntEquivalent = new ArrayList<>();

        for (String rucksack : inputArray) {
            String compartment1String = rucksack.substring(0, rucksack.length() / 2);
            String compartment2String = rucksack.substring(rucksack.length() / 2);
            Set<Character> compartment1Set = new HashSet<>();

            for (int i = 0; i < compartment1String.length(); i++) {
                compartment1Set.add(compartment1String.charAt(i));
            }
            char[] compartment2CharArray = compartment2String.toCharArray();
            Arrays.sort(compartment2CharArray);
            for (Character compartment1Char : compartment1Set) {
                if (Arrays.binarySearch(compartment2CharArray, compartment1Char) >= 0) {
                    dupes.add(compartment1Char);
                }
            }

        }
        for (Character character : dupes) {
            if (Character.isLowerCase(character)) {
                dupesIntEquivalent.add((int) character - (int) 'a' + 1);
            } else {
                dupesIntEquivalent.add(((int) character - (int) 'A' + 1) + 26);
            }
        }
        System.out.println(dupesIntEquivalent.stream().mapToInt(Integer::intValue).sum());

        // PART2
        dupesIntEquivalent.clear();
        List<Character> badge = new ArrayList<>();
        for (int i = 0; i < inputArray.length; i += 3) {
            Set<Character> rucksack1Set = new HashSet<>();
            String rucksack2 = inputArray[i + 1];
            String rucksack3 = inputArray[i + 2];
            char[] rucksack2CharArray = rucksack2.toCharArray();
            char[] rucksack3CharArray = rucksack3.toCharArray();
            Arrays.sort(rucksack2CharArray);
            Arrays.sort(rucksack3CharArray);
            for (int x = 0; x < inputArray[i].length(); x++) {
                rucksack1Set.add(inputArray[i].charAt(x));
            }
            for (Character rucksack1Char : rucksack1Set) {

                if (Arrays.binarySearch(rucksack2CharArray, rucksack1Char) >= 0
                        && Arrays.binarySearch(rucksack3CharArray, rucksack1Char) >= 0) {
                    badge.add(rucksack1Char);
                    break;
                }
            }

        }
        for (Character character : badge) {
            if (Character.isLowerCase(character)) {
                dupesIntEquivalent.add((int) character - (int) 'a' + 1);
            } else {
                dupesIntEquivalent.add(((int) character - (int) 'A' + 1) + 26);
            }
        }
        System.out.println(dupesIntEquivalent.stream().mapToInt(Integer::intValue).sum());

    }

}
