package racingcar.dto;

import java.util.List;

public class RoundHistory {
    private final List<CarInfo> infoList;

    public RoundHistory(List<CarInfo> carInfos) {
        this.infoList = carInfos;
    }

    public List<CarInfo> getInfoList() {
        return infoList;
    }
}
