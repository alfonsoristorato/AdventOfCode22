package Day13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Path filePath = Path.of("src/Day13/Day13Input.txt");
        List<String> input = new ArrayList<>();

        try {
            input = Files.readAllLines(filePath);
        } catch (IOException e) {
            System.out.println(e);
        }
        Integer pairNumber = 1;
        Integer leftSmaller = 0;
        List<Packet> packets = new ArrayList<>();
        for (int i = 0; i < input.size(); i += 3) {
            Packet left = new Packet(input.get(i));
            packets.add(left);
            Packet right = new Packet(input.get(i + 1));
            packets.add(right);
            leftSmaller += left.compareTo(right) < 0 ? pairNumber : 0;
            pairNumber++;
        }
        System.out.println(leftSmaller);
        packets.add(new Packet("[[2]]"));
        packets.add(new Packet("[[6]]"));
        Collections.sort(packets);
        int index2 = 0;
        int index6 = 0;
        for (int i = 0; i < packets.size(); i++) {
            if (packets.get(i).getContents().equals("[[2]]")) {
                index2 = i + 1;
            }
            if (packets.get(i).getContents().equals("[[6]]")) {
                index6 = i + 1;
            }
        }
        System.out.println(index2 * index6);
    }
}
