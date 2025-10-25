package racingcar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RacingGame {

    private List<RacingCar> racingCars;
    private int totalRounds;

    public void init(SetupData setupData) {
        validateNotDuplicateNames(setupData.carNames());
        this.racingCars = createRacingCarsFrom(setupData.carNames());
        validateNotNegative(setupData.totalRounds());
        this.totalRounds = setupData.totalRounds();
    }

    public List<RoundHistory> race() {
        List<RoundHistory> histories = new ArrayList<>();
        for (int round = 1; round <= Math.max(totalRounds, 1); round++) {
            if (round <= totalRounds) takeOneRound(racingCars);
            addHistory(histories, racingCars);
        }
        return histories;
    }

    List<RacingCar> createRacingCarsFrom(List<String> names) {
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

    void validateNotNegative(int result) {
        if (result < 0 ) {
            throw new IllegalArgumentException("시도 횟수는 음수이면 안됩니다. : " + result);
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
