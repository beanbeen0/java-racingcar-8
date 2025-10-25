package racingcar.io;

import java.util.List;
import racingcar.dto.CarInfo;
import racingcar.dto.RoundHistory;

public class OutputHandler {

    public static void printGameResult(List<RoundHistory> histories, List<String> winnersNames) {
        System.out.println("\n실행 결과");
        printAllHistory(histories);
        printWinners(winnersNames);
    }

    static void printAllHistory(List<RoundHistory> histories) {
        for (RoundHistory history : histories) {
            printRoundHistory(history);
        }
    }

    static void printRoundHistory(RoundHistory thisRoundHistory) {
        for (CarInfo carInfo : thisRoundHistory.getInfoList()) {
            printCarInfo(carInfo);
        }
        System.out.println();
    }

    static void printCarInfo(CarInfo carInfo) {
        System.out.println(carInfo.name() + " : " + "-".repeat(carInfo.progress()));
    }

    static void printWinners(List<String> winnersNames) {
        String resultMessage = "최종 우승자 : " + String.join(", ", winnersNames);
        System.out.println(resultMessage);
    }
}
