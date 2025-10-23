package racingcar;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        List<String> carNames = getCarNameList();
        int totalRounds = getNumberOfTries();
        List<RacingCar> racingCars = createRacingCarsFrom(carNames);

        List<RoundHistory> histories = new ArrayList<>();
        System.out.println("실행 결과");
        for (int round = 1; round <= totalRounds; round++) {
            takeOneRound(racingCars);
            RoundHistory thisRoundHistory = addHistory(histories, racingCars);
            printRoundHistory(thisRoundHistory);
        }
    }

    public static RoundHistory addHistory(List<RoundHistory> histories, List<RacingCar> racingCars) {
        //racingCar로 carInfo리스트 생성
        List<CarInfo> carInfoList = new ArrayList<>();
        for (RacingCar racingCar: racingCars) {
            CarInfo carInfo = new CarInfo(racingCar.getName(), racingCar.getCurrentPosition());
            carInfoList.add(carInfo);
        }

        //carInfo리스트로 RoundHistory 생성해 histories에 추가.
        RoundHistory thisRoudnHistory = new RoundHistory(carInfoList);
        histories.add(thisRoudnHistory);

        return thisRoudnHistory;
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
        return new ArrayList<>(Arrays.asList(input.split(",")));
    }

    public static int getNumberOfTries() {
        System.out.println("시도할 횟수는 몇 회인가요?");
        String input = Console.readLine();
        return Integer.parseInt(input);
    }

    public static List<RacingCar> createRacingCarsFrom(List<String> names) {
        return names.stream()
                .map(RacingCar::new)
                .toList();
    }

    public static void takeOneRound(List<RacingCar> cars) {
        cars.forEach(RacingCar::moveRandomly);
    }
}
