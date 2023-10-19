package baseball.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Computer {
    private List<Integer> computerNumberList;

    public Computer(List<Integer> computerNumberList){
        this.computerNumberList = computerNumberList;
    }

    public List<Integer> compareNumberList(List<Integer> userNumberList){
        int ballCount = 0;
        int strikeCount = 0;
        for(int i = 0; i < userNumberList.size(); i++){
            if(checkStrinkeNumber(computerNumberList.get(i),userNumberList.get(i))){
                strikeCount += 1;
                continue;
            }
            if(checkBallNumber(userNumberList.get(i))){
                ballCount += 1;
            }
        }
        return new ArrayList<>(Arrays.asList(ballCount,strikeCount));
    }

    private static boolean checkStrinkeNumber(int comuputerNumber, int userNumber){
        return comuputerNumber == userNumber;
    }

    private boolean checkBallNumber(int userNumber){
        return this.computerNumberList.contains(userNumber);
    }

}
