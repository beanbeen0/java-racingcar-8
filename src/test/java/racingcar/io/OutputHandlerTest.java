package racingcar.io;

import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import racingcar.Application;
import racingcar.dto.CarInfo;
import racingcar.dto.RoundHistory;

class OutputHandlerTest extends NsTest {

    @Test
    void 한_줄_출력_결과_확인() {
        //given
        CarInfo info = new CarInfo("pobi", 4);

        //when
        OutputHandler.printCarInfo(info);

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
        OutputHandler.printRoundHistory(roundHistory);

        //then
        assertThat(output()).isEqualTo("pobi : ---\nwoni : --");
    }

    @Test
    void 우승자를_출력한다() {
        //given
        List<String> names = List.of("pobi", "jun");

        //when
        OutputHandler.printWinners(names);

        //then
        assertThat(output()).isEqualTo("최종 우승자 : pobi, jun");
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}