package Day8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Path filePath = Path.of("src/Day8/Day8Input.txt");
        List<List<Integer>> lines = new ArrayList<>();

        try {
            for (String line:Files.readAllLines(filePath)) {

                int[] y = Arrays.stream(line.split("")).mapToInt(Integer::parseInt).toArray();
                lines.add(Arrays.stream(y).boxed().collect(Collectors.toList()));

            }

        } catch (IOException e) {
            System.out.println(e);
        }
        Integer visibleTrees = lines.get(0).size()*2 + (lines.size()-2)*2;
        Integer scenicScore = 0;
        List<Integer> top = new ArrayList<>();
        List<Integer> bottom = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        List<Integer> left = new ArrayList<>();
        for (int i = 1; i< lines.size()-1; i++){

            for(int x = 1; x <lines.get(i).size()-1; x++){
                top.clear();
                bottom.clear();
                right.clear();
                left.clear();
                Integer treesInViewTop = i;
                Integer treesInViewBottom = lines.size()-1-i;
                Integer treesInViewRight = lines.get(i).size()-1-x;
                Integer treesInViewLeft = x;
                Integer visibleTreesBeforeIteration = visibleTrees;


                int elemToEvaluate = lines.get(i).get(x);
                for(int topI = 0; topI < i; topI++){
                    top.add(lines.get(topI).get(x));
                }
                for(int bottomI = i+1; bottomI<lines.size(); bottomI++){
                    bottom.add(lines.get(bottomI).get(x));
                }
                for(int leftI = 0; leftI < x; leftI++){
                    left.add(lines.get(i).get(leftI));
                }
                for(int rightI = x+1; rightI < lines.get(i).size(); rightI++){
                    right.add(lines.get(i).get(rightI));
                }

                if(top.stream().noneMatch(e -> e >= elemToEvaluate)){
                        visibleTrees ++;
                }else{
                    Collections.reverse(top);
                    int counter = 0;
                    for (int topN : top){
                        counter++;
                        if (topN >= elemToEvaluate){
                            treesInViewTop = counter;
                            break;
                        }
                    }
                }
                if(bottom.stream().noneMatch(e -> e >= elemToEvaluate)){
                    if(visibleTrees.equals(visibleTreesBeforeIteration)){
                        visibleTrees ++;
                    }
                }else{
                    int counter = 0;
                    for (int bottomN : bottom){
                        counter++;
                        if (bottomN >= elemToEvaluate){
                            treesInViewBottom = counter;
                            break;
                        }
                    }
                }
                if(right.stream().noneMatch(e -> e >= elemToEvaluate)){
                    if(visibleTrees.equals(visibleTreesBeforeIteration)){
                        visibleTrees ++;
                    }
                }else{
                    int counter = 0;
                    for (int rightN : right){
                        counter++;
                        if (rightN >= elemToEvaluate){
                            treesInViewRight = counter;
                            break;
                        }
                    }

                }
                if(left.stream().noneMatch(e -> e >= elemToEvaluate)){
                    if(visibleTrees.equals(visibleTreesBeforeIteration)){
                        visibleTrees ++;
                    }
                }else{
                    Collections.reverse(left);
                    int counter = 0;
                    for (int leftN : left){
                        counter++;
                        if (leftN >= elemToEvaluate){
                            treesInViewLeft = counter;
                            break;
                        }
                    }
                }
                scenicScore = treesInViewTop * treesInViewBottom * treesInViewRight * treesInViewLeft > scenicScore
                        ? treesInViewTop * treesInViewBottom * treesInViewRight * treesInViewLeft
                        : scenicScore;

            }

        }
        System.out.println(visibleTrees);
        System.out.println(scenicScore);
    }
}
