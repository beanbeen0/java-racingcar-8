package racingcar;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        List<String> carNames = getCarNameList();
        int numberOfTries = getNumberOfTries();
        List<Car> cars = createCarsFrom(carNames);
    }

    public static List<String> getCarNameList() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String input = Console.readLine();
        return new ArrayList<>(Arrays.asList(input.split(",")));
    }

    public static int getNumberOfTries() {
        System.out.println("시도할 횟수는 몇 회인가요?");
        String input = Console.readLine();
        return Integer.parseInt(input);
    }

    public static List<Car> createCarsFrom(List<String> names) {
        return names.stream()
                .map(Car::new)
                .toList();
    }
}
