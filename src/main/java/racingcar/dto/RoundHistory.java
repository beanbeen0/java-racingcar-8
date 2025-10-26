package racingcar.dto;

import java.util.ArrayList;
import java.util.List;
import racingcar.game.RacingCar;

public class RoundHistory {
    private final List<CarInfo> infoList;

    public RoundHistory(List<CarInfo> carInfos) {
        this.infoList = carInfos;
    }

    public List<CarInfo> getInfoList() {
        return infoList;
    }

    public static RoundHistory create(List<RacingCar> racingCars) {
        List<CarInfo> carInfoList = new ArrayList<>();
        for (RacingCar racingCar: racingCars) {
            carInfoList.add(CarInfo.create(racingCar));
        }
        return new RoundHistory(carInfoList);
    }
}
