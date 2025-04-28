package kiosk_lv3;

import kiosk.MenuItem;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Kiosk {
    private final List<kiosk.MenuItem> menuItems;

    public Kiosk() {
        this.menuItems = new ArrayList<>();
        kiosk.MenuItem menuItem1 = new kiosk.MenuItem("ShackBurger", 6900, "토마토, 양상추, 쉑소스가 토핑된 치즈버거");
        kiosk.MenuItem menuItem2 = new kiosk.MenuItem("SmokeShack", 8900, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거");
        kiosk.MenuItem menuItem3 = new kiosk.MenuItem("Cheeseburger", 6900, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거");
        kiosk.MenuItem menuItem4 = new kiosk.MenuItem("Hamburger", 5400, "비프패티를 기반으로 야채가 들어간 기본버거");
        this.menuItems.add(menuItem1);
        this.menuItems.add(menuItem2);
        this.menuItems.add(menuItem3);
        this.menuItems.add(menuItem4);
    }

    public void start() {
        Scanner sc = new Scanner(System.in);

        System.out.println("[ SHAKESHACK MENU ]");
        System.out.println();
        for (int i = 0; i < menuItems.size(); i++) {
            kiosk.MenuItem item = menuItems.get(i);
            System.out.printf("%d. %-13s | W %.1f | %s\n", i + 1, item.getName(), (double) item.getPrice() / 1000, item.getDescription());
        }
        System.out.println("0. 종료");
        System.out.println();

        while (true) {
            System.out.print("원하시는 메뉴를 선택해주세요 : ");

            try {
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
                        MenuItem item = menuItems.get(choice - 1);
                        System.out.printf("> %d. %-13s | W %.1f | %s\n", choice, item.getName(), (double) item.getPrice() / 1000, item.getDescription());
                        break;
                    default:
                        System.out.println("잘못된 값 입력");
                        throw new IllegalArgumentException();
                }
            } catch (InputMismatchException e) {
                System.out.println("정수만 입력해주세요.");
                sc.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("0에서 4 사이의 번호만 선택 가능합니다.");
            } catch (Exception e) {
                System.out.println("알 수 없는 오류");
                e.printStackTrace();
            }

        }
    }
}
