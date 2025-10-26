package racingcar;

import java.util.List;
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
        List<String> winnersNames = game.getWinnersNames();
        OutputHandler.printGameResult(histories, winnersNames);
    }
}
