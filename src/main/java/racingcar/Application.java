package racingcar;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        SetupData setupData = InputHandler.getSetupData();
        RacingGame game = new RacingGame();
        game.init(setupData);
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
}
