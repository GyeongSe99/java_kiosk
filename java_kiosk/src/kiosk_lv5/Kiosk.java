package kiosk_lv5;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * 메뉴 출력, 입력 받기, 분기 처리(선택한 값에 따른 처리)
 */
public class Kiosk {
    private final List<Menu> menus;
    private Scanner sc = new Scanner(System.in);
    private Cart cart;


    public Kiosk(List<Menu> menus) {
        this.menus = menus;
        this.cart = new Cart();
    }

    public void start() {

        while (true) {
            showMainMenus();

            boolean canOrder = !cart.getCartItems().isEmpty();
            if (canOrder) {
                showOrderMenu();
            }
            int choiceMaxRange = menus.size() + (canOrder ? 2 : 0);
            int menuChoice = readIntInRange(0, choiceMaxRange);

            if (menuChoice == 0) {
                handleExit();
            } else if (menuChoice <= menus.size()) {
                handleMenuItem(menus.get(menuChoice - 1));
            } else if (canOrder && menuChoice == menus.size() + 1) {
                handleOrder();
            } else if (canOrder && menuChoice == menus.size() + 2) {
                System.out.println("장바구니를 초기화하고 진행중인 주문을 취소합니다.");
                cart.resetCartItems();
            } else {
                System.out.println("잘못된 메뉴 번호입니다.");
            }

        }
    }

    private void handleExit() {
        System.out.println("프로그램을 종료합니다.");
        sc.close();
        System.exit(0);
    }

    private void handleOrder() {
        System.out.println("아래와 같이 주문 하시겠습니까?");
        System.out.println();
        cart.showCartItems();
        int totalPrice = cart.showTotalPrice();
        System.out.println("1. 주문        2. 메뉴판");

        int selectedNum = readIntInRange(1, 2);
        if (selectedNum == 1) {
            System.out.printf("주문이 완료되었습니다. 금액은 W %.1f 입니다.%n", (double) totalPrice / 1000);
            cart.resetCartItems();
        }
    }

    private void showMainMenus() {
        System.out.println("[ MAIN MENU ]");
        for (int i = 0; i < this.menus.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, this.menus.get(i).getCategory());
        }
        System.out.println("0. 종료");
    }

    private void handleMenuItem(Menu menu) {
        menu.showMenuItems();
        List<MenuItem> menuItems = menu.getMenuItems();
        int choiceMenuItem = readIntInRange(0, menuItems.size());
        if (choiceMenuItem == 0) {
            return;
        }
        MenuItem menuItem = menuItems.get(choiceMenuItem - 1);
        System.out.printf("선택한 메뉴: %-16s | W %.1f | %s%n", menuItem.getName(), (double) menuItem.getPrice() / 1000, menuItem.getDescription());

        handleAddToCart(menuItem);
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


    private void handleAddToCart(MenuItem menuItem) {
        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인        2. 취소");
        int selectedNum = readIntInRange(1, 2);
        if (selectedNum == 1) {
            cart.addToCart(menuItem);
            System.out.printf("%s이 장바구니에 추가되었습니다. %n", menuItem.getName());
        }
    }

    private void showOrderMenu() {
        int menuSize = menus.size();
        System.out.println("[ ORDER MENU ]");
        System.out.printf("%d. Orders     | 장바구니를 확인 후 주문합니다.%n", menuSize + 1);
        System.out.printf("%d. Cancel     | 진행중인 주문을 취소합니다.%n", menuSize + 2);
    }
}
