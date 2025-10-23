package racingcar;

import camp.nextstep.edu.missionutils.test.NsTest;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.List;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static racingcar.Application.*;

class ApplicationTest extends NsTest {
    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;

    @Test
    void 기능_테스트() {
        assertRandomNumberInRangeTest(
            () -> {
                run("pobi,woni", "1");
                assertThat(output()).contains("pobi : -", "woni : ", "최종 우승자 : pobi");
            },
            MOVING_FORWARD, STOP
        );
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("pobi,javaji", "1"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 쉼표로_구분된_이름을_입력하면_개별_이름_리스트로_변환한다() {
        String str = "pobi,woni";
        assertSimpleTest(
                () -> {
                    System.setIn(new ByteArrayInputStream(str.getBytes()));
                    assertThat(getCarNameList()).containsExactly("pobi", "woni");
                }
        );
    }

    @Test
    void 시도할_횟수를_입력하면_정수로_반환한다() {
        String str = "5";
        assertSimpleTest(
                () -> {
                    System.setIn(new ByteArrayInputStream(str.getBytes()));
                    assertThat(getNumberOfTries()).isEqualTo(5);
                }
        );
    }

    @Test
    void 이름_리스트를_자동차_객체_리스트로_변환한다() {
        List<String> names = List.of("pobi", "woni");
        List<RacingCar> cars = Application.createRacingCarsFrom(names);

        assertThat(cars).hasSize(2)
                .extracting(RacingCar::getName)
                .containsExactly("pobi", "woni");
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
    void 한_라운드_전진_테스트() {
        RacingCar car1 = new RacingCar("car1");
        RacingCar car2 = new RacingCar("car2");
        RacingCar car3 = new RacingCar("car3");
        List<RacingCar> cars = List.of(car1, car2, car3);

        List<Integer> beforeCarsPositions = cars.stream().map(RacingCar::getCurrentPosition).toList();
        assertThat(beforeCarsPositions).containsExactly(0, 0, 0);

        assertRandomNumberInRangeTest(
                () -> {
                    takeOneRound(cars);
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

        List<Integer> beforeCarsPositions = cars.stream().map(RacingCar::getCurrentPosition).toList();
        assertThat(beforeCarsPositions).containsExactly(0, 0, 0);

        assertRandomNumberInRangeTest(
                () -> {
                    takeOneRound(cars);
                },
                0, 0, 9
        );

        List<Integer> AfterCarsPositions = cars.stream().map(RacingCar::getCurrentPosition).toList();
        assertThat(AfterCarsPositions).containsExactly(0, 0, 1);
    }

    @Test
    void 한_줄_출력_결과_확인() {
        //given
        CarInfo info = new CarInfo("pobi", 4);

        //when
        printCarInfo(info);

        //then
        assertThat(output()).isEqualTo("pobi : ----");
    }

    @Test
    void 한_히스토리_출력_결과_확인() {
        //given
        CarInfo carInfo1 = new CarInfo("pobi", 3);
        CarInfo carInfo2 = new CarInfo("woni", 2);
        List<CarInfo> carInfoList = new ArrayList<>();
        carInfoList.add(carInfo1);
        carInfoList.add(carInfo2);
        RoundHistory roundHistory = new RoundHistory(carInfoList);

        //when
        printRoundHistory(roundHistory);

        //then
        assertThat(output()).isEqualTo("pobi : ---\nwoni : --");
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
