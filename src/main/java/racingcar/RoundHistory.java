package racingcar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoundHistory {
    List<CarInfo> infoList;

    private RoundHistory() {
    }

    public RoundHistory(List<CarInfo> carInfos) {
        this.infoList = carInfos;
    }

    public void addInfo(CarInfo carInfo) {
        infoList.add(carInfo);
    }
}
