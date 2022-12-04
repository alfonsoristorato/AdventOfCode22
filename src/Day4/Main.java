package Day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Path filePath = Path.of("src/Day4/Day4Input.txt");
        String[] inputArray = new String[0];

        try {
            inputArray = Files.readString(filePath).replaceAll("\\s+\n+", ";").split(";");
        } catch (IOException e) {
            System.out.println(e.getCause());
        }

        Integer sameRange = 0;
        Integer overlappingRange = 0;
        for (String pair : inputArray) {
            String elf1Range = pair.split(",")[0];
            String elf2Range = pair.split(",")[1];
            Supplier<IntStream> elf1RangeArray = () -> IntStream.range(Integer.parseInt(elf1Range.split("-")[0]),
                    Integer.parseInt(elf1Range.split("-")[1]) + 1);
            Supplier<IntStream> elf2RangeArray = () -> IntStream.range(Integer.parseInt(elf2Range.split("-")[0]),
                    Integer.parseInt(elf2Range.split("-")[1]) + 1);
            // Part1
            if (intStreamContainsAnother(elf1RangeArray, elf2RangeArray, "allMatch")) {
                sameRange++;
            }
            // Part2
            if (intStreamContainsAnother(elf1RangeArray, elf2RangeArray, "notAllMatch")) {
                overlappingRange++;
            }
        }
        System.out.println(sameRange);
        System.out.println(overlappingRange);

    }

    public static boolean intStreamContainsAnother(Supplier<IntStream> streamOfX, Supplier<IntStream> streamOfY,
            String typeOfCheck) {
        Set<Integer> setOfX = streamOfX.get().boxed().collect(Collectors.toSet());
        Set<Integer> setOfY = streamOfY.get().boxed().collect(Collectors.toSet());
        if (typeOfCheck.equals("allMatch")) {
            return streamOfY.get().allMatch(setOfX::contains) || streamOfX.get().allMatch(setOfY::contains);
        } else {
            return streamOfY.get().anyMatch(setOfX::contains) || streamOfX.get().anyMatch(setOfY::contains);
        }

    }

}
