package racingcar;

import camp.nextstep.edu.missionutils.Randoms;

public class RacingCar {
    private String name;
    private int currentPosition;

    public RacingCar(String name) {
        this.name = name;
        this.currentPosition = 0;
    }

    public String getName() {
        return name;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void moveRandomly() {
        int numRandom = Randoms.pickNumberInRange(0, 9);
        if (numRandom >= 4) {
            currentPosition++;
        }
    }
}
