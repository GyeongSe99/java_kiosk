package kiosk_lv5;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
메뉴 출력 및 주문 흐름
 */
public class Kiosk {
    private final List<Menu> menus;
    private Scanner sc = new Scanner(System.in);


    public Kiosk(List<Menu> menus) {
        this.menus = menus;
    }

    public void start() {

        while (true) {
            printMenus();
            int menuChoice = readIntInRange(0, this.menus.size());
            if (menuChoice == 0) {
                System.out.println("프로그램을 종료합니다.");
                sc.close();
                System.exit(0);
            }
            handleMenuItem(menus.get(menuChoice - 1));
        }
    }

    private void printMenus() {
        System.out.println("[ MAIN MENU ]");
        for (int i = 0; i < this.menus.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, this.menus.get(i).getCategory());
        }
        System.out.println("0. 종료");
    }

    private void handleMenuItem(Menu menu) {
        menu.printMenuItems();
        List<MenuItem> menuItems = menu.getMenuItems();
        int choiceMenuItem = readIntInRange(0, menuItems.size());
        if (choiceMenuItem == 0) {
            return;
        }
        MenuItem menuItem = menuItems.get(choiceMenuItem - 1);
        System.out.printf("선택한 메뉴: %-16s | W %.1f | %s%n", menuItem.getName(), (double) menuItem.getPrice() / 1000, menuItem.getDescription());
        System.out.println();
    }

    private int readIntInRange(int min, int max) {
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
