package racingcar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RacingGame {

    private List<String> carNames;
    private int totalRounds;

    public void init(List<String> carNames, int totalRounds) {
        this.carNames = carNames;
        this.totalRounds = totalRounds;
    }

    public List<RoundHistory> race() {
        List<RacingCar> racingCars = createRacingCarsFrom(carNames);
        List<RoundHistory> histories = new ArrayList<>();
        for (int round = 1; round <= Math.max(totalRounds, 1); round++) {
            if (round <= totalRounds) takeOneRound(racingCars);
            addHistory(histories, racingCars);
        }
        return histories;
    }

    List<RacingCar> createRacingCarsFrom(List<String> names) {
        validateNotDuplicateNames(names);
        return names.stream()
                .map(RacingCar::new)
                .toList();
    }

    void validateNotDuplicateNames(List<String> names) {
        Set<String> seen = new HashSet<>();
        for (String name : names) {
            if (!seen.add(name)) {
                throw new IllegalArgumentException("중복된 이름이 있습니다. : " + name);
            }
        }
    }

    void takeOneRound(List<RacingCar> cars) {
        cars.forEach(RacingCar::moveRandomly);
    }

    RoundHistory addHistory(List<RoundHistory> histories, List<RacingCar> racingCars) {
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
}
