package kiosk;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Kiosk");

        Scanner sc = new Scanner(System.in);

        System.out.println("[ SHAKESHACK MENU ]");
        System.out.println("1. ShackBurger   | W 6.9 | 토마토, 양상추, 쉑소스가 토핑된 치즈버거");
        System.out.println("2. SmokeShack    | W 8.9 | 베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거");
        System.out.println("3. Cheeseburger  | W 6.9 | 포테이토 번과 비프패티, 치즈가 토핑된 치즈버거");
        System.out.println("4. Hamburger     | W 5.4 | 비프패티를 기반으로 야채가 들어간 기본버거");
        System.out.println("0. 종료");

        int choice = sc.nextInt();

        switch (choice) {
            case 0:
                System.out.println("프로그램을 종료합니다.");
                sc.close();
                System.exit(0);
            case 1:
                System.out.println("ShackBurger");
                break;
            case 2:
                System.out.println("SmokeShack");
                break;
            case 3:
                System.out.println("Cheeseburger");
                break;
            case 4:
                System.out.println("Hamburger");
                break;
            default:
                System.out.println("잘못된 값 입력");
                break;
        }
    }
}
