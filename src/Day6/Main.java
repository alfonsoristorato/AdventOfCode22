package Day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Path filePath = Path.of("src/Day6/Day6Input.txt");
        String input = "";

        try {
            input = Files.readString(filePath);
        } catch (IOException e) {
            System.out.println(e.getCause());
        }
        System.out.println(solver(input,4));
        System.out.println(solver(input,14));

    }
    public static Integer solver(String input, Integer lengthOfMarker) {
        Set<Character> distinctChars = new HashSet<>();
        Integer charPosition = 0;
        for (int i = 0; i < input.length(); i++) {
            Character singChar = input.charAt(i);
            if (!distinctChars.contains(singChar)) {
                distinctChars.add(singChar);
            } else {
                i = i - distinctChars.size();
                distinctChars.clear();
            }

            if (distinctChars.size() == lengthOfMarker) {

                charPosition = i+1;
                break;
            }
        }
        return charPosition;
    }

}

