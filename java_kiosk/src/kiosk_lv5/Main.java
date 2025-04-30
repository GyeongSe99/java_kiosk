package kiosk_lv5;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Menu burgers = new Menu("Burgers");
        Menu drinks = new Menu("drinks");
        Menu desserts = new Menu("desserts");

        burgers.addMenuItem("ShackBurger", 6900, "토마토, 양상추, 쉑소스가 토핑된 치즈버거");
        burgers.addMenuItem("SmokeShack", 8900, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거");
        burgers.addMenuItem("Cheeseburger", 6900, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거");
        burgers.addMenuItem("Hamburger", 5400, "비프패티를 기반으로 야채가 들어간 기본버거");

        drinks.addMenuItem("Orange juice", 2000, "미닛메이드 오렌지 주스");
        drinks.addMenuItem("Coke", 1500, "코카콜라");
        drinks.addMenuItem("Zero Coke", 1500, "제로 콜라");
        drinks.addMenuItem("Sprite", 1500, "스프라이트");

        desserts.addMenuItem("Soft Serve Cone", 1800, "바닐라 소프트 아이스크림이 담긴 콘");
        desserts.addMenuItem("Apple Pie", 2500, "달콤한 사과 필링이 가득한 따뜻한 파이");
        desserts.addMenuItem("Chocolate Sundae", 3200, "초콜릿 소스와 견과류 토핑이 어우러진 선데이");
        desserts.addMenuItem("Strawberry Shake", 4000, "신선한 딸기 과육이 들어간 밀크쉐이크");
        desserts.addMenuItem("Brownie", 2800, "촉촉한 초코 브라우니로 진한 초콜릿 맛");

        List<Menu> menuList = new ArrayList<>();
        menuList.add(burgers);
        menuList.add(drinks);
        menuList.add(desserts);

        Kiosk kiosk = new Kiosk(menuList);
        kiosk.start();
    }
}
