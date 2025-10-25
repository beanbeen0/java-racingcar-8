package racingcar;

import java.util.ArrayList;
import java.util.List;
import racingcar.dto.CarInfo;
import racingcar.game.RacingGame;
import racingcar.dto.RoundHistory;
import racingcar.dto.SetupData;
import racingcar.io.InputHandler;
import racingcar.io.OutputHandler;

public class Application {

    public static void main(String[] args) {
        SetupData setupData = InputHandler.getSetupData();
        RacingGame game = new RacingGame();
        game.init(setupData);
        List<RoundHistory> histories = game.race();
        List<String> winnersNames = findWinnersNames(histories.getLast().getInfoList());
        OutputHandler.printGameResult(histories, winnersNames);
    }

    public static List<String> findWinnersNames(List<CarInfo> carInfoList) {
        // 최댓값 얻기
        int max = 0;
        for (CarInfo carInfo : carInfoList) {
            if (carInfo.progress() > max) {
                max = carInfo.progress();
            }
        }

        //최댓값으로 우승자 이름 추출하기
        List<String> winnersName = new ArrayList<>();
        for (CarInfo carInfo : carInfoList) {
            if (carInfo.progress() == max) {
                winnersName.add(carInfo.name());
            }
        }

        return winnersName;
    }
}
