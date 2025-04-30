package kiosk;

import java.util.stream.Stream;

public enum UserType {
    NATIONAL_HONOREE("국가유공자", 10),
    SOLDIER("군인", 5),
    STUDENT("학생", 3),
    GENERAL("일반", 0);

    private final String label;
    private final int rate;

    UserType(String label, int rate) {
        this.label = label;
        this.rate = rate;
    }

    public String getLabel() {
        return label;
    }

    public int getRate() {
        return rate;
    }

    public static void showUserTypeMenu() {
        Stream.of(UserType.values())
                .forEach(type -> System.out.printf("%d. %s : %d%%%n", type.ordinal() + 1, type.getLabel(), type.getRate()));
    }
}
