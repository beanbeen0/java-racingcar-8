package racingcar.game;

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

    @Test
    void 자동차의_이름이_5자_초과면_예외가_발생한다() {
        String name = "abcdef";

        assertThatThrownBy(() -> new RacingCar(name))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 자동차의_이름이_없으면_예외가_발생한다() {
        String name = "";

        assertThatThrownBy(() -> new RacingCar(name))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 무작위_수가_4미만이면_자동차는_전진하지_않는다() {
        RacingCar car1 = new RacingCar("car1");
        assertThat(car1.getCurrentPosition()).isEqualTo(0);
        assertRandomNumberInRangeTest(
                () -> {
                    car1.moveRandomly();
                },
                0, 1, 2, 3
        );
        assertThat(car1.getCurrentPosition()).isEqualTo(0);
    }

    @Test
    void 무작위_수가_4이상이면_자동차는_전진한다() {
        RacingCar car1 = new RacingCar("car1");
        assertThat(car1.getCurrentPosition()).isEqualTo(0);
        assertRandomNumberInRangeTest(
                () -> {
                    car1.moveRandomly();
                },
                4, 5, 6, 7, 8, 9
        );
        assertThat(car1.getCurrentPosition()).isEqualTo(1);
    }

    @Test
    void 시도_횟수가_음수이면_예외가_발생한다() {
        RacingGame game = new RacingGame();
        //given
        int tries = -1;

        //then
        assertThatThrownBy(() -> game.validateNotNegative(tries))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 공동_우승자들을_조회한다() {
        //given
        RacingCar car1 = new RacingCar("pobi", 3);
        RacingCar car2 = new RacingCar("woni", 2);
        RacingCar car3 = new RacingCar("jun", 3);
        List<RacingCar> racingCars = List.of(car1, car2, car3);

        //when
        List<String> winners = RacingGame.findWinnersNames(racingCars, true);

        //then
        assertThat(winners).containsExactly("pobi", "jun");
    }
}