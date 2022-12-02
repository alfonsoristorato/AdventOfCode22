package Day2;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        String[] inputArray = Day2Input.day2Input.replaceAll("\n+", ",").split(",");

        int partOneScore = 0;
        int partTwoScore = 0;
        for (String hand:inputArray) {
            String opponentHand = hand.substring(0,1);
            String yourHand = hand.substring(2);

            partOneScore += opponentHand.equals(combinations.valueOf(yourHand).drawsWith)
                    ? 3 + combinations.valueOf(yourHand).score
                    : opponentHand.equals(combinations.valueOf(yourHand).winsAgainst)
                        ?  6 + combinations.valueOf(yourHand).score
                        :  combinations.valueOf(yourHand).score;

            partTwoScore
                    +=  combinations.valueOf(yourHand).partTwoRoundEndScore
                    +   combinations.valueOf(yourHandPart2(combinations.valueOf(yourHand).partTwoRoundEndScore,opponentHand)).score;
        }
        System.out.println("Part 1 = " + partOneScore + ", Part 2 = " + partTwoScore);

    }
    public enum combinations {
        X("C", 1, "A", "B", 0),
        Y("A", 2, "B", "C", 3),
        Z("B", 3, "C", "A",  6);
        combinations(String winsAgainst, int score, String drawsWith, String losesAgainst,  int partTwoRoundEndScore) {
            this.winsAgainst = winsAgainst;
            this.score = score;
            this.drawsWith = drawsWith;
            this.losesAgainst = losesAgainst;
            this.partTwoRoundEndScore = partTwoRoundEndScore;
        }
        private final String winsAgainst;
        private final String drawsWith;
        private final String losesAgainst;
        private final int score;
        private final int partTwoRoundEndScore;

    }

    public static String yourHandPart2(int partTwoRoundEndScore, String opponentHand){
        return partTwoRoundEndScore == 3
                ? Arrays.stream(combinations.values()).filter(el -> el.drawsWith.equalsIgnoreCase(opponentHand)).findFirst().get().name()
                : partTwoRoundEndScore == 0
                    ? Arrays.stream(combinations.values()).filter(el -> el.losesAgainst.equalsIgnoreCase(opponentHand)).findFirst().get().name()
                    : Arrays.stream(combinations.values()).filter(el -> el.winsAgainst.equalsIgnoreCase(opponentHand)).findFirst().get().name();
    }
}