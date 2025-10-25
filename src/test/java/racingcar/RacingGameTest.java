package racingcar;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class RacingGameTest {

    @Test
    void 이름_리스트를_자동차_객체_리스트로_변환한다() {
        List<String> names = List.of("pobi", "woni");
        RacingGame game = new RacingGame();
        List<RacingCar> cars = game.createRacingCarsFrom(names);

        assertThat(cars).hasSize(2)
                .extracting(RacingCar::getName)
                .containsExactly("pobi", "woni");
    }

    @Test
    void 이름이_중복되면_예외를_반환한다() {
        List<String> names = List.of("a", "b", "b");
        RacingGame game = new RacingGame();

        assertThatThrownBy(() -> game.validateNotDuplicateNames(names))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 한_라운드_전진_테스트() {
        RacingCar car1 = new RacingCar("car1");
        RacingCar car2 = new RacingCar("car2");
        RacingCar car3 = new RacingCar("car3");
        List<RacingCar> cars = List.of(car1, car2, car3);

        RacingGame game = new RacingGame();

        List<Integer> beforeCarsPositions = cars.stream().map(RacingCar::getCurrentPosition).toList();
        assertThat(beforeCarsPositions).containsExactly(0, 0, 0);

        assertRandomNumberInRangeTest(
                () -> {
                    game.takeOneRound(cars);
                },
                5, 4, 3
        );

        List<Integer> AfterCarsPositions = cars.stream().map(RacingCar::getCurrentPosition).toList();
        assertThat(AfterCarsPositions).containsExactly(1, 1, 0);
    }

    @Test
    void 한_라운드_전진_테스트2() {
        RacingCar car1 = new RacingCar("car1");
        RacingCar car2 = new RacingCar("car2");
        RacingCar car3 = new RacingCar("car3");
        List<RacingCar> cars = List.of(car1, car2, car3);

        RacingGame game = new RacingGame();

        List<Integer> beforeCarsPositions = cars.stream().map(RacingCar::getCurrentPosition).toList();
        assertThat(beforeCarsPositions).containsExactly(0, 0, 0);

        assertRandomNumberInRangeTest(
                () -> {
                    game.takeOneRound(cars);
                },
                0, 0, 9
        );

        List<Integer> AfterCarsPositions = cars.stream().map(RacingCar::getCurrentPosition).toList();
        assertThat(AfterCarsPositions).containsExactly(0, 0, 1);
    }
}