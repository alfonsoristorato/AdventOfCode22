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

    }

}
