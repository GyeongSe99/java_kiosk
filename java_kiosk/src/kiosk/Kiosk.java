package kiosk;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    /**
     * 키오스크 실행 루프
     */
    public void start() {
        while (true) {
            showMainMenus();
            // 장바구니에 아이템이 있으면 주문/취소 메뉴 추가 출력
            boolean canOrder = !cart.getCartItems().isEmpty();
            if (canOrder) {
                showOrderMenu();
            }

            // 유효 범위 계산 (0: 종료, 1~N: 메뉴, N+1~N+2: 주문/취소)
            int choiceMaxRange = menus.size() + (canOrder ? 2 : 0);
            // 사용자 입력 받기
            int menuChoice = readIntInRange(0, choiceMaxRange);
            // 입력값에 따른 분기 처리
            processChoice(menuChoice, canOrder);
        }
    }

    /**
     * 선택지에 따른 프로세스 분기 처리
     * (0: 종료, 1~N: 메뉴 카테고리, N+1~N+2: 주문/취소)
     * @param menuChoice 사용자가 선택한 값
     * @param canOrder 주문가능여부 (장바구니에 담은 물건이 있는지 없는지에 대한 여부)
     */
    private void processChoice(int menuChoice, boolean canOrder) {
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

    private void handleExit() {
        System.out.println("프로그램을 종료합니다.");
        sc.close();
        System.exit(0);
    }

    /**
     * 장바구니 내역 및 총 금액 확인 후 최종 주문
     */
    private void handleOrder() {
        System.out.println("아래와 같이 주문 하시겠습니까?");
        System.out.println();
        cart.showCartItems();
        int totalPrice = cart.showTotalPrice();
        System.out.println("1. 주문        2. 메뉴판");

        int selectedNum = readIntInRange(1, 2);
        if (selectedNum == 1) {
            System.out.println("할인 정보를 입력해주세요.");
            Stream.of(UserType.values())
                    .forEach(type -> System.out.printf("%d. %s : %d%%%n", type.ordinal()+1, type.getLabel(), type.getRate()));
            int selectedNumForUserType = readIntInRange(1, UserType.values().length);
            UserType userType = UserType.values()[selectedNumForUserType - 1];
            double discounted = totalPrice * (1 - userType.getRate() / 100.0);
            System.out.printf("주문이 완료되었습니다. 금액은 W %.1f 입니다.%n", (double) discounted / 1000);
            cart.resetCartItems();
        }
    }

    private void showMainMenus() {
        System.out.println("[ MAIN MENU ]");
        IntStream.range(0, menus.size())
                .forEach(i -> System.out.printf("%d. %s%n", i + 1, menus.get(i).getCategory()));
        System.out.println("0. 종료");
    }

    /**
     * 메뉴 카테고리 선택 후, 메뉴 선택 및 선택한 메뉴 장바구니 담기
     * @param menu
     */
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

    /**
     * 장바구니에 아이템을 추가할지 사용자에게 확인
     */
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
