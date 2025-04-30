package kiosk;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputReader {
    private Scanner sc;

    public InputReader() {
        this.sc = new Scanner(System.in);
    }

    public int readIntInRange(int min, int max) {
        int input;
        while (true) {
            try {
                input = sc.nextInt();
                if (input < min || input > max) {
                    System.out.println("범위를 벗어났습니다. 다시 시도해주세요.");
                    continue;
                }
                return input;
            } catch (InputMismatchException e) {
                System.out.println("정수만 입력해주세요.");
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("시스템 오류가 발생했습니다.");
                sc.nextLine();
            }
        }
    }
}
