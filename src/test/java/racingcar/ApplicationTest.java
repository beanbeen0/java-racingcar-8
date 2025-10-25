package racingcar;

import camp.nextstep.edu.missionutils.test.NsTest;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

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
        //given
        String input = "pobi,woni";

        //when
        List<String> names = parseCarNames(input);

        assertThat(names).containsExactly("pobi", "woni");
    }

    @Test
    void 시도할_횟수를_입력하면_정수로_반환한다() {
        //given
        String input = "5";

        //when
        int tries = parseTries(input);

        //then
        assertThat(tries).isEqualTo(5);
    }

    @Test
    void 시도_횟수가_0회인_경우() {
        assertSimpleTest(
                () -> {
                    run("pobi,woni", "0");
                    assertThat(output()).contains("pobi : ", "woni : ", "최종 우승자 : pobi, woni");
                }
        );
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

    @Test
    void 공동_우승자들을_조회한다() {
        //given
        CarInfo carInfo1 = new CarInfo("pobi", 3);
        CarInfo carInfo2 = new CarInfo("woni", 2);
        CarInfo carInfo3 = new CarInfo("jun", 3);
        List<CarInfo> carInfoList = List.of(carInfo1, carInfo2, carInfo3);

        //when
        List<String> winners = findWinnersNames(carInfoList);

        //then
        assertThat(winners).containsExactly("pobi", "jun");
    }

    @Test
    void 우승자를_출력한다() {
        //given
        List<String> names = List.of("pobi", "jun");

        //when
        printWinners(names);

        //then
        assertThat(output()).isEqualTo("최종 우승자 : pobi, jun");
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
