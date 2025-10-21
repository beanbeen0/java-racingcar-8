package racingcar;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.List;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static racingcar.Application.getCarNameList;
import static racingcar.Application.getNumberOfTries;

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
        List<Car> cars = Application.createCarsFrom(names);

        assertThat(cars).hasSize(2)
                .extracting(Car::getName)
                .containsExactly("pobi", "woni");
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
