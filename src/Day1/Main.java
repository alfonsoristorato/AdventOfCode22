package Day1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String input = Day1Input.day1Input;
        String[] inputArray = input.replaceAll("\n+\n", ",").split(",");
        List<Integer> list = new ArrayList<>();
        for (String s : inputArray) {
            String[] innerArray = s.replaceAll("\n+", ",").split(",");
            Integer innerTotal = 0;
            for (String element : innerArray) {
                innerTotal = innerTotal + Integer.parseInt(element);
            }
            list.add(innerTotal);
        }
        //part one - Elf with most calories
        int higherTotal = Collections.max(list);
        System.out.println(higherTotal);
        //part 2 - Top 3 Elves with most calories total
        Collections.sort(list);
        System.out.println(list.get(list.size()-1)+list.get(list.size()-2)+list.get(list.size()-3));


    }
}