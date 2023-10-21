package baseball.controller;

import baseball.model.Computer;
import baseball.util.BaseballNumberGenerator;
import baseball.util.BaseballRandomNumberGenerator;
import baseball.view.InputView;
import baseball.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class GameController {
    private final static String NOTHING_STRING = "낫싱";
    private final static String BALL_STRING = "볼";
    private final static String STRIKE_STRING = "스트라이크";
    private final static int COUNT_ZERO_VALUE = 0;
    private final static int LIST_BALL_INDEX = 0;
    private final static int LIST_STRIKE_INDEX = 1;
    private final static Computer computer = new Computer(new BaseballRandomNumberGenerator().generate());
    private final static InputView inputView = new InputView();
    private final static OutputView outputView = new OutputView();
    public void start(){
        outputView.printGameStartMessage();
    }
    private static List<Integer> transferStringToIntegerList(String targetString){
        String[] stringList = targetString.split("");
        List<Integer> result = new ArrayList<>();
        for(String s : stringList){
            result.add(Integer.parseInt(s));
        }
        return result;
    }
    private static String provideStrikeAndBallCount(int ballCount, int strikeCount){
        if(ballCount == COUNT_ZERO_VALUE && strikeCount == COUNT_ZERO_VALUE){
            return NOTHING_STRING;
        } else if (ballCount != COUNT_ZERO_VALUE && strikeCount == COUNT_ZERO_VALUE) {
            return String.valueOf(ballCount) + BALL_STRING;
        } else if (ballCount == COUNT_ZERO_VALUE && strikeCount != COUNT_ZERO_VALUE) {
            return String.valueOf(ballCount) + STRIKE_STRING;
        }
        return String.valueOf(ballCount) + BALL_STRING + " " + String.valueOf(ballCount) + STRIKE_STRING;
    }

    private static boolean gameRetry(){
        outputView.printGameEndMessage();
        outputView.printGameRetryMessage();
        String command = inputView.readRetryCommand();
        if (command.equals("1")){
            return true;
        }
        return false;
    }
}
