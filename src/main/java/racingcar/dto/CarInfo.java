package racingcar.dto;

import racingcar.game.RacingCar;

public record CarInfo(String name, int progress) {

    public static CarInfo create(RacingCar car) {
        return new CarInfo(car.getName(), car.getCurrentPosition());
    }
}
