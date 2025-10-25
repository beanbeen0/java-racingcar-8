package racingcar;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Application {
    public static void main(String[] args) {
        // 경주 정보 입력 받기
        List<String> carNames = getCarNameList();
        int totalRounds = getNumberOfTries();

        RacingGame game = new RacingGame();
        game.init(carNames, totalRounds);
        List<RoundHistory> histories = game.race();

        // 결과 출력
        printAllHistory(histories);
        List<String> winnersNames = findWinnersNames(histories.getLast().infoList);
        printWinners(winnersNames);
    }

    public static void printWinners(List<String> winnersNames) {
        String resultMessage = "최종 우승자 : " + String.join(", ", winnersNames);
        System.out.println(resultMessage);
    }

    public static List<String> findWinnersNames(List<CarInfo> carInfoList) {
        // 최댓값 얻기
        int max = 0;
        for (CarInfo carInfo : carInfoList) {
            if (carInfo.progress() > max) {
                max = carInfo.progress();
            }
        }

        //최댓값으로 우승자 이름 추출하기
        List<String> winnersName = new ArrayList<>();
        for (CarInfo carInfo : carInfoList) {
            if (carInfo.progress() == max) {
                winnersName.add(carInfo.name());
            }
        }

        return winnersName;
    }

    private static void printAllHistory(List<RoundHistory> histories) {
        System.out.println("\n실행 결과");
        for (RoundHistory history : histories) {
            printRoundHistory(history);
        }
    }

    public static void printRoundHistory(RoundHistory thisRoundHistory) {
        for (CarInfo carInfo : thisRoundHistory.infoList) {
            printCarInfo(carInfo);
        }
        System.out.println();
    }

    public static void printCarInfo(CarInfo carInfo) {
        System.out.println(carInfo.name() + " : " + "-".repeat(carInfo.progress()));
    }

    public static List<String> getCarNameList() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String input = Console.readLine();
        return parseCarNames(input);
    }

    public static ArrayList<String> parseCarNames(String input) {
        return new ArrayList<>(Arrays.asList(input.split(",")));
    }

    public static int getNumberOfTries() {
        System.out.println("시도할 횟수는 몇 회인가요?");
        String input = Console.readLine().trim();
        return parseTries(input);
    }

    public static int parseTries(String input) {
        int tries;
        try {
            tries = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("시도 횟수는 정수여야 합니다.", e);
        }
        validateNotNegative(tries);
        return tries;
    }

    public static void validateNotNegative(int result) {
        if (result < 0 ) {
            throw new IllegalArgumentException("시도 횟수는 음수이면 안됩니다. : " + result);
        }
    }
}
