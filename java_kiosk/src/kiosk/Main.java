package kiosk;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Kiosk");

        List<MenuItem> menus = new ArrayList<>();

        MenuItem menuItem1 = new MenuItem("ShackBurger", 6900, "토마토, 양상추, 쉑소스가 토핑된 치즈버거");
        MenuItem menuItem2 = new MenuItem("SmokeShack", 8900, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거");
        MenuItem menuItem3 = new MenuItem("Cheeseburger", 6900, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거");
        MenuItem menuItem4 = new MenuItem("Hamburger", 5400, "비프패티를 기반으로 야채가 들어간 기본버거");
        menus.add(menuItem1);
        menus.add(menuItem2);
        menus.add(menuItem3);
        menus.add(menuItem4);

        Scanner sc = new Scanner(System.in);

        System.out.println("[ SHAKESHACK MENU ]");
        System.out.println();
        for (int i = 0; i < menus.size(); i++) {
            MenuItem item = menus.get(i);
            System.out.printf("%d. %-13s | W %.1f | %s\n", i + 1, item.getName(), (double) item.getPrice() / 1000, item.getDescription());
        }
        System.out.println("0. 종료");
        System.out.println();

        while (true) {
            System.out.print("원하시는 메뉴를 선택해주세요 : ");

            int choice = sc.nextInt();
            switch (choice) {
                case 0:
                    System.out.println("프로그램을 종료합니다.");
                    sc.close();
                    System.exit(0);
                case 1:
                case 2:
                case 3:
                case 4:
                    MenuItem item = menus.get(choice - 1);
                    System.out.printf("> %d. %-13s | W %.1f | %s\n", choice, item.getName(), (double) item.getPrice() / 1000, item.getDescription());
                    break;
                default:
                    System.out.println("잘못된 값 입력");
                    break;
            }
        }
    }
}
