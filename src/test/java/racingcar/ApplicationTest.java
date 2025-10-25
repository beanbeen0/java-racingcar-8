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
    void 시도_횟수가_0회인_경우() {
        assertSimpleTest(
                () -> {
                    run("pobi,woni", "0");
                    assertThat(output()).contains("pobi : ", "woni : ", "최종 우승자 : pobi, woni");
                }
        );
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

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
