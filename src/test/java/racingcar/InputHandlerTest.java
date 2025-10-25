package racingcar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class InputHandlerTest {

    @Test
    void 쉼표로_구분된_이름을_입력하면_개별_이름_리스트로_변환한다() {
        //given
        String input = "pobi,woni";

        //when
        List<String> names = InputHandler.parseCarNames(input);

        assertThat(names).containsExactly("pobi", "woni");
    }

    @Test
    void 시도할_횟수를_입력하면_정수로_반환한다() {
        //given
        String input = "5";

        //when
        int tries = InputHandler.parseTries(input);

        //then
        assertThat(tries).isEqualTo(5);
    }
}