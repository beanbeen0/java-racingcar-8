package racingcar;

import camp.nextstep.edu.missionutils.Randoms;

public class RacingCar {
    private String name;
    private int currentPosition;

    public RacingCar(String name) {
        validate(name);
        this.name = name;
        this.currentPosition = 0;
    }

    public String getName() {
        return name;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    // 비즈니스 로직
    public void moveRandomly() {
        int numRandom = Randoms.pickNumberInRange(0, 9);
        if (numRandom >= 4) {
            currentPosition++;
        }
    }

    // 검증 로직
    private void validate(String name) {
        validateNotEmpty(name);
        validateLengthAtMostFive(name);
    }

    private static void validateNotEmpty(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("자동차의 이름을 지정하지 않았습니다.");
        }
    }

    private static void validateLengthAtMostFive(String name) {
        if (name.length() > 5) {
            throw new IllegalArgumentException("자동차의 이름은 5자 이하여야 합니다. : " + name);
        }
    }
}
