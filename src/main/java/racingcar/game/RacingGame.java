package racingcar.game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import racingcar.dto.RoundHistory;
import racingcar.dto.SetupData;

public class RacingGame {

    private List<RacingCar> racingCars;
    private int totalRounds;
    private boolean finished = false;

    // 생성 및 검증
    public void init(SetupData setupData) {
        validateNotDuplicateNames(setupData.carNames());
        this.racingCars = createRacingCarsFrom(setupData.carNames());
        validateNotNegative(setupData.totalRounds());
        this.totalRounds = setupData.totalRounds();
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

    // 경주 진행
    public List<RoundHistory> race() {
        List<RoundHistory> histories = new ArrayList<>();
        for (int round = 1; round <= Math.max(totalRounds, 1); round++) {
            if (round <= totalRounds) takeOneRound(racingCars);
            addHistory(histories, racingCars);
        }
        this.finished = true;
        return histories;
    }

    void takeOneRound(List<RacingCar> cars) {
        cars.forEach(RacingCar::moveRandomly);
    }

    void addHistory(List<RoundHistory> histories, List<RacingCar> racingCars) {
        histories.add(RoundHistory.create(racingCars));
    }

    // 우승자 조회
    public List<String> getWinnersNames() {
        return findWinnersNames(this.racingCars, this.finished);
    }

    static List<String> findWinnersNames(List<RacingCar> racingCars, boolean finished) {
        if (!finished) {
            throw new IllegalArgumentException("아직 경기가 끝나지 않았습니다.");
        }
        int max = getMaxProgress(racingCars);
        return filterNamesWithPosition(racingCars, max);
    }

    private static int getMaxProgress(List<RacingCar> racingCars) {
        return racingCars.stream()
                .mapToInt(RacingCar::getCurrentPosition)
                .max()
                .orElse(0);
    }

    private static List<String> filterNamesWithPosition(List<RacingCar> racingCars, int position) {
        return racingCars.stream()
                .filter(car -> car.getCurrentPosition() == position)
                .map(RacingCar::getName)
                .toList();
    }
}
