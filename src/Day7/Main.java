package Day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Path filePath = Path.of("src/Day7/Day7Input.txt");
        String[] inputArray = new String[0];

        try {
            inputArray = Files.readString(filePath).replaceAll("\n+", ",").split(",");
        } catch (IOException e) {
            System.out.println(e.getCause());
        }


        DirectoryEntry root = new DirectoryEntry("/",null);
        List<DirectoryEntry> allDirs = new ArrayList<>();
        // PART 1
        solver1(inputArray,root,allDirs);
        long count1 = 0;
        for (DirectoryEntry dirInAllDirs : allDirs){
            if(dirInAllDirs.getSize() <= 100000){
                count1 += dirInAllDirs.getSize();
            }
        }
        System.out.println(count1);

        // PART 2
        long totalSpace = 70_000_000;
        long spaceNeeded = 30_000_000;
        long spaceUsed = root.getSize();
        long spaceToDelete = spaceNeeded - (totalSpace - spaceUsed);

        List<DirectoryEntry> possibleDirsToDelete = new ArrayList<>();
        for (DirectoryEntry dirInAllDirs : allDirs){
            if(dirInAllDirs.getSize() >= spaceToDelete){
                possibleDirsToDelete.add(dirInAllDirs);
            }
        }
        possibleDirsToDelete.sort((a,b)-> (int) (a.getSize()-b.getSize()));
        System.out.println(possibleDirsToDelete.get(0).getSize());



    }
    static void solver1 (String[] inputArray, DirectoryEntry currentFolder, List<DirectoryEntry> allDirs){
        for (String line : inputArray) {
            if (!line.startsWith("$")) {
                if (line.startsWith("dir")) {
                    DirectoryEntry folder = new DirectoryEntry(line.substring(4),currentFolder);
                    currentFolder.addFile(folder);
                    allDirs.add(folder);
                } else {
                    currentFolder.addFile(new FileEntry(line.split(" ")[1],Long.parseLong(line.split(" ")[0])));
                }
            }else{
                if (line.startsWith("$ cd")) {
                    if (line.equals("$ cd ..")) {
                        currentFolder = currentFolder.getParent();
                    } else if(line.equals("$ cd /")) {

                    }else{
                        currentFolder = currentFolder.getDirectory(line.substring(5));
                    }
                }
            }
        }
    }
}
